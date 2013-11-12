package com.exfd.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import com.exfd.domain.SealRecord;

public interface SealRecordDao {
	
	// 添加一个记录.
	void add(SealRecord sRecord);

	// 添加多个记录.
	void add(ArrayList<SealRecord> sRecords);
		
	// 删除一个seal的全部记录(通过code).
	void delete(String code);
		
	// 查找一个seal的全部记录.
	ArrayList<SealRecord> find(String code);
		
	// 查找一个seal的限定时间的记录.
	ArrayList<SealRecord> find(String code, Date beginDate, Date endDate);
	
	// 查找一个seal的限定时间的记录.
	ArrayList<SealRecord> find(String code, String beginDate, String endDate) throws ParseException;

}
