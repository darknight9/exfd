package com.exfd.service.impl;

import com.exfd.dao.ContainerDao;
import com.exfd.dao.SealDao;
import com.exfd.dao.impl.ContainerLineDaoImpl;
import com.exfd.dao.impl.SealEagleDaoImpl;
import com.exfd.domain.Container;
import com.exfd.domain.Seal;
import com.exfd.exception.LineQueryException;
import com.google.gson.Gson;

public class Webservice {
	private SealDao sealDao = new SealEagleDaoImpl();	// 以后用工厂模式或者spring来解耦合.
	private ContainerDao containerDao = new ContainerLineDaoImpl();	// 以后用工厂模式或者spring来解耦合.
	
	// web service endpoint.
	public String trackSeal(String code){
		Gson gson = new Gson();
		// 在dao层查找.
		Seal seal = sealDao.find(code);
		String strJson = "";
		if (seal != null) {
			strJson = gson.toJson(seal);
		}
		
		return strJson;
	}
	
	// 对web层提供查询信息服务.
	public String trackContainer(String code) {
		
		// 在dao层查找.
		Container container = containerDao.find(code);
		String strJson = "";
		if (container != null) {
			strJson = container.getJsonString();
		}
		return strJson;
	}
}
