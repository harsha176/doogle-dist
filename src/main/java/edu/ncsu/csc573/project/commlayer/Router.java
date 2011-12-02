package edu.ncsu.csc573.project.commlayer;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import edu.ncsu.csc573.project.common.ByteOperationUtil;
import edu.ncsu.csc573.project.common.ConfigurationManager;
import edu.ncsu.csc573.project.common.schema.TableParamType;

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
	private static Router instance = null;
	private boolean isInitialized = false;
	
	private Router() {
		logger = Logger.getLogger(Router.class);
		for (int i = 0; i < ConfigurationManager.getInstance().getDimensions(); i++) {
			routingTable[i] = new String[3];
			routingTable[i][0] = "" + i;
			routingTable[i][1] = null;
			routingTable[i][2] = null;
		}
	}

	public static Router getInstance() {
		if(instance == null) {
			instance  = new Router();
		}
		return instance;
	}
	
	public String[] getRoute(int direction) {
		return routingTable[direction];
	}

	public void setRoute(String[] route) {
		routingTable[Integer.parseInt(route[0])] = route;
		isInitialized = true;
	}

	public void update(int direction, IPoint p, String add) {
		routingTable[direction][1] = p.toString();
		routingTable[direction][2] = add;
		logger.info("Router point co-ordinates in direction : "
				+ direction
				+ " is : "
				+ ByteOperationUtil.printCoordinates(ByteOperationUtil
						.convertStringToBytes(p.getAsString())));
		isInitialized = true;
	}

	public String getNextHop(IPoint destPoint) {
		// check if it is with in its zone
		/*
		 * if(zone.isPointPresent(destPoint)) { return null; }
		 */

		logger.info("Destination point co-ordinates : "
				+ ByteOperationUtil.printCoordinates(ByteOperationUtil
						.convertStringToBytes(destPoint.getAsString())));
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

	public List<TableParamType> getRoutingTableAsList() {
		List<TableParamType> list = new ArrayList<TableParamType>();
		for(int i = 0; i < ConfigurationManager.getInstance().getDimensions(); i++) {
			TableParamType tp = new TableParamType();
			tp.setDirection(i);
			tp.setPeerid(routingTable[i][1]);
			tp.setNexthop(routingTable[i][2]);
			list.add(tp);
		}
		return list;
	}
	
	public void setRoutingTable(List<TableParamType> list) {
		for(TableParamType tpt : list) {
			int dir = tpt.getDirection();
			routingTable[dir][0] = String.valueOf(dir);
			routingTable[dir][1] = tpt.getPeerid();
			routingTable[dir][2] = tpt.getNexthop();
		}
		isInitialized = true;
	}
	
	public boolean isInitialized() {
		return isInitialized;
	}
}
