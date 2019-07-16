package com.coolu.nokelock.bike.activity;

import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.base.BaseActivity;
import com.coolu.nokelock.bike.url.Url;
import com.coolu.nokelock.bike.util.ConditionsUtils;
import com.coolu.nokelock.bike.util.Config;
import com.coolu.nokelock.bike.util.VolleyController;
import com.coolu.nokelock.bike.util.VolleyUtils;
import com.fitsleep.sunshinelibrary.utils.DialogUtils;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.fitsleep.sunshinelibrary.utils.UtilSharedPreference;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class NcActivity extends BaseActivity {

    private TextView submit;
    private EditText edit;
    private Dialog loadingDialog;
    private String content;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 101:
                    finish();
                    break;
            }
        }
    };
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏文字颜色及图标为深色
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_nc);
        edit = (EditText)findViewById(R.id.id_person_name);
        submit = (TextView)findViewById(R.id.id_submit_tv);
        submit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                content = edit.getText().toString().toString();
                if (!TextUtils.isEmpty(content)){
                    submit.setClickable(false);
                    loadingDialog = DialogUtils.getLoadingDialog(NcActivity.this, "");
                    loadingDialog.show();
                    postHost(content);
                }
            }


        });
        back = (ImageView)findViewById(R.id.id_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void postHost(String content) {
        HashMap<String, String> map = VolleyUtils.getCommomParameter();
        map.put("token", UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(), Config.TOKEN));
        map.put("nicName",content);

        if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
            VolleyUtils.deStringPost(NcActivity.this, Url.NICK, map, "nc", new VolleyUtils.volleyListener() {
                @Override
                public void onResponse(JSONObject response) {

                }

                @Override
                public void onErrorResponse(String errorMessage) {
                    if (loadingDialog != null) {
                        loadingDialog.dismiss();
                        loadingDialog = null;
                    }
                    submit.setClickable(true);
                    ToastUtils.showMessage("认证失败");
                }

                @Override
                public void onResponse(String response) {
                    Log.e("nc", response.toString());
                    if (loadingDialog != null) {
                        loadingDialog.dismiss();
                        loadingDialog = null;
                    }
                    submit.setClickable(true);
                    try {
                        JSONObject ob = new JSONObject(response.toString());
                        String errorCode = ob.getString("errorCode");
                        if ("200".equals(errorCode)) {
                            ToastUtils.showMessage("修改成功");
                            VolleyUtils.GetPerson();
                            handler.sendEmptyMessageAtTime(101, 500);

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
                            ToastUtils.showMessage("身份信息已经添加过等待审核");
                            handler.sendEmptyMessageAtTime(101, 500);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        VolleyController.getInstance(NcActivity.this).getRequestQueue().cancelAll("nc");
    }
}
