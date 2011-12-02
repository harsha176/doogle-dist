package edu.ncsu.csc573.project.controllayer;

import org.apache.log4j.Logger;

import edu.ncsu.csc573.project.commlayer.Point;
import edu.ncsu.csc573.project.commlayer.Router;
import edu.ncsu.csc573.project.commlayer.Zone;
import edu.ncsu.csc573.project.common.messages.IResponse;
import edu.ncsu.csc573.project.common.messages.JoinResponse;
import edu.ncsu.csc573.project.controllayer.hashspacemanagement.HashSpaceManagerFactory;
import edu.ncsu.csc573.project.controllayer.hashspacemanagement.IHashSpaceManager;

public class ResponseProcessor {
	private Logger logger = Logger.getLogger(ResponseProcessor.class);
	private static ResponseProcessor instance = null;
	private Zone myZone;
	private IHashSpaceManager hashSpaceManager;
	
	private ResponseProcessor() {
		myZone = RequestProcessor.getInstance().getMyZone();
		hashSpaceManager = HashSpaceManagerFactory.getInstance();
	}
	
	public static ResponseProcessor getInstance() {
		if(instance == null) {
			instance = new ResponseProcessor();
		}
		return instance;
	}
	
	public void processResponse(IResponse response, String remoteIP) {
		logger.info("Processing response");
		switch(response.getOperationType()) {
		case REGISTERRESPONSE :
		case LOGINRESPONSE:
		case LOGOUTRESPONSE:
		case FORGOTPASSWORDRESPONSE:
		case CHANGEPASSWORDRESPONSE:
		case ACKRESPONSE:
			break;
		case JOINRESPONSE:
			logger.info("Processing join response");
			JoinResponse joinresp = (JoinResponse)response;
			
			// set zone limits
			myZone.setStart(new Point(joinresp.getFirsthash()));
			myZone.setEnd(new Point(joinresp.getLasthash()));
			
			// update routing table
			Router.getInstance().setRoutingTable(joinresp.getTable());
			
			// update its repository
			hashSpaceManager.handlePublishRequest(joinresp.getFile());
			
			break;
		case SEARCHRESPONSE:
			/*
			 * handle search response
			 */
		case PUTRESPONSE:
			/*
			 * handle put response
			 */
		case GETRESPONSE:
			/*
			 * Handle get response
			 */
			break;
		default :
				logger.error("Invalid response  " +  response.getOperationType());	
		}
		logger.info("Done processing response");
	}
}
