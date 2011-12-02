/**
 * 
 */
package edu.ncsu.csc573.project.doogle.test.commlayer;

import edu.ncsu.csc573.project.commlayer.ClientHandler;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import edu.ncsu.csc573.project.commlayer.CommunicationServiceFactory;
import edu.ncsu.csc573.project.commlayer.ICommunicationService;
import edu.ncsu.csc573.project.common.ConfigurationManager;
import edu.ncsu.csc573.project.common.messages.EnumOperationType;
import edu.ncsu.csc573.project.common.messages.IResponse;
import edu.ncsu.csc573.project.doogle.test.schema.TestRequestMessages;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.UnknownHostException;

/**
 * @author doogle-dev
 *
 */
public class TestCommunicationService {

	//private static ICommunicationService CommService;
	/**
	 * @throws java.lang.Exception
	 *
	 */
	/*
	@BeforeClass 
	public static void classSetup () throws Exception {
		
	}
	
	@Before
	public void setUp() throws Exception {
		//CommService = CommunicationServiceFactory.getInstance();
	}

	/*@Test
	public void testInitializeForUnknownHost() {
		ICommunicationService CommService = CommunicationServiceFactory.getInstance();
		try {
			CommService.initialize("192.168.1.1", null);
			Assert.assertFalse("Connected to invalid host", true);
		} catch (UnknownHostException e) {
			Assert.assertTrue("UnknownHostExceptionThrown", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	/*
	@Test
	public void testInitializeForKnownHost() {
		
		try {
			
			Assert.assertTrue("Connected to google.com", true);
		} catch (UnknownHostException e) {
			Assert.assertFalse("Unable to connect to google.com", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	@Test
	public void testExecuteRequest() throws Exception{
		//Assert.fail("Execute request functionality not implemented"); 
		//ICommunicationService commService = CommunicationServiceFactory.getInstance();
		
		ICommunicationService CommService = CommunicationServiceFactory.getInstance();
		IResponse response;
		CommService.initialize(null);
		CommService.initialize(null);
		
		//CommService.close();
		
		//CommService.executeRequest(request, ipaddress)
		String ipaddress = "127.0.0.1";
		response = CommService.executeRequest(TestRequestMessages.getRegisterRequest(),ipaddress);
		Assert.assertEquals(response.getOperationType(), EnumOperationType.REGISTERRESPONSE);
		//Assert.assertEquals(response.getStatus().getErrorId().intValue(), 0);
		//Assert.assertEquals(expected, actual)
		System.out.println("Response is : " + response);
		response = CommService.executeRequest(TestRequestMessages.getLoginRequest(),ipaddress);
		Assert.assertEquals(response.getOperationType(), EnumOperationType.LOGINRESPONSE);
		Assert.assertEquals(response.getStatus().getErrorId().intValue(), 1);
		
		//while(true) {
			
		//}*/
		/*response = CommService.executeRequest(TestRequestMessages.getLogoutRequest());
		Assert.assertEquals(response.getOperationType(), EnumOperationType.LOGOUTRESPONSE);
		Assert.assertEquals(response.getStatus().getErrorId().intValue(), 0);
		//CommService.executeRequest(TestRequestMessages.getSearchRequest());
		/*while(true) {
			Thread.sleep(1000);
		//}*/
	}
	
	/*@Test
	public void testDownloadFile() {
		ICommunicationService CommService = CommunicationServiceFactory.getInstance();
		try {
                      //File toBeUploadedFile = new File(ConfigurationManager.getInstance().getPublishDirectory(), ClientHandler.getFileName(TestClientHandler.prepareGetFileRequest("IMP.txt")));
                      //Assert.assertEquals(true, toBeUploadedFile.isFile());
                        //FileReader fw = new FileReader(toBeUploadedFile);
                        //BufferedReader br = new BufferedReader(new FileReader(toBeUploadedFile));
                        //fw.read();
			CommService.initialize("localhost", new DefaultPublishHandler());
			Assert.assertNotNull(CommService.getFile("localhost", "IMP - Test.txt"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*@Test
	public void testLogoutScenarion1(){
		// login first 
		
	}*/
	/*@Test
	public void testPublishRequest() {
		Assert.fail("Publish request functionality not implemented");
	}
	
	@Test 
	public void testSubscribeRequestTopic() {
		Assert.fail("Subscribe request topic functionality not implemented");
	}
	
	
	@Test
	public void testClose() {
		Assert.fail("Close functionality not implemented");
	}*/
	
	/**
	 * @throws java.lang.Exception
	 */
	/*@After
	public void tearDown() throws Exception {
	}

	/*@Test
	public void test() {
		fail("Not yet implemented");
	}*/

}
