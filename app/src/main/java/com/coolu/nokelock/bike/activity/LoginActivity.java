package com.coolu.nokelock.bike.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.base.BaseActivity;
import com.coolu.nokelock.bike.bean.CardTipBean;
import com.coolu.nokelock.bike.bean.YanzhengBean;
import com.coolu.nokelock.bike.url.Url;
import com.coolu.nokelock.bike.util.Config;
import com.coolu.nokelock.bike.util.MD5Utils;
import com.coolu.nokelock.bike.util.VolleyController;
import com.coolu.nokelock.bike.util.VolleyUtils;
import com.fitsleep.sunshinelibrary.utils.EncryptUtils;
import com.fitsleep.sunshinelibrary.utils.IntentUtils;
import com.fitsleep.sunshinelibrary.utils.Logger;
import com.fitsleep.sunshinelibrary.utils.MPermissionsActivity;
import com.fitsleep.sunshinelibrary.utils.RegularUtils;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.fitsleep.sunshinelibrary.utils.ToolsUtils;
import com.fitsleep.sunshinelibrary.utils.UtilSharedPreference;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;


public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_code)
    TextView btCode;
    @BindView(R.id.cb_select)
    CheckBox cbSelect;
    @BindView(R.id.user_xy)
    TextView userXy;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.id_back)
    ImageView back;

    private final static int MAX_S = 60 * 1000;
    private final static int COUNT_S = 1000;
    private DownTimer timer;
    private String phone;
    private PopupWindow yangzhengPopupWindow;
    //    private Handler handler=new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what){
//                case 0:
//                    yanBean = (YanzhengBean) msg.obj;
//                    initYanZhengPopWindow();
//                    break;
//                case 1:
//                    Picasso.with(LoginActivity.this).load(yanBean.getResult().getImageurl()).into(iv_two_code);
//                    break;
//            }
//        }
//    };
    private YanzhengBean yanBean;
    private ImageView iv_two_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏透明
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        timer = new DownTimer(MAX_S, COUNT_S);
//        findViewById(R.id.iv_break).setVisibility(View.VISIBLE);
//        findViewById(R.id.iv_break).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                IntentUtils.finish(LoginActivity.this);
//            }
//        });
    }

    @OnClick(R.id.id_back)
    void back() {
        finish();
    }


    @Override
    public void permissionSuccess(int requestCode) {
        super.permissionSuccess(requestCode);
        if (checkPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA})) {
//            JSONObject jsonObject = new JSONObject();
//            try {
//                jsonObject.put("message", Config.GETCODE);
//                jsonObject.put("userid", phone);
//                jsonObject.put("language", ToolsUtils.getLanguages(getApplicationContext()));
//                jsonObject.put("appcode", EncryptUtils.stringToMD5(phone+"20150515"));
//                OkHttpClientManager.postJson(Config.BASE_URL, new OkHttpClientManager.StringCallback() {
//                    @Override
//                    public void onFailure(Request request, IOException e) {
//                        Logger.e(LoginActivity.class.getSimpleName(), e.getMessage());
//                    }
//                    @Override
//                    public void onResponse(String response) {
//                        Log.e("aa","a"+response.toString());
//                        resolveJson(Config.GETCODE, response);
//                    }
//                }, jsonObject);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
            //得到token

//            //获取图片验证码
//            HashMap map=new HashMap();
//            // map.put("token",EncryptUtils.stringToMD5(phone+"20150515"));
//            if (!Url.isWifiProxy(this)) {
//                VolleyUtils.deStringPost(LoginActivity.this,Url.imgCode , map, "imgCode", new VolleyUtils.volleyListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//                    }
//
//                    @Override
//                    public void onErrorResponse(String errorMessage) {
//                        Log.i("fei", errorMessage.toString());
//                    }
//
//                    @Override
//                    public void onResponse(String response) {
//                        Log.e("code", response.toString());
//                        try {
//                            JSONObject jsonObject = new JSONObject(response.toString());
//                            String errorCode = jsonObject.getString("errorCode");
//                            if ("200".equals(errorCode)) {
//                                YanzhengBean yanzhengBean = VolleyUtils.parseJsonWithGson(response.toString(), YanzhengBean.class);
//                                Message message = new Message();
//                                message.what = 0;
//                                message.obj = yanzhengBean;
//                                handler.sendMessage(message);
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//            }

//            HashMap map=new HashMap();
//            map.put("phone",phone);
//           // map.put("token",EncryptUtils.stringToMD5(phone+"20150515"));
//
//            VolleyUtils.deStringPost(LoginActivity.this, Url.getCode, map, "getCode", new VolleyUtils.volleyListener() {
//                @Override
//                public void onResponse(JSONObject response) {
//
//                }
//
//                @Override
//                public void onErrorResponse(String errorMessage) {
//                 Log.i("fei",errorMessage.toString());
//                }
//
//                @Override
//                public void onResponse(String response) {
//                    Log.e("code",response.toString());
//                    resolveJson(Config.GETCODE, response);
//                }
//            });

//            learning add
            btCode.setFocusable(false);
            btCode.setClickable(false);
//            时间戳
            String timestamp = System.currentTimeMillis() + "";
//            拼接要加密的字符串
            String toMD5 = timestamp + "_" + phone + "_" + "aa3fb191441e4c1bb76cd18edf52fc3b";
            String md5String = MD5Utils.getMD5String(toMD5);
            HashMap map = new HashMap();
            map.put("phone", phone);
            map.put("timestamp", timestamp);
            map.put("singkey", md5String);
            map.put("inviter", "0");
            if (!Url.isWifiProxy(LoginActivity.this)) {
                VolleyUtils.deStringPost(LoginActivity.this, Url.NEWGETCODE, map, "getCode", new VolleyUtils.volleyListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }

                    @Override
                    public void onErrorResponse(String errorMessage) {
                        Log.i("fei", errorMessage.toString());
                    }

                    @Override
                    public void onResponse(String response) {
//                        Log.e("TAG","获取验证码返回的数据是："+response.toString());
                        resolveJson(Config.GETCODE, response);
                    }
                });
            }
        }
    }

    @Override
    public void permissionFail(int requestCode) {
        super.permissionFail(requestCode);
        IntentUtils.finish(this);
    }

    @OnClick(R.id.bt_login)
    void login() {
        phone = etPhone.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
            ToastUtils.showMessage("账号或验证码不能为空");

            return;
        }
        if (!RegularUtils.isMobileExact(phone)) {
            ToastUtils.showMessage("手机号码格式不正确");

            return;
        }
