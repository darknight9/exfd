package com.exfd.ship.first;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.CharacterReference;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.StartTag;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

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
import com.gargoylesoftware.htmlunit.html.HtmlRadioButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class LineCHINASHIPPING extends LineBase {

	public String GetPage(String code) throws FailingHttpStatusCodeException,
			MalformedURLException, IOException {

		final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_17);
		WebClientOptions webClientOptions = webClient.getOptions();
		webClientOptions.setCssEnabled(false);
		// webClientOptions.setAppletEnabled(false);
		webClientOptions.setJavaScriptEnabled(false);
		webClientOptions.setTimeout(60000);

		// 获取首页
		final HtmlPage page1 = (HtmlPage) webClient
				.getPage("http://www.cscl.com.cn/");

		DebugPage(code, page1.getWebResponse().getContentAsString(), ".P1");

		// 根据form的名字获取页面表单，也可以通过索引来获取：page.getForms().get(0)
		HtmlForm form1 = page1.getFormByName("form1");

		List<HtmlRadioButtonInput> raidoList = form1
				.getRadioButtonsByName("tr_num");
		raidoList.get(0).setChecked(false);
		raidoList.get(1).setChecked(true);

		final HtmlSubmitInput button = (HtmlSubmitInput) form1
				.getInputByName("Submit");

		final HtmlTextInput textField = (HtmlTextInput) form1
				.getInputByName("tf_bl_no");
		textField.setValueAttribute(code);

		// 提交表单，返回提交表单后跳转的页面
		final HtmlPage page2 = (HtmlPage) button.click();

		DebugPage(code, page2.getWebResponse().getContentAsString(), ".P2");

		// String strPage = page2.asXml();
		String strPage = null;

		strPage = page2.getWebResponse().getContentAsString();
		if (strPage.contains("No record has been found")) {
			strPage = "";
			return strPage;
		}

		// 开始获取历史记录.
		HtmlAnchor anchor1 = page2.getAnchorByText(code);
		final HtmlPage page3 = anchor1.click();
		strPage = page3.getWebResponse().getContentAsString();
		return strPage;
	}

	public boolean getContainerByTree(String code, String page,
			Container container) {

		int currentPos = page.indexOf(code);

		// 找不到标记点，返回.
		if (currentPos < 0) {
			container.setError(1);
			logger.debug(
					"CONT[{}] container page not find lastest pos[{}] postion.",
					code, currentPos);
			return false;
		}

		ArrayList<String> arrayList = new ArrayList<String>();
		getTableElement();
		Element tableElement = tablebaseElement;

		// 开始记录title.
		List<Element> trList = tableElement.getAllElements(HTMLElementName.TR);
		List<Element> tdList = trList.get(0).getAllElements(HTMLElementName.TD);

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
