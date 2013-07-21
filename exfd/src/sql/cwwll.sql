/*
MySQL Data Transfer
Source Host: localhost
Source Database: cwwll
Target Host: localhost
Target Database: cwwll
Date: 2013/4/28 21:26:24
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for container
-- ----------------------------
CREATE TABLE `container` (
  `ccode` varchar(50) NOT NULL COMMENT '集装箱的ID',
  `inputtime` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '时间',
  `longitute` varchar(20) NOT NULL COMMENT '经度',
  `latitute` varchar(20) NOT NULL COMMENT '纬度',
  `del_flg` int(1) NOT NULL default '0' COMMENT '删除标记',
  `remark` varchar(100) NOT NULL,
  PRIMARY KEY  (`ccode`,`inputtime`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='集装箱位置表';

-- ----------------------------
-- Table structure for goods
-- ----------------------------
CREATE TABLE `goods` (
  `gcode` varchar(50) NOT NULL COMMENT '货单号',
  `scode` varchar(50) NOT NULL COMMENT '船只ID',
  `ccode` varchar(50) NOT NULL COMMENT '集装箱ID',
  `status` int(1) NOT NULL default '0' COMMENT '货物当前的状态（0:未装箱,1:在集装箱,2:在船上）',
  `inputtime` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '时间',
  `longitude` varchar(20) NOT NULL COMMENT '经度',
  `latitude` varchar(20) NOT NULL COMMENT '纬度',
  `del_flg` int(1) NOT NULL default '0' COMMENT '删除标记',
  `remark` varchar(100) NOT NULL,
  PRIMARY KEY  (`gcode`,`inputtime`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='货物位置表';

-- ----------------------------
-- Table structure for ship
-- ----------------------------
CREATE TABLE `ship` (
  `scode` varchar(50) NOT NULL COMMENT '船的ID',
  `inputtime` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '时间',
  `longitute` varchar(20) NOT NULL COMMENT '经度',
  `latitute` varchar(20) NOT NULL COMMENT '纬度',
  `del_flg` int(1) NOT NULL default '0' COMMENT '删除标记',
  `remark` varchar(100) NOT NULL,
  PRIMARY KEY  (`scode`,`inputtime`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='船只位置表';

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `goods` VALUES ('g154689', 's435678', 'c254633', '1', '2013-04-01 00:00:00', '123.654', '256.478', '0', 'æµè¯');
INSERT INTO `goods` VALUES ('g78956', 's789635', 'c45623', '0', '2013-01-02 00:00:00', '456.321', '986.321', '0', '���');
INSERT INTO `goods` VALUES ('g7777', 's6666', 'c8888', '0', '2013-12-15 00:00:00', '123.564', '564.3256', '0', '%C3%A5%C2%93%C2%88%C3%A5%C2%93%C2%88');
INSERT INTO `goods` VALUES ('g1111', 's11111', 'c1111', '0', '2013-01-03 00:00:00', '1123.233', '564.3232', '0', 'ååå');
INSERT INTO `goods` VALUES ('g5555', 's99999', 'c66666', '0', '2013-01-03 00:00:00', '456.321', '123.654', '0', 'ååå');
INSERT INTO `goods` VALUES ('g33333', 's6666', 'c99999', '0', '2013-04-06 00:00:00', '456.321', '789.325', '0', '和哈哈哈');
INSERT INTO `goods` VALUES ('g9807', 's4564', 'c6574', '2', '2013-04-01 14:04:17', '987.888', '988.777', '0', '测试');
INSERT INTO `goods` VALUES ('g9807', 's5555', 'c123', '0', '2013-04-01 07:03:05', '44447.235', '669.36', '0', '成功了！');
INSERT INTO `goods` VALUES ('111', '11', '111', '0', '2013-04-01 14:49:16', '11', '11', '0', '备    注');
