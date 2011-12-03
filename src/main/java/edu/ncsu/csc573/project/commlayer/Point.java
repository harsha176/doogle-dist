package edu.ncsu.csc573.project.commlayer;

import java.util.Comparator;
import java.util.Random;
import edu.ncsu.csc573.project.common.ConfigurationManager;

/**
 * This class implements point as a vector
 * 
 * @author doogle-dev
 * 
 */
public class Point implements IPoint, Cloneable, Comparator<Point>, Comparable<Point> {
	private int[] co_ordinate;

	public Point(String encHach) {
		if(encHach != null) {
			String[] intercepts =  encHach.split(":");
			int i = 0;
			for(String intercept: intercepts) {
				co_ordinate[i] = Integer.parseInt(intercept);
			}
		}
		//co_ordinate = ByteOperationUtil.convertStringToBytes(encHach);
	}

	public String getAsString() {
		StringBuilder sb = new StringBuilder();
		for(int intercept: co_ordinate) {
			sb.append(intercept);
			sb.append(":");
		}
		return sb.substring(0, sb.length()-1);
	}

	public Point(IPoint ip) {
		Point p = (Point) ip;
		co_ordinate = new int[ConfigurationManager.getInstance()
				.getDimensions()];
		for (int i = 0; i < ConfigurationManager.getInstance().getDimensions(); i++) {
			setIntercept(i, p.getIntercept(i));
		}
	}

	public Point(int[] p) {
		co_ordinate = p;
	}

	public int[] getPoint() {
		return co_ordinate;
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
		StringBuilder sb  = new StringBuilder();
		sb.append("(");
		for(int intercept : co_ordinate) {
			sb.append(intercept);
			sb.append(",");
		}
		
		return sb.substring(0, sb.length() - 1) + ")";
	}

	public void setIntercept(int direction, int value) {
		co_ordinate[direction] = (int) value;
	}

	public static IPoint getHashSpaceStartPoint() {
		int[] coordinates = new int[ConfigurationManager.getInstance()
				.getDimensions()];
		for (int i = 0; i < ConfigurationManager.getInstance().getDimensions(); i++) {
			coordinates[i] = 0;
		}
		return new Point(coordinates);
	}

	public static IPoint getHashSpaceEndPoint() {
		int[] coordinates = new int[ConfigurationManager.getInstance()
				.getDimensions()];
		int maxValue = (int) ((1024*8 / ConfigurationManager.getInstance()
				.getDimensions())); 
		for (int i = 0; i < ConfigurationManager.getInstance().getDimensions(); i++) {
			coordinates[i] = maxValue;
		}
		return new Point(coordinates);
	}

	public static IPoint generateRandomPoint() {
		int[] p = new int[ConfigurationManager.getInstance().getDimensions()];
		Random randInt = new Random();
		int limit = (int) 1024
				/ ConfigurationManager.getInstance().getDimensions();

		for (int i = 0; i < ConfigurationManager.getInstance().getDimensions(); i++)
			p[i] = (int) randInt.nextInt(limit);

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

	public int compareTo(Point o) {
		return compare(this, o);
	}
}
