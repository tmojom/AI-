function handleMessages() {
    // 获取所有消息元素
    const messages = document.querySelectorAll('.message');
    
    // 为每个消息设置自动隐藏
    messages.forEach(message => {
        if (message) {
            // 5秒后开始淡出
            setTimeout(() => {
                message.classList.add('fade-out');
                // 淡出动画完成后移除元素
                setTimeout(() => {
                    message.remove();
                    // 使用完整的上下文路径
                    const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf('/', 1));
                    fetch(contextPath + '/clearMessage', { method: 'POST' });
                }, 500);
            }, 5000);
        }
    });
}

// 页面加载完成后执行
document.addEventListener('DOMContentLoaded', handleMessages); 