package com.exfd.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exfd.domain.Seal;
import com.exfd.domain.SealHistoryRecord;
import com.exfd.service.impl.SealServiceImpl;
import com.google.gson.Gson;

public class TrackSealInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String code = request.getParameter("code");
		String beginString = request.getParameter("beginTime");
		String endString = request.getParameter("endTime");

		SealServiceImpl service = new SealServiceImpl();
		if (beginString != null && endString != null && !beginString.equals("")
				&& !endString.equals("")) {
			ArrayList<SealHistoryRecord> records = service.trackHistory(code,
					beginString, endString);
			if (records != null) {
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
			    
				Gson gson = new Gson();
				String strJson = gson.toJson(records);
				
				// Get printerwriter object from response to write json object to the output stream.
			    PrintWriter out = response.getWriter();
			    out.print(strJson);
			    out.flush();
				return;
			} else {
				// 进行到这里说明没有找到.
				request.setAttribute("message", "没有找到对应的铅封的历史信息！");
				request.getRequestDispatcher("/message.jsp").forward(request,
						response);
			}
		} else {
			Seal seal = service.track(code);
			if (seal != null) {
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				
				Gson gson = new Gson();
				String strJson = gson.toJson(seal);
				
				// Get printerwriter object from response to write json object to the output stream.
			    PrintWriter out = response.getWriter();
			    out.print(strJson);
			    out.flush();
				return;
			}

			// 进行到这里说明没有找到.
			request.setAttribute("message", "没有找到对应的铅封！");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
