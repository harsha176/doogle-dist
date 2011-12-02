/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Login.java
 *
 * Created on Oct 30, 2011, 3:14:23 PM
 */
package edu.ncsu.csc573.project.viewlayer.gui;

import edu.ncsu.csc573.project.commlayer.CommunicationService;
import edu.ncsu.csc573.project.commlayer.CommunicationServiceFactory;
import edu.ncsu.csc573.project.commlayer.Point;
import edu.ncsu.csc573.project.common.ConfigurationManager;
import edu.ncsu.csc573.project.common.messages.EnumOperationType;
import edu.ncsu.csc573.project.common.messages.EnumParamsType;
import edu.ncsu.csc573.project.common.messages.IParameter;
import edu.ncsu.csc573.project.common.messages.IRequest;
import edu.ncsu.csc573.project.common.messages.IResponse;
import edu.ncsu.csc573.project.common.messages.JoinRequest;
import edu.ncsu.csc573.project.common.messages.LoginRequestMessage;
import edu.ncsu.csc573.project.common.messages.Parameter;
import edu.ncsu.csc573.project.controllayer.Controller;
import edu.ncsu.csc573.project.controllayer.RequestProcessor;
import edu.ncsu.csc573.project.controllayer.Session;
import java.math.BigInteger;
import org.apache.log4j.Logger;

/**
 * 
 * @author krishna
 */
public class Login extends javax.swing.JFrame {
	// public String Username;
	/** Creates new form Login */
	public Login() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		username = new javax.swing.JTextField();
		loginButton = new javax.swing.JButton();
		jLabel4 = new javax.swing.JLabel();
		registerButton = new javax.swing.JButton();
		password = new javax.swing.JPasswordField();
		forgotPwd = new javax.swing.JButton();
		jLabel5 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setBounds(new java.awt.Rectangle(450, 250, 0, 0));
		setResizable(false);

		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
		jLabel1.setText("DGOOGLE");

		jLabel2.setText("Username");

		jLabel3.setText("Password");