//        if (!cbSelect.isChecked()) {
//            //ToastUtils.showMessage("你必须同意用户协议才能继续");
//
//            return;
//        }
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("message",Config.Login);
//            jsonObject.put("userid", phone);
//            jsonObject.put("code", password);
//            jsonObject.put("language", ToolsUtils.getLanguage(getApplicationContext()));
//            OkHttpClientManager.postJson(Config.BASE_URL, new OkHttpClientManager.StringCallback() {
//                @Override
//                public void onFailure(Request request, IOException e) {
//                    Logger.e(LoginActivity.class.getSimpleName(), e.getMessage());
//                }
//                @Override
//                public void onResponse(String response) {
//                    Log.e("mm",response.toString());
//                    resolveJson(Config.Login, response);
//                    Logger.e(LoginActivity.class.getSimpleName(), response);
//                }
//            }, jsonObject);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        //  Log.e("kop","cid"+App.getInstance().getCid());
        HashMap map = new HashMap();
        map.put("phone", phone);
        map.put("code", password);
        map.put("geTuiId", App.getInstance().getCid() == null ? "" : App.getInstance().getCid());
        map.put("inviter", "0");

        if (!Url.isWifiProxy(this)) {
            VolleyUtils.deStringPost(LoginActivity.this, Url.LOGIN, map, "login", new VolleyUtils.volleyListener() {
                @Override
                public void onResponse(JSONObject response) {

                }

                @Override
                public void onErrorResponse(String errorMessage) {

                }

                @Override
                public void onResponse(String response) {
                    Log.e("TAG", response.toString());
                    // Toast.makeText(LoginActivity.this,response.toString(),Toast.LENGTH_LONG).show();
                    resolveJson(Config.Login, response);
                }
            });
        }
    }

    @OnClick(R.id.bt_code)
    void getCode() {
        phone = etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone) || !RegularUtils.isMobileExact(phone)) {
            ToastUtils.showMessage("账号不能为空或格式不正确");
            return;
        }
        requestPermission(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA}, 100);
    }

    private void resolveJson(String type, String response) {
        Log.e("lpl", response.toString());


        try {
            JSONObject jsonObject = new JSONObject(response.toString());
            String errorCode = jsonObject.getString("errorCode");
            if ("200".equals(errorCode)) {
                if (Config.GETCODE.equals(type)) {
                    timer.start();
                } else {
                    String string = jsonObject.getJSONObject("result").getJSONObject("user").getString("userToken");
                    UtilSharedPreference.saveString(getApplicationContext(), Config.TOKEN, string);
                    UtilSharedPreference.saveString(getApplicationContext(), Config.PHONE, phone);
//                    //得到优惠券信息
                    CardTipBean cardTipBean = VolleyUtils.parseJsonWithGson(response.toString(), CardTipBean.class);

                    App.getInstance().setCardTipBean(cardTipBean);


                    IntentUtils.startActivityAndFinish(this, MainActivity.class);

                }
            } else {
                ToastUtils.showMessage("验证码不正确");
                btCode.setFocusable(true);
                btCode.setClickable(true);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VolleyController.getInstance(this.getApplicationContext()).getRequestQueue().cancelAll("login");
    }

    class DownTimer extends CountDownTimer {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public DownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            btCode.setText(millisUntilFinished / COUNT_S + "秒后重试");
            btCode.setClickable(false);
            btCode.setFocusable(false);
        }

        @Override
        public void onFinish() {
            btCode.setText("再次获取");
            btCode.setClickable(true);
            btCode.setFocusable(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean isNet = inspectNet();
        if (!isNet) {
            ToastUtils.showMessage(getResources().getString(R.string.net_un_work));
        }
    }


    private void initYanZhengPopWindow() {
        // 将布局文件转换成View对象，popupview 内容视图
        View quPopView = LayoutInflater.from(this).inflate(R.layout.pop_yanzheng_window, null);
        // 将转换的View放置到 新建一个popuwindow对象中
        yangzhengPopupWindow = new PopupWindow(quPopView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        yangzhengPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.pop_bg));
        // 点击popuwindow外让其消失
//        feedbackPopupWindow.setOutsideTouchable(true);
        final EditText et_two_code = (EditText) quPopView.findViewById(R.id.et_two_code);
        iv_two_code = (ImageView) quPopView.findViewById(R.id.iv_two_code);
        TextView tv_two_code_huan = (TextView) quPopView.findViewById(R.id.tv_two_code_huan);
        TextView tv_two_code_true = (TextView) quPopView.findViewById(R.id.tv_two_code_true);
        ImageView iv_pop_close = (ImageView) quPopView.findViewById(R.id.iv_pop_close);
        yangzhengPopupWindow.setFocusable(true);
        tv_two_code_huan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap map = new HashMap();
                if (!Url.isWifiProxy(LoginActivity.this)) {
                    VolleyUtils.deStringPost(LoginActivity.this, Url.imgCode, map, "imgCode", new VolleyUtils.volleyListener() {
                        @Override
                        public void onResponse(JSONObject response) {

                        }

                        @Override
                        public void onErrorResponse(String errorMessage) {
                            Log.i("fei", errorMessage.toString());
                        }

                        @Override
                        public void onResponse(String response) {
                            Log.e("code", response.toString());
                            try {
                                JSONObject jsonObject = new JSONObject(response.toString());
                                String errorCode = jsonObject.getString("errorCode");
                                if ("200".equals(errorCode)) {
                                    yanBean = VolleyUtils.parseJsonWithGson(response.toString(), YanzhengBean.class);
                                    Message message = new Message();
                                    message.what = 1;
//                                    handler.sendMessage(message);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
        iv_pop_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yangzhengPopupWindow.isShowing())
                    yangzhengPopupWindow.dismiss();
            }
        });
        Picasso.with(LoginActivity.this).load(yanBean.getResult().getImageurl()).into(iv_two_code);
        tv_two_code_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btCode.setFocusable(false);
                btCode.setClickable(false);
                if (yangzhengPopupWindow.isShowing())
                    yangzhengPopupWindow.dismiss();
                String text = et_two_code.getText().toString().trim();
                HashMap map = new HashMap();
                map.put("phone", phone);
                map.put("codeid", yanBean.getResult().getCodeid());
                map.put("codetext", text);
                map.put("inviter", "0");
//                if (!Url.isWifiProxy(LoginActivity.this)) {
//                    VolleyUtils.deStringPost(LoginActivity.this, Url.getCode, map, "getCode", new VolleyUtils.volleyListener() {
//                        @Override
//                        public void onResponse(JSONObject response) {
//
//                        }
//
//                        @Override
//                        public void onErrorResponse(String errorMessage) {
//                            Log.i("fei", errorMessage.toString());
//                        }
//
//                        @Override
//                        public void onResponse(String response) {
//                            Log.e("code", response.toString());
//                            resolveJson(Config.GETCODE, response);
//                        }
//                    });
//                }
            }
        });
        yangzhengPopupWindow.showAtLocation(btCode, Gravity.CENTER, 0, 0);
    }


}