package com.coolu.nokelock.bike.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.activity.App;
import com.coolu.nokelock.bike.activity.BalanceAddActivity;
import com.coolu.nokelock.bike.activity.DepositOkActivity;
import com.coolu.nokelock.bike.activity.VipComboActivity;
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

import butterknife.ButterKnife;

/**
 * @author Learning
 * @date 2019/3/4
 */
public class BuyDialog extends Dialog {
    private String mPayMoney;
    private VipComboActivity mContext;
    private TextView tv_money;
    private RadioGroup rg_pay;
    private TextView tv_pay;
    //  支付方式的标记
    private int payTag = 0;
    private Dialog loadingDialog;
    private HashMap balanceAddMap = new HashMap();
    private String orderInfo;
    private int flag=1;
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
                    Log.e("TAG",resultStatus);
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        ToastUtils.showMessage("支付成功");
                        Bundle bundle = new Bundle();
                        bundle.putInt("flag",flag);
                        IntentUtils.startActivityAndFinish(mContext, DepositOkActivity.class,bundle);

                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtils.showMessage("支付失败");
                    }
                    tv_pay.setClickable(true);
                    break;
            }
        }
    };
    public BuyDialog(VipComboActivity context, int themeResId, String payMoney) {
        super(context, themeResId);
        setContentView(R.layout.dialog_buy);
        mContext=context;
        mPayMoney=payMoney;

        //设置弹窗的大小和位置
        WindowManager.LayoutParams wm = getWindow().getAttributes();
        wm.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wm.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        wm.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        onWindowAttributesChanged(wm);

        initData();
    }

    private void initData() {
        tv_money = (TextView) findViewById(R.id.tv_money);
        rg_pay = (RadioGroup) findViewById(R.id.rg_pay);
        tv_pay = (TextView) findViewById(R.id.tv_pay);
        tv_money.setText(mPayMoney+"元");
        rg_pay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_pay_weixin:
                        payTag = 0;
                        break;
                    case R.id.rb_pay_alipay:
                        payTag = 1;
                        break;
                }
            }
        });
        tv_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BuyDialog.this.dismiss();
                tv_pay.setClickable(false);
                loadingDialog = DialogUtils.getLoadingDialog(mContext, "");
                loadingDialog.show();
                if (!balanceAddMap.isEmpty()) {
                    balanceAddMap.clear();
                }
                balanceAddMap.put("channelId","0");
                balanceAddMap.put("payWhat", "充骑行卡");
                balanceAddMap.put("token", UtilSharedPreference.getStringValue(App.getInstance().getApplicationContext(), Config.TOKEN));
                balanceAddMap.put("money",mPayMoney);
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
        });
    }
    private void getPayData(final String flag, String url, HashMap map, String vv) {
        VolleyUtils.deStringPost(mContext, url, map, vv, new VolleyUtils.volleyListener() {
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
                if (tv_pay != null) {
                    tv_pay.setClickable(true);
                }
            }

            @Override
            public void onResponse(String response) {
                resolveJson(response, flag);
            }
        });
    }
    private void resolveJson(String response, String payType) {
//        Log.e("TAG","返回的数据是："+response);
        if (TextUtils.equals(payType,Config.ZFB)){
            PayBean payBean = VolleyUtils.parseJsonWithGson(response, PayBean.class);
            if ("200".equals(payBean.getErrorCode())){
                if (loadingDialog!=null){
                    loadingDialog.dismiss();
                    tv_pay.setClickable(true);
                }
                orderInfo = payBean.getResult();
                Runnable payRunnable = new Runnable() {
                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(mContext);
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
                    tv_pay.setClickable(true);
                }
            }
            else {
                if (loadingDialog!=null){
                    loadingDialog.dismiss();
                    tv_pay.setClickable(true);
                }

            }
        }else { //微信
            WXbean wXbean = VolleyUtils.parseJsonWithGson(response, WXbean.class);
            if ("200".equals(wXbean.getErrorCode())){
                if (loadingDialog!=null){
                    loadingDialog.dismiss();
                    tv_pay.setClickable(true);
                }
                //app_id 要换成公司自己注册的id
                IWXAPI wxApi = WXAPIFactory.createWXAPI(mContext, Config.APP_ID);
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
                    tv_pay.setClickable(true);
                }
            } else{
                if (loadingDialog!=null){
                    loadingDialog.dismiss();
                    tv_pay.setClickable(true);
                }
            }
        }
    }
}
