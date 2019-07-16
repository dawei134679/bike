package com.coolu.nokelock.bike.activity;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.amap.api.maps.model.MarkerOptions;


import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.base.BaseActivity;
import com.coolu.nokelock.bike.zxing.CameraManager;
import com.coolu.nokelock.bike.zxing.CaptureActivityHandler;
import com.coolu.nokelock.bike.zxing.InactivityTimer;
import com.coolu.nokelock.bike.zxing.ViewfinderView;
import com.fitsleep.sunshinelibrary.inter.OnItemClickListener;
import com.fitsleep.sunshinelibrary.utils.KeyboardUtils;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.fitsleep.sunshinelibrary.view.AlertView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;


import java.io.IOException;
import java.util.Vector;

/**
 * Initial the camera
 * @author Ryan.Tang
 */
public class CaptureActivity extends BaseActivity implements Callback,View.OnClickListener {

	private CaptureActivityHandler handler;
	private ViewfinderView viewfinderView;
	private boolean hasSurface;
	private Vector<BarcodeFormat> decodeFormats;
	private String characterSet;
	private InactivityTimer inactivityTimer;
	private MediaPlayer mediaPlayer;
	private boolean playBeep;
	private static final float BEEP_VOLUME = 0.10f;
	private boolean vibrate;
	private Button cancelScanButton;
	private ImageView input;
	private ImageView open;

	public static final int MM=999;
	private ImageView back;
	private EditText etName;
	private String flag;


	//private static final String address="50:8C:B1:5F:D9:AD";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//设置状态栏透明
		this.getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
				WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		setContentView(R.layout.camera);
		flag = getIntent().getStringExtra("card_flag");

		//activity=this;
		//ViewUtil.addTopView(getApplicationContext(), this, R.string.scan_card);
		back = (ImageView)findViewById(R.id.id_back);
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
		CameraManager.init(getApplication());
		viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
		if (flag!=null&&TextUtils.equals(flag,"card")){
			viewfinderView.text_scan="对准商家的二维码";
		}else{
			viewfinderView.text_scan="对准车上的二维码";
		}
		input = (ImageView)findViewById(R.id.input);
		open = (ImageView)findViewById(R.id.open);
		input.setOnClickListener(this);
		open.setOnClickListener(this);

		//cancelScanButton = (Button) this.findViewById(R.id.btn_cancel_scan);
		hasSurface = false;
		inactivityTimer = new InactivityTimer(this);
	}


	/**
	 * 输入锁编号
	 */
	private void inputNumber() {
		final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		final AlertView mAlertViewExt = new AlertView("提示", "请输入车辆编号！", "取消", null, new String[]{"完成"}, this, AlertView.Style.Alert, new OnItemClickListener() {
			@Override
			public void onItemClick(Object o, int position) {
				KeyboardUtils.hideSoftInput(CaptureActivity.this);
				if (position == 0) {
					final String newPassword = etName.getText().toString().trim();
					if (TextUtils.isEmpty(newPassword)) {
						ToastUtils.showMessage("请输入编号");
						return;
					}
					Intent intent = new Intent().putExtra("result", newPassword);
					intent.putExtra("flag",true);
					setResult(RESULT_OK, intent);
					finish();
				}
			}
		});
		ViewGroup extView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.alertext_form, null);
		etName = (EditText) extView.findViewById(R.id.etName);
		etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View view, boolean focus) {
				//输入框出来则往上移动
				boolean isOpen = imm.isActive();
				mAlertViewExt.setMarginBottom(isOpen && focus ? 120 : 0);
				System.out.println(isOpen);
			}
		});
		mAlertViewExt.addExtView(extView);
		mAlertViewExt.show();
	}







	@Override
	protected void onResume() {
		super.onResume();
		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
		SurfaceHolder surfaceHolder = surfaceView.getHolder();
		if (hasSurface) {
			initCamera(surfaceHolder);
		} else {
			surfaceHolder.addCallback(this);
			/**
			 * SURFACE_TYPE_PUSH_BUFFERS表明该Surface不包含原生数据，Surface用到的数据由其他对象提供�?
			 * * 在Camera图像预览中就使用该类型的Surface，有Camera负责提供给预览Surface数据，这样图像预览会比较流畅�?			 */
			surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}
		decodeFormats = null;
		characterSet = null;

		playBeep = true;
		AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
		if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
			playBeep = false;
		}
		initBeepSound();
		vibrate = true;

		//quit the scan view
