package edu.ncsu.csc573.project.common.messages;

import java.math.BigInteger;
import org.apache.log4j.Logger;

import edu.ncsu.csc573.project.common.schema.CommandResponseType;
import edu.ncsu.csc573.project.common.schema.ForgotPWDResponseType;
import edu.ncsu.csc573.project.common.schema.ForgotPWDResponseParamsType;
import edu.ncsu.csc573.project.common.schema.Response;

/**
 *
 * @author krishna
*/ 
public class ForgotPWDResponseMessage extends ResponseMessage {
    	private Logger logger;
        
	public String getRequestInXML() throws Exception {

		logger = Logger.getLogger(ForgotPWDResponseMessage.class);

		Response req = new Response();
		req.setId(BigInteger.valueOf(System.currentTimeMillis()));
		CommandResponseType ForgotPWDresponse = new CommandResponseType();
		ForgotPWDResponseType rt = new ForgotPWDResponseType();
		ForgotPWDResponseParamsType rpt = new ForgotPWDResponseParamsType();
		
		rpt.setStatuscode((BigInteger)(getParameter().getParamValue(EnumParamsType.STATUSCODE)));
		rpt.setMessage(getParameter().getParamValue(EnumParamsType.MESSAGE).toString());
            
                rt.setParams(rpt);
		ForgotPWDresponse.setForgotPWDResponse(rt);
		req.setCommand(ForgotPWDresponse);

		return getXML(req);
	}

	public void parseXML(String XML) {
		logger = Logger.getLogger(ForgotPWDResponseMessage.class);
		try {
			Response req = getResponse(XML);
			
			CommandResponseType command = req.getCommand();
			ForgotPWDResponseType forgotPwdType = command.getForgotPWDResponse();
			ForgotPWDResponseParamsType regparams = forgotPwdType.getParams();
                        IParameter param = new Parameter();
			param.add(EnumParamsType.STATUSCODE, regparams.getStatuscode());
			param.add(EnumParamsType.MESSAGE, regparams.getMessage());
			
			this.setOperationType(EnumOperationType.FORGOTPASSWORDRESPONSE);
			this.setParameter(param);
		} catch(Exception e) {
			logger.error("Unable to parse request from string", e);
		} 
	} 
}
