package app.servlet.user;

import app.dao.UserDao;
import app.entity.Userinfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/updatePassword")
public class UpdatePasswordServlet extends HttpServlet {
    private UserDao userDao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Userinfo user = (Userinfo) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/user/login.jsp");
            return;
        }

        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        // 添加调试日志
        System.out.println("User ID: " + user.getId());
        System.out.println("Old password from form: " + oldPassword);
        System.out.println("Current password in session: " + user.getPassword());

        // 验证当前密码
        if (!oldPassword.equals(user.getPassword())) {
            request.getSession().setAttribute("message", "当前密码错误");
            request.getSession().setAttribute("messageType", "error");
            response.sendRedirect(request.getContextPath() + "/user/profile.jsp");
            return;
        }

        // 验证新密码
        if (!newPassword.equals(confirmPassword)) {
            request.getSession().setAttribute("message", "两次输入的新密码不一致");
            request.getSession().setAttribute("messageType", "error");
            response.sendRedirect(request.getContextPath() + "/user/profile.jsp");
            return;
        }

        // 更新密码
        user.setPassword(newPassword);
        boolean updateSuccess = userDao.update(user);
        System.out.println("Update success: " + updateSuccess);  // 添加调试日志

        if (updateSuccess) {
            // 更新session中的用户信息
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("message", "密码修改成功");
            request.getSession().setAttribute("messageType", "success");
        } else {
            request.getSession().setAttribute("message", "密码修改失败");
            request.getSession().setAttribute("messageType", "error");
        }
        
        response.sendRedirect(request.getContextPath() + "/user/profile.jsp");
    }
} 