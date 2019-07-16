package com.coolu.nokelock.bike.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.base.BaseActivity;
import com.coolu.nokelock.bike.url.Url;
import com.coolu.nokelock.bike.util.BaseUtil;
import com.coolu.nokelock.bike.util.Config;
import com.coolu.nokelock.bike.util.VolleyUtils;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.fitsleep.sunshinelibrary.utils.UtilSharedPreference;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Request;

/*
*会员激活界面
 */
public class ActivationActivity extends BaseActivity {


    private Button ctivation_button;
    private AlertDialog alertDialog;
    private TextView active_content;
    private TextView que;
    private ImageView back;
    private TextView active_tv;
    private String card=null; //兑换码
    private int  flag=-1;
    private String cardId=null;
    private TextView toolbar_title;
    private TextView activation_date;
    private TextView content_time;
    private TextView activation_go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        //设置状态栏透明
//        this.getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_activation);
        //兑换码
        card = getIntent().getStringExtra("card");
        //二维码扫描骑行卡
        flag = getIntent().getIntExtra("flag",-1);
        Log.e("nnnn","flag"+flag);
        cardId = getIntent().getStringExtra("cardId"); //扫描的骑行卡编号
        initView();

    }

    private void initView() {

        back = (ImageView)findViewById(R.id.id_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbar_title = (TextView)findViewById(R.id.id_title_toolbar);
        activation_date = (TextView)findViewById(R.id.id_activation_date);
        content_time = (TextView)findViewById(R.id.id_activation_centent);
        activation_go = (TextView)findViewById(R.id.id_activation_tv);
        if (flag==2){
            toolbar_title.setText("酷游会员购买");
            activation_date.setText("欢迎购买畅骑卡");
            content_time.setText("购买类型：酷游365天畅骑卡");
            activation_go.setText("确认购买");
        }

        activation_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ActivationActivity.this,RechargeActivity.class);
                intent.putExtra("cardId",cardId);
                //骑行卡激活界面
                intent.putExtra("flag",flag);
                startActivity(intent);
            }
        });


    }

    //兑换卡卷的请求
    private void postCard() {
        HashMap<String, String> map = VolleyUtils.getCommomParameter();
        map.put("token",UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(), Config.TOKEN));
        map.put("cardKey",card);
        if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
            VolleyUtils.deStringPost(ActivationActivity.this, Url.EXCARD, map, "card", new VolleyUtils.volleyListener() {
                @Override
                public void onResponse(JSONObject response) {

                }

                @Override
                public void onErrorResponse(String errorMessage) {
                    active_tv.setClickable(true);
                }

                @Override
                public void onResponse(String response) {
                    active_tv.setClickable(true);

                    Log.e("nnnn", response.toString());
                    try {
                        JSONObject object = new JSONObject(response.toString());
                        String errorCode = object.getString("errorCode");
                        if ("200".equals(errorCode)) {
                            Intent intent = new Intent(ActivationActivity.this, SafeActivity.class);
                            intent.putExtra("flag", "active");
                            startActivity(intent);
                        } else {
                            ToastUtils.showMessage("兑换失败，已使用或卡密错误");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }


    }




//
//    /*
//   * 创建对话框
//   */
//    public void createDialog(String content){
//        alertDialog = new AlertDialog.Builder(ActivationActivity.this).setMessage("                        "+content).create();
//        alertDialog.show();
//    }

//    /**
//     * 延迟操作
//     * @param time
//     */
//    public void delay_operation(long time){
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                alertDialog.dismiss();
//                App.getInstance().setMember(true);
//                try {
//                    Thread.sleep(500);
//                   // Intent intent=new Intent(ActivationActivity.this,ActionActivity.class);
//                   // startActivity(intent);
//                    finish();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        }, time);
//    }


}
