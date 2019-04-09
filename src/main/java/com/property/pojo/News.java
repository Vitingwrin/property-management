package com.property.pojo;

import lombok.Data;

/**
 * @author Chichiu Yeung
 * Created in 2018/6/29 10:18
 */
@Data
public class News {

    public static final int DRAFT = 0;
    public static final int PUBLISH = 1;
    public static final int TRASH = 2;

    private Integer id;
    private String title;
    private String summary;
    private String html;
    private String markdown;
    private String author;
    private String createTime;
    private String lastEditTime;
    private String timeStamp;
    private String posterUrl;

    private Long clickTimes;
    private Integer status;

    public static class Builder {
        private String title;
        private String summary;
        private String html;
        private String markdown;
        private String author;
        private String timeStamp;
        private String posterUrl;
        private Integer status = PUBLISH;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder summary(String summary) {
            this.summary = summary;
            return this;
        }

        public Builder html(String html) {
            this.html = html;
            return this;
        }

        public Builder markdown(String markdown) {
            this.markdown = markdown;
            return this;
        }

        public Builder status(Integer status) {
            this.status = status;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder posterUrl(String posterUrl) {
            this.posterUrl = posterUrl;
            return this;
        }

        public News build() {
            News news = new News();
            news.title = this.title;
            news.summary = this.summary;
            news.html = this.html;
            news.markdown = this.markdown;
            news.status = this.status;
            news.author = this.author;
            news.timeStamp = timeStamp;
            news.posterUrl = this.posterUrl;
            return news;
        }
    }

}
