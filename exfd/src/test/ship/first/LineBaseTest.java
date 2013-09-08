package test.ship.first;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.exfd.domain.Container;
import com.exfd.exception.ContainerNotFoundException;
import com.exfd.exception.ContainerParseException;
import com.exfd.ship.first.*;
import com.exfd.ship.other.*;
import com.exfd.ship.second.*;

// "EXFDCODE"
// TCNU8691959
public class LineBaseTest {
	private static Logger logger = LogManager.getLogger();

	@Test
	public void TestDownload() throws Exception {
		LineBase.LoadConfig();

		LineBase line = new LineOOCL();
		String code = "OOLU1037233";
		String strPage = null;
		try {
			strPage = line.GetPage(code);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter out = new PrintWriter(new File(
				"/Users/david/Developer/TestData/" + code + ".html"));
		out.print(strPage);
		out.flush();
		out.close();
	}

	
	@Test
	public void TestGetContainerByPage() throws Exception {
		LineBase.LoadConfig();

		LineBase line = new LineOOCL();
		String code = "OOLU1037233";

		FileInputStream in = new FileInputStream(
				"/Users/david/Developer/TestData/" + code + ".html");
		byte[] readBytes = new byte[in.available()];
		in.read(readBytes);
		String strPage = new String(readBytes);

		logger.debug("{} : get page length : {}", code, strPage.length());

		Container container = line.GetContainerByPage(code, strPage);
		logger.debug(
				"container[{}]: download[{}], found[{}], error[{}]",
				code, container.getDownload(), container.getFound(),
				container.getError());

		// 由于table string不会再生成了，所以测试函数自己生成。
		String tableString = line.status2Table(container.getStatus());
		PrintWriter out = new PrintWriter(new File(
				"/Users/david/Developer/TestData/" + code + "T.html"));
		out.print(tableString);
		out.flush();
		out.close();

		PrintWriter out2 = new PrintWriter(new File(
				"/Users/david/Developer/TestData/" + code + "J.html"));
		out2.print(container.getJsonString());
		out2.flush();
		out2.close();
		
		PrintWriter out3 = new PrintWriter(new File(
				"/Users/david/Developer/TestData/" + code + "H.html"));
		out3.print(container.getHttpresult());
		out3.flush();
		out3.close();
		
		

	}

}
