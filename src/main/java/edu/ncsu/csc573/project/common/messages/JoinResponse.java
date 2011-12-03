/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncsu.csc573.project.common.messages;

import edu.ncsu.csc573.project.common.schema.CommandResponseType;
import edu.ncsu.csc573.project.common.schema.DownloadFileParamType;
import edu.ncsu.csc573.project.common.schema.FileParamType;
import edu.ncsu.csc573.project.common.schema.JoinResponseType;
import edu.ncsu.csc573.project.common.schema.JoinResponseTypeParams;
import edu.ncsu.csc573.project.common.schema.Response;
import edu.ncsu.csc573.project.common.schema.TableParamType;

import java.math.BigInteger;
import java.util.List;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

/**
 * 
 * @author krishna
 */
public class JoinResponse extends ResponseMessage {
	// private Logger logger;
	private JoinResponseTypeParams joinResponseTypeParams;
	private String id;

	public JoinResponse(String id) {
		super();
		this.id = id;
		joinResponseTypeParams = new JoinResponseTypeParams();
	}
	public List<DownloadFileParamType> getFile() {
		return joinResponseTypeParams.getFile();
	}

	public String getMyipaddress() {
		return joinResponseTypeParams.getMyipaddress();
	}

	public String getFirsthash() {
		return joinResponseTypeParams.getFirsthash();
	}

	public String getLasthash() {
		return joinResponseTypeParams.getLasthash();
	}

	public String getPeerid() {
		return joinResponseTypeParams.getPeerid();
	}

	public void setLasthash(String lasthash) {
		joinResponseTypeParams.setLasthash(lasthash);
	}

	public List<TableParamType> getTable() {
		return joinResponseTypeParams.getTable();
	}

	public void setMyipaddress(String myipaddress) {
		joinResponseTypeParams.setMyipaddress(myipaddress);
	}

	public void setPeerid(String peerid) {
		joinResponseTypeParams.setPeerid(peerid);
	}

	public void setFirsthash(String firsthash) {
		joinResponseTypeParams.setFirsthash(firsthash);
	}

	public String getRequestInXML() throws Exception {
		// logger = Logger.getLogger(LoginRequestMessage.class);

		Response req = new Response();
		req.setId(id);
		CommandResponseType Putresponse = new CommandResponseType();
		JoinResponseType rt = new JoinResponseType();

		joinResponseTypeParams.setStatuscode((BigInteger) (getParameter()
				.getParamValue(EnumParamsType.STATUSCODE)));
		joinResponseTypeParams.setMessage(getParameter().getParamValue(
				EnumParamsType.MESSAGE).toString());
		rt.setParams(joinResponseTypeParams);
		Putresponse.setJoinResponse(rt);
		req.setCommand(Putresponse);

		return getXML(req);
	}

	public void parseXML(String XML) {
		Response req;
		try {
			req = getResponse(XML);
			id = req.getId();
			CommandResponseType command = req.getCommand();
			JoinResponseType joinResponse = command.getJoinResponse();
			joinResponseTypeParams = joinResponse.getParams();
			IParameter param = new Parameter();
			param.add(EnumParamsType.STATUSCODE,
					joinResponseTypeParams.getStatuscode());
			param.add(EnumParamsType.MESSAGE,
					joinResponseTypeParams.getMessage());

			this.setOperationType(EnumOperationType.JOINRESPONSE);
			this.setParameter(param);
		} catch (JAXBException e) {
			Logger.getLogger(JoinResponse.class).error("Failed to generate join response xml ",e);
		}
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = String.valueOf(id);
	}
}
