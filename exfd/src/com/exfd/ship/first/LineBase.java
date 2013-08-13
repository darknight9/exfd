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
import com.exfd.domain.ContainerRecordOld;
import com.exfd.domain.ContainerStatus;
import com.google.gson.Gson;

public class LineBase {

	static Logger logger = LogManager.getLogger();
	static XMLConfiguration config = null;

	// 配置的XML中的解析结点.
	SubnodeConfiguration parseNode = null;

	// 页面解析出的source.
	Source source = null;

	// 解析出的Hint position.
	int hintPos = -1;

	// 解析出的表格元素.
	Element tablebaseElement = null;

	// 解析出的表格元素.
	Element tableElement2 = null;

	int usetdclass = 0;
	int timeIndex = -1;
	int eventIndex = -1;
	int locationIndex = -1;
	int vesselIndex = -1;
	int voyageIndex = -1;

	// 读取配置信息.
	public static void LoadConfig() {
		if (config != null) {
			return;
		}
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

	// 根据箱号获取网页内容.
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

	// 使用POST请求获取网页内容.
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

	// 使用GET请求获取网页内容.
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

	// 根据箱号直接获取箱子信息.
	public Container GetContainer(String code) {
		String strPage = null;
		try {
			strPage = GetPage(code);
		} catch (Exception e) {
			logger.catching(e);
			return null;
		}
		Container container = GetContainerByPage(code, strPage);
		return container;
	}

	// Parse.1.解析基本信息.
	public Container ParseBaseInfo(String code, String page) {

		// 获取公司名称, 去掉前面的"Line".
		String company = this.getClass().getSimpleName().substring(4);

		Container container = new Container();
		container.setCode(code);
		container.setCompany(company);
		container.setJsonString("");
		container.setTableString("");
		container.setHttpresult("");

		ContainerStatus status = new ContainerStatus();
		status.setCode(code);
		status.setCompany(company);
		container.setStatus(status);
		status.setSize("未知");

		ContainerRecord statusTitle = new ContainerRecord();
		status.setStatusTitle(statusTitle);
		ContainerRecord historyTitle = new ContainerRecord();
		status.setHistoryTitle(historyTitle);
		ContainerRecord statusRecord = new ContainerRecord();
		status.setStatusRecord(statusRecord);
		ArrayList<ContainerRecord> historyRecords = new ArrayList<ContainerRecord>();
		status.setHistoryRecords(historyRecords);
		
		

		// 获取当前的船公司的配置信息.
		parseNode = config.configurationAt("/container[@classname='"
				+ this.getClass().getSimpleName() + "']/parse");

		usetdclass = parseNode.getInt("usetdclass");
		timeIndex = parseNode.getInt("time");
		eventIndex = parseNode.getInt("event");
		locationIndex = parseNode.getInt("location");
		vesselIndex = parseNode.getInt("vessel");
		voyageIndex = parseNode.getInt("voyage");

		return container;
	}

	// 检查是否没有找到箱子.
	public boolean isNotFoundPage(String code, String page, Container container) {

		String notfoundString = parseNode.getString("notfound");
		if (page.indexOf(notfoundString) >= 0) {
			logger.debug(
					"CONT[{}] container page contains not found information.",
					code);
			container.setNotfound(1);
			return true;
		}
		return false;
	}

	// 获取Hint
	public boolean getHintPos(String code, String page, Container container) {
		String hintString = parseNode.getString("hint");
		hintString = hintString.replace("EXFDCODE", code);
		hintPos = page.indexOf(hintString);

		// 找不到标记点，返回.
		if (hintPos < 0) {
			container.setParseerror(1);
			logger.debug("CONT[{}] container page not find hint[{}] postion.",
					code, hintString);
			return false;
		}
		return true;
	}

	// 获取解析的Tree.
	public Source prepareTree(String page) {
		// 寻找结果.
		MicrosoftConditionalCommentTagTypes.register();
		PHPTagTypes.register();
		PHPTagTypes.PHP_SHORT.deregister(); // remove PHP short tags for this
											// example otherwise they override
											// processing instructions
		MasonTagTypes.register();
		Source source = new Source(page);
		return source;
	}

	// 解析获取的table元素.
	public void getTableElement() {
		String dirString = parseNode.getString("dir");
		StartTag start = null;
		if (dirString.equals("next")) {
			start = source.getNextStartTag(hintPos, "table");
		} else {
			start = source.getPreviousStartTag(hintPos, "table");
		}
		tablebaseElement = start.getElement();
	}

	// 从字符串数组中，根据预先定义的位置设置record各项的值.
	public void list2Record(ArrayList<String> arrayList, ContainerRecord record) {
		if (timeIndex > 0) {
			record.setTime(arrayList.get(timeIndex - 1));
		}
		if (eventIndex > 0) {
			record.setEvent(arrayList.get(eventIndex - 1));
		}
		if (locationIndex > 0) {
			record.setLocation(arrayList.get(locationIndex - 1));
		}
		if (vesselIndex > 0) {
			record.setVessel(arrayList.get(vesselIndex - 1));
		}
		if (voyageIndex > 0) {
			record.setVoyage(arrayList.get(voyageIndex - 1));
		}
	}

	public Container GetContainerByPage(String code, String page) {

		// Parse.1.解析基本信息.
		Container container = ParseBaseInfo(code, page);

		// Parse.2.检查是否没有找到箱子.
		boolean isNotFound = isNotFoundPage(code, page, container);
		if (isNotFound) {
			logger.info(
					"CONT[{}] container page contains not found information.",
					code);
			return container;
		}

		// Parse.3.获取hint position.
		boolean isGetHint = getHintPos(code, page, container);
		if (!isGetHint) {
			logger.info("CONT[{}] container page not find hint postion.", code);
			return container;
		}

		// Parse.4.获取解析的Tree.
		source = prepareTree(page);

		boolean isGetContainerByTree = getContainerByTree(code, page, container);
		if (!isGetContainerByTree) {
			logger.info("CONT[{}] get container by tree fail.", code);
			return container;
		}
		
		// 生成Json.
		ContainerStatus status = container.getStatus();
		container.setJsonString(status2Json(status));
		
		// 向后兼容.
		container.setHttpresult(status2JsonOld(status));
		
		// 生成table.
		container.setTableString(status2Table(status));
		
		return container;
	}

	public boolean getContainerByTree(String code, String page,
			Container container) {

		// Parse.5. 获取table元素.
		getTableElement();

		Element resultElement = null;
		List<Element> trList = null;
		String rowclass = parseNode.getString("rowclass");
		String rowclassPattern = parseNode.getString("rowclassPattern");
		if (rowclass != null) {

		} else if (rowclassPattern != null) {
			Pattern valueRegexPattern = Pattern.compile(rowclass);
			trList = tablebaseElement
					.getAllElements("class", valueRegexPattern);
		} else {
			trList = tablebaseElement.getAllElements(HTMLElementName.TR);
		}

		ArrayList<ArrayList<String>> aLists = new ArrayList<ArrayList<String>>();
		ArrayList<ContainerRecord> records = new ArrayList<ContainerRecord>();

		// 默认记录是record，而不是header.
		String defaultRecord = parseNode.getString("defaultRecord");
		String recordClassStart = parseNode.getString("recordClassStart");
		String headerClassStart = parseNode.getString("headerClassStart");

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
					} else if (classAttribute != null
							&& headerClassStart != null
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
				list2Record(arrayList, record);

				aLists.add(arrayList);
				records.add(record);
			}
		}

