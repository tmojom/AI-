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

@WebServlet("/user/learn")
public class LearnServlet extends HttpServlet {
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

        int contentId = Integer.parseInt(request.getParameter("id"));
        Content content = contentDao.get(contentId);
        
        if (content != null) {
            // 增加点击量
            content.setHits(content.getHits() + 1);
            contentDao.update(content);
            
            // 获取上一章和下一章
            Content prevChapter = contentDao.getPreviousChapter(content.getChapter_id());
            Content nextChapter = contentDao.getNextChapter(content.getChapter_id());
            
            // 计算学习进度
            int totalChapters = contentDao.getTotalChapters();
            int completedChapters = logDao.getCompletedChaptersCount(user.getId());
            int progress = (int)((completedChapters * 100.0) / totalChapters);
            
            request.setAttribute("content", content);
            request.setAttribute("prevChapter", prevChapter);
            request.setAttribute("nextChapter", nextChapter);
            request.setAttribute("progress", progress);
            
            request.getRequestDispatcher("/user/learn.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/user/study");
        }
    }
} 