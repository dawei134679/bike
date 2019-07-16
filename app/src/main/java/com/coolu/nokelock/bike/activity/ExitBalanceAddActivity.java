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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.base.BaseActivity;
import com.coolu.nokelock.bike.bean.PayBean;
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
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Learning
 * @date 2019/3/7
 */
public class ExitBalanceAddActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.iv_back_balance_add_exit)
    ImageView iv_back_balance_add_exit;
    @BindView(R.id.tv_prompt)
    TextView tv_prompt;
    @BindView(R.id.tv_add_money)
    TextView tv_add_money;
    @BindView(R.id.rg_pay_exit)
    RadioGroup rg_pay_exit;
    @BindView(R.id.tv_balance_add_exit)
    TextView tv_balance_add_exit;
    @BindView(R.id.tv_recharge_money_exit)
    TextView tv_recharge_money_exit;
    //  支付方式的标记
    private int payTag = 0;
    private HashMap balanceAddMap = new HashMap();

    private Dialog loadingDialog;
    private String orderInfo;

    private double addMoney;

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
                        Bundle bundle = new Bundle();
                        bundle.putInt("flag",1);
                        IntentUtils.startActivityAndFinish(ExitBalanceAddActivity.this, DepositOkActivity.class,bundle);

                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtils.showMessage("支付失败");
                    }
                    tv_balance_add_exit.setClickable(true);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_add_exit);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        float newopenmoney = intent.getFloatExtra("newopenmoney", 0);
        double userMoney = intent.getDoubleExtra("userMoney", 0);
        addMoney = newopenmoney - userMoney;
        tv_prompt.setText("余额不足，请保持余额在"+newopenmoney+"元以上才能开始骑行。您的余额是"+userMoney+"元，只需再冲"+addMoney+"元");
        tv_add_money.setText(addMoney+"");
        tv_recharge_money_exit.setText(addMoney+"");
        Toast.makeText(this,"余额不足，请先充值余额！",Toast.LENGTH_LONG).show();

        iv_back_balance_add_exit.setOnClickListener(this);
        tv_balance_add_exit.setOnClickListener(this);
        rg_pay_exit.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_balance_add_exit:
                ExitBalanceAddActivity.this.finish();
                break;
            case R.id.tv_balance_add_exit:
                //去充值
                balanceAdd();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rb_balance_add_exit_weixin:
                payTag = 0;
                break;
            case R.id.rb_balance_add_exit_alipay:
                payTag = 1;
                break;
        }
    }

    private void balanceAdd() {
        tv_balance_add_exit.setClickable(false);
        loadingDialog = DialogUtils.getLoadingDialog(this, "");
        loadingDialog.show();
        if (!balanceAddMap.isEmpty()) {
            balanceAddMap.clear();
        }
        balanceAddMap.put("payWhat", "充余额");
        balanceAddMap.put("token", UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(), Config.TOKEN));
        balanceAddMap.put("money", addMoney+"");
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
        VolleyUtils.deStringPost(ExitBalanceAddActivity.this, url, map, vv, new VolleyUtils.volleyListener() {
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
                if (tv_balance_add_exit != null) {
                    tv_balance_add_exit.setClickable(true);
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
                    tv_balance_add_exit.setClickable(true);
                }
                orderInfo = payBean.getResult();
                Runnable payRunnable = new Runnable() {
                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(ExitBalanceAddActivity.this);
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
                    tv_balance_add_exit.setClickable(true);
                }
            }
            else {
                if (loadingDialog!=null){
                    loadingDialog.dismiss();
                    tv_balance_add_exit.setClickable(true);
                }

            }
        }else { //微信
            WXbean wXbean = VolleyUtils.parseJsonWithGson(response.toString(), WXbean.class);
            if ("200".equals(wXbean.getErrorCode())){
                if (loadingDialog!=null){
                    loadingDialog.dismiss();
                    tv_balance_add_exit.setClickable(true);
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
                    tv_balance_add_exit.setClickable(true);
                }
            } else{
                if (loadingDialog!=null){
                    loadingDialog.dismiss();
                    tv_balance_add_exit.setClickable(true);
                }
            }
        }
    }
}
