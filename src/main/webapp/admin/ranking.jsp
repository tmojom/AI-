<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>学习排行榜</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    <style>
        .ranking-section {
            margin-bottom: 30px;
            background: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
        .ranking-section h3 {
            color: #333;
            margin-bottom: 15px;
            padding-bottom: 10px;
            border-bottom: 2px solid #eee;
        }
        .rank-number {
            font-weight: bold;
            color: #ff6b6b;
        }
        .study-time {
            color: #666;
        }
    </style>
</head>
<body>
    <div class="admin-container">
        <div class="admin-header">
            <h2>学习排行榜</h2>
            <div class="admin-nav">
                <a href="${pageContext.request.contextPath}/admin/userList">用户管理</a>
                <a href="${pageContext.request.contextPath}/admin/contentList">内容管理</a>
                <a href="${pageContext.request.contextPath}/admin/ranking">学习排行榜</a>
                <a href="${pageContext.request.contextPath}/logout">退出登录</a>
            </div>
        </div>
        
        <div class="admin-content">
            <div class="ranking-section">
                <h3>🏆 学习内容完成最多的用户</h3>
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>排名</th>
                            <th>用户名</th>
                            <th>完成章节数</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${topUsers}" var="user" varStatus="status">
                            <tr>
                                <td class="rank-number">${status.index + 1}</td>
                                <td>${user.username}</td>
                                <td>${user.completedChapters} 章</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="ranking-section">
                <h3>📚 最近学习的用户</h3>
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>用户名</th>
                            <th>最后学习内容</th>
                            <th>学习时间</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${recentUsers}" var="user">
                            <tr>
                                <td>${user.username}</td>
                                <td>${user.lastChapter}</td>
                                <td class="study-time">
                                    <fmt:formatDate value="${user.lastStudyTime}" 
                                                  pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html> 