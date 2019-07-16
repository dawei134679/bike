package com.coolu.nokelock.bike.bean;

import java.util.List;

/**
 * Created by admin on 2017/8/31.
 * 骑行路线
 */

public class QxrouteBean {

    /**
     * code : 1101
     * msg : 操作成功！
     * datas : {"nextPager":-1,"routeList":[{"Id":4,"Title":"海滨第一弯","TitleImg":"fd067129-7517-4549-9186-e66c1c1aa11e.jpg","Content":null,"Summary":"","StationId":0,"Weight":0,"CreateDate":"2017-6-14 10:18","CoordinateSet":null,"PoiSet":null},{"Id":11,"Title":"岛上日出","TitleImg":"38c874dc-4c05-4a4c-a862-8173e2b54281.jpg","Content":null,"Summary":"","StationId":0,"Weight":0,"CreateDate":"2017-7-6 10:58","CoordinateSet":null,"PoiSet":null},{"Id":3,"Title":"手作之旅","TitleImg":"fefffaa3-bd9f-4be6-8c78-cdba45bc0f78.jpg","Content":null,"Summary":"","StationId":0,"Weight":0,"CreateDate":"2017-6-14 10:07","CoordinateSet":null,"PoiSet":null},{"Id":12,"Title":"赶海潮","TitleImg":"95ff1923-c599-4d3d-bee1-df21a16e1f8c.jpg","Content":null,"Summary":"涛声远，笛声长，一路骑行赶海忙","StationId":0,"Weight":0,"CreateDate":"2017-7-10 16:35","CoordinateSet":null,"PoiSet":null},{"Id":13,"Title":"一个名字引发的旅程","TitleImg":"fef22745-ba6a-4f45-95e9-56c3b1e523a1.jpg","Content":null,"Summary":"","StationId":0,"Weight":0,"CreateDate":"2017-7-11 16:12","CoordinateSet":null,"PoiSet":null},{"Id":6,"Title":"夜行记","TitleImg":"e007a228-8273-4f09-8ec8-99558dff75a7.jpg","Content":null,"Summary":"","StationId":0,"Weight":0,"CreateDate":"2017-6-14 10:25","CoordinateSet":null,"PoiSet":null},{"Id":7,"Title":"美味穿梭","TitleImg":"06655129-cf38-49dc-bcd3-846c7de1addf.jpg","Content":null,"Summary":"","StationId":0,"Weight":0,"CreateDate":"2017-6-14 10:25","CoordinateSet":null,"PoiSet":null},{"Id":9,"Title":"老别墅穿越之旅（一）","TitleImg":"6af79c49-a556-4c3b-ad53-b3682b838c13.jpg","Content":null,"Summary":"","StationId":0,"Weight":0,"CreateDate":"2017-6-14 10:26","CoordinateSet":null,"PoiSet":null},{"Id":1011,"Title":"老别墅穿越之旅（二）","TitleImg":"c4a9d9a7-19dd-4289-bf22-b83168abfd65.jpg","Content":null,"Summary":"","StationId":0,"Weight":0,"CreateDate":"2017-8-11 15:51","CoordinateSet":null,"PoiSet":null},{"Id":1012,"Title":"老别墅穿越之旅（三）","TitleImg":"a3162b96-a75e-4c45-99d3-f22fa9b9d063.jpg","Content":null,"Summary":"","StationId":0,"Weight":0,"CreateDate":"2017-8-18 12:40","CoordinateSet":null,"PoiSet":null}]}
     */

