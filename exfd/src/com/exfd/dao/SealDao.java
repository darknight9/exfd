package com.exfd.dao;

import java.util.ArrayList;

import com.exfd.domain.Seal;

public interface SealDao {

	// 添加一个seal记录.
	void add(Seal seal);

	// 查找一个seal记录.
	Seal find(String code);
	
	public ArrayList<Seal> list();

	// 更新一条seal的记录.
	public void update(Seal seal);

}