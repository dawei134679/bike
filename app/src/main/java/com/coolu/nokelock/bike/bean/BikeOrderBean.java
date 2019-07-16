package com.coolu.nokelock.bike.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 订单
 * Created by sunshine on 2017/3/11.
 */
@Entity
public class BikeOrderBean {
    @Id(autoincrement = true)
    private Long id;
    private String orderNumber;
    private String mac;
    private String lockType;
    private String startTime;
    private String endTime;
    private String barcode;
    private String key;
    private String password;
    private String shebeiid;
    public String getShebeiid() {
        return this.shebeiid;
    }
    public void setShebeiid(String shebeiid) {
        this.shebeiid = shebeiid;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getKey() {
        return this.key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getBarcode() {
        return this.barcode;
    }
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    public String getEndTime() {
        return this.endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public String getStartTime() {
        return this.startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getLockType() {
        return this.lockType;
    }
    public void setLockType(String lockType) {
        this.lockType = lockType;
    }
    public String getMac() {
        return this.mac;
    }
    public void setMac(String mac) {
        this.mac = mac;
    }
    public String getOrderNumber() {
        return this.orderNumber;
    }
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 353108284)
    public BikeOrderBean(Long id, String orderNumber, String mac, String lockType,
            String startTime, String endTime, String barcode, String key,
            String password, String shebeiid) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.mac = mac;
        this.lockType = lockType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.barcode = barcode;
        this.key = key;
        this.password = password;
        this.shebeiid = shebeiid;
    }
    @Generated(hash = 930074905)
    public BikeOrderBean() {
    }
   
}
