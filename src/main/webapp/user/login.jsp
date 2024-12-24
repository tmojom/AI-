<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/js/message.js"></script>
</head>
<body>
    <div class="login-container">
        <h2>用户登录</h2>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="form-group">
                <label for="username">用户名：</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">密码：</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
                <button type="submit">登录</button>
                <a href="${pageContext.request.contextPath}/user/register.jsp">注册新用户</a>
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