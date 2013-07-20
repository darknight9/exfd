package com.exfd.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exfd.domain.User;
import com.exfd.service.impl.BusinessServieImpl;

// 处理登陆请求.
public class LoginServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		BusinessServieImpl service = new BusinessServieImpl();
		User user = service.login(username, password);
		if(user!=null){
			request.getSession().setAttribute("user", user);
			// 让用户登陆成功后，跳转到首页.
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			return;
		}
		
		request.setAttribute("message", "用户名或密码错误！");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
