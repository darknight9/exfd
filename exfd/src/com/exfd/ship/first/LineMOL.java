package com.exfd.ship.first;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

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

public class LineMOL extends LineBase {

	public String GetPage(String code)
			throws UnsupportedEncodingException, ClientProtocolException,
			IOException, URISyntaxException {
		
		// 准备request.
		HttpClient httpclient = new DefaultHttpClient();
		
		// 获取当前的船公司的配置信息.
		SubnodeConfiguration sub = config.configurationAt("/container[@classname='"+this.getClass().getSimpleName()+"']/search");
		
		// 建立GET连接点.
		String endpoint = sub.getString("endpoint");
		logger.debug(endpoint);
		URIBuilder builder = new URIBuilder(endpoint);

		// 配置GET参数.
		builder.addParameter("Eqpno", code.substring(0, code.length()-1));
		builder.addParameter("EqpChked", code.substring(code.length()-1, code.length()));
		
		URI uri = builder.build();
		HttpGet httpget = new HttpGet(uri);
		logger.debug(httpget.getURI());
			
			//httpget.setHeader("Accept", "text/html, application/xhtml+xml, */*");
			//httpget.setHeader("Accept-Language", "zh-CN");
			//httpget.setHeader("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)");

		String strPage = getGetPage(httpclient, httpget);		
		return strPage;
	}
}
