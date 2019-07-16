package com.coolu.nokelock.bike.bean;

import java.util.List;

/**
 * 作者: Sunshine
 * 时间: 2017/7/10.
 * 邮箱: 44493547@qq.com
 * 描述:
 */

public class CouponBean {

    /**
     * status : ok
     * result : [{"startday":"2017-07-01","endday":"2017-07-31","days":"六日","type":"money","money":"5.00","url":""}]
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
         * startday : 2017-07-01
         * endday : 2017-07-31
         * days : 六日
         * type : money
         * money : 5.00
         * url :
         */

        private String title;
        private String startday;
        private String endday;
        private String days;
        private String type;
        private String money;
        private String url;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setStartday(String startday) {
            this.startday = startday;
        }

        public void setEndday(String endday) {
            this.endday = endday;
        }

        public void setDays(String days) {
            this.days = days;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getStartday() {
            return startday;
        }

        public String getEndday() {
            return endday;
        }

        public String getDays() {
            return days;
        }

        public String getType() {
            return type;
        }

        public String getMoney() {
            return money;
        }

        public String getUrl() {
            return url;
        }
    }
}
