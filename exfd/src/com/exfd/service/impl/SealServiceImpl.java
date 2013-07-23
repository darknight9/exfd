package com.exfd.service.impl;

import com.exfd.dao.SealDao;
import com.exfd.dao.impl.SealDaoImpl;
import com.exfd.dao.impl.SealEagleDaoImpl;
import com.exfd.domain.Seal;

//对web层提供和铅封有关的服务.
public class SealServiceImpl {

	private SealDao dao = new SealEagleDaoImpl();	// 以后用工厂模式或者spring来解耦合.
	
	// 对web层提供查询铅封信息服务.
	public Seal track(String code) {
		
		// 在dao层查找.
		Seal seal = dao.find(code);

		return seal;
	}
}
