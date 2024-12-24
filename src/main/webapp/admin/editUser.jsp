<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>编辑用户</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    <script src="${pageContext.request.contextPath}/js/message.js"></script>
</head>
<body>
    <div class="admin-container">
        <div class="admin-header">
            <h2>编辑用户</h2>
            <div class="admin-nav">
                <a href="${pageContext.request.contextPath}/admin/userList">返回用户列表</a>
            </div>
        </div>
        
        <div class="admin-content">
            <c:if test="${not empty message}">
                <div class="message ${messageType}">${message}</div>
            </c:if>
            
            <form action="${pageContext.request.contextPath}/admin/updateUser" method="post" class="edit-form">
                <input type="hidden" name="id" value="${user.id}">
                
                <div class="form-group">
                    <label>用户名：${user.username}</label>
                </div>
                
                <div class="form-group">
                    <label for="role">角色：</label>
                    <select id="role" name="role" required>
                        <option value="admin" ${user.role == 'admin' ? 'selected' : ''}>管理员</option>
                        <option value="user" ${user.role == 'user' ? 'selected' : ''}>普通用户</option>
                        <option value="hacker" ${user.role == 'hacker' ? 'selected' : ''}>黑客</option>
                    </select>
                </div>
                
                <div class="form-group">
                    <label>当前头像：</label>
                    <c:if test="${not empty user.head_image}">
                        <img src="${pageContext.request.contextPath}${user.head_image}" alt="头像" class="avatar">
                        <button type="button" onclick="deleteAvatar('${user.id}')" class="btn btn-delete">删除头像</button>
                    </c:if>
                </div>
                
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">保存修改</button>
                </div>
            </form>
        </div>
    </div>

    <script>
        function deleteAvatar(userId) {
            if (confirm('确定要删除用户头像吗？')) {
                window.location.href = '${pageContext.request.contextPath}/admin/deleteAvatar?id=' + userId;
            }
        }
    </script>
</body>
</html> 