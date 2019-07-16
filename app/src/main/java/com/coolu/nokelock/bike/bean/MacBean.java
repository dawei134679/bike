package com.coolu.nokelock.bike.bean;

/**
 * 获取二维码扫描信息
 * Created by sunshine on 2017/3/10.
 */

public class MacBean {

    /**
     * status : ok
     * result : {"locktype":"ble","lockmac":"C8:FD:19:91:4D:A2","data":"IFcvUjZLP0cwUEFYEWMtKzAwMDAwMA==","ordernum":"17031114260069787"}
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
         * locktype : ble
         * lockmac : C8:FD:19:91:4D:A2
         * data : IFcvUjZLP0cwUEFYEWMtKzAwMDAwMA==
         * ordernum : 17031114260069787
         */

        private String locktype;
        private String lockmac;
        private String data;
        private String ordernum;
        private String barcode;
        private String shebeiid;

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

        public String getBarcode() {
            return barcode;
        }

        public void setBarcode(String barcode) {
            this.barcode = barcode;
        }

        public String getShebeiid() {
            return shebeiid;
        }

        public void setShebeiid(String shebeiid) {
            this.shebeiid = shebeiid;
        }
    }
}
