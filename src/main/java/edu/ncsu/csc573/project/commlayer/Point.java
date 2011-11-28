package edu.ncsu.csc573.project.commlayer;

import edu.ncsu.csc573.project.common.ByteOperationUtil;
import edu.ncsu.csc573.project.common.ConfigurationManager;

/**
 * This class implements point as a vector
 * 
 * @author doogle-dev
 *
 */
public class Point implements IPoint, Cloneable {
	private byte[] co_ordinate;
	
	public Point(String encHach) {
		co_ordinate = ByteOperationUtil.convertStringToBytes(encHach); 
	}
	
	public Point(IPoint ip) {
		Point p = (Point) ip;
		co_ordinate = new byte[ConfigurationManager.getInstance().getDimensions()];
		for(int i = 0; i < ConfigurationManager.getInstance().getDimensions(); i++) {
			setIntercept(i, p.getIntercept(i));
		}
	}
	public Point(byte[] p) {
		co_ordinate = p;
	}
	
	public byte[] getPoint(){
		return co_ordinate;
	}

	public void createPoint(String p) {
		co_ordinate = ByteOperationUtil.getCordinates(p);
	}

	public boolean isPointGreater(IPoint p, int direction) {
		if( p!= null && co_ordinate[direction] > p.getIntercept(direction))
			return true;
		else 
			return false;
	}
	
	public int getIntercept(int direction) {
		return co_ordinate[direction];
	}
	
	public String toString() {
		return ByteOperationUtil.convertBytesToString(co_ordinate);
	}

	public void setIntercept(int direction, int value) {
		co_ordinate[direction] = (byte) value;
	}
}
