package edu.ncsu.csc573.project.common.messages;

import edu.ncsu.csc573.project.common.schema.FileParamType;

public class CustomFileParamType extends FileParamType {
	private int download;
	
	public CustomFileParamType(int download) {
		super();
		this.download = download;
	}
	
	public int getDownload() {
		return download;
	}
	
	public void setDownload(int download) {
		this.download = download;
	}
}
