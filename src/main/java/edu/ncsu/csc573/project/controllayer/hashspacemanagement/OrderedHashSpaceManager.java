package edu.ncsu.csc573.project.controllayer.hashspacemanagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.log4j.Logger;
import edu.ncsu.csc573.project.commlayer.IPoint;
import edu.ncsu.csc573.project.commlayer.IZone;
import edu.ncsu.csc573.project.commlayer.Point;
import edu.ncsu.csc573.project.common.ByteOperationUtil;
import edu.ncsu.csc573.project.common.ConfigurationManager;
import edu.ncsu.csc573.project.common.schema.DownloadFileParamType;
import edu.ncsu.csc573.project.common.messages.EnumParamsType;
import edu.ncsu.csc573.project.common.messages.PublishRequestMessage;
import edu.ncsu.csc573.project.common.messages.PutRequest;
import edu.ncsu.csc573.project.common.schema.MatchFileParamType;

public class OrderedHashSpaceManager {

	private static OrderedHashSpaceManager instance;
	private SortedMap<IPoint, List<DownloadFileParamType>> hashspace;
	private IMatcher matcher;
	private Logger logger;

	private OrderedHashSpaceManager() {
		matcher = new DefaultMatcher();
		hashspace = Collections
				.synchronizedSortedMap(new TreeMap<IPoint, List<DownloadFileParamType>>());
		logger = Logger.getLogger(OrderedHashSpaceManager.class);
		logger.trace("Inside OrderHashSpaceManger constructor");
	}

	public static OrderedHashSpaceManager getInstance() {
		if (instance == null) {
			instance = new OrderedHashSpaceManager();
		}
		return instance;
	}

	public List<MatchFileParamType> search(IQuery query) {
		logger.trace("Inside search method parameter : " + query);

		Set<IPoint> pointSet = hashspace.keySet();
		logger.debug("Number of points in space : " + pointSet.size());

		Iterator<IPoint> itr = pointSet.iterator();
		List<MatchFileParamType> searchResults = new ArrayList<MatchFileParamType>();
		List<DownloadFileParamType> matchResuts;
		while (itr.hasNext()) {
			IPoint temp = itr.next();
			//Double matchFactor;
			matchResuts = hashspace.get(temp);			 
				//matchResuts = hashspace.get(temp.getPoint());
				for (DownloadFileParamType file : matchResuts) {
					if (matcher.isMatches(query.getQueryDigest(), ByteOperationUtil.convertStringToBytes(file.getFiledigest()))) {
					MatchFileParamType aMatch = new MatchFileParamType();
					aMatch.setAbstract(file.getAbstract());
					aMatch.setFiledigest(file.getFiledigest());
					aMatch.setFilename(file.getFilename());
					aMatch.setIpaddress(file.getIpaddress());
					aMatch.setFilesize(file.getFilesize());
					double matchCoefficient = matcher.getMatchFactor() * 0.9
						+ ((file.getDownloads()/ConfigurationManager        .getInstance().getMaxDownloadLimit()) * 0.1);
					aMatch.setMatchFactor(matchCoefficient);
					logger.debug("match found with Filename : "
							+ file.getFilename() + " with match factor : "
							+ matchCoefficient);
					searchResults.add(aMatch);
					}
				}
		}
		logger.info("Total number of search results : " + searchResults.size());
		return searchResults;
	}

	public void handlePublishRequest(PublishRequestMessage pubRequest) {
		// TODO Auto-generated method stub

	}

	public synchronized List<DownloadFileParamType> getAllFileDetails(
			IZone childZone) {
		List<DownloadFileParamType> fileList = new ArrayList<DownloadFileParamType>();
		SortedMap<IPoint, List<DownloadFileParamType>> childSpace = hashspace
				.subMap(childZone.getStart(), childZone.getEnd());
		Set<IPoint> points = childSpace.keySet();
		Iterator<IPoint> itr = points.iterator();
		while (itr.hasNext()) {
			List<DownloadFileParamType> c = hashspace.get(itr.next());
			if (c == null) {
				continue;
			}
			fileList.addAll(c);
		}
		return fileList;
	}

	public void handlePublishRequest(List<DownloadFileParamType> fileList) {
		for (DownloadFileParamType file : fileList) {
			Point key = new Point(ByteOperationUtil.getCordinates(file
					.getFiledigest()));
			List<DownloadFileParamType> existingFiles = hashspace.get(key);
			// check if list exists
			if (existingFiles == null) {
				existingFiles = new ArrayList<DownloadFileParamType>();
			}
			// add put request to it
			existingFiles.add(file);
		}
	}

	public void handlePutRequest(PutRequest putRequest) {
		logger.trace("Inside handlePutRequest : " + putRequest.getParameter().getParamValue(EnumParamsType.FILENAME).toString());
		Point key = new Point(ByteOperationUtil.getCordinates(((byte[])putRequest
				.getParameter().getParamValue(EnumParamsType.FILEDIGEST))));
		
		logger.debug("Search key point is : " + key.toString());
		List<DownloadFileParamType> existingFiles = hashspace.get(key);
		// check if list exists
		if (existingFiles == null) {
			existingFiles = new ArrayList<DownloadFileParamType>();
		}
		// add put request to it
		DownloadFileParamType newFileDetails = new DownloadFileParamType();

		newFileDetails.setAbstract(putRequest.getParameter()
				.getParamValue(EnumParamsType.ABSTRACT).toString());
		newFileDetails.setFiledigest(ByteOperationUtil.convertBytesToString((byte[]) putRequest.getParameter()
				.getParamValue(EnumParamsType.FILEDIGEST)));
		newFileDetails.setFilename(putRequest.getParameter()
				.getParamValue(EnumParamsType.FILENAME).toString());
		newFileDetails.setFilesize(putRequest.getParameter()
				.getParamValue(EnumParamsType.FILESIZE).toString());
		newFileDetails.setIpaddress(putRequest.getParameter()
				.getParamValue(EnumParamsType.IPADDRESS).toString());
		newFileDetails.setDownloads(0);
		
		existingFiles.add(newFileDetails);
		hashspace.put(key, existingFiles);
	}
	
	public void updateDownloadCount(IPoint fileDigest, String fileName) {
		List<DownloadFileParamType> fileList = hashspace.get(fileDigest);
		if(fileList == null) {
			logger.error("File not found in hashspace : " + fileName);
		}
		for(DownloadFileParamType dfpt: fileList) {
			if(dfpt.getFilename().equals(fileName)) {
				dfpt.setDownloads(dfpt.getDownloads()+1);
				logger.debug("Updated download count for file : " + fileName + " to " + dfpt.getDownloads() );
			}
		}
	}
	
	public void unPublishFile(String fileDigest, String fileName) {
		IPoint fileLoc = new Point(ByteOperationUtil.getCordinates(fileDigest));
		List<DownloadFileParamType> fileList = hashspace.get(fileLoc);
		if(fileList == null) {
			logger.error("File not found in hashspace : " + fileName);
		}
               
		for(int i = 0; i < fileList.size(); i++) {
		DownloadFileParamType dfpt = fileList.get(i);	
                if(dfpt.getFilename().equals(fileName) /*&& dfpt.getFiledigest().equals(fileDigest)*/) {
				fileList.remove(dfpt);
				logger.debug("Removed file " + fileName + " from repository and its size is : " + hashspace.size());
			}
		}
	}
}
