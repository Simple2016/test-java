/*
Navicat MySQL Data Transfer

Source Server         : 1
Source Server Version : 50548
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50548
File Encoding         : 65001

Date: 2016-12-09 17:26:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'lqw', '123', '2');
INSERT INTO `user` VALUES ('2', 'manager', '123', '2');
INSERT INTO `user` VALUES ('4', '1', '1', '1');
INSERT INTO `user` VALUES ('5', '1', '1', '2');
INSERT INTO `user` VALUES ('6', 'manager1', '123', '1');
