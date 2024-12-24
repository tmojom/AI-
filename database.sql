-- 创建数据库
CREATE DATABASE IF NOT EXISTS user DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE user;

-- 用户信息表
CREATE TABLE userinfo (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    username VARCHAR(20),
    password VARCHAR(32),
    role VARCHAR(10),
    head_image VARCHAR(255),
    stu110599 VARCHAR(20)
);

-- 内容表
CREATE TABLE content (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    chapter_id INT,
    chapter_title VARCHAR(255),
    chapter_text MEDIUMTEXT,
    chapter_image VARCHAR(255),
    hits INT DEFAULT 0
);

-- 日志表
CREATE TABLE log (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    user_id INT,
    content_id INT,
    optime DATETIME
);


-- 插入验证记录
INSERT INTO userinfo (id, username, password, role, stu110599) 
VALUES (1, '张三李四', '202111070102', 'ZSLS', 'zsls');
  </rewritten_file> 