package com.coolu.nokelock.bike.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by FlyingStarts on 2018/5/21.
 */

public class OpenImageBean {


    /**
     * errorCode : 200
     * message : 秦皇岛
     * result : {"\u201cactivityUrl\u201d":"http://www.onetriptech.com/images/apptest/card.jpg","\u201cjumpUrl\u201d":"http://www.onetriptech.com/images/apptest/jump.html"}
     */

    private int errorCode;
    private String message;
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        @SerializedName("“activityUrl”")
        private String activityUrl; // FIXME check this code
        @SerializedName("“jumpUrl”")
        private String jumpUrl; // FIXME check this code

        public String getactivityUrl() {
            return activityUrl;
        }

        public void setactivityUrl(String activityUrl) {
            this.activityUrl = activityUrl;
        }

        public String getjumpUrl() {
            return jumpUrl;
        }

        public void setjumpUrl(String jumpUrl) {
            this.jumpUrl = jumpUrl;
        }
    }
}
