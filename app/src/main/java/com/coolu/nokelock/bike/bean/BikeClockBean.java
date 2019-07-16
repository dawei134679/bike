package com.coolu.nokelock.bike.bean;

import java.util.List;

/**
 * Created by admin on 2017/10/17.
 */

public class BikeClockBean {


    /**
     * errorCode : 200
     * message : 成功
     * result : {"lockdata":"","shebeiid":"","starttime":"","forcemoney":"20","openmoney":"-30","riding":{"id":968390110146068480,"pkuser":898451825689231360,"orderNo":"60b98f84d56c7f4de4a8abaad44fdb2e69","userId":18888888888,"barCode":"http://app.coolubike.com/app.html?id=335122849","startTime":"2018-02-27 15:39:29","endTime":"2018-02-27 15:40:28","useMinute":0,"money":0,"status":1,"sysTime":"2018-02-27 15:39:29","userBonus":0,"userMoney":0,"kcal":0,"runs":0,"startlng":116.199888,"startlat":39.913273,"endlng":116.199888,"endlat":39.913273},"returnmoney":"0","youhui":[{"promocard_id":10673371,"title":"优惠券","value":30,"condition":"无限制","start_at":"2018-01-15 10:04:15","end_at":"2018-05-15 10:03:46","is_used":0,"is_invalid":0,"is_expired":0,"background_color":"#ff5050","detail_url":"https://h5.youzan.com/v2/showcase/coupon/detail?alias=2269626&id=10673371","verify_code":"138381859091"}],"user":{"id":898451825689231360,"userId":18888888888,"nicName":"132","userName":"来来来","idno":"230203199206220011","idcheck":0,"userPic":"http://www.onetriptech.com:8089/userpic/20171023114759872.jpg","pinNo":"6666","pinTime":"2019-02-01 10:38:15","userToken":"2ba09471dbb45dff0c3d37c57f03d999","loginTime":"2018-02-27 15:33:31","deposit":99,"depositSource":0,"defaultDeposit":99,"orderNo":"","tradeNo":"","userMoney":105,"userBonus":0,"userType":0,"userStatus":0,"sysTime":"2017-10-18 10:10:56","userCredit":100,"idealMoney":0,"userLevel":0,"userFrom":"","userFromUrl":"","userLabel":"北戴河"},"ridingprice":"","lockid":"","locktype":"","lockmac":""}
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
         * lockdata :
         * shebeiid :
         * starttime :
         * forcemoney : 20
         * openmoney : -30
         * riding : {"id":968390110146068480,"pkuser":898451825689231360,"orderNo":"60b98f84d56c7f4de4a8abaad44fdb2e69","userId":18888888888,"barCode":"http://app.coolubike.com/app.html?id=335122849","startTime":"2018-02-27 15:39:29","endTime":"2018-02-27 15:40:28","useMinute":0,"money":0,"status":1,"sysTime":"2018-02-27 15:39:29","userBonus":0,"userMoney":0,"kcal":0,"runs":0,"startlng":116.199888,"startlat":39.913273,"endlng":116.199888,"endlat":39.913273}
         * returnmoney : 0
         * youhui : [{"promocard_id":10673371,"title":"优惠券","value":30,"condition":"无限制","start_at":"2018-01-15 10:04:15","end_at":"2018-05-15 10:03:46","is_used":0,"is_invalid":0,"is_expired":0,"background_color":"#ff5050","detail_url":"https://h5.youzan.com/v2/showcase/coupon/detail?alias=2269626&id=10673371","verify_code":"138381859091"}]
         * user : {"id":898451825689231360,"userId":18888888888,"nicName":"132","userName":"来来来","idno":"230203199206220011","idcheck":0,"userPic":"http://www.onetriptech.com:8089/userpic/20171023114759872.jpg","pinNo":"6666","pinTime":"2019-02-01 10:38:15","userToken":"2ba09471dbb45dff0c3d37c57f03d999","loginTime":"2018-02-27 15:33:31","deposit":99,"depositSource":0,"defaultDeposit":99,"orderNo":"","tradeNo":"","userMoney":105,"userBonus":0,"userType":0,"userStatus":0,"sysTime":"2017-10-18 10:10:56","userCredit":100,"idealMoney":0,"userLevel":0,"userFrom":"","userFromUrl":"","userLabel":"北戴河"}
         * ridingprice :
         * lockid :
         * locktype :
         * lockmac :
         */

        private String lockdata;
        private String shebeiid;
        private String starttime;
        private String forcemoney;
        private String openmoney;
        private RidingBean riding;
        private String returnmoney;
        private UserBean user;
        private String ridingprice;
        private String lockid;
        private String locktype;
        private String lockmac;
        private List<YouhuiBean> youhui;

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

        public String getForcemoney() {
            return forcemoney;
        }

