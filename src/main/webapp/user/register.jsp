<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/js/message.js"></script>
</head>
<body>
    <div class="login-container">
        <h2>用户注册</h2>
        <form action="${pageContext.request.contextPath}/register" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="username">用户名：</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">密码：</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
                <label for="confirmPassword">确认密码：</label>
                <input type="password" id="confirmPassword" name="confirmPassword" required>
            </div>
            <div class="form-group">
                <label for="avatar">头像：</label>
                <input type="file" id="avatar" name="avatar" accept="image/*" required>
            </div>
            <div class="form-group">
                <button type="submit">注册</button>
                <a href="${pageContext.request.contextPath}/user/login.jsp">返回登录</a>
            </div>
            <% if (request.getAttribute("error") != null) { %>
                <div class="error-message">
                    <%= request.getAttribute("error") %>
                </div>
            <% } %>
        </form>
    </div>
</body>
</html> 