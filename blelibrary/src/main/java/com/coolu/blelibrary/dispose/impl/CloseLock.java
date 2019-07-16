package com.coolu.blelibrary.dispose.impl;

import com.coolu.blelibrary.config.Config;
import com.coolu.blelibrary.dispose.BaseHandler;
import com.coolu.blelibrary.utils.GlobalParameterUtils;

/**
 * 复位指令
 * Created by sunshine on 2017/2/20.
 */

public class CloseLock extends BaseHandler {
    @Override
    protected void handler(String hexString, int state) {
        if (hexString.startsWith("050D0101")){
            GlobalParameterUtils.getInstance().sendBroadcast(Config.CLOSE_ACTION,"");
        }else {
            GlobalParameterUtils.getInstance().sendBroadcast(Config.CLOSE_ACTION,hexString);
        }
    }

    @Override
    protected String action() {
        return "0508";
    }
}
