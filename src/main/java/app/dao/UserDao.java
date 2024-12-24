package app.dao;

import app.entity.Userinfo;
import utils.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    
    /**
     * 作者：2021110786 张聪 2021110785 曾子山 2021110792 郑嘉鑫
     * 作用：添加新用户
     * @param userinfo 用户对象
     * @return 新增用户的ID
     */
    public Integer insert(Userinfo userinfo) {
        String sql = "INSERT INTO userinfo (username, password, role, head_image, stu110599) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, userinfo.getUsername());
            stmt.setString(2, userinfo.getPassword());
            stmt.setString(3, userinfo.getRole());
            stmt.setString(4, userinfo.getHead_image());
            stmt.setString(5, userinfo.getStu110599());
            
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
     * 作用：删除用户
     * @param id 用户ID
     * @return 是否删除成功
     */
    public boolean delete(Integer id) {
        String sql = "DELETE FROM userinfo WHERE id = ?";
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
     * 作用：更新用户信息
     * @param userinfo 用户对象
     * @return 是否更新成功
     */
    public boolean update(Userinfo userinfo) {
        String sql = "UPDATE userinfo SET username=?, password=?, role=?, head_image=?, stu110599=? WHERE id=?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, userinfo.getUsername());
            stmt.setString(2, userinfo.getPassword());
            stmt.setString(3, userinfo.getRole());
            stmt.setString(4, userinfo.getHead_image());
            stmt.setString(5, userinfo.getStu110599());
            stmt.setInt(6, userinfo.getId());
            
            int rowsAffected = stmt.executeUpdate();
            
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 作者：2021110786 张聪 2021110785 曾子山 2021110792 郑嘉鑫
     * 作用：获取所有用户列表
     * @return 用户列表
     */
    public List<Userinfo> list() {
        List<Userinfo> list = new ArrayList<>();
        String sql = "SELECT * FROM userinfo";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Userinfo user = new Userinfo();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setHead_image(rs.getString("head_image"));
                user.setStu110599(rs.getString("stu110599"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 作者：2021110786 张聪 2021110785 曾子山 2021110792 郑嘉鑫
     * 作用：验证用户登录
     * @param username 用户名
     * @param password 密码
     * @return 用户对象，如果验证失败返回null
     */
    public Userinfo validateUser(String username, String password) {
        String sql = "SELECT * FROM userinfo WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Userinfo user = new Userinfo();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setHead_image(rs.getString("head_image"));
                user.setStu110599(rs.getString("stu110599"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 作者：2021110786 张聪 2021110785 曾子山 2021110792 郑嘉鑫
     * 作用：根据ID获取用户
     * @param id 用户ID
     * @return 用户对象
     */
    public Userinfo get(Integer id) {
        String sql = "SELECT * FROM userinfo WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Userinfo user = new Userinfo();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setHead_image(rs.getString("head_image"));
                user.setStu110599(rs.getString("stu110599"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 作者：2021110786 张聪 2021110785 曾子山 2021110792 ���嘉鑫
     * 作用：获取完成章节最多的前N名用户
     * @param limit 限制数量
     * @return 完成章节最多的用户列表
     */
    public List<Userinfo> getTopUsersByCompletedChapters(int limit) {
        List<Userinfo> list = new ArrayList<>();
        String sql = "SELECT u.*, COUNT(DISTINCT l.content_id) as completed_chapters " +
                    "FROM userinfo u " +
                    "LEFT JOIN log l ON u.id = l.user_id " +
                    "WHERE u.role != 'admin' " +
                    "GROUP BY u.id " +
                    "ORDER BY completed_chapters DESC " +
                    "LIMIT ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, limit);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Userinfo user = new Userinfo();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setRole(rs.getString("role"));
                user.setHead_image(rs.getString("head_image"));
                user.setStu110599(rs.getString("stu110599"));
                user.setCompletedChapters(rs.getInt("completed_chapters"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 作者：2021110786 张聪 2021110785 曾子山 2021110792 郑嘉鑫
     * 作用：获取最近学习的用户
     * @param excludeUserIds 排除的用户ID列表
     * @param limit 限制数量
     * @return 最近学习的用户列表
     */
    public List<Userinfo> getRecentStudyUsers(List<Integer> excludeUserIds, int limit) {
        List<Userinfo> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
            "SELECT DISTINCT u.*, l.content_id, l.optime " +
            "FROM userinfo u " +
            "JOIN log l ON u.id = l.user_id " +
            "WHERE u.role = 'user' " +
            "AND (u.id, l.optime) IN ( " +
            "    SELECT user_id, MAX(optime) " +
            "    FROM log " +
            "    GROUP BY user_id " +
            ") " +
            "ORDER BY l.optime DESC LIMIT ?"
        );
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            
            stmt.setInt(1, limit);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Userinfo user = new Userinfo();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setRole(rs.getString("role"));
                user.setHead_image(rs.getString("head_image"));
                user.setStu110599(rs.getString("stu110599"));
                user.setLastChapter("第" + rs.getString("content_id") + "章");
                user.setLastStudyTime(rs.getTimestamp("optime"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
} 