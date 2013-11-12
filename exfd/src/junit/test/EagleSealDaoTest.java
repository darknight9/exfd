package junit.test;

import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.exfd.dao.SealDao;
import com.exfd.dao.SealRecordDao;
import com.exfd.dao.impl.SealEagleDaoImpl;
import com.exfd.dao.impl.SealRecordEagleDaoImpl;
import com.exfd.domain.Seal;
import com.exfd.util.EagleGPSUtils;

public class EagleSealDaoTest {
	
	static Logger logger = LogManager.getLogger();
	
	@Test
	public void testTrackVehicle() {
		EagleGPSUtils eagle = new EagleGPSUtils();
		String resString = eagle.trackVehicle("3004");
		System.out.println(resString);
		
		SealEagleDaoImpl dao = new SealEagleDaoImpl();
		Seal seal = dao.xml2seal(resString);
		System.out.println(seal);
	}
	
	@Test
	public void testFind() {
		SealEagleDaoImpl dao = new SealEagleDaoImpl();
		Seal seal = dao.find("2899");
		System.out.println(seal);
	}
	
	@Test
	public void testDate() {
		Calendar rightnow = Calendar.getInstance();
		
		// 规则：1天前的数据是失效的.
		rightnow.add(Calendar.DAY_OF_YEAR, -1);
		Date expire = rightnow.getTime();
		
		System.out.print(expire);
		
	}
	
	@Test
	public void testFillData() throws InterruptedException {
		SealDao dao = new SealEagleDaoImpl();	// 以后用工厂模式或者spring来解耦合.
		
		for (int i = 2438; i < 3000; i++) {
			Seal seal = dao.find("" + i);
			if (seal != null) {
				logger.debug("seal code[{}] fill OK.", i);
			} else {
				logger.debug("seal code[{}] FFFFFFFFFF NOT OOOOOOOOOOO.", i);
			}
			Thread.sleep(1000);
		}
	}

	
}
