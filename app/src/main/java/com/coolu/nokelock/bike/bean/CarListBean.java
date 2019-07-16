package com.coolu.nokelock.bike.bean;

import java.util.List;

/**
 * 车辆列表
 * Created by sunshine on 2017/3/5.
 */

public class CarListBean {


    /**
     * status : ok
     * result : [{"lockid":"12345678","lng":"108.228844","lat":"22.888888"},{"lockid":"12345678","lng":"108.228844","lat":"22.888888"}]
     */

    private String status;
    private List<ResultEntity> result;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResult(List<ResultEntity> result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public List<ResultEntity> getResult() {
        return result;
    }

    public static class ResultEntity {
        /**
         * lockid : 12345678
         * lng : 108.228844
         * lat : 22.888888
         */

        private String lockid;
        private String lng;
        private String lat;

        public void setLockid(String lockid) {
            this.lockid = lockid;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLockid() {
            return lockid;
        }

        public String getLng() {
            return lng;
        }

        public String getLat() {
            return lat;
        }
    }
}
