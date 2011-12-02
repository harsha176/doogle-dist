package edu.ncsu.csc573.project.doogle.test.controllayer;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc573.project.commlayer.CommunicationServiceFactory;
import edu.ncsu.csc573.project.controllayer.Controller;
import edu.ncsu.csc573.project.controllayer.IController;

public class TestController {
	private IController controller;
	
	@Before
	public void initialize() {
		try {
			controller = Controller.getInstance();
		} catch (Exception e) {
			Assert.fail();
		}
	}
	@Test
	public void testInitialization() {
		try {
			controller = Controller.getInstance();
			Assert.assertEquals(true, CommunicationServiceFactory.getInstance().isPeerServerRunning());
		} catch (Exception e) {
			Assert.fail();
			e.printStackTrace();
		}
	}
	
	@Test 
	public void testRegister() {
		try {
			controller.connect("localhost");
			//controller.register("Harshavardhan", "Malipatel", "harsha", "harsha", "harsha176@123.com", "student");
			//controller.login("harsha", "harsha");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
