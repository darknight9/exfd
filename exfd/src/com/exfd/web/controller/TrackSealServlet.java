package com.exfd.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.exfd.domain.Seal;
import com.exfd.domain.SealHistoryRecord;
import com.exfd.domain.User;
import com.exfd.service.impl.BusinessServieImpl;
import com.exfd.service.impl.SealServiceImpl;

public class TrackSealServlet extends HttpServlet {

	static Logger logger = LogManager.getLogger();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		String code = request.getParameter("code");
		String beginString = request.getParameter("beginTime");
		String endString = request.getParameter("endTime");

		SealServiceImpl service = new SealServiceImpl();
		if (beginString != null && endString!= null && 
				!beginString.equals("") && !endString.equals("")) {
			ArrayList<SealHistoryRecord> records = service.trackHistory(code, beginString, endString);
			if(records != null){
				// TODO
				// 展现结果.
				request.setAttribute("records", records);
				// 重定向到搜索结果页.
				request.getRequestDispatcher("/seal.jsp").forward(request, response);
				return;
			} else {
				// 进行到这里说明没有找到.
				request.setAttribute("message", "没有找到对应的铅封的历史信息！");
				request.getRequestDispatcher("/message.jsp").forward(request, response);
			}
		} else {
			Seal seal = service.track(code);
			if(seal != null){
				// TODO
				// 展现结果.
				request.setAttribute("seal", seal);
				logger.error("seal is send:{},{},{}", seal.getCode(), seal.getLongitude(), seal.getLatitude());
				
				// 重定向到搜索结果页.
				//response.sendRedirect(request.getContextPath()+"/seal.jsp");
				request.getRequestDispatcher("/seal.jsp").forward(request, response);
				return;
			}
			
			// 进行到这里说明没有找到.
			request.setAttribute("message", "没有找到对应的铅封！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
