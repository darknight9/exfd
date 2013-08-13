package com.exfd.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exfd.domain.Container;
import com.exfd.domain.Ship;
import com.exfd.service.impl.ContainerServiceImpl;
import com.exfd.service.impl.ShipServiceImpl;
import com.google.gson.Gson;

public class TrackContainerInfoServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		String code = request.getParameter("code");
		
		ContainerServiceImpl service = new ContainerServiceImpl();
		Container cbox;
		try {
			cbox = service.track(code);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "没有找到对应的箱子！");
			request.setAttribute("exception", e.toString());
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		if(cbox!=null){
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
		    
			//String strJson = cbox.getJsonString();
			
			//向后兼容.
			String strJson = cbox.getHttpresult();
			
			// Get printerwriter object from response to write json object to the output stream.
		    PrintWriter out = response.getWriter();
		    out.print(strJson);
		    out.flush();
		    return;
		} else {
			request.setAttribute("message", "没有找到对应的箱子！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
