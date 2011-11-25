package edu.ncsu.csc573.project.viewlayer;

import edu.ncsu.csc573.project.common.messages.IResponse;

/**
 * This acts as an interface for view layer. Controllayer assumes each view
 * implements this interface.
 * 
 * @author doogle-dev
 * 
 */
public interface IViewLayer {
	/**
	 * This method is called by control layer whenever something changes in
	 * control layer.
	 * 
	 * @param response
	 */
	public void update(IResponse response);
}
