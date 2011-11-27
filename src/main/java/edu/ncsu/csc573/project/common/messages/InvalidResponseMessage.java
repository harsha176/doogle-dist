package edu.ncsu.csc573.project.common.messages;

import java.math.BigInteger;

import org.apache.log4j.Logger;

import edu.ncsu.csc573.project.common.schema.CommandResponseType;
import edu.ncsu.csc573.project.common.schema.InvalidResponseType;
import edu.ncsu.csc573.project.common.schema.InvalidResponseTypeParams;
import edu.ncsu.csc573.project.common.schema.Response;

/**
*
* @author krishna
*/
public class InvalidResponseMessage extends ResponseMessage {
   	private Logger logger;
       
	public String getRequestInXML() throws Exception {

		logger = Logger.getLogger(InvalidResponseMessage.class);

		Response req = new Response();
		req.setId(BigInteger.valueOf(System.currentTimeMillis()));
		CommandResponseType Logoutresponse = new CommandResponseType();
		InvalidResponseType rt = new InvalidResponseType();
		InvalidResponseTypeParams rpt = new InvalidResponseTypeParams();
		
		rpt.setStatuscode((BigInteger)(getParameter().getParamValue(EnumParamsType.STATUSCODE)));
		rpt.setMessage(getParameter().getParamValue(EnumParamsType.MESSAGE).toString());
           
        rt.setParams(rpt);
		Logoutresponse.setInvalidResponse(rt);
		req.setCommand(Logoutresponse);

		return getXML(req);
	}

	public void parseXML(String XML) {
		logger = Logger.getLogger(LogoutResponseMessage.class);
		try {
			Response req = getResponse(XML);
			
			CommandResponseType command = req.getCommand();
			InvalidResponseType logoutType = command.getInvalidResponse();
			InvalidResponseTypeParams regparams = logoutType.getParams();
			IParameter param = new Parameter();
			param.add(EnumParamsType.STATUSCODE, regparams.getStatuscode());
			param.add(EnumParamsType.MESSAGE, regparams.getMessage());
			
			this.setOperationType(EnumOperationType.INVALIDRESPONSE);
			this.setParameter(param);
		} catch(Exception e) {
			logger.error("Unable to parse request from string", e);
		} 
	} 
}