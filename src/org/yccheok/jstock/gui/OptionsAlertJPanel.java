/*
 * JStock - Free Stock Market Software
 * Copyright (C) 2010 Yan Cheng CHEOK <yccheok@yahoo.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package org.yccheok.jstock.gui;

import com.google.api.client.auth.oauth2.Credential;
import java.awt.Font;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yccheok.jstock.alert.GoogleCalendar;
import org.yccheok.jstock.alert.GoogleMail;
import org.yccheok.jstock.internationalization.GUIBundle;
import org.yccheok.jstock.internationalization.MessagesBundle;

/**
 *
 * @author  yccheok
 */
public class OptionsAlertJPanel extends javax.swing.JPanel implements JStockOptionsObserver {
    
    /** Creates new form OptionsAlertJPanel */
    public OptionsAlertJPanel() {
        initComponents();
        initCredentialEx();
    }
    
    private void signOut() {
        this.credentialEx = null;
        jCheckBox3.setSelected(false);
        org.yccheok.jstock.google.Utils.logoutCalendar();
        updateGUIState();
    }
    
    private void signIn() {
        jCheckBox3.setEnabled(false);
        jButton2.setEnabled(false);
        jButton4.setEnabled(false);
        
        SwingWorker swingWorker = new SwingWorker<Pair<Pair<Credential, String>, Boolean>, Void>() {

            @Override
            protected Pair<Pair<Credential, String>, Boolean> doInBackground() throws Exception {
                final Pair<Pair<Credential, String>, Boolean> pair = org.yccheok.jstock.google.Utils.authorizeCalendar();
                return pair;
            }
            
            @Override
            public void done() {
                Pair<Pair<Credential, String>, Boolean> pair = null;
                
                try {
                    pair = this.get();
                } catch (InterruptedException ex) {
                    JOptionPane.showMessageDialog(OptionsAlertJPanel.this, ex.getMessage(), GUIBundle.getString("OptionsAlertJPanel_Alert"), JOptionPane.ERROR_MESSAGE);
                    log.error(null, ex);
                } catch (ExecutionException ex) {
                    JOptionPane.showMessageDialog(OptionsAlertJPanel.this, ex.getMessage(), GUIBundle.getString("OptionsAlertJPanel_Alert"), JOptionPane.ERROR_MESSAGE);
                    log.error(null, ex);
                }                
                
                if (pair != null) {
                    credentialEx = pair.first;
                } else {
                    jCheckBox3.setSelected(false);
                }
                
                updateGUIState();                
            }
        };
        
        swingWorker.execute();
    }
    
