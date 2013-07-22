/*
 Navicat MySQL Data Transfer

 Source Server         : naviconn
 Source Server Version : 50610
 Source Host           : localhost
 Source Database       : SealDB

 Target Server Version : 50610
 File Encoding         : utf-8

 Date: 07/22/2013 17:11:39 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `SEALINFO`
-- ----------------------------
DROP TABLE IF EXISTS `SEALINFO`;
CREATE TABLE `SEALINFO` (
  `code` varchar(50) NOT NULL COMMENT '铅封编号',
  `status` int(1) unsigned NOT NULL DEFAULT '0' COMMENT '铅封状态（0:未使用,1:使用中,2:废弃）',
  `longitude` double NOT NULL COMMENT '经度',
  `latitude` double NOT NULL COMMENT '纬度',
  `ctime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生产时间',
  `mtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `markdel` int(1) NOT NULL DEFAULT '0' COMMENT '标记(0:正常,1:删除)',
  `remark` varchar(100) DEFAULT '' COMMENT '备注信息',
  `plate` varchar(20) DEFAULT NULL COMMENT '车牌',
  `gpstime` timestamp NULL DEFAULT NULL COMMENT 'GSP设备最后一次汇报的时间',
  `speed` int(10) unsigned DEFAULT '0' COMMENT '速度',
  `direction` double DEFAULT NULL COMMENT '方位角',
  `daymiles` double DEFAULT NULL COMMENT '日里程',
  `monthmiles` double DEFAULT NULL,
  `oil` int(11) DEFAULT NULL COMMENT '油箱油量',
  `engst` varchar(20) DEFAULT NULL COMMENT '发动机状态',
  PRIMARY KEY (`code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='铅封信息表';

-- ----------------------------
--  Records of `SEALINFO`
-- ----------------------------
BEGIN;
INSERT INTO `SEALINFO` VALUES ('sid001', '1', '111.111', '122.222', '2013-07-01 00:00:00', null, '0', '备注1', null, null, '0', null, null, null, null, null), ('sid002', '1', '222.222', '2.222', '2013-07-02 00:00:00', null, '0', 'what the fuck!', null, null, '0', null, null, null, null, null), ('sid003', '1', '333.333', '3.222', '2013-07-03 00:00:00', null, '0', '%C3%A5%C2%93%C2%88%C3%A5%C2%93%C2%88', null, null, '0', null, null, null, null, null), ('sid004', '1', '444.111', '12.222', '2013-07-04 00:00:00', null, '0', 'ååå', null, null, '0', null, null, null, null, null), ('sid005', '1', '555.111', '99.222', '2013-07-05 00:00:00', null, '1', '���', null, null, '0', null, null, null, null, null), ('sid006', '1', '666.111', '100.222', '2013-07-06 23:00:00', null, '0', '备注6', null, null, '0', null, null, null, null, null), ('sid007', '1', '777.111', '444.222', '2013-07-07 22:11:23', null, '1', '备注7', null, null, '0', null, null, null, null, null), ('sid008', '1', '888.111', '333.222', '2013-08-01 11:22:33', null, '0', '备注8', null, null, '0', null, null, null, null, null), ('sid009', '1', '999.111', '211.222', '2011-03-12 15:59:59', null, '0', '备注9', null, null, '0', null, null, null, null, null), ('sid00X', '0', '1000.111', '99.222', '2013-07-01 23:59:59', null, '1', '备注X', null, null, '0', null, null, null, null, null), ('sid00Y', '0', '123.456', '99.99', '2002-07-01 00:00:00', null, '0', '备注Y', null, null, '0', null, null, null, null, null), ('sid00Z', '2', '987.321', '9.9', '2031-12-31 23:58:58', null, '0', '备注Z', null, null, '0', null, null, null, null, null), ('test002', '1', '123.123', '456.456', '2013-07-21 17:59:41', '2013-07-21 17:59:41', '1', '来自测试testUpdate的注释', null, null, '0', null, null, null, null, null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
