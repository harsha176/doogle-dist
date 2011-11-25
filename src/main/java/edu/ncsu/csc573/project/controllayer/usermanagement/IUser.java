package edu.ncsu.csc573.project.controllayer.usermanagement;
/**
 * This interface abstracts the notion of user.
 * @author doogle-dev
 *
 */
public interface IUser {
	/**
	 * returns user id of the user
	 */
	public int getUserID();
	
	/**
	 * returns user name
	 */
	public String getUsername();
	
	/**
	 * checks if the given password is valid 
	 * @param password
	 */
	public boolean validatePassword(String password);
}
