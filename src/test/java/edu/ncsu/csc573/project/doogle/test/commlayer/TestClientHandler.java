package edu.ncsu.csc573.project.doogle.test.commlayer;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import edu.ncsu.csc573.project.commlayer.ClientHandler;

public class TestClientHandler {
	/*@Test
	public void testGetFileName() {
		String fileName = "C:/abc/dsa.txt";
		Assert.assertEquals(fileName, ClientHandler.getFileName(prepareGetFileRequest(fileName)));
	}*/
	
	/*@Test
	public void testGetFileNameInWindows() {
		String fileName = "/tmp/dsa/dsa/bcd.txt";
		Assert.assertEquals(fileName, ClientHandler.getFileName(prepareGetFileRequest(fileName)));
	}*/
    
	
	public static StringBuffer prepareGetFileRequest(String fileName) {
		StringBuffer sb = new StringBuffer();
		sb.append("<request>");
		sb.append(System.lineSeparator());
		sb.append("File:"+fileName);
		sb.append(System.lineSeparator());
		sb.append("</request>");
		
		return sb;
	}
	
}
