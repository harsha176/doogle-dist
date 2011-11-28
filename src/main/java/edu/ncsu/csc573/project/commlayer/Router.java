package edu.ncsu.csc573.project.commlayer;

import java.net.InetAddress;

import org.apache.log4j.Logger;

import edu.ncsu.csc573.project.common.ByteOperationUtil;
import edu.ncsu.csc573.project.common.ConfigurationManager;

/**
 * 
 * ------------------------------------------- Direction Co-ordinates NextHop IP
 * -------------------------------------------
 * 
 * @author doogle-dev
 * 
 */
public class Router implements IRouter {
	String[][] routingTable = new String[ConfigurationManager.getInstance()
			.getDimensions()][3];
	IZone zone;
	Logger logger;

	public Router() {
		logger = Logger.getLogger(Router.class);
		for (int i = 0; i < ConfigurationManager.getInstance().getDimensions(); i++) {
			routingTable[i] = new String[3];
			routingTable[i][0] = "" + i;
			routingTable[i][1] = null;
			routingTable[i][2] = null;
		}
	}

	public String[] getRoute(int direction) {
		return routingTable[direction];
	}

	public void setRoute(String[] route) {
		routingTable[Integer.parseInt(route[0])] = route;
	}

	public void update(int direction, IPoint p, String add) {
		routingTable[direction][1] = p.toString();
		routingTable[direction][2] = add;
		logger.info("Router point co-ordinates in direction : "
				+ direction
				+ " is : "
				+ ByteOperationUtil.printCoordinates(ByteOperationUtil
						.convertStringToBytes(p.toString())));
	}

	public String getNextHop(IPoint destPoint) {
		// check if it is with in its zone
		/*
		 * if(zone.isPointPresent(destPoint)) { return null; }
		 */

		logger.info("Destination point co-ordinates : "
				+ ByteOperationUtil.printCoordinates(ByteOperationUtil
						.convertStringToBytes(destPoint.toString())));
		for (int i = 0; i < ConfigurationManager.getInstance().getDimensions(); i++) {
			IPoint currPoint = new Point(routingTable[i][1]);
			if (currPoint.isPointGreater(destPoint, i)) {
				return routingTable[i][2];
			}
		}

		return null;
	}

	public String[][] getRoutingTable() {
		return routingTable;
	}

}
