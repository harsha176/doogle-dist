/**
 * 
 */
package edu.ncsu.csc573.project.commlayer;

/**
 * This class is a factory class for configuring various implementations of
 * CommunicationService instance
 * 
 * @author doogle-dev
 * 
 */
public class CommunicationServiceFactory {
	private static ICommunicationService commService = null;

	/**
	 * This method is used to get an instance of ICommunicationService object.
	 * 
	 * @return
	 */
	public static ICommunicationService getInstance() {
		synchronized (CommunicationServiceFactory.class) {
			if (commService == null) {
				commService = new CommunicationService();
			}
			return commService;
		}
	}
}