        public void setForcemoney(String forcemoney) {
            this.forcemoney = forcemoney;
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

        public List<YouhuiBean> getYouhui() {
            return youhui;
        }

        public void setYouhui(List<YouhuiBean> youhui) {
            this.youhui = youhui;
        }

        public static class RidingBean {
            /**
             * id : 968390110146068480
             * pkuser : 898451825689231360
             * orderNo : 60b98f84d56c7f4de4a8abaad44fdb2e69
             * userId : 18888888888
             * barCode : http://app.coolubike.com/app.html?id=335122849
             * startTime : 2018-02-27 15:39:29
             * endTime : 2018-02-27 15:40:28
             * useMinute : 0
             * money : 0.0
             * status : 1
             * sysTime : 2018-02-27 15:39:29
             * userBonus : 0.0
             * userMoney : 0.0
             * kcal : 0
             * runs : 0
             * startlng : 116.199888
             * startlat : 39.913273
             * endlng : 116.199888
             * endlat : 39.913273
             */

            private long id;
            private long pkuser;
            private String orderNo;
            private long userId;
            private String barCode;
            private String startTime;
            private String endTime;
            private int useMinute;
            private double money;
            private String beiYong1;
            private String beiYong2;
            private int status;
            private String sysTime;
            private double userBonus;
            private double userMoney;
            private int kcal;
            private int runs;
            private double startlng;
            private double startlat;
            private double endlng;
            private double endlat;

            public String getBeiYong1() {
                return beiYong1;
            }

            public void setBeiYong1(String beiYong1) {
                this.beiYong1 = beiYong1;
            }

            public String getBeiYong2() {
                return beiYong2;
            }

            public void setBeiYong2(String beiYong2) {
                this.beiYong2 = beiYong2;
            }

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

            public double getUserBonus() {
                return userBonus;
            }

            public void setUserBonus(double userBonus) {
                this.userBonus = userBonus;
            }

            public double getUserMoney() {
                return userMoney;
            }

            public void setUserMoney(double userMoney) {
                this.userMoney = userMoney;
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

            public double getEndlng() {
                return endlng;
            }

            public void setEndlng(double endlng) {
                this.endlng = endlng;
            }

            public double getEndlat() {
                return endlat;
            }

            public void setEndlat(double endlat) {
                this.endlat = endlat;
            }
        }

        public static class UserBean {
            /**
             * id : 898451825689231360
             * userId : 18888888888
             * nicName : 132
             * userName : 来来来
             * idno : 230203199206220011
             * idcheck : 0
             * userPic : http://www.onetriptech.com:8089/userpic/20171023114759872.jpg
             * pinNo : 6666
             * pinTime : 2019-02-01 10:38:15
             * userToken : 2ba09471dbb45dff0c3d37c57f03d999
             * loginTime : 2018-02-27 15:33:31
             * deposit : 99.0
             * depositSource : 0
             * defaultDeposit : 99.0
             * orderNo :
             * tradeNo :
             * userMoney : 105.0
             * userBonus : 0
             * userType : 0
             * userStatus : 0
             * sysTime : 2017-10-18 10:10:56
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
            private String userName;
            private String idno;
            private int idcheck;
            private String userPic;
            private String pinNo;
            private String pinTime;
            private String userToken;
            private String loginTime;
            private double deposit;
            private int depositSource;
            private double defaultDeposit;
            private String orderNo;
            private String tradeNo;
            private double userMoney;
            private int userBonus;
            private int userType;
            private int userStatus;
            private String sysTime;
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

            public int getIdcheck() {
                return idcheck;
            }

            public void setIdcheck(int idcheck) {
                this.idcheck = idcheck;
            }

            public String getUserPic() {
                return userPic;
            }

            public void setUserPic(String userPic) {
                this.userPic = userPic;
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

            public int getDepositSource() {
                return depositSource;
            }

            public void setDepositSource(int depositSource) {
                this.depositSource = depositSource;
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

            public String getTradeNo() {
                return tradeNo;
            }

            public void setTradeNo(String tradeNo) {
                this.tradeNo = tradeNo;
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

        public static class YouhuiBean {
            /**
             * promocard_id : 10673371
             * title : 优惠券
             * value : 30.0
             * condition : 无限制
             * start_at : 2018-01-15 10:04:15
             * end_at : 2018-05-15 10:03:46
             * is_used : 0
             * is_invalid : 0
             * is_expired : 0
             * background_color : #ff5050
             * detail_url : https://h5.youzan.com/v2/showcase/coupon/detail?alias=2269626&id=10673371
             * verify_code : 138381859091
             */

            private int promocard_id;
            private String title;
            private String value;
            private String condition;
            private String start_at;
            private String end_at;
            private int is_used;
            private int is_invalid;
            private int is_expired;
            private String background_color;
            private String detail_url;
            private String verify_code;

            public int getPromocard_id() {
                return promocard_id;
            }

            public void setPromocard_id(int promocard_id) {
                this.promocard_id = promocard_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getCondition() {
                return condition;
            }

            public void setCondition(String condition) {
                this.condition = condition;
            }

            public String getStart_at() {
                return start_at;
            }

            public void setStart_at(String start_at) {
                this.start_at = start_at;
            }

            public String getEnd_at() {
                return end_at;
            }

            public void setEnd_at(String end_at) {
                this.end_at = end_at;
            }

            public int getIs_used() {
                return is_used;
            }

            public void setIs_used(int is_used) {
                this.is_used = is_used;
            }

            public int getIs_invalid() {
                return is_invalid;
            }

            public void setIs_invalid(int is_invalid) {
                this.is_invalid = is_invalid;
            }

            public int getIs_expired() {
                return is_expired;
            }

            public void setIs_expired(int is_expired) {
                this.is_expired = is_expired;
            }

            public String getBackground_color() {
                return background_color;
            }

            public void setBackground_color(String background_color) {
                this.background_color = background_color;
            }

            public String getDetail_url() {
                return detail_url;
            }

            public void setDetail_url(String detail_url) {
                this.detail_url = detail_url;
            }

            public String getVerify_code() {
                return verify_code;
            }

            public void setVerify_code(String verify_code) {
                this.verify_code = verify_code;
            }
        }
    }
}
