package com.coolu.nokelock.bike.bean;

/**
 * Created by admin on 2017/9/18.
 */

public class MacBeann {


    /**
     * errorCode : 200
     * message : 查询成功
     * result : {"sheBeiId":"063075346888","sheBeiMac":"50:F1:4A:49:E8:A4","barCode":"http://app.coolubike.com/app.html?id=335122849","sheBeiType":"ble2","orderNum":"c52e2e4832ec4e86b073bedb55513ed0","data":"T6mV/AT0TDNeOxSdl1+k+bWsaGeYIfnPWV7ZKuP9hYNR99ScKoIX0edmeoD4g/e4KfGKcnmX7m0EYQPocCTaCA=="}
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
         * sheBeiId : 063075346888
         * sheBeiMac : 50:F1:4A:49:E8:A4
         * barCode : http://app.coolubike.com/app.html?id=335122849
         * sheBeiType : ble2
         * orderNum : c52e2e4832ec4e86b073bedb55513ed0
         * data : T6mV/AT0TDNeOxSdl1+k+bWsaGeYIfnPWV7ZKuP9hYNR99ScKoIX0edmeoD4g/e4KfGKcnmX7m0EYQPocCTaCA==
         */

        private String sheBeiId;
        private String sheBeiMac;
        private String barCode;
        private String sheBeiType;
        private String orderNum;
        private String data;

        public String getSheBeiId() {
            return sheBeiId;
        }

        public void setSheBeiId(String sheBeiId) {
            this.sheBeiId = sheBeiId;
        }

        public String getSheBeiMac() {
            return sheBeiMac;
        }

        public void setSheBeiMac(String sheBeiMac) {
            this.sheBeiMac = sheBeiMac;
        }

        public String getBarCode() {
            return barCode;
        }

        public void setBarCode(String barCode) {
            this.barCode = barCode;
        }

        public String getSheBeiType() {
            return sheBeiType;
        }

        public void setSheBeiType(String sheBeiType) {
            this.sheBeiType = sheBeiType;
        }

        public String getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(String orderNum) {
            this.orderNum = orderNum;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
}
