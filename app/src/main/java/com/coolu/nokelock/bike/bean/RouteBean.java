package com.coolu.nokelock.bike.bean;

/**
 * Created by admin on 2018/1/8.
 */

public class RouteBean
{
    /**
     * errorCode : 200
     * message : 北戴河市
     * result : {"RouteLIst":"http://info.coolubike.com/appservice.asmx/getRouteList","RouteDetails":"http://info.coolubike.com/Route.htm"}
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
         * RouteLIst : http://info.coolubike.com/appservice.asmx/getRouteList
         * RouteDetails : http://info.coolubike.com/Route.htm
         */

        private String RouteList;
        private String RouteDetails;

        public String getRouteLIst() {
            return RouteList;
        }

        public void setRouteLIst(String RouteList) {
            this.RouteList =RouteList;
        }

        public String getRouteDetails() {
            return RouteDetails;
        }

        public void setRouteDetails(String RouteDetails) {
            this.RouteDetails = RouteDetails;
        }
    }
}
