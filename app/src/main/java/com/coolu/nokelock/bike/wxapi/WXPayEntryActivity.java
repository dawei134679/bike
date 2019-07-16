package com.coolu.nokelock.bike.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.coolu.nokelock.bike.activity.BalanceAddActivity;
import com.coolu.nokelock.bike.activity.VipComboActivity;
import com.coolu.nokelock.bike.util.Config;
import com.fitsleep.sunshinelibrary.utils.IntentUtils;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, Config.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
        ToastUtils.showMessage("支付失败");
        Log.e("llll","rrrrrr");
        finish();
    }

    @Override
    public void onResp(BaseResp resp) {
        Intent intent = new Intent(Config.WX_PAY);
        Log.e("llll","kkkkkkkkkkkkk");
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == -2) {
                intent.putExtra("pay", false);
                ToastUtils.showMessage("支付取消");
            } else if (resp.errCode == -1){
                intent.putExtra("pay", false);
                ToastUtils.showMessage("支付失败");
            }else {
                intent.putExtra("pay", true);
                ToastUtils.showMessage("支付成功");
//                startActivity(new Intent(WXPayEntryActivity.this, VipComboActivity.class));
                WXPayEntryActivity.this.finish();
            }
        } else {
            intent.putExtra("pay", false);
            ToastUtils.showMessage("支付失败");
        }
        sendBroadcast(intent);
        try {
            Thread.sleep(500);
            IntentUtils.finish(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }




    }
}