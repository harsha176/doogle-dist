/**
 * 
 */
package edu.ncsu.csc573.project.common.messages;

import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.apache.log4j.Logger;
import edu.ncsu.csc573.project.common.schema.Request;

/**
 * @author doogle-dev
 * 
 */
public abstract class RequestMessage implements IRequest {

	private EnumOperationType operationType;
	private IParameter params;
	private static Logger logger;
	private String id;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.ncsu.csc573.project.common.messages.IRequest#createRequest(edu.ncsu
	 * .csc573.project.common.messages.EnumOperationType,
	 * edu.ncsu.csc573.project.common.messages.IParameter)
	 */
	public void createRequest(EnumOperationType opType, IParameter parameter) {
		// TODO Auto-generated method stub
		operationType = opType;
		params = parameter;
	}

	protected void setOperationType(EnumOperationType opType) {
		operationType = opType;
	}

	protected void setParameter(IParameter param) {
		params = param;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.ncsu.csc573.project.common.messages.IRequest#getOperationType()
	 */
	public EnumOperationType getOperationType() {
		return operationType;
	}

	public IParameter getParameter() {
		return params;
	}

	// 
	public static String getXML(Request req) throws Exception{
		logger = Logger.getLogger(RequestMessage.class);
		StringWriter reqXMLWriter = new StringWriter();
		//req.setId(String.valueOf(System.currentTimeMillis()));
		try {
			JAXBContext context = JAXBContext.newInstance(Request.class);
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

	public Request getRequest(String XML) throws Exception {
		return getRequestFromGenXML(XML);
	}	
	
	public static IRequest createRequest(String XML) throws Exception {
		logger = Logger.getLogger(RequestMessage.class);
		IRequest req = null;
		
		if(XML.indexOf("Register") != -1) {
			req = new RegisterRequestMessage();
			req.parseXML(XML);
		} else if(XML.indexOf("Login") != -1){
			req = new LoginRequestMessage();
			req.parseXML(XML);
		} else if(XML.indexOf("Logout") != -1) {
			req = new LogoutRequestMessage();
			req.parseXML(XML);
		} else if(XML.indexOf("Search") != -1) {
			req = new SearchRequestMessage();
			req.parseXML(XML);
		} else if(XML.indexOf("ForgotPWD") != -1) {
			req = new ForgotPwdRequestMessage();
			req.parseXML(XML);
		} else if(XML.indexOf("Publish") != -1) {
			req = new PublishRequestMessage();
			req.parseXML(XML);
        } else if(XML.indexOf("ChangePassword") != -1) {
			req = new ChangePasswordRequestMessage();
			req.parseXML(XML);
        } else if(XML.indexOf("Join") != -1) {
			req = new JoinRequest();
			req.parseXML(XML);
        } else if(XML.indexOf("Leave") != -1) {
			req = new LeaveRequest();
			req.parseXML(XML);
        } else if(XML.indexOf("Put") != -1) {
			req = new PutRequest();
			req.parseXML(XML);
        } else if(XML.indexOf("Get") != -1) {
			req = new GetRequest();
			req.parseXML(XML);
        } else if(XML.indexOf("DownloadUpdate") != -1) {
			req = new DownloadUpdateRequest();
			req.parseXML(XML);
        }else if(XML.indexOf("Unpublish") != -1) {
			req = new UnPublishRequestMessage();
			req.parseXML(XML);
        }
        else {
			logger.error("Given XML " + XML + " is an invalid request");
		}
		return req;
		
	}
	
	public static Request getRequestFromGenXML(String XML) throws Exception {
		JAXBContext context = JAXBContext.newInstance(Request.class);
		Unmarshaller unMarsheller = context.createUnmarshaller();
		Request req = (Request)unMarsheller.unmarshal(new StringReader(XML));
		return req;
	}
}
