/**
 * 
 */
package edu.ncsu.csc573.project.controllayer;

import java.util.concurrent.ConcurrentHashMap;
import org.apache.log4j.Logger;
import edu.ncsu.csc573.project.common.messages.IRequest;
import edu.ncsu.csc573.project.common.messages.IResponse;

/**
 * @author doogle-dev
 *
 */
public class ConcurrentQueueManagement implements IQueueManagement{
	/**
	 * This queue maintains pending requests
	 */
	private ConcurrentHashMap<String, IRequest> pendingRequestsQueue;
	
	/**
	 * This queue maintains ready responses
	 */
	private ConcurrentHashMap<String , IResponse> readyResponseQueue;
	
	private static ConcurrentQueueManagement instance;
	
	private static Logger logger;
	
	private ConcurrentQueueManagement() {
		pendingRequestsQueue = new ConcurrentHashMap<String, IRequest>();
		readyResponseQueue = new ConcurrentHashMap<String, IResponse>();
	}
	
	public static synchronized ConcurrentQueueManagement getInstance() {
		if(instance == null) {
			instance = new ConcurrentQueueManagement();
			logger = Logger.getLogger(ConcurrentQueueManagement.class);
			logger.debug("Request and response queues are initialized");
		}
		return instance;
	}
	
	public synchronized void putRequest(String id, IRequest req) {
		pendingRequestsQueue.put(id, req);
		logger.info("request with id : " +  id + " is added to pending requests queue");
	}

	public boolean isResponseReceived(String id) {
		return !pendingRequestsQueue.containsKey(id);
	}

	public synchronized IResponse getResponse(String id) throws Exception {
		if(readyResponseQueue.containsKey(id)) {
			return readyResponseQueue.remove(id);
		} else {
			logger.error("No response with id : " + id + " is received ");
			throw new Exception("No response is received");
		}
	}

	public synchronized void updateResponse(String id, IResponse response) throws Exception {
		if(pendingRequestsQueue.containsKey(id)) {
			logger.debug("Received response for request id: " + id);
			logger.debug("Response received for " + pendingRequestsQueue.remove(id) + " operation");
			readyResponseQueue.put(id, response);
		} else {
			logger.error("Request with id : " + id + " is not present in the pending request queue");
			throw new Exception("No request is pending with that id");
		}
	}

}
