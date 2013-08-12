package com.exfd.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.exfd.dao.ContainerDao;
import com.exfd.dao.impl.ContainerLineDaoImpl;
import com.exfd.domain.Container;

public class ContainerServiceImpl {

	private static Logger logger = LogManager.getLogger();

	private ContainerDao dao = new ContainerLineDaoImpl();	// 以后用工厂模式或者spring来解耦合.
	
	// 对web层提供查询信息服务.
	public Container track(String code) {
		
		// 在dao层查找.
		Container container = dao.find(code);
		
		if (container != null) {
			logger.info("CONT[{}] find. Very Good.", code);
		} else {
			logger.info("CONT[{}] not find.", code);
		}

		return container;
	}
}
