package com.coolu.nokelock.bike.activity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.alipay.sdk.app.PayTask;
import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.base.BaseActivity;
import com.coolu.nokelock.bike.bean.PayBean;
import com.coolu.nokelock.bike.bean.UserEntityBean;
import com.coolu.nokelock.bike.bean.WXbean;
import com.coolu.nokelock.bike.pay.PayResult;
import com.coolu.nokelock.bike.prensenter.MainPresenter;
import com.coolu.nokelock.bike.url.Url;
import com.coolu.nokelock.bike.util.BaseUtil;
import com.coolu.nokelock.bike.util.ConditionsUtils;
import com.coolu.nokelock.bike.util.Config;
import com.coolu.nokelock.bike.util.TimeUtil;
import com.coolu.nokelock.bike.util.VolleyUtils;
import com.fitsleep.sunshinelibrary.utils.DialogUtils;
import com.fitsleep.sunshinelibrary.utils.EncryptUtils;
import com.fitsleep.sunshinelibrary.utils.IntentUtils;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.fitsleep.sunshinelibrary.utils.UtilSharedPreference;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RechargeActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back;
    private RelativeLayout weixin_relative;
    private RelativeLayout zhifubao_relative;
    private ImageView weixin_img;
    private ImageView zhifubao_img;
    @BindView(R.id.ex_four)
    TextView four;
    @BindView(R.id.ex_three)
    TextView three;
    @BindView(R.id.ex_two)
    TextView two;
    @BindView(R.id.ex_one)
    TextView one;
    @BindView(R.id.id_exchange_cz)
    TextView cz;
