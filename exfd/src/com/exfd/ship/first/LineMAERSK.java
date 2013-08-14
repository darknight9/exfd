package com.exfd.ship.first;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

/**
 马士基	MAERSK		MSKU、MRKU、MAEU、MASU、MARU、SEAU（这个箱号是马士基收购的一个公司箱号，可以放上去）
 MSKU7877430	MSKU7511991
 （马士基集团）南非航运	SAFMARINE		
 MRKU8164977、MRKU8916098、MSKU2474004、MSKU4492347、MSKU2031114
（马士基集团）MCC
 SEAU8644657、TCKU9429851、MSKU8396866、MSKU0243602、TGHU7271125
 http://www.maerskline.com
*/
public class LineMAERSK extends LineBase {

	public String GetPage(String code) throws FailingHttpStatusCodeException, MalformedURLException, IOException{

	    final WebClient webClient = new WebClient();

	    // 获取首页
	    final HtmlPage page1 = (HtmlPage) webClient.getPage("http://www.maerskline.com/");

	    // 根据form的名字获取页面表单，也可以通过索引来获取：page.getForms().get(0)
	    List<HtmlForm> forms = page1.getForms();
	    HtmlForm form1 = forms.get(3);
	    		
	    final HtmlSubmitInput button 
	        = (HtmlSubmitInput) form1.getInputByValue("Track");
	    
	    final HtmlTextInput textField 
	        = (HtmlTextInput) form1.getInputByName("portlet_quickentries_2{actionForm.trackNo}");
	    textField.setValueAttribute(code);
	    
	    HtmlSelect select1 = form1.getSelectByName("portlet_quickentries_2wlw-select_key:{actionForm.trackType}");
	    HtmlOption option1 = select1.getOptionByValue("CONTAINERNUMBER");
	    option1.setSelected(true);
	    
	    // 提交表单，返回提交表单后跳转的页面
	    final HtmlPage page2 = (HtmlPage) button.click();
		String strPage = page2.asText();
		return strPage;
	}
}
