package com.exfd.util;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class EagleGPSUtils {

	private static String getGPSUrlPrefix = "http://121.14.37.120/yxgps/service/getIntPostalDynamicData.asp";
	private static String getHistoryUrlPrefix = "http://121.14.37.120/yxgps/service/getPostalTextHistoryTrack.asp";
	
	// 联网获取结果.
	public static String trackVehicle(String code) {
		
		try {
			URL url = new URL(getGPSUrlPrefix);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Content-Type", "text/html;charset=UTF-8");
			
			// 向服务器发送请求.
			connection.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
			String s= "<?xm version=\"1.0\" encoding = \"GB2312\"?><VehicleList VehicleList=\"" + code + "\" />";
			out.write(s);                                       
            out.flush();     
            out.close();      
            
            // 获取返回结果.
            InputStream in = connection.getInputStream();       
            //BufferedReader l_reader = new BufferedReader(new InputStreamReader(l_urlStream));                  
            int count = 0;
            while (count == 0) {
             count = in.available();
            }
            byte[] bytes = new byte[count];
            in.read(bytes);
            //while ((sCurrentLine = l_reader.readLine()) != null) { 
                //sTotalString += sCurrentLine;     
            //}     
            
            String str = new String(bytes, "UTF-8");
            return str;
            
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
