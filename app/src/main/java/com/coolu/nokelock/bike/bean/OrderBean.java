package com.coolu.nokelock.bike.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 预约订单
 * Created by sunshine on 2017/3/9.
 */
@Entity
public class OrderBean {
    @Id(autoincrement = true)
    private Long id;

    private String orderId;
    private double latitude;
    private double longitude;
    public double getLongitude() {
        return this.longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public double getLatitude() {
        return this.latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public String getOrderId() {
        return this.orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1806782682)
    public OrderBean(Long id, String orderId, double latitude, double longitude) {
        this.id = id;
        this.orderId = orderId;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    @Generated(hash = 1725534308)
    public OrderBean() {
    }
}
