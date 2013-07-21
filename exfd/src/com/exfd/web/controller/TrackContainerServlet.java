package com.exfd.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exfd.domain.Seal;
import com.exfd.domain.User;
import com.exfd.exception.UserExistException;
import com.exfd.service.impl.BusinessServieImpl;
import com.exfd.service.impl.SealServiceImpl;
import com.exfd.util.WebUtils;
import com.exfd.web.formbean.RegisterForm;

public class TrackContainerServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		String code = request.getParameter("code");

		SealServiceImpl service = new SealServiceImpl();
		Seal seal = service.track(code);
		if(seal!=null){
			// TODO
			// 展现结果.
			return;
		}
		
		// 进行到这里说明没有找到.
		request.setAttribute("message", "没有找到对应的箱子！");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
