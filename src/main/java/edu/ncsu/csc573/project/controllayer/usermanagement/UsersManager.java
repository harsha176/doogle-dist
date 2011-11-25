package edu.ncsu.csc573.project.controllayer.usermanagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import edu.ncsu.csc573.project.common.EncDecUtil;


public class UsersManager extends IUsersManager {

	private static final String EMAIL_BODY = "Your new password is ";
	private static final String ENDEARMENTS = "Dear ";
	private static final String SUBJECT = "new password";
	private static final String DOOGLE_PASSWORD = "harfoush";
	private static final String DOOGLE_USERNAME = "csc573.doogle";
	private static final int PASSWORD_MIN_LENGTH = 7;
	private String userDataBaseFileName = "user_db.txt";
	private File userDataBaseFile = null;
	private Logger logger;
	private Map<String, IUser> users;
	
	public void addUser(IUser user) throws UserManagementException{
		// update user in hashmap and database file
		// first add in file and then in database
		if(userDataBaseFile == null) {
			logger.error("Usermanager not initialized");
			throw new UserManagementException(1, "Usermanager not initialized");
		}
		
		// check if the user already exists
		if(users.get(user.getUsername()) != null) {
			logger.info("user: " + user.getUsername() + " is already registered");
			throw new UserManagementException(2, "user: " + user.getUsername() + " is already registered");
		}
		PrintWriter pw = null;
		try {
			User newUser = (User)user;
			newUser.setStatus(EnumStatus.LOGGED_OUT);
			users.put(user.getUsername(), newUser);
			pw = new PrintWriter(new BufferedWriter(new FileWriter(userDataBaseFile, true)));
			pw.println(user);
			pw.flush();
		} catch (IOException e) {
			logger.error("Unable to open userdatabase file for writing");
			throw new UserManagementException(3, "Unable to open userdatabase file for writing");
		}finally {
			if(pw != null)
				pw.close();
		}
	}

	public IUser getUser(String username) {
		return users.get(username);
	}

	public void userLogin(String username, String password) throws UserManagementException{
		User aUser = (User)users.get(username);
		if(aUser == null || !aUser.validatePassword(password)) {
			logger.error("Username or password is wrong");
			throw new UserManagementException(1, "Username or password is wrong");
		} else {
			aUser.setStatus(EnumStatus.LOGGED_IN);
			logger.info(username  + " successfully logged in");
		}
	}

	public void userLogout(String username) throws UserManagementException{
		User aUser = (User)users.get(username);
		if(aUser == null || !isUserLoggedin(username)) {
			logger.error(username + " is not logged in or registered");
			throw new UserManagementException(1, username + " is not logged in or registered");
			//add exception
		} else {
			aUser.setStatus(EnumStatus.LOGGED_OUT);
			logger.info(username + " successfully logged out");
		}
	}

	public boolean isUserLoggedin(String username) {
		User aUser = (User)users.get(username);
		if(aUser == null) {
			logger.error(username + " not registered");
			//add exception
			return false;
		} else {
			if(aUser.getStatus() == EnumStatus.LOGGED_IN) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public void initialize() throws Exception{
		logger = Logger.getLogger(UsersManager.class);
		// Check if the file exists
		userDataBaseFile = getUserDBFile();
		InputStream is = ClassLoader.getSystemResourceAsStream(userDataBaseFileName);
		if(is == null) {
			logger.error("Userdatabase file is missing");
			throw new Exception("Userdatabase file is missing");
		}
		
		logger.info("Reading userdatabase file");
		// initialize hashmap
		users = new HashMap<String, IUser>();
		
		// read each entry from database and load it into hashmap with logged out status
		// each entry is of the form username:lastName:firstName:password:emailID:designation
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String userEntry;
		int userCount = 0;
		while((userEntry = br.readLine()) != null) {
			String[] fields = userEntry.split(":");
			User user = new User();
			
			if(fields.length != 6) {
				logger.warn("Invalid entry: " + userEntry);
			}
			
			user.setUsername(fields[0]);
			user.setLastName(fields[1]);
			user.setFirstName(fields[2]);
			user.setEncryptedPassword(fields[3]);
			user.setEmailID(fields[4]);
			user.setDesignation(fields[5]);
			user.setStatus(EnumStatus.LOGGED_OUT);
			
			logger.debug("added user in map " + user.getUsername());
			users.put(user.getUsername(), user);
			userCount++;
		}
		logger.info("Loaded " + userCount + " users from database");
		br.close();
	}
	
	private String generateRandomPassword() {
		
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < PASSWORD_MIN_LENGTH; i++) {
			sb.append(generateRandomChar());
		}
		return sb.toString();
	}
	
	private char generateRandomChar() {
		Random rand = new Random();
		while(true) {
			char ch = (char)rand.nextInt(127);
			if(Character.isLetter(ch) && !Character.isWhitespace(ch)) {
				return ch;
			}
			else {
				continue;
			}
		}
	}

	@Override
	public void forgotPassword(String username) throws UserManagementException{
		User aUser = (User)users.get(username);
		if(aUser == null) {
			logger.error("User not registered");
			// add exception
			throw new UserManagementException(1, username + " is not registered");
		} 
		String prevPassword = aUser.getPassword();
	    aUser.setPassword(generateRandomPassword());
	    try {
	    	mailPassword(aUser);
	    	updateUserDBFile();
			// mail the password to the server
	    } catch (Exception e) {
	    	logger.error("Failed to update password", e);
	    	logger.info("restoring previous password");
	    	aUser.setPassword(prevPassword);
	    	throw new UserManagementException(2, "Failed to update password");
	    }
	}
	
	private void mailPassword(User aUser) {
		Properties props = new Properties();
		final String username = DOOGLE_USERNAME;
		final String password = DOOGLE_PASSWORD;
 
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
 
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username,password);
				}
			});
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username+"@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(aUser.getEmailID()));
			message.setSubject(SUBJECT);
			message.setText(ENDEARMENTS + aUser.getFirstName() + " " + aUser.getLastName() + ","+ System.lineSeparator()+
					EMAIL_BODY + EncDecUtil.decryptMessage(aUser.getPassword()));
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	private void updateUserDBFile() throws Exception{
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(getUserDBFile())));
		Set<String> username = users.keySet();
		Iterator<String> itr = username.iterator();
		
		while(itr.hasNext()) {
			String aUsername = itr.next();
			User aUser = (User)users.get(aUsername);
			pw.println(aUser.toString());
		}
		pw.flush();
		pw.close();
	}
	
	private File getUserDBFile() throws Exception{
		URL url = ClassLoader.getSystemResource(userDataBaseFileName);
		if(url != null) {
			userDataBaseFile = new File(url.getFile()); 
			return userDataBaseFile;
		} else {
			logger.error("Unable to find " + userDataBaseFile);
			throw new Exception("Unable to find " + userDataBaseFile);
		}
	}

	@Override
	public void changePassword(String username, String oldPassword,
			String newPassword) throws UserManagementException {
		if(isUserLoggedin(username) && users.get(username).validatePassword(oldPassword)) {
			User user = (User)users.get(username);
			user.setPassword(newPassword);
			try {
				updateUserDBFile();
			} catch (Exception e) {
				logger.error("Unable to update user database");
				throw new UserManagementException(2, "Unable to update user database");
			}
		} else {
			throw new UserManagementException(1, "User is not logged in or password is invalid");
		}
	}

	@Override
	public void flushDatabase() {
		try {
			FileWriter fw = new FileWriter(getUserDBFile());
			users.clear();
			fw.close();
		} catch (Exception e) {
			logger.error("Failed to find user database file", e);
		}
	}	
}
