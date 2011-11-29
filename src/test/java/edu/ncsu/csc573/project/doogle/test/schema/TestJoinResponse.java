package edu.ncsu.csc573.project.doogle.test.schema;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.List;

import org.junit.Test;

import edu.ncsu.csc573.project.commlayer.Point;
import edu.ncsu.csc573.project.common.messages.EnumOperationType;
import edu.ncsu.csc573.project.common.messages.EnumParamsType;
import edu.ncsu.csc573.project.common.messages.IParameter;
import edu.ncsu.csc573.project.common.messages.JoinResponse;
import edu.ncsu.csc573.project.common.messages.Parameter;
import edu.ncsu.csc573.project.common.schema.FileParamType;

public class TestJoinResponse {
	@Test
	public void testJoinResponse() {
		JoinResponse resp = new JoinResponse();
		IParameter param = new Parameter();
		param.add(EnumParamsType.STATUSCODE, new BigInteger(String.valueOf(1)));
		param.add(EnumParamsType.MESSAGE, "Bye");
		
		resp.setPeerid(((Point)(Point.generateRandomPoint())).getAsString());
		resp.setFirsthash(((Point)(Point.generateRandomPoint())).getAsString());
		resp.setLasthash(((Point)(Point.generateRandomPoint())).getAsString());
		resp.setMyipaddress("172.2.1.1");
		List<FileParamType> list = resp.getFile();
		FileParamType fp1 = new FileParamType();
		fp1.setAbstract("dsadsa");
		fp1.setFiledigest("dsadsa");
		fp1.setFilename("abc.txt");
		fp1.setId(11);
		fp1.setIpaddress("12.21.4.1");
		list.add(fp1);
		resp.createResponse(EnumOperationType.JOINRESPONSE, param);
		
		try {
			System.out.println(resp.getRequestInXML());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
