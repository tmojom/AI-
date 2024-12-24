<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>我的学习记录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/study.css">
</head>
<body>
    <div class="study-container">
        <div class="study-header">
            <h2>我的学习记录</h2>
            <div class="nav-links">
                <a href="${pageContext.request.contextPath}/user/study">返回学习</a>
                <a href="${pageContext.request.contextPath}/user/exportMyLogs" class="btn-export">导出PDF</a>
            </div>
        </div>

        <div class="study-content">
            <div class="study-summary">
                <div class="summary-item">
                    <span class="label">已学习章节：</span>
                    <span class="value">${completedLessons}</span>
                </div>
                <div class="summary-item">
                    <span class="label">最近学习时间：</span>
                    <span class="value">
                        <c:if test="${not empty lastStudyTime}">
                            <fmt:formatDate value="${lastStudyTime}" pattern="yyyy年MM月dd日 HH:mm:ss"/>
                        </c:if>
                    </span>
                </div>
            </div>

            <div class="log-list">
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>学习时间</th>
                            <th>章节</th>
                            <th>内容标题</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${logs}" var="log">
                            <tr>
                                <td>
                                    <fmt:formatDate value="${log.optime}" pattern="yyyy年MM月dd日 HH:mm:ss"/>
                                </td>
                                <td>第${log.content.chapter_id}章</td>
                                <td>${log.content.chapter_title}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html> 