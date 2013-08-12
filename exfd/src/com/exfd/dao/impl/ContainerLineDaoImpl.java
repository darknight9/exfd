package com.exfd.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.exfd.dao.ContainerDao;
import com.exfd.domain.Container;
import com.exfd.util.ContainerUtils;

public class ContainerLineDaoImpl implements ContainerDao {

	private static Logger logger = LogManager.getLogger();

	// 1980-09-09
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");

	// 内部使用ContainerDaoImpl.
	ContainerDaoImpl dbimp = new ContainerDaoImpl();

	@Override
	public void add(Container container) {
		dbimp.add(container);
	}

	@Override
	public void delete(String code) {
		dbimp.delete(code);
	}

	@Override
	public void update(Container container) {
		dbimp.update(container);
	}

	@Override
	public void updateOrAdd(Container container) {
		dbimp.updateOrAdd(container);
	}

	@Override
	public Container find(String code) {
		
		// 先在数据库中查找.
		Container container = dbimp.find(code);
		
		// 规则：7天前的数据是失效的.
		Calendar rightnow = Calendar.getInstance();
		rightnow.add(Calendar.DAY_OF_YEAR, -7);
		Date expire = rightnow.getTime();

		// 如果找到，还需要判断是否有效.
		if (container != null) {

			logger.debug("CONT[{}].find container code [{}] in db", code, code);

			// 如果有效就可以返回了.
			if (isTrackValid(container, expire)) {
				logger.debug("CONT[{}].find container code [{}] in db valid, return.", code, code);
				return container;
			}
		}
		
		// 需要联网获取最新的信息.
		String page = ContainerUtils.GetPage(code);
		
		Container onlineContainer = null;
		if (page != null && !page.isEmpty()) {
			logger.debug("CONT[{}]. THE FIRST 300 BYTES OF THE PAGE IS: [{}]", code, page.substring(0,300));
			onlineContainer = ContainerUtils.GetContainerByPage(code, page);
		} else {
			logger.debug("CONT[{}]. GetPage return empty page.", code);
		}
		
		// 如果联网失败或者信息无效.
		if (onlineContainer == null) {
			if (container != null) {
				
				// 更新记录的mtime值.
				update(container);
				return container;
			} else {
				return null;
			}
		} else {
			// 联网信息有效，写入数据库并返回.
			updateOrAdd(onlineContainer);
			return onlineContainer;
		}
	}

	private boolean isTrackValid(Container container, Date expire) {
		
		// 如果记录更新时间没有过期，说明记录有效.
		if (container.getMtime().after(expire)){
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Container> list() {
		return dbimp.list();
	}

}