    private void initCredentialEx() {
        SwingWorker swingWorker = new SwingWorker<Pair<Credential, String>, Void>() {

            @Override
            protected Pair<Credential, String> doInBackground() throws Exception {
                final Pair<Credential, String> pair = org.yccheok.jstock.google.Utils.authorizeCalendarOffline();
                return pair;
            }
            
            @Override
            public void done() { 
                Pair<Credential, String> pair = null;
                
                try {
                    pair = this.get();
                } catch (InterruptedException ex) {
                    log.error(null, ex);
                } catch (ExecutionException ex) {
                    log.error(null, ex);
                }
                
                if (pair != null) {
                    credentialEx = pair;
                    jCheckBox3.setSelected(MainFrame.getInstance().getJStockOptions().isSMSEnabled());
                }
                
                updateGUIState();
            }
        };
        
        swingWorker.execute();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jXHeader1 = new org.jdesktop.swingx.JXHeader();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jCheckBox3 = new javax.swing.JCheckBox();
        jPanel6 = new javax.swing.JPanel();
        jEditorPane3 = new javax.swing.JEditorPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/yccheok/jstock/data/gui"); // NOI18N
        jXHeader1.setDescription(bundle.getString("OptionsAlertJPanel_description")); // NOI18N
        jXHeader1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/32x32/bell.png"))); // NOI18N
        jXHeader1.setTitle(bundle.getString("OptionsAlertJPanel_Alert")); // NOI18N
        add(jXHeader1, java.awt.BorderLayout.NORTH);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("OptionsAlertJPanel_Email"))); // NOI18N

        jCheckBox2.setText(bundle.getString("OptionsAlertJPanel_SendMessageToEmails")); // NOI18N
        jCheckBox2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox2ItemStateChanged(evt);
            }
        });

        jLabel1.setText(bundle.getString("OptionsAlertJPanel_Email")); // NOI18N
        jLabel1.setEnabled(false);

        jLabel3.setText(bundle.getString("OptionsAlertJPanel_Password")); // NOI18N
        jLabel3.setEnabled(false);

        jTextField2.setEnabled(false);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jPasswordField1.setEnabled(false);
        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setText("@gmail.com");
        jLabel2.setEnabled(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/16x16/mail_send.png"))); // NOI18N
        jButton1.setText(bundle.getString("OptionsAlertJPanel_TestEmail")); // NOI18N
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/16x16/spinner.gif"))); // NOI18N

        jLabel11.setText(bundle.getString("OptionsAlertJPanel_CCCopy")); // NOI18N
        jLabel11.setEnabled(false);

        jTextField1.setEnabled(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel12.setFont(jLabel12.getFont().deriveFont(jLabel12.getFont().getSize()-1f));
        jLabel12.setText(bundle.getString("OptionsAlertJPanel_EmailExample")); // NOI18N
        jLabel12.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3))
                                .addGap(21, 21, 21))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(25, 25, 25)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7))
                            .addComponent(jLabel12))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jCheckBox2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jButton1)))
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("OptionsAlertJPanel_SMS"))); // NOI18N
        jPanel4.setLayout(new java.awt.BorderLayout(5, 5));

        jCheckBox3.setText(bundle.getString("OptionsAlertJPanel_SMSThroughGoogleCalendar")); // NOI18N
        jCheckBox3.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 0, 0));
        jCheckBox3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });
        jPanel4.add(jCheckBox3, java.awt.BorderLayout.NORTH);

        jPanel6.setLayout(new java.awt.BorderLayout());

        jEditorPane3.setEditable(false);
        jEditorPane3.setBackground(new java.awt.Color(240, 240, 240));
        jEditorPane3.setContentType("text/html"); // NOI18N
        jEditorPane3.setText(bundle.getString("OptionsAlertJPanel_FindOutHowToSetupYourMobilePhone")); // NOI18N
        jEditorPane3.setEnabled(false);
        jEditorPane3.addHyperlinkListener(new javax.swing.event.HyperlinkListener() {
            public void hyperlinkUpdate(javax.swing.event.HyperlinkEvent evt) {
                jEditorPane3jEditorPane1HyperlinkUpdate(evt);
            }
        });
        jPanel6.add(jEditorPane3, java.awt.BorderLayout.SOUTH);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel9.setText(bundle.getString("OptionsAlertJPanel_Limit")); // NOI18N
        jLabel9.setEnabled(false);
        jPanel2.add(jLabel9);

        jComboBox1.setModel(getComboBoxModel());
        jComboBox1.setEnabled(false);
        jPanel2.add(jComboBox1);

        jLabel10.setText(bundle.getString("OptionsAlertJPanel_SMSPerDay")); // NOI18N
        jLabel10.setEnabled(false);
        jPanel2.add(jLabel10);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/16x16/sms_protocol.png"))); // NOI18N
        jButton2.setText(bundle.getString("OptionsAlertJPanel_TestSMS")); // NOI18N
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/16x16/spinner.gif"))); // NOI18N
        jPanel2.add(jLabel8);

        jPanel6.add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel4.add(jPanel6, java.awt.BorderLayout.SOUTH);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("OptionsAlertJPanel_GoogleAccount"))); // NOI18N

        jLabel13.setBackground(new java.awt.Color(140, 196, 116));
        jLabel13.setFont(getRobotoLightFont());
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("username@email.com");
        jLabel13.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jLabel13.setOpaque(true);
        jPanel7.add(jLabel13);

        jButton4.setText(bundle.getString("OptionsAlertJPanel_SignOut")); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton4);

        jPanel4.add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("OptionsAlertJPanel_System"))); // NOI18N

        jCheckBox1.setText(bundle.getString("OptionsAlertJPanel_ShowAMessage")); // NOI18N
        jCheckBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox1.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jCheckBox4.setText(bundle.getString("OptionsAlertJPanel_PlayingAlertSound")); // NOI18N
        jCheckBox4.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox4ItemStateChanged(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/16x16/sound.png"))); // NOI18N
        jButton3.setText(bundle.getString("OptionsAlertJPanel_TestSound")); // NOI18N
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jCheckBox4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addComponent(jCheckBox1))
                .addGap(134, 134, 134))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox1)
                .addGap(16, 16, 16)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox4)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jTextField2.getText().trim().length() == 0)
        {
            JOptionPane.showMessageDialog(this, "Email address cannot be empty", "Empty email", JOptionPane.WARNING_MESSAGE);
            jTextField2.requestFocus();
            return;
        }

        if (jPasswordField1.getPassword().length == 0)
        {
            JOptionPane.showMessageDialog(this, "Email password cannot be empty", "Empty password", JOptionPane.WARNING_MESSAGE);
            jPasswordField1.requestFocus();
            return;
        }   

        this.testEmailSwingWorker = getTestEmailSwingWorker();
        this.updateGUIState();
        this.jButton1.requestFocus();
        this.testEmailSwingWorker.execute();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox2ItemStateChanged
        updateGUIState();
    }//GEN-LAST:event_jCheckBox2ItemStateChanged

    private void jEditorPane3jEditorPane1HyperlinkUpdate(javax.swing.event.HyperlinkEvent evt) {//GEN-FIRST:event_jEditorPane3jEditorPane1HyperlinkUpdate
        Utils.launchWebBrowser(evt);
}//GEN-LAST:event_jEditorPane3jEditorPane1HyperlinkUpdate

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.testSMSSwingWorker = getTestSMSSwingWorker();
        this.updateGUIState();
        this.jButton2.requestFocus();
        this.testSMSSwingWorker.execute();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox4ItemStateChanged
        updateGUIState();
    }//GEN-LAST:event_jCheckBox4ItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Utils.playAlertSound();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        jButton1.doClick();
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        jButton1.doClick();
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        jButton1.doClick();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        signOut();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        updateGUIState();
        
        if (jCheckBox3.isSelected()) {
            if (this.credentialEx == null) {
                signIn();
            }
        }
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    public void cancel() {
        if (this.testSMSSwingWorker != null) {
            this.testSMSSwingWorker.cancel(true);
        }

        if (this.testEmailSwingWorker != null) {
            this.testEmailSwingWorker.cancel(true);
        }
    }

    private ComboBoxModel getComboBoxModel() {
        return new javax.swing.DefaultComboBoxModel(new String[] { "1", "5", "10", "20", "50", GUIBundle.getString("OptionsAlertJPanel_Unlimited") });
    }

    @Override
    public void set(JStockOptions jStockOptions) {
        jCheckBox1.setSelected(jStockOptions.isPopupMessage());
        jCheckBox2.setSelected(jStockOptions.isSendEmail());
        //jCheckBox3.setSelected(jStockOptions.isSMSEnabled());
        jCheckBox4.setSelected(jStockOptions.isSoundEnabled());
        jTextField2.setText(Utils.decrypt(jStockOptions.getEmail()));
        jTextField1.setText(Utils.decrypt(jStockOptions.getCCEmail()));
        jPasswordField1.setText(Utils.decrypt(jStockOptions.getEmailPassword()));

        if (jStockOptions.getMaxSMSPerDay() <= 0) {
            jComboBox1.setSelectedItem(GUIBundle.getString("OptionsAlertJPanel_Unlimited"));
        }
        else {
            jComboBox1.setSelectedItem("" + jStockOptions.getMaxSMSPerDay());
        }
        
        updateGUIState();
    }

    @Override
    public boolean apply(JStockOptions jStockOptions) {
        if (jCheckBox2.isSelected()) {
            if (jTextField2.getText().trim().length() == 0)
            {
                JOptionPane.showMessageDialog(this, "Email address cannot be empty", "Empty email", JOptionPane.WARNING_MESSAGE);
                jTextField2.requestFocus();
                return false;
            }
            
            if (jPasswordField1.getPassword().length == 0)
            {
                JOptionPane.showMessageDialog(this, "Email password cannot be empty", "Empty password", JOptionPane.WARNING_MESSAGE);
                jPasswordField1.requestFocus();
                return false;
            }            
        }

        if (jCheckBox3.isSelected()) {
        }

        jStockOptions.setSoundEnabled(jCheckBox4.isSelected());
        jStockOptions.setPopupMessage(jCheckBox1.isSelected());
        jStockOptions.setSendEmail(jCheckBox2.isSelected());
        jStockOptions.setEmail(Utils.encrypt(jTextField2.getText().trim()));
        jStockOptions.setCCEmail(Utils.encrypt(jTextField1.getText().trim()));
        jStockOptions.setEmailPassword(Utils.encrypt(new String(jPasswordField1.getPassword())));
        jStockOptions.setSMSEnabled(jCheckBox3.isSelected());

        int maxSMSPerDay = -1;
        try {
            maxSMSPerDay = Integer.parseInt(jComboBox1.getSelectedItem().toString());
            jStockOptions.setMaxSMSPerDay(maxSMSPerDay);
        }
        catch (NumberFormatException exp) {
            log.error(null, exp);
            jStockOptions.setMaxSMSPerDay(-1);
        }
        

        return true;
    }

    /**
     * Update GUI components enable/disable state, according to current
     * selection.
     */
    private void updateGUIState() {
        final boolean isTestEmailDone = (testEmailSwingWorker == null || testEmailSwingWorker.isDone());
        final boolean isTestSMSDone = (testSMSSwingWorker == null || testSMSSwingWorker.isDone());

        final boolean soundState = jCheckBox4.isSelected();
        final boolean emailState = jCheckBox2.isSelected();
        final boolean smsState = jCheckBox3.isSelected();

        jButton3.setEnabled(soundState);
        jLabel1.setEnabled(emailState);
        jLabel11.setEnabled(emailState);
        jLabel2.setEnabled(emailState);
        jLabel3.setEnabled(emailState);
        jLabel12.setEnabled(emailState);
        jButton1.setEnabled(emailState && isTestEmailDone);
        jTextField2.setEnabled(emailState && isTestEmailDone);
        jTextField1.setEnabled(emailState && isTestEmailDone);
        jPasswordField1.setEnabled(emailState && isTestEmailDone);
        jCheckBox2.setEnabled(isTestEmailDone);
        jLabel7.setVisible(!isTestEmailDone);

        jButton2.setEnabled(smsState && isTestSMSDone && this.credentialEx != null);
        jCheckBox3.setEnabled(isTestSMSDone);
        
        jLabel8.setVisible(!isTestSMSDone);
        jLabel9.setEnabled(smsState && this.credentialEx != null);
        jLabel10.setEnabled(smsState && this.credentialEx != null);
        jComboBox1.setEnabled(smsState && this.credentialEx != null);
        jButton4.setEnabled(smsState && isTestSMSDone && this.credentialEx != null);
        jPanel7.setEnabled(smsState && this.credentialEx != null);
        jEditorPane3.setEnabled(smsState && this.credentialEx != null);
        
        if (this.credentialEx == null) {
            jLabel13.setText("?");
        } else {
            jLabel13.setText(credentialEx.second);
        }        
    }

    private Font getRobotoLightFont() {
        return Utils.getRobotoLightFont().deriveFont((float)16);
    }
    
    /**
     * Get Swing worker thread which performs email testing task.
     * @return Swing wroker thread which performs email testing task
     */
    private SwingWorker getTestEmailSwingWorker() {
        SwingWorker worker = new SwingWorker<Boolean, Void>() {
            @Override
            public Boolean doInBackground() {
                boolean status = true;

                try {
                    final String username = jTextField2.getText().trim();
                    final String cc_email = jTextField1.getText().trim();
                    GoogleMail.Send(
                        username,
                        new String(jPasswordField1.getPassword()),
                        username + "@gmail.com",
                        cc_email,
                        MessagesBundle.getString("info_message_congratulation_email_alert_system_is_working"),
                        MessagesBundle.getString("info_message_congratulation_email_alert_system_is_working")
                    );
                } catch (AddressException ex) {
                    log.error(null, ex);
                    status = false;
                } catch (MessagingException ex) {
                    log.error(null, ex);
                    status = false;
                }

                return status;
            }

            @Override
            public void done() {
                // The done Method: When you are informed that the SwingWorker
                // is done via a property change or via the SwingWorker object's
                // done method, you need to be aware that the get methods can
                // throw a CancellationException. A CancellationException is a
                // RuntimeException, which means you do not need to declare it
                // thrown and you do not need to catch it. Instead, you should
                // test the SwingWorker using the isCancelled method before you
                // use the get method.
                if (this.isCancelled()) {
                    // Cancelled by user explicitly. Do not perform any GUI update.
                    // No pop-up message.
                    return;
                }

                Boolean status = null;
                try {
                    status = get();
                } catch (InterruptedException ex) {
                    log.error(null, ex);
                } catch (ExecutionException ex) {
                    log.error(null, ex);
                } catch (CancellationException ex) {
                    // Some developers suggest to catch this exception, instead of
                    // checking on isCancelled. As I am not confident by merely
                    // isCancelled check can prevent CancellationException (What
                    // if cancellation is happen just after isCancelled check?),
                    // I will apply both techniques. 
                    log.error(null, ex);
                }

                OptionsAlertJPanel.this.updateGUIState();

                if (status == null || status == false)
                {
                    JOptionPane.showMessageDialog(OptionsAlertJPanel.this, MessagesBundle.getString("error_message_error_in_sending_email"), MessagesBundle.getString("error_title_error_in_sending_email"), JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(OptionsAlertJPanel.this, MessagesBundle.getString("info_message_email_alert_system_is_working"), MessagesBundle.getString("info_title_email_alert_system_is_working"), JOptionPane.INFORMATION_MESSAGE);
                }
            }
        };

        return worker;
    }

    private SwingWorker getTestSMSSwingWorker() {
        SwingWorker worker = new SwingWorker<Boolean, Void>() {
            @Override
            public Boolean doInBackground() {
                final boolean status = GoogleCalendar.SMS(MessagesBundle.getString("info_message_congratulation_sms_alert_system_is_working"));
                return status;
            }

            @Override
            public void done() {
                // The done Method: When you are informed that the SwingWorker
                // is done via a property change or via the SwingWorker object's
                // done method, you need to be aware that the get methods can
                // throw a CancellationException. A CancellationException is a
                // RuntimeException, which means you do not need to declare it
                // thrown and you do not need to catch it. Instead, you should
                // test the SwingWorker using the isCancelled method before you
                // use the get method.
                if (this.isCancelled()) {
                    // Cancelled by user explicitly. Do not perform any GUI update.
                    // No pop-up message.
                    return;
                }

                Boolean status = null;
                try {
                    status = get();
                } catch (InterruptedException ex) {
                    log.error(null, ex);
                } catch (ExecutionException ex) {
                    log.error(null, ex);
                } catch (CancellationException ex) {
                    // Some developers suggest to catch this exception, instead of
                    // checking on isCancelled. As I am not confident by merely
                    // isCancelled check can prevent CancellationException (What
                    // if cancellation is happen just after isCancelled check?),
                    // I will apply both techniques. 
                    log.error(null, ex);
                }

                OptionsAlertJPanel.this.updateGUIState();

                if (status == null || status == false)
                {
                    JOptionPane.showMessageDialog(OptionsAlertJPanel.this, MessagesBundle.getString("error_message_error_in_sending_sms"), MessagesBundle.getString("error_title_error_in_sending_sms"), JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(OptionsAlertJPanel.this, MessagesBundle.getString("info_message_sms_alert_system_is_working"), MessagesBundle.getString("info_title_sms_alert_system_is_working"), JOptionPane.INFORMATION_MESSAGE);
                }
            }
        };

        return worker;
    }

    private Pair<Credential, String> credentialEx;
    
    private volatile SwingWorker testSMSSwingWorker = null;
    private volatile SwingWorker testEmailSwingWorker = null;

    private static final Log log = LogFactory.getLog(OptionsAlertJPanel.class);
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JEditorPane jEditorPane3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private org.jdesktop.swingx.JXHeader jXHeader1;
    // End of variables declaration//GEN-END:variables
    
}
