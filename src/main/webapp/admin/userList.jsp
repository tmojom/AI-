<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    <script src="${pageContext.request.contextPath}/js/message.js"></script>
</head>
<body>
    <div class="admin-container">
        <div class="admin-header">
            <h2>用户管理</h2>
            <div class="admin-nav">
                <a href="${pageContext.request.contextPath}/admin/userList">用户管理</a>
                <a href="${pageContext.request.contextPath}/admin/contentList">内容管理</a>
                <a href="${pageContext.request.contextPath}/admin/ranking">学习排行榜</a>
                <a href="${pageContext.request.contextPath}/logout">退出登录</a>
            </div>
        </div>
        
        <div class="admin-content">
            <c:if test="${not empty message}">
                <div class="message ${messageType}">${message}</div>
            </c:if>
            
            <table class="data-table">
                <thead>
                    <tr>
                        <th>用户名</th>
                        <th>角色</th>
                        <th>头像</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${users}" var="user">
                        <c:if test="${user.role ne 'admin'}">
                            <tr>
                                <td>${user.username}</td>
                                <td>${user.role}</td>
                                <td>
                                    <c:if test="${not empty user.head_image}">
                                        <img src="${pageContext.request.contextPath}${user.head_image}" alt="头像" class="avatar">
                                    </c:if>
                                </td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/admin/editUser?id=${user.id}" class="btn btn-edit">编辑</a>
                                    <button onclick="confirmDelete('${user.id}')" class="btn btn-delete">删除</button>
                                    <a href="${pageContext.request.contextPath}/admin/userLogs?id=${user.id}" class="btn btn-view">查看日志</a>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <script>
        function confirmDelete(userId) {
            if (confirm('确定要删除这个用户吗？')) {
                window.location.href = '${pageContext.request.contextPath}/admin/deleteUser?id=' + userId;
            }
        }
    </script>
</body>
</html> 