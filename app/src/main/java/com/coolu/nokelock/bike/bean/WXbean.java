package com.coolu.nokelock.bike.bean;

/**
 * Created by admin on 2017/9/27.
 */

public class WXbean {

    /**
     * errorCode : 200
     * message : 订单返回成功
     * result : {"sign":"108EB7EDCEA23DEF1B4E3A4E234E96FF","timestamp":"1506480760","partnerid":"1489543242","noncestr":"1052405334","prepayid":"wx201709271052394d1e2597230946624431","package":"Sign=WXPay","appid":"wxb1549732a56ed4bd","msg":"ok"}
     */

    private String errorCode;
    private String message;
    private String result;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
