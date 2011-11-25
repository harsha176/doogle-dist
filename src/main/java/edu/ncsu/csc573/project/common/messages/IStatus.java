package edu.ncsu.csc573.project.common.messages;

import java.math.BigInteger;

/**
 * This interface abstracts Status component in Response.
 * @author doogle-dev
 *
 */
public interface IStatus {
	/**
	 * returns status of the response. true if successfull or else false.
	 * @return
	 
	public boolean getStatus();
	*/
	/**
	 * return error id if there is Response has an error.
	 * @return
	 */
	public BigInteger getErrorId();
	
}
