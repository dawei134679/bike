package com.coolu.nokelock.bike.bean;

import java.util.List;

/**
 * Created by admin on 2018/2/27.
 * 优惠券
 */

public class CardTipBean {


    /**
     * errorCode : 200
     * message : 查询成功
     * result : {"shebeiid":"","starttime":"","openmoney":"-30","youhui":[{"promocard_id":10673341,"title":"优惠券","value":30,"condition":"无限制","start_at":"2018-01-15 10:04:15","end_at":"2018-05-15 10:03:46","is_used":0,"is_invalid":0,"is_expired":0,"background_color":"#ff5050","detail_url":"https://h5.youzan.com/v2/showcase/coupon/detail?alias=2269626&id=10673341","verify_code":"318467223151"}],"barcode":"","ridingprice":"","locktype":"","cardprice":"10","lockdata":"","forcemoney":"20","returnmoney":"0","user":{"id":968365101914652672,"userId":18822222222,"nicName":"18822222222","idcheck":0,"pinNo":"2235","pinTime":"2018-02-27 14:10:06","userToken":"73e0422d3d2aff9f5ab7d096c9154bde","loginTime":"2018-02-27 14:00:06","deposit":0,"defaultDeposit":99,"userMoney":0,"userBonus":0,"userType":0,"userStatus":0,"sysTime":"2018-02-27 14:00:06","userCredit":100,"idealMoney":0,"userLevel":0,"userFrom":"old","userFromUrl":"","userLabel":"北戴河"},"lockid":"","lockmac":""}
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
         * shebeiid :
         * starttime :
         * openmoney : -30
         * youhui : [{"promocard_id":10673341,"title":"优惠券","value":30,"condition":"无限制","start_at":"2018-01-15 10:04:15","end_at":"2018-05-15 10:03:46","is_used":0,"is_invalid":0,"is_expired":0,"background_color":"#ff5050","detail_url":"https://h5.youzan.com/v2/showcase/coupon/detail?alias=2269626&id=10673341","verify_code":"318467223151"}]
         * barcode :
         * ridingprice :
         * locktype :
         * cardprice : 10
         * lockdata :
         * forcemoney : 20
         * returnmoney : 0
         * user : {"id":968365101914652672,"userId":18822222222,"nicName":"18822222222","idcheck":0,"pinNo":"2235","pinTime":"2018-02-27 14:10:06","userToken":"73e0422d3d2aff9f5ab7d096c9154bde","loginTime":"2018-02-27 14:00:06","deposit":0,"defaultDeposit":99,"userMoney":0,"userBonus":0,"userType":0,"userStatus":0,"sysTime":"2018-02-27 14:00:06","userCredit":100,"idealMoney":0,"userLevel":0,"userFrom":"old","userFromUrl":"","userLabel":"北戴河"}
         * lockid :
         * lockmac :
         */

        private String shebeiid;
        private String starttime;
        private String openmoney;
        private float newopenmoney;
        private String barcode;
        private String ridingprice;
        private String locktype;
        private String cardprice;
        private String lockdata;
        private String forcemoney;
        private String returnmoney;
        private UserBean user;
        private String lockid;
        private String lockmac;
        private List<YouhuiBean> youhui=null;

        public float getNewopenmoney() {
            return newopenmoney;
        }

        public void setNewopenmoney(float newopenmoney) {
            this.newopenmoney = newopenmoney;
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

        public String getBarcode() {
            return barcode;
        }

        public void setBarcode(String barcode) {
            this.barcode = barcode;
        }

        public String getRidingprice() {
            return ridingprice;
        }

        public void setRidingprice(String ridingprice) {
            this.ridingprice = ridingprice;
        }

        public String getLocktype() {
            return locktype;
        }

        public void setLocktype(String locktype) {
            this.locktype = locktype;
        }

        public String getCardprice() {
            return cardprice;
        }

        public void setCardprice(String cardprice) {
            this.cardprice = cardprice;
        }

        public String getLockdata() {
            return lockdata;
        }

        public void setLockdata(String lockdata) {
            this.lockdata = lockdata;
        }

        public String getForcemoney() {
            return forcemoney;
        }

        public void setForcemoney(String forcemoney) {
            this.forcemoney = forcemoney;
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

        public static class UserBean {
            /**
             * id : 968365101914652672
             * userId : 18822222222
             * nicName : 18822222222
             * idcheck : 0
             * pinNo : 2235
             * pinTime : 2018-02-27 14:10:06
             * userToken : 73e0422d3d2aff9f5ab7d096c9154bde
             * loginTime : 2018-02-27 14:00:06
             * deposit : 0.0
             * defaultDeposit : 99.0
             * userMoney : 0.0
             * userBonus : 0.0
             * userType : 0
             * userStatus : 0
             * sysTime : 2018-02-27 14:00:06
             * userCredit : 100
             * idealMoney : 0.0
             * userLevel : 0
             * userFrom : old
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
            private double userMoney;
            private double userBonus;
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

            public double getUserMoney() {
                return userMoney;
            }

            public void setUserMoney(double userMoney) {
                this.userMoney = userMoney;
            }

            public double getUserBonus() {
                return userBonus;
            }

            public void setUserBonus(double userBonus) {
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
             * promocard_id : 10673341
             * title : 优惠券
             * value : 30.0
             * condition : 无限制
             * start_at : 2018-01-15 10:04:15
             * end_at : 2018-05-15 10:03:46
             * is_used : 0
             * is_invalid : 0
             * is_expired : 0
             * background_color : #ff5050
             * detail_url : https://h5.youzan.com/v2/showcase/coupon/detail?alias=2269626&id=10673341
             * verify_code : 318467223151
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
