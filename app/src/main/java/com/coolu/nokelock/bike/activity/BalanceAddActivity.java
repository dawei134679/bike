package com.coolu.nokelock.bike.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.adapter.BalanceAddAdapter;
import com.coolu.nokelock.bike.base.BaseActivity;
import com.coolu.nokelock.bike.bean.BalanceAddBean;
import com.coolu.nokelock.bike.bean.PayBean;
import com.coolu.nokelock.bike.bean.ReturnBalanceBean;
import com.coolu.nokelock.bike.bean.WXbean;
import com.coolu.nokelock.bike.pay.PayResult;
import com.coolu.nokelock.bike.url.Url;
import com.coolu.nokelock.bike.util.ConditionsUtils;
import com.coolu.nokelock.bike.util.Config;
import com.coolu.nokelock.bike.util.VolleyUtils;
import com.fitsleep.sunshinelibrary.utils.DialogUtils;
import com.fitsleep.sunshinelibrary.utils.IntentUtils;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.fitsleep.sunshinelibrary.utils.UtilSharedPreference;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.LoginException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Learning
 * @date 2019/3/4
 */
public class BalanceAddActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.iv_back_balance_add)
    ImageView iv_back_balance_add;
    @BindView(R.id.tv_money)
    TextView tv_money;
    @BindView(R.id.rg_pay)
    RadioGroup rg_pay;
    @BindView(R.id.tv_balance_describe)
    TextView tv_balance_describe;
    @BindView(R.id.lv_balance_add)
    ListView lv_balance_add;
    @BindView(R.id.tv_balance_add)
    TextView tv_balance_add;
    @BindView(R.id.tv_recharge_money)
    TextView tv_recharge_money;
    //  支付方式的标记
    private int payTag = 0;
    private HashMap balanceAddMap = new HashMap();

    private Dialog loadingDialog;
    private String orderInfo;
