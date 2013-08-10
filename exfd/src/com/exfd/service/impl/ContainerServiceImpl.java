package com.exfd.service.impl;

import org.apache.logging.log4j.Logger;

import com.exfd.dao.ContainerDao;
import com.exfd.dao.impl.ContainerDaoImpl;
import com.exfd.domain.Container;
import com.exfd.exception.LineQueryException;
import com.exfd.ship.first.LineMAERSK;

public class ContainerServiceImpl {
	private ContainerDao dao = new ContainerDaoImpl();	// 以后用工厂模式或者spring来解耦合.
	
	// 对web层提供查询信息服务.
	public Container track(String code) throws LineQueryException {
		
		// 在dao层查找.
		Container container = dao.find(code);

		if (container == null) {
			LineMAERSK line = new LineMAERSK();
			container = line.Query(code);
		}
		return container;
	}
}