		loginButton.setText("Login");
		loginButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				loginButtonActionPerformed(evt);
			}
		});

		jLabel4.setText("New Users? Register now");

		registerButton.setText("Register");
		registerButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				registerButtonActionPerformed(evt);
			}
		});

		forgotPwd.setText("Forgot Pwd");
		forgotPwd.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				forgotPwdActionPerformed(evt);
			}
		});

		jLabel5.setText("Forgot Password? Click here");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(84, 84,
																		84)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																												.addComponent(
																														jLabel2)
																												.addComponent(
																														jLabel3)
																												.addComponent(
																														loginButton,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														67,
																														javax.swing.GroupLayout.PREFERRED_SIZE))
																								.addGap(46,
																										46,
																										46)
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																												.addComponent(
																														password,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addComponent(
																														username,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														100,
																														javax.swing.GroupLayout.PREFERRED_SIZE)))
																				.addGroup(
																						layout.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								false)
																								.addGroup(
																										layout.createSequentialGroup()
																												.addComponent(
																														jLabel5)
																												.addPreferredGap(
																														javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														Short.MAX_VALUE)
																												.addComponent(
																														forgotPwd))
																								.addGroup(
																										layout.createSequentialGroup()
																												.addComponent(
																														jLabel4)
																												.addPreferredGap(
																														javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														Short.MAX_VALUE)
																												.addComponent(
																														registerButton)))))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(170,
																		170,
																		170)
																.addComponent(
																		jLabel1,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		83,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(109, Short.MAX_VALUE)));

		layout.linkSize(javax.swing.SwingConstants.HORIZONTAL,
				new java.awt.Component[] { password, username });

		layout.linkSize(javax.swing.SwingConstants.HORIZONTAL,
				new java.awt.Component[] { forgotPwd, loginButton,
						registerButton });

		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(54, 54, 54)
								.addComponent(jLabel1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										25,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jLabel2)
																.addGap(18, 18,
																		18)
																.addComponent(
																		jLabel3)
																.addGap(18, 18,
																		18)
																.addComponent(
																		loginButton))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		username,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		password,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(46, 46,
																		46)))
								.addGap(30, 30, 30)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(registerButton)
												.addComponent(jLabel4))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(forgotPwd)
												.addComponent(jLabel5))
								.addContainerGap(26, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_loginButtonActionPerformed
		Logger logger = Logger.getLogger(Login.class);

		if (username.getText().isEmpty()) {
			inValidUserScreen();
		} else if (password.getPassword().toString().isEmpty()) {
			inValidPWDScreen();
		} else {
			try {
			Session.createInstance(username.getText());
			Controller.getInstance().login(username.getText(), password.getText());
			Search LoggedIn = new Search();
			this.setVisible(false);
			LoggedIn.setVisible(true);
			LoggedIn.setLocationRelativeTo(this);
				/*IResponse response = CommunicationServiceFactory.getInstance()
						.executeRequest(loginRequest);
				BigInteger statusCode = response.getStatus().getErrorId();
				if (statusCode.intValue() == 0) {
					logger.info("Closing bootstrap server connect");
					CommunicationServiceFactory.getInstance().closeClientSocket();
                                        /*while(CommunicationServiceFactory.getInstance().isPeerServerRunning()) {
                                            logger.debug("Server is still running");
                                            Thread.sleep(1000);
                                        }
                                        logger.debug("Server status : " + CommunicationServiceFactory.getInstance().isPeerServerRunning());
					Session.createInstance(username.getText());
					Search LoggedIn = new Search();
					this.setVisible(false);
					LoggedIn.setVisible(true);
					LoggedIn.setLocationRelativeTo(this);
					LoggedIn.setTitle("Hello " + username.getText()
							+ " , Welcome!!");
					String joinPeerIP = response.getParameter()
							.getParamValue(EnumParamsType.MESSAGE).toString();
                                        logger.debug("Join peer ip is : " + joinPeerIP);
					/*
					 * if this is the first peer set its zone to entire
					 * Hashspace
					
					if (joinPeerIP.equalsIgnoreCase("0.0.0.0")) {
						logger.info("I am the first peeer");
						CommunicationServiceFactory.getInstance().initialize(
								"127.0.0.1", null);
						RequestProcessor
								.getInstance()
								.getMyZone()
								.create(Point.getHashSpaceStartPoint(),
										Point.getHashSpaceEndPoint());
						logger.info("My zone detais "
								+ RequestProcessor.getInstance().getMyZone()
										.toString());
					} else {
						// connect to join peer
						CommunicationServiceFactory.getInstance().initialize(
								joinPeerIP.trim(), null);
						// send join request
						IRequest joinRequest = new JoinRequest();
						IParameter params = new Parameter();
						params.add(EnumParamsType.IPADDRESS,
								ConfigurationManager.getInstance()
										.getHostInterface());
						params.add(EnumParamsType.PEERID,
								Point.generateRandomPoint());
						joinRequest.createRequest(EnumOperationType.JOIN,
								params);
                                                logger.info("Join request: " + joinRequest.getRequestInXML());
                                                        
						response = CommunicationServiceFactory.getInstance()
								.executeRequest(joinRequest);
						// update zone and file repository based on response
					}*/

					// logger.info("Status of response is  : "
					// +response.getStatus().getErrorId().toString());
					// logger.info("Message is " + response.getMessage());
				 //}
			} catch (Exception e) {
				// inValidComboScreen();
				inValidComboScreen();
				logger.error("Failed to perform login ", e);
			}
		}
	}// GEN-LAST:event_loginButtonActionPerformed

	private void inValidUserScreen() {
		ErrorScreen inValidScreen = new ErrorScreen();
		inValidScreen.setMessage("Username not entered");
		this.setVisible(false);
		inValidScreen.setVisible(true);
		inValidScreen.setLocationRelativeTo(this);
	}

	private void inValidPWDScreen() {
		ErrorScreen inValidScreen = new ErrorScreen();
		inValidScreen.setMessage("Password not entered");
		this.setVisible(false);
		inValidScreen.setVisible(true);
		inValidScreen.setLocationRelativeTo(this);
	}

	private void inValidComboScreen() {
		ErrorScreen inValidScreen = new ErrorScreen();
		inValidScreen.setMessage("Username or Password are incorrect");
		this.setVisible(false);
		inValidScreen.setVisible(true);
		inValidScreen.setLocationRelativeTo(this);
	}

	private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_registerButtonActionPerformed
		// TODO add your handling code here:
		Register registerFrame = new Register();
		this.setVisible(false);
		registerFrame.setVisible(true);
	}// GEN-LAST:event_registerButtonActionPerformed

	/*
	 * private void forgotpwdlinkActionPerformed(java.awt.event.ActionEvent evt)
	 * {                                                                        
	 * code here: Forgotpwd Forgotpassword = new Forgotpwd();
	 * this.setVisible(false); Forgotpassword.setVisible(true);
	 * }                                             
	 */

	private void forgotPwdActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_forgotPwdActionPerformed
		// TODO add your handling code here:
		Forgotpwd Forgotpassword = new Forgotpwd();
		this.setVisible(false);
		Forgotpassword.setVisible(true);
	}// GEN-LAST:event_forgotPwdActionPerformed

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed"
		// desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase
		 * /tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Login.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Login.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Login.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Login.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				new Login().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton forgotPwd;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JButton loginButton;
	private javax.swing.JPasswordField password;
	private javax.swing.JButton registerButton;
	private javax.swing.JTextField username;
	// End of variables declaration//GEN-END:variables
}
