package app.entity;

public class Content {
    private Integer id;
    private Integer chapter_id;
    private String chapter_title;
    private String chapter_text;
    private String chapter_image;
    private Integer hits;
    private boolean isLearned;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(Integer chapter_id) {
        this.chapter_id = chapter_id;
    }

    public String getChapter_title() {
        return chapter_title;
    }

    public void setChapter_title(String chapter_title) {
        this.chapter_title = chapter_title;
    }

    public String getChapter_text() {
        return chapter_text;
    }

    public void setChapter_text(String chapter_text) {
        this.chapter_text = chapter_text;
    }

    public String getChapter_image() {
        return chapter_image;
    }

    public void setChapter_image(String chapter_image) {
        this.chapter_image = chapter_image;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public boolean isLearned() {
        return isLearned;
    }

    public void setLearned(boolean learned) {
        isLearned = learned;
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", chapter_id=" + chapter_id +
                ", chapter_title='" + chapter_title + '\'' +
                ", chapter_text='" + chapter_text + '\'' +
                ", chapter_image='" + chapter_image + '\'' +
                ", hits=" + hits +
                ", isLearned=" + isLearned +
                '}';
    }

    public String myname() {
        return "张聪曾子山郑嘉鑫";
    }
} 