    private String code;
    private String msg;
    private DatasBean datas;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * nextPager : -1
         * routeList : [{"Id":4,"Title":"海滨第一弯","TitleImg":"fd067129-7517-4549-9186-e66c1c1aa11e.jpg","Content":null,"Summary":"","StationId":0,"Weight":0,"CreateDate":"2017-6-14 10:18","CoordinateSet":null,"PoiSet":null},{"Id":11,"Title":"岛上日出","TitleImg":"38c874dc-4c05-4a4c-a862-8173e2b54281.jpg","Content":null,"Summary":"","StationId":0,"Weight":0,"CreateDate":"2017-7-6 10:58","CoordinateSet":null,"PoiSet":null},{"Id":3,"Title":"手作之旅","TitleImg":"fefffaa3-bd9f-4be6-8c78-cdba45bc0f78.jpg","Content":null,"Summary":"","StationId":0,"Weight":0,"CreateDate":"2017-6-14 10:07","CoordinateSet":null,"PoiSet":null},{"Id":12,"Title":"赶海潮","TitleImg":"95ff1923-c599-4d3d-bee1-df21a16e1f8c.jpg","Content":null,"Summary":"涛声远，笛声长，一路骑行赶海忙","StationId":0,"Weight":0,"CreateDate":"2017-7-10 16:35","CoordinateSet":null,"PoiSet":null},{"Id":13,"Title":"一个名字引发的旅程","TitleImg":"fef22745-ba6a-4f45-95e9-56c3b1e523a1.jpg","Content":null,"Summary":"","StationId":0,"Weight":0,"CreateDate":"2017-7-11 16:12","CoordinateSet":null,"PoiSet":null},{"Id":6,"Title":"夜行记","TitleImg":"e007a228-8273-4f09-8ec8-99558dff75a7.jpg","Content":null,"Summary":"","StationId":0,"Weight":0,"CreateDate":"2017-6-14 10:25","CoordinateSet":null,"PoiSet":null},{"Id":7,"Title":"美味穿梭","TitleImg":"06655129-cf38-49dc-bcd3-846c7de1addf.jpg","Content":null,"Summary":"","StationId":0,"Weight":0,"CreateDate":"2017-6-14 10:25","CoordinateSet":null,"PoiSet":null},{"Id":9,"Title":"老别墅穿越之旅（一）","TitleImg":"6af79c49-a556-4c3b-ad53-b3682b838c13.jpg","Content":null,"Summary":"","StationId":0,"Weight":0,"CreateDate":"2017-6-14 10:26","CoordinateSet":null,"PoiSet":null},{"Id":1011,"Title":"老别墅穿越之旅（二）","TitleImg":"c4a9d9a7-19dd-4289-bf22-b83168abfd65.jpg","Content":null,"Summary":"","StationId":0,"Weight":0,"CreateDate":"2017-8-11 15:51","CoordinateSet":null,"PoiSet":null},{"Id":1012,"Title":"老别墅穿越之旅（三）","TitleImg":"a3162b96-a75e-4c45-99d3-f22fa9b9d063.jpg","Content":null,"Summary":"","StationId":0,"Weight":0,"CreateDate":"2017-8-18 12:40","CoordinateSet":null,"PoiSet":null}]
         */

        private int nextPager;
        private List<RouteListBean> routeList;

        public int getNextPager() {
            return nextPager;
        }

        public void setNextPager(int nextPager) {
            this.nextPager = nextPager;
        }

        public List<RouteListBean> getRouteList() {
            return routeList;
        }

        public void setRouteList(List<RouteListBean> routeList) {
            this.routeList = routeList;
        }

        public static class RouteListBean {
            /**
             * Id : 4
             * Title : 海滨第一弯
             * TitleImg : fd067129-7517-4549-9186-e66c1c1aa11e.jpg
             * Content : null
             * Summary :
             * StationId : 0
             * Weight : 0
             * CreateDate : 2017-6-14 10:18
             * CoordinateSet : null
             * PoiSet : null
             */

            private int Id;
            private String Title;
            private String TitleImg;
            private Object Content;
            private String Summary;
            private int StationId;
            private int Weight;
            private String CreateDate;
            private Object CoordinateSet;
            private Object PoiSet;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getTitle() {
                return Title;
            }

            public void setTitle(String Title) {
                this.Title = Title;
            }

            public String getTitleImg() {
                return TitleImg;
            }

            public void setTitleImg(String TitleImg) {
                this.TitleImg = TitleImg;
            }

            public Object getContent() {
                return Content;
            }

            public void setContent(Object Content) {
                this.Content = Content;
            }

            public String getSummary() {
                return Summary;
            }

            public void setSummary(String Summary) {
                this.Summary = Summary;
            }

            public int getStationId() {
                return StationId;
            }

            public void setStationId(int StationId) {
                this.StationId = StationId;
            }

            public int getWeight() {
                return Weight;
            }

            public void setWeight(int Weight) {
                this.Weight = Weight;
            }

            public String getCreateDate() {
                return CreateDate;
            }

            public void setCreateDate(String CreateDate) {
                this.CreateDate = CreateDate;
            }

            public Object getCoordinateSet() {
                return CoordinateSet;
            }

            public void setCoordinateSet(Object CoordinateSet) {
                this.CoordinateSet = CoordinateSet;
            }

            public Object getPoiSet() {
                return PoiSet;
            }

            public void setPoiSet(Object PoiSet) {
                this.PoiSet = PoiSet;
            }
        }
    }
}
