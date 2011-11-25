/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncsu.csc573.project.common.messages;

import edu.ncsu.csc573.project.common.schema.ChangePasswordParamsType;
import edu.ncsu.csc573.project.common.schema.ChangePasswordType;
import edu.ncsu.csc573.project.common.schema.CommandRequestType;
import edu.ncsu.csc573.project.common.schema.Request;
import java.math.BigInteger;
import org.apache.log4j.Logger;


/**
 *
 * @author krishna
 */
public class ChangePasswordRequestMessage extends RequestMessage {
	private Logger logger;

	public String getRequestInXML() throws Exception {

		logger = Logger.getLogger(ChangePasswordRequestMessage.class);

		Request req = new Request();
		req.setId(BigInteger.valueOf(System.currentTimeMillis()));
		CommandRequestType changepassword = new CommandRequestType();
		ChangePasswordType rt = new ChangePasswordType();
		ChangePasswordParamsType rpt = new ChangePasswordParamsType();
		
		rpt.setUsername((getParameter().getParamValue(EnumParamsType.USERNAME).toString()));
		rpt.setPassword(getParameter().getParamValue(EnumParamsType.PASSWORD).toString());
        rpt.setNewpassword(getParameter().getParamValue(EnumParamsType.NEWPASSWORD).toString());
               
		rt.setParams(rpt);
		changepassword.setChangePassword(rt);
		req.setCommand(changepassword);

		return getXML(req);
	}

	public void parseXML(String XML) {
		logger = Logger.getLogger(ChangePasswordRequestMessage.class);
		try {
			Request req = getRequest(XML);
			
			CommandRequestType command = req.getCommand();
			ChangePasswordType changeType = command.getChangePassword();
			ChangePasswordParamsType regparams = changeType.getParams();
			IParameter param = new Parameter();
			param.add(EnumParamsType.USERNAME, regparams.getUsername());
			param.add(EnumParamsType.PASSWORD, regparams.getPassword());
            param.add(EnumParamsType.NEWPASSWORD, regparams.getNewpassword());
                        
			this.setOperationType(EnumOperationType.CHANGEPASSWORD);
			this.setParameter(param);
		} catch(Exception e) {
			logger.error("Unable to parse request from string", e);
		}
	}    
}
