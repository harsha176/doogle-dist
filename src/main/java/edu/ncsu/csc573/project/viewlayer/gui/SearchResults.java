/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SearchResults.java
 *
 * Created on Oct 26, 2011, 2:05:10 AM
 */
package edu.ncsu.csc573.project.viewlayer.gui;

import edu.ncsu.csc573.project.commlayer.CommunicationServiceFactory;
import edu.ncsu.csc573.project.common.ByteOperationUtil;
import edu.ncsu.csc573.project.common.messages.EnumOperationType;
import edu.ncsu.csc573.project.common.messages.EnumParamsType;
import edu.ncsu.csc573.project.common.messages.IParameter;
import edu.ncsu.csc573.project.common.messages.IRequest;
import edu.ncsu.csc573.project.common.messages.IResponse;
import edu.ncsu.csc573.project.common.messages.LogoutRequestMessage;
import edu.ncsu.csc573.project.common.messages.Parameter;
import edu.ncsu.csc573.project.common.messages.PublishRequestMessage;
import edu.ncsu.csc573.project.common.messages.PublishSearchParameter;
import edu.ncsu.csc573.project.common.messages.SearchRequestMessage;
import edu.ncsu.csc573.project.common.schema.MatchFileParamType;
import edu.ncsu.csc573.project.controllayer.Controller;
import edu.ncsu.csc573.project.controllayer.Session;
import edu.ncsu.csc573.project.controllayer.hashspacemanagement.DigestAdaptor;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 
 * @author krishna
 */
public class SearchResults extends javax.swing.JFrame {

	// private PublishSearchParameter searchResults;
	// private static final int RESULTS_PER_PAGE = 5;
	// Result[] res = new Result[5];
	List<MatchFileParamType> allResults;
	int i = 0;

	/** Creates new form SearchResults */
	public SearchResults() {
		initComponents();
	}

	public void setMessage(List<MatchFileParamType> searchResults) {
		allResults = searchResults;
                int results = searchResults.size();
           resultsnumber.setText(results + " results found");

		if (i < searchResults.size()) {
			fname1.setText(searchResults.get(i).getFilename());
			abs1.setText(searchResults.get(i).getAbstract());
			IPAdd1.setText(searchResults.get(i).getIpaddress());
			i++;
		} else {
			download1.setVisible(false);
			download2.setVisible(false);
			download3.setVisible(false);
			download4.setVisible(false);
			download5.setVisible(false);
			noResult1();
			noResult2();
			noResult3();
			noResult4();
			noResult5();
		}
		if (i < searchResults.size()) {
			fname2.setText(searchResults.get(i).getFilename());
			abs2.setText(searchResults.get(i).getAbstract());
			IPAdd2.setText(searchResults.get(i).getIpaddress());
			i++;
		} else {
			download2.setVisible(false);
			download3.setVisible(false);
			download4.setVisible(false);
			download5.setVisible(false);
			noResult2();
			noResult3();
			noResult4();
			noResult5();
		}
		if (i < searchResults.size()) {
			fname3.setText(searchResults.get(i).getFilename());
			abs3.setText(searchResults.get(i).getAbstract());
			IPAdd3.setText(searchResults.get(i).getIpaddress());
			i++;
		} else {
			download3.setVisible(false);
			download4.setVisible(false);
			download5.setVisible(false);
			noResult3();
			noResult4();
			noResult5();
		}
		if (i < searchResults.size()) {
            fname4.setText(searchResults.get(i).getFilename());
            abs4.setText(searchResults.get(i).getAbstract());
            IPAdd4.setText(searchResults.get(i).getIpaddress());
            i++;
		} else {
			download4.setVisible(false);
			download5.setVisible(false);
			noResult4();
			noResult5();
		}
		if (i < searchResults.size()) {
            fname5.setText(searchResults.get(i).getFilename());
            abs5.setText(searchResults.get(i).getAbstract());
            IPAdd5.setText(searchResults.get(i).getIpaddress());
            i++;
		} else {
			download5.setVisible(false);
			noResult5();
		}
	}

	public void noResult1() {
		fname1.setText(null);
		abs1.setText(null);
		IPAdd1.setText(null);
	}

	public void noResult2() {
		fname2.setText(null);
		abs2.setText(null);
		IPAdd2.setText(null);
	}

