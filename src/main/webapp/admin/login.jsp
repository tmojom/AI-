<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="login-container">
        <h2>管理员登录</h2>
        <form action="${pageContext.request.contextPath}/admin/login" method="post">
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
            </div>
            <div class="form-group">
                <a href="${pageContext.request.contextPath}/">返回首页</a>
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