package com.exfd.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.exfd.dao.ShipDao;
import com.exfd.domain.Ship;
import com.exfd.domain.ShipReturnSet;
import com.exfd.util.MyshipsUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

//使用了类似修饰模式的类.
public class ShipMyshipsDaoImpl implements ShipDao {

	static Logger logger = LogManager.getLogger();

	// 内部使用ShipDaoImpl.
	ShipDaoImpl dbimp = new ShipDaoImpl();

	@Override
	public void add(Ship ship) {
		dbimp.add(ship);
	}

	@Override
	public void add(List<Ship> ships) {
		dbimp.add(ships);

	}

	@Override
	public void delete(Ship ship) {
		dbimp.delete(ship);
	}

	@Override
	public void delete(String code) {
		dbimp.delete(code);
	}

	@Override
	public void delete(List<String> codes) {
		dbimp.delete(codes);
	}

	@Override
	public void update(Ship ship) {
		dbimp.update(ship);
	}

	@Override
	public void update(List<Ship> ships) {
		dbimp.update(ships);
	}

	@Override
	public Ship find(String code) {

		// 先在数据库中查找.
		Ship ship = dbimp.find(code);
		Calendar rightnow = Calendar.getInstance();

		// 规则：1天前的数据是失效的.
		rightnow.add(Calendar.DAY_OF_YEAR, -1);
		Date expire = rightnow.getTime();

		// 如果找到，还需要判断是否有效.
		if (ship != null) {

			logger.debug("find ship code [{}] in db", code);

			// 如果有效就可以返回了.
			if (isTrackValid(ship, expire)) {
				logger.debug("find ship code [{}] in db valid, return.", code);
				return ship;
			}
		}
		// 需要联网获取最新的信息.
		String str = null;
		try {
			str = MyshipsUtils.getSearchRecByKeyAndTypeInShipBaseInfo("", code,
					"mmsi", 1, 2);
		} catch (Exception e) {
			str = null;
			e.printStackTrace();
		}

		// 如果联网失败或者信息无效，直接返回null.
		// TODO 这个逻辑是有问题的，信息无效应该尽量返回数据库中的信息.
		if (str == null || str.equals("")) {
			return null;
		}
		Ship onlineShip = json2ship(str);
		if (onlineShip != null) {
			// 联网信息有效，写入数据库并返回.
			if (ship != null) {

				// TODO 这里以后需要升级复杂的更新逻辑.
				// 有旧记录，更新.
				update(onlineShip);
			} else {
				// 没有记录，添加.
				add(onlineShip);
			}
		}
		return onlineShip;
	}

	private Ship json2ship(String str) {
		Gson gson = new Gson();
		ShipReturnSet rSet = gson.fromJson(str, new TypeToken<ShipReturnSet>() {
		}.getType());
		return rSet.getShips().get(0);
	}

	private List<Ship> json2ships(String str) {
		Gson gson = new Gson();
		ShipReturnSet rSet = gson.fromJson(str, new TypeToken<ShipReturnSet>() {
		}.getType());
		return rSet.getShips();
	}

	// TODO 目前的实现是只要有记录就有效.
	private boolean isTrackValid(Ship ship, Date expire) {
		return true;
	}

	@Override
	public Map<String, Ship> find(List<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Ship> list() {
		return dbimp.list();
	}

	@Override
	public List<Ship> findDetail(String operid, String keystr, String type,
			int start_ship, int end_ship) {

		logger.debug("ship findDetail:operid[{}], keystr[{}], type[{}]", operid, keystr, type);
		// 先在数据库中查找.
		ArrayList<Ship> ships = dbimp.findDetail(operid, keystr, type,
				start_ship, end_ship);
		if (ships != null) {
			logger.debug("dbimp.findDetail return {} records", ships.size());
		} else {
			logger.debug("dbimp.findDetail return NULL 0 record.");
		}
		
		Calendar rightnow = Calendar.getInstance();

		// 规则：1天前的数据是失效的.
		rightnow.add(Calendar.DAY_OF_YEAR, -1);
		Date expire = rightnow.getTime();

		// 如果找到，还需要判断是否有效.
		if (ships != null && !ships.isEmpty()) {

			// 如果有效就可以返回了.
			// TODO 这里还有很多检查要做.
			// if (isTrackValid(ship, expire)) {
			// return ship;
			// }
			return ships;
		}
		// 需要联网获取最新的信息.
		String str = null;
		try {
			str = MyshipsUtils.getSearchRecByKeyAndTypeInShipBaseInfo(operid,
					keystr, type, start_ship, end_ship);
		} catch (Exception e) {
			str = null;
			e.printStackTrace();
		}

		// 如果联网失败或者信息无效，直接返回null.
		// TODO 这个逻辑是有问题的，信息无效应该尽量返回数据库中的信息.
		if (str == null || str.trim().equals("")) {
			return null;
		}
		
		logger.debug(str);
		
		List<Ship> onlineShips;
		try {
			onlineShips = json2ships(str);
		} catch (Exception e) {
			e.printStackTrace();
			onlineShips = null;
		}
		if (onlineShips != null && !onlineShips.isEmpty()) {
			// 联网信息有效，写入数据库并返回.
			updateOrAdd(onlineShips);
		}
		return onlineShips;
	}

	public void updateOrAdd(List<Ship> ships) {
		dbimp.updateOrAdd(ships);
	}

	public void updateOrAdd(Ship ship) {
		dbimp.updateOrAdd(ship);
	}

}
