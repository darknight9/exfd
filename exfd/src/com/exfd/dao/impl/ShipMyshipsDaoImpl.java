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

	private static Logger logger = LogManager.getLogger();

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

		// 规则：7天前的数据是失效的.
		Calendar rightnow = Calendar.getInstance();
		rightnow.add(Calendar.DAY_OF_YEAR, -7);
		Date expire = rightnow.getTime();

		// 如果找到，还需要判断是否有效.
		if (ship != null) {

			logger.debug("SHIP[{}].find ship code [{}] in db", code, code);

			// 如果有效就可以返回了.
			if (isTrackValid(ship, expire)) {
				logger.debug(
						"SHIP[{}].find ship code [{}] in db valid, return.",
						code, code);
				return ship;
			}
		}
		// 需要联网获取最新的信息.
		String str = null;
		try {
			str = MyshipsUtils.getSearchRecByKeyAndTypeInShipBaseInfo("", code,
					"mmsi", 1, 2);
		} catch (Exception e) {
			logger.catching(e);
			str = null;
		}
		
		// 如果联网失败或者信息无效.返回一个"1"表示搜索无结果.
		// 宝船网的返回结果，如果是没有找到船，返回2个字节的（"")2个双引号.
		// 而正常结果不可能字节小于5，所以用这个来判断是否有结果.
		if (str == null || str.trim().equals("") || str.length() < 5 ) {
			if (ship != null) {
				// 还是需要更新mtime的值的.
				update(ship);
				return ship;
			} else {
				return null;
			}
		}
	
		Ship onlineShip = null;
		try {
			onlineShip = json2ship(str);
		} catch (Exception e) {
			logger.catching(e);
			onlineShip = null;
		}
		
		if (onlineShip != null) {
			// 联网信息有效，写入数据库并返回.
			if (ship != null) {
				// 有旧记录，更新.
				update(onlineShip);
			} else {
				// 没有记录，添加.
				add(onlineShip);
			}
		} else if (ship != null) {
			// 还是需要更新mtime的值的.
			update(ship);
			return ship;
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

	// 可能返回null.
	// 也可能返回空list.
	@Override
	public List<Ship> findDetail(String operid, String keystr, String type,
			int start_ship, int end_ship) {

		// 先在数据库中查找.
		ArrayList<Ship> ships = dbimp.findDetail(operid, keystr, type,
				start_ship, end_ship);
		if (ships != null) {
			logger.debug("SHIP[{}].dbimp.findDetail return {} records", keystr, ships.size());
		} else {
			logger.debug("SHIP[{}]. dbimp.findDetail return 0 record.", keystr);
		}

		// 规则：7天前的数据是失效的.
		Calendar rightnow = Calendar.getInstance();
		rightnow.add(Calendar.DAY_OF_YEAR, -7);
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
			logger.catching(e);
			str = null;
		}
		
		// 如果联网失败或者信息无效.返回一个"1"表示搜索无结果.
		// 宝船网的返回结果，如果是没有找到船，返回2个字节的（"")2个双引号.
		// 而正常结果不可能字节小于5，所以用这个来判断是否有结果.
		if (str == null || str.trim().equals("") || str.length() < 5 ) {
			if (ships != null && !ships.isEmpty()) {
				// 还是需要更新mtime的值的.
				update(ships);
				return ships;
			} else {
				// 这里实际返回了一个空list.
				// 不会返回空指针.
				return ships;
			}
		}
				
		List<Ship> onlineShips = null;
		try {
			onlineShips = json2ships(str);
		} catch (Exception e) {
			logger.catching(e);
			onlineShips = null;
		}
		
		if (onlineShips != null && !onlineShips.isEmpty()) {
			// 联网信息有效，写入数据库并返回.
			updateOrAdd(onlineShips);
		} else if (ships != null) {
			// 还是需要更新mtime的值的.
			update(ships);
			return ships;
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
