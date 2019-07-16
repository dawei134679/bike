package com.coolu.nokelock.bike.bean;

/**
 * 费用
 * Created by sunshine on 2017/3/15.
 */

public class OrderMoneyBean {

    /**
     * status : ok
     * result : {"ordernum":"b9fa43afcc1e4530beb364d5d9f2415c","starttime":"1489542259","endtime":"1","money":"0.50"}
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
         * ordernum : b9fa43afcc1e4530beb364d5d9f2415c
         * starttime : 1489542259
         * endtime : 1
         * money : 0.50
         */

        private String ordernum;
        private String starttime;
        private String endtime;
        private String money;

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
