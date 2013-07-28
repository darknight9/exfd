package com.exfd.service.impl;

import java.util.List;

import com.exfd.dao.ShipDao;
import com.exfd.dao.impl.ShipMyshipsDaoImpl;
import com.exfd.domain.Ship;

public class ShipServiceImpl {


	private ShipDao dao = new ShipMyshipsDaoImpl();	// 以后用工厂模式或者spring来解耦合.
	
	// 对web层提供查询信息服务.
	public Ship track(String code) {
		
		// 在dao层查找.
		Ship ship = dao.find(code);

		return ship;
	}
	
	// 对web层提供查询信息服务.
	public List<Ship> track(String operid, String keystr, String type,
			int start_ship, int end_ship) {
		
		// 在dao层查找.
		List<Ship> ships = dao.findDetail(operid, keystr, type, start_ship, end_ship);

		return ships;
	}


}
