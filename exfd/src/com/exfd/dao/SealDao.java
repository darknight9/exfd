package com.exfd.dao;

import java.util.ArrayList;
import java.util.Map;

import com.exfd.domain.Seal;

// Seal数据处理接口.
public interface SealDao {

	// 添加一个seal记录.
	void add(Seal seal);

	// 添加多个seal记录.
	void add(ArrayList<Seal> seals);
	
	// 删除一个seal记录.
	void delete(Seal seal);
	
	// 删除一个seal记录(通过code).
	void delete(String code);
	
	// 删除多个seal记录.
	void delete(ArrayList<String> codes);
	
	// 更新一条seal的记录.
	void update(Seal seal);
	
	// 更新多条seal的记录.
	void update(ArrayList<Seal> seals);
	
	// 查找一个seal记录.
	Seal find(String code);
	
	// 根据uid查找多个seal记录.
	public ArrayList<Seal> findMany(Long uid, int first, int count);
	
	// 查找多个seal记录.
	Map<String, Seal> find(ArrayList<String> codes);
	
	// 列出全部的seal记录.
	public ArrayList<Seal> list();


}