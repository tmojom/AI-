package app.servlet.user;

import app.dao.LogDao;
import app.entity.Log;
import app.entity.Userinfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/user/completeLearn")
public class CompleteLearnServlet extends HttpServlet {
    private LogDao logDao = new LogDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Userinfo user = (Userinfo) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/user/login.jsp");
            return;
        }

        int contentId = Integer.parseInt(request.getParameter("contentId"));
        
        // 记录学习日志
        Log log = new Log();
        log.setUser_id(user.getId());
        log.setContent_id(contentId);
        log.setOptime(new Date());
        
        logDao.insert(log);
        
        // 返回学习页面
        response.sendRedirect(request.getContextPath() + "/user/study");
    }
} 