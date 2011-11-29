package edu.ncsu.csc573.project.controllayer;

import edu.ncsu.csc573.project.common.ConfigurationManager;
import edu.ncsu.csc573.project.common.messages.EnumOperationType;

public class AdminServerFilter implements IFilter {
	private boolean isAdminServer = ConfigurationManager.getInstance().isAdminServer(); 
	
	public boolean isRequestValid(EnumOperationType opType) {
		 if(isAdminServer) {
			 switch(opType) {
			 	case PUBLISH:
			 	case SEARCH: 
			 		return false;
			 	default: 
			 		return true;
			 }
		 } /*else {
			 switch (opType) {
			 case PUBLISH:
			 case SEARCH:
				 return true;
			 }
		 }
		return false;*/
		return !isAdminServer;
	}
	
	public boolean isAdminServer() {
		return isAdminServer;
	}
}
