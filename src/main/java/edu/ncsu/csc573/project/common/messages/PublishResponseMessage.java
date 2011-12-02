package edu.ncsu.csc573.project.common.messages;

import java.math.BigInteger;
import org.apache.log4j.Logger;

import edu.ncsu.csc573.project.common.schema.CommandResponseType;
import edu.ncsu.csc573.project.common.schema.PublishResponseTypeParams;
import edu.ncsu.csc573.project.common.schema.PublishResponseType;
import edu.ncsu.csc573.project.common.schema.Response;

/**
 *
 * @author krishna
 */
public class PublishResponseMessage extends ResponseMessage {
    	private Logger logger;
		private String id;
        
	public String getRequestInXML() throws Exception {

		logger = Logger.getLogger(PublishResponseMessage.class);

		Response req = new Response();
		req.setId(id);
		CommandResponseType Publishresponse = new CommandResponseType();
		PublishResponseType rt = new PublishResponseType();
		PublishResponseTypeParams rpt = new PublishResponseTypeParams();
		
		rpt.setStatuscode((BigInteger)(getParameter().getParamValue(EnumParamsType.STATUSCODE)));
		rpt.setMessage(getParameter().getParamValue(EnumParamsType.MESSAGE).toString());
            
                rt.setParams(rpt);
		Publishresponse.setPublishResponse(rt);
		req.setCommand(Publishresponse);

		return getXML(req);
	}

	public void parseXML(String XML) {
		logger = Logger.getLogger(PublishResponseMessage.class);
		try {
			Response req = getResponse(XML);
			
			CommandResponseType command = req.getCommand();
			PublishResponseType publishType = command.getPublishResponse();
			PublishResponseTypeParams regparams = publishType.getParams();
			IParameter param = new Parameter();
			param.add(EnumParamsType.STATUSCODE, regparams.getStatuscode());
			param.add(EnumParamsType.MESSAGE, regparams.getMessage());
			
			this.setOperationType(EnumOperationType.PUBLISHRESPONSE);
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
	public PublishResponseMessage(String id) {
		this.id = id;
	}
	
}


