/**
 * 
 */
package edu.ncsu.csc573.project.controllayer;

import java.io.File;

/**
 * This interface abstracts settings configured by user.
 * @author doogle-dev
 *
 */
public interface ISettings {
	/**
	 * This method returns the folder published by the user.
	 * @return File object of the folder.
	 */
	public File getPublishedFolder();
}
