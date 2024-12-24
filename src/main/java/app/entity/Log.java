package app.entity;

import java.util.Date;

public class Log {
    private Integer id;
    private Integer user_id;
    private Integer content_id;
    private Date optime;
    private Content content;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getContent_id() {
        return content_id;
    }

    public void setContent_id(Integer content_id) {
        this.content_id = content_id;
    }

    public Date getOptime() {
        return optime;
    }

    public void setOptime(Date optime) {
        this.optime = optime;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", content_id=" + content_id +
                ", optime=" + optime +
                ", content=" + content +
                '}';
    }

    public String myname() {
        return "张聪曾子山郑嘉鑫";
    }
} 