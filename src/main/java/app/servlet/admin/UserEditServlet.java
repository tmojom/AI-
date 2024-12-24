package app.servlet.admin;

import app.dao.UserDao;
import app.entity.Userinfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/editUser")
public class UserEditServlet extends HttpServlet {
    private UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Userinfo user = userDao.get(id);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/admin/editUser.jsp").forward(request, response);
    }
} 