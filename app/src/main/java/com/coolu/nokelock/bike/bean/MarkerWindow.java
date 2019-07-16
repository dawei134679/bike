package com.coolu.nokelock.bike.bean;

/**
 * Created by admin on 2017/8/3.
 * 在地图上点击“停车点”或“红包”时用到
 */
public class MarkerWindow {
    private String number; //车辆编码
    private String address;  //地址
    private String  site;  //站点
    private String time;   //时间
    private String distance; //距离

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
