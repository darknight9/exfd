package com.exfd.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.exfd.dao.SealDao;
import com.exfd.dao.SealRecordDao;
import com.exfd.dao.impl.SealEagleDaoImpl;
import com.exfd.dao.impl.SealRecordEagleDaoImpl;
import com.exfd.domain.Seal;
import com.exfd.domain.SealRecord;

//对web层提供和铅封有关的服务.
public class SealServiceImpl {

	private static Logger logger = LogManager.getLogger();
	
	private SealDao dao = new SealEagleDaoImpl();	// 以后用工厂模式或者spring来解耦合.
	private SealRecordDao historyDao = new SealRecordEagleDaoImpl();
	
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

	// 对web层提供查询铅封集合服务.
	public ArrayList<Seal> trackSealByCid(String cid) {
		
		// 在dao层查找.
		Long uid = Long.parseLong(cid);
		ArrayList<Seal> records = dao.findMany(uid, 0, 10);
		if (records != null && !records.isEmpty()) {
			logger.info("SEALCID[{}] find. Very Good.", cid);
		} else {
			logger.info("SEALCID[{}] not find.", cid);
		}
		return records;
	}
	
	// 对web层提供查询铅封历史服务.
	public ArrayList<SealRecord> trackHistory(String code) {
		
		// 在dao层查找.
		ArrayList<SealRecord> records = historyDao.find(code);
		if (records != null && !records.isEmpty()) {
			logger.info("SEALHISTORY[{}] find. Very Good.", code);
		} else {
			logger.info("SEALHISTORY[{}] not find.", code);
		}
		return records;
	}
	
	// 对web层提供查询铅封历史服务.
	public ArrayList<SealRecord> trackHistory(String code, String beginDate
			, String endDate) {
		
		// 在dao层查找.
		ArrayList<SealRecord> records;
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
