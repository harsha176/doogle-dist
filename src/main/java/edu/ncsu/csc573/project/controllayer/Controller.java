package edu.ncsu.csc573.project.controllayer;

import java.math.BigInteger;
import java.net.Socket;
import java.util.List;

import org.apache.log4j.Logger;

import edu.ncsu.csc573.project.commlayer.CommunicationServiceFactory;
import edu.ncsu.csc573.project.commlayer.ICommunicationService;
import edu.ncsu.csc573.project.commlayer.Point;
import edu.ncsu.csc573.project.commlayer.Router;
import edu.ncsu.csc573.project.common.ByteOperationUtil;
import edu.ncsu.csc573.project.common.ConfigurationManager;
import edu.ncsu.csc573.project.common.messages.ChangePasswordRequestMessage;
import edu.ncsu.csc573.project.common.messages.EnumOperationType;
import edu.ncsu.csc573.project.common.messages.EnumParamsType;
import edu.ncsu.csc573.project.common.messages.ForgotPwdRequestMessage;
import edu.ncsu.csc573.project.common.messages.IParameter;
import edu.ncsu.csc573.project.common.messages.IRequest;
import edu.ncsu.csc573.project.common.messages.IResponse;
import edu.ncsu.csc573.project.common.messages.LoginRequestMessage;
import edu.ncsu.csc573.project.common.messages.LogoutRequestMessage;
import edu.ncsu.csc573.project.common.messages.Parameter;
import edu.ncsu.csc573.project.common.messages.PublishRequestMessage;
import edu.ncsu.csc573.project.common.messages.RegisterRequestMessage;
import edu.ncsu.csc573.project.common.messages.SearchRequestMessage;
import edu.ncsu.csc573.project.common.schema.FileParamType;
import edu.ncsu.csc573.project.controllayer.hashspacemanagement.DigestAdaptor;
import edu.ncsu.csc573.project.viewlayer.gui.Search;

public class Controller implements IController {

	private static String bootstrapServerIP = null;
	private static Controller instance = null;
	private Logger logger = Logger.getLogger(Controller.class);
	
	private static ICommunicationService commService;
	
	private Controller () {
		
	}
	
	public static IController getInstance() throws Exception {
		if(instance == null) {
			instance  = new Controller();
			commService = CommunicationServiceFactory.getInstance();
			commService.initialize(null);
		} 
		return instance;
	}

	public String login(String username, String passwd) throws Exception {
		/*
		 * send login request
		 */
		LoginRequestMessage loginRequest = new LoginRequestMessage();

		IParameter params = new Parameter();
		params.add(EnumParamsType.USERNAME, username);
		params.add(EnumParamsType.PASSWORD, passwd);
		
		loginRequest.createRequest(EnumOperationType.LOGIN, params);

		if (bootstrapServerIP == null) {
			throw new Exception("Not connected to bootstrap server");
		}

		IResponse response = commService.executeRequest(loginRequest,
				bootstrapServerIP);
		return validateResponse(response);
	}

	private String validateResponse(IResponse response) throws Exception {
		if ((response.getStatus().getErrorId().intValue() != BigInteger.ZERO.intValue())) {
			throw new Exception(response.getMessage());
		}
		return response.getMessage();
	}

	public String logout(String username) throws Exception {
		LogoutRequestMessage logoutRequest = new LogoutRequestMessage();

		IParameter params = new Parameter();
		params.add(EnumParamsType.USERNAME, Session.getInstance().getUsername());

		logoutRequest.createRequest(EnumOperationType.LOGOUT, params);

		if (bootstrapServerIP == null) {
			throw new Exception("Not connected to bootstrap server");
		}

		IResponse response = commService.executeRequest(logoutRequest,
				bootstrapServerIP);
		bootstrapServerIP = null;
		return validateResponse(response);
	}

	public void connect(String ipaddress) throws Exception {
		bootstrapServerIP = ipaddress;
		Socket soc = new Socket(bootstrapServerIP, ConfigurationManager
				.getInstance().getServerPort());
		soc.close();
		logger.debug("Able to connect to bootstrap server");
	}

