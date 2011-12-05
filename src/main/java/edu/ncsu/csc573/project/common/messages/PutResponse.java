/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncsu.csc573.project.common.messages;

import edu.ncsu.csc573.project.common.schema.CommandResponseType;
import edu.ncsu.csc573.project.common.schema.PutResponseType;
import edu.ncsu.csc573.project.common.schema.PutResponseTypeParams;
import edu.ncsu.csc573.project.common.schema.Response;
import java.math.BigInteger;
import org.apache.log4j.Logger;

/**
 *
 * @author krishna
 */
public class PutResponse extends ResponseMessage {
    	private Logger logger;
		private String id;
        
	public String getRequestInXML() throws Exception {

		logger = Logger.getLogger(LoginResponseMessage.class);

		Response req = new Response();
		req.setId(id);
		CommandResponseType Putresponse = new CommandResponseType();
		PutResponseType rt = new PutResponseType();
		PutResponseTypeParams rpt = new PutResponseTypeParams();
		
		rpt.setStatuscode((BigInteger)(getParameter().getParamValue(EnumParamsType.STATUSCODE)));
		rpt.setMessage(getParameter().getParamValue(EnumParamsType.MESSAGE).toString());
            
                rt.setParams(rpt);
		Putresponse.setPutResponse(rt);
		req.setCommand(Putresponse);

		return getXML(req);
	}

	public void parseXML(String XML) {
		logger = Logger.getLogger(LoginResponseMessage.class);
		try {
			Response req = getResponse(XML);
			id = req.getId();
			CommandResponseType command = req.getCommand();
			PutResponseType leaveType = command.getPutResponse();
			PutResponseTypeParams leaveparams = leaveType.getParams();
			IParameter param = new Parameter();
			param.add(EnumParamsType.STATUSCODE, leaveparams.getStatuscode());
			param.add(EnumParamsType.MESSAGE, leaveparams.getMessage());
                        
			this.setOperationType(EnumOperationType.PUTRESPONSE);
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
	public PutResponse(String id) {
		this.id = id;
	}
	
}
