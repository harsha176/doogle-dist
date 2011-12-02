/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncsu.csc573.project.common.messages;

/**
 *
 * @author krishna
 */
public class LeaveRequest extends RequestMessage{

	
	private String id;

	public String getRequestInXML() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void parseXML(String XML) {
		// TODO Auto-generated method stub
		
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = String.valueOf(id);
	}
	public LeaveRequest() {
		id = ""+System.currentTimeMillis();
	}
}
