package com.coolu.nokelock.bike.bean;

/**
 * Created by admin on 2017/10/24.
 */

public class VersionBean {


    /**
     * errorCode : 200
     * message : 查询成功请更新
     * result : {"id":4,"versionApp":"1.1.2","versionSort":1,"isUpdate":0,"dlURL":"http:www.baidu.com","phoneSystem":"android","sysTime":"2017-10-24 15:08:08"}
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
         * id : 4
         * versionApp : 1.1.2
         * versionSort : 1
         * isUpdate : 0
         * dlURL : http:www.baidu.com
         * phoneSystem : android
         * sysTime : 2017-10-24 15:08:08
         */

        private int id;
        private String versionApp;
        private int versionSort;
        private int isUpdate;
        private String dlURL;
        private String phoneSystem;
        private String sysTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getVersionApp() {
            return versionApp;
        }

        public void setVersionApp(String versionApp) {
            this.versionApp = versionApp;
        }

        public int getVersionSort() {
            return versionSort;
        }

        public void setVersionSort(int versionSort) {
            this.versionSort = versionSort;
        }

        public int getIsUpdate() {
            return isUpdate;
        }

        public void setIsUpdate(int isUpdate) {
            this.isUpdate = isUpdate;
        }

        public String getDlURL() {
            return dlURL;
        }

        public void setDlURL(String dlURL) {
            this.dlURL = dlURL;
        }

        public String getPhoneSystem() {
            return phoneSystem;
        }

        public void setPhoneSystem(String phoneSystem) {
            this.phoneSystem = phoneSystem;
        }

        public String getSysTime() {
            return sysTime;
        }

        public void setSysTime(String sysTime) {
            this.sysTime = sysTime;
        }
    }
}
