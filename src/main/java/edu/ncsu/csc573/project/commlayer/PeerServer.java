/**
 * 
 */
package edu.ncsu.csc573.project.commlayer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import edu.ncsu.csc573.project.common.ConfigurationManager;

/**
 * @author doogle-dev
 * 
 */
class PeerServer {
	private Thread serverThread;
	private ServerSocket peerServerSocket;
	private int serverPort;
	private Logger logger;
	private boolean isToBeStopped;

	public PeerServer() {
		logger = Logger.getLogger(this.getClass());
		serverPort = ConfigurationManager.getInstance().getServerPort();
		Runnable r = new Runnable() {
			public void run() {
				runServer();
			}
		};
		isToBeStopped = false;
		serverThread = new Thread(r);
		serverThread.start();
	}

	public void runServer() {
		Socket connSoc;
		logger.info("Launching server thread...");
		try {
			peerServerSocket = new ServerSocket(serverPort,
					ConfigurationManager.getInstance().getBackLogCount());
			while (!isToBeStopped) {
				logger.info("Waiting for clients to connet...");
				connSoc = peerServerSocket.accept();
				logger.info("Client connected: "
						+ connSoc.getRemoteSocketAddress());
				new Thread(new ClientHandler(connSoc)).start();
			}
		} catch (IOException e) {
			logger.error("Unable to launch server", e);
			logger.error("Closing server thread");
		} finally {
			try {
				peerServerSocket.close();
				logger.info("Server is stopped.");
			} catch (IOException e) {
				logger.error("Failed to close server socket");
			}
		}
	}

	public void stop() {
		logger.info("Server is going to stop");
		isToBeStopped = true;
	}

	public boolean isServerRunning() {
		return serverThread.isAlive();
	}
}