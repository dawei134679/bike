package com.coolu.nokelock.bike.bean;

import java.util.List;

/**
 * Created by admin on 2017/9/11.
 */

public class ActionListBean {

    /**
     * errorCode : 200
     * message : 查询成功
     * result : [{"id":1,"activityName":"碧螺塔购票大优惠","activityType":0,"activityStatus":0,"activityContext":"赠送20元卡券","startTime":"2017-07-23 09:26:19","endTime":"2017-12-31 09:26:26","sysTime":"2017-07-25 09:26:37","activityUrl":"http://www.onetriptech.com/images/apptest/biluota1.jpg","activityTopUrl":"http://www.onetriptech.com/images/apptest/top.jpg","activityOutUrl":"http://www.onetriptech.com/images/apptest/popup.jpg","jumpUrl":"http://www.onetriptech.com/images/apptest/biluota2.jpg","activitySort":1,"activityLabel":"北戴河","activityVersion":6,"activityCount":1,"isTop":1},{"id":2,"activityName":"民宿预定送卡券","activityType":0,"activityStatus":0,"activityContext":"赠送30元卡券","startTime":"2017-07-23 09:26:19","endTime":"2017-12-30 09:26:26","sysTime":"2017-07-25 09:26:37","activityUrl":"http://www.onetriptech.com/images/apptest/minsu1.jpg","activityTopUrl":"http://www.onetriptech.com/images/apptest/top.jpg","activityOutUrl":"http://www.onetriptech.com/images/apptest/popup.jpg","jumpUrl":"http://www.onetriptech.com/images/apptest/minsu2.jpg","activitySort":2,"activityLabel":"北戴河","activityVersion":6,"activityCount":1,"isTop":2},{"id":3,"activityName":"老虎石购票送卡券","activityType":0,"activityStatus":0,"activityContext":"赠送10元卡券","startTime":"2017-07-23 09:26:19","endTime":"2017-12-31 09:26:26","sysTime":"2017-07-25 09:26:37","activityUrl":"http://www.onetriptech.com/images/apptest/laohushi1.jpg","activityTopUrl":"http://www.onetriptech.com/images/apptest/top.jpg","activityOutUrl":"http://www.onetriptech.com/images/apptest/popup.jpg","jumpUrl":"http://www.onetriptech.com/images/apptest/laohushi2.jpg","activitySort":3,"activityLabel":"北戴河","activityVersion":6,"activityCount":1,"isTop":0}]
     */

    private int errorCode;
    private String message;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 1
         * activityName : 碧螺塔购票大优惠
         * activityType : 0
         * activityStatus : 0
         * activityContext : 赠送20元卡券
         * startTime : 2017-07-23 09:26:19
         * endTime : 2017-12-31 09:26:26
         * sysTime : 2017-07-25 09:26:37
         * activityUrl : http://www.onetriptech.com/images/apptest/biluota1.jpg
         * activityTopUrl : http://www.onetriptech.com/images/apptest/top.jpg
         * activityOutUrl : http://www.onetriptech.com/images/apptest/popup.jpg
         * jumpUrl : http://www.onetriptech.com/images/apptest/biluota2.jpg
         * activitySort : 1
         * activityLabel : 北戴河
         * activityVersion : 6
         * activityCount : 1
         * isTop : 1
         */

        private String id;
        private String activityName;
        private int activityType;
        private int activityStatus;
        private String activityContext;
        private String startTime;
        private String endTime;
        private String sysTime;
        private String activityUrl;
        private String activityTopUrl;
        private String activityOutUrl;
        private String jumpUrl;
        private int activitySort;
        private String activityLabel;
        private int activityVersion;
        private int activityCount;
        private int isTop;
        private String openUrl;
        private String weixinUrl;

        public String getWeixinUrl() {
            return weixinUrl;
        }

        public void setWeixinUrl(String weixinUrl) {
            this.weixinUrl = weixinUrl;
        }

        public String getOpenUrl() {
            return openUrl;
        }

        public void setOpenUrl(String openUrl) {
            this.openUrl = openUrl;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }

        public int getActivityType() {
            return activityType;
        }

        public void setActivityType(int activityType) {
            this.activityType = activityType;
        }

        public int getActivityStatus() {
            return activityStatus;
        }

        public void setActivityStatus(int activityStatus) {
            this.activityStatus = activityStatus;
        }

        public String getActivityContext() {
            return activityContext;
        }

        public void setActivityContext(String activityContext) {
            this.activityContext = activityContext;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getSysTime() {
            return sysTime;
        }

        public void setSysTime(String sysTime) {
            this.sysTime = sysTime;
        }

        public String getActivityUrl() {
            return activityUrl;
        }

        public void setActivityUrl(String activityUrl) {
            this.activityUrl = activityUrl;
        }

        public String getActivityTopUrl() {
            return activityTopUrl;
        }

        public void setActivityTopUrl(String activityTopUrl) {
            this.activityTopUrl = activityTopUrl;
        }

        public String getActivityOutUrl() {
            return activityOutUrl;
        }

        public void setActivityOutUrl(String activityOutUrl) {
            this.activityOutUrl = activityOutUrl;
        }

        public String getJumpUrl() {
            return jumpUrl;
        }

        public void setJumpUrl(String jumpUrl) {
            this.jumpUrl = jumpUrl;
        }

        public int getActivitySort() {
            return activitySort;
        }

        public void setActivitySort(int activitySort) {
            this.activitySort = activitySort;
        }

        public String getActivityLabel() {
            return activityLabel;
        }

        public void setActivityLabel(String activityLabel) {
            this.activityLabel = activityLabel;
        }

        public int getActivityVersion() {
            return activityVersion;
        }

        public void setActivityVersion(int activityVersion) {
            this.activityVersion = activityVersion;
        }

        public int getActivityCount() {
            return activityCount;
        }

        public void setActivityCount(int activityCount) {
            this.activityCount = activityCount;
        }

        public int getIsTop() {
            return isTop;
        }

        public void setIsTop(int isTop) {
            this.isTop = isTop;
        }
    }
}
