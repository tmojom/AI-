package app.servlet.admin;

import app.dao.UserDao;
import app.entity.Userinfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/admin/ranking")
public class RankingServlet extends HttpServlet {
    private UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 获取前3名用户
        List<Userinfo> topUsers = userDao.getTopUsersByCompletedChapters(3);
        
        // 获取这些用户的ID
        List<Integer> topUserIds = topUsers.stream()
                .map(Userinfo::getId)
                .collect(Collectors.toList());
        
        // 获取最近学习的3名用户（排除前3名用户）
        List<Userinfo> recentUsers = userDao.getRecentStudyUsers(topUserIds, 3);

        request.setAttribute("topUsers", topUsers);
        request.setAttribute("recentUsers", recentUsers);
        request.getRequestDispatcher("/admin/ranking.jsp").forward(request, response);
    }
} 