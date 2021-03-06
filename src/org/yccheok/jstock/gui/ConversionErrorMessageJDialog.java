/*
 * JStock - Free Stock Market Software
 * Copyright (C) 2012 Yan Cheng CHEOK <yccheok@yahoo.com>
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

import java.io.File;
import java.text.MessageFormat;
import org.yccheok.jstock.internationalization.GUIBundle;

/**
 *
 * @author yccheok
 */
public class ConversionErrorMessageJDialog extends javax.swing.JDialog {

    /** Creates new form ConvertingErrorMessageJDialog */
    public ConversionErrorMessageJDialog(File file) {
        super();
        initComponents();
        
        String content = MessageFormat.format(GUIBundle.getString("ConversionErrorMessageJDialog_Content_template"), 
                file.getAbsolutePath());
        jEditorPane1.setText(content);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/yccheok/jstock/data/gui"); // NOI18N
        setTitle(bundle.getString("ConversionErrorMessageJDialog_Title")); // NOI18N
        setAlwaysOnTop(true);
        setModal(true);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/64x64/cry.png"))); // NOI18N
        getContentPane().add(jLabel1, java.awt.BorderLayout.WEST);

        jEditorPane1.setContentType("text/html");
        jEditorPane1.setEditable(false);
        jEditorPane1.addHyperlinkListener(new javax.swing.event.HyperlinkListener() {
            public void hyperlinkUpdate(javax.swing.event.HyperlinkEvent evt) {
                jEditorPane1HyperlinkUpdate(evt);
            }
        });
        jScrollPane1.setViewportView(jEditorPane1);
        jEditorPane1.setCaretPosition(0);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jButton1.setText(bundle.getString("ConversionErrorMessageJDialog_OK")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-591)/2, (screenSize.height-372)/2, 591, 372);
    }// </editor-fold>//GEN-END:initComponents

private void jEditorPane1HyperlinkUpdate(javax.swing.event.HyperlinkEvent evt) {//GEN-FIRST:event_jEditorPane1HyperlinkUpdate
    Utils.launchWebBrowser(evt);
}//GEN-LAST:event_jEditorPane1HyperlinkUpdate

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    dispose();
}//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
