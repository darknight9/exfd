package com.exfd.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.exfd.domain.Seal;
import com.exfd.domain.Ship;
import com.exfd.service.impl.SealServiceImpl;
import com.exfd.service.impl.ShipServiceImpl;

public class TrackShipServlet extends HttpServlet {

	static Logger logger = LogManager.getLogger();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		String code = request.getParameter("code");
		String type = request.getParameter("type");
		code = new String(code.getBytes(), "UTF-8");
		logger.debug("get get GETTTTT code=[{}], type=[{}]", code, type);
		ShipServiceImpl service = new ShipServiceImpl();
		List<Ship> ships = service.track("", code, type, 1, 10);
		if(ships != null && !ships.isEmpty()){
			// TODO
			// 展现结果.
			request.setAttribute("shipnumbers", ships.size());
			request.setAttribute("ship", ships.get(0));
			request.setAttribute("code", code);
			request.setAttribute("type", type);

			// 重定向到搜索结果页.
			//response.sendRedirect(request.getContextPath()+"/WEB-INF/jsp/showseal.jsp");
			request.getRequestDispatcher("/ship.jsp").forward(request, response);
			return;
		}
		
		// 进行到这里说明没有找到.
		request.setAttribute("message", "没有找到对应的船！");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
