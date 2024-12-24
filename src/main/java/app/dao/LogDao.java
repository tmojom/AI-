package app.dao;

import app.entity.Log;
import app.entity.Content;
import utils.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class LogDao {
    
    /**
     * 作者：2021110786 张聪 2021110785 曾子山 2021110792 郑嘉鑫
     * 作用：添加新日志
     * @param log 日志对象
     * @return 新增日志的ID
     */
    public Integer insert(Log log) {
        String sql = "INSERT INTO log (user_id, content_id, optime) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, log.getUser_id());
            stmt.setInt(2, log.getContent_id());
            stmt.setTimestamp(3, new Timestamp(log.getOptime().getTime()));
            
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
     * 作用：查询用户最近的日志
     * @param user_id 用户ID
     * @return 最近的日志记录
     */
    public Log queryLastLog(Integer user_id) {
        String sql = "SELECT * FROM log WHERE user_id = ? ORDER BY optime DESC LIMIT 1";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, user_id);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Log log = new Log();
                log.setId(rs.getInt("id"));
                log.setUser_id(rs.getInt("user_id"));
                log.setContent_id(rs.getInt("content_id"));
                log.setOptime(rs.getTimestamp("optime"));
                return log;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 作者：2021110786 张聪 2021110785 曾子山 2021110792 郑嘉鑫
     * 作用：获取用户的所有学习日志
     * @param userId 用户ID
     * @return 日志列表
     */
    public List<Log> getUserLogs(Integer userId) {
        List<Log> logs = new ArrayList<>();
        String sql = "SELECT l.*, c.chapter_id, c.chapter_title FROM log l " +
                     "LEFT JOIN content c ON l.content_id = c.id " +
                     "WHERE l.user_id = ? ORDER BY l.optime DESC";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Log log = new Log();
                log.setId(rs.getInt("id"));
                log.setUser_id(rs.getInt("user_id"));
                log.setContent_id(rs.getInt("content_id"));
                log.setOptime(rs.getTimestamp("optime"));
                // 设置关联的内容信息
                Content content = new Content();
                content.setChapter_id(rs.getInt("chapter_id"));
                content.setChapter_title(rs.getString("chapter_title"));
                // 这里需要在Log类中添加content字段
                log.setContent(content);
                logs.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logs;
    }

    /**
     * 作者：2021110786 张聪 2021110785 曾子山 2021110792 郑嘉鑫
     * 作用：获取学习内容完成最多的前3名用户
     */
    public List<Map<String, Object>> getTopUsers() {
        List<Map<String, Object>> result = new ArrayList<>();
        String sql = "SELECT u.username, COUNT(DISTINCT l.content_id) as completedChapters " +
                     "FROM userinfo u LEFT JOIN log l ON u.id = l.user_id " +
                     "GROUP BY u.id ORDER BY completedChapters DESC LIMIT 3";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> user = new HashMap<>();
                user.put("username", rs.getString("username"));
                user.put("completedChapters", rs.getInt("completedChapters"));
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 作者：2021110786 张聪 2021110785 曾子山 2021110792 郑嘉鑫
     * 作用：获取最近学习的3位不重复用户
     */
    public List<Map<String, Object>> getRecentUsers() {
        List<Map<String, Object>> result = new ArrayList<>();
        String sql = "SELECT DISTINCT u.username, c.chapter_id as lastChapter, l.optime as lastStudyTime " +
                     "FROM userinfo u JOIN log l ON u.id = l.user_id " +
                     "JOIN content c ON l.content_id = c.id " +
                     "ORDER BY l.optime DESC LIMIT 3";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> user = new HashMap<>();
                user.put("username", rs.getString("username"));
                user.put("lastChapter", rs.getInt("lastChapter"));
                user.put("lastStudyTime", rs.getTimestamp("lastStudyTime"));
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 作者：2021110786 张聪 2021110785 曾子山 2021110792 郑嘉鑫
     * 作用：获取用户已完成章节数
     * @param userId 用户ID
     * @return 已完成章节数
     */
    public int getCompletedChaptersCount(Integer userId) {
        String sql = "SELECT COUNT(DISTINCT content_id) FROM log WHERE user_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, userId);
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