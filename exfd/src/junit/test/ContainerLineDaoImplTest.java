package junit.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.exfd.dao.impl.ContainerLineDaoImpl;
import com.exfd.domain.Container;
import com.exfd.ship.first.LineBase;
import com.exfd.util.ContainerUtils;

public class ContainerLineDaoImplTest {

	private static Logger logger = LogManager.getLogger();
	
	@Test
	public void testFind() throws Exception{
		ContainerLineDaoImpl impl = new ContainerLineDaoImpl();
		ContainerUtils.LoadConfig();
		LineBase.LoadConfig();
		
		String code = "CMAU1396117";
		
		Container container = impl.find(code, true);

		logger.debug(
				"container[{}]: download[{}], found[{}], error[{}]",
				code, container.getDownload(), container.getFound(),
				container.getError());

		PrintWriter out2 = new PrintWriter(new File(
				"/Users/david/Developer/TestData/" + code + "J.html"));
		out2.print(container.getJsonString());
		out2.flush();
		out2.close();
	}
}
