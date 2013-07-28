package junit.test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.exfd.domain.Ship;
import com.exfd.domain.ShipReturnSet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class Json2ShipTest {

	@Test
	public void TestGson() throws IOException {
		String json = "{" + "\"return\": [" + "{" + "\"averagespeed\": 0,"
				+ "\"callsign\": \"3ERX3\"," + "\"destination\": \"\","
				+ "\"direction\": 83.8," + "\"distanceMoved\": 0,"
				+ "\"draft\": 145," + "\"eta\": \"\","
				+ "\"gpstime\": \"2013-07-27 13:27:27\","
				+ "\"gpstimepre\": \"2013-07-27 01:45:57\","
				+ "\"imo\": \"9345439\"," + "\"lat\": 5.811647,"
				+ "\"latitude\": \"5°48'41.92\\\"N\"," + "\"latpre\": 5.69757,"
				+ "\"lon\": 85.511343,"
				+ "\"longitude\": \"85°30'40.83\\\"E\","
				+ "\"lonpre\": 83.110717," + "\"mmsi\": \"370188000\","
				+ "\"reporttype\": \"6\"," + "\"shipflag\": \"370\","
				+ "\"shipid\": \"201208141141174119032\","
				+ "\"shiplength\": 349," + "\"shipname\": \"COSCO AFRICA\","
				+ "\"shipnamecn\": \"中远非洲\"," + "\"shiptype\": \"0\","
				+ "\"shipwidth\": 46," + "\"speed\": 11.4,"
				+ "\"state\": \"0\"," + "\"truehending\": 9.1,"
				+ "\"updatetime\": 1374913313" + " }" + " ]" + "	} ";
		//Type listType = new TypeToken<LinkedList<Ship>>(){}.getType();
		Gson gson = new Gson();


		ShipReturnSet rSet= gson.fromJson(json,  
                new TypeToken<ShipReturnSet>() {  
                }.getType()); 
		System.out.println(rSet);
	}
	
	@Test
	public void TestGson2() throws IOException {
		String json = "{" + "\"return\": [" + "{" + "\"averagespeed\": 0,"
				+ "\"callsign\": \"3ERX3\"," + "\"destination\": \"\","
				+ "\"direction\": 83.8," + "\"distanceMoved\": 0,"
				+ "\"draft\": 145," + "\"eta\": \"\","
				+ "\"gpstime\": \"2013-07-27 13:27:27\","
				+ "\"gpstimepre\": \"2013-07-27 01:45:57\","
				+ "\"imo\": \"9345439\"," + "\"lat\": 5.811647,"
				+ "\"latitude\": \"5°48'41.92\\\"N\"," + "\"latpre\": 5.69757,"
				+ "\"lon\": 85.511343,"
				+ "\"longitude\": \"85°30'40.83\\\"E\","
				+ "\"lonpre\": 83.110717," + "\"mmsi\": \"370188000\","
				+ "\"reporttype\": \"6\"," + "\"shipflag\": \"370\","
				+ "\"shipid\": \"201208141141174119032\","
				+ "\"shiplength\": 349," + "\"shipname\": \"COSCO AFRICA\","
				+ "\"shipnamecn\": \"中远非洲\"," + "\"shiptype\": \"0\","
				+ "\"shipwidth\": 46," + "\"speed\": 11.4,"
				+ "\"state\": \"0\"," + "\"truehending\": 9.1,"
				+ "\"updatetime\": 1374913313" + " }" + " ]" + "	} ";
		//Type listType = new TypeToken<LinkedList<Ship>>(){}.getType();
		Gson gson = new Gson();

		Map<String, Object> retMap = gson.fromJson(json,  
                new TypeToken<Map<String, List<Object>>>() {  
                }.getType()); 
		for (String key : retMap.keySet()) {  
            System.out.println("key:" + key + " values:" + retMap.get(key));  
            if (key.equals("return")) {  
                List<Ship> shipList = (List<Ship>) retMap.get(key);
                Ship aShip = shipList.get(0);
                System.out.println(aShip);  
            } 
        }  
	}
}
