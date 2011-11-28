package edu.ncsu.csc573.project.controllayer.hashspacemanagement;

import edu.ncsu.csc573.project.common.messages.PublishRequestMessage;
import edu.ncsu.csc573.project.common.messages.PublishSearchParameter;
import edu.ncsu.csc573.project.common.messages.PutRequest;

/**
 * This acts as an interface for control layer to communicate with
 * HashSpaceManager.
 * 
 * @author doogle-dev
 * 
 */
public interface IHashSpaceManager {
	/**
	 * This method retrieves the search results for the given query.
	 * 
	 * @param query
	 * @return
	 */
	public PublishSearchParameter search(IQuery query);

	/**
	 * This method is called every time a publish request message is received from
	 * the server.
	 * 
	 * @param pubRequest
	 */
	public void handlePublishRequest(PublishRequestMessage pubRequest);
        /**
	 * This method is called every time a put request message is received from
	 * one of the peers.
	 * 
	 * @param putRequest
	 */
        public void handlePutRequest(PutRequest putRequest);
}
