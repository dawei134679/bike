package com.coolu.nokelock.bike.bean;

import java.util.List;

/**
 * Created by admin on 2017/8/30.
 */

public class MoneyDetailBean {

    private String errorCode;
    private String message;
    public List<Result> result;

    public  class Result {
        private String id;
        private String sheBeiId;
        private String sheBeiBianHao;
        private String sheBeiMac;
        private String barCode;
        private String sheBeiType;
        private String sheBeiName;
        private String lng;
        private String lat;
        private String power;
        private String sheBeiStatus;
        private String redBike;
        private String redTime;
    }
}
