/**
 * 
 */
package edu.ncsu.csc573.project.commlayer;

import java.io.File;

/**
 * This interface provides a callback function whenever a it receives a publish
 * request from other peer.
 * 
 * @author Harshavardhan
 * 
 */
public interface IPublishHandler {

	/**
	 * This method retrieves the file to be uploaded when a download request is
	 * received from other peer.
	 * 
	 * @param fileName
	 *            requested file
	 * @return File file to be uploaded.
	 */
	public File getFileToUpload(String fileName);
}
