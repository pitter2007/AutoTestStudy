/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : course

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 28/02/2019 18:00:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for addUserCase
-- ----------------------------
DROP TABLE IF EXISTS `addUserCase`;
CREATE TABLE `addUserCase`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isDelete` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `expected` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of addUserCase
-- ----------------------------
INSERT INTO `addUserCase` VALUES ('1', 'zhao9', 'zhaozhao', '0', '20', '1', '0', 'true');

-- ----------------------------
-- Table structure for getUserInfoCase
-- ----------------------------
DROP TABLE IF EXISTS `getUserInfoCase`;
CREATE TABLE `getUserInfoCase`  (
  `id` int(11) NOT NULL,
  `userId` int(11) NULL DEFAULT NULL,
  `expected` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of getUserInfoCase
-- ----------------------------
INSERT INTO `getUserInfoCase` VALUES (1, 1, 'getUserInfo');

-- ----------------------------
-- Table structure for getUserListCase
-- ----------------------------
DROP TABLE IF EXISTS `getUserListCase`;
CREATE TABLE `getUserListCase`  (
  `id` int(11) NOT NULL,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `expected` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of getUserListCase
-- ----------------------------
INSERT INTO `getUserListCase` VALUES (1, NULL, NULL, '0', 'getUserList');

-- ----------------------------
-- Table structure for loginCase
-- ----------------------------
DROP TABLE IF EXISTS `loginCase`;
CREATE TABLE `loginCase`  (
  `id` int(11) NOT NULL,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `expected` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of loginCase
-- ----------------------------
INSERT INTO `loginCase` VALUES (1, 'zhangsan', '123456', 'true');
INSERT INTO `loginCase` VALUES (2, 'zhangsanerror', '123', 'false');

-- ----------------------------
-- Table structure for updateUserInfoCase
-- ----------------------------
DROP TABLE IF EXISTS `updateUserInfoCase`;
CREATE TABLE `updateUserInfoCase`  (
  `id` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isDelete` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `expected` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of updateUserInfoCase
-- ----------------------------
INSERT INTO `updateUserInfoCase` VALUES (1, 2, 'hahaha', NULL, NULL, NULL, NULL, 'getUpdateUserInfo');
INSERT INTO `updateUserInfoCase` VALUES (2, 8, NULL, NULL, NULL, NULL, '1', 'getUpdateUserInfo');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限',
  `isDelete` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'zhangsan', '123456', '25', '1', '0', '0');
INSERT INTO `user` VALUES (2, 'zhaoming', '123456', '18', '0', '1', '0');
INSERT INTO `user` VALUES (3, 'zhouzhirou', '123456', '16', '0', '1', '0');
INSERT INTO `user` VALUES (4, 'zhangwuji', '123456', '26', '1', '1', '1');
INSERT INTO `user` VALUES (5, 'xiaozhao', '123456', '26', '0', '1', '1');
INSERT INTO `user` VALUES (6, 'yinli', '123456', '26', '0', '1', '0');

SET FOREIGN_KEY_CHECKS = 1;
