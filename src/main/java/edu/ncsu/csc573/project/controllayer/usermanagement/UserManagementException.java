package edu.ncsu.csc573.project.controllayer.usermanagement;

public class UserManagementException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int status;
	//private String message;
	
	public UserManagementException(int status, String message) {
		super(message);
		this.status = status;
		//this.message = message;
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
	public int getStatus() {
		return status;
	}
	
}
