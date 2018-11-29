/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : cpp

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-11-29 15:32:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` varchar(255) DEFAULT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `employee_id` varchar(255) DEFAULT NULL COMMENT '雇员id',
  `create_time` datetime DEFAULT NULL,
  `is_enable` tinyint(4) DEFAULT NULL COMMENT '是否结束'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` varchar(255) NOT NULL,
  `employer_id` varchar(255) DEFAULT NULL COMMENT '雇主id',
  `employee_id` varchar(255) DEFAULT NULL COMMENT '雇员id',
  `title` varchar(255) DEFAULT NULL COMMENT '兼职标题',
  `content` varchar(255) DEFAULT NULL COMMENT '兼职内容',
  `deadline` datetime DEFAULT NULL COMMENT '截至时间',
  `classification` varchar(255) DEFAULT NULL COMMENT '订单分类',
  `status` varchar(255) DEFAULT NULL COMMENT '订单状态',
  `need_number` int(11) DEFAULT NULL COMMENT '所需人数',
  `salary` int(255) DEFAULT NULL COMMENT '薪水',
  `place` varchar(255) DEFAULT NULL COMMENT '兼职地点',
  `sex` tinyint(255) DEFAULT NULL COMMENT '性别需求',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `settlement_method` tinyint(4) DEFAULT NULL COMMENT '结算方式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `id` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL COMMENT '更改的状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `parent_id` varchar(11) DEFAULT NULL COMMENT '被监视的状态数据'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of record
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `head_icon` varchar(255) DEFAULT NULL,
  `sex` tinyint(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `is_official` tinyint(4) DEFAULT NULL COMMENT '是否官方',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
