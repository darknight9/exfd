package com.exfd.web.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.exfd.domain.Ship;
import com.exfd.service.impl.ShipServiceImpl;

public class TrackShipServlet extends HttpServlet {

	private static Logger logger = LogManager.getLogger();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// 回传code.
		String code = request.getParameter("code").trim();
		code = URLDecoder.decode(code, "utf-8");
		code = code.trim();
		request.setAttribute("code", code);

		String type = request.getParameter("type");
		request.setAttribute("type", type);

		// 记录请求.
		logger.info("Get Request Ship. code:[{}].type:[{}].", code, type);

		// 前置检查.
		boolean isParameterOK = checkParameter(code, type);
		if (!isParameterOK) {

			// 可以直接返回表示出错.
			logger.info("check Parameter fail. code:[{}].type:[{}].", code,
					type);
			request.getRequestDispatcher("/ship.jsp")
					.forward(request, response);
			return;
		}

		// 提供查询服务.
		ShipServiceImpl service = new ShipServiceImpl();
		List<Ship> ships = null;
		try {
			// 搜最多10条船.
			ships = service.track("", code, type, 1, 10);
		} catch (Exception e) {
			logger.catching(e);

			// 出错的时候直接返回.
			logger.error(
					"ShipServiceImpl.service get exception. code:[{}].type:[{}].",
					code, type);
			request.getRequestDispatcher("/ship.jsp")
					.forward(request, response);
			return;
		}

		if (ships != null && !ships.isEmpty()) {

			// TODO 先返回一条船.
			// 后面考虑加排序策略或者返回多条船.
			request.setAttribute("ship", ships.get(0));
			logger.info("ShipServiceImpl.service OK. code:[{}].type:[{}].",
					code, type);

			// 重定向到搜索结果页.
			request.getRequestDispatcher("/ship.jsp")
					.forward(request, response);
			return;
		} else {
			// 进行到这里说明没有找到, 直接返回.
			logger.info(
					"ShipServiceImpl.service Not Found. code:[{}].type:[{}].",
					code, type);
			request.getRequestDispatcher("/ship.jsp")
					.forward(request, response);
			return;
		}
	}

	private boolean checkParameter(String code, String type) {

		// mmsi是9位数字.
		if (type.equals("mmsi") && code.length() != 9) {
			return false;
		}
		// 4个字节的code是最短要求.
		// 超过50个字节也不服务.
		if (code.length() < 4 || code.length() > 50) {
			return false;
		}
		return true;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
