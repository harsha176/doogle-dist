/**
 * 
 */
package edu.ncsu.csc573.project.controllayer;

import java.util.List;

import edu.ncsu.csc573.project.common.schema.FileParamType;
import edu.ncsu.csc573.project.common.schema.MatchFileParamType;

/**
 * This interface provides flow control for peer/BS view layer.
 * 
 * @author doogle-dev
 * 
 */
public interface IController {

	/**
	 * This method is called to perform login operation.
	 * 
	 * @throws Exception
	 */
	public String login(String username, String passwd) throws Exception;

	/**
	 * 
	 * @throws Exception
	 */
	public String logout(String username) throws Exception;

	/**
	 * 
	 * @throws Exception
	 */
	public void connect(String bootStrapServer) throws Exception;

	/**
	 * 
	 * @throws Exception
	 */
	public String publish() throws Exception;

	/**
	 * 
	 * @param query
	 * @throws Exception
	 */
	public List<MatchFileParamType> search(String query) throws Exception;

	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param passwd
	 * @param email_id
	 * @param designation
	 */
	public String register(String firstName, String lastName, String username,
			String passwd, String email_id, String designation) throws Exception;

	/**
	 * 
	 * @param newPasswd
	 * @param oldPasswd
	 */
	public String changePasswd(String newPasswd, String oldPasswd) throws Exception;
	
	public String forgotPasswd(String username) throws Exception;
}
