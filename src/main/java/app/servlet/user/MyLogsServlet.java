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
import java.util.List;

@WebServlet("/user/myLogs")
public class MyLogsServlet extends HttpServlet {
    private LogDao logDao = new LogDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Userinfo user = (Userinfo) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/user/login.jsp");
            return;
        }

        // 获取用户的学习记录
        List<Log> logs = logDao.getUserLogs(user.getId());
        request.setAttribute("logs", logs);
        
        // 设置完成的课程数
        request.setAttribute("completedLessons", logs.size());
        
        // 设置最近学习时间
        if (!logs.isEmpty()) {
            request.setAttribute("lastStudyTime", logs.get(0).getOptime());
        }
        
        request.getRequestDispatcher("/user/myLogs.jsp").forward(request, response);
    }
} 