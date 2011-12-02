package edu.ncsu.csc573.project.doogle.test.schema;

import edu.ncsu.csc573.project.common.messages.ChangePasswordRequestMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import junit.framework.Assert;

import org.junit.Test;
import edu.ncsu.csc573.project.common.messages.EnumOperationType;
import edu.ncsu.csc573.project.common.messages.EnumParamsType;
import edu.ncsu.csc573.project.common.messages.ForgotPwdRequestMessage;
import edu.ncsu.csc573.project.common.messages.IParameter;
import edu.ncsu.csc573.project.common.messages.IRequest;
import edu.ncsu.csc573.project.common.messages.LoginRequestMessage;
import edu.ncsu.csc573.project.common.messages.LogoutRequestMessage;
import edu.ncsu.csc573.project.common.messages.Parameter;
import edu.ncsu.csc573.project.common.messages.RegisterRequestMessage;
import edu.ncsu.csc573.project.common.messages.RequestMessage;
import edu.ncsu.csc573.project.common.messages.SearchRequestMessage;

/*
 import java.util.ArrayList;
 import javax.xml.bind.JAXBException;
 import edu.ncsu.csc573.project.common.messages.EnumOperationType;
 import edu.ncsu.csc573.project.common.messages.EnumParamsType;
 import edu.ncsu.csc573.project.common.messages.IParameter;
 import edu.ncsu.csc573.project.common.messages.IRequest;
 */

public class TestRequestMessages {

	/**
	 * @param args
	 * @throws Exception
	 * @throws JAXBException
	 */
	
	@Test
	public void testRegisterRequestMessageToXML() throws Exception {
		IRequest regRequest = getRegisterRequest();

		try {
			String requestInXML = regRequest.getRequestInXML();
			System.out.println(requestInXML);
			String id = regRequest.getId();
			Assert.assertNotSame("", regRequest.getId());
			regRequest.parseXML(requestInXML);
			Assert.assertEquals(id, regRequest.getId());
			//Assert.assertTrue("Successfully parsed xml", true);
		} catch (Exception e) {
			Assert.fail();
			// e.printStackTrace();
		}
	}

	public static IRequest getRegisterRequest() throws Exception {
		IRequest regRequest = new RegisterRequestMessage();
		regRequest.setId(""+System.currentTimeMillis());
		IParameter Regparams = new Parameter();
		Regparams.add(EnumParamsType.USERNAME, "krishna");
		Regparams.add(EnumParamsType.PASSWORD, "abcdef");
                Regparams.add(EnumParamsType.FIRSTNAME, "sri");
                Regparams.add(EnumParamsType.LASTNAME, "krishna");
		Regparams.add(EnumParamsType.EMAIL_ID, "harsha176@gmail.com");
		Regparams.add(EnumParamsType.DESIGNATION, "guest");
		regRequest.createRequest(EnumOperationType.REGISTER, Regparams);
		// System.out.println(regRequest.getRequestInXML());
		return regRequest;
	}

	/*@Test
	public void testLoginRequestMessageToXML() throws Exception {
		IRequest loginRequest = getLoginRequest();

		try {
			System.out.println(loginRequest.getRequestInXML());
			Assert.assertTrue("Successfully parsed xml", true);
		} catch (Exception e) {
			Assert.fail();
			// e.printStackTrace();
		}
	}*/

	public static IRequest getLoginRequest() throws Exception {
		IRequest loginRequest = new LoginRequestMessage();
		loginRequest.setId(""+System.currentTimeMillis());
		IParameter Loginparams = new Parameter();
		Loginparams.add(EnumParamsType.USERNAME, "harsha176");
		Loginparams.add(EnumParamsType.PASSWORD, "abcdef");
		Loginparams.add(EnumParamsType.IPADDRESS, "127.0.0.1");
		loginRequest.createRequest(EnumOperationType.LOGIN, Loginparams);
		// System.out.println(regRequest.getRequestInXML());
		return loginRequest;
	}

