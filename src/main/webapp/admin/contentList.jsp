<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>内容管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    <script src="${pageContext.request.contextPath}/js/message.js"></script>
</head>
<body>
    <div class="admin-container">
        <div class="admin-header">
            <h2>内容管理</h2>
            <div class="admin-nav">
                <a href="${pageContext.request.contextPath}/admin/userList">用户管理</a>
                <a href="${pageContext.request.contextPath}/admin/contentList">内容管理</a>
                <a href="${pageContext.request.contextPath}/admin/ranking">学习排行榜</a>
                <a href="${pageContext.request.contextPath}/logout">退出登录</a>
            </div>
        </div>
        
        <div class="admin-content">
            <div class="action-bar">
                <a href="${pageContext.request.contextPath}/admin/addContent" class="btn btn-primary">添加新内容</a>
            </div>

            <table class="data-table">
                <thead>
                    <tr>
                        <th>章节</th>
                        <th>标题</th>
                        <th>封面</th>
                        <th>点击量</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${contents}" var="content">
                        <tr>
                            <td>第${content.chapter_id}章</td>
                            <td>${content.chapter_title}</td>
                            <td>
                                <c:if test="${not empty content.chapter_image}">
                                    <img src="${pageContext.request.contextPath}${content.chapter_image}" alt="封面" class="cover-image">
                                </c:if>
                            </td>
                            <td>${content.hits}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/admin/editContent?id=${content.id}" class="btn btn-edit">编辑</a>
                                <button onclick="confirmDeleteContent('${content.id}')" class="btn btn-delete">删除</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <script>
        function confirmDeleteContent(contentId) {
            if (confirm('确定要删除这个内容吗？')) {
                window.location.href = '${pageContext.request.contextPath}/admin/deleteContent?id=' + contentId;
            }
        }
    </script>
</body>
</html> 