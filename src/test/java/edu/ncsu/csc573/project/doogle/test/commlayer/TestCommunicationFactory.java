/**
 * 
 */
package edu.ncsu.csc573.project.doogle.test.commlayer;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc573.project.commlayer.CommunicationServiceFactory;
import edu.ncsu.csc573.project.commlayer.ICommunicationService;

/**
 * @author doogle-dev
 * 
 */
public class TestCommunicationFactory {
	@Test
	public void testGetInstance() {
		ICommunicationService commService = CommunicationServiceFactory
				.getInstance();
		assertNotNull(
				"Communication factory class getInstance method is not intialized properly",
				commService);
	}

	@Test
	public void testSingletonGetInstance() {
		ICommunicationService commServiceInstance1 = CommunicationServiceFactory
				.getInstance();
		ICommunicationService commServiceInstance2 = CommunicationServiceFactory
				.getInstance();

		assertEquals(
				"Singleton functionality of Communication service factory is not working properly",
				commServiceInstance1, commServiceInstance2);
	}
}
