/**
 * 
 */
package edu.ncsu.csc573.project.controllayer.hashspacemanagement;

/**
 * This interface abstracts the notion of query.
 * 
 * @author doogle-dev
 *
 */
public interface IQuery {
	/**
	 * This method returns the digest of the query.
	 * @return trigram of the request.
	 */
	public byte[] getQueryDigest();
	
	/**
	 * This 
	 * @return
	 */
	public int[] getCordinates();
}
