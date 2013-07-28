package junit.test;

import org.junit.Test;

import com.webservice.GeneralShipWebServiceImplServiceStub;
import com.webservice.GetCodeEnCnInfo;
import com.webservice.GetCodeEnCnInfoE;
import com.webservice.GetSearchCountByKeyAndTypeInShipBaseInfo;
import com.webservice.GetSearchCountByKeyAndTypeInShipBaseInfoE;
import com.webservice.GetSearchRecByKeyAndTypeInShipBaseInfo;
import com.webservice.GetSearchRecByKeyAndTypeInShipBaseInfoE;

public class TestWs {
	
	@Test
	public void testgetCodeEnCnInfo() throws Exception {
		//初始化Sub类
		GeneralShipWebServiceImplServiceStub stub = new GeneralShipWebServiceImplServiceStub();
		
		//传递AxisServiceStub.ShowName对象，相关参数在这边赋值。
		GetCodeEnCnInfo info = new GetCodeEnCnInfo();
		GetCodeEnCnInfoE command = new GetCodeEnCnInfoE();
		command.setGetCodeEnCnInfo(info);
		//command.setName("Hello!");
		//取得返回值
		String name = stub.getCodeEnCnInfo(command).getGetCodeEnCnInfoResponse().get_return();
		System.out.println(name);
	}
	
	@Test
	public void testgetSearchCountByKeyAndTypeInShipBaseInfo() throws Exception {
		//初始化Sub类
		GeneralShipWebServiceImplServiceStub stub = new GeneralShipWebServiceImplServiceStub();
		
		GetSearchCountByKeyAndTypeInShipBaseInfo info = new GetSearchCountByKeyAndTypeInShipBaseInfo();
		info.setArg0("中远非洲");
		info.setArg1("name");
		
		GetSearchCountByKeyAndTypeInShipBaseInfoE command = new GetSearchCountByKeyAndTypeInShipBaseInfoE();
		command.setGetSearchCountByKeyAndTypeInShipBaseInfo(info);
		//command.setName("Hello!");
		//取得返回值
		String name = stub.getSearchCountByKeyAndTypeInShipBaseInfo(command).getGetSearchCountByKeyAndTypeInShipBaseInfoResponse().get_return();
		System.out.println(name);
	}
	
	@Test
	public void testgetSearchRecByKeyAndTypeInShipBaseInfo() throws Exception {
		//初始化Sub类
		GeneralShipWebServiceImplServiceStub stub = new GeneralShipWebServiceImplServiceStub();
		
		GetSearchRecByKeyAndTypeInShipBaseInfo info = new GetSearchRecByKeyAndTypeInShipBaseInfo();
		info.setArg0("");
		info.setArg1("中远非洲");
		info.setArg2("name");
		info.setArg3(1);
		info.setArg4(2);
		
		GetSearchRecByKeyAndTypeInShipBaseInfoE command = new GetSearchRecByKeyAndTypeInShipBaseInfoE();
		command.setGetSearchRecByKeyAndTypeInShipBaseInfo(info);
		//command.setName("Hello!");
		//取得返回值
		String name = stub.getSearchRecByKeyAndTypeInShipBaseInfo(command).getGetSearchRecByKeyAndTypeInShipBaseInfoResponse().get_return();
		
		System.out.println(name);
	}

}
