/*
 Navicat Premium Data Transfer

 Source Server         : 本机mysql数据库
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : train_core

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 19/09/2019 00:16:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for train_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `train_dictionary`;
CREATE TABLE `train_dictionary`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典的key',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典的val',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) NULL DEFAULT 2 COMMENT '是否删除 1-是 2-否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of train_dictionary
-- ----------------------------
INSERT INTO `train_dictionary` VALUES (1174155790677913601, 'shortMessageVerificationCodeSwitch', '1', '短信验证码开关(1-开启 2-关闭)', '2019-09-18 10:58:49', NULL, 2);

-- ----------------------------
-- Table structure for train_interface
-- ----------------------------
DROP TABLE IF EXISTS `train_interface`;
CREATE TABLE `train_interface`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '接口路径',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '接口描述',
  `iden` int(1) NULL DEFAULT 2 COMMENT '权限标识(1-需要token 2-任意访问)',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) NULL DEFAULT 2 COMMENT '是否删除 1-是 2-否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '接口登记表(用于登记不需要token的接口)' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of train_interface
-- ----------------------------
INSERT INTO `train_interface` VALUES (1173852183931883522, '/apigateway/user-center/api/user-center/userRegister', '用户服务->用户注册接口-pc端', 2, '2019-09-18 09:14:12', NULL, 2);
INSERT INTO `train_interface` VALUES (1174129351626772481, '/apigateway/user-center/api/user-center/userLogin', '用户服务->用户登录接口-pc端', 2, '2019-09-18 09:13:46', NULL, 2);

-- ----------------------------
-- Table structure for train_login_log
-- ----------------------------
DROP TABLE IF EXISTS `train_login_log`;
CREATE TABLE `train_login_log`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `platform` int(1) NULL DEFAULT NULL COMMENT '登录平台（1-pc 2-app 3-小程序）',
  `ip_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录IP地址',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '登录时间',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) NULL DEFAULT 2 COMMENT '是否删除 1-是 2-否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '登录日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of train_login_log
-- ----------------------------
INSERT INTO `train_login_log` VALUES (1174149012959514625, 1173853273876299777, 1, '127.0.0.1', '2019-09-18 10:31:54', '2019-09-18 10:31:53', NULL, 2);
INSERT INTO `train_login_log` VALUES (1174149361665523714, 1173853273876299777, 1, '127.0.0.1', '2019-09-18 10:33:17', '2019-09-18 10:33:17', NULL, 2);
INSERT INTO `train_login_log` VALUES (1174150119970541569, 1173853273876299777, 1, '127.0.0.1', '2019-09-18 10:36:18', '2019-09-18 10:36:17', NULL, 2);
INSERT INTO `train_login_log` VALUES (1174150896483012609, 1173853273876299777, 1, '127.0.0.1', '2019-09-18 10:39:23', '2019-09-18 10:39:23', NULL, 2);
INSERT INTO `train_login_log` VALUES (1174151168630427649, 1173853273876299777, 1, '127.0.0.1', '2019-09-18 10:40:28', '2019-09-18 10:40:27', NULL, 2);
INSERT INTO `train_login_log` VALUES (1174164160826806274, 1173853273876299777, 1, '127.0.0.1', '2019-09-18 11:32:06', '2019-09-18 11:32:05', NULL, 2);
INSERT INTO `train_login_log` VALUES (1174314385309945857, 1173853273876299777, 1, '127.0.0.1', '2019-09-18 21:29:02', '2019-09-18 21:29:01', NULL, 2);
INSERT INTO `train_login_log` VALUES (1174315442043867137, 1173853273876299777, 1, '127.0.0.1', '2019-09-18 21:33:14', '2019-09-18 21:33:13', NULL, 2);
INSERT INTO `train_login_log` VALUES (1174315487606591490, 1173853273876299777, 1, '127.0.0.1', '2019-09-18 21:33:25', '2019-09-18 21:33:24', NULL, 2);
INSERT INTO `train_login_log` VALUES (1174315536973549569, 1173853273876299777, 1, '127.0.0.1', '2019-09-18 21:33:36', '2019-09-18 21:33:36', NULL, 2);

-- ----------------------------
-- Table structure for train_sms_model
-- ----------------------------
DROP TABLE IF EXISTS `train_sms_model`;
CREATE TABLE `train_sms_model`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `type` int(1) NOT NULL COMMENT '短信类型（1-注册 2-登录  3-修改密码 4-修改手机号\r\n短信类型有且唯一 ，且类型依据实际需求递增\r\n）',
  `sign_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '短信签名',
  `template_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '短信模板码',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) NULL DEFAULT 2 COMMENT '是否删除 1-是 2-否',
  PRIMARY KEY (`id`, `type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '短信模板表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for train_user
-- ----------------------------
DROP TABLE IF EXISTS `train_user`;
CREATE TABLE `train_user`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `avatar` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '用户头像',
  `gender` int(1) NULL DEFAULT 1 COMMENT '用户性别(1-男 2-女)',
  `age` int(8) NULL DEFAULT NULL COMMENT '用户年龄',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) NULL DEFAULT 2 COMMENT '是否删除 1-是 2-否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of train_user
-- ----------------------------
INSERT INTO `train_user` VALUES (1173853273876299777, '13163775346', 'zhanglei8552', NULL, NULL, 1, NULL, '2019-09-17 14:56:44', NULL, 2);

-- ----------------------------
-- Table structure for train_user_thirdparty
-- ----------------------------
DROP TABLE IF EXISTS `train_user_thirdparty`;
CREATE TABLE `train_user_thirdparty`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `platform` int(1) NOT NULL DEFAULT 2 COMMENT '平台类型(1-qq 2-微信)',
  `union_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '第三方平台的用户标识',
  `gender` int(1) NULL DEFAULT NULL COMMENT '第三方用户性别(0-未知 1-男 2女)',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '第三方用户昵称',
  `age` int(8) NULL DEFAULT NULL COMMENT '第三方用户年龄',
  `avatar` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '第三方用户头像',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) NULL DEFAULT 2 COMMENT '是否删除 1-是 2-否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户第三方信息表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
