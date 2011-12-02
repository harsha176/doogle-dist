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
	private String id;
       
	public InvalidResponseMessage(String id, String message) {
		this(new BigInteger(String.valueOf(1)), message);
		this.id = id;
	}
	
	public String getRequestInXML() throws Exception {

		logger = Logger.getLogger(InvalidResponseMessage.class);

		Response req = new Response();
		req.setId(id);
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
			id = req.getId();
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
	
	public InvalidResponseMessage(BigInteger status, String message) {
		super();
		IParameter param = new Parameter();
		param.add(EnumParamsType.STATUSCODE, status);
		param.add(EnumParamsType.MESSAGE, message);
		
		this.setOperationType(EnumOperationType.INVALIDRESPONSE);
		this.setParameter(param);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = String.valueOf(id);
	}
}