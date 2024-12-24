package app.servlet.admin;

import app.dao.UserDao;
import app.entity.Userinfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin/login")
public class AdminLoginServlet extends HttpServlet {
    private UserDao userDao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // 验证用户名和密码
        Userinfo user = userDao.validateUser(username, password);
        
        if (user != null && "admin".equals(user.getRole())) {
            // 管理员登录成功
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role", "admin");
            response.sendRedirect(request.getContextPath() + "/admin/userList");
        } else {
            // 登录失败或不是管理员
            request.setAttribute("error", "用户名或密码错误，或者没有管理员权限");
            request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
        }
    }
} 