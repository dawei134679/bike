package com.coolu.nokelock.bike.bean;

/**
 * Gps订单情况
 * Created by sunshine on 2017/3/24.
 */

public class GpsStateBean {

    /**
     * status : ok
     * result : {"state":"using","ordernum":"e98a04d2c20f41538f81873009113f8a","starttime":"1490320299","endtime":"1","money":"0.50"}
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
         * state : using
         * ordernum : e98a04d2c20f41538f81873009113f8a
         * starttime : 1490320299
         * endtime : 1
         * money : 0.50
         */

        private String state;
        private String ordernum;
        private String starttime;
        private String endtime;
        private String money;

        public void setState(String state) {
            this.state = state;
        }

        public void setOrdernum(String ordernum) {
            this.ordernum = ordernum;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getState() {
            return state;
        }

        public String getOrdernum() {
            return ordernum;
        }

        public String getStarttime() {
            return starttime;
        }

        public String getEndtime() {
            return endtime;
        }

        public String getMoney() {
            return money;
        }
    }
}
