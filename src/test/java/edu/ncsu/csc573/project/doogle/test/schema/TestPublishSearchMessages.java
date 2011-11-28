package edu.ncsu.csc573.project.doogle.test.schema;

import edu.ncsu.csc573.project.common.ByteOperationUtil;
import edu.ncsu.csc573.project.common.ConfigurationManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import edu.ncsu.csc573.project.common.messages.EnumOperationType;
import edu.ncsu.csc573.project.common.messages.EnumParamsType;
import edu.ncsu.csc573.project.common.messages.IRequest;
import edu.ncsu.csc573.project.common.messages.PublishRequestMessage;
import edu.ncsu.csc573.project.common.messages.PublishSearchParameter;
import edu.ncsu.csc573.project.controllayer.hashspacemanagement.DigestAdaptor;
import edu.ncsu.csc573.project.controllayer.hashspacemanagement.IDigest;

public class TestPublishSearchMessages {
	/*
	@Test
	public void testPublishRequestMessageToXML() {
		
		try {
			IRequest PublishRequest = testPublishFolderRequest();
			System.out.println(PublishRequest.getRequestInXML());
			Assert.assertTrue("Successfully parsed xml", true);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertFalse(false);
			// e.printStackTrace();
		}
	}

	/*
	 * private IRequest getPublishRequest() { IRequest PublishRequest = new
	 * PublishRequestMessage(); PublishSearchParameter publishParams = new
	 * PublishSearchParameter();
	 * 
	 * publishParams.add(EnumParamsType.FILENAME, "abc.txt");
	 * publishParams.add(EnumParamsType.FILEDIGEST, "0847");
	 * publishParams.add(EnumParamsType.FILESIZE, "20");
	 * publishParams.add(EnumParamsType.IPADDRESS, "10.12.2.1");
	 * publishParams.add(EnumParamsType.ABSTRACT, "This is a sample file");
	 * publishParams.add(EnumParamsType.DELIMITER, null);
	 * 
	 * publishParams.add(EnumParamsType.FILENAME, "abc.txt");
	 * publishParams.add(EnumParamsType.FILEDIGEST, "0847");
	 * publishParams.add(EnumParamsType.FILESIZE, "20");
	 * publishParams.add(EnumParamsType.IPADDRESS, "10.12.2.1");
	 * publishParams.add(EnumParamsType.ABSTRACT, "This is a sample file");
	 * publishParams.add(EnumParamsType.DELIMITER, null);
	 * 
	 * publishParams.add(EnumParamsType.FILENAME, "abc.txt");
	 * publishParams.add(EnumParamsType.FILEDIGEST, "0847");
	 * publishParams.add(EnumParamsType.FILESIZE, "20");
	 * publishParams.add(EnumParamsType.IPADDRESS, "10.12.2.1");
	 * publishParams.add(EnumParamsType.ABSTRACT, "This is a sample file");
	 * publishParams.add(EnumParamsType.DELIMITER, null);
	 * 
	 * publishParams.add(EnumParamsType.FILENAME, "abc.txt");
	 * publishParams.add(EnumParamsType.FILEDIGEST, "0847");
	 * publishParams.add(EnumParamsType.FILESIZE, "20");
	 * publishParams.add(EnumParamsType.IPADDRESS, "10.12.2.1");
	 * publishParams.add(EnumParamsType.ABSTRACT, "This is a sample file");
	 * publishParams.add(EnumParamsType.DELIMITER, null);
	 * 
	 * PublishRequest.createRequest(EnumOperationType.PUBLISH, publishParams);
	 * return PublishRequest; }
	 */
	/*
	private IRequest testPublishFolderRequest() throws Exception {
		URL url = ClassLoader.getSystemResource("samplePublishFolder");
		Assert.assertNotNull(url);
		System.out.println(url.getFile());
		File pubDir = new File(url.getFile());
		// File pubDir =
		// ConfigurationManager.getInstance().getPublishDirectory();
		Assert.assertEquals(pubDir.isDirectory(), true);
		FilenameFilter textFilter = new FilenameFilter() {

			public boolean accept(File dir, String name) {
				if (name.endsWith(".txt"))
					return true;
				else
					return false;
			}
		};
		List<File> files = Arrays.asList(pubDir.listFiles(textFilter));
		IRequest PublishRequest = new PublishRequestMessage();
		PublishSearchParameter publishParams = new PublishSearchParameter();
		String localIPAddress = ConfigurationManager.getInstance().getHostInterface();
		try {
			localIPAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		IDigest digestUtil = null;

		try {
			digestUtil = DigestAdaptor.getInstance();
		} catch (Exception e) {
			//logger.error("Unable to initialize digest utility", e);
			return null;
		}
		for (File file : files) {

			publishParams.add(EnumParamsType.FILENAME, file.getName());
			publishParams.add(EnumParamsType.FILEDIGEST,
					ByteOperationUtil.convertBytesToString(digestUtil.getDigest(file)));
			publishParams.add(EnumParamsType.FILESIZE,
					String.valueOf(file.length()));
			publishParams.add(EnumParamsType.IPADDRESS, localIPAddress);
			publishParams.add(EnumParamsType.ABSTRACT, getAbstract(file));
			publishParams.add(EnumParamsType.DELIMITER, null);

		}
		PublishRequest.createRequest(EnumOperationType.PUBLISH, publishParams);
		return PublishRequest;
	}

	public String getAbstract(File file) {
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

	/*
	 * @Test public void testPublishRequestMessageParseXML() { InputStream is =
	 * ClassLoader .getSystemResourceAsStream("TestPublishRequest.xml");
	 * BufferedReader br = new BufferedReader(new InputStreamReader(is));
	 * 
	 * StringBuffer sb = new StringBuffer(); String temp; try { while ((temp =
	 * br.readLine()) != null) { sb.append(temp); } } catch (IOException e) {
	 * e.printStackTrace(); Assert.fail(); }
	 * 
	 * RequestMessage publishReq = new PublishRequestMessage();
	 * publishReq.parseXML(sb.toString());
	 * Assert.assertEquals(EnumOperationType.PUBLISH,
	 * publishReq.getOperationType()); PublishSearchParameter pubSearchParam =
	 * (PublishSearchParameter) publishReq.getParameter();
	 * pubSearchParam.resetCounter(); int i = 0;
	 * //while(pubSearchParam.getParamCount() < pubSearchParam.getSize()) {
	 * Assert
	 * .assertEquals(pubSearchParam.getParamValue(EnumParamsType.DELIMITER), i);
	 * Assert
	 * .assertEquals(pubSearchParam.getParamValue(EnumParamsType.FILENAME),
	 * "abc.txt"); pubSearchParam.setNextParam();
	 * Assert.assertEquals(pubSearchParam
	 * .getParamValue(EnumParamsType.DELIMITER), 1);
	 * Assert.assertEquals(pubSearchParam
	 * .getParamValue(EnumParamsType.FILENAME), "xyz.txt");
	 * pubSearchParam.setNextParam();
	 * Assert.assertEquals(pubSearchParam.getParamValue
	 * (EnumParamsType.DELIMITER), 2);
	 * Assert.assertEquals(pubSearchParam.getParamValue
	 * (EnumParamsType.IPADDRESS), "10.12.2.4");
	 * 
	 * }
	 */

}
