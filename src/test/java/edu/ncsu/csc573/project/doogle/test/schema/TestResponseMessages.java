package edu.ncsu.csc573.project.doogle.test.schema;

import edu.ncsu.csc573.project.common.messages.ChangePasswordResponseMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import junit.framework.Assert;

import org.junit.Test;
import edu.ncsu.csc573.project.common.messages.EnumOperationType;
import edu.ncsu.csc573.project.common.messages.EnumParamsType;
import edu.ncsu.csc573.project.common.messages.IParameter;
import edu.ncsu.csc573.project.common.messages.IResponse;
import edu.ncsu.csc573.project.common.messages.InvalidResponseMessage;
import edu.ncsu.csc573.project.common.messages.LoginResponseMessage;
import edu.ncsu.csc573.project.common.messages.LogoutResponseMessage;
import edu.ncsu.csc573.project.common.messages.Parameter;
import edu.ncsu.csc573.project.common.messages.PublishResponseMessage;
import edu.ncsu.csc573.project.common.messages.RegisterResponseMessage;
import edu.ncsu.csc573.project.common.messages.ForgotPWDResponseMessage;
import edu.ncsu.csc573.project.common.messages.ResponseMessage;
import java.math.BigInteger;

public class TestResponseMessages {

/*	@Test
	public void testInvalidResponseToXML() throws Exception {
		IResponse regRequest = getInvalidResponse();

		try {
			System.out.println(regRequest.getRequestInXML());
			Assert.assertTrue("Successfully parsed xml", true);
		} catch (Exception e) {
                    e.printStackTrace();
                    Assert.fail();
			
		}
	}
        
	public static IResponse getInvalidResponse() throws Exception {
		IResponse regRequest = new InvalidResponseMessage();
		IParameter Regparams = new Parameter();
		Regparams.add(EnumParamsType.STATUSCODE, new BigInteger(String.valueOf(0)));
		Regparams.add(EnumParamsType.MESSAGE, "abcdef");
		regRequest.createResponse(EnumOperationType.INVALIDRESPONSE, Regparams);
		//System.out.println(regRequest.getRequestInXML());
		return regRequest;
	}
	
	
	@Test
	public void testRegisterResponseMessageToXML() throws Exception {
		IResponse regRequest = getRegisterResponse();

		try {
			System.out.println(regRequest.getRequestInXML());
			Assert.assertTrue("Successfully parsed xml", true);
		} catch (Exception e) {
                    e.printStackTrace();
                    Assert.fail();
			
		}
	}
        
	public static IResponse getRegisterResponse() throws Exception {
		IResponse regRequest = new RegisterResponseMessage();
		IParameter Regparams = new Parameter();
		Regparams.add(EnumParamsType.STATUSCODE, new BigInteger(String.valueOf(0)));
		Regparams.add(EnumParamsType.MESSAGE, "abcdef");
		regRequest.createResponse(EnumOperationType.REGISTERRESPONSE, Regparams);
		//System.out.println(regRequest.getRequestInXML());
		return regRequest;
	}
    
	
    @Test
	public void testLoginResponseMessageToXML() throws Exception {
		IResponse regRequest = getLoginResponse();

		try {
			System.out.println(regRequest.getRequestInXML());
			Assert.assertTrue("Successfully parsed xml", true);
		} catch (Exception e) {
                    e.printStackTrace();
                    Assert.fail();
			
		}
	}
        
	public static IResponse getLoginResponse() throws Exception {
		IResponse regRequest = new LoginResponseMessage();
		IParameter Regparams = new Parameter();
		Regparams.add(EnumParamsType.STATUSCODE, new BigInteger(String.valueOf(1)));
		Regparams.add(EnumParamsType.MESSAGE, "abcdefed");
		regRequest.createResponse(EnumOperationType.LOGINRESPONSE, Regparams);
		//System.out.println(regRequest.getRequestInXML());
		return regRequest;
	}
        
      @Test
	public void testLogoutResponseMessageToXML() throws Exception {
		IResponse regRequest = getLogoutResponse();

		try {
			System.out.println(regRequest.getRequestInXML());
			Assert.assertTrue("Successfully parsed xml", true);
		} catch (Exception e) {
                    e.printStackTrace();
                    Assert.fail();
			
		}
	}
        
	public static IResponse getLogoutResponse() throws Exception {
		IResponse regRequest = new LogoutResponseMessage();
		IParameter Regparams = new Parameter();
		Regparams.add(EnumParamsType.STATUSCODE, new BigInteger(String.valueOf(0)));
		regRequest.createResponse(EnumOperationType.LOGOUTRESPONSE, Regparams);
		//System.out.println(regRequest.getRequestInXML());
		return regRequest;
	}
        
        
       
        
        @Test
	public void testPublishResponseMessageToXML() throws Exception {
		IResponse regRequest = getPublishResponse();

		try {
			System.out.println(regRequest.getRequestInXML());
			Assert.assertTrue("Successfully parsed xml", true);
		} catch (Exception e) {
                    e.printStackTrace();
                    Assert.fail();
			
		}
	}
        
	public static IResponse getPublishResponse() throws Exception {
		IResponse regRequest = new PublishResponseMessage();
		IParameter Regparams = new Parameter();
		Regparams.add(EnumParamsType.STATUSCODE, new BigInteger(String.valueOf(0)));
		Regparams.add(EnumParamsType.MESSAGE, "abcdef");
		regRequest.createResponse(EnumOperationType.PUBLISHRESPONSE, Regparams);
		//System.out.println(regRequest.getRequestInXML());
		return regRequest;
	}
        
        
        @Test
	public void testForgotPWDResponseMessageToXML() throws Exception {
		IResponse regRequest = getForgotPWDResponse();

		try {
			System.out.println(regRequest.getRequestInXML());
			Assert.assertTrue("Successfully parsed xml", true);
		} catch (Exception e) {
                    e.printStackTrace();
                    Assert.fail();
			
		}
	}
        
	public static IResponse getForgotPWDResponse() throws Exception {
		IResponse regRequest = new ForgotPWDResponseMessage();
		IParameter Regparams = new Parameter();
		Regparams.add(EnumParamsType.STATUSCODE, new BigInteger(String.valueOf(0)));
		Regparams.add(EnumParamsType.MESSAGE, "abcdef");
		regRequest.createResponse(EnumOperationType.FORGOTPASSWORDRESPONSE, Regparams);
		//System.out.println(regRequest.getRequestInXML());
		return regRequest;
	}
	 
 
        @Test
	public void testChangePasswordResponseMessageToXML() throws Exception {
		IResponse regRequest = getChangePasswordResponse();

		try {
			System.out.println(regRequest.getRequestInXML());
			Assert.assertTrue("Successfully parsed xml", true);
		} catch (Exception e) {
                    e.printStackTrace();
                    Assert.fail();
			
		}
	}
        
	public static IResponse getChangePasswordResponse() throws Exception {
		IResponse regRequest = new ChangePasswordResponseMessage();
		IParameter Regparams = new Parameter();
		Regparams.add(EnumParamsType.STATUSCODE, new BigInteger(String.valueOf(0)));
		Regparams.add(EnumParamsType.MESSAGE, "abcdef");
		regRequest.createResponse(EnumOperationType.CHANGEPASSWORDRESPONSE, Regparams);
		//System.out.println(regRequest.getRequestInXML());
		return regRequest;
	}        
        
        
        
                     
	@Test
	public void testRegisterResponseMessageParseXML() {
		InputStream is = ClassLoader
				.getSystemResourceAsStream("TestSampleRegisterResponse.xml");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		StringBuffer sb = new StringBuffer();
		String temp;
		try {
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
		}

		ResponseMessage regReq = new RegisterResponseMessage();
		regReq.parseXML(sb.toString());
		Assert.assertEquals(EnumOperationType.REGISTERRESPONSE,
				regReq.getOperationType());
		Assert.assertEquals("0",
				regReq.getParameter().getParamValue(EnumParamsType.STATUSCODE)
						.toString());
		Assert.assertEquals("abcdef", regReq.getParameter()
				.getParamValue(EnumParamsType.MESSAGE).toString());
	}
    
	@Test
	public void testInvalidResponseMessageParseXML() {
		InputStream is = ClassLoader
				.getSystemResourceAsStream("TestInvalidResponse.xml");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		StringBuffer sb = new StringBuffer();
		String temp;
		try {
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
		}

		ResponseMessage regReq = new InvalidResponseMessage();
		regReq.parseXML(sb.toString());
		Assert.assertEquals(EnumOperationType.INVALIDRESPONSE,
				regReq.getOperationType());
		Assert.assertEquals("0",
				regReq.getParameter().getParamValue(EnumParamsType.STATUSCODE)
						.toString());
		Assert.assertEquals("qwerty1", regReq.getParameter()
				.getParamValue(EnumParamsType.MESSAGE).toString());
	}
	
	@Test
	public void testLoginResponseMessageParseXML() {
		InputStream is = ClassLoader
				.getSystemResourceAsStream("TestLoginResponse.xml");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		StringBuffer sb = new StringBuffer();
		String temp;
		try {
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
		}

		ResponseMessage loginReq = new LoginResponseMessage();
		loginReq.parseXML(sb.toString());
		Assert.assertEquals(EnumOperationType.LOGINRESPONSE,
				loginReq.getOperationType());
		Assert.assertEquals("1",
				loginReq.getParameter().getParamValue(EnumParamsType.STATUSCODE)
						.toString());
		Assert.assertEquals("abcdef", loginReq.getParameter()
				.getParamValue(EnumParamsType.MESSAGE).toString());
	}
	
	
	@Test
	public void testLogoutResponseMessageParseXML() {
		InputStream is = ClassLoader
				.getSystemResourceAsStream("TestLogoutResponse.xml");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		StringBuffer sb = new StringBuffer();
		String temp;
		try {
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
		}

		ResponseMessage logoutReq = new LogoutResponseMessage();
		logoutReq.parseXML(sb.toString());
		Assert.assertEquals(EnumOperationType.LOGOUTRESPONSE,
				logoutReq.getOperationType());
		Assert.assertEquals("0",
				logoutReq.getParameter().getParamValue(EnumParamsType.STATUSCODE)
						.toString());
	}
	
	@Test
	public void testForgotPWDResponseMessageParseXML() {
		InputStream is = ClassLoader
				.getSystemResourceAsStream("TestForgotPWDResponse.xml");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		StringBuffer sb = new StringBuffer();
		String temp;
		try {
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
		} catch (IOException e) {
			//e.printStackTrace();
			Assert.fail();
		}

		ResponseMessage forgotPWDReq = new ForgotPWDResponseMessage();
		forgotPWDReq.parseXML(sb.toString());
		Assert.assertEquals(EnumOperationType.FORGOTPASSWORDRESPONSE,
				forgotPWDReq.getOperationType());
		Assert.assertEquals("0",
				forgotPWDReq.getParameter().getParamValue(EnumParamsType.STATUSCODE)
						.toString());
		Assert.assertEquals("abcdef", forgotPWDReq.getParameter()
				.getParamValue(EnumParamsType.MESSAGE).toString());
	}
	
        
        @Test
	public void testChangePWDResponseMessageParseXML() {
		InputStream is = ClassLoader
				.getSystemResourceAsStream("TestChangePasswordResponse.xml");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		StringBuffer sb = new StringBuffer();
		String temp;
		try {
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
		} catch (IOException e) {
			//e.printStackTrace();
			Assert.fail();
		}

		ResponseMessage changePWDReq = new ChangePasswordResponseMessage();
		changePWDReq.parseXML(sb.toString());
		Assert.assertEquals(EnumOperationType.CHANGEPASSWORDRESPONSE,
				changePWDReq.getOperationType());
		Assert.assertEquals("0",
				changePWDReq.getParameter().getParamValue(EnumParamsType.STATUSCODE)
						.toString());
		Assert.assertEquals("abcdef", changePWDReq.getParameter()
				.getParamValue(EnumParamsType.MESSAGE).toString());
	}
	
        
        
	@Test
	public void testPublishResponseMessageParseXML() {
		InputStream is = ClassLoader
				.getSystemResourceAsStream("TestPublishResponse.xml");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		StringBuffer sb = new StringBuffer();
		String temp;
		try {
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
		} catch (IOException e) {
			Assert.fail();
		}

		ResponseMessage publishReq = new PublishResponseMessage();
		publishReq.parseXML(sb.toString());
		Assert.assertEquals(EnumOperationType.PUBLISHRESPONSE,
				publishReq.getOperationType());
		Assert.assertEquals(String.valueOf(0),
				publishReq.getParameter().getParamValue(EnumParamsType.STATUSCODE)
						.toString());
		Assert.assertEquals("abcdef", publishReq.getParameter()
				.getParamValue(EnumParamsType.MESSAGE).toString());
	}
	*/
}
