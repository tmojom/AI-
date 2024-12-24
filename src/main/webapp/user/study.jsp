<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学习中心</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/study.css">
</head>
<body>
    <div class="study-container">
        <div class="study-header">
            <div class="user-info">
                <img src="${pageContext.request.contextPath}${sessionScope.user.head_image}" alt="头像" class="user-avatar">
                <span class="username">${sessionScope.user.username}</span>
            </div>
            <div class="nav-links">
                <a href="${pageContext.request.contextPath}/user/profile.jsp">个人设置</a>
                <a href="${pageContext.request.contextPath}/user/myLogs">学习记录</a>
                <a href="${pageContext.request.contextPath}/logout">退出登录</a>
            </div>
        </div>

        <div class="study-summary">
            <div class="summary-item">
                <span class="label">总课程数：</span>
                <span class="value">${totalLessons}</span>
            </div>
            <div class="summary-item">
                <span class="label">已学习：</span>
                <span class="value">${completedLessons}</span>
            </div>
            <div class="summary-item">
                <span class="label">未学习：</span>
                <span class="value">${totalLessons - completedLessons}</span>
            </div>
        </div>

        <div class="content-list">
            <h2>课程列表</h2>
            <c:forEach items="${contents}" var="content">
                <div class="content-item ${content.learned ? 'learned' : ''}">
                    <div class="content-image">
                        <img src="${pageContext.request.contextPath}${content.chapter_image}" alt="课程封面">
                    </div>
                    <div class="content-info">
                        <h3>第${content.chapter_id}章：${content.chapter_title}</h3>
                        <p class="status">
                            <c:if test="${content.learned}">
                                <span class="learned-tag">已学习</span>
                            </c:if>
                            <c:if test="${!content.learned}">
                                <span class="unlearned-tag">未学习</span>
                            </c:if>
                        </p>
                        <a href="${pageContext.request.contextPath}/user/learn?id=${content.id}" 
                           class="btn-learn">开始学习</a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html> 