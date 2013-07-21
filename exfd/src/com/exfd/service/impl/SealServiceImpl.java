package com.exfd.service.impl;

import com.exfd.dao.SealDao;
import com.exfd.dao.impl.SealDaoImpl;
import com.exfd.domain.Seal;

//对web层提供和铅封有关的服务.
public class SealServiceImpl {

	private SealDao dao = new SealDaoImpl();	// 以后用工厂模式或者spring来解耦合.
	
	// 对web层提供查询铅封信息服务.
	public Seal track(String code) {
		
		// 先在dao层查找.
		Seal seal = dao.find(code);
		
		// 如果找到，还需要判断是否有效.
		if(seal!=null){
			
			// TODO. 如果有效就可以返回了.
			
		}
		
		// TODO. 需要联网获取最新的信息.
		
		// TODO. 如果联网失败或者信息无效，直接返回null.
		
		// TODO. 联网信息有效，写入数据库并返回.

		return seal;
	}
}
