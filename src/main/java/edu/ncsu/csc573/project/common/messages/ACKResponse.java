package edu.ncsu.csc573.project.common.messages;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import edu.ncsu.csc573.project.common.schema.ACKType;
import edu.ncsu.csc573.project.common.schema.ACKTypeParams;
import edu.ncsu.csc573.project.common.schema.CommandResponseType;
import edu.ncsu.csc573.project.common.schema.Response;
import java.math.BigInteger;
import org.apache.log4j.Logger;

/**
 *
 * @author krishna
 */
public class ACKResponse extends ResponseMessage {
    	private Logger logger;
        
	public String getRequestInXML() throws Exception {

		logger = Logger.getLogger(ACKResponse.class);

		Response req = new Response();
		req.setId(BigInteger.valueOf(System.currentTimeMillis()));
		CommandResponseType ACKresponse = new CommandResponseType();
		ACKType rt = new ACKType();
		ACKTypeParams rpt = new ACKTypeParams();
		
		rpt.setStatuscode((BigInteger)(getParameter().getParamValue(EnumParamsType.STATUSCODE)));
		rpt.setMessage(getParameter().getParamValue(EnumParamsType.MESSAGE).toString());
            
                rt.setParams(rpt);
		ACKresponse.setACK(rt);
		req.setCommand(ACKresponse);

		return getXML(req);
	}

	public void parseXML(String XML) {
		logger = Logger.getLogger(ForgotPWDResponseMessage.class);
		try {
			Response req = getResponse(XML);
			
			CommandResponseType command = req.getCommand();
			ACKType ackType = command.getACK();
			ACKTypeParams regparams = ackType.getParams();
                        IParameter param = new Parameter();
			param.add(EnumParamsType.STATUSCODE, regparams.getStatuscode());
			param.add(EnumParamsType.MESSAGE, regparams.getMessage());
			
			this.setOperationType(EnumOperationType.ACKRESPONSE);
			this.setParameter(param);
		} catch(Exception e) {
			logger.error("Unable to parse request from string", e);
		} 
	} 
}
