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

import com.exfd.dao.SealHistoryDao;
import com.exfd.domain.SealHistoryRecord;
import com.exfd.util.EagleGPSUtils;

public class SealEagleHistoryDaoImpl implements SealHistoryDao {
	
	private static Logger logger = LogManager.getLogger();
	
	// 1980-09-09
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
	
	// 内部使用SealDaoImpl.
	SealHistoryDaoImpl dbimp = new SealHistoryDaoImpl();

	@Override
	public void add(SealHistoryRecord sRecord) {
		dbimp.add(sRecord);
		
	}

	@Override
	public void add(ArrayList<SealHistoryRecord> sRecords) {
		dbimp.add(sRecords);

	}

	@Override
	public void delete(String code) {
		dbimp.delete(code);		
	}

	@Override
	public ArrayList<SealHistoryRecord> find(String code) {

		Calendar rightnow = Calendar.getInstance();
		Date nowDate = rightnow.getTime();
		
		rightnow.add(Calendar.DAY_OF_YEAR, -2);
		Date last = rightnow.getTime();
		return find(code, last, nowDate);
	}

	@Override
	public ArrayList<SealHistoryRecord> find(String code, Date beginDate,
			Date endDate) {

		// 先在数据库中查找.
		ArrayList<SealHistoryRecord> records = dbimp.find(code, beginDate, endDate);
		Calendar rightnow = Calendar.getInstance();
		
		// 规则：1天前的数据是失效的.
		rightnow.add(Calendar.DAY_OF_YEAR, -1);
		Date expire = rightnow.getTime();

		// 如果找到，还需要判断是否有效.
		if(records != null && !records.isEmpty()){
			
			logger.debug("find records. the number is [{}] for code[{}]", records.size(), code);
			
			// 如果有效就可以返回了.
			return records;
		}
		// 需要联网获取最新的信息.
		String str = EagleGPSUtils.trackHistory(code, df.format(beginDate), df.format(endDate));
		
		// 如果联网失败或者信息无效.
		if (str == null || str.equals("")) {
			if (records != null) {
				return records;
			} else {
				return null;
			}
		}
		
		if (records == null) {
			records = new ArrayList<SealHistoryRecord>();
		}
		xml2history(code, str, records);
		if (records != null && !records.isEmpty()) {
			add(records);
		} 
		return records;
	}
	
	// 通过返回的xml字符串提取出sealhistory对象.
	public void xml2history(String code, String str, ArrayList<SealHistoryRecord> records) {
		//int endpoint = str.lastIndexOf("</HistoryTrack>");
		//logger.debug("endpoint={}", endpoint);
		//String xmlString = str.substring(0, endpoint + 15);
		//xmlString = xmlString.concat("</HistoryTracks>");
		//logger.debug("string length={}, xmlString length={}", str.length(), xmlString.length());
		try {
			Document document = DocumentHelper.parseText(str);
			List<Element> tags =  document.selectNodes("/HistoryTracks/HistoryTrack");
			for (Element tagElement : tags) {
				SealHistoryRecord record = new SealHistoryRecord();
				elment2record(code, record, tagElement);
				records.add(record);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void elment2record(String code, SealHistoryRecord record, Element tagElement) throws ParseException {
		record.setCode(code);
		record.setLongitude(Double.parseDouble(tagElement.elementTextTrim("Long")));
		record.setLatitude(Double.parseDouble(tagElement.elementTextTrim("Lat")));
		record.setSpeed(Integer.parseInt(tagElement.elementTextTrim("Speed")));
		record.setDirection(Double.parseDouble(tagElement.elementTextTrim("Dir")));
		
		try {
			record.setGpstime(df.parse(tagElement.elementTextTrim("Time")));
		} catch (Exception e) {
			record.setGpstime(df2.parse(tagElement.elementTextTrim("Time")));
		}
		
		record.setPoi(tagElement.elementTextTrim("Loc"));
		record.setEngst(tagElement.elementTextTrim("Status"));	
		record.setMonthmiles(Double.parseDouble(tagElement.elementTextTrim("dis")));
		record.setOil(Integer.parseInt(tagElement.elementTextTrim("oil")));
		
	}

	@Override
	public ArrayList<SealHistoryRecord> find(String code, String beginDate,
			String endDate) throws ParseException {
		return find(code, df.parse(beginDate), df.parse(endDate));
	}
}
