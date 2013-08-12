package com.exfd.util;

import java.lang.reflect.Method;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.exfd.domain.Container;

public class ContainerUtils {

	static Logger logger = LogManager.getLogger();

	static {
		LoadConfig();
	}

	static private Configuration config = null;
	static private Configuration levelconfig = null;

	static public String GetPage(String code) {
		String company = getCompany(code);
		if (company != null) {
			return GetPage(code, company);
		}
		return null;
	}
	
	static public Container GetContainer(String code) {
		String company = getCompany(code);
		if (company != null) {
			return GetContainer(code, company);
		}
		return null;
	}
	
	static public Container GetContainerByPage(String code, String page) {
		String company = getCompany(code);
		if (company != null) {
			return GetContainerByPage(code, company, page);
		}
		return null;
	}

	static public String GetPage(String code, String company) {
		String level = getCompanyLevel(company);
		String className = "com.exfd.ship." + level + ".Line" + company;
		try {
			Class lineClass = Class.forName(className);
			Class[] argsClass = new Class[1];
			argsClass[0] = String.class;
			Method method = lineClass.getMethod("GetPage", argsClass);
			logger.debug(method);
			Object[] args = new Object[1];
			args[0] = code;
			Object lineObject = lineClass.newInstance();
			logger.debug("invoke {}.GetPage(\"{}\")", className, code);
			return (String) method.invoke(lineObject, args);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	static public Container GetContainer(String code, String company) {
		String level = getCompanyLevel(company);
		String className = "com.exfd.ship." + level + ".Line" + company;
		try {
			Class lineClass = Class.forName(className);
			Class[] argsClass = new Class[1];
			argsClass[0] = String.class;
			Method method = lineClass.getMethod("GetContainer", argsClass);
			logger.debug(method);
			Object[] args = new Object[1];
			args[0] = code;
			Object lineObject = lineClass.newInstance();
			logger.debug("invoke {}.GetContainer(\"{}\")", className, code);
			return (Container) method.invoke(lineObject, args);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	static public Container GetContainerByPage(String code, String company, String page) {
		String level = getCompanyLevel(company);
		String className = "com.exfd.ship." + level + ".Line" + company;
		try {
			Class lineClass = Class.forName(className);
			Class[] argsClass = new Class[2];
			argsClass[0] = String.class;
			argsClass[1] = String.class;
			Method method = lineClass.getMethod("GetContainerByPage", argsClass);
			logger.debug(method);
			Object[] args = new Object[2];
			args[0] = code;
			args[1] = page;
			Object lineObject = lineClass.newInstance();
			logger.debug("invoke {}.GetContainerByPage(\"{}\")", className, code);
			return (Container) method.invoke(lineObject, args);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	static public void LoadConfig() {
		try {
			config = new PropertiesConfiguration("containercode.properties");
			levelconfig = new PropertiesConfiguration("shiplevel.properties");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	static public String getCompany(String code) {
		String prefix = code.substring(0, 4);
		if (config == null) {
			LoadConfig();
		}
		return config.getString(prefix);
	}

	static public String getCompanyLevel(String company) {
		if (levelconfig == null) {
			LoadConfig();
		}
		return levelconfig.getString(company);
	}
}
