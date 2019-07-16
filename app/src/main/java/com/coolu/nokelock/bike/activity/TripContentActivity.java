package com.coolu.nokelock.bike.activity;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.base.BaseActivity;
import com.coolu.nokelock.bike.bean.TripBean;
import com.coolu.nokelock.bike.util.BaseUtil;
import com.coolu.nokelock.bike.util.CircleTransform;
import com.coolu.nokelock.bike.util.DialogShowUtils;
import com.fitsleep.sunshinelibrary.utils.ScreenUtils;
import com.squareup.picasso.Picasso;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TripContentActivity extends BaseActivity {

    private ImageView back;
    private TripBean tripBean;
    private TextView min;
    private TextView phone;
    private Dialog fen;
    @BindView(R.id.id_fenxiang)
    TextView fen_tv;
    private TextView kcal;
    private TextView dis;
    private ImageView touxiang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏文字颜色及图标为深色
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_trip_content);
        Bundle extras = getIntent().getExtras();
        tripBean =(TripBean) extras.getSerializable("tripBean");
        int postion = extras.getInt("postion");
        ButterKnife.bind(this);
//        for (int i=2;i<tripBean.getResult().size();i++){
//            Log.e("ww",tripBean.getResult().get(i).getKcal()+" 时间 "+tripBean.getResult().get(i).getUseMinute()+"历程"+tripBean.getResult().get(i).getRuns());
//
//        }
        back = (ImageView) findViewById(R.id.id_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        min = (TextView) findViewById(R.id.id_trip_min);
        if (tripBean.getResult().get(postion).getUseMinute()!=null){
            min.setText(tripBean.getResult().get(postion).getUseMinute());
        }

        phone = (TextView)findViewById(R.id.id_trip_phone);
        if (tripBean.getResult().get(postion).getUserId()!=null){
            phone.setText(tripBean.getResult().get(postion).getUserId());
        }
        kcal = (TextView)findViewById(R.id.id_trip_cal);
        if (tripBean.getResult().get(postion).getKcal()!=null){
            kcal.setText(tripBean.getResult().get(postion).getKcal());
        }
        dis = (TextView)findViewById(R.id.id_trip_dis);
        if (tripBean.getResult().get(postion).getRuns()!=null){
            dis.setText(tripBean.getResult().get(postion).getRuns());
        }

        touxiang = (ImageView)findViewById(R.id.id_trip_touxiang);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (App.getInstance().getUserEntityBean()!=null&&App.getInstance().getUserEntityBean().getUserPic()!=null){
            Log.e("nnnn","ooooo"+App.getInstance().getUserEntityBean().getUserPic());

            Picasso.with(TripContentActivity.this).load(App.getInstance().getUserEntityBean().getUserPic()).resize(240,240).transform(new CircleTransform()).into(touxiang);
        }
    }

    //分享
    @OnClick(R.id.id_fenxiang)
    public void fen(){
        if (fen==null){
            fen = DialogShowUtils.showDialogResult(this, R.layout.dialog_fenxiang_layout,1);
        }
        fen.setCancelable(true);
        fen.setCanceledOnTouchOutside(true);
        //微信好友
      ImageView   wechat=(ImageView)fen.findViewById(R.id.id_wechat);
        wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //    DialogShowUtils.share(TripContentActivity.this,0);
               // api.sendReq(DialogShowUtils.share(MainActivity.this, 0));
                //得到分享的截图
                Bitmap bitmap = ScreenUtils.captureWithStatusBar(TripContentActivity.this);
                App.getInstance().getApi().sendReq(DialogShowUtils.share(TripContentActivity.this,bitmap,0));
            }
        });
        //朋友圈
        ImageView quan=(ImageView)fen.findViewById(R.id.id_quan);
        quan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = ScreenUtils.captureWithStatusBar(TripContentActivity.this);
                App.getInstance().getApi().sendReq(DialogShowUtils.share(TripContentActivity.this,bitmap,1));
            }
        });

        fen.show();

    }


}
