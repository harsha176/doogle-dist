package edu.ncsu.csc573.project.controllayer.hashspacemanagement;

import edu.ncsu.csc573.project.common.ByteOperationUtil;
/**
 * This class is default implementation of query interface.
 * 
 * This class helps in converting a query digest to encoded string.
 * @author doogle-dev
 *
 */
public class Query implements IQuery{
	/**
	 * digest value stored in raw bytes.
	 */
	private byte[] digest;
	
	public byte[] getQueryDigest() {
		return digest;
	}
	
	public Query(byte[] digest) {
		this.digest = digest;
	}
	
	public Query(String encDigest) {
		digest = ByteOperationUtil.convertStringToBytes(encDigest);
	}
}
