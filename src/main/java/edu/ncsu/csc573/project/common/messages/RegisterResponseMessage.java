package edu.ncsu.csc573.project.common.messages;

import java.math.BigInteger;
import org.apache.log4j.Logger;

import edu.ncsu.csc573.project.common.schema.CommandResponseType;
import edu.ncsu.csc573.project.common.schema.RegisterResponseParamsType;
import edu.ncsu.csc573.project.common.schema.RegisterResponseType;
import edu.ncsu.csc573.project.common.schema.Response;

/**
 *
 * @author krishna
 */
public class RegisterResponseMessage extends ResponseMessage {
    	private Logger logger;
        
	public String getRequestInXML() throws Exception {

		logger = Logger.getLogger(RegisterResponseMessage.class);

		Response req = new Response();
		req.setId(BigInteger.valueOf(System.currentTimeMillis()));
		CommandResponseType regresponse = new CommandResponseType();
		RegisterResponseType rt = new RegisterResponseType();
		RegisterResponseParamsType rpt = new RegisterResponseParamsType();
		
		rpt.setStatuscode((BigInteger)(getParameter().getParamValue(EnumParamsType.STATUSCODE)));
		rpt.setMessage(getParameter().getParamValue(EnumParamsType.MESSAGE).toString());
            
                rt.setParams(rpt);
		regresponse.setRegisterResponse(rt);
		req.setCommand(regresponse);

		return getXML(req);
	}

	public void parseXML(String XML) {
		logger = Logger.getLogger(RegisterResponseMessage.class);
		try {
			Response req = getResponse(XML);
			
			CommandResponseType command = req.getCommand();
			RegisterResponseType regType = command.getRegisterResponse();
			RegisterResponseParamsType regparams = regType.getParams();
			IParameter param = new Parameter();
			param.add(EnumParamsType.STATUSCODE, regparams.getStatuscode());
			param.add(EnumParamsType.MESSAGE, regparams.getMessage());
			
			this.setOperationType(EnumOperationType.REGISTERRESPONSE);
			this.setParameter(param);
		} catch(Exception e) {
			logger.error("Unable to parse request from string", e);
		} 
	} 
        
        
}
