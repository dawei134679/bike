package com.coolu.nokelock.bike.bean;

/**
 * Created by admin on 2017/9/11.
 */

public class JoinBean {

    /**
     * errorCode : 200
     * message : 参加成功
     */

    private int errorCode;
    private String message;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
