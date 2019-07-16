package com.coolu.nokelock.bike.bean;

/**
 * Created by Administrator on 2018/10/26.
 */

public class YanzhengBean {
    /**
     * errorCode : 200
     * message : 成功
     * result : {"codeid":"500239df2c0f5c9c403a859f2f34a7615917","imageurl":"http://w.coolubike.com:/imagecode/500239df2c0f5c9c403a859f2f34a7615917.jpg"}
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
        /**
         * codeid : 500239df2c0f5c9c403a859f2f34a7615917
         * imageurl : http://w.coolubike.com:/imagecode/500239df2c0f5c9c403a859f2f34a7615917.jpg
         */

        private String codeid;
        private String imageurl;

        public String getCodeid() {
            return codeid;
        }

        public void setCodeid(String codeid) {
            this.codeid = codeid;
        }

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }
    }
}
