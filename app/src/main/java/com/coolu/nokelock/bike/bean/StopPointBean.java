package com.coolu.nokelock.bike.bean;

import java.util.List;

/**
 * 作者: Sunshine
 * 时间: 2017/4/24.
 * 邮箱: 44493547@qq.com
 * 描述:
 */

public class StopPointBean {


    /**
     * status : ok
     * result : [{"name":"大勘村","lat":"22.616392","lng":"113.977055"},{"name":"大勘村2","lat":"22.616239","lng":"113.977155"}]
     */

    private String status;
    private List<ResultEntity> result;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResult(List<ResultEntity> result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public List<ResultEntity> getResult() {
        return result;
    }

    public static class ResultEntity {
        /**
         * name : 大勘村
         * lat : 22.616392
         * lng : 113.977055
         */

        private String name;
        private String lat;
        private String lng;

        public void setName(String name) {
            this.name = name;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getName() {
            return name;
        }

        public String getLat() {
            return lat;
        }

        public String getLng() {
            return lng;
        }
    }
}
