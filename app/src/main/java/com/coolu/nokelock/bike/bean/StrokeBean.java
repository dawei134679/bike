package com.coolu.nokelock.bike.bean;

import java.util.List;

/**
 * 我的行程
 * Created by sunshine on 2017/3/7.
 */

public class StrokeBean {

    /**
     * status : ok
     * result : [{"lockid":"12345678","usedate":"2016-02-22","minute":"8","money":"8.00"},{"lockid":"12345678","usedate":"2016-02-22","minute":"28","money":"18.00"}]
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
         * usedate : 2016-02-22
         * minute : 8
         * money : 8.00
         */

        private String lockid;
        private String usedate;
        private String minute;
        private String money;

        public void setLockid(String lockid) {
            this.lockid = lockid;
        }

        public void setUsedate(String usedate) {
            this.usedate = usedate;
        }

        public void setMinute(String minute) {
            this.minute = minute;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getLockid() {
            return lockid;
        }

        public String getUsedate() {
            return usedate;
        }

        public String getMinute() {
            return minute;
        }

        public String getMoney() {
            return money;
        }
    }
}
