/*
 Navicat MySQL Data Transfer

 Source Server         : naviconn
 Source Server Version : 50610
 Source Host           : localhost
 Source Database       : SealDB

 Target Server Version : 50610
 File Encoding         : utf-8

 Date: 08/13/2013 14:40:55 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `SHIPINFO`
-- ----------------------------
DROP TABLE IF EXISTS `SHIPINFO`;
CREATE TABLE `SHIPINFO` (
  `shipid` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `shipname` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '英文船名',
  `shipnamecn` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '中文船名',
  `mmsi` varchar(50) CHARACTER SET utf8 DEFAULT '',
  `imo` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `callsign` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '呼号',
  `shipflag` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '船籍',
  `shiptype` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '船舶类型',
  `shiplength` double DEFAULT NULL COMMENT '船长',
  `shipwidth` double DEFAULT NULL COMMENT '船宽',
  `draft` double DEFAULT NULL COMMENT '吃水',
  `gpstime` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '报位时间',
  `latitude` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '纬度，度分秒格式',
  `longitude` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '经度，度分秒格式',
  `lat` double DEFAULT NULL COMMENT '纬度 小数值',
  `lon` double DEFAULT NULL COMMENT '经度 小数值',
  `speed` double DEFAULT NULL COMMENT '航速',
  `direction` double DEFAULT NULL COMMENT '航向',
  `truehending` double DEFAULT NULL COMMENT '航首向',
  `reporttype` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '报位方式',
  `state` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '航行状态',
  `updatetime` double DEFAULT NULL COMMENT '更新时间',
  `gpstimepre` varchar(50) CHARACTER SET utf8 DEFAULT '""' COMMENT '上一个位置的定位时间',
  `latpre` double DEFAULT '0' COMMENT '上一个位置的纬度',
  `lonpre` double DEFAULT '0' COMMENT '上一个位置的经度',
  `averagespeed` double DEFAULT '0' COMMENT '平均速度',
  `distanceMoved` double DEFAULT '0' COMMENT '最后行驶距离'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `SHIPINFO`
-- ----------------------------
BEGIN;
INSERT INTO `SHIPINFO` VALUES ('201208141121108184764', 'COSCO ANSTEEL', '中远鞍钢', '477617800', '9377391', 'VRFV5', '477', '0', '327', '55', '108', '2013-07-28 00:28:17', '30°40\'33.42\"S', '32°8\'33.11\"E', '-30.67595', '32.142533', '13.2', '226', '22.6', '4', '0', '1374942453', '2013-07-28 00:27:17', '-30.673433', '32.14565', '0', '0'), ('201306260640260822276', 'YUEZHAO QINGHUO 2666', '', '413977830', '0', 'RPPPRRR', '413', '0', '49', '11', '0', '2013-07-16 11:41:32', '32°10\'42.40\"N', '119°54\'22.61\"E', '32.178447', '119.906282', '30.5', '352.8', '51.1', '4', '0', '1373946314', '', '0', '0', '0', '0'), ('201208141146412404993', 'YUEZHAO QINGHUO 2666', '', '413977829', '0', '2355', '413', '0', '49', '11', '0', '2013-07-19 02:11:37', '22°27\'21.71\"N', '113°52\'36.86\"E', '22.456032', '113.876907', '5.6', '331.6', '51.1', '4', '0', '1374171060', '', '0', '0', '0', '0'), ('201208141213531770990', '', '中远鞍钢', '', '', '', '', '90', '0', '0', '0', '', '', '', '0', '0', '0', '0', '0', '', '', '0', '', '0', '0', '0', '0'), ('201307250747117276354', '', '', '370188016', '', '', '', '', '0', '0', '0', '2013-07-25 07:46:01', '52°6\'32.93\"N', '4°11\'42.31\"E', '52.10915', '4.195088', '2.5', '151.5', '13.1', '4', '0', '1374709591', '', '0', '0', '0', '0'), ('201208141141174119032', 'COSCO AFRICA', '中远非洲', '370188000', '9345439', '3ERX3', '370', '0', '349', '46', '102', '2013-08-11 07:47:39', '33°7\'11.41\"N', '123°11\'40.91\"E', '33.119837', '123.194698', '14.9', '158.7', '15.9', '4', '0', '1376178412', '2013-08-11 07:46:46', '33.123293', '123.193098', '0', '0'), ('201306192335539307170', '', '', '477617824', '', '', '', '', '0', '0', '0', '2013-06-19 23:35:55', '38°6\'40.49\"N', '120°38\'31.99\"E', '38.11125', '120.642222', '23.4', '291', '26.6', '4', '1', '1371656141', '', '0', '0', '0', '0'), ('201302071927099992536', '', '', '477617896', '', '', '', '', '0', '0', '0', '2013-02-07 19:27:05', '54°24\'21.37\"N', '18°39\'58.13\"E', '54.405938', '18.666148', '0', '0.1', '11.9', '4', '0', '1360236417', '', '0', '0', '0', '0'), ('201208141212537243302', ' ', '', '352687264', '33555102', '__N;A,$', '352', '90', '0', '0', '26', '', '', '', '0', '0', '0', '0', '0', '', '', '0', '', '0', '0', '0', '0'), ('201208141120041150134', 'GREAT CHINA', 'GREAT CHINA', '477399300', '9384617', 'VRFK5', '477', '0', '330', '60', '112', '2013-08-12 05:40:35', '6°1\'3.6\"N', '92°35\'56.81\"E', '6.017518', '92.599117', '8.4', '267', '25.9', '6', '0', '1376257358', '', '0', '0', '0', '0'), ('201208141138410059825', 'Eastern Glamour', 'EASTERN GLAMOUR', '477759600', '9437238', 'VRIQ5', '477', '301', '254', '43', '15', '2013-08-12 00:30:30', '12°20\'41.4\"N', '120°25\'36.48\"E', '12.344733', '120.4268', '11', '344', '34.2', '6', '0', '1376240879', '', '0', '0', '0', '0'), ('201208141141353021125', 'MV EASTERN GLAMOUR', '', '815908796', '202406929', 'VRIQ5', '', '70', '254', '43', '60', '', '', '', '0', '0', '0', '0', '0', '', '', '0', '', '0', '0', '0', '0'), ('201209130926069781752', ' ', '', '704643111', '123456789', 'CYMW', '704', '131', '20', '6', '43', '2012-10-29 01:46:05', '49°6\'40.77\"N', '123°9\'32.57\"W', '49.111327', '-123.15905', '10.2', '286.3', '51.1', '4', '0', '1351446441', '', '0', '0', '0', '0'), ('201208141122162406107', ' ', '', '316005415', '123456789', 'CYMW', '316', '52', '20', '5', '1', '2012-08-21 17:07:35', '49°8\'59.5\"N', '122°59\'59.18\"W', '49.149737', '-122.999773', '0', '143.6', '51.1', '4', '0', '1345549555', '', '0', '0', '0', '0'), ('201208141138328800543', ' IN  WANG 111', '', '413406166', '0', '', '413', '19', '19', '15', '0', '', '', '', '0', '0', '0', '0', '0', '', '', '0', '', '0', '0', '0', '0'), ('201208141137579740685', 'Bulk Ingenuity', 'BULK INGENUITY', '477229700', '9566980', 'VRHY4', '477', '0', '292', '45', '18', '2013-08-12 20:38:26', '38°49\'35.44\"N', '118°13\'49.78\"E', '38.826512', '118.230497', '0.1', '25.6', '30.5', '4', '0', '1376311059', '2013-08-12 20:35:29', '38.82645', '118.230448', '0', '0');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
