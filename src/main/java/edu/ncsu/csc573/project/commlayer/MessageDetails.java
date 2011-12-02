package edu.ncsu.csc573.project.commlayer;

import edu.ncsu.csc573.project.common.messages.IRequest;

public class MessageDetails {
	String IPAddress;
	IRequest request;
	
	public MessageDetails(String ip, IRequest req) {
		IPAddress = ip;
		request = req;
	}
	public String getIPAddress() {
		return IPAddress;
	}
	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
	}
	public void setRequest(IRequest request) {
		this.request = request;
	}
	public IRequest getRequest() {
		return request;
	}
}
