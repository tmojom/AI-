<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${content.chapter_title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/study.css">
</head>
<body>
    <div class="study-container">
        <div class="study-header">
            <h2>第${content.chapter_id}章：${content.chapter_title}</h2>
            <div class="nav-links">
                <a href="${pageContext.request.contextPath}/user/study">返回课程列表</a>
            </div>
        </div>

        <div class="progress-bar">
            <div class="progress" style="width: '${progress}%'">
                <span class="progress-text">${progress}% 完成</span>
            </div>
        </div>

        <div class="study-timer">
            学习时长：<span id="timer">00:00:00</span>
        </div>

        <div class="learn-content">
            <div class="chapter-image">
                <img src="${pageContext.request.contextPath}${content.chapter_image}" alt="章节图片">
            </div>
            
            <div class="chapter-text">
                ${content.chapter_text}
            </div>

            <div class="chapter-navigation">
                <c:if test="${prevChapter != null}">
                    <a href="${pageContext.request.contextPath}/user/learn?id=${prevChapter.id}" class="btn-nav btn-prev">
                        ← 上一章：${prevChapter.chapter_title}
                    </a>
                </c:if>
                <c:if test="${nextChapter != null}">
                    <a href="${pageContext.request.contextPath}/user/learn?id=${nextChapter.id}" class="btn-nav btn-next">
                        下一章：${nextChapter.chapter_title} →
                    </a>
                </c:if>
            </div>

            <div class="learn-actions">
                <form action="${pageContext.request.contextPath}/user/completeLearn" method="post">
                    <input type="hidden" name="contentId" value="${content.id}">
                    <input type="hidden" name="studyTime" id="studyTimeInput">
                    <button type="submit" class="btn-complete">完成学习</button>
                </form>
            </div>
        </div>
    </div>

    <script>
        let startTime = new Date().getTime();
        let timerElement = document.getElementById('timer');
        let studyTimeInput = document.getElementById('studyTimeInput');

        function updateTimer() {
            let currentTime = new Date().getTime();
            let diff = currentTime - startTime;
            
            let hours = Math.floor(diff / (1000 * 60 * 60));
            let minutes = Math.floor((diff % (1000 * 60 * 60)) / (1000 * 60));
            let seconds = Math.floor((diff % (1000 * 60)) / 1000);
            
            hours = hours.toString().padStart(2, '0');
            minutes = minutes.toString().padStart(2, '0');
            seconds = seconds.toString().padStart(2, '0');
            
            timerElement.textContent = `${hours}:${minutes}:${seconds}`;
            studyTimeInput.value = diff;
        }

        setInterval(updateTimer, 1000);

        window.onbeforeunload = function() {
            localStorage.setItem('studyTime_${content.id}', 
                new Date().getTime() - startTime);
        };
    </script>
</body>
</html> 