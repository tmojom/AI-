package app.servlet.admin;

import app.dao.ContentDao;
import app.entity.Content;
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

@WebServlet("/admin/editContent")
@MultipartConfig
public class ContentEditServlet extends HttpServlet {
    private ContentDao contentDao = new ContentDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Content content = contentDao.get(id);
        request.setAttribute("content", content);
        request.getRequestDispatcher("/admin/editContent.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Content content = contentDao.get(id);
        
        content.setChapter_id(Integer.parseInt(request.getParameter("chapter_id")));
        content.setChapter_title(request.getParameter("chapter_title"));
        content.setChapter_text(request.getParameter("chapter_text"));
        
        // 处理图片上传
        Part filePart = request.getPart("chapter_image");
        if (filePart != null && filePart.getSize() > 0) {
            String uploadPath = getServletContext().getRealPath("/uploads");
            Files.createDirectories(Paths.get(uploadPath));
            
            String fileName = System.currentTimeMillis() + "_" + filePart.getSubmittedFileName();
            filePart.write(uploadPath + "/" + fileName);
            content.setChapter_image("/uploads/" + fileName);
        }
        
        if (contentDao.update(content)) {
            response.sendRedirect(request.getContextPath() + "/admin/contentList");
        } else {
            request.setAttribute("error", "更新失败");
            request.setAttribute("content", content);
            request.getRequestDispatcher("/admin/editContent.jsp").forward(request, response);
        }
    }
} 