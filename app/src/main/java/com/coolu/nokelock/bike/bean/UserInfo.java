package com.coolu.nokelock.bike.bean;

/**
 * 个人信息
 * Created by sunshine on 2017/3/6.
 */

public class UserInfo {

    /**
     * status : ok
     * result : {"nickname":"李召","username":"李召","idcard":"666688889999","deposit":"199.00","depositDefault":"0.01","balance":"72.12","picurl":"http://service.rocolock.com:16888/Picture/13590208859infoPic20170322102754.png","userstate":"yes","state":"none","lockid":"","barcode":"","locktype":"","lockmac":"","data":"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA==","ordernum":"","starttime":"","endtime":"","money":"0.00"}
     */

    private String status;
    private ResultEntity result;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public ResultEntity getResult() {
        return result;
    }

    public static class ResultEntity {
        /**
         * nickname : 李召
         * username : 李召
         * idcard : 666688889999
         * deposit : 199.00
         * depositDefault : 0.01
         * balance : 72.12
         * picurl : http://service.rocolock.com:16888/Picture/13590208859infoPic20170322102754.png
         * userstate : yes
         * state : none
         * lockid :
         * barcode :
         * locktype :
         * lockmac :
         * data : AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA==
         * ordernum :
         * starttime :
         * endtime :
         * money : 0.00
         */

        private String nickname;
        private String username;
        private String idcard;
        private String deposit;
        private String depositDefault;
        private String balance;
        private String picurl;
        private String userstate;
        private String state;
        private String lockid;
        private String barcode;
        private String locktype;
        private String lockmac;
        private String data;
        private String ordernum;
        private String starttime;
        private String endtime;
        private String money;
        private String shebeiid;
        private String openMoney;
        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public void setDeposit(String deposit) {
            this.deposit = deposit;
        }

        public void setDepositDefault(String depositDefault) {
            this.depositDefault = depositDefault;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public void setUserstate(String userstate) {
            this.userstate = userstate;
        }

        public void setState(String state) {
            this.state = state;
        }

        public void setLockid(String lockid) {
            this.lockid = lockid;
        }

        public void setBarcode(String barcode) {
            this.barcode = barcode;
        }

        public void setLocktype(String locktype) {
            this.locktype = locktype;
        }

        public void setLockmac(String lockmac) {
            this.lockmac = lockmac;
        }

        public void setData(String data) {
            this.data = data;
        }

        public void setOrdernum(String ordernum) {
            this.ordernum = ordernum;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getNickname() {
            return nickname;
        }

        public String getUsername() {
            return username;
        }

        public String getIdcard() {
            return idcard;
        }

        public String getDeposit() {
            return deposit;
        }

        public String getDepositDefault() {
            return depositDefault;
        }

        public String getBalance() {
            return balance;
        }

        public String getPicurl() {
            return picurl;
        }

        public String getUserstate() {
            return userstate;
        }

        public String getState() {
            return state;
        }

        public String getLockid() {
            return lockid;
        }

        public String getBarcode() {
            return barcode;
        }

        public String getLocktype() {
            return locktype;
        }

        public String getLockmac() {
            return lockmac;
        }

        public String getData() {
            return data;
        }

        public String getOrdernum() {
            return ordernum;
        }

        public String getStarttime() {
            return starttime;
        }

        public String getEndtime() {
            return endtime;
        }

        public String getMoney() {
            return money;
        }

        public String getShebeiid() {
            return shebeiid;
        }

        public void setShebeiid(String shebeiid) {
            this.shebeiid = shebeiid;
        }

        public String getOpenMoney() {
            return openMoney;
        }

        public void setOpenMoney(String openMoney) {
            this.openMoney = openMoney;
        }
    }
}
