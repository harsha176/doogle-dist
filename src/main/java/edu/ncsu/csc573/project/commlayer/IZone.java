package edu.ncsu.csc573.project.commlayer;

public interface IZone {
	/**
	 * This method initializes the zone with start and end points. It includes
	 * all the points within this limits
	 * 
	 * @param start
	 * @param end
	 */
	public void create(IPoint start, IPoint end);

	/**
	 * This method is used to split the zone into half along the plane
	 * perpendicular to the direction
	 * 
	 * @param dir 
	 * @return
	 */
	public IZone split(int dir);

	/**
	 * This method checks if the a point is within its zone.
	 * 
	 * @param p
	 * @return
	 */
	public boolean isPointPresent(IPoint p);

	/**
	 * This method is used to merge leaving zone place.
	 * 
	 * @param leavingZone
	 * @param dir
	 */
	public void mergeZone(IZone leavingZone, int dir);
	
	public IPoint getStart() ;
	
	public IPoint getEnd() ;
}
