/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncsu.csc573.project.common.messages;

import edu.ncsu.csc573.project.common.schema.CommandResponseType;
import edu.ncsu.csc573.project.common.schema.LeaveResponseType;
import edu.ncsu.csc573.project.common.schema.LeaveResponseTypeParams;
import edu.ncsu.csc573.project.common.schema.Response;
import java.math.BigInteger;
import org.apache.log4j.Logger;

/**
 *
 * @author krishna
 */
public class LeaveResponse extends ResponseMessage {
    	private Logger logger;
        
	public String getRequestInXML() throws Exception {

		logger = Logger.getLogger(LoginResponseMessage.class);

		Response req = new Response();
		req.setId(BigInteger.valueOf(System.currentTimeMillis()));
		CommandResponseType Leaveresponse = new CommandResponseType();
		LeaveResponseType rt = new LeaveResponseType();
		LeaveResponseTypeParams rpt = new LeaveResponseTypeParams();
		
		rpt.setStatuscode((BigInteger)(getParameter().getParamValue(EnumParamsType.STATUSCODE)));
		rpt.setMessage(getParameter().getParamValue(EnumParamsType.MESSAGE).toString());
            
                rt.setParams(rpt);
		Leaveresponse.setLeaveResponse(rt);
		req.setCommand(Leaveresponse);

		return getXML(req);
	}

	public void parseXML(String XML) {
		logger = Logger.getLogger(LoginResponseMessage.class);
		try {
			Response req = getResponse(XML);
			
			CommandResponseType command = req.getCommand();
			LeaveResponseType leaveType = command.getLeaveResponse();
			LeaveResponseTypeParams leaveparams = leaveType.getParams();
			IParameter param = new Parameter();
			param.add(EnumParamsType.STATUSCODE, leaveparams.getStatuscode());
			param.add(EnumParamsType.MESSAGE, leaveparams.getMessage());
                        
			this.setOperationType(EnumOperationType.LEAVERESPONSE);
			this.setParameter(param);
		} catch(Exception e) {
			logger.error("Unable to parse request from string", e);
		} 
	}   
    
}
