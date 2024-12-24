package app.servlet.user;

import app.dao.UserDao;
import app.entity.Userinfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet("/user/updateAvatar")
@MultipartConfig
public class UpdateAvatarServlet extends HttpServlet {
    private UserDao userDao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Userinfo user = (Userinfo) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/user/login.jsp");
            return;
        }

        Part filePart = request.getPart("avatar");
        String uploadPath = getServletContext().getRealPath("/") + "uploads";
        Files.createDirectories(Paths.get(uploadPath));
        
        String fileName = System.currentTimeMillis() + "_" + filePart.getSubmittedFileName();
        filePart.write(uploadPath + "/" + fileName);
        
        user.setHead_image("/uploads/" + fileName);
        
        if (userDao.update(user)) {
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("message", "头像更新成功");
            request.getSession().setAttribute("messageType", "success");
        } else {
            request.getSession().setAttribute("message", "头像更新失败");
            request.getSession().setAttribute("messageType", "error");
        }
        
        response.sendRedirect(request.getContextPath() + "/user/profile.jsp");
    }
} 