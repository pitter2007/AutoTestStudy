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

 Date: 21/02/2019 16:09:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张三丰', 100, '男');
INSERT INTO `user` VALUES (2, '张无忌', 28, '男');
INSERT INTO `user` VALUES (3, '周芷若', 22, '女');
INSERT INTO `user` VALUES (4, '赵敏', 20, '女');
INSERT INTO `user` VALUES (6, '王保保', 25, '男');
INSERT INTO `user` VALUES (7, '殷离', 26, '女');
INSERT INTO `user` VALUES (10, '我是谁', 21, '男');
INSERT INTO `user` VALUES (11, '秋水', 21, '女');

SET FOREIGN_KEY_CHECKS = 1;
