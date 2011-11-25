/**
 * 
 */
package edu.ncsu.csc573.project.controllayer.hashspacemanagement;

import org.apache.log4j.Logger;

import edu.ncsu.csc573.project.common.messages.EnumParamsType;
import edu.ncsu.csc573.project.common.messages.PublishRequestMessage;
import edu.ncsu.csc573.project.common.messages.PublishSearchParameter;

/**
 * This class is a default implementation of HashSpaceManager.
 * 
 * It does'nt include any sophisticated data structures and algorithms.
 * 
 * @author doogle-dev
 * @see PublishSearchParameter
 * @see IMatcher
 * 
 */
public class HashSpaceManager implements IHashSpaceManager {
	/**
	 * This data structure is used to maintain all the published files to the
	 * server.
	 */
	private PublishSearchParameter publishedFilesRepository;
	/**
	 * This is used to match files during search.
	 */
	private IMatcher matcher;
	/**
	 * logger instance used for logging events and errors
	 */
	private Logger logger;

	/**
	 * Initialize published file repository data structure and matcher
	 */
	public HashSpaceManager() {
		publishedFilesRepository = new PublishSearchParameter();
		matcher = new DefaultMatcher();
		logger = Logger.getLogger(HashSpaceManager.class);
	}

	/**
	 * This method performs search and based on the query search results are
	 * returned.
	 * 
	 * @return search results in PublishSearchParameter data structure
	 */
	public synchronized PublishSearchParameter search(IQuery query) {

		PublishSearchParameter searchResults = new PublishSearchParameter();

		// Iterate through publishedFilesRepository and uses matcher to see if a
		// query matches any of the file digest in publishedFilesRemository
		publishedFilesRepository.resetCounter(); // reset publishedFilesRepository counter
		while (publishedFilesRepository.getParamCount() < publishedFilesRepository
				.getSize()) {
			// obtain digest of each file in repository
			byte[] digest = (byte[]) publishedFilesRepository
					.getParamValue(EnumParamsType.FILEDIGEST);
			// check if it matches the query if so add them to search results.
			if (matcher.isMatches(query.getQueryDigest(), digest)) {
				searchResults.add(EnumParamsType.FILENAME,
						publishedFilesRepository
								.getParamValue(EnumParamsType.FILENAME));
				searchResults.add(EnumParamsType.FILEDIGEST,
						publishedFilesRepository
								.getParamValue(EnumParamsType.FILEDIGEST));
				searchResults.add(EnumParamsType.FILESIZE,
						publishedFilesRepository
								.getParamValue(EnumParamsType.FILESIZE));
				searchResults.add(EnumParamsType.IPADDRESS,
						publishedFilesRepository
								.getParamValue(EnumParamsType.IPADDRESS));
				searchResults.add(EnumParamsType.ABSTRACT,
						publishedFilesRepository
								.getParamValue(EnumParamsType.ABSTRACT));
				searchResults.add(EnumParamsType.DELIMITER, null);
			}
			// go the next entry in the repository
			publishedFilesRepository.setNextParam();
		}
		logger.info("Number of search results obtained on search is " + searchResults.getSize());
		return searchResults;
	}

	/**
	 * This method simply adds all the published files from server to published file repository 
	 */
	public synchronized void handlePublishRequest(
			PublishRequestMessage pubRequest) {
		PublishSearchParameter publishedFilesParam = (PublishSearchParameter) pubRequest
						.getParameter();
		publishedFilesRepository.add(publishedFilesParam);
		logger.info("Added "  + publishedFilesParam.getSize()+ " published files into database");
		logger.info("Total size of repository is " + publishedFilesRepository.getSize());
	}

}
