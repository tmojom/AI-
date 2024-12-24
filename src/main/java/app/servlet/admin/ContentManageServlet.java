package app.servlet.admin;

import app.dao.ContentDao;
import app.entity.Content;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/contentList")
public class ContentManageServlet extends HttpServlet {
    private ContentDao contentDao = new ContentDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Content> contents = contentDao.list();
        request.setAttribute("contents", contents);
        request.getRequestDispatcher("/admin/contentList.jsp").forward(request, response);
    }
} 