	public void noResult3() {
		fname3.setText(null);
		abs3.setText(null);
		IPAdd3.setText(null);
	}

	public void noResult4() {
		fname4.setText(null);
		abs4.setText(null);
		IPAdd4.setText(null);
	}

	public void noResult5() {
		fname5.setText(null);
		abs5.setText(null);
		IPAdd5.setText(null);
		download5.setText(null);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        newSearch = new javax.swing.JTextField();
        Search = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        Previous = new javax.swing.JButton();
        Next = new javax.swing.JButton();
        fname1 = new javax.swing.JLabel();
        abs1 = new javax.swing.JLabel();
        IPAdd1 = new javax.swing.JLabel();
        fname2 = new javax.swing.JLabel();
        abs2 = new javax.swing.JLabel();
        IPAdd2 = new javax.swing.JLabel();
        fname3 = new javax.swing.JLabel();
        abs3 = new javax.swing.JLabel();
        IPAdd3 = new javax.swing.JLabel();
        fname4 = new javax.swing.JLabel();
        abs4 = new javax.swing.JLabel();
        IPAdd4 = new javax.swing.JLabel();
        fname5 = new javax.swing.JLabel();
        abs5 = new javax.swing.JLabel();
        IPAdd5 = new javax.swing.JLabel();
        download1 = new javax.swing.JButton();
        download2 = new javax.swing.JButton();
        download3 = new javax.swing.JButton();
        download4 = new javax.swing.JButton();
        download5 = new javax.swing.JButton();
        resultsnumber = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        Options = new javax.swing.JMenu();
        Settings = new javax.swing.JMenuItem();
        Publish = new javax.swing.JMenuItem();
        unpublish = new javax.swing.JMenuItem();
        Logout = new javax.swing.JMenuItem();

        jButton2.setText("jButton2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        newSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newSearchActionPerformed(evt);
            }
        });

        Search.setText("Search");
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel2.setText("Search Results");

        Previous.setText("Previous");
        Previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PreviousActionPerformed(evt);
            }
        });

        Next.setText("Next");
        Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }
        });

        fname1.setText("Filename");

        abs1.setText("Abstract");

        IPAdd1.setText("IP Address");

        fname2.setText("Filename");

        abs2.setText("Abstract");

        IPAdd2.setText("IP Address");

        fname3.setText("Filename");

        abs3.setText("Abstract");

        IPAdd3.setText("IP Address");

        fname4.setText("Filename");

        abs4.setText("Abstract");

        IPAdd4.setText("IP Address");

        fname5.setText("Filename");

        abs5.setText("Abstract");

        IPAdd5.setText("IP Address");

        download1.setText("Download");
        download1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                download1ActionPerformed(evt);
            }
        });

        download2.setText("Download");
        download2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                download2ActionPerformed(evt);
            }
        });

        download3.setText("Download");
        download3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                download3ActionPerformed(evt);
            }
        });

        download4.setText("Download");
        download4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                download4ActionPerformed(evt);
            }
        });

        download5.setText("Download");
        download5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                download5ActionPerformed(evt);
            }
        });

        resultsnumber.setText("results");

        Options.setText("Options");

        Settings.setText("Settings");
        Settings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsActionPerformed(evt);
            }
        });
        Options.add(Settings);

        Publish.setText("Publish");
        Publish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PublishActionPerformed(evt);
            }
        });
        Options.add(Publish);

        unpublish.setText("Unpublish");
        unpublish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unpublishActionPerformed(evt);
            }
        });
        Options.add(unpublish);

        Logout.setText("Logout");
        Logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutActionPerformed(evt);
            }
        });
        Options.add(Logout);

        jMenuBar1.add(Options);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(78, 78, 78)
                                .addComponent(resultsnumber))
                            .addComponent(fname1)
                            .addComponent(fname2)
                            .addComponent(fname3)
                            .addComponent(fname4)
                            .addComponent(fname5)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(IPAdd5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(download5))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(IPAdd4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(download4))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(IPAdd3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(download3))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(IPAdd2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(download2))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(IPAdd1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(download1))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(newSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(56, 56, 56)
                                    .addComponent(Search)))
                            .addComponent(abs1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(abs2, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(abs3, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(abs4, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(abs5, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(Previous)
                        .addGap(45, 45, 45)
                        .addComponent(Next)))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Next, Previous});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Search, download1});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Search))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(resultsnumber))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fname1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(abs1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IPAdd1)
                    .addComponent(download1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fname2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(abs2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IPAdd2)
                    .addComponent(download2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fname3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(abs3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IPAdd3)
                    .addComponent(download3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fname4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(abs4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IPAdd4)
                    .addComponent(download4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fname5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(abs5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IPAdd5)
                    .addComponent(download5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Previous)
                    .addComponent(Next))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void unpublishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unpublishActionPerformed
// TODO add your handling code here:
    Unpublish remove = new Unpublish();
    remove.setVisible(true);
    remove.setLocationRelativeTo(this);


}//GEN-LAST:event_unpublishActionPerformed

	private void linkButton3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_linkButton3ActionPerformed
	// TODO add your handling code here:
	}// GEN-LAST:event_linkButton3ActionPerformed

	private void newSearchActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_newSearchActionPerformed
	// TODO add your handling code here:
	}// GEN-LAST:event_newSearchActionPerformed

	private void SettingsActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_SettingsActionPerformed
	// TODO add your handling code here:
		SettingsFrame Settings = new SettingsFrame();
		Settings.setVisible(true);
		Settings.setLocationRelativeTo(this);
	}// GEN-LAST:event_SettingsActionPerformed

	private void PublishActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_PublishActionPerformed
		try {
			Logger logger = Logger.getLogger(Search.class);
			// IRequest pubRequest = PublishRequestMessage.getPublishRequest();
			String message = Controller.getInstance().publish();
			logger.info("Message is " + message);
			PublishFrame Publish = new PublishFrame();
			Publish.setVisible(true);
			Publish.setLocationRelativeTo(this);
		} catch (IOException ex) {
			PublishFrame Pubfail = new PublishFrame();
			Pubfail.setMessage("Failed to create publish request");
			Pubfail.setVisible(true);
			Pubfail.setLocationRelativeTo(this);
			Logger.getLogger(Search.class.getName()).error(
					"Failed to create publish request", ex);
		} catch (Exception e) {
			PublishFrame Publishfail = new PublishFrame();
			Publishfail.setMessage("Failed to create search request");
			Publishfail.setVisible(true);
			Publishfail.setLocationRelativeTo(this);
			Logger.getLogger(Search.class.getName()).error(
					"Failed to send request to boot strap server", e);
		}
	}// GEN-LAST:event_PublishActionPerformed

	private void LogoutActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_LogoutActionPerformed
	// TODO add your handling code here:
		Logger logger = Logger.getLogger(Search.class);
		try {
			Controller.getInstance()
					.logout(Session.getInstance().getUsername());
			loggedOut logout = new loggedOut();
			this.setVisible(false);
			logout.setVisible(true);
		} catch (Exception e) {
			logger.error("User not logged in", e);
		}
	}// GEN-LAST:event_LogoutActionPerformed

	private void NextActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_NextActionPerformed
	// TODO add your handling code here:
		this.setVisible(false);
                this.setMessage(allResults);
                this.setVisible(true);
