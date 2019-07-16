package com.coolu.nokelock.bike.bean;

/**
 * Created by admin on 2017/8/21.
 */

public class GetCode {
    private String errorCode;
    private String message;
    private  String result;

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
