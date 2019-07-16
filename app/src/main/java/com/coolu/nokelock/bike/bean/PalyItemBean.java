package com.coolu.nokelock.bike.bean;

import java.util.List;

/**
 * Created by admin on 2017/8/31.
 */

public class PalyItemBean {
    private String code;
    private String msg;
    private  Datas datas;

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

    public Datas getDatas() {
        return datas;
    }

    public void setDatas(Datas datas) {
        this.datas = datas;
    }

    public class  Datas{
        private String nextPager;
        private  List<ArticleList>articleList;

        public class ArticleList{
            private String Id;
            private  String Title;
            private String Summary;
            private String Content;
            private String TitleImg;
            private String Type;

            public String getId() {
                return Id;
            }

            public void setId(String id) {
                Id = id;
            }

            public String getTitle() {
                return Title;
            }

            public void setTitle(String title) {
                Title = title;
            }

            public String getSummary() {
                return Summary;
            }

            public void setSummary(String summary) {
                Summary = summary;
            }

            public String getContent() {
                return Content;
            }

            public void setContent(String content) {
                Content = content;
            }

            public String getTitleImg() {
                return TitleImg;
            }

            public void setTitleImg(String titleImg) {
                TitleImg = titleImg;
            }

            public String getType() {
                return Type;
            }

            public void setType(String type) {
                Type = type;
            }
        }

        public String getNextPager() {
            return nextPager;
        }

        public void setNextPager(String nextPager) {
            this.nextPager = nextPager;
        }

        public List<ArticleList> getArticleList() {
            return articleList;
        }

        public void setArticleList(List<ArticleList> articleList) {
            this.articleList = articleList;
        }
    }


}
