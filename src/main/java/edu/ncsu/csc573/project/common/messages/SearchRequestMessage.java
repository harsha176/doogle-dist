package edu.ncsu.csc573.project.common.messages;

import java.math.BigInteger;
import org.apache.log4j.Logger;

import edu.ncsu.csc573.project.common.schema.CommandRequestType;
import edu.ncsu.csc573.project.common.schema.SearchTypeParams;
import edu.ncsu.csc573.project.common.schema.SearchType;
import edu.ncsu.csc573.project.common.schema.Request;

/**
 *
 * @author krishna
 */
public class SearchRequestMessage extends RequestMessage {
    	private Logger logger;

	public String getRequestInXML() throws Exception {

		logger = Logger.getLogger(SearchRequestMessage.class);

		Request req = new Request();
		req.setId(BigInteger.valueOf(System.currentTimeMillis()));
		CommandRequestType search = new CommandRequestType();
		SearchType s = new SearchType();
		SearchTypeParams rpt = new SearchTypeParams();
		
		rpt.setUsername((getParameter().getParamValue(EnumParamsType.USERNAME).toString()));
		rpt.setSearch(getParameter().getParamValue(EnumParamsType.SEARCHKEY).toString());

		s.setParams(rpt);
		search.setSearch(s);
		req.setCommand(search);

		return getXML(req);
	}

	public void parseXML(String XML) {
		logger = Logger.getLogger(RegisterRequestMessage.class);
		try {
			Request req = getRequest(XML);
			
			CommandRequestType command = req.getCommand();
			SearchType searchType = command.getSearch();
			SearchTypeParams searchparams = searchType.getParams();
			IParameter param = new Parameter();
			param.add(EnumParamsType.USERNAME, searchparams.getUsername());
			param.add(EnumParamsType.SEARCHKEY, searchparams.getSearch());
		
			this.setOperationType(EnumOperationType.SEARCH);
			this.setParameter(param);
		} catch(Exception e) {
			logger.error("Unable to parse request from string", e);
		}
	}
    
}
