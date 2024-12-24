<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>个人设置</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/study.css">
    <script src="${pageContext.request.contextPath}/js/message.js"></script>
</head>
<body>
    <div class="study-container">
        <div class="study-header">
            <h2>个人设置</h2>
            <div class="nav-links">
                <a href="${pageContext.request.contextPath}/user/study">返回学习</a>
            </div>
        </div>

        <div class="profile-content">
            <c:if test="${not empty message}">
                <div class="message ${messageType}">${message}</div>
            </c:if>

            <div class="profile-section">
                <h3>修改头像</h3>
                <form action="${pageContext.request.contextPath}/user/updateAvatar" method="post" enctype="multipart/form-data">
                    <div class="current-avatar">
                        <img src="${pageContext.request.contextPath}${sessionScope.user.head_image}" alt="当前头像" class="avatar-preview">
                    </div>
                    <div class="form-group">
                        <label for="avatar">选择新头像：</label>
                        <input type="file" id="avatar" name="avatar" accept="image/*" required>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn-submit">更新头像</button>
                    </div>
                </form>
            </div>

            <div class="profile-section">
                <h3>修改密码</h3>
                <form action="${pageContext.request.contextPath}/user/updatePassword" method="post">
                    <div class="form-group">
                        <label for="oldPassword">当前密码：</label>
                        <input type="password" id="oldPassword" name="oldPassword" required>
                    </div>
                    <div class="form-group">
                        <label for="newPassword">新密码：</label>
                        <input type="password" id="newPassword" name="newPassword" required>
                    </div>
                    <div class="form-group">
                        <label for="confirmPassword">确认新密码：</label>
                        <input type="password" id="confirmPassword" name="confirmPassword" required>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn-submit">更新密码</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html> 