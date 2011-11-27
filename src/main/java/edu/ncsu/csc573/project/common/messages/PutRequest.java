/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncsu.csc573.project.common.messages;

import edu.ncsu.csc573.project.common.ByteOperationUtil;
import edu.ncsu.csc573.project.common.schema.CommandRequestType;
import edu.ncsu.csc573.project.common.schema.PutType;
import edu.ncsu.csc573.project.common.schema.PutTypeParams;
import edu.ncsu.csc573.project.common.schema.Request;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import org.apache.log4j.Logger;

/**
 *
 * @author krishna
 */
public class PutRequest extends RequestMessage {

	public String getRequestInXML() throws Exception {
		Request req = new Request();
		req.setId(BigInteger.valueOf(System.currentTimeMillis()));
		CommandRequestType publish = new CommandRequestType();
		PutType publishType = new PutType();
		PutTypeParams lpt = new PutTypeParams();

			lpt.setFilename(getParameter().getParamValue(
					EnumParamsType.FILENAME).toString());
			lpt.setFiledigest(getParameter().getParamValue(
					EnumParamsType.FILEDIGEST).toString());
			lpt.setFilesize(getParameter().getParamValue(
					EnumParamsType.FILESIZE).toString());
			lpt.setAbstract(getParameter().getParamValue(
					EnumParamsType.ABSTRACT).toString());
			lpt.setIpaddress(getParameter().getParamValue(
					EnumParamsType.IPADDRESS).toString());

		publishType.setParams(lpt);
		publish.setPut(publishType);
		req.setCommand(publish);

		return getXML(req);
	}

	public void parseXML(String XML) {
		try {
			Request req = getRequest(XML);

			CommandRequestType command = req.getCommand();
			PutType regType = command.getPut();
			PutTypeParams regparams = regType.getParams();
			IParameter param = new Parameter();

			param.add(EnumParamsType.FILENAME, regparams.getFilename());
			param.add(EnumParamsType.FILEDIGEST, ByteOperationUtil
						.convertStringToBytes(regparams.getFiledigest()));
			param.add(EnumParamsType.FILESIZE, regparams.getFilesize());
			param.add(EnumParamsType.ABSTRACT, regparams.getAbstract());
			param.add(EnumParamsType.IPADDRESS,
						regparams.getIpaddress());
			

			this.setOperationType(EnumOperationType.PUT);
			this.setParameter(param);
		} catch (Exception e) {
			//logger.error("Unable to parse request from string", e);
		}
	}

/*	public static IRequest getPublishRequest() throws Exception {

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
			return null;
		}

		List<File> files = Arrays.asList(pubDir.listFiles(textFilter));
		IRequest PublishRequest = new PublishRequestMessage();
		PublishSearchParameter publishParams = new PublishSearchParameter();
		String localIPAddress = ConfigurationManager.getInstance()
				.getHostInterface();

		try {
			localIPAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			logger.error("Unable to get IPAddress of the host interface", e);
		}
		for (File file : files) {

			publishParams.add(EnumParamsType.FILENAME, file.getName());
			publishParams.add(EnumParamsType.FILEDIGEST, ByteOperationUtil
					.convertBytesToString(digestUtil.getDigest(file)));
			publishParams.add(EnumParamsType.FILESIZE,
					String.valueOf(file.length()));
			publishParams.add(EnumParamsType.IPADDRESS, localIPAddress);
			publishParams.add(EnumParamsType.ABSTRACT, getAbstract(file));
			publishParams.add(EnumParamsType.DELIMITER, null);

		}
		PublishRequest.createRequest(EnumOperationType.PUBLISH, publishParams);
		return PublishRequest;
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
*/    
}
