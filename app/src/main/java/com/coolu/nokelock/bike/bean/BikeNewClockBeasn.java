package com.coolu.nokelock.bike.bean;

/**
 * Created by admin on 2018/1/3.
 */

public class BikeNewClockBeasn {

    /**
     * lockdata :
     * shebeiid :
     * openmoney : -30
     * forcemoney : 20
     * starttime :
     * riding : {"pkuser":897667898452082688,"userBonus":0,"endlat":39.903996,"status":1,"kcal":0,"startlat":39.903996,"endTime":"2018-01-03 11:15:07","userMoney":0,"useMinute":0,"id":948392141527318528,"startTime":"2018-01-03 11:14:41","sysTime":"2018-01-03 11:14:41","startlng":116.184967,"orderNo":"606ccbc7c246c44bec9829a09a1eb0a2b2","userId":18813151324,"money":0,"barCode":"989123542879","runs":0,"endlng":116.184967}
     * returnmoney : 0
     * user : {"loginTime":"2018-01-02 15:44:33","userFrom":"","deposit":0.1,"pinNo":"7193","geTuiID":"9a01676edca08498ba9c0f8c9640a9f9","userLabel":"北戴河","userType":0,"id":897667898452082688,"depositSource":0,"userToken":"797b0637a7d3111669fff558ce682281","defaultDeposit":99,"userId":18813151324,"userLevel":1,"beiYong3":"","userName":"杨绍宁","pinTime":"2018-01-02 15:54:28","userBonus":491,"userPic":"http://www.onetriptech.com:8089/userpic/20171013154848449.jpg","tradeNo":"2017112421001004410534308521","userFromUrl":"","idcheck":0,"idealMoney":0,"userMoney":296.91,"sysTime":"2017-10-25 17:01:00","userLevelEndTime":"2017-11-26 17:56:30","orderNo":"","userCredit":100,"nicName":"fnjj","userStatus":0,"idno":"37082816616646446"}
     * lockid :
     * ridingprice :
     * lockmac :
     * locktype :
     */

    private String lockdata;
    private String shebeiid;
    private String openmoney;
    private String forcemoney;
    private String starttime;
    private RidingBean riding;
    private String returnmoney;
    private UserBean user;
    private String lockid;
    private String ridingprice;
    private String lockmac;
    private String locktype;

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

    public String getOpenmoney() {
        return openmoney;
    }

    public void setOpenmoney(String openmoney) {
        this.openmoney = openmoney;
    }

    public String getForcemoney() {
        return forcemoney;
    }

