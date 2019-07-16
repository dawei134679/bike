package com.coolu.nokelock.bike.bean;

import java.util.List;

/**
 * 支付明细
 * Created by sunshine on 2017/3/15.
 */

public class PayListBean {

    /**
     * status : ok
     * result : [{"date":"1489544127","source":"支付宝","type":"保证金","money":"0.50","state":"正在退款"}]
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
         * date : 1489544127
         * source : 支付宝
         * type : 保证金
         * money : 0.50
         * state : 正在退款
         */

        private String date;
        private String source;
        private String type;
        private String money;
        private String state;

        public void setDate(String date) {
            this.date = date;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getDate() {
            return date;
        }

        public String getSource() {
            return source;
        }

        public String getType() {
            return type;
        }

        public String getMoney() {
            return money;
        }

        public String getState() {
            return state;
        }
    }
}
