package com.coolu.nokelock.bike.bean;

/**
 * @author Learning
 * @date 2019/3/8
 */
public class ReturnBalanceBean {
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