		return true;
	}

	public String status2Json(ContainerStatus status) {

		Gson gson = new Gson();
		String strJson = gson.toJson(status);
		return strJson;
	}

	public void record2RecordOld(ContainerRecord record, ContainerRecordOld old) {
		old.setTime(record.getTime());
		old.setEvent(record.getEvent());
		old.setLocation(record.getLocation());
		old.setVessel(record.getVessel());
		old.setVoyage(record.getVoyage());
	}

	public String status2JsonOld(ContainerStatus status) {

		Gson gson = new Gson();
		ArrayList<ContainerRecordOld> recordOlds = new ArrayList<ContainerRecordOld>();
		ContainerRecordOld old1 = new ContainerRecordOld();
		ContainerRecordOld old2 = new ContainerRecordOld();
		ContainerRecordOld old3 = new ContainerRecordOld();
		record2RecordOld(status.getStatusTitle(), old1);
		old1.setHeader(true);
		record2RecordOld(status.getStatusRecord(), old2);
		old2.setHeader(false);
		record2RecordOld(status.getHistoryTitle(), old3);
		old3.setHeader(true);

		recordOlds.add(old1);
		recordOlds.add(old2);
		recordOlds.add(old3);
		ArrayList<ContainerRecord> records = status.getHistoryRecords();
		for (ContainerRecord record : records) {
			ContainerRecordOld old = new ContainerRecordOld();
			record2RecordOld(record, old);
			old.setHeader(false);
			recordOlds.add(old);
		}
		String strJson = gson.toJson(recordOlds);
		return strJson;
	}

	public String status2Table(ContainerStatus status) {

		ContainerRecord statusTitle = status.getStatusTitle();
		ContainerRecord historyTitle = status.getHistoryTitle();
		ContainerRecord statusRecord = status.getStatusRecord();
		ArrayList<ContainerRecord> historyRecords = status.getHistoryRecords();

		StringBuilder sb = new StringBuilder(historyRecords.size() * 200);
		sb.append("<table border=\"1\">");

		sb.append("<tr>");
		sb.append("<th>箱号：").append(status.getCode()).append("</th>");
		sb.append("<th>公司：").append(status.getCompany()).append("</th>");
		sb.append("<th>大小：").append(status.getSize()).append("</th>");
		sb.append("</tr>");

		sb.append("<tr>");
		sb.append("<th>日期(").append(statusTitle.getTime()).append(")</th>");
		sb.append("<th>事件(").append(statusTitle.getEvent()).append(")</th>");
		sb.append("<th>地点(").append(statusTitle.getLocation()).append(")</th>");
		sb.append("<th>舰船(").append(statusTitle.getVessel()).append(")</th>");
		sb.append("<th>行程(").append(statusTitle.getVoyage()).append(")</th>");
		sb.append("</tr>");

		sb.append("<tr>");
		sb.append("<td>").append(statusRecord.getTime()).append("</td>");
		sb.append("<td>").append(statusRecord.getEvent()).append("</td>");
		sb.append("<td>").append(statusRecord.getLocation()).append("</td>");
		sb.append("<td>").append(statusRecord.getVessel()).append("</td>");
		sb.append("<td>").append(statusRecord.getVoyage()).append("</td>");
		sb.append("</tr>");

		sb.append("<tr>");
		sb.append("<th>日期(").append(historyTitle.getTime()).append(")</th>");
		sb.append("<th>事件(").append(historyTitle.getEvent()).append(")</th>");
		sb.append("<th>地点(").append(historyTitle.getLocation())
				.append(")</th>");
		sb.append("<th>舰船(").append(historyTitle.getVessel()).append(")</th>");
		sb.append("<th>行程(").append(historyTitle.getVoyage()).append(")</th>");
		sb.append("</tr>");

		for (ContainerRecord cb : historyRecords) {
			sb.append("<tr>");
			sb.append("<td>").append(cb.getTime()).append("</td>");
			sb.append("<td>").append(cb.getEvent()).append("</td>");
			sb.append("<td>").append(cb.getLocation()).append("</td>");
			sb.append("<td>").append(cb.getVessel()).append("</td>");
			sb.append("<td>").append(cb.getVoyage()).append("</td>");
			sb.append("</tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}

}
