/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SettingsFrame.java
 *
 * Created on Oct 30, 2011, 7:37:41 PM
 */
package edu.ncsu.csc573.project.viewlayer.gui;

import edu.ncsu.csc573.project.commlayer.CommunicationServiceFactory;
import edu.ncsu.csc573.project.common.ConfigurationManager;
import edu.ncsu.csc573.project.common.messages.ChangePasswordRequestMessage;
import edu.ncsu.csc573.project.common.messages.EnumOperationType;
import edu.ncsu.csc573.project.common.messages.EnumParamsType;
import edu.ncsu.csc573.project.common.messages.IParameter;
import edu.ncsu.csc573.project.common.messages.IRequest;
import edu.ncsu.csc573.project.common.messages.IResponse;
import edu.ncsu.csc573.project.common.messages.Parameter;
import edu.ncsu.csc573.project.controllayer.Controller;
import edu.ncsu.csc573.project.controllayer.Session;
import java.io.File;
import java.math.BigInteger;
import javax.swing.JFileChooser;

import org.apache.log4j.Logger;

/**
 *
 * @author krishna
 */
public class SettingsFrame extends javax.swing.JFrame {

    /** Creates new form SettingsFrame */
    public SettingsFrame() {
        initComponents();
        downloadfolder.setText(ConfigurationManager.getInstance().getDownloadDirectory().getAbsolutePath());
        publishfolder.setText(ConfigurationManager.getInstance().getPublishDirectory().getAbsolutePath());
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        publishfolder = new javax.swing.JTextField();
        publishchoosebutton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        oldpwd = new javax.swing.JLabel();
        newpwd = new javax.swing.JLabel();
        confnewpwd = new javax.swing.JLabel();
        changepwd = new javax.swing.JButton();
        oldpasswd = new javax.swing.JPasswordField();
        newpasswd = new javax.swing.JPasswordField();
        confnewpasswd = new javax.swing.JPasswordField();
        back = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        downloadfolder = new javax.swing.JTextField();
        downloadchoosebutton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(450, 250, 0, 0));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Settings");

        jLabel2.setText("Change the folder to share");

        publishfolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                publishfolderActionPerformed(evt);
            }
        });

        publishchoosebutton.setText("Browse");
        publishchoosebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                publishchoosebuttonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel3.setText("Change Password");

        oldpwd.setText("Old Password");

        newpwd.setText("New Password");

        confnewpwd.setText("Confirm New Password");

        changepwd.setText("Change Password");
        changepwd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changepwdActionPerformed(evt);
            }
        });

        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        jLabel4.setText("Change the download folder");

        downloadfolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadfolderActionPerformed(evt);
            }
        });

        downloadchoosebutton.setText("Browse");
        downloadchoosebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadchoosebuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(oldpwd)
                            .addComponent(newpwd)
                            .addComponent(confnewpwd))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(confnewpasswd, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(newpasswd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(oldpasswd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(downloadfolder, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(downloadchoosebutton))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(publishfolder, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(publishchoosebutton)))
                        .addGap(12, 12, 12))
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(changepwd)
                        .addGap(48, 48, 48)
                        .addComponent(back)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {confnewpwd, newpwd, oldpwd});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {confnewpasswd, newpasswd, oldpasswd});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {back, publishchoosebutton});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {downloadfolder, publishfolder});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(publishfolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(publishchoosebutton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(downloadchoosebutton)
                    .addComponent(downloadfolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addGap(25, 25, 25)
                .addComponent(jLabel3)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(oldpwd)
                    .addComponent(oldpasswd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newpwd)
                    .addComponent(newpasswd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confnewpwd)
                    .addComponent(confnewpasswd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(changepwd)
                    .addComponent(back))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {back, changepwd});

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void changepwdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changepwdActionPerformed
// TODO add your handling code here:
  String pwd = newpasswd.getText();
  String confpwd = confnewpasswd.getText();
  int compare = pwd.compareTo(confpwd);
  int length = pwd.length(); 
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
            try {
              Controller.getInstance().changePasswd(newpasswd.getText(), oldpasswd.getText());
              PasswdChangeSuccess();
            }
            //Comm instance to change password
            catch (Exception ex) {
                PasswdChangeFail();
            }
      }
    //Comm instance to change password
}//GEN-LAST:event_changepwdActionPerformed

public JFileChooser fileChooser() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Select Folder");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        return chooser;
    }

private void publishfolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_publishfolderActionPerformed

}//GEN-LAST:event_publishfolderActionPerformed

private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
// TODO add your handling code here:
    this.setVisible(false);
    //Search newSearch = new Search();
    //newSearch.setVisible(true);
}//GEN-LAST:event_backActionPerformed

private void publishchoosebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_publishchoosebuttonActionPerformed
    JFileChooser chooser = fileChooser();
    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        publishfolder.setText(chooser.getSelectedFile().getAbsolutePath());
    } 
    try {
        ConfigurationManager.getInstance().setPublishDirectory(new File(publishfolder.getText()));
         UpdateDoneSuccess();
    } catch (Exception e) {
        Updatefail();
    }   
}//GEN-LAST:event_publishchoosebuttonActionPerformed

private void downloadfolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadfolderActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_downloadfolderActionPerformed

private void downloadchoosebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadchoosebuttonActionPerformed
// TODO add your handling code here:
    JFileChooser chooser = fileChooser();
    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        downloadfolder.setText(chooser.getSelectedFile().getAbsolutePath());
    } 
     try {
        ConfigurationManager.getInstance().setDownloadDirectory(new File(downloadfolder.getText()));
         UpdateDoneSuccess();
    } catch (Exception e) {
        Updatefail();
    }   
}//GEN-LAST:event_downloadchoosebuttonActionPerformed

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
            java.util.logging.Logger.getLogger(SettingsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SettingsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SettingsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SettingsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            //@Override
            public void run() {
                new SettingsFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JButton changepwd;
    private javax.swing.JPasswordField confnewpasswd;
    private javax.swing.JLabel confnewpwd;
    private javax.swing.JButton downloadchoosebutton;
    private javax.swing.JTextField downloadfolder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField newpasswd;
    private javax.swing.JLabel newpwd;
    private javax.swing.JPasswordField oldpasswd;
    private javax.swing.JLabel oldpwd;
    private javax.swing.JButton publishchoosebutton;
    private javax.swing.JTextField publishfolder;
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

    private void PasswdChangeSuccess() {
        RegisterErrors passwdRegisterSuccess = new RegisterErrors();
        passwdRegisterSuccess.PasswordSucess();
        passwdRegisterSuccess.setVisible(true);
    }
       private void PasswdChangeFail() {
        RegisterErrors passwdRegisterFail = new RegisterErrors();
        passwdRegisterFail.PasswordFail();
        passwdRegisterFail.setVisible(true);
    }
     private void UpdateDoneSuccess() {
        RegisterErrors update = new RegisterErrors();
        update.UpdateSuccess();
        update.setVisible(true);
    }
     private void Updatefail() {
        RegisterErrors update = new RegisterErrors();
        update.UpdateFail();
        update.setVisible(true);
    }
}