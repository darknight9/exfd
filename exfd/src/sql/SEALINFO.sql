/*
 Navicat MySQL Data Transfer

 Source Server         : naviexfd
 Source Server Version : 50610
 Source Host           : localhost
 Source Database       : SealDB

 Target Server Version : 50610
 File Encoding         : utf-8

 Date: 07/21/2013 17:19:21 PM
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
  PRIMARY KEY (`code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='铅封信息表';

-- ----------------------------
--  Records of `SEALINFO`
-- ----------------------------
BEGIN;
INSERT INTO `SEALINFO` VALUES ('sid001', '1', '111.111', '122.222', '2013-07-01 00:00:00', null, '0', '备注1'), ('sid002', '1', '222.222', '2.222', '2013-07-02 00:00:00', null, '0', 'what the fuck!'), ('sid003', '1', '333.333', '3.222', '2013-07-03 00:00:00', null, '0', '%C3%A5%C2%93%C2%88%C3%A5%C2%93%C2%88'), ('sid004', '1', '444.111', '12.222', '2013-07-04 00:00:00', null, '0', 'ååå'), ('sid005', '1', '555.111', '99.222', '2013-07-05 00:00:00', null, '1', '���'), ('sid006', '1', '666.111', '100.222', '2013-07-06 23:00:00', null, '0', '备注6'), ('sid007', '1', '777.111', '444.222', '2013-07-07 22:11:23', null, '1', '备注7'), ('sid008', '1', '888.111', '333.222', '2013-08-01 11:22:33', null, '0', '备注8'), ('sid009', '1', '999.111', '211.222', '2011-03-12 15:59:59', null, '0', '备注9'), ('sid00X', '0', '1000.111', '99.222', '2013-07-01 23:59:59', null, '1', '备注X'), ('sid00Y', '0', '123.456', '99.99', '2002-07-01 00:00:00', null, '0', '备注Y'), ('sid00Z', '2', '987.321', '9.9', '2031-12-31 23:58:58', null, '0', '备注Z'), ('test001', '1', '123.123', '456.456', '2013-07-01 00:00:00', '2013-07-01 00:00:00', '1', '???????'), ('test002', '1', '123.123', '456.456', '2013-07-01 00:00:00', '2013-07-01 00:00:00', '1', '???????'), ('test003', '1', '123.123', '456.456', '2013-07-01 00:00:00', '2013-07-01 00:00:00', '1', '???????'), ('test004', '1', '123.123', '456.456', '1980-03-03 04:05:06', '1993-05-05 06:07:08', '1', '???????'), ('?', '1', '123.123', '456.456', '1980-03-03 04:05:06', '1993-05-05 06:07:08', '1', '???????');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
