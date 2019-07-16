package com.coolu.nokelock.bike.bean;

import java.util.List;

/**
 * Created by admin on 2017/8/21.
 */

public class UserInfoo{
    private String errorCode;
    private String message;
    //private List<UserInfo.Result> result;
    private Result result;

    public class Result{
        private  String id;
        private  String userId;
        private  String nicName;
        private  String idcheck;
        private  String pinNo;
        private  String pinTime;
        private  String userToken;
        private  String loginTime;
        private  String deposit;
        private  String defaultDeposit;
        private  String userMoney;
        private  String userBonus;
        private  String userType;
        private  String userStatus;
        private  String sysTime;
        private  String userCredit;
        private  String idealMoney;
        private  String userLevel;
        private  String userFrom;
        private  String userFromUrl;
        private  String userLabel;

        public String getUserLabel() {
            return userLabel;
        }

        public void setUserLabel(String userLabel) {
            this.userLabel = userLabel;
        }



        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getNicName() {
            return nicName;
        }

        public void setNicName(String nicName) {
            this.nicName = nicName;
        }

        public String getIdcheck() {
            return idcheck;
        }

        public void setIdcheck(String idcheck) {
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

        public String getDeposit() {
            return deposit;
        }

        public void setDeposit(String deposit) {
            this.deposit = deposit;
        }

        public String getDefaultDeposit() {
            return defaultDeposit;
        }

        public void setDefaultDeposit(String defaultDeposit) {
            this.defaultDeposit = defaultDeposit;
        }

        public String getUserMoney() {
            return userMoney;
        }

        public void setUserMoney(String userMoney) {
            this.userMoney = userMoney;
        }

        public String getUserBonus() {
            return userBonus;
        }

        public void setUserBonus(String userBonus) {
            this.userBonus = userBonus;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getUserStatus() {
            return userStatus;
        }

        public void setUserStatus(String userStatus) {
            this.userStatus = userStatus;
        }

        public String getSysTime() {
            return sysTime;
        }

        public void setSysTime(String sysTime) {
            this.sysTime = sysTime;
        }

        public String getUserCredit() {
            return userCredit;
        }

        public void setUserCredit(String userCredit) {
            this.userCredit = userCredit;
        }

        public String getIdeaMoney() {
            return idealMoney;
        }

        public void setIdeaMoney(String ideaMoney) {
            this.idealMoney = ideaMoney;
        }

        public String getUserLevel() {
            return userLevel;
        }

        public void setUserLevel(String userLevel) {
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

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