//		newResults.setVisible(true);
		this.setLocationRelativeTo(this);
	}// GEN-LAST:event_NextActionPerformed

	private void PreviousActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_PreviousActionPerformed
		if (i>5)
                {
                i = i - 5;
                this.setVisible(false);
                this.setMessage(allResults);
                this.setVisible(true);
//		newResults.setVisible(true);
		this.setLocationRelativeTo(this);
                }
		 
	}// GEN-LAST:event_PreviousActionPerformed

	private void SearchActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_SearchActionPerformed
	// TODO add your handling code here:
		try {

			List<MatchFileParamType> searchResults = Controller.getInstance().search(newSearch.getText());
			// PublishSearchParameter searchResults =
			// (PublishSearchParameter)response.getParameter();
			// searchResults.resetCounter();

			// SearchResults newResults = new SearchResults();
			// newResults.setMessage(searchResults);
			// this.setVisible(false);
			// newResults.setVisible(true);
			// newResults.setLocationRelativeTo(this);

                SearchResults sr = new SearchResults();
		sr.setMessage(searchResults);
		this.setVisible(false);
		sr.setVisible(true);
		sr.setLocationRelativeTo(this);
		sr.setTitle("Hello " + Session.getInstance().getUsername() + " , Welcome ");
                
		} catch (IOException ex) {
			PublishFrame Searchfail = new PublishFrame();
			Searchfail.setMessage("Failed to create search request");
			Searchfail.setVisible(true);
			Searchfail.setLocationRelativeTo(this);
			Logger.getLogger(Search.class.getName()).error(
					"Failed to create search request", ex);
		} catch (Exception e) {
			PublishFrame Searchfailure = new PublishFrame();
			Searchfailure
					.setMessage("Failed to send request to boot strap server");
			Searchfailure.setVisible(true);
			Searchfailure.setLocationRelativeTo(this);
			Logger.getLogger(Search.class.getName()).error(
					"Failed to send request to boot strap server", e);
		}

	}// GEN-LAST:event_SearchActionPerformed

	private void download1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_download1ActionPerformed
	// TODO add your handling code here:
		CommunicationServiceFactory.getInstance().getFile(IPAdd1.getText(),
				fname1.getText());
                RegisterErrors download = new RegisterErrors();
                download.DownloadSuccess();
                download.setVisible(true);
                download.setLocationRelativeTo(this);
                        
	}// GEN-LAST:event_download1ActionPerformed

	private void download2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_download2ActionPerformed
	// TODO add your handling code here:
		CommunicationServiceFactory.getInstance().getFile(IPAdd2.getText(),
				fname2.getText());
                RegisterErrors download = new RegisterErrors();
                download.DownloadSuccess();
                download.setVisible(true);
                download.setLocationRelativeTo(this);
	}// GEN-LAST:event_download2ActionPerformed

	private void download3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_download3ActionPerformed
	// TODO add your handling code here:

		CommunicationServiceFactory.getInstance().getFile(IPAdd3.getText(),
				fname3.getText());
                RegisterErrors download = new RegisterErrors();
                download.DownloadSuccess();
                download.setVisible(true);
                download.setLocationRelativeTo(this);
	}// GEN-LAST:event_download3ActionPerformed

	private void download4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_download4ActionPerformed
	// TODO add your handling code here:
		CommunicationServiceFactory.getInstance().getFile(IPAdd4.getText(),
				fname4.getText());
                RegisterErrors download = new RegisterErrors();
                download.DownloadSuccess();
                download.setVisible(true);
                download.setLocationRelativeTo(this);
	}// GEN-LAST:event_download4ActionPerformed

	private void download5ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_download5ActionPerformed
	// TODO add your handling code here:
		CommunicationServiceFactory.getInstance().getFile(IPAdd5.getText(),
				fname5.getText());
                RegisterErrors download = new RegisterErrors();
                download.DownloadSuccess();
                download.setVisible(true);
                download.setLocationRelativeTo(this);
	}// GEN-LAST:event_download5ActionPerformed

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
			java.util.logging.Logger.getLogger(SearchResults.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(SearchResults.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(SearchResults.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(SearchResults.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {

			//@Override
			public void run() {
				new SearchResults().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel IPAdd1;
    private javax.swing.JLabel IPAdd2;
    private javax.swing.JLabel IPAdd3;
    private javax.swing.JLabel IPAdd4;
    private javax.swing.JLabel IPAdd5;
    private javax.swing.JMenuItem Logout;
    private javax.swing.JButton Next;
    private javax.swing.JMenu Options;
    private javax.swing.JButton Previous;
    private javax.swing.JMenuItem Publish;
    private javax.swing.JButton Search;
    private javax.swing.JMenuItem Settings;
    private javax.swing.JLabel abs1;
    private javax.swing.JLabel abs2;
    private javax.swing.JLabel abs3;
    private javax.swing.JLabel abs4;
    private javax.swing.JLabel abs5;
    private javax.swing.JButton download1;
    private javax.swing.JButton download2;
    private javax.swing.JButton download3;
    private javax.swing.JButton download4;
    private javax.swing.JButton download5;
    private javax.swing.JLabel fname1;
    private javax.swing.JLabel fname2;
    private javax.swing.JLabel fname3;
    private javax.swing.JLabel fname4;
    private javax.swing.JLabel fname5;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField newSearch;
    private javax.swing.JLabel resultsnumber;
    private javax.swing.JMenuItem unpublish;
    // End of variables declaration//GEN-END:variables
}
