package junit.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4JTest {
	private static Logger logger = LogManager.getLogger("Log4JTest"); 
	public static void main(String[] args) {
		logger.entry();
		logger.info("hello, in Log4JTest!");
		logger.error("hello, again!");
		logger.exit();
	}
}