	/*@Test
	public void testLogoutRequestMessageToXML() throws Exception {
		IRequest logoutRequest = getLogoutRequest();

		try {
			System.out.println(logoutRequest.getRequestInXML());
			Assert.assertTrue("Successfully parsed xml", true);
		} catch (Exception e) {
			Assert.fail();
			// e.printStackTrace();
		}
	}

	public static IRequest getLogoutRequest() throws Exception {
		IRequest LogoutRequest = new LogoutRequestMessage();
		IParameter Logoutparams = new Parameter();
		Logoutparams.add(EnumParamsType.USERNAME, "harsha176");
		// System.out.println(regRequest.getRequestInXML());

		LogoutRequest.createRequest(EnumOperationType.LOGOUT, Logoutparams);
		return LogoutRequest;
	}

	@Test
	public void testForgotPWDMessageToXML() throws Exception {
		IRequest ForgotPWDRequest = getForgotPWDRequest();

		try {
			System.out.println(ForgotPWDRequest.getRequestInXML());
			Assert.assertTrue("Successfully parsed xml", true);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
			// e.printStackTrace();
		}
	}

	public static IRequest getForgotPWDRequest() throws Exception {
		IRequest ForgotPWDRequest = new ForgotPwdRequestMessage();
		IParameter ForgotPWDparams = new Parameter();
		ForgotPWDparams.add(EnumParamsType.USERNAME, "harsha176");
		//ForgotPWDparams.add(EnumParamsType.EMAIL_ID, "hmalipa@ncsu.edu");
		// System.out.println(regRequest.getRequestInXML());

		ForgotPWDRequest.createRequest(EnumOperationType.FORGOTPASSWORD,
				ForgotPWDparams);
		return ForgotPWDRequest;
	}

	@Test
	public void testchangePasswordMessageToXML() throws Exception {
		IRequest ChangePWDRequest = getChangePassword();

		try {
			System.out.println(ChangePWDRequest.getRequestInXML());
			Assert.assertTrue("Successfully parsed xml", true);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
			// e.printStackTrace();
		}
	}

	public static IRequest getChangePassword() throws Exception {
		IRequest ChangePWDRequest = new ChangePasswordRequestMessage();
		IParameter ChangePWDparams = new Parameter();
		ChangePWDparams.add(EnumParamsType.USERNAME, "harsha176");
		ChangePWDparams.add(EnumParamsType.PASSWORD, "hmalipa");
                ChangePWDparams.add(EnumParamsType.NEWPASSWORD, "krishna");
		// System.out.println(regRequest.getRequestInXML());

		ChangePWDRequest.createRequest(EnumOperationType.CHANGEPASSWORD,
				ChangePWDparams);
		return ChangePWDRequest;
	}

        
        
        @Test
	public void testSearchRequestMessageToXML() throws Exception {
		IRequest SearchRequest = getSearchRequest();

		try {
			System.out.println(SearchRequest.getRequestInXML());
			Assert.assertTrue("Successfully parsed xml", true);
		} catch (Exception e) {
			Assert.fail();
			// e.printStackTrace();
		}
	}

	public static IRequest getSearchRequest() throws Exception {
		IRequest SearchRequest = new SearchRequestMessage();
		IParameter Searchparams = new Parameter();
		Searchparams.add(EnumParamsType.USERNAME, "harsha176");
		Searchparams.add(EnumParamsType.SEARCHKEY, "12123412312");
		// System.out.println(regRequest.getRequestInXML());
		SearchRequest.createRequest(EnumOperationType.SEARCH, Searchparams);
		return SearchRequest;
	}


	@Test
	public void testRegisterRequestMessageParseXML() {
		InputStream is = ClassLoader
				.getSystemResourceAsStream("TestSampleRegisterRequest.xml");
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

		RequestMessage regReq = new RegisterRequestMessage();
		regReq.parseXML(sb.toString());
		Assert.assertEquals(EnumOperationType.REGISTER,
				regReq.getOperationType());
		Assert.assertEquals("harsha176",
				regReq.getParameter().getParamValue(EnumParamsType.USERNAME)
						.toString());
                Assert.assertEquals("harsha",
				regReq.getParameter().getParamValue(EnumParamsType.FIRSTNAME)
						.toString());
                Assert.assertEquals("reddy",
				regReq.getParameter().getParamValue(EnumParamsType.LASTNAME)
						.toString());
		Assert.assertEquals("harsha176@gmail.com", regReq.getParameter()
				.getParamValue(EnumParamsType.EMAIL_ID).toString());
		Assert.assertEquals("abcdef",
				regReq.getParameter().getParamValue(EnumParamsType.PASSWORD)
						.toString());
		Assert.assertEquals("guest",
				regReq.getParameter().getParamValue(EnumParamsType.DESIGNATION)
						.toString());
	}

	@Test
	public void testLoginRequestMessageParseXML() {
		InputStream is = ClassLoader
				.getSystemResourceAsStream("TestLoginRequest.xml");
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

		RequestMessage loginReq = new LoginRequestMessage();
		loginReq.parseXML(sb.toString());
		Assert.assertEquals(EnumOperationType.LOGIN,
				loginReq.getOperationType());
		Assert.assertEquals("harsha176",
				loginReq.getParameter().getParamValue(EnumParamsType.USERNAME)
						.toString());
		Assert.assertEquals("abcdef",
				loginReq.getParameter().getParamValue(EnumParamsType.PASSWORD)
						.toString());
	}

	@Test
	public void testLogoutRequestMessageParseXML() {
		InputStream is = ClassLoader
				.getSystemResourceAsStream("TestLogoutRequest.xml");
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

		RequestMessage logoutReq = new LogoutRequestMessage();
		logoutReq.parseXML(sb.toString());
		Assert.assertEquals(EnumOperationType.LOGOUT,
				logoutReq.getOperationType());
		Assert.assertEquals("harsha176", logoutReq.getParameter()
				.getParamValue(EnumParamsType.USERNAME).toString());
	}

	@Test
	public void testForgotPWDRequestMessageParseXML() {
		InputStream is = ClassLoader
				.getSystemResourceAsStream("TestForgotPWDRequest.xml");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		StringBuffer sb = new StringBuffer();
		String temp;
		try {
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
		} catch (IOException e) {
			// e.printStackTrace();
			Assert.fail();
		}

		RequestMessage forgotPWDReq = new ForgotPwdRequestMessage();
		forgotPWDReq.parseXML(sb.toString());
		Assert.assertEquals(EnumOperationType.FORGOTPASSWORD,
				forgotPWDReq.getOperationType());
		Assert.assertEquals("harsha176", forgotPWDReq.getParameter()
				.getParamValue(EnumParamsType.USERNAME).toString());
		//Assert.assertEquals("harsha176@gmail.com", forgotPWDReq.getParameter()
		//		.getParamValue(EnumParamsType.EMAIL_ID).toString());
	}

	
        @Test
	public void testChangePWDRequestMessageParseXML() {
		InputStream is = ClassLoader
				.getSystemResourceAsStream("TestChangePasswordRequest.xml");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		StringBuffer sb = new StringBuffer();
		String temp;
		try {
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
		} catch (IOException e) {
			// e.printStackTrace();
			Assert.fail();
		}

		RequestMessage changePWDReq = new ChangePasswordRequestMessage();
		changePWDReq.parseXML(sb.toString());
		Assert.assertEquals(EnumOperationType.CHANGEPASSWORD,
				changePWDReq.getOperationType());
		Assert.assertEquals("harsha176", changePWDReq.getParameter()
				.getParamValue(EnumParamsType.USERNAME).toString());
		Assert.assertEquals("hmalipa", changePWDReq.getParameter()
				.getParamValue(EnumParamsType.PASSWORD).toString());
                Assert.assertEquals("krishna", changePWDReq.getParameter()
				.getParamValue(EnumParamsType.NEWPASSWORD).toString());                
        }
        
        
        @Test
	public void testSearchRequestMessageParseXML() {
		InputStream is = ClassLoader
				.getSystemResourceAsStream("TestSearchRequest.xml");
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

		RequestMessage SearchReq = new SearchRequestMessage();
		SearchReq.parseXML(sb.toString());
		Assert.assertEquals(EnumOperationType.SEARCH,
				SearchReq.getOperationType());
		Assert.assertEquals("harsha176", SearchReq.getParameter()
				.getParamValue(EnumParamsType.USERNAME).toString());
		Assert.assertEquals("13801294803983440", SearchReq.getParameter()
				.getParamValue(EnumParamsType.SEARCHKEY).toString());
	}*/
}
