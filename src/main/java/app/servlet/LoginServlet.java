package app.servlet;

import app.dao.UserDao;
import app.entity.Userinfo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private UserDao userDao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // 验证用户名和密码
        Userinfo user = userDao.validateUser(username, password);
        
        if (user != null && "user".equals(user.getRole())) {
            // 普通用户登录成功
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role", "user");
            response.sendRedirect(request.getContextPath() + "/user/study");
        } else {
            // 登录失败或是管理员账号
            request.setAttribute("error", "用户名或密码错误，或者使用了管理员账号");
            request.getRequestDispatcher("/user/login.jsp").forward(request, response);
        }
    }
} 