//
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (handler != null) {
			handler.quitSynchronously();
			handler = null;
		}
		CameraManager.get().closeDriver();
	}

	@Override
	protected void onDestroy() {
		inactivityTimer.shutdown();
		super.onDestroy();



	}

	/**
	 * 得到扫描结果
	 * Handler scan result
	 * @param result
	 * @param barcode
	 */
	public void handleDecode(Result result, Bitmap barcode) {
		inactivityTimer.onActivity();
		playBeepSoundAndVibrate();
		String resultString = result.getText();
		//FIXME
		if (resultString.equals("")) {
			Toast.makeText(CaptureActivity.this, "Scan failed!", Toast.LENGTH_SHORT).show();
		}else {
            Log.e("nnnn","resultString==="+resultString);
			System.out.println("Result:"+resultString);
			Intent resultIntent = new Intent();
			Bundle bundle = new Bundle();
			bundle.putString("result", resultString);
			bundle.putBoolean("flag",false);
			resultIntent.putExtras(bundle);
			this.setResult(Activity.RESULT_OK, resultIntent);

			//App.getInstance().setResult(resultString);

		}
		CaptureActivity.this.finish();
	}

	private void initCamera(SurfaceHolder surfaceHolder) {
		try {
			CameraManager.get().openDriver(surfaceHolder);
		} catch (IOException ioe) {
			return;
		} catch (RuntimeException e) {
			return;
		}
		if (handler == null) {
			handler = new CaptureActivityHandler(this, decodeFormats, characterSet);
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (!hasSurface) {
			hasSurface = true;
			initCamera(holder);
		}

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		hasSurface = false;

	}

	public ViewfinderView getViewfinderView() {
		return viewfinderView;
	}

	public Handler getHandler() {
		return handler;
	}

	public void drawViewfinder() {
		viewfinderView.drawViewfinder();

	}

	private void initBeepSound() {
		if (playBeep && mediaPlayer == null) {
			// The volume on STREAM_SYSTEM is not adjustable, and users found it
			// too loud,
			// so we now play on the music stream.
			setVolumeControlStream(AudioManager.STREAM_MUSIC);
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setOnCompletionListener(beepListener);

			AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.beep);
			try {
				mediaPlayer.setDataSource(file.getFileDescriptor(), file.getStartOffset(), file.getLength());
				file.close();
				mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
				mediaPlayer.prepare();
			} catch (IOException e) {
				mediaPlayer = null;
			}
		}
	}

	private static final long VIBRATE_DURATION = 200L;

	private void playBeepSoundAndVibrate() {
		if (playBeep && mediaPlayer != null) {
			mediaPlayer.start();
		}
		if (vibrate) {
			Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
			vibrator.vibrate(VIBRATE_DURATION);
		}
	}

	/**
	 * When the beep has finished playing, rewind to queue up another one.
	 */
	private final OnCompletionListener beepListener = new OnCompletionListener() {
		public void onCompletion(MediaPlayer mediaPlayer) {
			mediaPlayer.seekTo(0);
		}
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.input:
				Toast.makeText(CaptureActivity.this,"手指",Toast.LENGTH_SHORT).show();
			//	InputDialog dialog=new InputDialog(CaptureActivity.this);
				//dialog.createDialog();

                inputNumber();
				break;
			case R.id.open:
				//Toast.makeText(CaptureActivity.this,"手电",Toast.LENGTH_SHORT).show();
				CameraManager.get().flashHandler();
				break;
		}
	}


}