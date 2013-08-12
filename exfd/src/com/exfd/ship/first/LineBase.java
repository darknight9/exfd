package com.exfd.ship.first;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.htmlparser.jericho.CharacterReference;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.MasonTagTypes;
import net.htmlparser.jericho.MicrosoftConditionalCommentTagTypes;
import net.htmlparser.jericho.PHPTagTypes;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.StartTag;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.SubnodeConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.tree.xpath.XPathExpressionEngine;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.exfd.domain.Container;
import com.exfd.domain.ContainerRecord;
import com.google.gson.Gson;

public class LineBase {

	static Logger logger = LogManager.getLogger();
	static XMLConfiguration config = null;

	public String GetPage(String code) throws UnsupportedEncodingException,
			ClientProtocolException, IOException, URISyntaxException {

		// 读取配置文件，这里就不加线程锁了.
		if (config == null) {
			LoadConfig();
		}
		// 准备request.
		HttpClient httpclient = new DefaultHttpClient();

		// 获取当前的船公司的配置信息.
		SubnodeConfiguration sub = config
				.configurationAt("/container[@classname='"
						+ this.getClass().getSimpleName() + "']/search");

		if (sub.getString("@method").equals("POST")) {

			// 建立POST连接点.
			String endpoint = sub.getString("endpoint").trim();
			HttpPost httppost = new HttpPost(endpoint);
			logger.debug(endpoint);
			// 配置POST参数.
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			List<HierarchicalConfiguration> params = sub
					.configurationsAt("params/param");
			for (HierarchicalConfiguration param : params) {
				String paramName = param.getString("@name");
				String paramValue = param.getString("@value");
				logger.debug("name=[{}],value=[{}]", paramName, paramValue);
				if (paramValue.equals("EXFDCODE")) {
					paramValue = code;
				}
				nvps.add(new BasicNameValuePair(paramName, paramValue));
			}

			// 配置Header.
			List<HierarchicalConfiguration> headers = sub
					.configurationsAt("headers/header");
			if (headers != null) {
				for (HierarchicalConfiguration header : headers) {
					String headerName = header.getString("@name");
					String headerValue = header.getString("@value");
					String headerMethod = header.getString("@method");
					if (headerValue.equals("default")) {
						headerValue = config
								.getString("/defaults/default[@name='"
										+ headerName + "']/@value");
					}
					logger.debug("name=[{}],value=[{}],method=[{}]",
							headerName, headerValue, headerMethod);
					if (headerMethod != null && headerMethod.equals("SET")) {
						httppost.removeHeaders(headerName);
						httppost.setHeader(headerName, headerValue);
					} else {
						httppost.addHeader(headerName, headerValue);
					}
				}
			}
			String strPage = getPostPage(httpclient, httppost, nvps);
			return strPage;
		} else {

			// 建立GET连接点.
			String endpoint = sub.getString("endpoint");
			logger.debug(endpoint);
			URIBuilder builder = new URIBuilder(endpoint);

			// 配置GET参数.
			List<HierarchicalConfiguration> params = sub
					.configurationsAt("params/param");
			for (HierarchicalConfiguration param : params) {
				String paramName = param.getString("@name");
				String paramValue = param.getString("@value");
				logger.debug("name=[{}],value=[{}]", paramName, paramValue);
				if (paramValue.equals("EXFDCODE")) {
					paramValue = code;
				}
				builder.addParameter(paramName, paramValue);
			}
			URI uri = builder.build();
			HttpGet httpget = new HttpGet(uri);
			logger.debug(httpget.getURI());

			// 配置Header.
			List<HierarchicalConfiguration> headers = sub
					.configurationsAt("headers/header");
			if (headers != null) {
				for (HierarchicalConfiguration header : headers) {
					String headerName = header.getString("@name");
					String headerValue = header.getString("@value");
					String headerMethod = header.getString("@method");
					if (headerValue.equals("default")) {
						headerValue = config
								.getString("/defaults/default[@name='"
										+ headerName + "']/@value");
					}
					logger.debug("name=[{}],value=[{}],method=[{}]",
							headerName, headerValue, headerMethod);
					if (headerMethod != null && headerMethod.equals("SET")) {
						httpget.removeHeaders(headerName);
						httpget.setHeader(headerName, headerValue);
					} else {
						httpget.addHeader(headerName, headerValue);
					}
				}
			}
			String strPage = getGetPage(httpclient, httpget);
			return strPage;
		}
	}

