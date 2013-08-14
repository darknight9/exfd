package com.exfd.service.impl;

import com.exfd.dao.ContainerDao;
import com.exfd.dao.SealDao;
import com.exfd.dao.impl.ContainerLineDaoImpl;
import com.exfd.dao.impl.SealEagleDaoImpl;
import com.exfd.domain.Container;
import com.exfd.domain.Seal;
import com.google.gson.Gson;

public class Webservice {
	private SealDao sealDao = new SealEagleDaoImpl();	// 以后用工厂模式或者spring来解耦合.
	private ContainerDao containerDao = new ContainerLineDaoImpl();	// 以后用工厂模式或者spring来解耦合.
	
	/**
	 * 查找铅封信息.
	 * @param code 传入的铅封号。
	 * @return 表示铅封信息的Json字符串.
	 */
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
	
	/**
	 * 查找集装箱信息.
	 * @param code 传入的集装箱编号.
	 * @return 表示集装箱信息的Json字符串.
	 */
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
