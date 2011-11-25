package edu.ncsu.csc573.project.controllayer.hashspacemanagement;

/**
 * This class is used to configure any of the various implementations of HashSpaceManager.
 * 
 * @see IHashSpaceManager
 * @author doogle-dev
 *
 */
public class HashSpaceManagerFactory {
	private static IHashSpaceManager hashmanager= null;
	
	/**
	 * This method is called by control layer to get an instance of HashSpaceManager
	 * @return HashSpaceManager instance
	 */
	public static synchronized IHashSpaceManager getInstance() {
		if(hashmanager == null) {
			/*
			 * This is where appropriate implementation of IHashSpaceManager is configured.
			 */
			hashmanager = new HashSpaceManager();    // using default HashSpaceManager
		}
		return hashmanager;
	}
}
