package edu.ncsu.csc573.project.controllayer;

import java.math.BigInteger;

import org.apache.log4j.Logger;
import edu.ncsu.csc573.project.commlayer.CommunicationServiceFactory;
import edu.ncsu.csc573.project.commlayer.Point;
import edu.ncsu.csc573.project.commlayer.Router;
import edu.ncsu.csc573.project.commlayer.Zone;
import edu.ncsu.csc573.project.common.ConfigurationManager;
import edu.ncsu.csc573.project.common.messages.EnumOperationType;
import edu.ncsu.csc573.project.common.messages.EnumParamsType;
import edu.ncsu.csc573.project.common.messages.IParameter;
import edu.ncsu.csc573.project.common.messages.IRequest;
import edu.ncsu.csc573.project.common.messages.IResponse;
import edu.ncsu.csc573.project.common.messages.JoinRequest;
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
			break;
		case LOGINRESPONSE:
			/*
			 * send join request
			 */
			String joinPeerIP = response.getParameter()
			.getParamValue(EnumParamsType.MESSAGE).toString();
                        logger.debug("Join peer ip is : " + joinPeerIP);
			if (joinPeerIP.equalsIgnoreCase("0.0.0.0")) {
				logger.info("I am the first peeer");
				RequestProcessor
						.getInstance()
						.getMyZone()
						.create(Point.getHashSpaceStartPoint(),
								Point.getHashSpaceEndPoint());
				logger.info("My zone detais "
						+ RequestProcessor.getInstance().getMyZone()
								.toString());
			} else {
				// connect to join peer
				//CommunicationServiceFactory.getInstance().initialize(
						joinPeerIP.trim(); //, null);
				// send join request
				IRequest joinRequest = new JoinRequest();
				IParameter params = new edu.ncsu.csc573.project.common.messages.Parameter();
				params.add(EnumParamsType.IPADDRESS,
						ConfigurationManager.getInstance()
								.getHostInterface());
				params.add(EnumParamsType.PEERID,
						Point.generateRandomPoint().getAsString());
				joinRequest.createRequest(EnumOperationType.JOIN,
						params);
                try {
					logger.info("Join request: " + joinRequest.getRequestInXML());
				} catch (Exception e1) {
					logger.error("Failed to create join request " + joinRequest);
				}
                                                
				try {
					response = CommunicationServiceFactory.getInstance()
							.executeRequest(joinRequest, joinPeerIP);
					validateResponse(response);
				} catch (Exception e) {
					logger.error("Failed to send join request");
				}
			}
			break; 
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
	
	private String validateResponse(IResponse response) throws Exception {
		if ((response.getStatus().getErrorId().intValue() != BigInteger.ZERO.intValue())) {
			throw new Exception(response.getMessage());
		}
		return response.getMessage();
	}
}
