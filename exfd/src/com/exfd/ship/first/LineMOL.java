package com.exfd.ship.first;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.CharacterReference;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.StartTag;

import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.SubnodeConfiguration;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.exfd.domain.Container;
import com.exfd.domain.ContainerRecord;
import com.exfd.domain.ContainerStatus;

public class LineMOL extends LineBase {

	public String GetPage(String code) throws UnsupportedEncodingException,
			ClientProtocolException, IOException, URISyntaxException {

		// 准备request.
		HttpClient httpclient = new DefaultHttpClient();

		// 获取当前的船公司的配置信息.
		SubnodeConfiguration sub = config
				.configurationAt("/container[@classname='"
						+ this.getClass().getSimpleName() + "']/search");

		// 建立GET连接点.
		String endpoint = sub.getString("endpoint");
		logger.debug(endpoint);
		URIBuilder builder = new URIBuilder(endpoint);

		// 配置GET参数.
		builder.addParameter("Eqpno", code.substring(0, code.length() - 1));
		builder.addParameter("EqpChked",
				code.substring(code.length() - 1, code.length()));

		URI uri = builder.build();
		HttpGet httpget = new HttpGet(uri);
		logger.debug(httpget.getURI());

		// httpget.setHeader("Accept", "text/html, application/xhtml+xml, */*");
		// httpget.setHeader("Accept-Language", "zh-CN");
		// httpget.setHeader("User-Agent",
		// "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)");

		String strPage = getGetPage(httpclient, httpget);
		return strPage;
	}

	public boolean getContainerByTree(String code, String page,
			Container container) {

		// 获取"Current shipment cycle status"表格位置.
		int currentPos = page.indexOf("grvEqpMoveDtls_ct");

		// 找不到标记点，返回.
		if (currentPos < 0) {
			container.setError(1);
			logger.debug(
					"CONT[{}] container page not find lastest pos[{}] postion.",
					code, currentPos);
			return false;
		}

		ArrayList<String> arrayList = new ArrayList<String>();
		StartTag startTable = null;
		startTable = source.getNextStartTag(hintPos, "table");
		Element tableElement = startTable.getElement();

		// 开始记录title.
		List<Element> trList = tableElement.getAllElements(HTMLElementName.TR);
		// 注意这里是<th>不是<td>.
		List<Element> tdList = trList.get(0).getAllElements(HTMLElementName.TH);

		ContainerStatus status = container.getStatus();

		arrayList.clear();
		for (Element element : tdList) {
			String conString = CharacterReference
					.decodeCollapseWhiteSpace(element.getContent()
							.getTextExtractor().toString());
			arrayList.add(conString);
		}
		list2Record(arrayList, status.getHistoryTitle());

		// 开始记录record.
		ArrayList<ContainerRecord> historyRecords = status.getHistoryRecords();
		// trList = tableElement.getAllElements(HTMLElementName.TR);
		boolean isHeader = true;
		for (Element trelement : trList) {
			if (isHeader) {
				isHeader = false;

				// 跳过第一行title.
				continue;
			}
			ContainerRecord statusRecord = new ContainerRecord();
			tdList = trelement.getAllElements(HTMLElementName.TD);
			arrayList.clear();
			for (Element element : tdList) {
				String conString = CharacterReference
						.decodeCollapseWhiteSpace(element.getContent()
								.getTextExtractor().toString());
				arrayList.add(conString);
			}
			list2Record(arrayList, statusRecord);
			historyRecords.add(statusRecord);
		}

		// 复制history到current.
		status.setStatusRecord(historyRecords.get(0));
		status.setStatusTitle(status.getHistoryTitle());

		return true;
	}

}
