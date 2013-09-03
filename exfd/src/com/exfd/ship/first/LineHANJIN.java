package com.exfd.ship.first;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class LineHANJIN extends LineBase {

	public String GetPage(String code) throws FailingHttpStatusCodeException,
			MalformedURLException, IOException {

		final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_17);
		WebClientOptions webClientOptions = webClient.getOptions();
		webClientOptions.setCssEnabled(false);
		// webClientOptions.setAppletEnabled(false);
		// webClientOptions.setJavaScriptEnabled(false);
		webClientOptions.setTimeout(60000);
		webClientOptions.setThrowExceptionOnScriptError( false ) ;

		// 获取首页
		final HtmlPage page1 = (HtmlPage) webClient
				.getPage("http://www.hanjin.com/hanjin/CUP_HOM_1001.do");

		DebugPage(code, page1.getWebResponse().getContentAsString(), ".P1");

		// 根据form的名字获取页面表单，也可以通过索引来获取：page.getForms().get(0)
		HtmlForm form1 = page1.getFormByName("frm");

		final HtmlTextInput textField = (HtmlTextInput) form1
				.getInputByName("cargoTrackingNo");
		textField.setValueAttribute(code);
		
		HtmlSelect select1 = page1.getHtmlElementById("cargoTrackingNoType");
		HtmlOption option1 = select1.getOptionByValue("C");
		option1.setSelected(true);
		
		final HtmlImageInput imageInput = form1.getInputByName("btnCargoTrakingSearch");
		
		// 提交表单，返回提交表单后跳转的页面
		final HtmlPage page2 = (HtmlPage) imageInput.click();

		DebugPage(code, page2.getWebResponse().getContentAsString(), ".P2");

		// String strPage = page2.asXml();
		String strPage = null;

		strPage = page2.getWebResponse().getContentAsString();
		if (strPage.contains("No data available")) {
			strPage = "";
			return strPage;
		}

		return strPage;
	}
}
