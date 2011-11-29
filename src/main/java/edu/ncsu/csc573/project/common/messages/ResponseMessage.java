/**
 * 
 */
package edu.ncsu.csc573.project.common.messages;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import edu.ncsu.csc573.project.common.schema.Response;
import edu.ncsu.csc573.project.common.schema.Request;
import java.math.BigInteger;

/**
 * 
 * @author krishna
 */
public abstract class ResponseMessage extends RequestMessage implements
		IResponse {
	private static Logger logger;

	// @Override
	public IStatus getStatus() {
		return new Status ((BigInteger)(this.getParameter().getParamValue(EnumParamsType.STATUSCODE)));
	}

	// @Override
	public String getMessage() {
		return this.getParameter().getParamValue(EnumParamsType.MESSAGE).toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.ncsu.csc573.project.common.messages.IRequest#createRequest(edu.ncsu
	 * .csc573.project.common.messages.EnumOperationType,
	 * edu.ncsu.csc573.project.common.messages.IParameter)
	 */
	public void createResponse(EnumOperationType opType, IParameter parameter) {
		// TODO Auto-generated method stub
		super.createRequest(opType, parameter);
	}

	@Override
	public void createRequest(EnumOperationType opType, IParameter parameter) {
		logger.warn("Create response should be called instead of create request");
		createResponse(opType, parameter);
	}

	@Override
	public String getXML(Request req) throws Exception {
		throw new Exception(
				"Invalid operation getXNL from request on response object");
	}

	public String getXML(Response req) throws Exception {
		logger = Logger.getLogger(RequestMessage.class);
		StringWriter reqXMLWriter = new StringWriter();
		try {
			JAXBContext context = JAXBContext.newInstance(Response.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(req, reqXMLWriter);
		} catch (Exception e) {
			logger.error("Unable to parse request ", e);
			throw e;
		} finally {
			reqXMLWriter.close();
		}
		return reqXMLWriter.toString();
	}

	public Response getResponse(String XML) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(Response.class);
		Unmarshaller unMarsheller = context.createUnmarshaller();
		Response req = (Response) unMarsheller.unmarshal(new StringReader(XML));
		return req;
	}

	public static IResponse createResponse(String XML) throws Exception {
		logger = Logger.getLogger(RequestMessage.class);
		IResponse res = null;

		if (XML.indexOf("Register") != -1) {
			res = new RegisterResponseMessage();
			res.parseXML(XML);
		} else if (XML.indexOf("Login") != -1) {
			res = new LoginResponseMessage();
			res.parseXML(XML);
		} else if (XML.indexOf("Logout") != -1) {
			res = new LogoutResponseMessage();
			res.parseXML(XML);
		} else if (XML.indexOf("Publish") != -1) {
			res = new PublishResponseMessage();
			res.parseXML(XML);
		} /*
		 * else if(XML.indexOf("Search") != -1) { res = new
		 * SearchResponseType(); res.parseXML(XML); }
		 */else if (XML.indexOf("ForgotPWD") != -1) {
			res = new ForgotPWDResponseMessage();
			res.parseXML(XML);
		} else if (XML.indexOf("ChangePassword") != -1) {
			res = new ChangePasswordResponseMessage();
			res.parseXML(XML);
		} else if (XML.indexOf("Search") != -1) {
			res = new SearchResponseMessage();
			res.parseXML(XML);
		}else if (XML.indexOf("Invalid") != -1) {
			res = new InvalidResponseMessage("");
			res.parseXML(XML);
		}
		 else {
			logger.error("Given XML " + XML + " is an invalid request");
		}
		return res;
	}
}
