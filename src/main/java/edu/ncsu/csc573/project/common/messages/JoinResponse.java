/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncsu.csc573.project.common.messages;

import edu.ncsu.csc573.project.common.schema.CommandResponseType;
import edu.ncsu.csc573.project.common.schema.JoinResponseType;
import edu.ncsu.csc573.project.common.schema.JoinResponseTypeParams;
import edu.ncsu.csc573.project.common.schema.Response;
import java.math.BigInteger;
import org.apache.log4j.Logger;

/**
 *
 * @author krishna
 */
public class JoinResponse extends ResponseMessage {
     private Logger logger;

	public String getRequestInXML() throws Exception {
	logger = Logger.getLogger(LoginRequestMessage.class);

		Response req = new Response();
		req.setId(BigInteger.valueOf(System.currentTimeMillis()));
		CommandResponseType Putresponse = new CommandResponseType();
		JoinResponseType rt = new JoinResponseType();
		JoinResponseTypeParams rpt = new JoinResponseTypeParams();
		
		rpt.setStatuscode((BigInteger)(getParameter().getParamValue(EnumParamsType.STATUSCODE)));
		rpt.setMessage(getParameter().getParamValue(EnumParamsType.MESSAGE).toString());
                rpt.setIpaddress((getParameter().getParamValue(EnumParamsType.IPADDRESS).toString()));
		rpt.setPeerid(getParameter().getParamValue(EnumParamsType.PEERID).toString());
                rpt.setFirsthash(getParameter().getParamValue(EnumParamsType.FIRSTHASH).toString());
                rpt.setLasthash(getParameter().getParamValue(EnumParamsType.LASTHASH).toString());
                //
                
		rt.setParams(rpt);
		Putresponse.setJoinResponse(rt);
		req.setCommand(Putresponse);
		
		return getXML(req);
	}

	public void parseXML(String XML) {
		
	}
    
}
