package edu.ncsu.csc573.project.commlayer;

import java.io.File;

import edu.ncsu.csc573.project.common.messages.IRequest;
import edu.ncsu.csc573.project.common.messages.IResponse;

/**
 * This interface provides communication services for peer/BS control layer.
 * 
 * @author Harshavardhan
 * 
 */
public interface ICommunicationService {
	/**
	 * This method initializes communication server data structures.
	 * 
	 * Precondition: none Postcondition: if able to connect to BootStrapServer
	 * then Communication layer is initialized.
	 * 
	 * @param BootStrapServer
	 *            the bootstrap server details.
	 * @param PublishHandeler
	 *            for handling file download requests from peer.
	 * @param RequestQueue
	 * @param ResponseQueue
	 */
	public void initialize(IPublishHandler aPublishHandler) throws Exception;

	/**
	 * Checks if the communication server is connected to bootstrap server.
	 * 
	 * @return
	 */
	public boolean isConnected();

	/**
	 * This method allows peer to send a request to BootStrap server and get
	 * response from the server.
	 * 
	 * This is a blocking call and is generally used by peers.
	 * 
	 * Precondition: none Postcondition: if called with valid request and server
	 * is up then a valid response object is returned.
	 * 
	 * @param request
	 *            object to be sent
	 * @return response object from BootStrapServer
	 * @throws Exception
	 * 
	 * @see IRequest
	 * @see IResponse
	 */
	public IResponse executeRequest(IRequest request, String ipaddress)
			throws Exception;

	/**
	 * This method retrieves the file from the remote server
	 * 
	 * @param IPAddress
	 * @param fileName
	 * @return
	 */
	public File getFile(String IPAddress, String fileName);

	/**
	 * This method checks if the peer server is running.
	 * 
	 * @return true if it is running otherwise false
	 */
	public boolean isPeerServerRunning();

	/**
	 * This method fetches file from the server
	 * 
	 * @param fileName
	 * @return
	 */
	public File getFileToUpload(String fileName);

	/**
	 * This method closes the communication layer ports and releases all the
	 * data structures including threads in thread pool.
	 * 
	 * 
	 */
	public void close() throws Exception;
}
