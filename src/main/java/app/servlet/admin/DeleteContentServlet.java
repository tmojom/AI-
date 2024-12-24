package app.servlet.admin;

import app.dao.ContentDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/deleteContent")
public class DeleteContentServlet extends HttpServlet {
    private ContentDao contentDao = new ContentDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        if (contentDao.delete(id)) {
            request.getSession().setAttribute("message", "内容删除成功");
            request.getSession().setAttribute("messageType", "success");
        } else {
            request.getSession().setAttribute("message", "内容删除失败");
            request.getSession().setAttribute("messageType", "error");
        }
        
        response.sendRedirect(request.getContextPath() + "/admin/contentList");
    }
} 