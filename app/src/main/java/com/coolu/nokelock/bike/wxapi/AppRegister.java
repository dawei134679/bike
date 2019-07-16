package com.coolu.nokelock.bike.wxapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


import com.coolu.nokelock.bike.util.Config;
import com.fitsleep.sunshinelibrary.utils.Logger;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class AppRegister extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		final IWXAPI api = WXAPIFactory.createWXAPI(context, null);
		Logger.e(AppRegister.class.getSimpleName(),"action:"+intent.getAction());
		// 将该app注册到微信
		api.registerApp(Config.APP_ID);
	}
}
