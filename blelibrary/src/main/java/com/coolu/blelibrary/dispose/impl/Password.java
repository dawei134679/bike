package com.coolu.blelibrary.dispose.impl;

import com.coolu.blelibrary.config.Config;
import com.coolu.blelibrary.dispose.BaseHandler;
import com.coolu.blelibrary.utils.GlobalParameterUtils;

/**
 * 修改密码
 * Created by sunshine on 2017/2/20.
 */

public class Password extends BaseHandler {
    @Override
    protected void handler(String hexString, int state) {
//        byte[] mingwen = ConvertUtils.hexString2Bytes(hexString);
//        Logger.e("close lock","decrypt:"+ ConvertUtils.bytes2HexString(mingwen));
//        Intent intent = new Intent();
//        intent.setAction(Config.PASSWORD_ACTION);
        if (hexString.startsWith("05050101")){
            GlobalParameterUtils.getInstance().sendBroadcast(Config.PASSWORD_ACTION,"");
        }else {
//            intent.putExtra("data",ConvertUtils.bytes2HexString(mingwen));
            GlobalParameterUtils.getInstance().sendBroadcast(Config.PASSWORD_ACTION,hexString);
        }

//        GlobalParameterUtils.getInstance().getContext().sendBroadcast(intent);
    }

    @Override
    protected String action() {
        return "0505";
    }
}
