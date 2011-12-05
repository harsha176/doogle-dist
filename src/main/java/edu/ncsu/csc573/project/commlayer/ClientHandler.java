/**
 * 
 */
package edu.ncsu.csc573.project.commlayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.omg.Dynamic.Parameter;

import edu.ncsu.csc573.project.common.ByteOperationUtil;
import edu.ncsu.csc573.project.common.ConfigurationManager;
import edu.ncsu.csc573.project.common.messages.DownloadUpdateRequest;
import edu.ncsu.csc573.project.common.messages.EnumOperationType;
import edu.ncsu.csc573.project.common.messages.EnumParamsType;
import edu.ncsu.csc573.project.common.messages.IParameter;
import edu.ncsu.csc573.project.common.messages.IRequest;
import edu.ncsu.csc573.project.common.messages.IResponse;
import edu.ncsu.csc573.project.common.messages.RequestMessage;
import edu.ncsu.csc573.project.common.messages.ResponseMessage;
import edu.ncsu.csc573.project.common.schema.Request;
import edu.ncsu.csc573.project.controllayer.ConcurrentQueueManagement;
import edu.ncsu.csc573.project.controllayer.RequestProcessor;
import edu.ncsu.csc573.project.controllayer.hashspacemanagement.Digest;
import edu.ncsu.csc573.project.controllayer.hashspacemanagement.DigestAdaptor;

/**
 * This class handles individual client request.
 * 
 * @author doogle-dev
 * 
 */
public class ClientHandler implements Runnable {
	private Socket conncetedSocket;
	private Logger logger;
	// private ICommunicationService distCommService;
	private String remoteIP;

	public ClientHandler(Socket connSock) {
		logger = Logger.getLogger(ClientHandler.class);
		conncetedSocket = connSock;
		if (conncetedSocket.getRemoteSocketAddress() instanceof InetSocketAddress) {
			remoteIP = ((InetSocketAddress) conncetedSocket
					.getRemoteSocketAddress()).getAddress().getHostAddress();
			logger.debug("Remote peer ip is : " + remoteIP);
		} else {
			logger.error("Failed to get remote peer ip address of "
					+ conncetedSocket);
		}
		// distCommService = CommunicationServiceFactory.getInstance();
	}

