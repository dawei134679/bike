package com.coolu.nokelock.bike.bean;

/**
 * Created by admin on 2017/9/15.
 */

public class UserBean {

    /**
     * errorCode : 200
     * message : 查询成功
     * result : {"id":897667898452082700,"userId":18813151324,"nicName":"18813151324","idcheck":0,"pinNo":"2984","pinTime":"2017-08-22 11:01:30","userToken":"06883fc21b68599187df134811df413d","loginTime":"2017-08-21 13:56:03","deposit":0,"defaultDeposit":99,"userMoney":0,"userBonus":0,"userType":0,"userStatus":0,"sysTime":"2017-08-16 11:54:39","userCredit":100,"idealMoney":0,"userLevel":0,"userFrom":"","userFromUrl":"","userLabel":"北戴河"}
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
         * id : 897667898452082700
         * userId : 18813151324
         * nicName : 18813151324
         * idcheck : 0
         * pinNo : 2984
         * pinTime : 2017-08-22 11:01:30
         * userToken : 06883fc21b68599187df134811df413d
         * loginTime : 2017-08-21 13:56:03
         * deposit : 0
         * defaultDeposit : 99
         * userMoney : 0
         * userBonus : 0
         * userType : 0
         * userStatus : 0
         * sysTime : 2017-08-16 11:54:39
         * userCredit : 100
         * idealMoney : 0
         * userLevel : 0
         * userFrom :
         * userFromUrl :
         * userLabel : 北戴河
         */

        private long id;
        private long userId;
        private String nicName;
        private int idcheck;
        private String pinNo;
        private String pinTime;
        private String userToken;
        private String loginTime;
        private int deposit;
        private int defaultDeposit;
        private int userMoney;
        private int userBonus;
        private int userType;
        private int userStatus;
        private String sysTime;
        private int userCredit;
        private int idealMoney;
        private int userLevel;
        private String userFrom;
        private String userFromUrl;
        private String userLabel;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public String getNicName() {
            return nicName;
        }

        public void setNicName(String nicName) {
            this.nicName = nicName;
        }

        public int getIdcheck() {
            return idcheck;
        }

        public void setIdcheck(int idcheck) {
            this.idcheck = idcheck;
        }

        public String getPinNo() {
            return pinNo;
        }

        public void setPinNo(String pinNo) {
            this.pinNo = pinNo;
        }

        public String getPinTime() {
            return pinTime;
        }

        public void setPinTime(String pinTime) {
            this.pinTime = pinTime;
        }

        public String getUserToken() {
            return userToken;
        }

        public void setUserToken(String userToken) {
            this.userToken = userToken;
        }

        public String getLoginTime() {
            return loginTime;
        }

        public void setLoginTime(String loginTime) {
            this.loginTime = loginTime;
        }

        public int getDeposit() {
            return deposit;
        }

        public void setDeposit(int deposit) {
            this.deposit = deposit;
        }

        public int getDefaultDeposit() {
            return defaultDeposit;
        }

        public void setDefaultDeposit(int defaultDeposit) {
            this.defaultDeposit = defaultDeposit;
        }

        public int getUserMoney() {
            return userMoney;
        }

        public void setUserMoney(int userMoney) {
            this.userMoney = userMoney;
        }

        public int getUserBonus() {
            return userBonus;
        }

        public void setUserBonus(int userBonus) {
            this.userBonus = userBonus;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public int getUserStatus() {
            return userStatus;
        }

        public void setUserStatus(int userStatus) {
            this.userStatus = userStatus;
        }

        public String getSysTime() {
            return sysTime;
        }

        public void setSysTime(String sysTime) {
            this.sysTime = sysTime;
        }

        public int getUserCredit() {
            return userCredit;
        }

        public void setUserCredit(int userCredit) {
            this.userCredit = userCredit;
        }

        public int getIdealMoney() {
            return idealMoney;
        }

        public void setIdealMoney(int idealMoney) {
            this.idealMoney = idealMoney;
        }

        public int getUserLevel() {
            return userLevel;
        }

        public void setUserLevel(int userLevel) {
            this.userLevel = userLevel;
        }

        public String getUserFrom() {
            return userFrom;
        }

        public void setUserFrom(String userFrom) {
            this.userFrom = userFrom;
        }

        public String getUserFromUrl() {
            return userFromUrl;
        }

        public void setUserFromUrl(String userFromUrl) {
            this.userFromUrl = userFromUrl;
        }

        public String getUserLabel() {
            return userLabel;
        }

        public void setUserLabel(String userLabel) {
            this.userLabel = userLabel;
        }
    }
}
