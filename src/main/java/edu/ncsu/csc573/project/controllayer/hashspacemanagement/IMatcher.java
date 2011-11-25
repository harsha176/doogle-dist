package edu.ncsu.csc573.project.controllayer.hashspacemanagement;

public interface IMatcher {
	/**
	 * Any matcher should implement this this interface to define their own logic for match
	 * @param query
	 * @param other
	 * @return
	 */
	public boolean isMatches(byte[] query, byte[] other);
}
