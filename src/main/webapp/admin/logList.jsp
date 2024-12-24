<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>学习日志</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
</head>
<body>
    <div class="admin-container">
        <div class="admin-header">
            <h2>学习日志 - ${username}</h2>
            <div class="admin-nav">
                <a href="${pageContext.request.contextPath}/admin/userList">用户管理</a>
                <a href="${pageContext.request.contextPath}/admin/contentList">内容管理</a>
                <a href="${pageContext.request.contextPath}/admin/ranking">学习排行榜</a>
                <a href="${pageContext.request.contextPath}/logout">退出登录</a>
            </div>
        </div>
        
        <div class="admin-content">
            <div class="action-bar">
                <a href="${pageContext.request.contextPath}/admin/exportLogs?id=${userId}" class="btn btn-primary">导出PDF</a>
            </div>

            <table class="data-table">
                <thead>
                    <tr>
                        <th>时间</th>
                        <th>章节</th>
                        <th>内容标题</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${logs}" var="log">
                        <tr>
                            <td>
                                <fmt:formatDate value="${log.optime}" pattern="yyyy年MM月dd日 HH:mm:ss"/>
                            </td>
                            <td>第${log.content.chapter_id}章</td>
                            <td>${log.content.chapter_title}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html> 