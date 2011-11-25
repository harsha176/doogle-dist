package edu.ncsu.csc573.project.common.messages;

import java.math.BigInteger;
import org.apache.log4j.Logger;

import edu.ncsu.csc573.project.common.schema.CommandResponseType;
import edu.ncsu.csc573.project.common.schema.LogoutResponseParamsType;
import edu.ncsu.csc573.project.common.schema.LogoutResponseType;
import edu.ncsu.csc573.project.common.schema.Response;

/**
 *
 * @author krishna
 */
public class LogoutResponseMessage extends ResponseMessage {
    	private Logger logger;
        
	public String getRequestInXML() throws Exception {

		logger = Logger.getLogger(LogoutResponseMessage.class);

		Response req = new Response();
		req.setId(BigInteger.valueOf(System.currentTimeMillis()));
		CommandResponseType Logoutresponse = new CommandResponseType();
		LogoutResponseType rt = new LogoutResponseType();
		LogoutResponseParamsType rpt = new LogoutResponseParamsType();
		
		rpt.setStatuscode((BigInteger)(getParameter().getParamValue(EnumParamsType.STATUSCODE)));
//		rpt.setMessage(getParameter().getParamValue(EnumParamsType.MESSAGE).toString());
            
                rt.setParams(rpt);
		Logoutresponse.setLogoutResponse(rt);
		req.setCommand(Logoutresponse);

		return getXML(req);
	}

	public void parseXML(String XML) {
		logger = Logger.getLogger(LogoutResponseMessage.class);
		try {
			Response req = getResponse(XML);
			
			CommandResponseType command = req.getCommand();
			LogoutResponseType logoutType = command.getLogoutResponse();
			LogoutResponseParamsType regparams = logoutType.getParams();
			IParameter param = new Parameter();
			param.add(EnumParamsType.STATUSCODE, regparams.getStatuscode());
//			param.add(EnumParamsType.MESSAGE, regparams.getMessage());
			
			this.setOperationType(EnumOperationType.LOGOUTRESPONSE);
			this.setParameter(param);
		} catch(Exception e) {
			logger.error("Unable to parse request from string", e);
		} 
	} 
}

