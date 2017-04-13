/*
Navicat MySQL Data Transfer

Source Server         : 1
Source Server Version : 50548
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50548
File Encoding         : 65001

Date: 2016-12-09 17:25:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `ArticleldId` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(50) DEFAULT NULL,
  `Content` varchar(30) DEFAULT NULL,
  `Detail` text,
  `Group` text,
  `CreateTime` datetime DEFAULT NULL,
  `ModifyTime` datetime DEFAULT NULL,
  `Author` varchar(10) DEFAULT NULL,
  `Modifier` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ArticleldId`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('6', '国泰民安', '国泰民安\"adsf\"', '国泰民安<div>\"adsf\"</div>', ' [{\"groupId\":7,\"groupName\":\"test2\",\"createTime\":\"Nov 22, 2016 4:12:03 PM\"},{\"groupId\":10,\"groupName\":\"test3\",\"createTime\":\"Nov 22, 2016 4:12:49 PM\"}]\r\n', '2016-02-20 23:45:48', '2016-11-17 14:17:04', '李庆伟', '');
INSERT INTO `article` VALUES ('7', '测试alert', '国泰民安<script>alert(0)</scrip...', '国泰民安<script>alert(0)</script>', ' [{\"groupId\":7,\"groupName\":\"test2\",\"createTime\":\"Nov 22, 2016 4:12:03 PM\"},{\"groupId\":10,\"groupName\":\"test3\",\"createTime\":\"Nov 22, 2016 4:12:49 PM\"}]\r\n', '2016-02-20 23:46:37', '2016-11-17 13:54:50', '李庆伟', '');
INSERT INTO `article` VALUES ('9', '测试script', '呵呵呵呵呵呵呵呵呵var d=document;var...', '呵呵呵呵呵呵呵呵呵<script>var d=document;var i=d.createElement(\"img\");i.setAttribute(\"src\",\"http://f.51240.com/file/zaixianwangyebianji/kindeditor-4.1.7/plugins/emoticons/images/1.gif\");d.body.appendChild(i);alert(d.cookie)</script>', ' [{\"groupId\":7,\"groupName\":\"test2\",\"createTime\":\"Nov 22, 2016 4:12:03 PM\"},{\"groupId\":10,\"groupName\":\"test3\",\"createTime\":\"Nov 22, 2016 4:12:49 PM\"}]\r\n', '2016-02-20 23:54:49', '2016-11-17 14:51:05', '李庆伟', null);
INSERT INTO `article` VALUES ('10', '成功', '', '成功', ' [{\"groupId\":7,\"groupName\":\"test2\",\"createTime\":\"Nov 22, 2016 4:12:03 PM\"},{\"groupId\":10,\"groupName\":\"test3\",\"createTime\":\"Nov 22, 2016 4:12:49 PM\"}]\r\n', '2016-02-21 11:41:14', '2016-11-10 09:59:19', '李庆伟', null);
INSERT INTO `article` VALUES ('17', 'title', 'contentcontentcontentconten...', 'contentcontentcontentcontentcontent', '[{\"groupId\":10,\"groupName\":\"test3\",\"createTime\":\"Nov 22, 2016 4:12:49 PM\"}]', '2016-11-16 15:52:14', '2016-12-09 14:12:17', null, null);
INSERT INTO `article` VALUES ('18', 'title', '11', '11', '[{\"groupId\":7,\"groupName\":\"test2\",\"createTime\":\"Nov 22, 2016 4:12:03 PM\"}]', '2016-11-22 14:45:50', '2016-12-09 14:12:04', null, null);
INSERT INTO `article` VALUES ('20', 'title', 'contentcontentcontentconten...', 'contentcontentcontentcontentcontent', '[{\"groupId\":1,\"groupName\":\"test\",\"createTime\":\"Nov 22, 2016 3:45:01 PM\"}]', '2016-11-24 10:24:21', '2016-12-09 14:11:50', null, null);
INSERT INTO `article` VALUES ('21', 'title', 'contentcontentcontentconten...', 'contentcontentcontentcontentcontent', '[{\"groupId\":1,\"groupName\":\"test\",\"createTime\":\"Nov 22, 2016 3:45:01 PM\"}]', '2016-12-06 15:44:39', '2016-12-09 10:06:15', null, null);
INSERT INTO `article` VALUES ('22', 'title', 'contentcontentcontentconten...', 'contentcontentcontentcontentcontent', ' [{\"groupId\":7,\"groupName\":\"test2\",\"createTime\":\"Nov 22, 2016 4:12:03 PM\"},{\"groupId\":10,\"groupName\":\"test3\",\"createTime\":\"Nov 22, 2016 4:12:49 PM\"}]\r\n', '2016-12-06 15:47:41', null, null, null);
INSERT INTO `article` VALUES ('23', 'title111', 'contentcontentcontentconten...', 'contentcontentcontentcontentcontent111', ' [{\"groupId\":7,\"groupName\":\"test2\",\"createTime\":\"Nov 22, 2016 4:12:03 PM\"},{\"groupId\":10,\"groupName\":\"test3\",\"createTime\":\"Nov 22, 2016 4:12:49 PM\"}]\r\n', '2016-12-06 16:56:17', null, null, null);
INSERT INTO `article` VALUES ('26', 'title', 'contentcontentcontentconten...', 'contentcontentcontentcontentcontent', '[{\"groupId\":10,\"groupName\":\"test3\",\"createTime\":\"Nov 22, 2016 4:12:49 PM\"}]', '2016-12-09 09:52:36', '2016-12-09 10:08:11', null, null);
INSERT INTO `article` VALUES ('27', 'maven导出项目依赖的jar包', ' 一、导出到默认目录 targed/dependenc...', '<p>\n	一、导出到默认目录 targed/dependency&nbsp;\n</p>\n<p>\n	<br />\n&nbsp; &nbsp; 从Maven项目中导出项目依赖的jar包：进入工程pom.xml 所在的目录下，执行如下命令：<br />\n<span style=\"background-color:#FFFFFF;\">&nbsp;&nbsp;</span><span style=\"color:#FFFFFF;background-color:#FFFFFF;\">&nbsp;&nbsp;</span><span style=\"color:#FFFFFF;background-color:#000000;\">mvn dependency:copy-dependencies</span><br />\n&nbsp; &nbsp; &nbsp; 或在eclipse中，选择项目的pom.xml文件，点击右键菜单中的Run As,见下图红框中，在弹出的Configuration窗口中，输入 dependency:copy-dependencies后，点击运行<br />\n&nbsp;&nbsp;<br />\n二、导出到自定义目录中<br />\n<br />\n&nbsp; &nbsp; 在maven项目下创建lib文件夹，输入以下命令：<br />\n<span style=\"color:#FFFFFF;background-color:#000000;\"><span style=\"background-color:#FFFFFF;\">&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=\"background-color:#FFFFFF;\">&nbsp;</span>mvn dependency:copy-dependencies -DoutputDirectory=lib</span><br />\n&nbsp; &nbsp; maven项目所依赖的jar包都会复制到项目目录下的lib目录下\n</p>\n<p>\n	<br />\n三、设置依赖级别\n</p>\n<p>\n	<br />\n&nbsp; &nbsp; 同时可以设置依赖级别，通常使用compile级别<br />\n<span style=\"color:#FFFFFF;background-color:#000000;\"><span style=\"background-color:#FFFFFF;\">&nbsp;&nbsp;&nbsp;&nbsp;</span>mvn dependency:copy-dependencies -DoutputDirectory=lib &nbsp; -DincludeScope=compile</span>\n</p>\n<p>\n	<br />\n</p>', '[{\"groupId\":14,\"groupName\":\"mvn\",\"createTime\":\"Dec 9, 2016 2:22:15 PM\"}]', '2016-12-09 14:29:53', null, null, null);
