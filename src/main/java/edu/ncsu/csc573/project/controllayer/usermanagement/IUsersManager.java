/**
 * 
 */
package edu.ncsu.csc573.project.controllayer.usermanagement;

/**
 * This acts as an interface for maintaining users.
 * 
 * @author doogle-dev
 * 
 */
public abstract class IUsersManager {

	private static IUsersManager userManager = null;

	/**
	 * Singleton class
	 * 
	 * @return
	 */
	public static IUsersManager getInstance() throws Exception {
		if (userManager == null) {
			userManager = new UsersManager();
			userManager.initialize();
		}
		return userManager;
	}

	public abstract void initialize() throws Exception;

	/**
	 * Allows to add user
	 * 
	 * @param user
	 */
	public abstract void addUser(IUser user) throws UserManagementException;

	/**
	 * Allows to getUser details
	 * 
	 * @return
	 */
	public abstract IUser getUser(String username);

	/**
	 * allows user to login to the system
	 * 
	 * @param user_id
	 */
	public abstract void userLogin(String username, String password)
			throws UserManagementException;

	/**
	 * allows user to logout
	 * 
	 * @param user_id
	 */
	public abstract void userLogout(String username)
			throws UserManagementException;

	/**
	 * Checks if given has logged into the system.
	 */
	public abstract boolean isUserLoggedin(String username);

	/**
	 * Sends password to his email
	 * 
	 * @param username
	 */
	public abstract void forgotPassword(String username)
			throws UserManagementException;

	/**
	 * This method updates password.
	 * 
	 * @param username
	 * @param oldPassword
	 * @param newPassword
	 */
	public abstract void changePassword(String username, String oldPassword,
			String newPassword) throws UserManagementException;

	public abstract void flushDatabase();
}
