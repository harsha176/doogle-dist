package edu.ncsu.csc573.project.commlayer;

import org.apache.log4j.Logger;
import edu.ncsu.csc573.project.common.ByteOperationUtil;
import edu.ncsu.csc573.project.common.messages.EnumParamsType;
import edu.ncsu.csc573.project.common.messages.IRequest;
import edu.ncsu.csc573.project.common.messages.IResponse;
import edu.ncsu.csc573.project.common.messages.InvalidResponseMessage;
import edu.ncsu.csc573.project.controllayer.AdminServerFilter;
import edu.ncsu.csc573.project.controllayer.IFilter;
import edu.ncsu.csc573.project.controllayer.RequestProcessor;

public class DistributedCommunicationService extends CommunicationService {
	private IRouter router;
	private IFilter adminfilter;
	private Logger logger;
	private RequestProcessor processor;

	public void initialize(String BootStrapServer,
			IPublishHandler aPublishHandler) throws Exception {
		super.initialize(BootStrapServer, aPublishHandler);
		processor = RequestProcessor.getInstance();
		adminfilter = new AdminServerFilter();
		logger = Logger.getLogger(DistributedCommunicationService.class);
	}

	/**
	 * This method contacts routing table to identify appropriate host.
	 * 
	 * If it is the same host then it sends the request to upper layer
	 * (RequestProcessor to execute the request)
	 */
	public IResponse executeRequest(IRequest request) throws Exception {
		IPoint destPoint = null;
		String destPeerIP;
		IResponse response;

		/*
		 * Check if it is a valid request for the node.
		 */
		if (!adminfilter.isRequestValid(request.getOperationType())) {
			logger.debug("Received invalid request " + request.getOperationType());
			// send it to destination ip
			return new InvalidResponseMessage(
					"The requested operation is not supported by the node "
							+ request.getOperationType());
		}

		if (adminfilter.isAdminServer()) {
			switch (request.getOperationType()) {
			case LOGIN:
			case REGISTER:
			case LOGOUT:
			case CHANGEPASSWORD:
			case FORGOTPASSWORD:
				response = processor.processRequest(request);
				break;
			default:
				response = new InvalidResponseMessage("Invalid request to Bootstrap server");
				logger.error("Wrong request " + request.getRequestInXML());
			}
			return response;
		} else {
			/*
			 * get the destination coordinate in the request
			 */
			switch (request.getOperationType()) {
			case ACKRESPONSE:
				return null;
			case PUT:
				destPoint = new Point(request.getParameter()
						.getParamValue(EnumParamsType.FILEDIGEST).toString());
				break;
			case GET:
				destPoint = new Point(ByteOperationUtil.getCordinates(request
						.getParameter().getParamValue(EnumParamsType.SEARCHKEY)
						.toString()));
				break;
			case LEAVE:
			case JOIN:
				destPoint = new Point(request.getParameter()
						.getParamValue(EnumParamsType.PEERID).toString());
				break;
			default:
				logger.error("Wrong request " + request.getRequestInXML());
			}
		}

		/*
		 * Ask router for next hop.
		 */
		destPeerIP = router.getNextHop(destPoint);
		/*
		 * if it is destined to this peer then send it to upper layers. and send
		 * the response back to source request peer
		 */
		if (destPeerIP == null) {
			response = processor.processRequest(request);
			destPeerIP = request.getParameter()
					.getParamValue(EnumParamsType.IPADDRESS).toString();
		}

		/*
		 * fetch destination peer ip address
		 */
		initializeConnectedSocket(destPeerIP);

		/*
		 * launch thread to send this to destination peer and close
		 */
		response = super.executeRequest(request);
		logger.debug(response.toString());

		return response;
	}
}