//    learning add
    @BindView(R.id.tv_recharge_money)
    TextView tv_recharge_money;
    TextView tv_tishi;
    private EditText edit;
    private LinearLayout ex_one;
    private LinearLayout ex_two;
    private Button ex_button;
    private TextView ex_tv;
    private TextView title_toolbar;
    private boolean pay_flag = true; //区分是微信还是支付宝
    private String money_flag; //区分充保证金还是余额还是骑行卡
    private Dialog loadingDialog;
    private String currentMoney = "0.1"; //当前金额

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 101:
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    Log.e("llll", payResult.getResult());

                    String resultStatus = payResult.getResultStatus();
                    Log.e("llll", resultStatus);
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        ToastUtils.showMessage("支付成功");
                        Bundle bundle = new Bundle();
                        bundle.putInt("flag", flag);
                        IntentUtils.startActivityAndFinish(RechargeActivity.this, DepositOkActivity.class, bundle);

                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtils.showMessage("支付失败");
                    }
                    cz.setClickable(true);
                    break;
            }
        }
    };
    private String orderInfo;
    private int flag;
    private String cardId;
    private HashMap map;

    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏文字颜色及图标为深色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_recharge);
        //setContentView(R.layout.activity_deposit);
        //  EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        //得到flag 区分是充保证金还是余额还是骑行卡
        Intent intent = getIntent();
        flag = intent.getIntExtra("flag", -1);
        city = intent.getStringExtra("city");
        Log.e("TAG","获得的城市是："+city);

        Log.e("nnnn", "flag" + flag);
        cardId = getIntent().getStringExtra("cardId"); //扫描的骑行卡编号
        map = new HashMap();
        ButterKnife.bind(this);
        back = (ImageView) findViewById(R.id.id_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        weixin_relative = (RelativeLayout) findViewById(R.id.id_weixin_relative);
        zhifubao_relative = (RelativeLayout) findViewById(R.id.id_zhufubao_telative);
        weixin_relative.setOnClickListener(this);
        zhifubao_relative.setOnClickListener(this);
        weixin_img = (ImageView) findViewById(R.id.id_weixin_choose);
        zhifubao_img = (ImageView) findViewById(R.id.id_zhufubao_choose);
        //下面区分钱包还是保证金充值
        edit = (EditText) findViewById(R.id.id_card_edit);
        ex_one = (LinearLayout) findViewById(R.id.ex_linear_one);
        ex_two = (LinearLayout) findViewById(R.id.ex_linear_two);

        ex_button = (Button) findViewById(R.id.id_ex_button); //保证金按钮
        tv_tishi = (TextView) findViewById(R.id.tv_tishi);

        ex_tv = (TextView) findViewById(R.id.id_ex_tv); //我的保证金
        title_toolbar = (TextView) findViewById(R.id.id_title_toolbar);//标题

        registerReceiver(broadcastReceiver, new IntentFilter(Config.WX_PAY));

        VolleyUtils.GetPerson();

//        learning add
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    tv_recharge_money.setText(edit.getText().toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
            broadcastReceiver = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (App.getInstance().getUserEntityBean() != null && (!TextUtils.isEmpty(App.getInstance().getUserEntityBean().getDefaultDeposit()))) {
            ex_button.setText(App.getInstance().getUserEntityBean().getDefaultDeposit());
            Log.e("TAG","比较的城市是："+city);
//            if (TextUtils.equals("秦皇岛市",city)){
//                tv_tishi.setText(getResources().getString(R.string.money_chongzhi_one));
//            }else {
//                tv_tishi.setText(getResources().getString(R.string.money_chongzhi_two));
//            }
            tv_tishi.setText(getResources().getString(R.string.money_chongzhi_one));
            tv_recharge_money.setText(App.getInstance().getUserEntityBean().getDefaultDeposit());
        }

        if (flag == 1) {
            edit.setVisibility(View.VISIBLE);
            ex_one.setVisibility(View.VISIBLE);
            ex_two.setVisibility(View.VISIBLE);
            ex_button.setVisibility(View.GONE);
            ex_tv.setVisibility(View.GONE);
            title_toolbar.setText("余额充值");
//            tv_tishi.setText("1、骑行前请保持余额在29元（含）以上。\n2、余额随时可自助退还。");
//            learning add
            tv_tishi.setText("");
            if (App.getInstance().getUserEntityBean() != null){
                String userMoney = App.getInstance().getUserEntityBean().getUserMoney();
                if (!TextUtils.isEmpty(userMoney)){
                    double rechargeMoney = Double.parseDouble(userMoney);
                    if (rechargeMoney<0){
                        double abs = Math.abs(rechargeMoney);
                        edit.setText(abs+"");
                        tv_recharge_money.setText(abs+"");
//                        让EditText失去焦点,不可点击
                        edit.clearFocus();
                        edit.setFocusable(false);

                        one.setClickable(false);
                        two.setClickable(false);
                        three.setClickable(false);
                        four.setClickable(false);
                    }else {
                        edit.setText("");
//                        让EditText获得焦点，可以点击
                        edit.requestFocus();
                        edit.setFocusable(true);

                        one.setClickable(true);
                        two.setClickable(true);
                        three.setClickable(true);
                        four.setClickable(true);
                    }
                }
            }
        } else if (flag == 2) {
            ex_tv.setText("购畅骑卡");
            title_toolbar.setText("购畅骑卡");
            Log.e("kok", "骑行卡" + App.getInstance().getUserEntityBean().getCardprice());
            if (App.getInstance().getUserEntityBean() != null && App.getInstance().getUserEntityBean().getCardprice() != null)
                Log.e("kok", "gou骑行卡");
            ex_button.setText(App.getInstance().getUserEntityBean().getCardprice());
            tv_recharge_money.setText(App.getInstance().getUserEntityBean().getCardprice());
        }


//        if (App.getInstance().getUserEntityBean() != null) {
//            if (!TextUtils.isEmpty(App.getInstance().getUserEntityBean().getUserMoney())){
//                if (Double.parseDouble(App.getInstance().getUserEntityBean().getUserMoney()) < 0) {
//                    edit.setText(App.getInstance().getUserEntityBean().getUserMoney().substring(1));
//                }
//            }
//        }
    }
    @OnClick(R.id.id_exchange_cz)
    void cz() {
        currentMoney = edit.getText().toString().trim();
        if (!TextUtils.isEmpty(currentMoney)&&Double.valueOf(currentMoney) > 1000) {
            ToastUtils.showMessage("充值余额不能大于1000，请重新输入金额");
        } else {
            boolean fastClick = TimeUtil.isFastClick();
            Log.e("fei", fastClick + "");
            if (fastClick) {
                Log.e("fei", "ccccccc");
//                if (!TextUtils.isEmpty(UtilSharedPreference.getStringValue(RechargeActivity.this, Config.RECHARGE)) && TextUtils.isEmpty(edit.getText().toString().trim())) {
                if (flag==1&&TextUtils.isEmpty(edit.getText().toString().trim())) {
                    // ToastUtils.showMessage("充值金额不能为空");
                    Toast.makeText(RechargeActivity.this, "充值金额不能为空", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!map.isEmpty()) {
                    map.clear();
                }
                if (flag == 0) {
                    //保证金
                    money_flag = "充保证金";
                    map.put("payWhat", money_flag);
                    currentMoney = ex_button.getText().toString().trim();

                } else if (flag == 1) {
                    //余额
                    money_flag = "充余额";
                    map.put("payWhat", money_flag);
                    currentMoney = edit.getText().toString().trim();
                } else if (flag == 2) {
                    //充骑行卡
                    money_flag = "充骑行卡";
                    map.put("payWhat", money_flag);
                    map.put("channelId", cardId == null ? "" : cardId);
                    currentMoney = ex_button.getText().toString().trim();
                    Log.e("lol", cardId);
                    Log.e("lol", "flag" + flag);
                }

                cz.setClickable(false);
                loadingDialog = DialogUtils.getLoadingDialog(this, "");
                loadingDialog.show();

                if (pay_flag) {
                    Log.e("llll", "99999999");
                    map.put("token", UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(), Config.TOKEN));
                    // map.put("userId","18612222805");
                    map.put("money", currentMoney);
                    if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
                        getPayData(Config.WX, Url.WX, map, "wx");
                    }
                } else {

                    Log.e("llll", "0000000");

                    map.put("token", UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(), Config.TOKEN));
                    // map.put("userId","18612222805");
                    map.put("money", currentMoney);
                    map.put("payType", "2");
                    if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
                        getPayData(Config.ZFB, Url.ZF, map, "zhufubao");
                    }
                }
            }
        }
    }

    private void getPayData(final String flag, String url, HashMap map, String vv) {


        VolleyUtils.deStringPost(RechargeActivity.this, url, map, vv, new VolleyUtils.volleyListener() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("llll", response.toString());
            }

            @Override
            public void onErrorResponse(String errorMessage) {
                if (loadingDialog != null)
                    loadingDialog.dismiss();
                loadingDialog = null;
                if (cz != null) {
                    cz.setClickable(true);
                }
            }

            @Override
            public void onResponse(String response) {
                Log.e("llll", response.toString());

                resolveJson(response, flag);
            }
        });

    }

    public void resolveJson(String response, String payType) {
        if (TextUtils.equals(payType, Config.ZFB)) {
            PayBean payBean = VolleyUtils.parseJsonWithGson(response.toString(), PayBean.class);
            if ("200".equals(payBean.getErrorCode())) {
                if (loadingDialog != null) {
                    loadingDialog.dismiss();
                    cz.setClickable(true);
                }
                orderInfo = payBean.getResult();
                Runnable payRunnable = new Runnable() {
                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(RechargeActivity.this);
                        Map<String, String> result = alipay.payV2(orderInfo, true);
                        //   Logger.e(DepositActivity.class.getSimpleName(), "result:" + result);
                        Message msg = new Message();
                        msg.what = 101;
                        msg.obj = result;
                        handler.sendMessage(msg);
                    }
                };
                // 必须异步调用
                Thread payThread = new Thread(payRunnable);
                payThread.start();
            } else if ("301".equals(payBean.getErrorCode())) {
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
                if (loadingDialog != null) {
                    loadingDialog.dismiss();
                    cz.setClickable(true);
                }
            } else {
                if (loadingDialog != null) {
                    loadingDialog.dismiss();
                    cz.setClickable(true);
                }

            }
        } else { //微信
            Log.e("1111", response.toString());
            WXbean wXbean = VolleyUtils.parseJsonWithGson(response.toString(), WXbean.class);


            if ("200".equals(wXbean.getErrorCode())) {
                if (loadingDialog != null) {
                    loadingDialog.dismiss();
                    cz.setClickable(true);
                }

                //app_id 要换成公司自己注册的id
                IWXAPI wxApi = WXAPIFactory.createWXAPI(this, Config.APP_ID);
                wxApi.registerApp(Config.APP_ID);
                try {
                    JSONObject jsonObject = new JSONObject(wXbean.getResult());
                    String sign = jsonObject.getString("sign");
                    String partnerid = jsonObject.getString("partnerid");
                    String timestamp = jsonObject.getString("timestamp");
                    String noncestr = jsonObject.getString("noncestr");
                    String prepayid = jsonObject.getString("prepayid");
                    String aPackage = jsonObject.getString("package");
                    String appid = jsonObject.getString("appid");

                    //根据服务器返回的json填充数据
                    PayReq payReq = new PayReq();
                    payReq.appId = appid;
                    payReq.partnerId = partnerid;
                    payReq.prepayId = prepayid;
                    payReq.nonceStr = noncestr;
                    payReq.timeStamp = timestamp;
                    payReq.packageValue = aPackage;
                    payReq.sign = sign;
                    wxApi.sendReq(payReq);

                    Log.e("llll", "sign" + sign);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if ("301".equals(wXbean.getErrorCode())) {
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
                if (loadingDialog != null) {
                    loadingDialog.dismiss();
                    cz.setClickable(true);
                }
            } else {
                if (loadingDialog != null) {
                    loadingDialog.dismiss();
                    cz.setClickable(true);
                }
            }
        }
    }


    @OnClick(R.id.ex_one)
    void one() {
        edit.setText(one.getText().toString().trim());
        tv_recharge_money.setText(one.getText().toString().trim());
        one.setTextColor(getResources().getColor(R.color.white));
        two.setTextColor(getResources().getColor(R.color.xinyong));
        three.setTextColor(getResources().getColor(R.color.xinyong));
        four.setTextColor(getResources().getColor(R.color.xinyong));

        one.setBackgroundResource(R.mipmap.btn162);
        two.setBackgroundResource(R.mipmap.btn161);
        three.setBackgroundResource(R.mipmap.btn161);
        four.setBackgroundResource(R.mipmap.btn161);
    }

    @OnClick(R.id.ex_two)
    void two() {
        edit.setText(two.getText().toString().trim());
        tv_recharge_money.setText(two.getText().toString().trim());
        one.setTextColor(getResources().getColor(R.color.xinyong));
        two.setTextColor(getResources().getColor(R.color.white));
        three.setTextColor(getResources().getColor(R.color.xinyong));
        four.setTextColor(getResources().getColor(R.color.xinyong));


        one.setBackgroundResource(R.mipmap.btn161);
        two.setBackgroundResource(R.mipmap.btn162);
        three.setBackgroundResource(R.mipmap.btn161);
        four.setBackgroundResource(R.mipmap.btn161);
    }

    @OnClick(R.id.ex_three)
    void three() {
        edit.setText(three.getText().toString().trim());
        tv_recharge_money.setText(three.getText().toString().trim());
        one.setTextColor(getResources().getColor(R.color.xinyong));
        two.setTextColor(getResources().getColor(R.color.xinyong));
        three.setTextColor(getResources().getColor(R.color.white));
        four.setTextColor(getResources().getColor(R.color.xinyong));

        one.setBackgroundResource(R.mipmap.btn161);
        two.setBackgroundResource(R.mipmap.btn161);
        three.setBackgroundResource(R.mipmap.btn162);
        four.setBackgroundResource(R.mipmap.btn161);
    }

    @OnClick(R.id.ex_four)
    void four() {
        edit.setText(four.getText().toString().trim());
        tv_recharge_money.setText(four.getText().toString().trim());
        one.setTextColor(getResources().getColor(R.color.xinyong));
        two.setTextColor(getResources().getColor(R.color.xinyong));
        three.setTextColor(getResources().getColor(R.color.xinyong));
        four.setTextColor(getResources().getColor(R.color.white));


        one.setBackgroundResource(R.mipmap.btn161);
        two.setBackgroundResource(R.mipmap.btn161);
        three.setBackgroundResource(R.mipmap.btn161);
        four.setBackgroundResource(R.mipmap.btn162);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_weixin_relative:
                pay_flag = true;
                weixin_img.setImageDrawable(getResources().getDrawable(R.mipmap.choose));
                zhifubao_img.setImageDrawable(getResources().getDrawable(R.mipmap.choose1));
                break;
            case R.id.id_zhufubao_telative:
                pay_flag = false;
                weixin_img.setImageDrawable(getResources().getDrawable(R.mipmap.choose1));
                zhifubao_img.setImageDrawable(getResources().getDrawable(R.mipmap.choose));
                break;
        }
    }


    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Config.WX_PAY.equals(intent.getAction())) {
                if (loadingDialog != null && loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                    loadingDialog = null;
                }
                cz.setClickable(true);
                boolean pay = intent.getBooleanExtra("pay", false);
                if (pay) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("flag", flag);
                    IntentUtils.startActivityAndFinish(RechargeActivity.this, DepositOkActivity.class, bundle);
                }
            }
        }
    };
}
