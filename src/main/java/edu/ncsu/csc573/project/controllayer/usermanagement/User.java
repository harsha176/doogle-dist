package edu.ncsu.csc573.project.controllayer.usermanagement;

import edu.ncsu.csc573.project.common.EncDecUtil;

enum EnumStatus {
	LOGGED_IN, LOGGED_OUT
}

public class User implements IUser {

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String emailID;
	private String designation;
	private EnumStatus status;
	
	public int getUserID() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean validatePassword(String plainpassword) {
		// decrypt password and check it with given password
		if(EncDecUtil.decryptMessage(password).trim().equals(plainpassword)) {
			return true;
		} else {
			return false;
		}
	}
	
	public User() {
	}
	
	

	public User(String username, String password, String firstName, String lastName, String emailID, String designation) {
		this.username = username;
		this.password = EncDecUtil.encryptMessage(password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailID = emailID;
		this.designation = designation;
	}

	public String getDesignation() {
		return designation;
	}

	public String getEmailID() {
		return emailID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setPassword(String password) {
		this.password = EncDecUtil.encryptMessage(password);
	}
	
	public void setEncryptedPassword(String encPassword) {
		this.password = encPassword;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public EnumStatus getStatus() {
		return status;
	}

	public void setStatus(EnumStatus status) {
		this.status = status;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(username);
		sb.append(":");
		sb.append(lastName);
		sb.append(":");
		sb.append(firstName);
		sb.append(":");
		sb.append(password);
		sb.append(":");
		sb.append(emailID);
		sb.append(":");
		sb.append(designation);
		
		return sb.toString();
	}
}
