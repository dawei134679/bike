package com.coolu.nokelock.bike.bean;

import org.greenrobot.greendao.annotation.Entity;

/**
 * Created by admin on 2017/9/22.
 * 新接口中的  人员信息表
 */

public class UserEntity {

    /**
     * errorCode : 200
     * message : 查询成功
     * result : {"lockdata":"QRw+BVlKHBlSP2JhOSEpGQMBBgIBBQ==","shebeiid":"063075559563","starttime":"2017-09-22 15:04:06","openmoney":"-30","returnmoney":"0","barcode":"http://app.coolubike.com/app.html?id=335120190","user":{"id":897667898452082700,"userId":18813151324,"nicName":"18813151324","idcheck":0,"pinNo":"2984","pinTime":"2017-08-22 11:01:30","userToken":"06883fc21b68599187df134811df413d","loginTime":"2017-08-21 13:56:03","deposit":0,"defaultDeposit":99,"orderNo":"60e33526d0f1554d0ba0d6343116a2b1bd","userMoney":0,"userBonus":0,"userType":0,"userStatus":1,"userCredit":100,"idealMoney":0,"userLevel":0,"userFrom":"","userFromUrl":"","userLabel":"北戴河"},"ridingprice":3,"lockid":"335120190","locktype":"ble2","lockmac":"D4:36:39:D8:77:E2"}
     */

    private String errorCode;
    private String message;
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * lockdata : QRw+BVlKHBlSP2JhOSEpGQMBBgIBBQ==
         * shebeiid : 063075559563
         * starttime : 2017-09-22 15:04:06
         * openmoney : -30
         * returnmoney : 0
         * barcode : http://app.coolubike.com/app.html?id=335120190
         * user : {"id":897667898452082700,"userId":18813151324,"nicName":"18813151324","idcheck":0,"pinNo":"2984","pinTime":"2017-08-22 11:01:30","userToken":"06883fc21b68599187df134811df413d","loginTime":"2017-08-21 13:56:03","deposit":0,"defaultDeposit":99,"orderNo":"60e33526d0f1554d0ba0d6343116a2b1bd","userMoney":0,"userBonus":0,"userType":0,"userStatus":1,"userCredit":100,"idealMoney":0,"userLevel":0,"userFrom":"","userFromUrl":"","userLabel":"北戴河"}
         * ridingprice : 3
         * lockid : 335120190
         * locktype : ble2
         * lockmac : D4:36:39:D8:77:E2
         */

        private String lockdata;
        private String shebeiid;
        private String starttime;
        private float openmoney;
        private String returnmoney;
        private String barcode;
        private UserBean user;
        private String ridingprice;
        private String lockid;
        private String locktype;
        private String lockmac;
        private String forcemoney;
        private String cardprice;
        //    Learning add
//    控制会员卡套餐界面的先不购买按钮的显示
        private String isbuyridingcard;
        private float newopenmoney;
        private float warningmoney;

        public float getWarningmoney() {
            return warningmoney;
        }

        public void setWarningmoney(float warningmoney) {
            this.warningmoney = warningmoney;
        }

        public float getNewopenmoney() {
            return newopenmoney;
        }

        public void setNewopenmoney(float newopenmoney) {
            this.newopenmoney = newopenmoney;
        }

        public String getIsbuyridingcard() {
            return isbuyridingcard;
        }

        public void setIsbuyridingcard(String isbuyridingcard) {
            this.isbuyridingcard = isbuyridingcard;
        }

        public String getCardprice() {
            return cardprice;
        }

        public void setCardprice(String cardprice) {
            this.cardprice = cardprice;
        }

        public String getForcemoney() {
            return forcemoney;
        }

        public void setForcemoney(String forcemoney) {
            this.forcemoney = forcemoney;
        }

        public String getLockdata() {
            return lockdata;
        }

        public void setLockdata(String lockdata) {
            this.lockdata = lockdata;
        }

        public String getShebeiid() {
            return shebeiid;
        }

        public void setShebeiid(String shebeiid) {
            this.shebeiid = shebeiid;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public float getOpenmoney() {
            return openmoney;
        }

        public void setOpenmoney(float openmoney) {
            this.openmoney = openmoney;
        }

        public String getReturnmoney() {
            return returnmoney;
        }

        public void setReturnmoney(String returnmoney) {
            this.returnmoney = returnmoney;
        }

        public String getBarcode() {
            return barcode;
        }

        public void setBarcode(String barcode) {
            this.barcode = barcode;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getRidingprice() {
            return ridingprice;
        }

        public void setRidingprice(String ridingprice) {
            this.ridingprice = ridingprice;
        }

        public String getLockid() {
            return lockid;
        }

        public void setLockid(String lockid) {
            this.lockid = lockid;
        }

        public String getLocktype() {
            return locktype;
        }

        public void setLocktype(String locktype) {
            this.locktype = locktype;
        }

        public String getLockmac() {
            return lockmac;
        }

        public void setLockmac(String lockmac) {
            this.lockmac = lockmac;
        }

        public static class UserBean {
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
             * orderNo : 60e33526d0f1554d0ba0d6343116a2b1bd
             * userMoney : 0
             * userBonus : 0
             * userType : 0
             * userStatus : 1
             * userCredit : 100
             * idealMoney : 0
             * userLevel : 0
             * userFrom :
             * userFromUrl :
             * userLabel : 北戴河
             */

            private String id;
            private String userId;
            private String nicName;
            private String idcheck;
            private String pinNo;
            private String pinTime;
            private String userToken;
            private String loginTime;
            private String deposit;
            private String defaultDeposit;
            private String orderNo;
            private String userMoney;
            private String userBonus;
            private String userType;
            private String userStatus;
            private String userCredit;
            private String idealMoney;
            private String userLevel;
            private String userFrom;
            private String userFromUrl;
            private String userLabel;
            private String userPic;
            private String userLevelEndTime;
            private String userName;
            private String idno;

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getIdno() {
                return idno;
            }

            public void setIdno(String idno) {
                this.idno = idno;
            }

            public String getUserLevelEndTime() {
                return userLevelEndTime;
            }

            public void setUserLevelEndTime(String userLevelEndTime) {
                this.userLevelEndTime = userLevelEndTime;
            }

            public String getUserPic() {
                return userPic;
            }

            public void setUserPic(String userPic) {
                this.userPic = userPic;
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

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
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

            public String getUserCredit() {
                return userCredit;
            }

            public void setUserCredit(String userCredit) {
                this.userCredit = userCredit;
            }

            public String getIdealMoney() {
                return idealMoney;
            }

            public void setIdealMoney(String idealMoney) {
                this.idealMoney = idealMoney;
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

            public String getUserLabel() {
                return userLabel;
            }

            public void setUserLabel(String userLabel) {
                this.userLabel = userLabel;
            }
        }
    }
}
