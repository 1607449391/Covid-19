package com.xjq.covid19.bean;

/*
 *@author：徐家庆
 *@time：2020-12-19 19:30
 *@description：
 *
 */
public class Message {
    private long time;
    private String title;
    private String content;
    private String source;
    private String url;

    public Message() {
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
