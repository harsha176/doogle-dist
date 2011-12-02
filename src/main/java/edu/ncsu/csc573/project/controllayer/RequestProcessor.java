package edu.ncsu.csc573.project.controllayer;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import edu.ncsu.csc573.project.commlayer.IPoint;
import edu.ncsu.csc573.project.commlayer.IZone;
import edu.ncsu.csc573.project.commlayer.MessageDetails;
import edu.ncsu.csc573.project.commlayer.Router;
import edu.ncsu.csc573.project.commlayer.Zone;
import edu.ncsu.csc573.project.common.ConfigurationManager;
import edu.ncsu.csc573.project.common.messages.ChangePasswordResponseMessage;
import edu.ncsu.csc573.project.common.messages.EnumOperationType;
import edu.ncsu.csc573.project.common.messages.EnumParamsType;
import edu.ncsu.csc573.project.common.messages.ForgotPWDResponseMessage;
import edu.ncsu.csc573.project.common.messages.GetResponse;
import edu.ncsu.csc573.project.common.messages.IParameter;
import edu.ncsu.csc573.project.common.messages.IRequest;
import edu.ncsu.csc573.project.common.messages.IResponse;
import edu.ncsu.csc573.project.common.messages.InvalidResponseMessage;
import edu.ncsu.csc573.project.common.messages.JoinResponse;
import edu.ncsu.csc573.project.common.messages.LeaveResponse;
import edu.ncsu.csc573.project.common.messages.LoginResponseMessage;
import edu.ncsu.csc573.project.common.messages.LogoutResponseMessage;
import edu.ncsu.csc573.project.common.messages.Parameter;
import edu.ncsu.csc573.project.common.messages.PublishRequestMessage;
import edu.ncsu.csc573.project.common.messages.PublishResponseMessage;
import edu.ncsu.csc573.project.common.messages.PutRequest;
import edu.ncsu.csc573.project.common.messages.PutResponse;
import edu.ncsu.csc573.project.common.messages.RegisterResponseMessage;
import edu.ncsu.csc573.project.common.messages.SearchResponseMessage;
import edu.ncsu.csc573.project.common.schema.FileParamType;
import edu.ncsu.csc573.project.common.schema.TableParamType;
import edu.ncsu.csc573.project.controllayer.hashspacemanagement.HashSpaceManager;
import edu.ncsu.csc573.project.controllayer.hashspacemanagement.HashSpaceManagerFactory;
import edu.ncsu.csc573.project.controllayer.hashspacemanagement.Query;
import edu.ncsu.csc573.project.controllayer.usermanagement.IUsersManager;
import edu.ncsu.csc573.project.controllayer.usermanagement.User;
import edu.ncsu.csc573.project.controllayer.usermanagement.UserManagementException;

public class RequestProcessor {

	private Logger logger;
	private IUsersManager usermanager;
	private HashSpaceManager hashSpaceManager;
	private IFilter adminFilter;
	private static Zone myZone;
	private ArrayList<String> peers;
	private String destPeerIP;
	private static RequestProcessor instance = null;

	private RequestProcessor() {
		try {
			usermanager = IUsersManager.getInstance();
			hashSpaceManager = (HashSpaceManager)HashSpaceManagerFactory.getInstance();
			adminFilter = new AdminServerFilter();
			myZone = new Zone();
			peers = new ArrayList<String>();
		} catch (Exception e) {
			logger.error("Unable to initialize UserManager module", e);
		}
	}

	public static synchronized RequestProcessor getInstance() {
		if (instance == null) {
			instance = new RequestProcessor();
		}
		return instance;
	}

