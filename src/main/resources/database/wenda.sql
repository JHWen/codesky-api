/*
 Navicat Premium Data Transfer

 Source Server         : localhost_mysql
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : wenda

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 07/05/2019 14:43:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer`  (
  `id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '回答内容',
  `excerpt` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '回答内容摘要',
  `comment_count` int(11) NOT NULL DEFAULT 0 COMMENT '回答评论数',
  `voteup_count` int(11) NOT NULL DEFAULT 0 COMMENT '回答点赞数',
  `is_anonymously` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否匿名回答',
  `author_id` bigint(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '回答者id',
  `question_id` bigint(11) UNSIGNED NULL DEFAULT NULL COMMENT '问题id',
  `gmt_create` datetime(0) NOT NULL COMMENT '回答创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '回答修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pk_answer_author_to_user`(`author_id`) USING BTREE,
  INDEX `pk_answer_to_question`(`question_id`) USING BTREE,
  CONSTRAINT `pk_answer_author_to_user` FOREIGN KEY (`author_id`) REFERENCES `user_authentication_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `pk_answer_to_question` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题内容',
  `answer_count` int(11) NOT NULL DEFAULT 0 COMMENT '问题的回答数',
  `comment_count` int(11) NOT NULL DEFAULT 0 COMMENT '问题的评论数',
  `follower_count` int(11) NOT NULL DEFAULT 0 COMMENT '问题的关注人数',
  `author_id` bigint(11) UNSIGNED NULL DEFAULT 0 COMMENT '提问者的id，匿名id为0',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '上一次的更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pk_question_to_user_authentication_info`(`author_id`) USING BTREE,
  CONSTRAINT `pk_question_to_user_authentication_info` FOREIGN KEY (`author_id`) REFERENCES `user_authentication_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '问题表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `role_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user_addition_info
-- ----------------------------
DROP TABLE IF EXISTS `user_addition_info`;
CREATE TABLE `user_addition_info`  (
  `id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `user_id` bigint(11) UNSIGNED NULL DEFAULT NULL COMMENT '关联用户认证表外键',
  `gender` tinyint(2) NOT NULL DEFAULT 1 COMMENT '性别',
  `avatar_url` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户头像url',
  `headline` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '一句话介绍',
  `business` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '所在行业',
  `gmt_created` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pk_user_additional_info_to_authentication_info`(`user_id`) USING BTREE,
  CONSTRAINT `pk_user_additional_info_to_authentication_info` FOREIGN KEY (`user_id`) REFERENCES `user_authentication_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户个人描述信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_addition_info
-- ----------------------------
INSERT INTO `user_addition_info` VALUES (9, 'codesky', 9, 1, 'https://images.nowcoder.com/images/20170615/1347798_1497491166458_8E7B0656F73A23F6ECE12F2DAD72C5A7@0e_100w_100h_0c_1i_1o_90Q_1x', '这是一句话介绍', '计算机软件', '2019-05-05 17:26:55', '2019-05-05 17:26:55');
INSERT INTO `user_addition_info` VALUES (10, 'helloworld', 10, 1, 'https://images.nowcoder.com/images/20170615/1347798_1497491166458_8E7B0656F73A23F6ECE12F2DAD72C5A7@0e_100w_100h_0c_1i_1o_90Q_1x', '这是一句话介绍', '计算机软件', '2019-05-05 20:55:12', '2019-05-05 20:55:12');

-- ----------------------------
-- Table structure for user_authentication_info
-- ----------------------------
DROP TABLE IF EXISTS `user_authentication_info`;
CREATE TABLE `user_authentication_info`  (
  `id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `is_account_non_expired` tinyint(1) NOT NULL DEFAULT 1 COMMENT '账户未过期标志',
  `is_account_non_locked` tinyint(1) NOT NULL DEFAULT 1 COMMENT '账户未锁定标志',
  `is_credentials_non_expired` tinyint(1) NOT NULL DEFAULT 1 COMMENT '密码未过期标志',
  `is_enabled` tinyint(1) NOT NULL DEFAULT 1 COMMENT '账户可用标志',
  `gmt_create` datetime(0) NOT NULL COMMENT '账户创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '账户的最近一次修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username`) USING BTREE COMMENT '用户名唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户登录认证相关信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_authentication_info
-- ----------------------------
INSERT INTO `user_authentication_info` VALUES (9, 'codesky', '$2a$10$IshJ45BcLckOaTYCbNJXsezfFXBdttjZaAkjESkwYd0BIvqU1mvcK', 1, 1, 1, 1, '2019-05-05 17:26:55', '2019-05-05 17:26:55');
INSERT INTO `user_authentication_info` VALUES (10, 'helloworld', '$2a$10$P9ZbGyi3nUxhOBux36cho.q0U0hX0eGuu7IrK5o90/yCkyXITNe/K', 1, 1, 1, 1, '2019-05-05 20:55:12', '2019-05-05 20:55:12');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '用户id',
  `role_id` bigint(20) UNSIGNED NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pk_user_role_to_user`(`user_id`) USING BTREE,
  INDEX `pk_user_role_to_role`(`role_id`) USING BTREE,
  CONSTRAINT `pk_user_role_to_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `pk_user_role_to_user` FOREIGN KEY (`user_id`) REFERENCES `user_authentication_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色授权表' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
