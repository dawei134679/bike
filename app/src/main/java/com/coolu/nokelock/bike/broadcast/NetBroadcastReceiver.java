package com.coolu.nokelock.bike.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;


import com.coolu.nokelock.bike.base.BaseActivity;
import com.fitsleep.sunshinelibrary.utils.Logger;
import com.fitsleep.sunshinelibrary.utils.NetworkUtils;


/**
 * 网络状态广播
 * Created by cheng on 2016/11/28.
 */
public class NetBroadcastReceiver extends BroadcastReceiver {

    public NetEvevt evevt = BaseActivity.evevt;

    @Override
    public void onReceive(Context context, Intent intent) {
        Logger.e("broadcast",intent.getAction());
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            int netWorkState = NetworkUtils.getNetWorkType(context);
            // 接口回调传过去状态的类型
            evevt.onNetChange(netWorkState);

        }
    }


    // 自定义接口
    public interface NetEvevt {
        public void onNetChange(int netMobile);
    }
}
