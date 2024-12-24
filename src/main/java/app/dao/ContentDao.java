package app.dao;

import app.entity.Content;
import utils.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContentDao {
    
    /**
     * 作者：2021110786 张聪 2021110785 曾子山 2021110792 郑嘉鑫
     * 作用：添加新内容
     * @param content 内容对象
     * @return 新增内容的ID
     */
    public Integer insert(Content content) {
        String sql = "INSERT INTO content (chapter_id, chapter_title, chapter_text, chapter_image, hits) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, content.getChapter_id());
            stmt.setString(2, content.getChapter_title());
            stmt.setString(3, content.getChapter_text());
            stmt.setString(4, content.getChapter_image());
            stmt.setInt(5, content.getHits());
            
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 作者：2021110786 张聪 2021110785 曾子山 2021110792 郑嘉鑫
     * 作用：删除内容
     * @param id 内容ID
     * @return 是否删除成功
     */
    public boolean delete(Integer id) {
        String sql = "DELETE FROM content WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 作者：2021110786 张聪 2021110785 曾子山 2021110792 郑嘉鑫
     * 作用：更新内容
     * @param content 内容对象
     * @return 是否更新成功
     */
    public boolean update(Content content) {
        String sql = "UPDATE content SET chapter_id=?, chapter_title=?, chapter_text=?, chapter_image=?, hits=? WHERE id=?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, content.getChapter_id());
            stmt.setString(2, content.getChapter_title());
            stmt.setString(3, content.getChapter_text());
            stmt.setString(4, content.getChapter_image());
            stmt.setInt(5, content.getHits());
            stmt.setInt(6, content.getId());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 作者：2021110786 张聪 2021110785 曾子山 2021110792 郑嘉鑫
     * 作用：获取所有内容列表
     * @return 内容列表
     */
    public List<Content> list() {
        List<Content> list = new ArrayList<>();
        String sql = "SELECT * FROM content";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Content content = new Content();
                content.setId(rs.getInt("id"));
                content.setChapter_id(rs.getInt("chapter_id"));
                content.setChapter_title(rs.getString("chapter_title"));
                content.setChapter_text(rs.getString("chapter_text"));
                content.setChapter_image(rs.getString("chapter_image"));
                content.setHits(rs.getInt("hits"));
                list.add(content);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 作者：2021110786 张聪 2021110785 曾子山 2021110792 郑嘉鑫
     * 作用：根据ID获取内容
     * @param id 内容ID
     * @return 内容对象
     */
    public Content get(Integer id) {
        String sql = "SELECT * FROM content WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Content content = new Content();
                content.setId(rs.getInt("id"));
                content.setChapter_id(rs.getInt("chapter_id"));
                content.setChapter_title(rs.getString("chapter_title"));
                content.setChapter_text(rs.getString("chapter_text"));
                content.setChapter_image(rs.getString("chapter_image"));
                content.setHits(rs.getInt("hits"));
                return content;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 作者：2021110786 张聪 2021110785 曾子山 2021110792 郑嘉鑫
     * 作用：获取当前章节的上一章内容
     * @param currentChapterId 当前章节ID
     * @return 上一章的内容对象，如果是第一章则返回null
     */
    public Content getPreviousChapter(int currentChapterId) {
        String sql = "SELECT * FROM content WHERE chapter_id < ? ORDER BY chapter_id DESC LIMIT 1";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, currentChapterId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Content content = new Content();
                content.setId(rs.getInt("id"));
                content.setChapter_id(rs.getInt("chapter_id"));
                content.setChapter_title(rs.getString("chapter_title"));
                return content;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 作者：2021110786 张聪 2021110785 曾子山 2021110792 郑嘉鑫
     * 作用：获取当前章节的下一章内容
     * @param currentChapterId 当前章节ID
     * @return 下一章的内容对象，如果是最后一章则返回null
     */
    public Content getNextChapter(int currentChapterId) {
        String sql = "SELECT * FROM content WHERE chapter_id > ? ORDER BY chapter_id ASC LIMIT 1";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, currentChapterId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Content content = new Content();
                content.setId(rs.getInt("id"));
                content.setChapter_id(rs.getInt("chapter_id"));
                content.setChapter_title(rs.getString("chapter_title"));
                return content;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 作者：2021110786 张聪 2021110785 曾子山 2021110792 郑嘉鑫
     * 作用：获取学习内容的总章节数
     * @return 总章节数，如果发生错误则返回0
     */
    public int getTotalChapters() {
        String sql = "SELECT COUNT(*) FROM content";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
} 