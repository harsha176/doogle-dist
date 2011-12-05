package edu.ncsu.csc573.project.controllayer.hashspacemanagement;

import edu.ncsu.csc573.project.common.ByteOperationUtil;
import edu.ncsu.csc573.project.common.ConfigurationManager;

/**
 * This class implements default matching test. It calculates match factor,
 * number of bits match in query and result of and operation of digest. If match
 * factory exceeds certain threshold value only then it calls it a match.
 * 
 * @see IMatcher
 * @author doogle-dev
 * 
 */
public class DefaultMatcher implements IMatcher {
	/**
	 * Threshold value fetched from configuration file
	 */
	private static final double THRESHOLD = ConfigurationManager.getInstance().getThresholdValue();
	private double matchFactor;

	
	/**
	 * This method checks if the query digest matches other one.
	 * It uses configured THRESHOLD_VALUE as a parameter for match.
	 * 
	 * return true if it matches
	 */
	public boolean isMatches(byte[] query, byte[] other) {
		//matchFactor = new Double(0);
		byte[] result = ByteOperationUtil.and(query, other);
		
		int querySetBits = ByteOperationUtil.countSetBits(query);
		int resultSetBits = ByteOperationUtil.countSetBits(result);
		matchFactor = ((resultSetBits * 1.0) / querySetBits);
		if (matchFactor >= THRESHOLD) {
			return true;
		} else {
			return false;
		}
	}

	public double getMatchFactor() {
		return matchFactor;
	}
}
