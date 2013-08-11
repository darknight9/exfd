package com.exfd.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EagleGPSUtils {

	static Logger logger = LogManager.getLogger();
	
	// 目前的巡逻鹰接口.
	private final static String getGPSUrlPrefix = "http://121.14.37.120/yxgps/service/getIntPostalDynamicData.asp";
	private final static String getHistoryUrlPrefix = "http://121.14.37.120/yxgps/service/getPostalTextHistoryTrack.asp";

	private final static String strUser = "0";
	private final static String strCompany = "0";

	// 联网获取结果.
	public static String trackVehicle(String code) {

		try {
			URL url = new URL(getGPSUrlPrefix);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestProperty("Content-Type",
					"text/html;charset=UTF-8");

			// 向服务器发送请求.
			connection.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(
					connection.getOutputStream(), "UTF-8");
			String s = "<?xm version=\"1.0\" encoding = \"GB2312\"?><VehicleList VehicleList=\""
					+ code + "\" />";
			out.write(s);
			out.flush();
			out.close();

			// 获取返回结果.
			InputStream in = connection.getInputStream();
			int count = 0;
			while (count == 0) {
				count = in.available();
			}
			byte[] bytes = new byte[count];
			in.read(bytes);
			String str = new String(bytes, "UTF-8");
			return str;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 联网获取结果.
	public static String trackVehicle2(String code) {

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(getGPSUrlPrefix);
		String s = "<?xm version=\"1.0\" encoding = \"GB2312\"?><VehicleList VehicleList=\""
				+ code + "\" />";
		String resString = null;
		try {
			StringEntity sEntity = new StringEntity(s, "gb2312");
			httppost.setEntity(sEntity);
			httppost.setHeader("Content-Type",
					"text/html;charset=UTF-8");
			
			// 开始获取请求结果.
			HttpResponse response = null;
			logger.debug("before httpclient.execute");
			
			response = httpclient.execute(httppost);

			logger.debug("after httpclient.execute");

			HttpEntity entity = response.getEntity();
			long len = entity.getContentLength();
			logger.debug("the result content length is : {}",len);
			
			byte[] bytes = EntityUtils.toByteArray(entity);
	    	resString = new String(bytes, "UTF-8");
			//resString = EntityUtils.toString(entity);

		} catch (Exception e) {
			e.printStackTrace();
			logger.catching(e);
		}
		return resString;
	}

	public static String trackHistory(String strVid, String strBeginTime,
			String strEndTime) {
		String str = "";
		try {
			// 远程连接
			URL url = new URL(getHistoryUrlPrefix);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestProperty("Content-Type",
					"text/html;charset=UTF-8");

			// 参数设置
			// 向服务器发送请求.
			connection.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(
					connection.getOutputStream(), "UTF-8");
			String s = "<?xm version=\"1.0\" encoding = \"GB2312\"?><HisText UserName=\""
					+ strUser
					+ "\" SiKM=\""
					+ strCompany
					+ "\" VID=\""
					+ strVid
					+ "\" BeginTime=\""
					+ strBeginTime
					+ "\" EndTime=\"" + strEndTime + "\" />";
			out.write(s);
			out.flush();
			out.close();
			InputStream l_urlStream;

			logger.debug("send request: {}", s);
			// 获取返回结果.
			InputStream in = connection.getInputStream();
			// BufferedReader l_reader = new BufferedReader(new
			// InputStreamReader(l_urlStream));
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(
	                connection.getInputStream()));
			StringBuilder sBuilder = new StringBuilder(1024*1024);
			String line;
			while ((line = reader.readLine()) != null) {
	           sBuilder.append(line);
	        }
			reader.close();
			connection.disconnect();
			return sBuilder.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String trackHistory2(String strVid, String strBeginTime,
			String strEndTime) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(getHistoryUrlPrefix);
		String s = "<?xm version=\"1.0\" encoding = \"GB2312\"?><HisText UserName=\""
				+ strUser
				+ "\" SiKM=\""
				+ strCompany
				+ "\" VID=\""
				+ strVid
				+ "\" BeginTime=\""
				+ strBeginTime
				+ "\" EndTime=\"" + strEndTime + "\" />";
		String resString = null;
		try {
			StringEntity sEntity = new StringEntity(s, "UTF-8");
			httppost.setEntity(sEntity);
			httppost.setHeader("Content-Type",
					"text/html;charset=UTF-8");
			
			// 开始获取请求结果.
			HttpResponse response = null;
			logger.debug("before httpclient.execute");
			
			response = httpclient.execute(httppost);

			logger.debug("after httpclient.execute");

			HttpEntity entity = response.getEntity();
			long len = entity.getContentLength();
			logger.debug("the result content length is : {}",len);
			
			byte[] bytes = EntityUtils.toByteArray(entity);
	    	resString = new String(bytes, "UTF-8");
			//resString = EntityUtils.toString(entity);

		} catch (Exception e) {
			e.printStackTrace();
			logger.catching(e);
		}
		return resString;
	}
	
}
