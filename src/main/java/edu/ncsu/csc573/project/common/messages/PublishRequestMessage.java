package edu.ncsu.csc573.project.common.messages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import edu.ncsu.csc573.project.commlayer.CommunicationServiceFactory;
import edu.ncsu.csc573.project.commlayer.ICommunicationService;
import edu.ncsu.csc573.project.commlayer.Point;
import edu.ncsu.csc573.project.commlayer.Router;
import edu.ncsu.csc573.project.common.ByteOperationUtil;
import edu.ncsu.csc573.project.common.ConfigurationManager;
import edu.ncsu.csc573.project.common.schema.CommandRequestType;
import edu.ncsu.csc573.project.common.schema.FileParamType;
import edu.ncsu.csc573.project.common.schema.PublishType;
import edu.ncsu.csc573.project.common.schema.PublishTypeParams;
import edu.ncsu.csc573.project.common.schema.Request;
import edu.ncsu.csc573.project.controllayer.hashspacemanagement.DigestAdaptor;
import edu.ncsu.csc573.project.controllayer.hashspacemanagement.IDigest;

public class PublishRequestMessage extends RequestMessage {
	private PublishSearchParameter pubParam;
	private Logger logger;
	private String id;

	public IParameter getParameter() {
		return pubParam;
	}

	public void createRequest(EnumOperationType opType, IParameter parameter) {
		super.createRequest(opType, parameter);
		pubParam = (PublishSearchParameter) parameter;
	}

	public void setParameter(IParameter param) {
		if (param instanceof PublishSearchParameter) {
			pubParam = (PublishSearchParameter) param;
		} else {
			if (logger == null) {
				logger = Logger.getLogger(PublishRequestMessage.class);
			}
			logger.error("Invalid parameter");
		}
	}

	public String getRequestInXML() throws Exception {
		Request req = new Request();
		req.setId(id);
		CommandRequestType publish = new CommandRequestType();
		PublishType publishType = new PublishType();
		PublishTypeParams lpt = new PublishTypeParams();

		pubParam.resetCounter();

		while (pubParam.getParamCount() < pubParam.getSize()) {
			FileParamType fileParamType = new FileParamType();

			fileParamType.setFilename(pubParam.getParamValue(
					EnumParamsType.FILENAME).toString());
			fileParamType.setFiledigest(pubParam.getParamValue(
					EnumParamsType.FILEDIGEST).toString());
			fileParamType.setFilesize(pubParam.getParamValue(
					EnumParamsType.FILESIZE).toString());
			fileParamType.setAbstract(pubParam.getParamValue(
					EnumParamsType.ABSTRACT).toString());
			fileParamType.setIpaddress(pubParam.getParamValue(
					EnumParamsType.IPADDRESS).toString());
			pubParam.setNextParam();
			lpt.getFile().add(fileParamType);
		}

		publishType.setParams(lpt);
		publish.setPublish(publishType);
		req.setCommand(publish);

		return getXML(req);
	}

	public void parseXML(String XML) {
		logger = Logger.getLogger(RegisterRequestMessage.class);
		try {
			Request req = getRequest(XML);

			CommandRequestType command = req.getCommand();
			PublishType regType = command.getPublish();
			PublishTypeParams regparams = regType.getParams();
			List<FileParamType> fileList = regparams.getFile();
			IParameter param = new PublishSearchParameter();

			for (FileParamType fileParamType : fileList) {
				param.add(EnumParamsType.FILENAME, fileParamType.getFilename());
				param.add(EnumParamsType.FILEDIGEST, ByteOperationUtil
						.convertStringToBytes(fileParamType.getFiledigest()));
				param.add(EnumParamsType.FILESIZE, fileParamType.getFilesize());
				param.add(EnumParamsType.ABSTRACT, fileParamType.getAbstract());
				param.add(EnumParamsType.IPADDRESS,
						fileParamType.getIpaddress());
				param.add(EnumParamsType.DELIMITER, fileParamType.getId());
			}

			this.setOperationType(EnumOperationType.PUBLISH);
			this.setParameter(param);
		} catch (Exception e) {
			logger.error("Unable to parse request from string", e);
		}
	}

	public static void sendPublishRequest() throws Exception {
		List<IRequest> pubreqs = new ArrayList<IRequest>(0);
		
		Logger logger = Logger.getLogger(PublishRequestMessage.class);
		File pubDir = ConfigurationManager.getInstance().getPublishDirectory();

		FilenameFilter textFilter = new FilenameFilter() {

			public boolean accept(File dir, String name) {
				if (name.endsWith(".txt"))
					return true;
				else
					return false;
			}
		};
		IDigest digestUtil = null;

		try {
			digestUtil = DigestAdaptor.getInstance();
		} catch (Exception e) {
			logger.error("Unable to initialize digest utility", e);
			return ;
		}

		List<File> files = Arrays.asList(pubDir.listFiles(textFilter));
		//PublishRequest.setId();
		
		String localIPAddress = ConfigurationManager.getInstance()
				.getHostInterface();
		//*
		//try {
		//	localIPAddress = InetAddress.getLocalHost().getHostAddress();
//		} catch (UnknownHostException e) {
//			logger.error("Unable to get IPAddress of the host interface", e);
		//}
		//		
		/*(try {
			localIPAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			logger.error("Unable to get IPAddress of the host interface", e);
		}*/

		ICommunicationService commService = CommunicationServiceFactory.getInstance();
		
		for (File file : files) {
			IRequest putIRequest = new PutRequest();
			IParameter putParams = new Parameter();
			
			putParams.add(EnumParamsType.FILENAME, file.getName());
			byte[] filedigest = digestUtil.getDigest(file);
			putParams.add(EnumParamsType.FILEDIGEST, ByteOperationUtil
					.convertBytesToString(filedigest));
			putParams.add(EnumParamsType.FILESIZE,
					String.valueOf(file.length()));
			putParams.add(EnumParamsType.IPADDRESS, localIPAddress);
			putParams.add(EnumParamsType.ABSTRACT, getAbstract(file));
			
			putIRequest.createRequest(EnumOperationType.PUT, putParams);
			//pubreqs.add(putIRequest);
			commService.executeRequest(putIRequest, Router.getInstance().getNextHop(new Point(ByteOperationUtil.getCordinates(filedigest))));
		}
		
	}

	private static String getAbstract(File file) throws IOException {
		StringBuffer sb = new StringBuffer();

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			sb.append(br.readLine());
			// sb.append(System.lineSeparator());
			sb.append(br.readLine());
			// sb.append(System.lineSeparator());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = String.valueOf(id);
	}
	
	public PublishRequestMessage()  {
		id = ""+System.currentTimeMillis();
	}
}
