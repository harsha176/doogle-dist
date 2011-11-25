package edu.ncsu.csc573.project.controllayer.hashspacemanagement;

import java.io.File;

/**
 * This provides an interface for performing digest calculation.
 * @author doogle-dev
 *
 */
public interface IDigest {
	
	/**
	 * This function calculates digest for the given file
	 * 
	 * @param file
	 * @return digest of the file
	 */
	public byte[] getDigest(File file) throws Exception;
	
	/**
	 * This function calculates digest for the given query
	 * 
	 * @param query
	 * @return
	 */
	public byte[] getDigest(String query) throws Exception;
}
