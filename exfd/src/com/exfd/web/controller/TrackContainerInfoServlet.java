package com.exfd.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.exfd.domain.Container;
import com.exfd.service.impl.ContainerServiceImpl;
import com.exfd.util.ContainerUtils;

public class TrackContainerInfoServlet extends HttpServlet {

	private static Logger logger = LogManager.getLogger();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// 设置返回类型.
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		// 参数归一化后回传.
		String code = request.getParameter("code").trim().toUpperCase();
		request.setAttribute("code", code);
		String company = request.getParameter("company");
		if (company == null || company.isEmpty()) {
			company = "DEFAULT";
		} else {
			company = company.trim().toUpperCase();
		}
		request.setAttribute("company", company);

		// 记录请求.
		logger.info("Get Request Cont. code:[{}].", code);

		// 前置检查.
		boolean isParameterOK = checkParameter(code, company);
		if (!isParameterOK) {

			// 可以直接返回表示出错.
			logger.info("check Parameter fail. code:[{}].", code);
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
			return;
		}
		if (container != null) {
			String strJson = container.getJsonString();

			PrintWriter out = response.getWriter();
			out.print(strJson);
			out.flush();
			logger.info(
					"ContainerServiceImpl.service OK. code:[{}].company:[{}].",
					code, company);
			return;
		} else {
			// 没有箱子信息，直接返回.
			logger.info(
					"ContainerServiceImpl.service Not Found. code:[{}].company:[{}].",
					code, company);
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
