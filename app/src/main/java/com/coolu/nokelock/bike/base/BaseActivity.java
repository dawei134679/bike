package com.coolu.nokelock.bike.base;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;


import com.coolu.nokelock.bike.broadcast.NetBroadcastReceiver;
import com.coolu.nokelock.bike.service.DemoIntentService;
import com.coolu.nokelock.bike.service.DemoPushService;
import com.fitsleep.sunshinelibrary.utils.ActManager;
import com.fitsleep.sunshinelibrary.utils.IntentUtils;
import com.fitsleep.sunshinelibrary.utils.Logger;
import com.fitsleep.sunshinelibrary.utils.MPermissionsActivity;
import com.fitsleep.sunshinelibrary.utils.NetworkUtils;

import com.igexin.sdk.PushManager;
import com.umeng.analytics.MobclickAgent;


/**
 * activity基类
 * Created by sunshine on 2017/2/28.
 */

public class BaseActivity extends MPermissionsActivity implements NetBroadcastReceiver.NetEvevt {

    private static final String TAG = BaseActivity.class.getSimpleName();
    public static NetBroadcastReceiver.NetEvevt evevt;
    /**
     * 网络类型
     */
    private int netMobile;
    private NetBroadcastReceiver myReceiver;
    private String clientid;

    //   private Class usePushService = PushService.class;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActManager.getAppManager().addActivity(this);
        evevt = this;
        inspectNet();
        registerReceiver();
      //  PushManager.getInstance().initialize(this.getApplicationContext(), usePushService);
        // 注册 intentService 后 PushDemoReceiver 无效, sdk 会使用 DemoIntentService 传递数据,
        // AndroidManifest 对应保留一个即可(如果注册 DemoIntentService, 可以去掉 PushDemoReceiver, 如果注册了
        // IntentService, 必须在 AndroidManifest 中声明)

      //  String clientid = PushManager.getInstance().getClientid(getApplicationContext());
       // Logger.e(BaseActivity.class.getSimpleName(),"cid:"+clientid);
          // com.getui.demo.DemoPushService 为第三方自定义推送服务
        //个推
        PushManager.getInstance().initialize(this.getApplicationContext(), DemoPushService.class);
        clientid = PushManager.getInstance().getClientid(this.getApplicationContext());
        //友盟统计
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);

    }

    private void registerReceiver() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        myReceiver = new NetBroadcastReceiver();
        this.registerReceiver(myReceiver, filter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this); //友盟
    }

    @Override
    protected void onStart() {
        super.onStart();
        // com.getui.demo.DemoIntentService 为第三方自定义的推送服务事件接收类
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), DemoIntentService.class);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this); //友盟
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myReceiver != null) {
            unregisterReceiver(myReceiver);
            myReceiver = null;
        }
        ActManager.getAppManager().finishActivity(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        IntentUtils.finish(this);
    }

    /**
     * 初始化时判断有没有网络
     */

    public boolean inspectNet() {
        this.netMobile = NetworkUtils.getNetWorkType(BaseActivity.this);
        return isNetConnect();
    }

    /**
     * 网络变化之后的类型
     */
    @Override
    public void onNetChange(int netMobile) {
        // TODO Auto-generated method stub
        this.netMobile = netMobile;
        isNetConnect();

    }

    /**
     * 判断有无网络 。
     *
     * @return true 有网, false 没有网络.
     */
    public boolean isNetConnect() {
        if (netMobile == NetworkUtils.NETWORK_WIFI) {
            return true;
        } else if (netMobile == NetworkUtils.NETWORK_4G || netMobile == NetworkUtils.NETWORK_3G || netMobile == NetworkUtils.NETWORK_2G) {
            return true;
        } else if (netMobile == NetworkUtils.NETWORK_NO) {
            return false;

        }
        return false;
    }

//    //个推id
//
//    public String getGeTuiId() {
//        return clientid;
//    }
}
