package com.coolu.nokelock.bike.bean;

/**
 * 启动页
 * Created by sunshine on 2017/3/25.
 */

public class StartPage {

    /**
     * status : ok
     * result : {"apptype":"android","picsize":"default","url":"http://service.rocolock.com:16888/Picture/720x1280.jpg"}
     */

    private String status;
    private ResultEntity result;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public ResultEntity getResult() {
        return result;
    }

    public static class ResultEntity {
        /**
         * apptype : android
         * picsize : default
         * url : http://service.rocolock.com:16888/Picture/720x1280.jpg
         */

        private String apptype;
        private String picsize;
        private String url;

        public void setApptype(String apptype) {
            this.apptype = apptype;
        }

        public void setPicsize(String picsize) {
            this.picsize = picsize;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getApptype() {
            return apptype;
        }

        public String getPicsize() {
            return picsize;
        }

        public String getUrl() {
            return url;
        }
    }
}
