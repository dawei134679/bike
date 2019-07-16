package com.coolu.nokelock.bike.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 个人信息表
 * Created by sunshine on 2017/3/15.
 */

@Entity
public class UseBean {
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
    public String getMoney() {
        return this.money;
    }
    public void setMoney(String money) {
        this.money = money;
    }
    public String getEndtime() {
        return this.endtime;
    }
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
    public String getStarttime() {
        return this.starttime;
    }
    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }
    public String getOrdernum() {
        return this.ordernum;
    }
    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }
    public String getData() {
        return this.data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getLockmac() {
        return this.lockmac;
    }
    public void setLockmac(String lockmac) {
        this.lockmac = lockmac;
    }
    public String getLocktype() {
        return this.locktype;
    }
    public void setLocktype(String locktype) {
        this.locktype = locktype;
    }
    public String getBarcode() {
        return this.barcode;
    }
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    public String getLockid() {
        return this.lockid;
    }
    public void setLockid(String lockid) {
        this.lockid = lockid;
    }
    public String getState() {
        return this.state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getUserstate() {
        return this.userstate;
    }
    public void setUserstate(String userstate) {
        this.userstate = userstate;
    }
    public String getPicurl() {
        return this.picurl;
    }
    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }
    public String getBalance() {
        return this.balance;
    }
    public void setBalance(String balance) {
        this.balance = balance;
    }
    public String getDepositDefault() {
        return this.depositDefault;
    }
    public void setDepositDefault(String depositDefault) {
        this.depositDefault = depositDefault;
    }
    public String getDeposit() {
        return this.deposit;
    }
    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }
    public String getIdcard() {
        return this.idcard;
    }
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getNickname() {
        return this.nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    @Generated(hash = 2034815291)
    public UseBean(String nickname, String username, String idcard, String deposit,
            String depositDefault, String balance, String picurl, String userstate,
            String state, String lockid, String barcode, String locktype,
            String lockmac, String data, String ordernum, String starttime,
            String endtime, String money) {
        this.nickname = nickname;
        this.username = username;
        this.idcard = idcard;
        this.deposit = deposit;
        this.depositDefault = depositDefault;
        this.balance = balance;
        this.picurl = picurl;
        this.userstate = userstate;
        this.state = state;
        this.lockid = lockid;
        this.barcode = barcode;
        this.locktype = locktype;
        this.lockmac = lockmac;
        this.data = data;
        this.ordernum = ordernum;
        this.starttime = starttime;
        this.endtime = endtime;
        this.money = money;
    }
    @Generated(hash = 895360490)
    public UseBean() {
    }

}
