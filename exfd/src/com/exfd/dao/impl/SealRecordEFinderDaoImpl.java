package com.exfd.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.exfd.dao.SealRecordDao;
import com.exfd.domain.SealRecord;
import com.exfd.util.EagleGPSUtils;
import com.exfd.util.MarkerUitls;

public class SealRecordEFinderDaoImpl implements SealRecordDao {
	
	private static Logger logger = LogManager.getLogger();
	
	// 1980-09-09
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	static SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
	
	// 内部使用SealDaoImpl.
	SealRecordDaoImpl dbimp = new SealRecordDaoImpl();

	@Override
	public void add(SealRecord sRecord) {
		dbimp.add(sRecord);
		
	}

	@Override
	public void add(ArrayList<SealRecord> sRecords) {
		dbimp.add(sRecords);

	}

	@Override
	public void delete(String code) {
		dbimp.delete(code);		
	}

	@Override
	public ArrayList<SealRecord> find(String code) {

		Calendar rightnow = Calendar.getInstance();
		Date nowDate = rightnow.getTime();
		
		rightnow.add(Calendar.DAY_OF_YEAR, -2);
		Date last = rightnow.getTime();
		return find(code, last, nowDate);
	}

	@Override
	public ArrayList<SealRecord> find(String code, Date beginDate,
			Date endDate) {

		// 返回值.
		// 先获得全部记录.
		ArrayList<SealRecord> sRecords = dbimp.find(code,beginDate,endDate);
		
		ArrayList<SealRecord> limitRecords = normalize(sRecords, beginDate, endDate);
		// 调整.
		// @TODO 现在先不调整。
		return limitRecords;
	}

	@Override
	public ArrayList<SealRecord> find(String code, String beginDate,
			String endDate) throws ParseException {
		return find(code, df.parse(beginDate), df.parse(endDate));
	}
	
	public ArrayList<SealRecord> findAll(String code) {

		return dbimp.find(code);
	}
	
	// 归一化.
	public ArrayList<SealRecord> normalize(ArrayList<SealRecord> sRecords, Date beginDate,
			Date endDate) {
		
		if (sRecords == null) {
			return null;
		}
		
		// 少于20个点，直接返回.
		if (sRecords.size() <= 20) {
			return sRecords;
		}

		long beginTime = beginDate.getTime();
		long endTime = endDate.getTime();
		long distance = endTime - beginTime;
		
		// 时间间隔在2小时内，直接返回全部记录.
		if (distance <= 1000*60*60*2L) {
			return sRecords;
		}
		
		// 默认第一个围栏是开始时间，默认间隔时间是1天。
		long barTime = beginTime;
		long interval = 1000*60*60*24L;	
		
		// 间隔：2小时-6小时，每0.5小时一次记录。
		if (distance <= 1000*60*60*6L ) {
			barTime = (beginTime/(1000*60*30L) + 1)*1000*60*30L;
			interval = 1000*60*30L;
		} else if (distance <= 1000*60*60*24L) {	// 6-24小时，每1小时1次记录.
			barTime = (beginTime/(1000*60*60L) + 1)*1000*60*60L;
			interval = 1000*60*60L;			
		} else if (distance <= 1000*60*60*24*5L) {	// 1-5天，每6小时1次记录.
			barTime = (beginTime/(1000*60*60*6L) + 1)*1000*60*60*6L;
			interval = 1000*60*60*6L;			
		} else if (distance <= 1000*60*60*24*32L) {	// 5-32天，每1天1次记录.
			barTime = (beginTime/(1000*60*60*24L) + 1)*1000*60*60*24L;
			interval = 1000*60*60*24L;			
		} else {		// 1个月以上，先不支持。默认变成间隔1个月记录一次了。
			barTime = (beginTime/(1000*60*60*24*30L) + 1)*1000*60*60*24*30L;
			interval = 1000*60*60*24*30L;	
		}
		
		// 多于20个点，开始减少返回的点.
		ArrayList<SealRecord> limitRecords = new ArrayList<SealRecord>();
		
		// 第一个点插入.
		limitRecords.add(sRecords.get(0));
		
		// 中间点插入
		int count = sRecords.size();
		for (int i = 1; i < count - 1; i++) {
			SealRecord record = sRecords.get(i);
			long gpsTime = record.getGpstime().getTime();
			if (gpsTime >= barTime) {
				limitRecords.add(record);
				barTime += interval;
			}
		}
		
		// 最后一个点插入.
		if (count > 1) {
			limitRecords.add(sRecords.get(count - 1));
		}
		return limitRecords;
		
	}
}
