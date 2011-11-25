/**
 * 
 */
package edu.ncsu.csc573.project.controllayer;

import edu.ncsu.csc573.project.viewlayer.IViewLayer;

/**
 * This interface provides flow control for peer/BS view layer.
 * 
 * @author doogle-dev
 *
 */
public interface IController{
	
    /**
     * This method allows different views to register with control layer.
     * For eg: cli, gui 
     * @param view view layer
     */
	public void registerViewLayer(IViewLayer view);
	
	/**
	 * This method allows views to detach from contoller.
	 * @param view layer
	 */
	public void removeViewLayer(IViewLayer view);
	
}
