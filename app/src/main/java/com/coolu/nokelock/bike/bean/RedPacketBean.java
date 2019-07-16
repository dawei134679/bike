package com.coolu.nokelock.bike.bean;

import java.util.List;

/**
 * Created by admin on 2017/8/4.
 */
public class RedPacketBean {
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSheBeiId() {
            return sheBeiId;
        }

        public void setSheBeiId(String sheBeiId) {
            this.sheBeiId = sheBeiId;
        }

        public String getRedTime() {
            return redTime;
        }

        public void setRedTime(String redTime) {
            this.redTime = redTime;
        }

        public String getSheBeiName() {
            return sheBeiName;
        }

        public void setSheBeiName(String sheBeiName) {
            this.sheBeiName = sheBeiName;
        }

        public String getSheBeiType() {
            return sheBeiType;
        }

        public void setSheBeiType(String sheBeiType) {
            this.sheBeiType = sheBeiType;
        }

        public String getBarCode() {
            return barCode;
        }

        public void setBarCode(String barCode) {
            this.barCode = barCode;
        }

        public String getSheBeiMac() {
            return sheBeiMac;
        }

        public void setSheBeiMac(String sheBeiMac) {
            this.sheBeiMac = sheBeiMac;
        }

        public String getSheBeiBianHao() {
            return sheBeiBianHao;
        }

        public void setSheBeiBianHao(String sheBeiBianHao) {
            this.sheBeiBianHao = sheBeiBianHao;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getPower() {
            return power;
        }

        public void setPower(String power) {
            this.power = power;
        }

        public String getSheBeiStatus() {
            return sheBeiStatus;
        }

        public void setSheBeiStatus(String sheBeiStatus) {
            this.sheBeiStatus = sheBeiStatus;
        }

        public String getRedBike() {
            return redBike;
        }

        public void setRedBike(String redBike) {
            this.redBike = redBike;
        }
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
