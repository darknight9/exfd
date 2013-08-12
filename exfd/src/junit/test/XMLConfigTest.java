package junit.test;

import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.SubnodeConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.tree.xpath.XPathExpressionEngine;
import org.junit.Test;

public class XMLConfigTest {

	@Test
	public void testXmlConfig() {
		try {
			XMLConfiguration config = new XMLConfiguration("containers.xml");
			config.setExpressionEngine(new XPathExpressionEngine());
			config.setDelimiterParsingDisabled(true);
			config.setAttributeSplittingDisabled(true);
			
			String complex = config.getString("/container[@company='EVERGREEN']/name");
			System.out.println(complex);
			System.out.println(this.getClass().getSimpleName());
			String companyclass = "LineEVERGREEN";
			String method = config.getString("/container[@classname='"+companyclass+"']/search/@method");
			System.out.println(method);
			SubnodeConfiguration sub = config.configurationAt("/container[@classname='"+companyclass+"']/search");
			System.out.println(sub.getString("@method"));
			
			List<HierarchicalConfiguration> params = sub.configurationsAt("params/param");
			for(HierarchicalConfiguration param : params){
			    String paramName = param.getString("@name");
			    String paramValue = param.getString("@value");
			    System.out.println("name="+paramName+"value="+paramValue);
			}
			//title[@lang='eng']
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
