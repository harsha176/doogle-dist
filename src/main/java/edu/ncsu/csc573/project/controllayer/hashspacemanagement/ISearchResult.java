/**
 * 
 */
package edu.ncsu.csc573.project.controllayer.hashspacemanagement;

/**
 * This interface abstracts SearchResults returned by Hashspacemanagement.
 * @author doogle-dev
 *
 */
public interface ISearchResult {
	/**
	 * returns ID of the file
	 * @return
	 */
	public int getFileID();
	
	/**
	 * returns File name
	 * @return  
	 */
	public String getFileName();
	
	/**
	 * 
	 * @return Abstract of the file
	 */
	public String getFileAbstract();
	
	/**
	 * returns digest 
	 * @return 
	 */
	public String getDigest();
	
	/**
	 * returns address of the peer who has the file.
	 * @return
	 */
	public String getPeerAddress();
	
}