//    private float openmoney;
    private float newopenmoney;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 101:
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    Log.e("llll",payResult.getResult());

                    String resultStatus = payResult.getResultStatus();
                    Log.e("llll",resultStatus);
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        ToastUtils.showMessage("支付成功");
                        Log.e("TAG","支付成功");
                        Bundle bundle = new Bundle();
                        bundle.putInt("flag",100);
                        IntentUtils.startActivityAndFinish(BalanceAddActivity.this, DepositOkActivity.class,bundle);
                        startActivity(new Intent(BalanceAddActivity.this, VipComboActivity.class));
                        BalanceAddActivity.this.finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtils.showMessage("支付失败");
                    }
                    tv_balance_add.setClickable(true);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_add);
        ButterKnife.bind(this);
        newopenmoney = getIntent().getFloatExtra("newopenmoney", 0);
        iv_back_balance_add.setOnClickListener(this);
        tv_balance_add.setOnClickListener(this);
        rg_pay.setOnCheckedChangeListener(this);
        initData();
    }

    private void initData() {
//        if (App.getInstance().getUserEntityBean() != null) {
////            openmoney = App.getInstance().getUserEntityBean().getOpenmoney();
//            newopenmoney=App.getInstance().getUserEntityBean().getNewopenmoney();
////            tv_money.setText(openmoney+"");
//            tv_money.setText(newopenmoney+"");
//        }
        tv_money.setText(newopenmoney+"");
        tv_recharge_money.setText(newopenmoney+"");
        tv_balance_describe.setText("免保证金，骑车前预存"+newopenmoney+"元余额，可实时自助退还。");
        if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
            VolleyUtils.deStringPost(BalanceAddActivity.this, Url.LESSBALANCE, null,null, new VolleyUtils.volleyListener() {
                @Override
                public void onResponse(JSONObject response) {

                }

                @Override
                public void onErrorResponse(String errorMessage) {
                    Log.e("TAG","错误信息是："+errorMessage);

                }

                @Override
                public void onResponse(String response) {
//                    Log.e("TAG","余额充值界面的数据是："+response);
                    jsonData(response);
                }
            });
        }
    }

    private void jsonData(String response) {
        Gson gson=new Gson();
        BalanceAddBean balanceAddBean = gson.fromJson(response, BalanceAddBean.class);
        BalanceAddAdapter balanceAddAdapter=new BalanceAddAdapter(BalanceAddActivity.this,balanceAddBean.getResult());
        lv_balance_add.setAdapter(balanceAddAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_balance_add:
                BalanceAddActivity.this.finish();
                break;
            case R.id.tv_balance_add:
                //去充值
                balanceAdd();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rb_balance_add_weixin:
                payTag = 0;
                break;
            case R.id.rb_balance_add_alipay:
                payTag = 1;
                break;
        }
    }

    private void balanceAdd() {
        tv_balance_add.setClickable(false);
        loadingDialog = DialogUtils.getLoadingDialog(this, "");
        loadingDialog.show();
        if (!balanceAddMap.isEmpty()) {
            balanceAddMap.clear();
        }
        balanceAddMap.put("payWhat", "充余额");
        balanceAddMap.put("token", UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(), Config.TOKEN));
        balanceAddMap.put("money", newopenmoney+"");
        if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
            if (payTag == 0) {
//            微信支付
//                Log.e("TAG", "微信支付");
                balanceAddMap.put("payType", "1");
                if (!Url.isWifiProxy(App.getInstance().getApplicationContext())) {
                    getPayData(Config.WX, Url.WX, balanceAddMap, "wx");
                }
            } else {
//            支付宝支付
//                Log.e("TAG", "支付宝支付");
                balanceAddMap.put("payType", "2");
                getPayData(Config.ZFB, Url.ZF, balanceAddMap, "zhufubao");
            }
        }
    }

    private void getPayData(final String flag, String url, HashMap map, String vv) {
        VolleyUtils.deStringPost(BalanceAddActivity.this, url, map, vv, new VolleyUtils.volleyListener() {
            @Override
            public void onResponse(JSONObject response) {
//                Log.e("TAG","错误信息是："+response);
            }

            @Override
            public void onErrorResponse(String errorMessage) {
//                Log.e("TAG","错误信息是："+errorMessage);
                if (loadingDialog != null){
                    loadingDialog.dismiss();
                }
                loadingDialog = null;
                if (tv_balance_add != null) {
                    tv_balance_add.setClickable(true);
                }
            }

            @Override
            public void onResponse(String response) {
                resolveJson(response, flag);
            }
        });
    }

    private void resolveJson(String response, String payType) {
        if (TextUtils.equals(payType,Config.ZFB)){
            PayBean payBean = VolleyUtils.parseJsonWithGson(response, PayBean.class);
            if ("200".equals(payBean.getErrorCode())){
                if (loadingDialog!=null){
                    loadingDialog.dismiss();
                    tv_balance_add.setClickable(true);
                }
                orderInfo = payBean.getResult();
                Runnable payRunnable = new Runnable() {
                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(BalanceAddActivity.this);
                        Map<String, String> result = alipay.payV2(orderInfo, true);
                        //   Logger.e(DepositActivity.class.getSimpleName(), "result:" + result);
                        Message msg = new Message();
                        msg.what =101;
                        msg.obj = result;
                        handler.sendMessage(msg);
                    }
                };
                // 必须异步调用
                Thread payThread = new Thread(payRunnable);
                payThread.start();
            }else if ("301".equals(payBean.getErrorCode())) {
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
                if (loadingDialog!=null){
                    loadingDialog.dismiss();
                    tv_balance_add.setClickable(true);
                }
            }
            else {
                if (loadingDialog!=null){
                    loadingDialog.dismiss();
                    tv_balance_add.setClickable(true);
                }

            }
        }else { //微信
            WXbean wXbean = VolleyUtils.parseJsonWithGson(response.toString(), WXbean.class);
            if ("200".equals(wXbean.getErrorCode())){
                if (loadingDialog!=null){
                    loadingDialog.dismiss();
                    tv_balance_add.setClickable(true);
                }
                //app_id 要换成公司自己注册的id
                IWXAPI wxApi = WXAPIFactory.createWXAPI(this, Config.APP_ID);
                wxApi.registerApp(Config.APP_ID);
                try {
                    JSONObject jsonObject=new JSONObject(wXbean.getResult());
                    String sign = jsonObject.getString("sign");
                    String partnerid = jsonObject.getString("partnerid");
                    String timestamp = jsonObject.getString("timestamp");
                    String noncestr = jsonObject.getString("noncestr");
                    String prepayid = jsonObject.getString("prepayid");
                    String aPackage = jsonObject.getString("package");
                    String appid = jsonObject.getString("appid");

                    //根据服务器返回的json填充数据
                    PayReq payReq = new PayReq();
                    payReq.appId =appid;
                    payReq.partnerId =partnerid;
                    payReq.prepayId =prepayid;
                    payReq.nonceStr =noncestr;
                    payReq.timeStamp =timestamp;
                    payReq.packageValue =aPackage;
                    payReq.sign =sign;
                    wxApi.sendReq(payReq);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if ("301".equals(wXbean.getErrorCode())) {
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
                if (loadingDialog!=null){
                    loadingDialog.dismiss();
                    tv_balance_add.setClickable(true);
                }
            } else{
                if (loadingDialog!=null){
                    loadingDialog.dismiss();
                    tv_balance_add.setClickable(true);
                }
            }
        }
    }
}
