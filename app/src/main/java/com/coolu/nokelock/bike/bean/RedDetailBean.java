package com.coolu.nokelock.bike.bean;

import java.util.List;

/**
 * Created by admin on 2017/8/17.
 */

public class RedDetailBean {
    private String errorCode;
    private String message;
    private List<Result> result;

    public  class Result {
        private String id;
        private String orderNo;
        private String pkriding;
        private String pkuser;
        private String money;
        private int payType=-1;
        private String payWhat;
        private int payStatus;
        private String startTime;
        private String endTime;
        private String sysTime;
        private String userId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getPkriding() {
            return pkriding;
        }

        public void setPkriding(String pkriding) {
            this.pkriding = pkriding;
        }

        public String getPkuser() {
            return pkuser;
        }

        public void setPkuser(String pkuser) {
            this.pkuser = pkuser;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }

        public String getPayWhat() {
            return payWhat;
        }

        public void setPayWhat(String payWhat) {
            this.payWhat = payWhat;
        }

        public int getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(int payStatus) {
            this.payStatus = payStatus;
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

        public String getSysTime() {
            return sysTime;
        }

        public void setSysTime(String sysTime) {
            this.sysTime = sysTime;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }
}
