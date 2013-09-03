package com.exfd.ship.second;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.exfd.ship.first.LineBase;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlRadioButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLElement;

public class LineOOCL extends LineBase {

	public String GetPage(String code) throws FailingHttpStatusCodeException,
			MalformedURLException, IOException {

		final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_17);
		WebClientOptions webClientOptions = webClient.getOptions();
		webClientOptions.setCssEnabled(false);
		// webClientOptions.setAppletEnabled(false);
		//webClientOptions.setJavaScriptEnabled(false);
		webClientOptions.setTimeout(60000);

		// 获取首页
		final HtmlPage page1 = (HtmlPage) webClient
				.getPage("http://www.oocl.com/schi/Pages/default.aspx");

		DebugPage(code, page1.getWebResponse().getContentAsString(), ".P1");

		final HtmlTextInput textField = page1.getHtmlElementById("Cont_No");
		textField.setValueAttribute(code);

		final HtmlElement gobutton = page1.getHtmlElementById("conttd");

		// 提交表单，返回提交表单后跳转的页面
		final HtmlPage page2 = (HtmlPage) gobutton.click();

		DebugPage(code, page2.getWebResponse().getContentAsString(), ".P2");

		// String strPage = page2.asXml();
		String strPage = null;

		strPage = page2.getWebResponse().getContentAsString();
		if (strPage.contains("No record has been found")) {
			strPage = "";
			return strPage;
		}
		return strPage;
	}
}
