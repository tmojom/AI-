package app.servlet.admin;

import app.dao.LogDao;
import app.dao.UserDao;
import app.entity.Log;
import app.entity.Userinfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/userLogs")
public class LogViewServlet extends HttpServlet {
    private LogDao logDao = new LogDao();
    private UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        Userinfo user = userDao.get(userId);
        
        if (user != null) {
            request.setAttribute("username", user.getUsername());
            request.setAttribute("userId", userId);
            List<Log> logs = logDao.getUserLogs(userId);
            request.setAttribute("logs", logs);
        }
        
        request.getRequestDispatcher("/admin/logList.jsp").forward(request, response);
    }
} 