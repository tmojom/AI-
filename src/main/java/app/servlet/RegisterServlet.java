package app.servlet;

import app.dao.UserDao;
import app.entity.Userinfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024, // 1 MB
    maxFileSize = 1024 * 1024 * 5,   // 5 MB
    maxRequestSize = 1024 * 1024 * 10 // 10 MB
)
public class RegisterServlet extends HttpServlet {
    private UserDao userDao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 使用Tomcat的temp目录
        String tempPath = "D:/programmer/Compiler/apache-tomcat-10.1.34/temp";
        Files.createDirectories(Paths.get(tempPath));
        
        // 使用Tomcat的webapps目录下的uploads
        String uploadPath = getServletContext().getRealPath("/") + "uploads";
        Files.createDirectories(Paths.get(uploadPath));
        
        // 获取所有表单字段
        String username = getPartValue(request, "username");
        String password = getPartValue(request, "password");
        String confirmPassword = getPartValue(request, "confirmPassword");

        // 验证必填字段
        if (username == null || password == null || confirmPassword == null) {
            request.setAttribute("error", "所有字段都必须填写");
            request.getRequestDispatcher("/user/register.jsp").forward(request, response);
            return;
        }

        // 验证密码
        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "两次输入的密码不一致");
            request.getRequestDispatcher("/user/register.jsp").forward(request, response);
            return;
        }

        // 处理头像上传
        Part filePart = request.getPart("avatar");
        String fileName = System.currentTimeMillis() + "_" + filePart.getSubmittedFileName();

        // 保存文件
        filePart.write(uploadPath + "/" + fileName);

        // 创建用户对象
        Userinfo user = new Userinfo();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole("user"); // 默认角色
        user.setHead_image("/uploads/" + fileName);

        // 保存到数据库
        try {
            Integer userId = userDao.insert(user);
            if (userId != null) {
                // 注册成功，重定向到登录页面
                response.sendRedirect(request.getContextPath() + "/user/login.jsp");
            } else {
                request.setAttribute("error", "注册失败，请重试");
                request.getRequestDispatcher("/user/register.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "用户名已存在");
            request.getRequestDispatcher("/user/register.jsp").forward(request, response);
        }
    }

    // 辅助方法：从multipart表单中获取字段值
    private String getPartValue(HttpServletRequest request, String fieldName) 
            throws ServletException, IOException {
        Part part = request.getPart(fieldName);
        if (part != null) {
            try (java.io.InputStream is = part.getInputStream();
                 java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A")) {
                return s.hasNext() ? s.next().trim() : "";
            }
        }
        return null;
    }
} 