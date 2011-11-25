package edu.ncsu.csc573.projcet.doogle.test.usermanagement;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import edu.ncsu.csc573.project.common.EncDecUtil;
import edu.ncsu.csc573.project.controllayer.usermanagement.IUsersManager;
import edu.ncsu.csc573.project.controllayer.usermanagement.User;

public class TestUserManager {
	
	@BeforeClass
	public static void setUpClass() {
		try {
			IUsersManager.getInstance().flushDatabase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Before
	public void setUp() {
		try {
			IUsersManager.getInstance().flushDatabase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUserManagerSingleTon() {
		IUsersManager userManager1;
		IUsersManager userManager2;
		try {
			userManager1 = IUsersManager.getInstance();
			userManager2 = IUsersManager.getInstance();
			Assert.assertEquals(userManager1, userManager2);
		} catch (Exception e) {
			Assert.assertFalse(true);
			e.printStackTrace();
		}
	}
	
	@Test 
	public void testUserAdd() {
		try {
			IUsersManager usermanager = IUsersManager.getInstance();
			User aUser = new User();
			String username = "harsha176";
			aUser.setUsername(username);
			String firstName = "Harshavardhan";
			aUser.setFirstName(firstName);
			String lastName = "Malipatel";
			aUser.setLastName(lastName);
			String password = "abcdefg";
			aUser.setPassword(password);
			String emailID = "harsha176@gmail.com";
			aUser.setEmailID(emailID);
			
			usermanager.addUser(aUser);
			User checkUser = (User)usermanager.getUser(username);
			Assert.assertEquals(username, checkUser.getUsername());
			Assert.assertEquals(firstName, checkUser.getFirstName());
			Assert.assertEquals(lastName, checkUser.getLastName());
			Assert.assertEquals(password, EncDecUtil.decryptMessage(checkUser.getPassword()));
			Assert.assertEquals(emailID, checkUser.getEmailID());
			
		} catch (Exception e) {
			Assert.assertFalse(true);
			e.printStackTrace();
		}
	}
	
	@Test
	public void testValidUserLogin() {
		try {
			
			IUsersManager usermanager = IUsersManager.getInstance();
			usermanager.addUser(createUser());
			usermanager.userLogin("harsha176", "abcdefg");
			Assert.assertEquals(true, usermanager.isUserLoggedin("harsha176"));
			//usermanager.userLogout("harsha176");
			//Assert.assertEquals(false, usermanager.isUserLoggedin("harsha176"));
		} catch (Exception e) {
			Assert.assertFalse(true);
			e.printStackTrace();
		}
	}
	
	@Test 
	public void testInvalidCredentialsLogin() {
		IUsersManager usermanager=null;
		try {
			usermanager = IUsersManager.getInstance();
			usermanager.userLogin("harsha176", "dsaqwerty");
			Assert.assertEquals(false, usermanager.isUserLoggedin("harsha176"));
		} catch (Exception e) {
			Assert.assertTrue(true);
			Assert.assertEquals(false, usermanager.isUserLoggedin("harsha176"));
			//e.printStackTrace();
		}
	}
	@Test 
	public void testInvalidUserLogin() {
		IUsersManager usermanager = null;
		try {
			usermanager = IUsersManager.getInstance();
			usermanager.userLogin("harsha", "dsaqwerty");
			Assert.assertEquals(false, usermanager.isUserLoggedin("harsha"));
		} catch (Exception e) {
			Assert.assertTrue(true);
			Assert.assertEquals(false, usermanager.isUserLoggedin("harsha"));
			//e.printStackTrace();
		}
	}
	
	@Test
	public void testValidChangePassword() {
		try {
			IUsersManager usermanager = IUsersManager.getInstance();
			String username = "krishna";
			String oldpassword = "qwerty";
			usermanager.addUser(new User(username, oldpassword, "Krishna", "Devarapu", "krishan136@gmail.com", "guest"));
			usermanager.userLogin(username, oldpassword);
			String newPassword = "abcdefg";
			usermanager.changePassword(username,oldpassword,newPassword);
			Assert.assertEquals(newPassword, EncDecUtil.decryptMessage(((User)usermanager.getUser(username)).getPassword()));
			usermanager.changePassword(username, newPassword, oldpassword);
			Assert.assertEquals(oldpassword, EncDecUtil.decryptMessage(((User)usermanager.getUser(username)).getPassword()));
		} catch (Exception e) {
			Assert.assertFalse(true);
			e.printStackTrace();
		}
	}
	
	@Test
	public void testForgotPasswd() {
		try {
			IUsersManager usermanager = IUsersManager.getInstance();
			String username = "Roger";
			String oldpassword = "Admin123";
			usermanager.addUser(new User(username, oldpassword, "Roger", "Fin", "harsha176@gmail.com", "guest"));
			usermanager.forgotPassword(username);	
			Assert.assertNotSame(oldpassword, ((User)usermanager.getUser(username)).getPassword());
		} catch (Exception e) {
			Assert.assertFalse(true);
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public static void tearDownClass() {
		try {
			IUsersManager.getInstance().flushDatabase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@After
	public void tearDown() {
		try {
			IUsersManager.getInstance().flushDatabase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private User createUser() {
		User aUser = new User();
		String username = "harsha176";
		aUser.setUsername(username);
		String firstName = "Harshavardhan";
		aUser.setFirstName(firstName);
		String lastName = "Malipatel";
		aUser.setLastName(lastName);
		String password = "abcdefg";
		aUser.setPassword(password);
		String emailID = "harsha176@gmail.com";
		aUser.setEmailID(emailID);
		
		return aUser;
	}
}
