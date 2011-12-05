/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncsu.csc573.project.common.messages;

import edu.ncsu.csc573.project.common.EncDecUtil;
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
	private String id;
	
	public ChangePasswordRequestMessage() {
		id = ""+System.currentTimeMillis();
	}
	
	public String getRequestInXML() throws Exception {

		logger = Logger.getLogger(ChangePasswordRequestMessage.class);

		Request req = new Request();
		req.setId(id);
		CommandRequestType changepassword = new CommandRequestType();
		ChangePasswordType rt = new ChangePasswordType();
		ChangePasswordParamsType rpt = new ChangePasswordParamsType();
		
		rpt.setUsername((getParameter().getParamValue(EnumParamsType.USERNAME).toString()));
		String clearTextPasswd = getParameter().getParamValue(EnumParamsType.PASSWORD)
				.toString();
		String encryptedPasswd = EncDecUtil.encryptMessage(clearTextPasswd);
		rpt.setPassword(encryptedPasswd);
        String clearTextNewPasswd = getParameter().getParamValue(EnumParamsType.NEWPASSWORD).toString();
		String encryptedNewPasswd = EncDecUtil.encryptMessage(clearTextNewPasswd);
		rpt.setNewpassword(encryptedNewPasswd);
               
		rt.setParams(rpt);
		changepassword.setChangePassword(rt);
		req.setCommand(changepassword);

		return getXML(req);
	}

	public void parseXML(String XML) {
		logger = Logger.getLogger(ChangePasswordRequestMessage.class);
		try {
			Request req = getRequest(XML);
			id = req.getId();
			CommandRequestType command = req.getCommand();
			ChangePasswordType changeType = command.getChangePassword();
			ChangePasswordParamsType regparams = changeType.getParams();
			IParameter param = new Parameter();
			param.add(EnumParamsType.USERNAME, regparams.getUsername());
			String encryptedPasswd = regparams.getPassword();
			String clearTextPasswd = EncDecUtil.decryptMessage(encryptedPasswd);
			param.add(EnumParamsType.PASSWORD, clearTextPasswd);
			String encryptedNewTextPasswd = regparams.getNewpassword(); 
			String clearTextNewPasswd = EncDecUtil.decryptMessage(encryptedNewTextPasswd);
			
			
			param.add(EnumParamsType.PASSWORD,  clearTextPasswd);
            param.add(EnumParamsType.NEWPASSWORD, clearTextNewPasswd);
                        
			this.setOperationType(EnumOperationType.CHANGEPASSWORD);
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
}
