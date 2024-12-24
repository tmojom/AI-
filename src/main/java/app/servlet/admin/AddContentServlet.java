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

@WebServlet("/admin/addContent")
@MultipartConfig
public class AddContentServlet extends HttpServlet {
    private ContentDao contentDao = new ContentDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // 获取表单数据
        int chapterId = Integer.parseInt(request.getParameter("chapter_id"));
        String chapterTitle = request.getParameter("chapter_title");
        String chapterText = request.getParameter("chapter_text");
        
        // 处理图片上传
        Part filePart = request.getPart("chapter_image");
        String uploadPath = getServletContext().getRealPath("/") + "uploads";
        Files.createDirectories(Paths.get(uploadPath));
        
        String fileName = System.currentTimeMillis() + "_" + filePart.getSubmittedFileName();
        filePart.write(uploadPath + "/" + fileName);
        
        // 创建内容对象
        Content content = new Content();
        content.setChapter_id(chapterId);
        content.setChapter_title(chapterTitle);
        content.setChapter_text(chapterText);
        content.setChapter_image("/uploads/" + fileName);
        content.setHits(0);
        
        // 保存到数据库
        Integer contentId = contentDao.insert(content);
        
        if (contentId != null) {
            request.getSession().setAttribute("message", "内容添加成功");
            request.getSession().setAttribute("messageType", "success");
            response.sendRedirect(request.getContextPath() + "/admin/contentList");
        } else {
            request.setAttribute("error", "内容添加失败");
            request.getRequestDispatcher("/admin/addContent.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/admin/addContent.jsp").forward(request, response);
    }
} 