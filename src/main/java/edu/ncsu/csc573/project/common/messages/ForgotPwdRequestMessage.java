/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncsu.csc573.project.common.messages;

import java.math.BigInteger;
import org.apache.log4j.Logger;

import edu.ncsu.csc573.project.common.schema.CommandRequestType;
import edu.ncsu.csc573.project.common.schema.ForgotPasswdParamsType;
import edu.ncsu.csc573.project.common.schema.ForgotPasswdType;
import edu.ncsu.csc573.project.common.schema.Request;
/**
 *
 * @author krishna
 */
public class ForgotPwdRequestMessage extends RequestMessage {
    private Logger logger;
	private String id;

	public String getRequestInXML() throws Exception {
	logger = Logger.getLogger(ForgotPwdRequestMessage.class);

		Request req = new Request();
		req.setId(id);
		CommandRequestType forgotPwd = new CommandRequestType();
		ForgotPasswdType forgotPwdType = new ForgotPasswdType();
		ForgotPasswdParamsType lpt = new ForgotPasswdParamsType();
		
		lpt.setUsername((getParameter().getParamValue(EnumParamsType.USERNAME).toString()));
//                lpt.setEmailId(getParameter().getParamValue(EnumParamsType.EMAIL_ID).toString());

		forgotPwdType.setParams(lpt);
		forgotPwd.setForgotPWD(forgotPwdType);
		req.setCommand(forgotPwd);
		
		return getXML(req);
	}

	public void parseXML(String XML) {
		try {
			Request req = getRequest(XML);
			id = req.getId();
			CommandRequestType command = req.getCommand();
			ForgotPasswdType forgotPasswdType = command.getForgotPWD();
			ForgotPasswdParamsType forgoPasswdparams = forgotPasswdType.getParams();
			IParameter param = new Parameter();
		
                        param.add(EnumParamsType.USERNAME, forgoPasswdparams.getUsername());
  //              	param.add(EnumParamsType.EMAIL_ID, forgoPasswdparams.getEmailId());

			this.setOperationType(EnumOperationType.FORGOTPASSWORD);
			this.setParameter(param);
                        
		} catch(Exception e) {
			logger.error("Unable to parse request from string", e);
		}
	}  
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = String.valueOf(id);
	}
	
	public ForgotPwdRequestMessage() {
		id = ""+System.currentTimeMillis();
	}
}
