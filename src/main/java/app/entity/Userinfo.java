package app.entity;

import java.sql.Timestamp;

public class Userinfo {
    private Integer id;
    private String username;
    private String password;
    private String role;
    private String head_image;
    private String stu110599;
    private int completedChapters;
    private String lastChapter;
    private Timestamp lastStudyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getHead_image() {
        return head_image;
    }

    public void setHead_image(String head_image) {
        this.head_image = head_image;
    }

    public String getStu110599() {
        return stu110599;
    }

    public void setStu110599(String stu110599) {
        this.stu110599 = stu110599;
    }

    public int getCompletedChapters() {
        return completedChapters;
    }

    public void setCompletedChapters(int completedChapters) {
        this.completedChapters = completedChapters;
    }

    public String getLastChapter() {
        return lastChapter;
    }

    public void setLastChapter(String lastChapter) {
        this.lastChapter = lastChapter;
    }

    public Timestamp getLastStudyTime() {
        return lastStudyTime;
    }

    public void setLastStudyTime(Timestamp lastStudyTime) {
        this.lastStudyTime = lastStudyTime;
    }

    @Override
    public String toString() {
        return "Userinfo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", head_image='" + head_image + '\'' +
                ", stu110599='" + stu110599 + '\'' +
                ", completedChapters=" + completedChapters +
                ", lastChapter='" + lastChapter + '\'' +
                ", lastStudyTime=" + lastStudyTime +
                '}';
    }

    public String myname() {
        return "张聪曾子山郑嘉鑫";
    }
} 