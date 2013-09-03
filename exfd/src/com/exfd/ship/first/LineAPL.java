package com.exfd.ship.first;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.CharacterReference;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.StartTag;

import com.exfd.domain.Container;
import com.exfd.domain.ContainerRecord;
import com.exfd.domain.ContainerStatus;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class LineAPL extends LineBase {

	
	public String GetPage(String code) throws FailingHttpStatusCodeException,
			MalformedURLException, IOException {

		final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_17);
		WebClientOptions webClientOptions = webClient.getOptions();
		webClientOptions.setCssEnabled(false);
		// webClientOptions.setAppletEnabled(false);
		// webClientOptions.setJavaScriptEnabled(false);
		webClientOptions.setTimeout(60000);

		// 获取首页
		final HtmlPage page1 = (HtmlPage) webClient
				.getPage("http://homeport.apl.com/gentrack/trackingMain.do?trckSingleInput="+code);

		DebugPage(code, page1.getWebResponse().getContentAsString(), ".P1");

		final HtmlPage page2 = (HtmlPage) page1.getFrameByName("bodyframe").getEnclosedPage();

		DebugPage(code, page2.getWebResponse().getContentAsString(), ".P2");

		// String strPage = page2.asXml();
		String strPage = null;

		strPage = page2.getWebResponse().getContentAsString();
		if (strPage.contains("Not Found")) {
			strPage = "";
			return strPage;
		}
		
		return strPage;
	}

	public boolean getContainerByTree(String code, String page,
			Container container) {

		// 获取"Current shipment cycle status"表格位置.
		int currentPos = page.indexOf("Routing");

		// 找不到标记点，返回.
		if (currentPos < 0) {
			container.setParseerror(1);
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
		//trList = tableElement.getAllElements(HTMLElementName.TR);
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

		// 复制history最后1条到current.
		status.setStatusRecord(historyRecords.get(historyRecords.size()-1));
		status.setStatusTitle(status.getHistoryTitle());

		return true;
	}

}
