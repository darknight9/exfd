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
import com.exfd.domain.SealRecord;
import com.exfd.service.impl.SealServiceImpl;
import com.google.gson.Gson;

public class TrackSealInfoServlet extends HttpServlet {

	private static Logger logger = LogManager.getLogger();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// 设置返回类型.
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		// 回传code.
		String code = request.getParameter("code");
		if (code != null) {
			code = code.trim();
		}
		request.setAttribute("code", code);
		
		String cid = request.getParameter("cid");
		if (cid != null) {
			cid = cid.trim();
		}
		request.setAttribute("cid", cid);
		String beginString = request.getParameter("beginTime");
		request.setAttribute("beginTime", beginString);
		String endString = request.getParameter("endTime");
		request.setAttribute("endTime", endString);

		// 记录请求.
		if (code != null && beginString != null && endString != null) {
			logger.info(
					"Get Request Seal. code:[{}].beginTime:[{}].endTime:[{}]",
					code, beginString, endString);
		} else if (code != null) {
			logger.info("Get Request Seal. code:[{}].", code);
		} else if (cid != null) {
			logger.info("Get Request Seal. cid:[{}].", cid);
		}

		// 前置检查.
		boolean isParameterOK = checkParameter(code, cid, beginString,
				endString);
		if (!isParameterOK) {
			// 可以直接返回表示出错.
			if (code != null) {
				logger.info("check Parameter fail. code:[{}].", code);
			} else if (cid != null) {
				logger.info("check Parameter fail. cid:[{}].", cid);
			}
			return;
		}

		// 提供查询服务.
		SealServiceImpl service = new SealServiceImpl();

		// 查询cid.
		if (cid != null) {
			// 没有时间参数，查询铅封位置信息.
			ArrayList<Seal> records = service.trackSealByCid(cid);
			if (records != null) {
				Gson gson = new Gson();
				String strJson = gson.toJson(records);

				// Get printerwriter object from response to write json object
				// to the output stream.
				PrintWriter out = response.getWriter();
				out.print(strJson);
				out.flush();
				logger.info("SealServiceImpl.trackSealByCid OK. cid:[{}].", cid);
				return;
			} else {
				// 进行到这里说明没有找到, 直接返回.
				logger.info(
						"SealServiceImpl.trackSealByCid Not Found. cid:[{}].",
						cid);
				return;
			}
		}

		// 查询历史信息.
		if (beginString != null && endString != null && !beginString.equals("")
				&& !endString.equals("")) {
			ArrayList<SealRecord> records = service.trackHistory(code,
					beginString, endString);
			if (records != null) {
				Gson gson = new Gson();
				String strJson = gson.toJson(records);

				// Get printerwriter object from response to write json object
				// to the output stream.
				PrintWriter out = response.getWriter();
				out.print(strJson);
				out.flush();
				logger.info("SealServiceImpl.trackHistory OK. code:[{}].", code);
				return;
			} else {
				// 进行到这里说明没有找到, 直接返回.
				logger.info(
						"SealServiceImpl.trackHistory Not Found. code:[{}].",
						code);
				return;
			}
		} else { // 查询单个铅封信息.
			Seal seal = service.track(code);
			if (seal != null) {
				Gson gson = new Gson();
				String strJson = gson.toJson(seal);

				// Get printerwriter object from response to write json object
				// to the output stream.
				PrintWriter out = response.getWriter();
				out.print(strJson);
				out.flush();
				logger.info(
						"SealServiceImpl.track OK. code:[{}].Long:[{}].Lati:[{}]",
						code, seal.getLongitude(), seal.getLatitude());
				return;
			} else {
				// 进行到这里说明没有找到, 直接返回.
				logger.info("SealServiceImpl.track Not Found. code:[{}].", code);
				return;
			}
		}
	}

	private boolean checkParameter(String code, String cid, String beginString,
			String endString) {

		// 目前要求：code是4位数字.
		// 这里只做简单检查，不做严格检查.
		// 这里没有使用beginString等，以后检查的时候需要检查日期格式及是否是null.
		if (code != null && code.length() == 4) {
			return true;
		}
		if (cid != null && !cid.isEmpty()) {
			return true;
		}
		return false;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
