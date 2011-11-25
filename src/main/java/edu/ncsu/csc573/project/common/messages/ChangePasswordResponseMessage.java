/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncsu.csc573.project.common.messages;

import edu.ncsu.csc573.project.common.schema.ChangePasswordResponseParamsType;
import edu.ncsu.csc573.project.common.schema.ChangePasswordResponseType;
import edu.ncsu.csc573.project.common.schema.CommandResponseType;
import edu.ncsu.csc573.project.common.schema.Response;
import java.math.BigInteger;
import org.apache.log4j.Logger;

/**
 *
 * @author krishna
 */
public class ChangePasswordResponseMessage extends ResponseMessage {
    	private Logger logger;
        
	public String getRequestInXML() throws Exception {

		logger = Logger.getLogger(ForgotPWDResponseMessage.class);

		Response req = new Response();
		req.setId(BigInteger.valueOf(System.currentTimeMillis()));
		CommandResponseType Changeresponse = new CommandResponseType();
		ChangePasswordResponseType rt = new ChangePasswordResponseType();
		ChangePasswordResponseParamsType rpt = new ChangePasswordResponseParamsType();
		
		rpt.setStatuscode((BigInteger)(getParameter().getParamValue(EnumParamsType.STATUSCODE)));
		rpt.setMessage(getParameter().getParamValue(EnumParamsType.MESSAGE).toString());
            
                rt.setParams(rpt);
		Changeresponse.setChangePasswordResponse(rt);
		req.setCommand(Changeresponse);

		return getXML(req);
	}

	public void parseXML(String XML) {
		logger = Logger.getLogger(ForgotPWDResponseMessage.class);
		try {
			Response req = getResponse(XML);
			
			CommandResponseType command = req.getCommand();
			ChangePasswordResponseType changeType = command.getChangePasswordResponse();
			ChangePasswordResponseParamsType regparams = changeType.getParams();
                        IParameter param = new Parameter();
			param.add(EnumParamsType.STATUSCODE, regparams.getStatuscode());
			param.add(EnumParamsType.MESSAGE, regparams.getMessage());
			
			this.setOperationType(EnumOperationType.CHANGEPASSWORDRESPONSE);
			this.setParameter(param);
		} catch(Exception e) {
			logger.error("Unable to parse request from string", e);
		} 
	} 
}
