package com.coolu.nokelock.bike.bean;

/**
 * Created by admin on 2017/9/22.
 */

public class OpenCloseBean {


    /**
     * errorCode : 200
     * message : 成功
     * result : {"lockdata":"","shebeiid":"","starttime":"","openmoney":"-30","riding":{"id":911175143185842176,"pkuser":897667898452082688,"orderNo":"60f072a08bce244b5ca85b40ecce578a93","userId":18813151324,"barCode":"http://app.coolubike.com/app.html?id=335120190","startTime":"2017-09-22 18:31:27","endTime":"2017-09-22 18:35:43","useMinute":4,"money":3,"status":1,"sysTime":"2017-09-22 18:27:38","userBonus":0,"userMoney":3,"kcal":44,"runs":0,"startlng":116.191922,"startlat":39.913045,"endlng":116.191922,"endlat":39.913045},"returnmoney":"0","user":{"id":897667898452082688,"userId":18813151324,"nicName":"18813151324","idcheck":0,"pinNo":"2984","pinTime":"2017-08-22 11:01:30","userToken":"06883fc21b68599187df134811df413d","loginTime":"2017-08-21 13:56:03","deposit":0,"defaultDeposit":99,"orderNo":"","userMoney":-27,"userBonus":0,"userType":0,"userStatus":0,"userCredit":100,"idealMoney":0,"userLevel":0,"userFrom":"","userFromUrl":"","userLabel":"北戴河"},"ridingprice":"","lockid":"","locktype":"","lockmac":""}
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
         * lockdata :
         * shebeiid :
         * starttime :
         * openmoney : -30
         * riding : {"id":911175143185842176,"pkuser":897667898452082688,"orderNo":"60f072a08bce244b5ca85b40ecce578a93","userId":18813151324,"barCode":"http://app.coolubike.com/app.html?id=335120190","startTime":"2017-09-22 18:31:27","endTime":"2017-09-22 18:35:43","useMinute":4,"money":3,"status":1,"sysTime":"2017-09-22 18:27:38","userBonus":0,"userMoney":3,"kcal":44,"runs":0,"startlng":116.191922,"startlat":39.913045,"endlng":116.191922,"endlat":39.913045}
         * returnmoney : 0
         * user : {"id":897667898452082688,"userId":18813151324,"nicName":"18813151324","idcheck":0,"pinNo":"2984","pinTime":"2017-08-22 11:01:30","userToken":"06883fc21b68599187df134811df413d","loginTime":"2017-08-21 13:56:03","deposit":0,"defaultDeposit":99,"orderNo":"","userMoney":-27,"userBonus":0,"userType":0,"userStatus":0,"userCredit":100,"idealMoney":0,"userLevel":0,"userFrom":"","userFromUrl":"","userLabel":"北戴河"}
         * ridingprice :
         * lockid :
         * locktype :
         * lockmac :
         */

        private String lockdata;
        private String shebeiid;
        private String starttime;
        private String openmoney;
        private RidingBean riding;
        private String returnmoney;
        private UserBean user;
        private String ridingprice;
        private String lockid;
        private String locktype;
        private String lockmac;

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

        public String getOpenmoney() {
            return openmoney;
        }

        public void setOpenmoney(String openmoney) {
            this.openmoney = openmoney;
        }

        public RidingBean getRiding() {
            return riding;
        }

        public void setRiding(RidingBean riding) {
            this.riding = riding;
        }

        public String getReturnmoney() {
            return returnmoney;
        }

        public void setReturnmoney(String returnmoney) {
            this.returnmoney = returnmoney;
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

        public static class RidingBean {
            /**
             * id : 911175143185842176
             * pkuser : 897667898452082688
             * orderNo : 60f072a08bce244b5ca85b40ecce578a93
             * userId : 18813151324
             * barCode : http://app.coolubike.com/app.html?id=335120190
             * startTime : 2017-09-22 18:31:27
             * endTime : 2017-09-22 18:35:43
             * useMinute : 4
             * money : 3.0
             * status : 1
             * sysTime : 2017-09-22 18:27:38
             * userBonus : 0.0
             * userMoney : 3.0
             * kcal : 44
             * runs : 0
             * startlng : 116.191922
             * startlat : 39.913045
             * endlng : 116.191922
             * endlat : 39.913045
             */

            private long id;
            private long pkuser;
            private String orderNo;
            private long userId;
            private String barCode;
            private String startTime;
            private String endTime;
            private String useMinute;
            private String money;
            private String status;
            private String sysTime;
            private String userBonus;
            private double userMoney;
            private String kcal;
            private int runs;
            private String startlng;
            private String startlat;
            private String endlng;
            private String endlat;

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

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
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

            public String getUserBonus() {
                return userBonus;
            }

            public void setUserBonus(String userBonus) {
                this.userBonus = userBonus;
            }

            public double getUserMoney() {
                return userMoney;
            }

            public void setUserMoney(double userMoney) {
                this.userMoney = userMoney;
            }

            public String getKcal() {
                return kcal;
            }

            public void setKcal(String kcal) {
                this.kcal = kcal;
            }

            public int getRuns() {
                return runs;
            }

            public void setRuns(int runs) {
                this.runs = runs;
            }

            public String getStartlng() {
                return startlng;
            }

            public void setStartlng(String startlng) {
                this.startlng = startlng;
            }

            public String getStartlat() {
                return startlat;
            }

            public void setStartlat(String startlat) {
                this.startlat = startlat;
            }

            public String getEndlng() {
                return endlng;
            }

            public void setEndlng(String endlng) {
                this.endlng = endlng;
            }

            public String getEndlat() {
                return endlat;
            }

            public void setEndlat(String endlat) {
                this.endlat = endlat;
            }
        }

        public static class UserBean {
            /**
             * id : 897667898452082688
             * userId : 18813151324
             * nicName : 18813151324
             * idcheck : 0
             * pinNo : 2984
             * pinTime : 2017-08-22 11:01:30
             * userToken : 06883fc21b68599187df134811df413d
             * loginTime : 2017-08-21 13:56:03
             * deposit : 0.0
             * defaultDeposit : 99.0
             * orderNo :
             * userMoney : -27.0
             * userBonus : 0
             * userType : 0
             * userStatus : 0
             * userCredit : 100
             * idealMoney : 0.0
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
            private double deposit;
            private double defaultDeposit;
            private String orderNo;
            private double userMoney;
            private int userBonus;
            private int userType;
            private int userStatus;
            private int userCredit;
            private double idealMoney;
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

            public double getDeposit() {
                return deposit;
            }

            public void setDeposit(double deposit) {
                this.deposit = deposit;
            }

            public double getDefaultDeposit() {
                return defaultDeposit;
            }

            public void setDefaultDeposit(double defaultDeposit) {
                this.defaultDeposit = defaultDeposit;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public double getUserMoney() {
                return userMoney;
            }

            public void setUserMoney(double userMoney) {
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

            public int getUserCredit() {
                return userCredit;
            }

            public void setUserCredit(int userCredit) {
                this.userCredit = userCredit;
            }

            public double getIdealMoney() {
                return idealMoney;
            }

            public void setIdealMoney(double idealMoney) {
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
}
