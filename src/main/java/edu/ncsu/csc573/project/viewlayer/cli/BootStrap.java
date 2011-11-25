/**
 * 
 */
package edu.ncsu.csc573.project.viewlayer.cli;

import edu.ncsu.csc573.project.commlayer.CommunicationServiceFactory;
import edu.ncsu.csc573.project.commlayer.ICommunicationService;
import edu.ncsu.csc573.project.common.EncDecUtil;

/**
 * @author doogle-dev
 *
 */
public class BootStrap {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		/**
		 * Initialize user database file if it does'nt exist.
		 * Load users into hashmap initialize from user database file
		 * make it singleton class.
		 */
		EncDecUtil.initialize();
		ICommunicationService service = CommunicationServiceFactory.getInstance();
		service.initialize("localhost", null);
	}
}
