package com.coolu.nokelock.bike.bean;

import java.util.List;

/**
 * Created by admin on 2017/9/11.
 */

public class CardBean {
    /**
     * errorCode : 200
     * message : 查询成功
     * result : [{"id":907130753119289344,"pkuser":897667898452082688,"pkcard":3,"cardMoney":30,"cardName":"民宿红包","cardLabel":"北戴河","cardType":2,"startTime":"2017-09-11 14:36:40","endTime":"2017-10-11 14:36:40","cardUrl":"http://www.onetriptech.com/images/apptest/Coupon1.jpg","sysTime":"2017-09-11 14:36:39","isUse":0,"cardFrom":1,"userId":18813151324},{"id":907132380001730560,"pkuser":897667898452082688,"pkcard":2,"cardMoney":20,"cardName":"碧螺塔红包","cardLabel":"北戴河","cardType":2,"startTime":"2017-09-11 14:43:08","endTime":"2017-10-11 14:43:08","cardUrl":"http://www.onetriptech.com/images/apptest/Coupon1.jpg","sysTime":"2017-09-11 14:43:07","isUse":0,"cardFrom":1,"userId":18813151324},{"id":907132781610532864,"pkuser":897667898452082688,"pkcard":4,"cardMoney":10,"cardName":"老虎石红包","cardLabel":"北戴河","cardType":2,"startTime":"2017-09-11 14:44:44","endTime":"2017-10-11 14:44:44","cardUrl":"http://www.onetriptech.com/images/apptest/Coupon1.jpg","sysTime":"2017-09-11 14:44:43","isUse":0,"cardFrom":1,"userId":18813151324}]
     */

    private int errorCode;
    private String message;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 907130753119289344
         * pkuser : 897667898452082688
         * pkcard : 3
         * cardMoney : 30.0
         * cardName : 民宿红包
         * cardLabel : 北戴河
         * cardType : 2
         * startTime : 2017-09-11 14:36:40
         * endTime : 2017-10-11 14:36:40
         * cardUrl : http://www.onetriptech.com/images/apptest/Coupon1.jpg
         * sysTime : 2017-09-11 14:36:39
         * isUse : 0
         * cardFrom : 1
         * userId : 18813151324
         */

        private long id;
        private long pkuser;
        private int pkcard;
        private double cardMoney;
        private String cardName;
        private String cardLabel;
        private int cardType;
        private String startTime;
        private String endTime;
        private String cardUrl;
        private String sysTime;
        private int isUse;
        private int cardFrom;
        private long userId;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getPkuser() {
            return pkuser;
        }

        public void setPkuser(long pkuser) {
            this.pkuser = pkuser;
        }

        public int getPkcard() {
            return pkcard;
        }

        public void setPkcard(int pkcard) {
            this.pkcard = pkcard;
        }

        public double getCardMoney() {
            return cardMoney;
        }

        public void setCardMoney(double cardMoney) {
            this.cardMoney = cardMoney;
        }

        public String getCardName() {
            return cardName;
        }

        public void setCardName(String cardName) {
            this.cardName = cardName;
        }

        public String getCardLabel() {
            return cardLabel;
        }

        public void setCardLabel(String cardLabel) {
            this.cardLabel = cardLabel;
        }

        public int getCardType() {
            return cardType;
        }

        public void setCardType(int cardType) {
            this.cardType = cardType;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getCardUrl() {
            return cardUrl;
        }

        public void setCardUrl(String cardUrl) {
            this.cardUrl = cardUrl;
        }

        public String getSysTime() {
            return sysTime;
        }

        public void setSysTime(String sysTime) {
            this.sysTime = sysTime;
        }

        public int getIsUse() {
            return isUse;
        }

        public void setIsUse(int isUse) {
            this.isUse = isUse;
        }

        public int getCardFrom() {
            return cardFrom;
        }

        public void setCardFrom(int cardFrom) {
            this.cardFrom = cardFrom;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }
    }
}
