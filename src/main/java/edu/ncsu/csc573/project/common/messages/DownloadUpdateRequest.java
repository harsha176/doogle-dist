package edu.ncsu.csc573.project.common.messages;

import org.apache.log4j.Logger;

import edu.ncsu.csc573.project.common.ByteOperationUtil;
import edu.ncsu.csc573.project.common.schema.CommandRequestType;
import edu.ncsu.csc573.project.common.schema.DownloadUpdateParams;
import edu.ncsu.csc573.project.common.schema.DownloadUpdateType;
import edu.ncsu.csc573.project.common.schema.Request;

public class DownloadUpdateRequest extends RequestMessage {
    private Logger logger;
	private String id;

	public String getRequestInXML() throws Exception {
	logger = Logger.getLogger(LoginRequestMessage.class);

		Request req = new Request();
		req.setId(id);
		CommandRequestType login = new CommandRequestType();
		DownloadUpdateType loginType = new DownloadUpdateType();
		DownloadUpdateParams lpt = new DownloadUpdateParams();
		
		lpt.setFilename((getParameter().getParamValue(EnumParamsType.FILENAME).toString()));
		lpt.setFiledigest(getParameter().getParamValue(EnumParamsType.FILEDIGEST).toString());
		
		loginType.setParams(lpt);
		login.setDownloadUpdate(loginType);
		req.setCommand(login);
		
		return getXML(req);
	}

	public void parseXML(String XML) {
		try {
			Request req = getRequest(XML);
			id = req.getId();
			CommandRequestType command = req.getCommand();
			DownloadUpdateType loginType = command.getDownloadUpdate();
			DownloadUpdateParams loginparams = loginType.getParams();
			
			IParameter param = new Parameter();
			param.add(EnumParamsType.FILEDIGEST, ByteOperationUtil
					.convertStringToBytes(loginparams.getFiledigest()));
			param.add(EnumParamsType.FILENAME, loginparams.getFilename());
			
			this.setOperationType(EnumOperationType.DOWNLOADUPDATE);
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
	public DownloadUpdateRequest() {
		id = ""+System.currentTimeMillis();
	}
}
