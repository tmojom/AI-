package app.servlet.admin;

import app.dao.LogDao;
import app.dao.UserDao;
import app.entity.Log;
import app.entity.Userinfo;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/admin/exportLogs")
public class ExportLogsServlet extends HttpServlet {
    private LogDao logDao = new LogDao();
    private UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        Userinfo user = userDao.get(userId);
        List<Log> logs = logDao.getUserLogs(userId);
        
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=user_logs.pdf");
        
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            
            // 添加标题
            Font titleFont = new Font(BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED), 18);
            document.add(new Paragraph(user.getUsername() + "的学习记录", titleFont));
            
            // 创建表格
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            
            // 添加表头
            Font headerFont = new Font(BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED), 12);
            table.addCell(new PdfPCell(new Phrase("时间", headerFont)));
            table.addCell(new PdfPCell(new Phrase("章节", headerFont)));
            table.addCell(new PdfPCell(new Phrase("内容标题", headerFont)));
            
            // 添加数据
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            Font contentFont = new Font(BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED), 10);
            for (Log log : logs) {
                table.addCell(new PdfPCell(new Phrase(sdf.format(log.getOptime()), contentFont)));
                table.addCell(new PdfPCell(new Phrase("第" + log.getContent().getChapter_id() + "章", contentFont)));
                table.addCell(new PdfPCell(new Phrase(log.getContent().getChapter_title(), contentFont)));
            }
            
            document.add(table);
            document.close();
            
        } catch (DocumentException e) {
            throw new ServletException("PDF生成失败", e);
        }
    }
} 