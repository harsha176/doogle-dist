/**
 * 
 */
package edu.ncsu.csc573.project.common.messages;

/**
 * This interface allows peer to createRequest objects.
 * @author doogle-dev
 *
 */
public interface IRequest {
	/**
	 * This method creates request based on operationType and parameter object.
	 * @param opType operation type of the request to be created.
	 * @param params List of parameters to be sent along with the request.
	 */
	void createRequest(EnumOperationType opType, IParameter parameter);
	
	/**
	 * This method retrieves operation type of the the request.
	 * 
	 * @return operation type
	 */
	EnumOperationType getOperationType();
	
	/**
	 * This method retrieves the parameters object configured for the request.
	 */
	IParameter getParameter();
	
	/**
	 * This method allows user to get configured request in XML.
	 *  
	 * @return
	 * @throws Exception 
	 */
	public String getRequestInXML() throws Exception;
	
	/**
	 * This method parses XML and creates a request object out of it. 
	 */
	public void parseXML(String XML);
}