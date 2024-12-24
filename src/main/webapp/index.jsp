<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AI教育平台</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        .welcome-container {
            text-align: center;
            margin-top: 100px;
        }
        
        .role-buttons {
            margin-top: 50px;
        }
        
        .role-button {
            display: inline-block;
            padding: 15px 30px;
            margin: 0 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        
        .role-button:hover {
            background-color: #45a049;
        }
        
        .role-button.admin {
            background-color: #2196F3;
        }
        
        .role-button.admin:hover {
            background-color: #1976D2;
        }
    </style>
</head>
<body>
    <div class="welcome-container">
        <h1>欢迎来到AI教育平台</h1>
        <div class="role-buttons">
            <a href="${pageContext.request.contextPath}/admin/login.jsp" class="role-button admin">管理员登录</a>
            <a href="${pageContext.request.contextPath}/user/login.jsp" class="role-button">用户登录</a>
        </div>
    </div>
</body>
</html> 