	public void run() {
		// SocketAddress clientAddress = remoteSocketAddress;
		// RequestProcessor reqProcessor = RequestProcessor.getInstance();
		logger.info("Handling client ");

		boolean isFileTransfer = false;
		// Expect register or login request from the client
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					conncetedSocket.getInputStream()));

			// read each request and see till it logout
			// sleep till the data is ready from the client
			IRequest req = null;
			IResponse response = null;
			IRequest forwardReqOrResp = null;

			logger.debug("Waiting for requests... ");
			while (!br.ready()) {
				// logger.debug("Waiting for request from client "
				// + conncetedSocket.getRemoteSocketAddress());
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					logger.error("Unexpected error from client ");
				}
			}
			logger.debug("Receiving request from client");

			StringBuffer sb = new StringBuffer();
			int c;
			while ((c = br.read()) != -1 && sb.indexOf("</Request>") == -1
					&& sb.indexOf("</Response>") == -1) {
				// logger.debug(c);
				sb.append((char) c);
			}

			logger.debug("received data from client " + sb.toString());

			if (c == -1) {
				logger.info("Remote peer closed connection");
				return;
			}

			/*
			 * Handle file requests
			 */
			if (sb.indexOf("FileDownload") != -1) {
				File toBeUploadedFile;
				try {
					toBeUploadedFile = new File(ConfigurationManager
							.getInstance().getPublishDirectory(), getFileName(sb));
					transferFile(conncetedSocket.getOutputStream(),
							toBeUploadedFile);
					sendDownloadUpdate(toBeUploadedFile);
				} catch (Exception e) {
					logger.error("Unable to upload file", e);
				}
				/*
				 * send update
				 */
				// br.read();
				return;
			}

			/*
			 * Handle response
			 */
			if (sb.indexOf("</Response>") != -1) {
				IResponse resp;
				try {
					resp = ResponseMessage.createResponse(sb.toString().trim());
				} catch (Exception e) {
					logger.error("Received invalid request or response "
							+ sb.toString());
					return;
				}
				try {
					logger.info("Response from peer is : "
							+ resp.getRequestInXML());
				} catch (Exception e1) {
					logger.error("Unable to parse response : "
							+ resp.getOperationType());
					return;
				}
				/*
				 * update response
				 */
				try {
					ConcurrentQueueManagement.getInstance().updateResponse(
							resp.getId(), resp);
				} catch (Exception e) {
					logger.error(
							"Received response is not appropriate for this client ",
							e);
					return;
				}
				logger.info("Successfully updated response");
				return;
			}

			/*
			 * Handle request
			 */
			if (sb.indexOf("</Request>") != -1) {
				try {
					req = RequestMessage.createRequest(sb.toString().trim());
				} catch (Exception e) {
					logger.error(
							"Received invalid request or response "
									+ sb.toString(), e);
					return;
				}
				try {
					logger.info("Resquest from peer is : "
							+ req.getRequestInXML());
				} catch (Exception e) {
					logger.error("Unable to parse request", e);
					return;
				}

				MessageDetails md = RequestProcessor.getInstance()
						.processRequest(req, remoteIP);
				PrintWriter pw = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(new Socket(md.getIPAddress(),
								ConfigurationManager.getInstance()
										.getServerPort()).getOutputStream())));
				forwardReqOrResp = md.getRequest();
				try {
					pw.println(forwardReqOrResp.getRequestInXML());
				} catch (Exception e) {
					logger.error(
							"Received invalid response or request from Request processor "
									+ forwardReqOrResp.getOperationType(), e);
				}
				pw.flush();
				try {
					logger.debug("Successfully sent response: "
							+ forwardReqOrResp.getRequestInXML());
				} catch (Exception e) {
					logger.error(
							"Failed to parse response "
									+ forwardReqOrResp.getOperationType(), e);
				}
			}
		} catch (IOException e) {
			logger.error("Failed to read data from the client ", e);
		} finally {
			String connSockAddr = conncetedSocket.toString();
			if (conncetedSocket != null && !isFileTransfer) {
				try {
					conncetedSocket.close();
					logger.info("Successfully closed connection for client "
							+ connSockAddr);
				} catch (IOException e) {
					logger.error("Failed to close ", e);
				}
			}
		}
	}

	private void sendDownloadUpdate(File toBeUploadedFile) {
		logger.info("Sending file download update request for file : " + toBeUploadedFile.getName());
		IRequest downloadUpdateRequest = new DownloadUpdateRequest();
		IParameter param = new edu.ncsu.csc573.project.common.messages.Parameter();
		param.add(EnumParamsType.FILENAME, toBeUploadedFile.getName());
		
		try {
			byte[] digest = DigestAdaptor.getInstance().getDigest(toBeUploadedFile);
			IPoint dest = new Point(ByteOperationUtil.getCordinates(digest));
			logger.debug("File coordinates in hashspace is : " + dest.toString());
			
			param.add(EnumParamsType.FILEDIGEST, ByteOperationUtil.convertBytesToString(digest));
			downloadUpdateRequest.createRequest(EnumOperationType.DOWNLOADUPDATE, param);
			String destIP = Router.getInstance().getNextHop(dest);
			logger.debug("Destination IP address to send download file update request is " + destIP);
			IResponse resp = CommunicationServiceFactory.getInstance().executeRequest(downloadUpdateRequest, destIP);
			logger.debug("Received ACK response " + resp.getRequestInXML());
		} catch (Exception e) {
			logger.error("Failed to send download update request" ,e);
		}
		
		
	}

	private void transferFile(OutputStream os, File toBeUploadedFile) {
		PrintWriter pw = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(os)));
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(toBeUploadedFile));
			String temp;
			while ((temp = br.readLine()) != null) {
				pw.println(temp);
				pw.flush();
			}
			pw.flush();
			logger.info("Done ! Sending file contents");
		} catch (FileNotFoundException e) {
			logger.error("Unable to find file: " + toBeUploadedFile, e);
		} catch (IOException e) {
			logger.error("Unable to read file: " + toBeUploadedFile, e);
		} finally {
			if (pw != null) {
				pw.close();
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					logger.error("Unable to close input stream reader", e);
				}
			}
		}

		logger.info("Successfully transfered file");
	}

	public static String getFileName(StringBuffer sb) throws Exception {
		Request req = RequestMessage.getRequestFromGenXML(sb.toString());
		return req.getCommand().getFileDownload().getParams().getFileName();
	}
}