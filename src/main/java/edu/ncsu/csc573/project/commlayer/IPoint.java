package edu.ncsu.csc573.project.commlayer;

public interface IPoint {
	public void createPoint(String p);
	
	public boolean isPointGreater(IPoint p, int direction);
	
	public int getIntercept(int direction);
	
	public void setIntercept(int direction, int value);
}
