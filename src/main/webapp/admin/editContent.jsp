<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>编辑学习内容</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/content.css">
    <script src="${pageContext.request.contextPath}/js/message.js"></script>
</head>
<body>
    <div class="admin-container">
        <div class="admin-header">
            <h2>编辑学习内容</h2>
            <div class="admin-nav">
                <a href="${pageContext.request.contextPath}/admin/contentList">返回内容列表</a>
            </div>
        </div>

        <div class="admin-content">
            <form action="${pageContext.request.contextPath}/admin/editContent" method="post" 
                  enctype="multipart/form-data" class="content-form">
                <input type="hidden" name="id" value="${content.id}">
                
                <div class="form-group">
                    <label for="chapter_id">章节编号：</label>
                    <input type="number" id="chapter_id" name="chapter_id" 
                           value="${content.chapter_id}" required>
                </div>

                <div class="form-group">
                    <label for="chapter_title">章节标题：</label>
                    <input type="text" id="chapter_title" name="chapter_title" 
                           value="${content.chapter_title}" required>
                </div>

                <div class="form-group">
                    <label for="chapter_text">章节内容：</label>
                    <textarea id="chapter_text" name="chapter_text" rows="10" required>${content.chapter_text}</textarea>
                </div>

                <div class="form-group">
                    <label>当前封面：</label>
                    <c:if test="${not empty content.chapter_image}">
                        <img src="${pageContext.request.contextPath}${content.chapter_image}" 
                             alt="当前封面" class="cover-preview">
                    </c:if>
                </div>

                <div class="form-group">
                    <label for="chapter_image">更新封面：</label>
                    <input type="file" id="chapter_image" name="chapter_image" accept="image/*">
                </div>
                <div class="form-group">
                    <button type="submit" class="btn-submit">保存修改</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html> 