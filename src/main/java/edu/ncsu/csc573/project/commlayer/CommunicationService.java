/**
 * 
 */
package edu.ncsu.csc573.project.commlayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import edu.ncsu.csc573.project.common.ConfigurationManager;
import edu.ncsu.csc573.project.common.messages.IRequest;
import edu.ncsu.csc573.project.common.messages.IResponse;
import edu.ncsu.csc573.project.controllayer.ConcurrentQueueManagement;
import edu.ncsu.csc573.project.controllayer.ResponseProcessor;

/**
 * @author doogle-dev
 * 
 */
public class CommunicationService implements ICommunicationService {

	// private Socket clientSocket;
	//private InetAddress BSSAddress;
	private static Logger logger;
	private PeerServer server;
	private IPublishHandler publishHandler;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.ncsu.csc573.project.commlayer.ICommunicationService#initialize(java
	 * .lang.String, edu.ncsu.csc573.project.commlayer.IPublishHandler)
	 */
	public void initialize(/*String BootStrapServer,*/
			IPublishHandler aPublishHandler) throws Exception {
		// get a logger instance for this class
		logger = Logger.getLogger(this.getClass());
		logger.debug("Inside initialization of communication handler");

		publishHandler = aPublishHandler;

		/*
		 * if the socket is already connected then initialization is already
		 * done and so simply exiting.
		 */
		synchronized (CommunicationService.class) {
			if (server != null && server.isServerRunning()) {
				logger.debug("Already connected to bootstrapserver");
				logger.info("Already initialized");
				return;
			} else {
				logger.info("Start server");
				server = new PeerServer();
			}
		}

	}

	protected Socket initializeConnectedSocket(String peerAddress)
			throws Exception, UnknownHostException, SocketException,
			SocketTimeoutException, IOException {
		Socket socket = new Socket();
		InetAddress BSSAddress = getInetAddress(peerAddress);

		int BSSport = ConfigurationManager.getInstance().getServerPort();
		int timeOut = ConfigurationManager.getInstance().getTimeOut();

		socket = new Socket();
		InetSocketAddress serverSocket = new InetSocketAddress(BSSAddress,
				BSSport);
		socket.setKeepAlive(true);
		socket.setTcpNoDelay(true);

		logger.debug("Enabled Keepalive socket option");
		try {
			socket.connect(serverSocket, timeOut);
		} catch (SocketTimeoutException e) {
			logger.error("Connection timed out", e);
			cleanUp();
			throw e;
		} catch (IOException exp) {
			logger.error("Connection refused", exp);
			cleanUp();
			throw exp;
		}
		return socket;
	}

	private InetAddress getInetAddress(String peerIP) throws Exception,
			UnknownHostException {
		InetAddress address = null;
		try {
			address = InetAddress.getByName(peerIP);
		} catch (UnknownHostException excpByHostName) {
			try {
				logger.info("Unable to find the address of the host: " + peerIP
						+ " by name");
				logger.info("Trying by ip address");
				address = InetAddress.getByAddress(peerIP.trim().getBytes());
			} catch (UnknownHostException excpByIpAddress) {
				logger.info("Unable to find the address of host: " + peerIP
						+ " even by ip address");
				cleanUp();
				throw excpByIpAddress;
			}
		}
		return address;
	}

	private void cleanUp() throws Exception {

		if (server != null) {
			server.stop();
		}
		while (!server.isServerRunning()) {
			logger.info("Waiting for peer server to close");
			Thread.sleep(100);
		}
		logger.error("Unable to initialize Communication layer. Exiting from Application");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.ncsu.csc573.project.commlayer.ICommunicationService#executeRequest
	 * (edu.ncsu.csc573.project.common.messages.IRequest)
	 */
	public IResponse executeRequest(IRequest request, String peerIP)
			throws Exception {
		Socket clientSocket = initializeConnectedSocket(peerIP);
		BlockingThread bt = new BlockingThread(clientSocket, request, "Op: "
				+ request.getOperationType() + " req thread ");
		bt.start();
		try {
			bt.join(ConfigurationManager.getInstance().getCLITimeOut());
			bt.stopListener();
			if (!bt.isResponseReady()) {
				logger.info("Failed to get response for the request : "
						+ request.getOperationType());
				throw new Exception();
			} else {
				logger.info("Received response : " + bt.getResponse());
			}
		} catch (InterruptedException e) {

		} finally {
			// CHANGE IT: this comment will not work for centralized system.
			// if (bt.getResponse().getOperationType() ==
			// EnumOperationType.LOGOUTRESPONSE) {
			clientSocket.close();
			// }
		}
		return bt.getResponse();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.ncsu.csc573.project.commlayer.ICommunicationService#close()
	 */
	public void close() throws Exception {
		server.stop();
		while (server.isServerRunning()) {
			Thread.sleep(100);
		}
	}

	@Deprecated
	public boolean isConnected() {
		return this.isPeerServerRunning();
	}

	class BlockingThread extends Thread {
		private Socket clientSocket;
		private IRequest request;
		private IResponse response = null;
		private boolean isToBeStopped = false;
		private ResponseProcessor respProcessor = null;
		private String remoteIP;
		
		BlockingThread(Socket clientSocket, IRequest request, String name) {
			super(name);
			this.clientSocket = clientSocket;
			this.request = request;
			respProcessor = ResponseProcessor.getInstance();
			if(clientSocket.getRemoteSocketAddress() instanceof InetSocketAddress) {
				 remoteIP = ((InetSocketAddress)clientSocket.getRemoteSocketAddress()).getAddress().getHostAddress();
				 logger.debug("Remote peer ip is : " + remoteIP);
			}
		}

		public void run() {

			try {
				/**
				 * Request has to be transformed from IRequest to request object
				 */
				PrintWriter pw = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(clientSocket.getOutputStream())));
				pw.println(request.getRequestInXML());
				// pw.println(System.);
				pw.flush();

				logger.info("Sent resquest to server " + clientSocket);
				ConcurrentQueueManagement queueManager = ConcurrentQueueManagement
						.getInstance();
				String requestId = request.getId();
				queueManager.putRequest(requestId, request);

				logger.info("Waiting for response...");
				while (!queueManager.isResponseReceived(requestId) && !isToBeStopped) {
					Thread.sleep(100);
				}

				logger.info("Received response ");
				response = queueManager.getResponse(requestId);
				logger.info("Response is : " + response.getRequestInXML());
				respProcessor.processResponse(response, remoteIP);
			} catch (Exception e) {
				logger.error("Unable to parse response ", e);
			}
		}

		public IResponse getResponse() {
			return response;
		}

		public boolean isResponseReady() {
			return (response == null ? false : true);
		}

		public void stopListener() {
			isToBeStopped = true;
		}
	}

	public boolean isPeerServerRunning() {
		return server.isServerRunning();
	}

	public File getFile(String IPAddress, String fileName) {
		PrintWriter pw = null;
		Socket ftSoc = null;
		BufferedReader br = null;
		PrintWriter pwFile = null;
		File newFile = null;

		try {
			ftSoc = new Socket(getInetAddress(IPAddress), ConfigurationManager
					.getInstance().getFileTransferPort());
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					ftSoc.getOutputStream())));
			logger.debug("Successfully opened socket for file transfer");

			pw.println("<request>");
			pw.println("File:" + fileName);
			pw.println("</request>");
			pw.flush();
			logger.debug("Sent request " + "File:" + fileName);
			br = new BufferedReader(new InputStreamReader(
					ftSoc.getInputStream()));
			String buff;
			newFile = new File(ConfigurationManager.getInstance()
					.getDownloadDirectory(), fileName.substring(0,
					fileName.indexOf("."))/* +"_"+System.currentTimeMillis() */
					+ ".txt");

			pwFile = new PrintWriter(
					new BufferedWriter(new FileWriter(newFile)));

			while ((buff = br.readLine()) != null) {
				pwFile.println(buff);
				pwFile.flush();
				logger.trace("Read: " + buff);
			}
			pwFile.flush();
			logger.info("Successfully saved downloaded file at "
					+ newFile.getAbsolutePath());
			pw.println("a");
			pw.flush();
		} catch (UnknownHostException e) {
			logger.error("Unable to find host", e);
		} catch (IOException e) {
			logger.error("Unable to perform IO", e);
		} catch (Exception e) {
			logger.error("Unable to get file", e);
		} finally {
			if (ftSoc != null) {
				try {
					ftSoc.close();
				} catch (IOException e) {
					logger.error("Unable to close socket", e);
				}
			}
			if (pw != null)
				pw.close();
			if (pwFile != null)
				pwFile.close();
		}
		return newFile;
	}

	public File getFileToUpload(String fileName) {
		if (publishHandler != null) {
			return publishHandler.getFileToUpload(fileName);
		} else {
			logger.error("PublishHandler is not initialized");
			return null;
		}
	}
}