    public void setForcemoney(String forcemoney) {
        this.forcemoney = forcemoney;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
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

    public String getLockid() {
        return lockid;
    }

    public void setLockid(String lockid) {
        this.lockid = lockid;
    }

    public String getRidingprice() {
        return ridingprice;
    }

    public void setRidingprice(String ridingprice) {
        this.ridingprice = ridingprice;
    }

    public String getLockmac() {
        return lockmac;
    }

    public void setLockmac(String lockmac) {
        this.lockmac = lockmac;
    }

    public String getLocktype() {
        return locktype;
    }

    public void setLocktype(String locktype) {
        this.locktype = locktype;
    }

    public static class RidingBean {
        /**
         * pkuser : 897667898452082688
         * userBonus : 0
         * endlat : 39.903996
         * status : 1
         * kcal : 0
         * startlat : 39.903996
         * endTime : 2018-01-03 11:15:07
         * userMoney : 0
         * useMinute : 0
         * id : 948392141527318528
         * startTime : 2018-01-03 11:14:41
         * sysTime : 2018-01-03 11:14:41
         * startlng : 116.184967
         * orderNo : 606ccbc7c246c44bec9829a09a1eb0a2b2
         * userId : 18813151324
         * money : 0
         * barCode : 989123542879
         * runs : 0
         * endlng : 116.184967
         */

        private long pkuser;
        private int userBonus;
        private double endlat;
        private int status;
        private int kcal;
        private double startlat;
        private String endTime;
        private int userMoney;
        private int useMinute;
        private long id;
        private String startTime;
        private String sysTime;
        private double startlng;
        private String orderNo;
        private long userId;
        private int money;
        private String barCode;
        private int runs;
        private double endlng;

        public long getPkuser() {
            return pkuser;
        }

        public void setPkuser(long pkuser) {
            this.pkuser = pkuser;
        }

        public int getUserBonus() {
            return userBonus;
        }

        public void setUserBonus(int userBonus) {
            this.userBonus = userBonus;
        }

        public double getEndlat() {
            return endlat;
        }

        public void setEndlat(double endlat) {
            this.endlat = endlat;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getKcal() {
            return kcal;
        }

        public void setKcal(int kcal) {
            this.kcal = kcal;
        }

        public double getStartlat() {
            return startlat;
        }

        public void setStartlat(double startlat) {
            this.startlat = startlat;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getUserMoney() {
            return userMoney;
        }

        public void setUserMoney(int userMoney) {
            this.userMoney = userMoney;
        }

        public int getUseMinute() {
            return useMinute;
        }

        public void setUseMinute(int useMinute) {
            this.useMinute = useMinute;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getSysTime() {
            return sysTime;
        }

        public void setSysTime(String sysTime) {
            this.sysTime = sysTime;
        }

        public double getStartlng() {
            return startlng;
        }

        public void setStartlng(double startlng) {
            this.startlng = startlng;
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

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public String getBarCode() {
            return barCode;
        }

        public void setBarCode(String barCode) {
            this.barCode = barCode;
        }

        public int getRuns() {
            return runs;
        }

        public void setRuns(int runs) {
            this.runs = runs;
        }

        public double getEndlng() {
            return endlng;
        }

        public void setEndlng(double endlng) {
            this.endlng = endlng;
        }
    }

    public static class UserBean {
        /**
         * loginTime : 2018-01-02 15:44:33
         * userFrom :
         * deposit : 0.1
         * pinNo : 7193
         * geTuiID : 9a01676edca08498ba9c0f8c9640a9f9
         * userLabel : 北戴河
         * userType : 0
         * id : 897667898452082688
         * depositSource : 0
         * userToken : 797b0637a7d3111669fff558ce682281
         * defaultDeposit : 99
         * userId : 18813151324
         * userLevel : 1
         * beiYong3 :
         * userName : 杨绍宁
         * pinTime : 2018-01-02 15:54:28
         * userBonus : 491
         * userPic : http://www.onetriptech.com:8089/userpic/20171013154848449.jpg
         * tradeNo : 2017112421001004410534308521
         * userFromUrl :
         * idcheck : 0
         * idealMoney : 0
         * userMoney : 296.91
         * sysTime : 2017-10-25 17:01:00
         * userLevelEndTime : 2017-11-26 17:56:30
         * orderNo :
         * userCredit : 100
         * nicName : fnjj
         * userStatus : 0
         * idno : 37082816616646446
         */

        private String loginTime;
        private String userFrom;
        private double deposit;
        private String pinNo;
        private String geTuiID;
        private String userLabel;
        private int userType;
        private long id;
        private int depositSource;
        private String userToken;
        private int defaultDeposit;
        private long userId;
        private int userLevel;
        private String beiYong3;
        private String userName;
        private String pinTime;
        private int userBonus;
        private String userPic;
        private String tradeNo;
        private String userFromUrl;
        private int idcheck;
        private int idealMoney;
        private double userMoney;
        private String sysTime;
        private String userLevelEndTime;
        private String orderNo;
        private int userCredit;
        private String nicName;
        private int userStatus;
        private String idno;

        public String getLoginTime() {
            return loginTime;
        }

        public void setLoginTime(String loginTime) {
            this.loginTime = loginTime;
        }

        public String getUserFrom() {
            return userFrom;
        }

        public void setUserFrom(String userFrom) {
            this.userFrom = userFrom;
        }

        public double getDeposit() {
            return deposit;
        }

        public void setDeposit(double deposit) {
            this.deposit = deposit;
        }

        public String getPinNo() {
            return pinNo;
        }

        public void setPinNo(String pinNo) {
            this.pinNo = pinNo;
        }

        public String getGeTuiID() {
            return geTuiID;
        }

        public void setGeTuiID(String geTuiID) {
            this.geTuiID = geTuiID;
        }

        public String getUserLabel() {
            return userLabel;
        }

        public void setUserLabel(String userLabel) {
            this.userLabel = userLabel;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getDepositSource() {
            return depositSource;
        }

        public void setDepositSource(int depositSource) {
            this.depositSource = depositSource;
        }

        public String getUserToken() {
            return userToken;
        }

        public void setUserToken(String userToken) {
            this.userToken = userToken;
        }

        public int getDefaultDeposit() {
            return defaultDeposit;
        }

        public void setDefaultDeposit(int defaultDeposit) {
            this.defaultDeposit = defaultDeposit;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public int getUserLevel() {
            return userLevel;
        }

        public void setUserLevel(int userLevel) {
            this.userLevel = userLevel;
        }

        public String getBeiYong3() {
            return beiYong3;
        }

        public void setBeiYong3(String beiYong3) {
            this.beiYong3 = beiYong3;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPinTime() {
            return pinTime;
        }

        public void setPinTime(String pinTime) {
            this.pinTime = pinTime;
        }

        public int getUserBonus() {
            return userBonus;
        }

        public void setUserBonus(int userBonus) {
            this.userBonus = userBonus;
        }

        public String getUserPic() {
            return userPic;
        }

        public void setUserPic(String userPic) {
            this.userPic = userPic;
        }

        public String getTradeNo() {
            return tradeNo;
        }

        public void setTradeNo(String tradeNo) {
            this.tradeNo = tradeNo;
        }

        public String getUserFromUrl() {
            return userFromUrl;
        }

        public void setUserFromUrl(String userFromUrl) {
            this.userFromUrl = userFromUrl;
        }

        public int getIdcheck() {
            return idcheck;
        }

        public void setIdcheck(int idcheck) {
            this.idcheck = idcheck;
        }

        public int getIdealMoney() {
            return idealMoney;
        }

        public void setIdealMoney(int idealMoney) {
            this.idealMoney = idealMoney;
        }

        public double getUserMoney() {
            return userMoney;
        }

        public void setUserMoney(double userMoney) {
            this.userMoney = userMoney;
        }

        public String getSysTime() {
            return sysTime;
        }

        public void setSysTime(String sysTime) {
            this.sysTime = sysTime;
        }

        public String getUserLevelEndTime() {
            return userLevelEndTime;
        }

        public void setUserLevelEndTime(String userLevelEndTime) {
            this.userLevelEndTime = userLevelEndTime;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public int getUserCredit() {
            return userCredit;
        }

        public void setUserCredit(int userCredit) {
            this.userCredit = userCredit;
        }

        public String getNicName() {
            return nicName;
        }

        public void setNicName(String nicName) {
            this.nicName = nicName;
        }

        public int getUserStatus() {
            return userStatus;
        }

        public void setUserStatus(int userStatus) {
            this.userStatus = userStatus;
        }

        public String getIdno() {
            return idno;
        }

        public void setIdno(String idno) {
            this.idno = idno;
        }
    }
}
