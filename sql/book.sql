CREATE TABLE `book` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `douban_id` bigint NOT NULL,
`ISBN` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
`book_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
`author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '作者',
`press` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '出版社',
`producer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '出品方',
`translator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '翻译者',
`series` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '系列',
`original_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '原作名',
`publish_time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '出版年',
`page` int DEFAULT NULL COMMENT '页数',
`price` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '定价',
`binding` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '装帧',
`douban_score` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '豆瓣评分',
`introduce` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '内容简介',
`tags` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标签',
`img_url` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图片链接',
PRIMARY KEY (`id`) USING BTREE,
UNIQUE KEY `isbn` (`ISBN`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21467 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;