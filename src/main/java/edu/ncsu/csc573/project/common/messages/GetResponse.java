/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncsu.csc573.project.common.messages;

import edu.ncsu.csc573.project.common.ByteOperationUtil;
import edu.ncsu.csc573.project.common.schema.CommandResponseType;
import edu.ncsu.csc573.project.common.schema.FileParamType;
import edu.ncsu.csc573.project.common.schema.GetResponseType;
import edu.ncsu.csc573.project.common.schema.GetResponseTypeParams;
import edu.ncsu.csc573.project.common.schema.Response;
import java.math.BigInteger;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author krishna
 */
public class GetResponse extends ResponseMessage {
	private PublishSearchParameter searchParam;
	private Logger logger;
	private String id;
	
	public GetResponse(String id) {
		this.id = id;
	}
	
	public IParameter getParameter() {
	    	return searchParam;
	}
	
	public void createResponse(EnumOperationType opType, IParameter parameter) {
		super.createResponse(opType, parameter);
		searchParam = (PublishSearchParameter)parameter;
	}
	
	public void setParameter(IParameter param) {
		if(param instanceof PublishSearchParameter) {
			searchParam = (PublishSearchParameter) param;
		} else {
			if(logger == null) {
				logger = Logger.getLogger(PublishRequestMessage.class);
			}
			logger.error("Invalid parameter");
		}
	}
	public String getRequestInXML() throws Exception {
		Response req = new Response();
		req.setId(id);
		CommandResponseType publish = new CommandResponseType();
		GetResponseType publishType = new GetResponseType();
		GetResponseTypeParams lpt = new GetResponseTypeParams();
		
		searchParam.resetCounter();
		
		while(searchParam.getParamCount() < searchParam.getSize()) {
			FileParamType fileParamType = new FileParamType();
			
			fileParamType.setId(searchParam.getParamCount());
			fileParamType.setFilename(searchParam.getParamValue(EnumParamsType.FILENAME).toString());
			fileParamType.setFiledigest(ByteOperationUtil.convertBytesToString((byte[])searchParam.getParamValue(EnumParamsType.FILEDIGEST)));
			fileParamType.setFilesize(searchParam.getParamValue(EnumParamsType.FILESIZE).toString());
			fileParamType.setAbstract(searchParam.getParamValue(EnumParamsType.ABSTRACT).toString());
			fileParamType.setIpaddress(searchParam.getParamValue(EnumParamsType.IPADDRESS).toString());
			searchParam.setNextParam();
			lpt.getFile().add(fileParamType);
		}
	
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
			GetResponseTypeParams regparams = regType.getParams();
			List<FileParamType> fileList = regparams.getFile();
			IParameter param = new PublishSearchParameter();
			
			for(FileParamType fileParamType : fileList) {
				param.add(EnumParamsType.FILENAME, fileParamType.getFilename());
				param.add(EnumParamsType.FILEDIGEST, ByteOperationUtil.convertStringToBytes(fileParamType.getFiledigest()));
				param.add(EnumParamsType.FILESIZE, fileParamType.getFilesize());
				param.add(EnumParamsType.ABSTRACT, fileParamType.getAbstract());
				param.add(EnumParamsType.IPADDRESS, fileParamType.getIpaddress());
				param.add(EnumParamsType.DELIMITER, fileParamType.getId());
			}
			
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
