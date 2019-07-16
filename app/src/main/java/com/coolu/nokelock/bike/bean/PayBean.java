package com.coolu.nokelock.bike.bean;

/**
 * Created by admin on 2017/9/25.
 */

public class PayBean {

    /**
     * errorCode : 200
     * message : 付款成功
     * result : ZFB912219501964165120
     */

    private String errorCode;
    private String message;
    private String result;

    public String  getErrorCode() {
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
