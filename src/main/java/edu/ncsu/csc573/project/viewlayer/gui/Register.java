/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Register.java
 *
 * Created on Oct 26, 2011, 1:26:48 AM
 */
package edu.ncsu.csc573.project.viewlayer.gui;

import edu.ncsu.csc573.project.commlayer.CommunicationServiceFactory;
import edu.ncsu.csc573.project.common.messages.EnumOperationType;
import edu.ncsu.csc573.project.common.messages.EnumParamsType;
import edu.ncsu.csc573.project.common.messages.IParameter;
import edu.ncsu.csc573.project.common.messages.IRequest;
import edu.ncsu.csc573.project.common.messages.IResponse;
import edu.ncsu.csc573.project.common.messages.Parameter;
import edu.ncsu.csc573.project.common.messages.RegisterRequestMessage;
import java.awt.event.ItemEvent;
import java.math.BigInteger;
import java.util.regex.Pattern;
import javax.swing.ButtonGroup;

/**
 *
 * @author krishna
 */
public class Register extends javax.swing.JFrame {

    /** Creates new form Register */
    public Register() {
        initComponents();
    }
    public void submitData(){
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        group = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        firstnameUser = new javax.swing.JTextField();
        lastnameUser = new javax.swing.JTextField();
        usernameData = new javax.swing.JTextField();
        emailUser = new javax.swing.JTextField();
        passwordUser = new javax.swing.JPasswordField();
        confirmPassword = new javax.swing.JPasswordField();
        registerButton = new javax.swing.JButton();
        faculty = new javax.swing.JRadioButton();
        student = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(450, 250, 0, 0));
        setMaximizedBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("New User Registration");

        jLabel2.setText("First Name");

        jLabel3.setText("Last Name");

        jLabel4.setText("User name");

        jLabel5.setText("Email");

        jLabel6.setText("Designation");

        jLabel7.setText("Password");

        jLabel8.setText("Confirm Password");

        emailUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailUserActionPerformed(evt);
            }
        });

        registerButton.setText("Register");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        group.add(faculty);
        faculty.setText("Faculty");
        faculty.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                facultyItemStateChanged(evt);
            }
        });
        faculty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facultyActionPerformed(evt);
            }
        });

        group.add(student);
        student.setText("Student");

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(emailUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(firstnameUser, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usernameData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lastnameUser, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addGap(18, 18, 18)
                            .addComponent(passwordUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel8)
                                .addComponent(registerButton))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton1)
                                .addComponent(confirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(student)
                            .addComponent(faculty))))
                .addGap(59, 59, 59))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {confirmPassword, emailUser, firstnameUser, lastnameUser, passwordUser, usernameData});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, registerButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(firstnameUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lastnameUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(usernameData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(emailUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(student))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(faculty)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(passwordUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registerButton)
                    .addComponent(jButton1))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void emailUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailUserActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_emailUserActionPerformed

public void RadioButton (){
    group.add(faculty);
    group.add(student);
}
    
private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
// TODO add your handling code here:
    String firstname = firstnameUser.getText();
    String lastname = lastnameUser.getText();
    String emailUsers = emailUser.getText();
    String username = usernameData.getText();
    String des = null;
    String EmailRegEx = ".*"+"@"+".*"+".com$";
    if (faculty.isSelected())
    {
       des = faculty.getText(); 
    }   
    else
    {
       des = student.getText();
    }
    if(firstname == null)
    {
        FirstnameErrors();
    }
    else if (lastname == null)
    {
        LastnameErrors();
     }
    else if(username == null)
    {
      UsernameErrors();
    }

    try {
        boolean isValid = Pattern.matches(EmailRegEx,emailUsers);
         if(!isValid) {
                 EmailErrors();
            }
        } catch(NullPointerException excp){
          EmailErrors();
          }  
    String password = passwordUser.getText();
    String confpassword = confirmPassword.getText();
    int compare = password.compareTo(confpassword);
    int length = password.length();    

   if (compare !=0)
   {
        PasswdMismatchErrors();
   }
   else if (length < 6)
   {
         PasswdLengthErrors();
   }
   //Communication handler instance
   else {
    IRequest regRequest = new RegisterRequestMessage();
    IParameter Regparams = new Parameter();
    Regparams.add(EnumParamsType.USERNAME, usernameData.getText());
    Regparams.add(EnumParamsType.PASSWORD, passwordUser.getText());
    Regparams.add(EnumParamsType.FIRSTNAME, firstname);
    Regparams.add(EnumParamsType.LASTNAME, lastname);
    Regparams.add(EnumParamsType.EMAIL_ID, emailUsers);
    Regparams.add(EnumParamsType.DESIGNATION, des);
       
    regRequest.createRequest(EnumOperationType.REGISTER, Regparams);
    try{
      IResponse response = CommunicationServiceFactory.getInstance().executeRequest(regRequest);
      BigInteger statusCode = response.getStatus().getErrorId();
      if (statusCode.intValue() == 0)
      {
          this.setVisible(false);
      
      Login loginFrame = new Login();
      loginFrame.setVisible(true);
      }
      else {
          UserErrors();
      }
      }
    catch (Exception e){
        ServerErrors();
    }
    }
}//GEN-LAST:event_registerButtonActionPerformed
       

