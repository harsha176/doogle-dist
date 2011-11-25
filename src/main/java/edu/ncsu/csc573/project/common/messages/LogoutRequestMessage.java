/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncsu.csc573.project.common.messages;

import java.math.BigInteger;
import org.apache.log4j.Logger;

import edu.ncsu.csc573.project.common.schema.CommandRequestType;
import edu.ncsu.csc573.project.common.schema.LogoutParamsType;
import edu.ncsu.csc573.project.common.schema.LogoutType;
import edu.ncsu.csc573.project.common.schema.Request;

/**
 *
 * @author krishna
 */
public class LogoutRequestMessage extends RequestMessage {
    private Logger logger;

	public String getRequestInXML() throws Exception {
	logger = Logger.getLogger(LogoutRequestMessage.class);

		Request req = new Request();
		req.setId(BigInteger.valueOf(System.currentTimeMillis()));
		CommandRequestType logout = new CommandRequestType();
		LogoutType logoutType = new LogoutType();
		LogoutParamsType lpt = new LogoutParamsType();
		
		lpt.setUsername((getParameter().getParamValue(EnumParamsType.USERNAME).toString()));
	//	lpt.setPassword(getParameter().getParamValue(EnumParamsType.PASSWORD).toString());

		logoutType.setParams(lpt);
		logout.setLogout(logoutType);
		req.setCommand(logout);
		
		return getXML(req);
	}

	public void parseXML(String XML) {
		try {
			Request req = getRequest(XML);
			
			CommandRequestType command = req.getCommand();
			LogoutType logoutType = command.getLogout();
			LogoutParamsType logoutparams = logoutType.getParams();
			IParameter param = new Parameter();
			param.add(EnumParamsType.USERNAME, logoutparams.getUsername());

			this.setOperationType(EnumOperationType.LOGOUT);
			this.setParameter(param);
                        
		} catch(Exception e) {
			logger.error("Unable to parse request from string", e);
		}
	}

}
