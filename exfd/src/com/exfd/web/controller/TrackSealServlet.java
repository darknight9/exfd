package com.exfd.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exfd.domain.Seal;
import com.exfd.domain.User;
import com.exfd.service.impl.BusinessServieImpl;
import com.exfd.service.impl.SealServiceImpl;

public class TrackSealServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		String code = request.getParameter("code");

		SealServiceImpl service = new SealServiceImpl();
		Seal seal = service.track(code);
		if(seal!=null){
			// TODO
			// 展现结果.
			request.setAttribute("seal", seal);
			// 重定向到搜索结果页.
			//response.sendRedirect(request.getContextPath()+"/WEB-INF/jsp/showseal.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/showseal.jsp").forward(request, response);
			return;
		}
		
		// 进行到这里说明没有找到.
		request.setAttribute("message", "没有找到对应的铅封！");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
