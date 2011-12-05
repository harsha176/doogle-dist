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
	private String id;

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
		String id = "1";
		if (XML.indexOf("Register") != -1) {
			res = new RegisterResponseMessage(id);
			res.parseXML(XML);
		} else if (XML.indexOf("Login") != -1) {
			res = new LoginResponseMessage(id);
			res.parseXML(XML);
		} else if (XML.indexOf("Logout") != -1) {
			res = new LogoutResponseMessage(id);
			res.parseXML(XML);
		} else if (XML.indexOf("Publish") != -1) {
			res = new PublishResponseMessage(id);
			res.parseXML(XML);
		} /*
		 * else if(XML.indexOf("Search") != -1) { res = new
		 * SearchResponseType(); res.parseXML(XML); }
		 */else if (XML.indexOf("ForgotPWD") != -1) {
			res = new ForgotPWDResponseMessage(id);
			res.parseXML(XML);
		} else if (XML.indexOf("ChangePassword") != -1) {
			res = new ChangePasswordResponseMessage(id);
			res.parseXML(XML);
		} else if (XML.indexOf("Search") != -1) {
			res = new SearchResponseMessage(id);
			res.parseXML(XML);
		}else if (XML.indexOf("Join") != -1) {
			res = new JoinResponse(id);
			res.parseXML(XML);
		}else if (XML.indexOf("Leave") != -1) {
			res = new LeaveResponse(id);
			res.parseXML(XML);
		}else if (XML.indexOf("Put") != -1) {
			res = new PutResponse(id);
			res.parseXML(XML);
		}else if (XML.indexOf("Get") != -1) {
			res = new GetResponse(id);
			res.parseXML(XML);
		}else if (XML.indexOf("Invalid") != -1) {
			res = new InvalidResponseMessage(id, "");
			res.parseXML(XML);
		}
		 else {
			logger.error("Given XML " + XML + " is an invalid request");
		}
		return res;
	}
}
