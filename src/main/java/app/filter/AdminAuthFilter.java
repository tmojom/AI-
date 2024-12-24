package app.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminAuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        
        // 允许访问登录页面和处理登录请求
        String requestURI = httpRequest.getRequestURI();
        if (requestURI.endsWith("/admin/login") || requestURI.endsWith("/admin/login.jsp")) {
            chain.doFilter(request, response);
            return;
        }
        
        // 验证管理员身份
        if (session != null && "admin".equals(session.getAttribute("role"))) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/admin/login.jsp");
        }
    }
} 