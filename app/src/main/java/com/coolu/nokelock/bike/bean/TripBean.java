package com.coolu.nokelock.bike.bean;





import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2017/8/18.
 */

public class TripBean implements Serializable{
    private String errorCode;
    private String message;
    private List<Result> result;

    public class Result implements Serializable{
        private String id;
        private String pkuser;
        private String orderNo;
        private String userId;
        private String startTime;
        private String useMinute;
        private double  userMoney;
        private String money;
        private String status;
        private String sysTime;
        private String kcal;
        private String runs;
        private String barCode;

        public double getUserMoney() {
            return userMoney;
        }

        public void setUserMoney(double userMoney) {
            this.userMoney = userMoney;
        }

        public String getBarCode() {
            return barCode;
        }

        public void setBarCode(String barCode) {
            this.barCode = barCode;
        }

        public String getRuns() {
            return runs;
        }

        public void setRuns(String runs) {
            this.runs = runs;
        }

        public String getKcal() {
            return kcal;
        }

        public void setKcal(String kcal) {
            this.kcal = kcal;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPkuser() {
            return pkuser;
        }

        public void setPkuser(String pkuser) {
            this.pkuser = pkuser;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getUseMinute() {
            return useMinute;
        }

        public void setUseMinute(String useMinute) {
            this.useMinute = useMinute;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSysTime() {
            return sysTime;
        }

        public void setSysTime(String sysTime) {
            this.sysTime = sysTime;
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
