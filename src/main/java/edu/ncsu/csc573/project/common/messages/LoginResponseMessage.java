package edu.ncsu.csc573.project.common.messages;

import java.math.BigInteger;
import org.apache.log4j.Logger;

import edu.ncsu.csc573.project.common.schema.CommandResponseType;
import edu.ncsu.csc573.project.common.schema.LoginResponseType;
import edu.ncsu.csc573.project.common.schema.LoginResponseParamsType;
import edu.ncsu.csc573.project.common.schema.Response;
/**
 *
 * @author krishna
 */
public class LoginResponseMessage extends ResponseMessage {
    	private Logger logger;
        
	public String getRequestInXML() throws Exception {

		logger = Logger.getLogger(LoginResponseMessage.class);

		Response req = new Response();
		req.setId(BigInteger.valueOf(System.currentTimeMillis()));
		CommandResponseType Loginresponse = new CommandResponseType();
		LoginResponseType rt = new LoginResponseType();
		LoginResponseParamsType rpt = new LoginResponseParamsType();
		
		rpt.setStatuscode((BigInteger)(getParameter().getParamValue(EnumParamsType.STATUSCODE)));
		rpt.setMessage(getParameter().getParamValue(EnumParamsType.MESSAGE).toString());
            
                rt.setParams(rpt);
		Loginresponse.setLoginResponse(rt);
		req.setCommand(Loginresponse);

		return getXML(req);
	}

	public void parseXML(String XML) {
		logger = Logger.getLogger(LoginResponseMessage.class);
		try {
			Response req = getResponse(XML);
			
			CommandResponseType command = req.getCommand();
			LoginResponseType loginType = command.getLoginResponse();
			LoginResponseParamsType loginparams = loginType.getParams();
			IParameter param = new Parameter();
			param.add(EnumParamsType.STATUSCODE, loginparams.getStatuscode());
			param.add(EnumParamsType.MESSAGE, loginparams.getMessage());
                        
			this.setOperationType(EnumOperationType.LOGINRESPONSE);
			this.setParameter(param);
		} catch(Exception e) {
			logger.error("Unable to parse request from string", e);
		} 
	}                
}
