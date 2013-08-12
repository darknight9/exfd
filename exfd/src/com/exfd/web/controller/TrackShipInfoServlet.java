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

import com.exfd.domain.Ship;
import com.exfd.service.impl.ShipServiceImpl;
import com.google.gson.Gson;

public class TrackShipInfoServlet extends HttpServlet {

	static Logger logger = LogManager.getLogger();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String code = request.getParameter("code");
		String type = request.getParameter("type");
		
		logger.debug("SHIPDETAIL[{}][{}] the request is received.", code, type);
		ShipServiceImpl service = new ShipServiceImpl();
		List<Ship> ships = service.track("", code, type, 1, 10);
		if(ships != null && !ships.isEmpty()){
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
		    
			Gson gson = new Gson();
			String strJson = gson.toJson(ships.get(0));
			
			// Get printerwriter object from response to write json object to the output stream.
		    PrintWriter out = response.getWriter();
		    out.print(strJson);
		    out.flush();
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
