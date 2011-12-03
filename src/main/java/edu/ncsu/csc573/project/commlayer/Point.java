package edu.ncsu.csc573.project.commlayer;

import java.util.Comparator;
import java.util.Random;

import edu.ncsu.csc573.project.common.ByteOperationUtil;
import edu.ncsu.csc573.project.common.ConfigurationManager;

/**
 * This class implements point as a vector
 * 
 * @author doogle-dev
 * 
 */
public class Point implements IPoint, Cloneable, Comparator<Point> {
	private byte[] co_ordinate;

	public Point(String encHach) {
		co_ordinate = ByteOperationUtil.convertStringToBytes(encHach);
	}

	public String getAsString() {
		return ByteOperationUtil.convertBytesToString(co_ordinate);
	}

	public Point(IPoint ip) {
		Point p = (Point) ip;
		co_ordinate = new byte[ConfigurationManager.getInstance()
				.getDimensions()];
		for (int i = 0; i < ConfigurationManager.getInstance().getDimensions(); i++) {
			setIntercept(i, p.getIntercept(i));
		}
	}

	public Point(byte[] p) {
		co_ordinate = p;
	}

	public byte[] getPoint() {
		return co_ordinate;
	}

	public void createPoint(String p) {
		co_ordinate = ByteOperationUtil.getCordinates(p);
	}

	public boolean isPointGreater(IPoint p, int direction) {
		if (p != null && co_ordinate[direction] > p.getIntercept(direction))
			return true;
		else
			return false;
	}

	public boolean isPointGreater(IPoint p) {
		for (int i = 0; i < co_ordinate.length; i++) {
			if (co_ordinate[i] < p.getIntercept(i)) {
				return false;
			}
		}
		return true;
	}
	

	public int getIntercept(int direction) {
		return co_ordinate[direction];
	}

	public String toString() {
		String cordinate = ByteOperationUtil.printCoordinates(co_ordinate);
		return "(" + cordinate.substring(0, cordinate.length() - 1) + ")";
	}

	public void setIntercept(int direction, int value) {
		co_ordinate[direction] = (byte) value;
	}

	public static IPoint getHashSpaceStartPoint() {
		byte[] coordinates = new byte[ConfigurationManager.getInstance()
				.getDimensions()];
		for (int i = 0; i < ConfigurationManager.getInstance().getDimensions(); i++) {
			coordinates[i] = 0;
		}
		return new Point(coordinates);
	}

	public static IPoint getHashSpaceEndPoint() {
		byte[] coordinates = new byte[ConfigurationManager.getInstance()
				.getDimensions()];
		byte maxValue = (byte) (1024 / ConfigurationManager.getInstance()
				.getDimensions());
		for (int i = 0; i < ConfigurationManager.getInstance().getDimensions(); i++) {
			coordinates[i] = maxValue;
		}
		return new Point(coordinates);
	}

	public static IPoint generateRandomPoint() {
		byte[] p = new byte[ConfigurationManager.getInstance().getDimensions()];
		Random randInt = new Random();
		int limit = (int) 1024
				/ ConfigurationManager.getInstance().getDimensions();

		for (int i = 0; i < ConfigurationManager.getInstance().getDimensions(); i++)
			p[i] = (byte) randInt.nextInt(limit);

		return new Point(p);
	}

	public int compare(Point o1, Point o2) {
		for (int i = 0; i < ConfigurationManager.getInstance().getDimensions(); i++) {
			if (o1.getIntercept(i) < o2.getIntercept(i)) {
				return -1;
			} else if(o1.getIntercept(i) == o2.getIntercept(i)) {
				continue;
			} else {
				return 1;
			}
		}
		return 0;
	}
	
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Point){
			Point other = (Point)obj;
			if(this.compare(this, other) == 0){
				return true;
			} 
		}
		return false;
	}
}
