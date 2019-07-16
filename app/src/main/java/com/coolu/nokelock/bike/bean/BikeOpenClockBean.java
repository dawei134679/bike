package com.coolu.nokelock.bike.bean;

/**
 * Created by admin on 2017/10/12.
 */

public class BikeOpenClockBean {

    /**
     * errorCode : 200
     * message : 成功
     * result : {"id":918407552679673856,"pkuser":897667898452082688,"orderNo":"602e1d1138bce4477196f68c633e077bca","userId":18813151324,"barCode":"http://app.coolubike.com/app.html?id=335120190","startTime":"2017-10-12 17:26:45","useMinute":0,"money":0,"status":0,"sysTime":"2017-10-12 17:26:38","kcal":0,"runs":0,"startlng":116.191921,"startlat":39.913008}
     */

    private int errorCode;
    private String message;
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 918407552679673856
         * pkuser : 897667898452082688
         * orderNo : 602e1d1138bce4477196f68c633e077bca
         * userId : 18813151324
         * barCode : http://app.coolubike.com/app.html?id=335120190
         * startTime : 2017-10-12 17:26:45
         * useMinute : 0
         * money : 0.0
         * status : 0
         * sysTime : 2017-10-12 17:26:38
         * kcal : 0
         * runs : 0
         * startlng : 116.191921
         * startlat : 39.913008
         */

        private long id;
        private long pkuser;
        private String orderNo;
        private long userId;
        private String barCode;
        private String startTime;
        private int useMinute;
        private double money;
        private int status;
        private String sysTime;
        private int kcal;
        private int runs;
        private double startlng;
        private double startlat;

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

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public String getBarCode() {
            return barCode;
        }

        public void setBarCode(String barCode) {
            this.barCode = barCode;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public int getUseMinute() {
            return useMinute;
        }

        public void setUseMinute(int useMinute) {
            this.useMinute = useMinute;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getSysTime() {
            return sysTime;
        }

        public void setSysTime(String sysTime) {
            this.sysTime = sysTime;
        }

        public int getKcal() {
            return kcal;
        }

        public void setKcal(int kcal) {
            this.kcal = kcal;
        }

        public int getRuns() {
            return runs;
        }

        public void setRuns(int runs) {
            this.runs = runs;
        }

        public double getStartlng() {
            return startlng;
        }

        public void setStartlng(double startlng) {
            this.startlng = startlng;
        }

        public double getStartlat() {
            return startlat;
        }

        public void setStartlat(double startlat) {
            this.startlat = startlat;
        }
    }
}
