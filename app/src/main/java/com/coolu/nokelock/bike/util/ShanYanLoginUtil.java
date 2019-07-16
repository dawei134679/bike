package com.coolu.nokelock.bike.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.chuanglan.shanyan_sdk.OneKeyLoginManager;
import com.chuanglan.shanyan_sdk.listener.OneKeyLoginListener;
import com.coolu.nokelock.bike.activity.App;
import com.coolu.nokelock.bike.activity.BalanceAddActivity;
import com.coolu.nokelock.bike.activity.LoginActivity;
import com.coolu.nokelock.bike.activity.MainActivity;
import com.coolu.nokelock.bike.activity.ShanYanActivity;
import com.coolu.nokelock.bike.bean.BikeClockBean;
import com.coolu.nokelock.bike.bean.CardTipBean;
import com.coolu.nokelock.bike.bean.MacBeann;
import com.coolu.nokelock.bike.bean.ShanYan;
import com.coolu.nokelock.bike.bean.ShanYanResult;
import com.coolu.nokelock.bike.bean.UseBean;
import com.coolu.nokelock.bike.bean.UserEntityBean;
import com.coolu.nokelock.bike.prensenter.MainPresenter;
import com.coolu.nokelock.bike.url.Url;
import com.coolu.nokelock.bike.view.impl.MainViewImpl;
import com.fitsleep.sunshinelibrary.utils.IntentUtils;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.fitsleep.sunshinelibrary.utils.UtilSharedPreference;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Learning
 * @date 2019/3/25
 */
public class ShanYanLoginUtil{
    private Activity mContext;
    public ShanYanLoginUtil(Activity context) {
        mContext=context;
    }
    public void shanYanLogin(){
        //先进行闪验
        OneKeyLoginManager.getInstance().setOneKeyLoginListener(10, new OneKeyLoginListener() {
            @Override
            public void getPhoneCode(int i, String s) {
                if (1000==i){
                    Gson gson=new Gson();
                    ShanYan shanYan = gson.fromJson(s, ShanYan.class);
                    initShanYan(shanYan);
                }else {
                    IntentUtils.startActivity(mContext, LoginActivity.class);
                }
            }
        });
//        自定义运营商授权界面
        OneKeyLoginManager.getInstance().setAuthThemeConfig(ConfigUtils.getUiConfig(mContext));
//        拉起授权页
        OneKeyLoginManager.getInstance().LoginStart(false);
    }
    private void initShanYan(ShanYan shanYan) {
        if (!TextUtils.isEmpty(shanYan.getAccessToken())){
            Map<String,String> syMap=new HashMap<>();
            syMap.put("appId",shanYan.getAppId());
            syMap.put("appkey","bBuW5OSU");
            syMap.put("accessToken",shanYan.getAccessToken());
            syMap.put("telecom",shanYan.getTelecom());
            syMap.put("timestamp",shanYan.getTimestamp());
            syMap.put("randoms",shanYan.getRandoms());
            syMap.put("sign",shanYan.getSign());
            syMap.put("version",shanYan.getVersion());
            syMap.put("device",shanYan.getDevice());
            if (!Url.isWifiProxy(mContext)){
                VolleyUtils.deStringPost(mContext, Url.GETPHONENUMBER, syMap, "login", new VolleyUtils.volleyListener() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        Log.e("TAG","返回的信息是："+response.toString());

                    }

                    @Override
                    public void onErrorResponse(String errorMessage) {
//                        Log.e("TAG","返回的错误信息是："+errorMessage);
                    }

                    @Override
                    public void onResponse(String response) {
//                        Log.e("TAG","获取手机号的信息是："+response);
                        Gson gson=new Gson();
                        ShanYanResult shanYanResult = gson.fromJson(response, ShanYanResult.class);
                        initShanYanResult(shanYanResult);
                    }
                });
            }
        }
    }
    private void initShanYanResult(ShanYanResult shanYanResult) {
        if (200==shanYanResult.getErrorCode()){
            //销毁授权页
            OneKeyLoginManager.getInstance().finishAuthActivity();
            if (!Url.isWifiProxy(mContext)){
                Map<String,String> fastLogin=new HashMap<>();
                final String phone = shanYanResult.getResult().getPhone();
                fastLogin.put("phone",phone);
                fastLogin.put("inviter","0");
                VolleyUtils.deStringPost(mContext, Url.FLASHREGISTER, fastLogin, "login", new VolleyUtils.volleyListener() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        Log.e("TAG","返回的信息是："+response.toString());

                    }

                    @Override
                    public void onErrorResponse(String errorMessage) {
//                        Log.e("TAG","返回的错误信息是："+errorMessage);
                    }

                    @Override
                    public void onResponse(String response) {
                        initLoginData(response,phone);
                    }
                });
            }
        }else {
            ToastUtils.showMessage(shanYanResult.getMessage());
        }
    }
    private void initLoginData(String response,String phone) {
//        Log.e("TAG","登陆成功的结果是："+response);
        CardTipBean cardTipBean = VolleyUtils.parseJsonWithGson(response, CardTipBean.class);
        if (cardTipBean!=null){
           if (200==cardTipBean.getErrorCode()){
               UtilSharedPreference.saveString(mContext, Config.TOKEN,cardTipBean.getResult().getUser().getUserToken());
               UtilSharedPreference.saveString(mContext, Config.PHONE,phone);
               App.getInstance().setCardTipBean(cardTipBean);
//               double userMoney = cardTipBean.getResult().getUser().getUserMoney();
//               if (0.00==userMoney){
//                   Intent intent=new Intent(mContext,BalanceAddActivity.class);
//                   intent.putExtra("newopenmoney",cardTipBean.getResult().getNewopenmoney());
//                   mContext.startActivity(intent);
//               }else {
               mContext.startActivity(new Intent(mContext,ShanYanActivity.class));
//               }
           }else {
               ToastUtils.showMessage(cardTipBean.getMessage());
           }
        }
    }
}
