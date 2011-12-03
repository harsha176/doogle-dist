/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncsu.csc573.project.common.messages;

import java.math.BigInteger;

import org.apache.log4j.Logger;

import edu.ncsu.csc573.project.common.schema.CommandResponseType;
import edu.ncsu.csc573.project.common.schema.GetResponseType;
import edu.ncsu.csc573.project.common.schema.GetResponseTypeParams;
import edu.ncsu.csc573.project.common.schema.Response;

/**
 *
 * @author krishna
 */
public class GetResponse extends ResponseMessage {
	private GetResponseTypeParams getParams;
	private Logger logger;
	private String id;
	
	public GetResponse(String id) {
		this.id = id;
		getParams = new GetResponseTypeParams();
	}
	
	public GetResponseTypeParams getGetParams() {
		return getParams;
	}
	
	public void setGetParams(GetResponseTypeParams getParams) {
		this.getParams = getParams;
	}
	
	public String getRequestInXML() throws Exception {
		Response req = new Response();
		req.setId(id);
		CommandResponseType publish = new CommandResponseType();
		GetResponseType publishType = new GetResponseType();
		GetResponseTypeParams lpt = getParams;
		getParams.setStatuscode(((BigInteger) (getParameter()
				.getParamValue(EnumParamsType.STATUSCODE))));
		getParams.setMessage(getParameter().getParamValue(
				EnumParamsType.MESSAGE).toString());
		publishType.setParams(lpt);
		publish.setGetResponse(publishType);
		req.setCommand(publish);
	
		return getXML(req);
	}

	public void parseXML(String XML) {
		logger = Logger.getLogger(RegisterRequestMessage.class);
		try {
			Response req = getResponse(XML);
			id = req.getId();
			CommandResponseType command = req.getCommand();
			GetResponseType regType = command.getGetResponse();
			getParams = regType.getParams();
			IParameter param = new Parameter();
			param.add(EnumParamsType.STATUSCODE,
					getParams.getStatuscode());
			param.add(EnumParamsType.MESSAGE,
					getParams.getMessage());

			this.setOperationType(EnumOperationType.GETRESPONSE);
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
}
