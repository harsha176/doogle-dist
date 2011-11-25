/**
 * 
 */
package edu.ncsu.csc573.project.commlayer;

import edu.ncsu.csc573.project.common.messages.IRequest;
import edu.ncsu.csc573.project.common.messages.IResponse;

/**
 * This interface acts as a handler whenever a response is received for a
 * previously submitted request.
 * 
 * Generally for asynchronous calls an implementation of this interface is
 * passed along with execute request.
 * 
 * @author doogle-dev
 * 
 */
public interface IResponseListener {
	/**
	 * This method is called whenever a response is received for a previously
	 * submitted request.
	 * 
	 * Precondition: executeRequest is called passing this 
	 * @param response
	 *            received from bootstrap server
	 * @param request
	 *            request sent for the above response
	 */
	public void handleResponse(IResponse response, IRequest request);
}
