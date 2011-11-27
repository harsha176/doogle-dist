/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncsu.csc573.project.common.messages;

import edu.ncsu.csc573.project.common.schema.CommandRequestType;
import edu.ncsu.csc573.project.common.schema.JoinType;
import edu.ncsu.csc573.project.common.schema.JoinTypeParams;
import edu.ncsu.csc573.project.common.schema.Request;
import java.math.BigInteger;
import org.apache.log4j.Logger;

/**
 *
 * @author krishna
 */
public class JoinRequest extends RequestMessage {
     private Logger logger;

	public String getRequestInXML() throws Exception {
	logger = Logger.getLogger(LoginRequestMessage.class);

		Request req = new Request();
		req.setId(BigInteger.valueOf(System.currentTimeMillis()));
		CommandRequestType join = new CommandRequestType();
		JoinType joinType = new JoinType();
		JoinTypeParams lpt = new JoinTypeParams();
		
		lpt.setIpaddress((getParameter().getParamValue(EnumParamsType.IPADDRESS).toString()));
		lpt.setPeerid(getParameter().getParamValue(EnumParamsType.PEERID).toString());
                    
		joinType.setParams(lpt);
		join.setJoin(joinType);
		req.setCommand(join);
		
		return getXML(req);
	}

	public void parseXML(String XML) {
		try {
			Request req = getRequest(XML);
			
			CommandRequestType command = req.getCommand();
			JoinType joinType = command.getJoin();
			JoinTypeParams joinparams = joinType.getParams();
			IParameter param = new Parameter();
			param.add(EnumParamsType.USERNAME, joinparams.getIpaddress());
			param.add(EnumParamsType.PASSWORD, joinparams.getPeerid());

			this.setOperationType(EnumOperationType.JOIN);
			this.setParameter(param);
		} catch(Exception e) {
			logger.error("Unable to parse request from string", e);
		}
	}

    
}
