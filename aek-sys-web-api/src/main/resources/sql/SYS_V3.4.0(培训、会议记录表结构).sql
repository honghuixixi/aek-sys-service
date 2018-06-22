/*
Navicat MySQL Data Transfer

Source Server         : 135
Source Server Version : 50718
Source Host           : 192.168.1.135:3306
Source Database       : sd_feat

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-06-01 16:23:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_train
-- ----------------------------
DROP TABLE IF EXISTS `sys_train`;
CREATE TABLE `sys_train` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` bigint(11) DEFAULT NULL COMMENT '所属租户ID',
  `num` char(14) COLLATE utf8_bin DEFAULT NULL COMMENT '培训编号',
  `subject` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '培训主题',
  `type` int(1) DEFAULT NULL COMMENT '培训类型(1，岗位基础培训 2，设备操作培训 3，科室业务培训 4，继续教育)',
  `train_time` datetime DEFAULT NULL COMMENT '培训时间',
  `lecturer` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '培训讲师',
  `locale` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '培训地点',
  `target` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '培训对象',
  `content` varchar(300) COLLATE utf8_bin DEFAULT NULL COMMENT '培训内容',
  `files` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '附件，分割',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` bit(1) DEFAULT b'0' COMMENT '删除标志 1 删除 0 未删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='机构培训表';

-- ----------------------------
-- Table structure for sys_meeting
-- ----------------------------
DROP TABLE IF EXISTS `sys_meeting`;
CREATE TABLE `sys_meeting` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` bigint(11) DEFAULT NULL COMMENT '所属租户ID',
  `num` char(14) COLLATE utf8_bin DEFAULT NULL COMMENT '会议编号',
  `subject` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '会议主题',
  `originator` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '会议发起人',
  `meeting_time` datetime DEFAULT NULL COMMENT '会议时间',
  `locale` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '会议地点',
  `attendee` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '参会人',
  `content` varchar(300) COLLATE utf8_bin DEFAULT NULL COMMENT '会议内容',
  `files` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '附件，分割',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` bit(1) DEFAULT b'0' COMMENT '删除标志 1 删除 0 未删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='机构会议记录表';
