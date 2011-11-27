/**
 * 
 */
package edu.ncsu.csc573.project.common.messages;

/**
 * This enumeration class defines all the operations that can be performed with
 * bootstrap server.
 * 
 * These values are used for creating IRequest objects.
 * 
 * @author doogle-dev
 * 
 */
public enum EnumOperationType {
	REGISTER, // Register message type
	LOGIN, // Login message type
	LOGOUT, // Logout message type
	SEARCH, // Search message type
	PUBLISH, // Publish message type
	FORGOTPASSWORD, // All below message types @ added Krishna
	REGISTERRESPONSE, LOGINRESPONSE, LOGOUTRESPONSE, SEARCHRESPONSE, PUBLISHRESPONSE, FORGOTPASSWORDRESPONSE, CHANGEPASSWORD, CHANGEPASSWORDRESPONSE, INVALIDRESPONSE
}
