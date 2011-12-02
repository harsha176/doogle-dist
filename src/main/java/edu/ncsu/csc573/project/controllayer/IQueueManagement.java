package edu.ncsu.csc573.project.controllayer;

import edu.ncsu.csc573.project.common.messages.IRequest;
import edu.ncsu.csc573.project.common.messages.IResponse;

public interface IQueueManagement {
	/**
	 * 
	 * @param id
	 * @param req
	 */
	public void putRequest(String id, IRequest req);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean isResponseReceived(String id);
	
	/**
	 * 
	 * @param id
	 * @throws Exception
	 */
	public IResponse getResponse(String id) throws Exception;
	
	
	/**
	 * 
	 * @param id
	 * @param repsonse
	 * @throws Exception
	 */
	public void updateResponse(String id, IResponse repsonse) throws Exception;
}