	public synchronized MessageDetails processRequest(IRequest req, String peerIp) {
		logger = Logger.getLogger(RequestProcessor.class);
		IResponse response = null;
		IParameter params = null;

		/*
		 * Check if its a valid request for this node
		 */
		
		 if (!adminFilter.isRequestValid(req.getOperationType())) { response =
				 new InvalidResponseMessage(req.getId(), req.getOperationType() +
						 "is not a requested operation "); 
		 return new MessageDetails(peerIp, response); 
		 }
		
		/*
		 * if(ConfigurationManager.getInstance().isAdminServer()) { myZone = new
		 * Zone(); myZone.create(Point.getHashSpaceStartPoint(),
		 * Point.getHashSpaceEndPoint()); }
		 */

		// Create zone
		// sample responses
		switch (req.getOperationType()) {
		case REGISTER:
			/*
			 * call usermanager
			 */
			User newUser = new User();
			newUser.setUsername(req.getParameter()
					.getParamValue(EnumParamsType.USERNAME).toString());
			newUser.setPassword(req.getParameter()
					.getParamValue(EnumParamsType.PASSWORD).toString());
			newUser.setFirstName(req.getParameter()
					.getParamValue(EnumParamsType.FIRSTNAME).toString());
			newUser.setLastName(req.getParameter()
					.getParamValue(EnumParamsType.LASTNAME).toString());
			newUser.setEmailID(req.getParameter()
					.getParamValue(EnumParamsType.EMAIL_ID).toString());

			try {
				response = new RegisterResponseMessage(req.getId());
				response.setId(req.getId());
				params = new Parameter();
				usermanager.addUser(newUser);
				params.add(EnumParamsType.STATUSCODE,
						new BigInteger(String.valueOf(0)));
				params.add(EnumParamsType.MESSAGE, req.getParameter()
						.getParamValue(EnumParamsType.USERNAME)
						+ " successfully registered");
			} catch (UserManagementException e1) {
				params.add(EnumParamsType.STATUSCODE,
						new BigInteger(String.valueOf(e1.getStatus())));
				params.add(EnumParamsType.MESSAGE, e1.getMessage());
			}
			response.createResponse(EnumOperationType.REGISTERRESPONSE, params);
			destPeerIP = peerIp;
			break;
		case LOGIN:
			response = new LoginResponseMessage(req.getId());
			params = new Parameter();
			String joinIp;

			try {
				usermanager.userLogin(
						req.getParameter()
								.getParamValue(EnumParamsType.USERNAME)
								.toString(),
						req.getParameter()
								.getParamValue(EnumParamsType.PASSWORD)
								.toString());
				params.add(EnumParamsType.STATUSCODE,
						new BigInteger(String.valueOf(0)));
				logger.debug("Choosing join peer");
				/*
				 * Check if peers exits
				 */
				if (peers.isEmpty()) {
					logger.debug("This is the first peer joining network");
					joinIp = "0.0.0.0";
				} else {
					/*
					 * choose random peer
					 */
					Random r = new Random();
					joinIp = peers.get(r.nextInt(peers.size()));
					logger.debug("Selected peer is " + joinIp);
				}
				/*
				 * adding peer to the list
				 */
				peers.add(peerIp);
				params.add(EnumParamsType.MESSAGE, joinIp);

			} catch (UserManagementException e1) {
				params.add(EnumParamsType.STATUSCODE,
						new BigInteger(String.valueOf(e1.getStatus())));
				params.add(EnumParamsType.MESSAGE, e1.getMessage());
			}
			response.createResponse(EnumOperationType.LOGINRESPONSE, params);
			destPeerIP = peerIp;
			break;
		case LOGOUT:
			response = new LogoutResponseMessage(req.getId());
			params = new Parameter();
			try {
				usermanager.userLogout(req.getParameter()
						.getParamValue(EnumParamsType.USERNAME).toString());
				params.add(EnumParamsType.STATUSCODE,
						new BigInteger(String.valueOf(0)));
				peers.remove(peerIp);
			} catch (UserManagementException e1) {
				params.add(EnumParamsType.STATUSCODE,
						new BigInteger(String.valueOf(e1.getStatus())));
				params.add(EnumParamsType.MESSAGE, e1.getMessage());

			}
			response.createResponse(EnumOperationType.LOGOUTRESPONSE, params);
			destPeerIP = peerIp;
			break;
		case CHANGEPASSWORD:
			response = new ChangePasswordResponseMessage(req.getId());
			params = new Parameter();

			try {
				usermanager.changePassword(
						req.getParameter()
								.getParamValue(EnumParamsType.USERNAME)
								.toString(),
						req.getParameter()
								.getParamValue(EnumParamsType.PASSWORD)
								.toString(),
						req.getParameter()
								.getParamValue(EnumParamsType.NEWPASSWORD)
								.toString());

				params.add(EnumParamsType.STATUSCODE,
						new BigInteger(String.valueOf(0)));
				params.add(EnumParamsType.MESSAGE,
						"Password sent to your mail account");
			} catch (UserManagementException e1) {

				params.add(EnumParamsType.STATUSCODE,
						new BigInteger(String.valueOf(e1.getStatus())));
				params.add(EnumParamsType.MESSAGE, e1.getMessage());
			}
			response.createResponse(EnumOperationType.FORGOTPASSWORD, params);
			destPeerIP = peerIp;
			break;
		case FORGOTPASSWORD:
			response = new ForgotPWDResponseMessage(req.getId());
			params = new Parameter();

			try {
				usermanager.forgotPassword(req.getParameter()
						.getParamValue(EnumParamsType.USERNAME).toString());

				params.add(EnumParamsType.STATUSCODE,
						new BigInteger(String.valueOf(0)));
				params.add(EnumParamsType.MESSAGE,
						"Password successfully updated");
			} catch (UserManagementException e1) {

				params.add(EnumParamsType.STATUSCODE,
						new BigInteger(String.valueOf(e1.getStatus())));
				params.add(EnumParamsType.MESSAGE, e1.getMessage());
			}
			response.createResponse(EnumOperationType.FORGOTPASSWORD, params);
			destPeerIP = peerIp;
			break;
		case PUBLISH:
			logger.debug("Processing publish request");
			hashSpaceManager.handlePublishRequest((PublishRequestMessage) req);
			response = new PublishResponseMessage(req.getId());
			params = new Parameter();
			params.add(EnumParamsType.STATUSCODE,
					new BigInteger(String.valueOf(0)));
			params.add(EnumParamsType.MESSAGE,
					"Successfully published folder on server");
			response.createResponse(EnumOperationType.PUBLISHRESPONSE, params);
			/*
			 * TODO choose destination ip and return it inform of messageDetail object 
			 */
			destPeerIP = peerIp;
			break;
		case SEARCH:
			logger.debug("Processing search request");
			response = new SearchResponseMessage(req.getId());
			IParameter searchResponseparams;
			String query_string = req.getParameter()
					.getParamValue(EnumParamsType.SEARCHKEY).toString();
			searchResponseparams = hashSpaceManager.search(new Query(
					query_string));
			response.createResponse(EnumOperationType.SEARCHRESPONSE,
					searchResponseparams);

			response.createResponse(EnumOperationType.SEARCHRESPONSE,
					searchResponseparams);
			/*
			 * TODO choose destination ip and return it inform of messageDetail object 
			 */
			destPeerIP = peerIp;
			break;
		case JOIN:
			logger.debug("Processing join request");
			int count = 0;
			response = new JoinResponse(req.getId());
			IZone child = myZone.split(count);
			
			logger.info("Created new child zone : " + child.toString());
			JoinResponse joinResponse = new JoinResponse(req.getId());
			
			// send first, last hash and peer id
			joinResponse.setFirsthash(child.getStart().getAsString());
			joinResponse.setLasthash(child.getEnd().getAsString());
			joinResponse.setPeerid(child.getStart().getAsString());
			joinResponse.setMyipaddress(ConfigurationManager.getInstance().getHostInterface());
			// send routing table
			List<TableParamType> routingTableAsList = Router.getInstance().getRoutingTableAsList();
			joinResponse.getTable().addAll(routingTableAsList);
			logger.info("Published routing table");
			for(TableParamType table:routingTableAsList) {
				logger.info(table.getDirection() + ":" + table.getNexthop() + ":" + table.getPeerid());
			}
			
			List<FileParamType> allFileDetailsAsList = hashSpaceManager.getAllFileDetailsAsList(child);
			joinResponse.getFile().addAll(allFileDetailsAsList);
			for(FileParamType table:allFileDetailsAsList) {
				logger.info(table.getFilename() + ":" + table.getIpaddress());
			}
			
			// Send routing table and file entries
			// response.createResponse(EnumOperationType.JOINRESPONSE,
			// joinResponseparams);
			
			IParameter joinParams = new Parameter();
			joinParams.add(EnumParamsType.STATUSCODE, new BigInteger(String.valueOf(1)));
			joinParams.add(EnumParamsType.MESSAGE, "Successfully executed request");
			joinResponse.createResponse(EnumOperationType.JOINRESPONSE, joinParams);
			
			response = joinResponse;
			try {
				logger.info("Successfull created response " + response.getRequestInXML());
			} catch (Exception e1) {
				logger.error("Failed to create join response", e1);
			}
			// update routing table in that direction
			Router.getInstance().update(count, child.getStart(), req.getParameter().getParamValue(EnumParamsType.IPADDRESS).toString());
			count++;
			count = count % 15;
			
			/*
			 * TODO choose destination ip and return it inform of messageDetail object 
			 */
			destPeerIP = peerIp;
			break;
		case LEAVE:
			logger.debug("Processing put request");
			// Update routing table of parent ZONE
			int counter = 0;
			Zone mer = new Zone();
			mer.create(
					(IPoint) req.getParameter().getParamValue(
							EnumParamsType.FIRSTHASH),
					(IPoint) req.getParameter().getParamValue(
							EnumParamsType.LASTHASH));
			// Parent zone
			myZone.mergeZone(mer, counter);
			response = new LeaveResponse(req.getId());
			params = new Parameter();
			params.add(EnumParamsType.STATUSCODE,
					new BigInteger(String.valueOf(0)));
			params.add(EnumParamsType.MESSAGE, "Leave Request Successful");
			response.createResponse(EnumOperationType.PUTRESPONSE, params);
			counter++;
			counter = counter % 15;
			/*
			 * TODO choose destination ip and return it inform of messageDetail object 
			 */
			destPeerIP = peerIp;
			break;
		case PUT:
			logger.debug("Processing put request");
			hashSpaceManager.handlePutRequest((PutRequest) req);
			response = new PutResponse(req.getId());
			params = new Parameter();
			params.add(EnumParamsType.STATUSCODE,
					new BigInteger(String.valueOf(0)));
			params.add(EnumParamsType.MESSAGE,
					"Successfully published file on peer");
			response.createResponse(EnumOperationType.PUTRESPONSE, params);
			/*
			 * TODO choose destination ip and return it inform of messageDetail object 
			 */
			destPeerIP = peerIp;
			break;
		case GET:
			logger.debug("Processing get request");
			response = new GetResponse(req.getId());
			IParameter getResponseparams;
			String query_string_get = req.getParameter()
					.getParamValue(EnumParamsType.SEARCHKEY).toString();
			getResponseparams = hashSpaceManager.search(new Query(
					query_string_get));
			response.createResponse(EnumOperationType.GETRESPONSE,
					getResponseparams);
			/*
			 * TODO choose destination ip and return it inform of messageDetail object 
			 */
			destPeerIP = peerIp;
			break;
		default:
			try {
				logger.error("Invalid request " + req.getRequestInXML());
			} catch (Exception e) {
				logger.error(" Invalid request: unable to create xml ");
			}
		}
		logger.error("sending it to default peer");
		//response.setId(req.getId());
		return new MessageDetails(peerIp, response);
	}

	// public void
	public synchronized Zone getMyZone() {
		if (myZone == null) {
			myZone = new Zone();
		}
		return myZone;
	}
}
