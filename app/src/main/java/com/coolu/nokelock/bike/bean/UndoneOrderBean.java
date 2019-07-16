package com.coolu.nokelock.bike.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 作者: Sunshine
 * 时间: 2017/6/23.
 * 邮箱: 44493547@qq.com
 * 描述:
 */
@Entity
public class UndoneOrderBean {
    @Id
    private long id;
    private String barcode;
    private String ordernum;
    private String lat;
    private String lng;
    private String lockstate;
    private String power;
    private String result;
    private String locktype;
    private String mac;
    private String startTime;
    private String stopTime;
    private String shebeiid;
    public String getShebeiid() {
        return this.shebeiid;
    }
    public void setShebeiid(String shebeiid) {
        this.shebeiid = shebeiid;
    }
    public String getStopTime() {
        return this.stopTime;
    }
    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }
    public String getStartTime() {
        return this.startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getMac() {
        return this.mac;
    }
    public void setMac(String mac) {
        this.mac = mac;
    }
    public String getLocktype() {
        return this.locktype;
    }
    public void setLocktype(String locktype) {
        this.locktype = locktype;
    }
    public String getResult() {
        return this.result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public String getPower() {
        return this.power;
    }
    public void setPower(String power) {
        this.power = power;
    }
    public String getLockstate() {
        return this.lockstate;
    }
    public void setLockstate(String lockstate) {
        this.lockstate = lockstate;
    }
    public String getLng() {
        return this.lng;
    }
    public void setLng(String lng) {
        this.lng = lng;
    }
    public String getLat() {
        return this.lat;
    }
    public void setLat(String lat) {
        this.lat = lat;
    }
    public String getOrdernum() {
        return this.ordernum;
    }
    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }
    public String getBarcode() {
        return this.barcode;
    }
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    @Generated(hash = 1140191146)
    public UndoneOrderBean(long id, String barcode, String ordernum, String lat,
            String lng, String lockstate, String power, String result,
            String locktype, String mac, String startTime, String stopTime,
            String shebeiid) {
        this.id = id;
        this.barcode = barcode;
        this.ordernum = ordernum;
        this.lat = lat;
        this.lng = lng;
        this.lockstate = lockstate;
        this.power = power;
        this.result = result;
        this.locktype = locktype;
        this.mac = mac;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.shebeiid = shebeiid;
    }
    @Generated(hash = 1896991729)
    public UndoneOrderBean() {
    }
}
