package junit.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.exfd.domain.Container;
import com.exfd.ship.first.LineBase;
import com.exfd.util.ContainerUtils;

public class ContainerUtilsTest {

	private static Logger logger = LogManager.getLogger();
	
	@Test
	public void testLoad(){
		ContainerUtils.LoadConfig();
		String prefix = ContainerUtils.getCompany("EISU9931581");
		System.out.print(prefix);
	}
	
	@Test
	public void testGetPage() throws Exception{
		
		ContainerUtils.LoadConfig();
		LineBase.LoadConfig();
		
		String code = "CAXU9286394";;
		String strPage = ContainerUtils.GetPage(code);
		PrintWriter out = new PrintWriter(new File("/Users/david/Developer/TestData/" + code + ".html"));
		out.print(strPage);
		out.flush();
		out.close();
	}
	
	@Test
	public void testGetContainerByPage() throws Exception{
		
		ContainerUtils.LoadConfig();
		LineBase.LoadConfig();
		
		String code = "CBHU1835330";
		FileInputStream in = new FileInputStream(
				"/Users/david/Developer/TestData/" + code + ".html");
		byte[] readBytes = new byte[in.available()];
		in.read(readBytes);
		String strPage = new String(readBytes);
		
		Container container = ContainerUtils.GetContainerByPage(code, strPage);

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
	
	@Test
	public void testGetContainer() throws Exception{
		
		ContainerUtils.LoadConfig();
		LineBase.LoadConfig();
		
		String code = "CMAU1396117";
		
		Container container = ContainerUtils.GetContainer(code);

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
