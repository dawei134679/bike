package com.coolu.nokelock.bike.bean;

import java.util.List;

/**
 * 作者: Sunshine
 * 时间: 2017/4/24.
 * 邮箱: 44493547@qq.com
 * 描述:
 */

public class LineBean {

    /**
     * status : ok
     * result : [{"name":"路线1","lat":"22.616239,22.616237","lng":"113.977155,113.977152"},{"name":"路线2","lat":"22.616259,22.616237","lng":"113.977165,113.977252"}]
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
         * name : 路线1
         * lat : 22.616239,22.616237
         * lng : 113.977155,113.977152
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
