package com.coolu.nokelock.bike.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.base.BaseActivity;
import com.coolu.nokelock.bike.url.Url;
import com.coolu.nokelock.bike.util.BaseUtil;
import com.coolu.nokelock.bike.util.ConditionsUtils;
import com.coolu.nokelock.bike.util.Config;
import com.coolu.nokelock.bike.util.VolleyController;
import com.coolu.nokelock.bike.util.VolleyUtils;
import com.fitsleep.sunshinelibrary.utils.DialogUtils;
import com.fitsleep.sunshinelibrary.utils.EncryptUtils;
import com.fitsleep.sunshinelibrary.utils.IntentUtils;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.fitsleep.sunshinelibrary.utils.UtilSharedPreference;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SafeActivity extends BaseActivity {

    private ImageView safe_no;
    @BindView(R.id.id_safe_yes)
    ImageView safe_yes;
    @BindView(R.id.id_safe_tv)
    TextView safe_tv;
    @BindView(R.id.id_active_one)
    TextView active_one;
    @BindView(R.id.id_active_two)
    TextView active_two;
    @BindView(R.id.id_active_three)
    TextView active_three;
    private String flag;
    @BindView(R.id.id_safe_img)
    ImageView img;
    @BindView(R.id.id_title_toolbar)
    TextView title;

    private Dialog loadingDialog;


    private Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏文字颜色及图标为深色
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_safe);
        ButterKnife.bind(this);
        ImageView back =(ImageView) findViewById(R.id.id_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        safe_no = (ImageView)findViewById(R.id.id_safe_no);
        safe_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        flag = getIntent().getStringExtra("flag");
        if ("safe".equals(flag)){
            active_one.setVisibility(View.GONE);
            active_two.setVisibility(View.GONE);
            active_three.setVisibility(View.GONE);

        }else if ("active".equals(flag)){
            img.setImageResource(R.mipmap.success1);
            active_one.setVisibility(View.VISIBLE);
            active_two.setVisibility(View.VISIBLE);
            active_three.setVisibility(View.VISIBLE);
            title.setText("激活成功");
            safe_tv.setVisibility(View.GONE);
            safe_yes.setVisibility(View.GONE);
            safe_no.setVisibility(View.GONE);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(SafeActivity.this,ActionActivity.class);
                    intent.putExtra("flag","member_success");
                    startActivity(intent);
                }
            },2000);

        }
    }

    @OnClick(R.id.id_safe_yes)
    void setSafe_yes(){
        if (!("0".equals(App.getInstance().getUserEntityBean().getUserStatus()))){
            ToastUtils.showMessage("当前用户状态异常,请联系客服后重试！");
        }else {
            loadingDialog = DialogUtils.getLoadingDialog(this, "");
            loadingDialog.show();

            HashMap<String, String> map = VolleyUtils.getCommomParameter();
            map.put("token", UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(), Config.TOKEN));
            map.put("payWhat", "退保证金");
            if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
                VolleyUtils.deStringPost(SafeActivity.this, Url.TUI, map, "tui", new VolleyUtils.volleyListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }

                    @Override
                    public void onErrorResponse(String errorMessage) {
                        // Log.e("llll","mm"+errorMessage);
                        if (loadingDialog != null) {
                            loadingDialog.dismiss();
                            loadingDialog = null;
                        }
                        ToastUtils.showMessage("请求失败");
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e("llll", response.toString());

                        resolveJson(response, flag);
                    }

                });
            }
        }
    }

    private void resolveJson(String response, String flag) {
        if (loadingDialog!=null){
            loadingDialog.dismiss();
            loadingDialog=null;
        }
        try {
            JSONObject jsonObject=new JSONObject(response.toString());
            Log.e("opo",response.toString());
            String errorCode = jsonObject.getString("errorCode");

            if (TextUtils.equals("200",errorCode)){
                ToastUtils.showMessage("退款成功，2分钟到账，请查收");
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                },2000);
            }else if ("301".equals(errorCode)){
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
            } else{
                ToastUtils.showMessage("退保证金失败或者保证金已退");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VolleyController.getInstance(SafeActivity.this).getRequestQueue().cancelAll("tui");

    }

    @Override
    protected void onResume() {
        super.onResume();
        VolleyUtils.GetPerson();
    }
}
