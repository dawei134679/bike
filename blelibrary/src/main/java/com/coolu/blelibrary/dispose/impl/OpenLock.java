package com.coolu.blelibrary.dispose.impl;

import android.util.Log;

import com.coolu.blelibrary.config.Config;
import com.coolu.blelibrary.dispose.BaseHandler;
import com.coolu.blelibrary.utils.GlobalParameterUtils;

/**
 * 开锁指令
 * Created by sunshine on 2017/2/20.
 */

public class OpenLock extends BaseHandler {
    @Override
    protected void handler(String hexString, int state) {
//        byte[] mingwen = ConvertUtils.hexString2Bytes(hexString);
//        Logger.e("Token","decrypt:"+ ConvertUtils.bytes2HexString(mingwen));
//        Intent intent = new Intent();
//        intent.setAction(Config.OPEN_ACTION);
        if (hexString.startsWith("05020101")){
            GlobalParameterUtils.getInstance().sendBroadcast(Config.OPEN_ACTION,"");
          //  Log.e("mmp","我草111");
        }else {
//            intent.putExtra("data",ConvertUtils.bytes2HexString(mingwen));
            GlobalParameterUtils.getInstance().sendBroadcast(Config.OPEN_ACTION,hexString);
         //   Log.e("mmp","我草222");
        }

//        GlobalParameterUtils.getInstance().getContext().sendBroadcast(intent);
    }

    @Override
    protected String action() {
        return "0502";
    }
}
