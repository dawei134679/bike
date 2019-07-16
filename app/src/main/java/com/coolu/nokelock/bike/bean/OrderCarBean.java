package com.coolu.nokelock.bike.bean;

/**
 * 车辆预约
 * Created by sunshine on 2017/3/9.
 */

public class OrderCarBean {

    /**
     * status : ok
     * result : {"lockid":"12345678","starttime":"2017-03-02 16:48:48","endtime":"48","state":"on"}
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
         * lockid : 12345678
         * starttime : 2017-03-02 16:48:48
         * endtime : 48
         * state : on
         */

        private String lockid;
        private String starttime;
        private String endtime;
        private String state;

        public void setLockid(String lockid) {
            this.lockid = lockid;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getLockid() {
            return lockid;
        }

        public String getStarttime() {
            return starttime;
        }

        public String getEndtime() {
            return endtime;
        }

        public String getState() {
            return state;
        }
    }
}
