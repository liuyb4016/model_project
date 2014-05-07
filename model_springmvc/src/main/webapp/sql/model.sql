/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : model

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2014-05-07 17:41:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `app`
-- ----------------------------
DROP TABLE IF EXISTS `app`;
CREATE TABLE `app` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ctrate_time` datetime DEFAULT NULL,
  `download_count` int(11) DEFAULT NULL,
  `file_md5` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `icon_name` varchar(255) DEFAULT NULL,
  `pack_size` int(11) DEFAULT NULL,
  `package_name` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `version_code` int(11) DEFAULT NULL,
  `version_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app
-- ----------------------------

-- ----------------------------
-- Table structure for `mobile_file`
-- ----------------------------
DROP TABLE IF EXISTS `mobile_file`;
CREATE TABLE `mobile_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `file_size` bigint(20) DEFAULT NULL,
  `folder_id` bigint(20) DEFAULT NULL,
  `md5` varchar(255) DEFAULT NULL,
  `mime_type` varchar(255) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `suffix` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `upload_time` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mobile_file
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `birthday` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `login_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `reg_date` datetime DEFAULT NULL,
  `status` int(11) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '2014-05-07', null, '2014-05-07 17:26:02', 'admin', 'c0ffbc4e2f04b00f3e41aca6166d9629', null, '2014-05-07 17:26:13', '1', '2014-05-07 17:26:18', 'admin');

-- ----------------------------
-- Table structure for `virtual_folder`
-- ----------------------------
DROP TABLE IF EXISTS `virtual_folder`;
CREATE TABLE `virtual_folder` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `father_id` bigint(20) DEFAULT NULL,
  `folder_name` varchar(255) DEFAULT NULL,
  `last_upload_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of virtual_folder
-- ----------------------------
