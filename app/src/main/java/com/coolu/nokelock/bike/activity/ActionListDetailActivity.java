package com.coolu.nokelock.bike.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.base.BaseActivity;
import com.coolu.nokelock.bike.bean.JoinBean;
import com.coolu.nokelock.bike.url.Url;
import com.coolu.nokelock.bike.util.BaseUtil;
import com.coolu.nokelock.bike.util.ConditionsUtils;
import com.coolu.nokelock.bike.util.Config;
import com.coolu.nokelock.bike.util.RoundedCornersTransformation;
import com.coolu.nokelock.bike.util.VolleyController;
import com.coolu.nokelock.bike.util.VolleyUtils;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.fitsleep.sunshinelibrary.utils.UtilSharedPreference;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

public class ActionListDetailActivity extends BaseActivity {

    private String jumpImg;
    private ImageView img;

    private String id;
    private ImageView btn;
    private ImageView back;
    private String title;
    private TextView title_tv;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 101:
                    JoinBean obj = (JoinBean) msg.obj;
                    if (obj.getErrorCode()==200){
                        Toast.makeText(ActionListDetailActivity.this,"参与成功",Toast.LENGTH_LONG).show();
                    }else if (obj.getErrorCode()==300){
                        Toast.makeText(ActionListDetailActivity.this,"活动已达到最大参与次数",Toast.LENGTH_LONG).show();
                    }else if ("301".equals(obj.getErrorCode())) {
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
                    }
                    handler.sendEmptyMessageDelayed(102,2000);
                    break;
                case 102:
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏文字颜色及图标为深色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_action_list_detail);
        back = (ImageView)findViewById(R.id.id_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        jumpImg = getIntent().getStringExtra("jump");
        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");
        title_tv = (TextView)findViewById(R.id.id_title_toolbar);
        if (title!=null){
            title_tv.setText(title);
        }
        img = (ImageView)findViewById(R.id.id_action_content);
        if (jumpImg!=null){
            Picasso.with(ActionListDetailActivity.this).load(jumpImg).transform(new RoundedCornersTransformation(12,0)).into(img);
        }

        btn = (ImageView)findViewById(R.id.id_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ActionListDetailActivity.this,"参与成功",Toast.LENGTH_SHORT).show();
                handler.sendEmptyMessageDelayed(102,2000);
//                HashMap map=new HashMap();
//                map.put("token",UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(),Config.TOKEN));
//                map.put("id",id!=null? id:-1);
//                VolleyUtils.deStringPost(ActionListDetailActivity.this, Url.ACTION_JION, map, "join", new VolleyUtils.volleyListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//                    }
//
//                    @Override
//                    public void onErrorResponse(String errorMessage) {
//                      //  Log.e("ppp",response.toString());
//                    }
//
//                    @Override
//                    public void onResponse(String response) {
//                      //  Log.e("ppp",response.toString());
//                        JoinBean joinBean = VolleyUtils.parseJsonWithGson(response.toString(), JoinBean.class);
//
//
//                        Message message=new Message();
//                        message.obj=joinBean;
//                        message.what=101;
//                        handler.sendMessage(message);
//
//
//                    }
//                });
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
     //   VolleyController.getInstance(this.getApplicationContext()).cancelPendingRequests("join");
    }
}
