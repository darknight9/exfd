package com.exfd.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.exfd.dao.SealDao;
import com.exfd.dao.SealHistoryDao;
import com.exfd.dao.impl.SealEagleDaoImpl;
import com.exfd.dao.impl.SealEagleHistoryDaoImpl;
import com.exfd.domain.Seal;
import com.exfd.domain.SealHistoryRecord;

//对web层提供和铅封有关的服务.
public class SealServiceImpl {

	private static Logger logger = LogManager.getLogger();
	
	private SealDao dao = new SealEagleDaoImpl();	// 以后用工厂模式或者spring来解耦合.
	private SealHistoryDao historyDao = new SealEagleHistoryDaoImpl();
	
	// 对web层提供查询铅封信息服务.
	public Seal track(String code) {
		
		// 在dao层查找.
		Seal seal = dao.find(code);
		if (seal != null) {
			logger.info("SEAL[{}] find. Very Good.", code);
		} else {
			logger.info("SEAL[{}] not find.", code);
		}
		return seal;
	}
	
	// 对web层提供查询铅封历史服务.
	public ArrayList<SealHistoryRecord> trackHistory(String code) {
		
		// 在dao层查找.
		ArrayList<SealHistoryRecord> records = historyDao.find(code);
		if (records != null && !records.isEmpty()) {
			logger.info("SEALHISTORY[{}] find. Very Good.", code);
		} else {
			logger.info("SEALHISTORY[{}] not find.", code);
		}
		return records;
	}
	
	// 对web层提供查询铅封历史服务.
	public ArrayList<SealHistoryRecord> trackHistory(String code, String beginDate
			, String endDate) {
		
		// 在dao层查找.
		ArrayList<SealHistoryRecord> records;
		try {
			records = historyDao.find(code, beginDate, endDate);
		} catch (ParseException e) {
			logger.catching(e);
			records = null;
		}
		if (records != null && !records.isEmpty()) {
			logger.info("SEALHISTORY[{}] find. Very Good.", code);
		} else {
			logger.info("SEALHISTORY[{}] not find.", code);
		}
		return records;
	}
	

	
}
