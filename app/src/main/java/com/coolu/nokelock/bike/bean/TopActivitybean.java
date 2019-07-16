package com.coolu.nokelock.bike.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2017/7/26.
 *
 */
public class TopActivitybean implements Serializable{
    private static final long serialVersionUID = 1L;
    private String errorCode;
    private String message;
    private List<Result> result;

    public  class Result implements Serializable{
        private String id;
        private String activityName;
        private String activityType;
        private String activityStatue;
        private String activityContext;
        private String startTime;
        private String endTime;
        private String sysTime;
        private String activityUrl;
        private String activityTopUrl;
        private String activityOutUrl;
        private String activitySort;
        private String activityLabel;
        private String activityVersion;
        private String activityCount;
        private String isTop;
        private String jumpUrl;

        public String getJumpUrl() {
            return jumpUrl;
        }

        public void setJumpUrl(String jumpUrl) {
            this.jumpUrl = jumpUrl;
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

        public String getActivityType() {
            return activityType;
        }

        public void setActivityType(String activityType) {
            this.activityType = activityType;
        }

        public String getActivityStatue() {
            return activityStatue;
        }

        public void setActivityStatue(String activityStatue) {
            this.activityStatue = activityStatue;
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

        public String getActivitySort() {
            return activitySort;
        }

        public void setActivitySort(String activitySort) {
            this.activitySort = activitySort;
        }

        public String getActivityLabel() {
            return activityLabel;
        }

        public void setActivityLabel(String activityLabel) {
            this.activityLabel = activityLabel;
        }

        public String getActivityVersion() {
            return activityVersion;
        }

        public void setActivityVersion(String activityVersion) {
            this.activityVersion = activityVersion;
        }

        public String getActivityCount() {
            return activityCount;
        }

        public void setActivityCount(String activityCount) {
            this.activityCount = activityCount;
        }

        public String getIsTop() {
            return isTop;
        }

        public void setIsTop(String isTop) {
            this.isTop = isTop;
        }
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Result> getResults() {
        return result;
    }

    public void setResults(List<Result> result) {
        this.result = result;


    }

    @Override
    public String toString() {
        return "TopActivitybean{" +
                "errorCode='" + errorCode + '\'' +
                ", message='" + message + '\'' +
                ", results=" + result+
                '}';
    }
}
