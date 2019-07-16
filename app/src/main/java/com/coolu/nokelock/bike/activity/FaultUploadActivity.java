package com.coolu.nokelock.bike.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v4.widget.TextViewCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.base.BaseActivity;
import com.coolu.nokelock.bike.url.Url;
import com.coolu.nokelock.bike.util.BaseUtil;
import com.coolu.nokelock.bike.util.ConditionsUtils;
import com.coolu.nokelock.bike.util.Config;
import com.coolu.nokelock.bike.util.OkHttpClientManager;
import com.fitsleep.sunshinelibrary.utils.DialogUtils;
import com.fitsleep.sunshinelibrary.utils.MPermissionsActivity;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.fitsleep.sunshinelibrary.utils.UtilSharedPreference;
import com.squareup.picasso.Picasso;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okhttp3.Request;

public class  FaultUploadActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back;
    private ImageView camera;
    String cameraPath;
    public final static int ALBUM_REQUEST_CODE = 1;
    public final static int CROP_REQUEST = 2;
    public final static int CAMERA_REQUEST_CODE = 3;
    public static String SAVED_IMAGE_DIR_PATH =
            Environment.getExternalStorageDirectory().getPath()
                    + "/NokeLockCach";// 拍照路径
    private ImageView showBitmap;

    Bitmap bm=null;
    Bitmap bitmap=null;
    private EditText edit;
    private RelativeLayout scan_relative;
    private ImageView fault_image;
    private String code="";
    private EditText number_edit;
    private TextView submit;
    private Dialog loadingDialog;
    private Handler handler=new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏文字颜色及图标为深色
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_post_gz);

        back = (ImageView)findViewById(R.id.id_back);
        camera =(ImageView) findViewById(R.id.id_photo);
         showBitmap =(ImageView) findViewById(R.id.id_photo_result);
        back.setOnClickListener(this);
        camera.setOnClickListener(this);
        //编号
        number_edit = (EditText)findViewById(R.id.id_fault_number_edit);
        //描述
        edit = (EditText) findViewById(R.id.tv_decs);
        //二维码
        fault_image = (ImageView)findViewById(R.id.id_fault_number_image);
        fault_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FaultUploadActivity.this,CaptureActivity.class);
                startActivityForResult(intent,102); //请求码
            }
        });

        //提交故障
        submit = (TextView)findViewById(R.id.id_submit_tv);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //上传信息


                String number = number_edit.getText().toString().trim(); //二维码
                String edit_content = edit.getText().toString().trim(); //描述
                if (!TextUtils.isEmpty(number)&&!TextUtils.isEmpty(edit_content)&&!TextUtils.isEmpty(cameraPath)){
                    submit.setClickable(false);
                    loadingDialog = DialogUtils.getLoadingDialog(FaultUploadActivity.this, "");
                    loadingDialog.show();
                    postHead(cameraPath,number,edit_content);
                }else{
                    ToastUtils.showMessage("车辆编号，描述，故障照片不能为空");
                }


            }
        });

    }


    /**
     * 上传故障
     * @param cameraPath 图片的路径
     * @param number  二维码
     * @param edit_content   描述
     */
    private void postHead(String cameraPath, final String number, String edit_content) {


        File file = new File(cameraPath);
        if (!file.exists())
        {
            return;
        }
        Log.e("nnnn","Name"+file.getName());
        if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
            try {
                OkHttpClientManager.postAsyn(
                        Url.TOU,
                        new OkHttpClientManager.StringCallback() {
                            @Override
                            public void onFailure(Request request, IOException e) {
                                Log.e("nnnn", e.toString());
                                submit.setClickable(true);
                                loadingDialog.dismiss();
                                loadingDialog = null;
                                ToastUtils.showMessage("上传故障失败");
                            }

                            @Override
                            public void onResponse(String response) {

                                Log.e("nnnn", response.toString());
                                submit.setClickable(true);
                                loadingDialog.dismiss();
                                loadingDialog = null;
                                try {
                                    JSONObject jsonObject = new JSONObject(response.toString());
                                    String errorCode = jsonObject.getString("errorCode");
                                    Log.e("nnnn", "errorCode" + errorCode);
                                    if ("200".equals(errorCode)) {
                                        ToastUtils.showMessage("上传故障成功");

                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                finish();
                                            }
                                        }, 200);


                                    } else if ("301".equals(errorCode)) {
                                        App.getInstance().getIBLE().disconnect();
                                        UtilSharedPreference.saveString(ConditionsUtils.getContext(), Config.PHONE, "");
                                        UtilSharedPreference.saveString(ConditionsUtils.getContext(), Config.TOKEN, "");
                                        App.getInstance().getDaoSession().getUseBeanDao().deleteAll();
                                        App.getInstance().getDaoSession().getSearchBeanDao().deleteAll();
                                        App.getInstance().getDaoSession().getBikeOrderBeanDao().deleteAll();
                                        App.getInstance().getDaoSession().getOrderBeanDao().deleteAll();
                                        //个人信息清空
                                        App.getInstance().setUserEntityBean(null);
                                        ToastUtils.showMessage("登陆失效，请重新登陆");
                                    } else {
                                        //  ToastUtils.showMessage("上传故障失败");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        file,
                        "file",
                        new OkHttpClientManager.Param[]{
                                new OkHttpClientManager.Param("moKuai", "problempic"),
                                new OkHttpClientManager.Param("barCode", number),
                                new OkHttpClientManager.Param("beiZhu", edit_content),
                                new OkHttpClientManager.Param("token", UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(), Config.TOKEN))

                        }
                );


            } catch (Exception e) {
                e.printStackTrace();
            }
        }




    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CAMERA_REQUEST_CODE){
            String state = Environment.getExternalStorageState();
            if (state.equals(Environment.MEDIA_MOUNTED)) {
                bm = BitmapFactory.decodeFile(cameraPath);
               // Bitmap bitmap = BaseUtil.resizeBitmap(cameraPath, 200, 250);
                if (bm!=null){
                    //  Bitmap bitmap1 = BaseUtil.resizeBitmap(bitmap, 32, 32);
                    bitmap = BaseUtil.centerSquarScaleBitmap(bm, 370);

                    if (showBitmap!=null){
                        showBitmap.setVisibility(View.VISIBLE);
                        showBitmap.setImageBitmap(bitmap);
                    }
                }
            }
        }

        if (requestCode==102&&resultCode==Activity.RESULT_OK){
            Bundle extras = data.getExtras();
            code = extras.getString("result");
            Log.e("nnnn","啊哈哈哈");
            Log.e("nnnn","code"+code);



            if (!TextUtils.isEmpty(code)&&code.contains("=")) {
                number_edit.setText(code.substring(code.lastIndexOf("=")+1));
            }else {
                ToastUtils.showMessage("获取二维码信息失败");
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bm!=null){
            bm.recycle();
        }

        if (bitmap!=null){
            bitmap.recycle();
        }

    }
    Uri uri=null;
    @Override
    public void permissionSuccess(int requestCode) {
        super.permissionSuccess(requestCode);
        // 指定相机拍摄照片保存地址
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            cameraPath = SAVED_IMAGE_DIR_PATH + System.currentTimeMillis() + ".png";
            Intent intent = new Intent();
            // 指定开启系统相机的Action
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            String out_file_path = SAVED_IMAGE_DIR_PATH;
            File dir = new File(out_file_path);
            if (!dir.exists()) {
                dir.mkdirs();
            } // 把文件地址转换成Uri格式
            if (Build.VERSION.SDK_INT >= 24){
                uri = FileProvider.getUriForFile(FaultUploadActivity.this, "com.coolu.nokelock.bike.fileprovider", new File(cameraPath));//通过FileProvider创建一个content类型的Uri
            }else {
                 uri = Uri.fromFile(new File(cameraPath));
            }
            if (Build.VERSION.SDK_INT >= 24) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
            }

            // 设置系统相机拍摄照片完成后图片文件的存放地址
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, CAMERA_REQUEST_CODE);
        } else {
            Toast.makeText(getApplicationContext(), "请确认已经插入SD卡",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_back:
                this.finish();
                break;
            case R.id.id_photo:
                requestPermission(new String[]{Manifest.permission.CAMERA},101); //相机授权


                break;
        }
    }
}
