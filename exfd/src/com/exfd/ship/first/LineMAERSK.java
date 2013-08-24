package com.exfd.ship.first;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.CharacterReference;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;

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

/**
 * 马士基 MAERSK MSKU、MRKU、MAEU、MASU、MARU、SEAU（这个箱号是马士基收购的一个公司箱号，可以放上去） MSKU7877430
 * MSKU7511991 （马士基集团）南非航运 SAFMARINE
 * MRKU8164977、MRKU8916098、MSKU2474004、MSKU4492347、MSKU2031114 （马士基集团）MCC
 * SEAU8644657、TCKU9429851、MSKU8396866、MSKU0243602、TGHU7271125
 * http://www.maerskline.com
 */
public class LineMAERSK extends LineBase {

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
				.getPage("http://www.maerskline.com/");

		DebugPage(code, page1.getWebResponse().getContentAsString(), ".P1");

		// 根据form的名字获取页面表单，也可以通过索引来获取：page.getForms().get(0)
		List<HtmlForm> forms = page1.getForms();
		HtmlForm form1 = null;
		if (forms.size() >= 3) {
			form1 = forms.get(3);
		} else {
			form1 = forms.get(forms.size() - 1);
		}

		final HtmlSubmitInput button = (HtmlSubmitInput) form1
				.getInputByValue("Track");

		final HtmlTextInput textField = (HtmlTextInput) form1
				.getInputByName("portlet_quickentries_2{actionForm.trackNo}");
		textField.setValueAttribute(code);

		HtmlSelect select1 = form1
				.getSelectByName("portlet_quickentries_2wlw-select_key:{actionForm.trackType}");
		HtmlOption option1 = select1.getOptionByValue("CONTAINERNUMBER");
		option1.setSelected(true);

		// 提交表单，返回提交表单后跳转的页面
		final HtmlPage page2 = (HtmlPage) button.click();

		DebugPage(code, page2.getWebResponse().getContentAsString(), ".P2");

		// String strPage = page2.asXml();
		String strPage = null;

		strPage = page2.getWebResponse().getContentAsString();
		if (strPage.contains("No result")) {
			strPage = "";
			return strPage;
		}
		// 获取详细信息.
		HtmlAnchor anchor = page2.getAnchorByText(code);
		if (anchor != null) {
			HtmlPage page3 = (HtmlPage) anchor.click();
			strPage = page3.getWebResponse().getContentAsString();
			DebugPage(code, page3.getWebResponse().getContentAsString(), ".P3");
		} else {
			strPage = page2.getWebResponse().getContentAsString();
		}
		return strPage;
	}

	public boolean getContainerByTree(String code, String page,
			Container container) {

		ArrayList<String> arrayList = new ArrayList<String>();
		ContainerStatus status = container.getStatus();

		// 获取table元素.
		getTableElement();

		// title和record是通过td上的class不同区分的.
		List<Element> trList = tablebaseElement
				.getAllElements(HTMLElementName.TR);

		List<Element> tdList = null;
		Element resultElement;

		// 开始记录record.
		ArrayList<ContainerRecord> historyRecords = status.getHistoryRecords();
		for (Element trelement : trList) {

			tdList = trelement.getAllElements(HTMLElementName.TD);
			arrayList.clear();

			boolean skipTr = false;
			boolean isTitle = false;
			for (int j = 0; j < tdList.size(); j++) {
				resultElement = tdList.get(j);

				// 有些header设置是在td标签上.
				if (j == 0) {
					String classAttribute = resultElement
							.getAttributeValue("class");
					if (classAttribute.startsWith("lstTxt")) {
						isTitle = false;
					} else if (classAttribute.startsWith("lstColLabel")) {
						isTitle = true;
					} else {
						skipTr = true;
						break;
					}
				}
				String conString = CharacterReference
						.decodeCollapseWhiteSpace(resultElement.getContent()
								.getTextExtractor().toString());
				// logger.debug("i={},j={},{}", i, j, conString);
				arrayList.add(conString);
			}
			if (!skipTr) {
				if (isTitle) {
					list2Record(arrayList, status.getHistoryTitle());
				} else {
					ContainerRecord statusRecord = new ContainerRecord();
					list2Record(arrayList, statusRecord);
					historyRecords.add(statusRecord);
				}
			}
		}

		// 复制history最后一条到current.
		status.setStatusRecord(historyRecords.get(historyRecords.size() - 1));

		// 复制title.
		status.setStatusTitle(status.getHistoryTitle());

		return true;
	}

}