	protected String getPostPage(HttpClient httpclient, HttpPost httppost,
			List<? extends NameValuePair> formparams) throws IOException {

		httppost.setEntity(new UrlEncodedFormEntity(formparams, Consts.UTF_8));

		// 开始获取请求结果.
		HttpResponse response = null;
		logger.debug("before httpclient.execute");

		try {
			response = httpclient.execute(httppost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			logger.catching(e);
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			logger.catching(e);
			throw e;
		}
		logger.debug("after httpclient.execute");
		HttpEntity entity = response.getEntity();
		long len = entity.getContentLength();
		logger.debug("the result content length is : {}", len);
		String strPage = EntityUtils.toString(entity);

		return strPage;
	}

	/**
	 * @param httpclient
	 * @param httpget
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	protected String getGetPage(HttpClient httpclient, HttpGet httpget)
			throws ClientProtocolException, IOException {

		// 开始获取请求结果.
		HttpResponse response = null;
		logger.debug("before httpclient.execute");

		try {
			response = httpclient.execute(httpget);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			logger.catching(e);
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			logger.catching(e);
			throw e;
		}
		logger.debug("after httpclient.execute");
		HttpEntity entity = response.getEntity();
		long len = entity.getContentLength();
		logger.debug("the result content length is : {}", len);
		String strPage = EntityUtils.toString(entity);
		return strPage;
	}

	public static void LoadConfig() {
		try {
			// 先设置属性再load才行.
			config = new XMLConfiguration();
			config.setExpressionEngine(new XPathExpressionEngine());
			config.setDelimiterParsingDisabled(true);
			config.setAttributeSplittingDisabled(true);
			config.load("containers.xml");
		} catch (ConfigurationException e) {
			e.printStackTrace();
			logger.catching(e);
		}
	}

	public Container GetContainer(String code) {
		String strPage = null;
		try {
			strPage = GetPage(code);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		Container container = GetContainerByPage(code, strPage);
		return container;
	}

	public Container GetContainerByPage(String code, String page) {

		// 获取公司名称, 去掉前面的"Line".
		String company = this.getClass().getSimpleName().substring(4);

		Container container = new Container();
		container.setCode(code);
		container.setCompany(company);

		// 获取当前的船公司的配置信息.
		SubnodeConfiguration sub = config
				.configurationAt("/container[@classname='"
						+ this.getClass().getSimpleName() + "']/parse");

		// 检查是否没有找到箱子.
		String notfoundString = sub.getString("notfound");
		if (page.indexOf(notfoundString) >= 0) {
			logger.info("container [{}] not found.", code);
			container.setNotfound(1);
			return container;
		}

		// 寻找结果.
		MicrosoftConditionalCommentTagTypes.register();
		PHPTagTypes.register();
		PHPTagTypes.PHP_SHORT.deregister(); // remove PHP short tags for this
											// example otherwise they override
											// processing instructions
		MasonTagTypes.register();
		Source source = new Source(page);
		String hintString = sub.getString("hint");
		hintString = hintString.replace("EXFDCODE", code);
		logger.debug(hintString);

		int flagPos = page.indexOf(hintString);

		// 找不到标记点，返回.
		if (flagPos < 0) {
			container.setParseerror(1);
			return container;
		}
		String dirString = sub.getString("dir");
		StartTag start = null;
		if (dirString.equals("next")) {
			start = source.getNextStartTag(flagPos, "table");
		} else {
			start = source.getPreviousStartTag(flagPos, "table");
		}
		logger.debug(start);
		Element tableElement = start.getElement();
		Element resultElement = null;
		List<Element> trList = null;
		String rowclass = sub.getString("rowclass");
		String rowclassPattern = sub.getString("rowclassPattern");
		if (rowclass != null) {

		} else if (rowclassPattern != null) {
			Pattern valueRegexPattern = Pattern.compile(rowclass);
			trList = tableElement.getAllElements("class", valueRegexPattern);
		} else {
			trList = tableElement.getAllElements(HTMLElementName.TR);
		}

		ArrayList<ArrayList<String>> aLists = new ArrayList<ArrayList<String>>();
		ArrayList<ContainerRecord> records = new ArrayList<ContainerRecord>();

		// 默认记录是record，而不是header.
		String defaultRecord = sub.getString("defaultRecord");
		String recordClassStart = sub.getString("recordClassStart");
		String headerClassStart = sub.getString("headerClassStart");
		int usetdclass = sub.getInt("usetdclass");
		int timeIndex = sub.getInt("time");
		int eventIndex = sub.getInt("event");
		int locationIndex = sub.getInt("location");
		int vesselIndex = sub.getInt("vessel");
		int voyageIndex = sub.getInt("voyage");

		boolean isHeaderDefault = false;
		if (defaultRecord.equals("false")) {
			isHeaderDefault = true;
		}
		for (int i = 0; i < trList.size(); i++) {
			Element trElement = trList.get(i);
			boolean isHeader = isHeaderDefault;
			
			// 如果标记在tr上，删除不符合条件的tr行.
			String classAttribute = trElement.getAttributeValue("class");
			if (usetdclass == 0) {
				if (classAttribute != null && recordClassStart != null
						&& classAttribute.startsWith(recordClassStart)) {
					isHeader = false;
				} else if (classAttribute != null && headerClassStart != null
						&& classAttribute.startsWith(headerClassStart)) {
					isHeader = true;
				} else {
					continue; // 跳过这行.
				}
			}
			
			// 如果标记在td class上，可能要删除不符合条件的tr行.
			boolean deleteTr = false;
			List<Element> tdList = trElement.getAllElements(HTMLElementName.TD);
			ArrayList<String> arrayList = new ArrayList<String>();
			ContainerRecord record = new ContainerRecord();
			record.setHeader(isHeader);

			for (int j = 0; j < tdList.size(); j++) {
				resultElement = tdList.get(j);
				
				// 有些header设置是在td标签上.
				if (j == 0 && usetdclass != 0) {
					classAttribute = resultElement.getAttributeValue("class");
					if (classAttribute != null && recordClassStart != null
							&& classAttribute.startsWith(recordClassStart)) {
						isHeader = false;
					} else if (classAttribute != null && headerClassStart != null
							&& classAttribute.startsWith(headerClassStart)) {
						isHeader = true;
					} else {
						deleteTr = true;
						break;
					}
					record.setHeader(isHeader);
				}
				String conString = CharacterReference
						.decodeCollapseWhiteSpace(resultElement.getContent()
								.getTextExtractor().toString());
				// logger.debug("i={},j={},{}", i, j, conString);
				arrayList.add(conString);
			}
			if (!deleteTr) {
				record.setTime(arrayList.get(timeIndex));
				record.setEvent(arrayList.get(eventIndex));
				record.setLocation(arrayList.get(locationIndex));
				record.setVessel(arrayList.get(vesselIndex));
				record.setVoyage(arrayList.get(voyageIndex));
				
				aLists.add(arrayList);
				records.add(record);
			}
		}
		String tableString = Record2Table(records, code);
		container.setTableString(tableString);

		String jsonString = Record2Json(records, code);
		container.setJsonString(jsonString);

		return container;

	}
	
	public String Array2Table(ArrayList<ArrayList<String>> aLists, String code) {

		StringBuilder sb = new StringBuilder(aLists.size() * 1000);
		sb.append("<table border=\"1\">");
		for (ArrayList<String> arrayList : aLists) {
			sb.append("<tr>");
			for (String string : arrayList) {
				sb.append("<td>").append(string).append("</td>");
			}
			sb.append("</tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}

	public String Array2Json(ArrayList<ArrayList<String>> aLists, String code) {

		Gson gson = new Gson();
		String strJson = gson.toJson(aLists);
		return strJson;
	}

	public String Record2Json(ArrayList<ContainerRecord> records, String code) {

		Gson gson = new Gson();
		String strJson = gson.toJson(records);
		return strJson;
	}
	
	public String Record2Table(ArrayList<ContainerRecord> records, String code) {

		StringBuilder sb = new StringBuilder(records.size() * 1000);
		sb.append("<table border=\"1\">");
		for (ContainerRecord cb : records) {
			if (cb.getHeader()) {
				sb.append("<tr>");
				sb.append("<th>日期(").append(cb.getTime()).append(")</th>");
				sb.append("<th>事件(").append(cb.getEvent()).append(")</th>");
				sb.append("<th>地点(").append(cb.getLocation()).append(")</th>");
				sb.append("<th>舰船(").append(cb.getVessel()).append(")</th>");
				sb.append("<th>行程(").append(cb.getVoyage()).append(")</th>");
				sb.append("</tr>");
			} else {
				sb.append("<tr>");
				sb.append("<td>").append(cb.getTime()).append("</td>");
				sb.append("<td>").append(cb.getEvent()).append("</td>");
				sb.append("<td>").append(cb.getLocation()).append("</td>");
				sb.append("<td>").append(cb.getVessel()).append("</td>");
				sb.append("<td>").append(cb.getVoyage()).append("</td>");
				sb.append("</tr>");
			}
		}
		sb.append("</table>");
		return sb.toString();
	}
	
	
}
