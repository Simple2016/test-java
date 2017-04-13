/*
Navicat MySQL Data Transfer

Source Server         : 1
Source Server Version : 50548
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50548
File Encoding         : 65001

Date: 2016-12-09 17:26:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `group`
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group` (
  `GroupId` int(11) NOT NULL AUTO_INCREMENT,
  `GroupName` varchar(255) CHARACTER SET utf8 NOT NULL,
  `CreateTime` datetime DEFAULT NULL,
  `Author` int(11) DEFAULT NULL COMMENT '保存user的ID',
  PRIMARY KEY (`GroupId`,`GroupName`),
  UNIQUE KEY `GroupName_2` (`GroupName`),
  KEY `GroupName` (`GroupName`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of group
-- ----------------------------
INSERT INTO `group` VALUES ('1', 'test', '2016-11-22 15:45:01', null);
INSERT INTO `group` VALUES ('7', 'test2', '2016-11-22 16:12:03', null);
INSERT INTO `group` VALUES ('10', 'test3', '2016-11-22 16:12:49', null);
INSERT INTO `group` VALUES ('11', 'test4', '2016-12-06 16:01:54', null);
INSERT INTO `group` VALUES ('14', 'mvn', '2016-12-09 14:22:15', null);
