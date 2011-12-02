package edu.ncsu.csc573.project.commlayer;


/**
 * This class abstracts the implementation of Router.
 * @author doogle-dev
 *
 */
public interface IRouter {
	
	/**
	 * This method retrieves the next hop given a point.
	 * @param p
	 * @return
	 */
	public String getNextHop(IPoint p);
	
	/**
	 * 
	 * @return
	 */
	public String[][] getRoutingTable();
	
	public boolean isInitialized();
}
