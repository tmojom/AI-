package app.servlet.admin;

import app.dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/deleteUser")
public class UserDeleteServlet extends HttpServlet {
    private UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        if (userDao.delete(id)) {
            request.getSession().setAttribute("message", "用户删除成功");
            request.getSession().setAttribute("messageType", "success");
        } else {
            request.getSession().setAttribute("message", "用户删除失败");
            request.getSession().setAttribute("messageType", "error");
        }
        
        response.sendRedirect(request.getContextPath() + "/admin/userList");
    }
} 