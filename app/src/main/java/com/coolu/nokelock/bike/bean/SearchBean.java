package com.coolu.nokelock.bike.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 搜索记录
 * Created by sunshine on 2017/3/2.
 */

@Entity
public class SearchBean {

    @Id (autoincrement = true)
    private long id;

    private String name;
    private String address;
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
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    @Generated(hash = 857071962)
    public SearchBean(long id, String name, String address, double latitude,
            double longitude) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    @Generated(hash = 562045751)
    public SearchBean() {
    }
}
