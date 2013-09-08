package com.exfd.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.exfd.domain.Container;
import com.exfd.service.impl.ContainerServiceImpl;
import com.exfd.util.ContainerUtils;

// 实际这个没有使用.
public class TrackContainerServlet extends HttpServlet {

	private static Logger logger = LogManager.getLogger();

	// 注意每一个return前都有dispatcher!
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// 参数归一化后回传.
		String code = request.getParameter("code").trim().toUpperCase();
		request.setAttribute("code", code);
		String company = request.getParameter("company").trim().toUpperCase();
		request.setAttribute("company", company);

		// 记录请求.
		logger.info("Get Request Cont. code:[{}].", code);

		// 前置检查.
		boolean isParameterOK = checkParameter(code, company);
		if (!isParameterOK) {

			// 可以直接返回表示出错.
			logger.info("check Parameter fail. code:[{}].", code);
			request.getRequestDispatcher("/container.jsp").forward(request,
					response);
			return;
		}
		
		// 设置了company后再查询.
		if (company.equals("DEFAULT")) {
			company = ContainerUtils.getCompany(code);
			if (company == null) {
				company = "";
			}
		}

		// 提供查询服务.
		ContainerServiceImpl service = new ContainerServiceImpl();
		Container container = null;
		try {
			container = service.track(code, company);
		} catch (Exception e) {
			logger.catching(e);
			// 出错的时候直接返回.
			logger.error(
					"ContainerServiceImpl.service get exception. code:[{}].company:[{}].",
					code, company);
			request.getRequestDispatcher("/container.jsp").forward(request,
					response);
			return;
		}
		if (container != null) {
			request.setAttribute("container", container);
			logger.info(
					"ContainerServiceImpl.service OK. code:[{}].company:[{}].",
					code, company);
			request.getRequestDispatcher("/container.jsp").forward(request,
					response);
			return;
		} else {
			// 没有箱子信息，直接返回.
			logger.info(
					"ContainerServiceImpl.service Not Found. code:[{}].company:[{}].",
					code, company);
			request.getRequestDispatcher("/container.jsp").forward(request,
					response);
			return;
		}
	}

	// 检查参数.
	private boolean checkParameter(String code, String company) {

		// container no.要求符合格式： 'ABC' + 'U' + '1234567'
		// 这里只做简单检查，不做严格检查.
		if (code.length() == 11 && code.charAt(3) == 'U') {
			return true;
		}

		// company暂时不做检查.
		return false;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
