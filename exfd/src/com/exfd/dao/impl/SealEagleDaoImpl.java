package com.exfd.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.exfd.dao.SealDao;
import com.exfd.domain.Seal;
import com.exfd.util.EagleGPSUtils;

// 使用了类似修饰模式的类.
public class SealEagleDaoImpl implements SealDao {

	static Logger logger = LogManager.getLogger();

	// 1980-09-09
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");

	// 内部使用SealDaoImpl.
	SealDaoImpl dbimp = new SealDaoImpl();

	@Override
	public void add(Seal seal) {
		dbimp.add(seal);
	}

	@Override
	public void add(ArrayList<Seal> seals) {
		dbimp.add(seals);
	}

	@Override
	public void delete(Seal seal) {
		dbimp.delete(seal);
	}

	@Override
	public void delete(String code) {
		dbimp.delete(code);
	}

	@Override
	public void delete(ArrayList<String> codes) {
		dbimp.delete(codes);
	}

	@Override
	public void update(Seal seal) {
		dbimp.update(seal);
	}

	@Override
	public void update(ArrayList<Seal> seals) {
		dbimp.update(seals);
	}

	@Override
	public Seal find(String code) {

		// 先在数据库中查找.
		Seal seal = dbimp.find(code);

		// 规则：1天前的数据是失效的.
		Calendar rightnow = Calendar.getInstance();
		rightnow.add(Calendar.DAY_OF_YEAR, -1);
		Date expire = rightnow.getTime();

		// 如果找到，还需要判断是否有效.
		if (seal != null) {

			logger.debug("SEAL[{}].find seal code [{}] in db", code, code);

			// 如果有效就可以返回了.
			if (isTrackValid(seal, expire)) {
				logger.debug(
						"SEAL[{}].find seal code [{}] in db valid, return.",
						code, code);
				return seal;
			}
		}
		// 需要联网获取最新的信息.
		String str = EagleGPSUtils.trackVehicle(code);

		// 如果联网失败或者信息无效.返回一个"1"表示搜索无结果.
		if (str == null || str.equals("") || str.startsWith("1")) {
			if (seal != null) {
				// 还是需要更新mtime的值的.
				update(seal);
				return seal;
			} else {
				return null;
			}
		}
		Seal onlineSeal = null;
		try {
			onlineSeal = xml2seal(str);
		} catch (Exception e) {
			logger.catching(e);
			onlineSeal = null;
		}

		if (onlineSeal != null) {
			// 联网信息有效，写入数据库并返回.
			if (seal != null) {
				// 有旧记录，更新.
				update(onlineSeal);
			} else {
				// 没有记录，添加.
				add(onlineSeal);
			}
		} else if (seal != null) {
			// 还是需要更新mtime的值的.
			update(seal);
			return seal;
		}
		return onlineSeal;
	}
	
	// 返回的至少是一个空Map.
	// 如果有的code没有找到，对应的Map中有<code, null>的pair.
	// 但是可能在查找的时候出现异常，这个时候返回的Map可能是不完整的。
	@Override
	public Map<String, Seal> find(ArrayList<String> codes) {

		// 先在数据库中查找.
		Map<String, Seal> mapSeal = dbimp.find(codes);

		// 规则：1天前的数据是失效的.
		Calendar rightnow = Calendar.getInstance();
		rightnow.add(Calendar.DAY_OF_YEAR, -1);
		Date expire = rightnow.getTime();

		// 如果找到，还需要判断是否有效.

		for (String code : codes) {
			Seal seal = mapSeal.get(code);
			if (seal != null && isTrackValid(seal, expire)) {
				// 有效，不用更新.
			} else {
				// 需要联网获取最新的信息.
				String str = EagleGPSUtils.trackVehicle(code);
				// 如果联网失败或者信息无效.返回一个"1"表示搜索无结果.
				if (str == null || str.equals("") || str.startsWith("1")) {
					if (seal != null) {
						// 还是需要更新mtime的值的.
						update(seal);
						mapSeal.put(code, seal);
					} else {
						mapSeal.put(code, null);
					}
				}
				Seal onlineSeal = null;
				try {
					onlineSeal = xml2seal(str);
				} catch (Exception e) {
					logger.catching(e);
					onlineSeal = null;
				}
				if (onlineSeal != null) {
					// 联网信息有效，写入数据库并返回.
					if (seal != null) {
						// 有旧记录，更新.
						update(onlineSeal);
						mapSeal.put(code, onlineSeal);
					} else {
						// 没有记录，添加.
						add(onlineSeal);
						mapSeal.put(code, onlineSeal);
					}
				} else if (seal != null) {
					// 还是需要更新mtime的值的.
					update(seal);
					mapSeal.put(code, seal);
				}
			}
		}
		return mapSeal;
	}

	@Override
	public ArrayList<Seal> list() {
		return dbimp.list();
	}

	// 检查记录是否有效.
	public boolean isTrackValid(Seal seal, Date expire) {

		// TODO 先实现简单逻辑，后面需要继续添加.
		// 如果GPS时间没有过期，说明记录有效.
		if (seal.getGpstime().after(expire)) {
			return true;
		}

		// 如果记录更新时间没有过期，也说明记录有效.
		if (seal.getMtime().after(expire)) {
			return true;
		}
		return false;
	}

	// 通过返回的xml字符串提取出seal对象.
	public Seal xml2seal(String str) {
		Seal seal = new Seal();
		try {
			Document document = DocumentHelper.parseText(str);
			Element seal_tag = (Element) document
					.selectSingleNode("//VehicleTrack");
			elment2seal(seal, seal_tag);
			return seal;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static void elment2seal(Seal seal, Element seal_tag)
			throws ParseException {
		seal.setCode(seal_tag.elementTextTrim("Vid"));
		seal.setStatus(2);
		seal.setLongitude(Double.parseDouble(seal_tag.elementTextTrim("Lon")));
		seal.setLatitude(Double.parseDouble(seal_tag.elementTextTrim("Lat")));
		seal.setCtime(new Date());
		seal.setMtime(new Date());
		seal.setMarkdel(false);
		seal.setRemark("");

		seal.setPlate(seal_tag.elementTextTrim("Plate"));

		try {
			seal.setGpstime(df.parse(seal_tag.elementTextTrim("GpsTime")));
		} catch (Exception e) {
			seal.setGpstime(df2.parse(seal_tag.elementTextTrim("GpsTime")));
		}

		seal.setSpeed(Integer.parseInt(seal_tag.elementTextTrim("Speed")));
		seal.setDirection(Double.parseDouble(seal_tag.elementTextTrim("Dir")));
		seal.setDaymiles(Double.parseDouble(seal_tag.elementTextTrim("MIL1")));
		seal.setMonthmiles(Double.parseDouble(seal_tag.elementTextTrim("MIL2")));
		seal.setOil(Integer.parseInt(seal_tag.elementTextTrim("oil")));
		seal.setEngst(seal_tag.elementTextTrim("St"));
		seal.setPoi(seal_tag.elementTextTrim("Lo"));
	}
}
