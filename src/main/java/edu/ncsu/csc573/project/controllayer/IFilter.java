/**
 * 
 */
package edu.ncsu.csc573.project.controllayer;

import edu.ncsu.csc573.project.common.messages.EnumOperationType;

/**
 * @author doogle-dev
 *
 */
public interface IFilter {
	/**
	 * This method returns if a given operation is valid for the request.
	 * @param opType
	 * @return
	 */
	public boolean isRequestValid(EnumOperationType opType);
	
	public boolean isAdminServer();
}
