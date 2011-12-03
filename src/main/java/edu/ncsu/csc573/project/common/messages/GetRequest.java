/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncsu.csc573.project.common.messages;

import edu.ncsu.csc573.project.common.ByteOperationUtil;
import edu.ncsu.csc573.project.common.schema.CommandRequestType;
import edu.ncsu.csc573.project.common.schema.GetType;
import edu.ncsu.csc573.project.common.schema.GetTypeParams;
import edu.ncsu.csc573.project.common.schema.Request;
import java.math.BigInteger;
import org.apache.log4j.Logger;

/**
 *
 * @author krishna
 */
public class GetRequest extends RequestMessage {
    	private Logger logger;
		private String id;

	public String getRequestInXML() throws Exception {

		logger = Logger.getLogger(SearchRequestMessage.class);

		Request req = new Request();
		req.setId(id);
		CommandRequestType search = new CommandRequestType();
		GetType s = new GetType();
		GetTypeParams rpt = new GetTypeParams();
		
		rpt.setIpaddress((getParameter().getParamValue(EnumParamsType.IPADDRESS).toString()));
		rpt.setSearchdigest(getParameter().getParamValue(EnumParamsType.SEARCHKEY).toString());
		rpt.setTtl("10");
		s.setParams(rpt);
		search.setGet(s);
		req.setCommand(search);

		return getXML(req);
	}

	public void parseXML(String XML) {
		logger = Logger.getLogger(RegisterRequestMessage.class);
		try {
			Request req = getRequest(XML);
			id = req.getId();
			CommandRequestType command = req.getCommand();
			GetType searchType = command.getGet();
			GetTypeParams searchparams = searchType.getParams();
			IParameter param = new Parameter();
			param.add(EnumParamsType.IPADDRESS, searchparams.getIpaddress());
			param.add(EnumParamsType.SEARCHKEY, ByteOperationUtil
					.convertStringToBytes(searchparams.getSearchdigest()));
			//param.add(EnumParamsType.);
			this.setOperationType(EnumOperationType.GET);
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
	
	public GetRequest() {
		id = ""+System.currentTimeMillis();
	}
}