	public String publish() throws Exception {
		try {
		PublishRequestMessage.sendPublishRequest();
		} catch(Exception e) {
			logger.error("Unable to send publish request", e);
		}
		return "Dummy repsonse";
	}

	public List<FileParamType> search(String query) throws Exception {
		/*Logger logger = Logger.getLogger(Search.class);

        IRequest searchRequest = new SearchRequestMessage();
        IParameter searchParams = new Parameter();
        searchParams.add(EnumParamsType.USERNAME, "DUMMY");
        searchParams.add(EnumParamsType.SEARCHKEY, ByteOperationUtil.convertBytesToString(DigestAdaptor.getInstance().getDigest(searchText.getText())));
        searchRequest.createRequest(EnumOperationType.SEARCH, searchParams);
        
        IResponse response = CommunicationServiceFactory.getInstance().executeRequest(searchRequest);
        */
		Logger logger = Logger.getLogger(Search.class);

        IRequest searchRequest = new SearchRequestMessage();
        IParameter searchParams = new Parameter();
        searchParams.add(EnumParamsType.USERNAME, "DUMMY");
        String QueryDigest = ByteOperationUtil.convertBytesToString(DigestAdaptor.getInstance().getDigest(query));
		searchParams.add(EnumParamsType.SEARCHKEY, QueryDigest);
        searchRequest.createRequest(EnumOperationType.SEARCH, searchParams);
        
        String destPeer = Router.getInstance().getNextHop(new Point(QueryDigest));
        IResponse response = CommunicationServiceFactory.getInstance().executeRequest(searchRequest, destPeer);
        
        validateResponse(response);
        return null;
	}

	public String register(String firstName, String lastName, String username,
			String passwd, String email_id, String designation) throws Exception{
		IRequest regRequest = new RegisterRequestMessage();
	    IParameter Regparams = new Parameter();
	    Regparams.add(EnumParamsType.USERNAME, username);
	    Regparams.add(EnumParamsType.PASSWORD, passwd);
	    Regparams.add(EnumParamsType.FIRSTNAME, firstName);
	    Regparams.add(EnumParamsType.LASTNAME, lastName);
	    Regparams.add(EnumParamsType.EMAIL_ID, email_id);
	    Regparams.add(EnumParamsType.DESIGNATION, designation);
	       
	    regRequest.createRequest(EnumOperationType.REGISTER, Regparams);
	    IResponse response = CommunicationServiceFactory.getInstance().executeRequest(regRequest, bootstrapServerIP);
	    
	    return validateResponse(response);
	    
	}

	public String changePasswd(String newPasswd, String oldPasswd) throws Exception {
		IRequest changeRequest = new ChangePasswordRequestMessage();
        IParameter Changeparams = new Parameter();
        Changeparams.add(EnumParamsType.USERNAME, Session.getInstance().getUsername());
        Changeparams.add(EnumParamsType.PASSWORD, oldPasswd);
        Changeparams.add(EnumParamsType.NEWPASSWORD, newPasswd);
        changeRequest.createRequest(EnumOperationType.CHANGEPASSWORD, Changeparams);
        IResponse response = CommunicationServiceFactory.getInstance().executeRequest(changeRequest, bootstrapServerIP);
        
        return validateResponse(response);   
	}

	public String forgotPasswd(String username) throws Exception{
		IRequest ForgotPWDRequest = new ForgotPwdRequestMessage();
		IParameter ForgotPWDparams = new Parameter();
		ForgotPWDparams.add(EnumParamsType.USERNAME, username);
		//ForgotPWDparams.add(EnumParamsType.EMAIL_ID, "hmalipa@ncsu.edu");
		// System.out.println(regRequest.getRequestInXML());

		ForgotPWDRequest.createRequest(EnumOperationType.FORGOTPASSWORD,
				ForgotPWDparams);
		IResponse response = CommunicationServiceFactory.getInstance().executeRequest(ForgotPWDRequest, bootstrapServerIP);
		return validateResponse(response);
	}
}
