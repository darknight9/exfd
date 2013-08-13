package com.exfd.web.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.exfd.domain.Seal;
import com.exfd.domain.SealHistoryRecord;
import com.exfd.service.impl.SealServiceImpl;

public class TrackSealServlet extends HttpServlet {

	private static Logger logger = LogManager.getLogger();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// 回传code.
		String code = request.getParameter("code").trim();
		request.setAttribute("code", code);
		String beginString = request.getParameter("beginTime");
		request.setAttribute("beginTime", beginString);
		String endString = request.getParameter("endTime");
		request.setAttribute("endTime", endString);

		// 记录请求.
		if (beginString != null && endString != null) {
			logger.info(
					"Get Request Seal. code:[{}].beginTime:[{}].endTime:[{}]",
					code, beginString, endString);
		} else {
			logger.info("Get Request Seal. code:[{}].", code);
		}

		// 前置检查.
		boolean isParameterOK = checkParameter(code, beginString, endString);
		if (!isParameterOK) {
			// 可以直接返回表示出错.
			logger.info("check Parameter fail. code:[{}].", code);
			request.getRequestDispatcher("/seal.jsp")
					.forward(request, response);
			return;
		}

		// 提供查询服务.
		SealServiceImpl service = new SealServiceImpl();

		// 查询历史信息.
		if (beginString != null && endString != null && !beginString.equals("")
				&& !endString.equals("")) {

			// 有时间参数，表明是查历史信息.
			ArrayList<SealHistoryRecord> records = service.trackHistory(code,
					beginString, endString);
			if (records != null) {

				// 展现结果.
				request.setAttribute("records", records);
				// 重定向到搜索结果页.
				logger.info("SealServiceImpl.trackHistory OK. code:[{}].", code);
				request.getRequestDispatcher("/seal.jsp").forward(request,
						response);
				return;
			} else {
				// 进行到这里说明没有找到.
				logger.info(
						"SealServiceImpl.trackHistory Not Found. code:[{}].",
						code);
				request.getRequestDispatcher("/seal.jsp").forward(request,
						response);
				return;
			}
		} else {
			// 没有时间参数，查询铅封位置信息.
			Seal seal = service.track(code);
			if (seal != null) {

				// 展现结果.
				request.setAttribute("seal", seal);
				logger.info(
						"SealServiceImpl.track OK. code:[{}].Long:[{}].Lati:[{}]",
						code, seal.getLongitude(), seal.getLatitude());

				// 重定向到搜索结果页.
				request.getRequestDispatcher("/seal.jsp").forward(request,
						response);
				return;
			} else {
				// 进行到这里说明没有找到.
				logger.info("SealServiceImpl.track Not Found. code:[{}].", code);
				request.getRequestDispatcher("/seal.jsp").forward(request,
						response);
				return;
			}
		}
	}

	private boolean checkParameter(String code, String beginString,
			String endString) {

		// 目前要求：code是4位数字.
		// 这里只做简单检查，不做严格检查.
		// 这里没有使用beginString等，以后检查的时候需要检查日期格式及是否是null.
		if (code.length() == 4) {
			return true;
		}
		return false;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
