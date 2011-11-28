package edu.ncsu.csc573.project.commlayer;

import edu.ncsu.csc573.project.common.ConfigurationManager;

public class Zone implements IZone {
	private IPoint start;
	private IPoint end;
	
	public void create(IPoint start, IPoint end) {
		this.start  = start;
		this.end = end;
	}

	public IZone split(int dir) {
		int k = end.getIntercept(dir);
		k = k/2;
		IPoint newstart = new Point(end);
		IPoint newend = new Point(end);
		end.setIntercept(dir, k);
		newstart.setIntercept(dir, k+1);
		Zone child = new Zone();
		child.create(newstart, newend);
		return child;
	}

	public boolean isPointPresent(IPoint p) {
		for(int i = 0; i < ConfigurationManager.getInstance().getDimensions(); i++) {
			if(p.getIntercept(i) < start.getIntercept(i) || p.getIntercept(i) > end.getIntercept(i)) {
				return false;
			}
		}
		return true;
	}

	public void mergeZone(IZone leavingZone, int dir) {
		for(int i = 0; i < ConfigurationManager.getInstance().getDimensions(); i++) {
			if(i != dir) {
				assert (leavingZone.getStart().getIntercept(i) == end.getIntercept(i)); 
			}
		}
		setEnd(leavingZone.getEnd());
	}
	
	public IPoint getStart() {
		return this.start;
	}
	
	public void setStart(IPoint start) {
		this.start = start;
	}
	
	public IPoint getEnd() {
		return end;
	}
	public void setEnd(IPoint end) {
		this.end = end;
	}
}
