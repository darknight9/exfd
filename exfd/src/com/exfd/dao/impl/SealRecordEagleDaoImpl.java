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

public class SealRecordEagleDaoImpl implements SealRecordDao {
	
	private static Logger logger = LogManager.getLogger();
	
	// 1980-09-09
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
		ArrayList<SealRecord> sRecords = new ArrayList<SealRecord>();
		
		// 先获得标点.
		ArrayList<Date> arDates = MarkerUitls.getMidTime(beginDate, endDate, 7);
		MarkerUitls.roundHour(arDates);
		ArrayList<Date> beginDates = new ArrayList<Date>();
		ArrayList<Date> endDates = new ArrayList<Date>();
		MarkerUitls.getBound(arDates, beginDates, endDates);
		
		
		for (int i = 0; i < arDates.size(); i++) {
			SealRecord record = findOne(code, beginDates.get(i), endDates.get(i), false);
			if (record != null) {
				sRecords.add(record);
			}
		}
		return sRecords;
	}
	
	// 返回null表示没有找到位置信息.
	public SealRecord findOne(String code, Date beginDate,
			Date endDate, boolean isNew) {

		// 先在数据库中查找.
		SealRecord record = dbimp.findOne(code, beginDate, endDate, isNew);
		if (record != null) {
			
			// 找到了，直接返回.
			return record;
		}
		// 需要联网获取最新的信息.
		String str = EagleGPSUtils.trackHistory(code, df.format(beginDate), df.format(endDate));
		
		// 如果联网失败或者信息无效.
		if (str == null || str.equals("")) {
				return null;
		}
		ArrayList<SealRecord> records = new ArrayList<SealRecord>();
		xml2history(code, str, records);
		if (records.isEmpty()) {
			return null;
		}
		if (isNew) {
			record = records.get(records.size()-1);
		} else {
			record = records.get(0);
		}
		
		// 记录入库，然后返回.
		add(record);
		return record;
	}
	
	// 通过返回的xml字符串提取出sealhistory对象.
	public void xml2history(String code, String str, ArrayList<SealRecord> records) {
		//int endpoint = str.lastIndexOf("</HistoryTrack>");
		//logger.debug("endpoint={}", endpoint);
		//String xmlString = str.substring(0, endpoint + 15);
		//xmlString = xmlString.concat("</HistoryTracks>");
		//logger.debug("string length={}, xmlString length={}", str.length(), xmlString.length());
		try {
			Document document = DocumentHelper.parseText(str);
			List<Element> tags =  document.selectNodes("/HistoryTracks/HistoryTrack");
			for (Element tagElement : tags) {
				SealRecord record = new SealRecord();
				elment2record(code, record, tagElement);
				records.add(record);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void elment2record(String code, SealRecord record, Element tagElement) throws ParseException {
		record.setCode(code);
		record.setLongitude(Double.parseDouble(tagElement.elementTextTrim("Long")));
		record.setLatitude(Double.parseDouble(tagElement.elementTextTrim("Lat")));
		
		try {
			record.setGpstime(df.parse(tagElement.elementTextTrim("Time")));
		} catch (Exception e) {
			record.setGpstime(df2.parse(tagElement.elementTextTrim("Time")));
		}
		
		record.setPoi(tagElement.elementTextTrim("Loc"));
		
	}

	@Override
	public ArrayList<SealRecord> find(String code, String beginDate,
			String endDate) throws ParseException {
		return find(code, df.parse(beginDate), df.parse(endDate));
	}
}
