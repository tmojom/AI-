package app.servlet.user;

import app.dao.ContentDao;
import app.dao.LogDao;
import app.entity.Content;
import app.entity.Userinfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet("/user/study")
public class StudyServlet extends HttpServlet {
    private ContentDao contentDao = new ContentDao();
    private LogDao logDao = new LogDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Userinfo user = (Userinfo) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/user/login.jsp");
            return;
        }

        // 获取所有课程
        List<Content> contents = contentDao.list();
        
        // 获取用户已学习的课��ID
        Set<Integer> learnedContentIds = logDao.getUserLogs(user.getId()).stream()
                .map(log -> log.getContent_id())
                .collect(Collectors.toSet());
        
        // 标记已学习的课程
        for (Content content : contents) {
            content.setLearned(learnedContentIds.contains(content.getId()));
        }

        request.setAttribute("contents", contents);
        request.setAttribute("totalLessons", contents.size());
        request.setAttribute("completedLessons", learnedContentIds.size());
        
        request.getRequestDispatcher("/user/study.jsp").forward(request, response);
    }
} 