package com.exfd.service.impl;

import com.exfd.dao.SealDao;
import com.exfd.dao.impl.SealEagleDaoImpl;
import com.exfd.domain.Seal;
import com.google.gson.Gson;

public class Webservice {
	private SealDao dao = new SealEagleDaoImpl();	// 以后用工厂模式或者spring来解耦合.
	
	// web service endpoint.
	public String trackSeal(String code){
		Gson gson = new Gson();
		// 在dao层查找.
		Seal seal = dao.find(code);
		String strJson = "";
		if (seal != null) {
			strJson = gson.toJson(seal);
		}
		
		return strJson;
	}
}