private void loginLinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginLinkActionPerformed
// TODO add your handling code here:
    this.setVisible(false);
    Login newLogin = new Login();
    newLogin.setVisible(true);
}//GEN-LAST:event_loginLinkActionPerformed

private void facultyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facultyActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_facultyActionPerformed

private void facultyItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_facultyItemStateChanged
// TODO add your handling code here:
  //  if(evt.getStateChange() == ItemEvent.SELECTED)
    //    evt.getSource().
}//GEN-LAST:event_facultyItemStateChanged

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
    this.setVisible(false);
    Login newLogin = new Login();
    newLogin.setVisible(true);
}//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Register().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField confirmPassword;
    private javax.swing.JTextField emailUser;
    private javax.swing.JRadioButton faculty;
    private javax.swing.JTextField firstnameUser;
    private javax.swing.ButtonGroup group;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField lastnameUser;
    private javax.swing.JPasswordField passwordUser;
    private javax.swing.JButton registerButton;
    private javax.swing.JRadioButton student;
    private javax.swing.JTextField usernameData;
    // End of variables declaration//GEN-END:variables

    private void PasswdMismatchErrors() {
        RegisterErrors passwdRegisterErrors = new RegisterErrors();
        passwdRegisterErrors.passwordmismatchError();
        passwdRegisterErrors.setVisible(true);
    }
        private void PasswdLengthErrors() {
        RegisterErrors passwdRegisterErrors = new RegisterErrors();
        passwdRegisterErrors.passwordLengthnotMet();
        passwdRegisterErrors.setVisible(true);
    }
        private void UserErrors() {
        RegisterErrors UserErrors = new RegisterErrors();
        UserErrors.UserError();
        UserErrors.setVisible(true);
    } 
        private void FirstnameErrors() {
        RegisterErrors FirstnameErrors = new RegisterErrors();
        FirstnameErrors.FirstnameError();
        FirstnameErrors.setVisible(true);
    }
        private void LastnameErrors() {
        RegisterErrors LastnameErrors = new RegisterErrors();
        LastnameErrors.LastnameError();
        LastnameErrors.setVisible(true);
    }
        private void UsernameErrors() {
        RegisterErrors UsernameErrors = new RegisterErrors();
        UsernameErrors.UsernameError();
        UsernameErrors.setVisible(true);
    }
        private void EmailErrors() {
        RegisterErrors EmailErrors = new RegisterErrors();
        EmailErrors.EmailError();
        EmailErrors.setVisible(true);
    }

    private void ServerErrors() {
     RegisterErrors ServerErrors = new RegisterErrors();
        ServerErrors.ServerError();
        ServerErrors.setVisible(true);   
    }

}
