package edu.ncsu.csc573.project.common.messages;

import java.math.BigInteger;
import org.apache.log4j.Logger;

import edu.ncsu.csc573.project.common.schema.CommandRequestType;
import edu.ncsu.csc573.project.common.schema.LoginParamsType;
import edu.ncsu.csc573.project.common.schema.LoginType;
import edu.ncsu.csc573.project.common.schema.Request;

public class LoginRequestMessage extends RequestMessage {
    private Logger logger;

	public String getRequestInXML() throws Exception {
	logger = Logger.getLogger(LoginRequestMessage.class);

		Request req = new Request();
		req.setId(BigInteger.valueOf(System.currentTimeMillis()));
		CommandRequestType login = new CommandRequestType();
		LoginType loginType = new LoginType();
		LoginParamsType lpt = new LoginParamsType();
		
		lpt.setUsername((getParameter().getParamValue(EnumParamsType.USERNAME).toString()));
		lpt.setPassword(getParameter().getParamValue(EnumParamsType.PASSWORD).toString());
		lpt.setPassword(getParameter().getParamValue(EnumParamsType.IPADDRESS).toString());
		
		loginType.setParams(lpt);
		login.setLogin(loginType);
		req.setCommand(login);
		
		return getXML(req);
	}

	public void parseXML(String XML) {
		try {
			Request req = getRequest(XML);
			
			CommandRequestType command = req.getCommand();
			LoginType loginType = command.getLogin();
			LoginParamsType loginparams = loginType.getParams();
			IParameter param = new Parameter();
			param.add(EnumParamsType.USERNAME, loginparams.getUsername());
			param.add(EnumParamsType.PASSWORD, loginparams.getPassword());
			param.add(EnumParamsType.IPADDRESS, loginparams.getIpaddress());
			this.setOperationType(EnumOperationType.LOGIN);
			this.setParameter(param);
		} catch(Exception e) {
			logger.error("Unable to parse request from string", e);
		}
	}

}
