package edu.ncsu.csc573.project.common.messages;

import java.math.BigInteger;
import org.apache.log4j.Logger;

import edu.ncsu.csc573.project.common.EncDecUtil;
import edu.ncsu.csc573.project.common.schema.CommandRequestType;
import edu.ncsu.csc573.project.common.schema.RegisterParamsType;
import edu.ncsu.csc573.project.common.schema.RegisterType;
import edu.ncsu.csc573.project.common.schema.Request;

public class RegisterRequestMessage extends RequestMessage {
	private Logger logger;
	private String id;

	public String getRequestInXML() throws Exception {

		logger = Logger.getLogger(RegisterRequestMessage.class);

		Request req = new Request();
		req.setId(id);
		CommandRequestType register = new CommandRequestType();
		RegisterType rt = new RegisterType();
		RegisterParamsType rpt = new RegisterParamsType();

		rpt.setUsername((getParameter().getParamValue(EnumParamsType.USERNAME)
				.toString()));
		String clearTextPasswd = getParameter().getParamValue(EnumParamsType.PASSWORD)
				.toString();
		String encryptedPasswd = EncDecUtil.encryptMessage(clearTextPasswd);
		rpt.setPassword(encryptedPasswd);
		rpt.setFirstname(getParameter().getParamValue(EnumParamsType.FIRSTNAME)
				.toString());
		rpt.setLastname(getParameter().getParamValue(EnumParamsType.LASTNAME)
				.toString());
		rpt.setEmailId(getParameter().getParamValue(EnumParamsType.EMAIL_ID)
				.toString());
		rpt.setDesignation(getParameter().getParamValue(
				EnumParamsType.DESIGNATION).toString());

		rt.setParams(rpt);
		register.setRegister(rt);
		req.setCommand(register);

		return getXML(req);
	}

	public void parseXML(String XML) {
		logger = Logger.getLogger(RegisterRequestMessage.class);
		try {
			Request req = getRequest(XML);
			id = req.getId();
			CommandRequestType command = req.getCommand();
			RegisterType regType = command.getRegister();
			RegisterParamsType regparams = regType.getParams();
			IParameter param = new Parameter();
			param.add(EnumParamsType.USERNAME, regparams.getUsername());
			String encryptedPasswd = regparams.getPassword();
			String clearTextPasswd = EncDecUtil.decryptMessage(encryptedPasswd);
			param.add(EnumParamsType.PASSWORD, clearTextPasswd);
			param.add(EnumParamsType.FIRSTNAME, regparams.getFirstname());
			param.add(EnumParamsType.LASTNAME, regparams.getLastname());
			param.add(EnumParamsType.EMAIL_ID, regparams.getEmailId());
			param.add(EnumParamsType.DESIGNATION, regparams.getDesignation());

			this.setOperationType(EnumOperationType.REGISTER);
			this.setParameter(param);
		} catch (Exception e) {
			logger.error("Unable to parse request from string", e);
		}
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = String.valueOf(id);
	}
	
	public RegisterRequestMessage() {
		id = ""+System.currentTimeMillis();
	}
}
