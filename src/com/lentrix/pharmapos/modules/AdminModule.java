/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.pharmapos.modules;

import com.lentrix.pharmapos.models.User;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author lentrix
 */
public class AdminModule extends javax.swing.JFrame {
    private User user;
    /**
     * Creates new form AdminModule
     */
    public AdminModule(User user) {
        initComponents();
        this.user = user;
        userLabel.setText("User: " + user.getFullName());
        
        new Thread() {
            public void run() {
                LocalDateTime dt;
                while(true) {
                    dt = LocalDateTime.now(ZoneId.systemDefault());
                    dateTimeLabel.setText(dt.format(DateTimeFormatter.ofPattern("MMMM d, u K:mm a")));
                    try {
                        Thread.sleep(10000);
                    }catch(Exception ex) {
                        ex.printStackTrace();
                        dateTimeLabel.setText("Error!");
                        break;
                    }
                }
            }
        }.start();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        userLabel = new javax.swing.JLabel();
        dateTimeLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        manageItemsButton = new javax.swing.JButton();
        reStockItemsButton = new javax.swing.JButton();
        manageStocksButton = new javax.swing.JButton();
        posTerminalButton = new javax.swing.JButton();
        manageUsersButton = new javax.swing.JButton();
        checkInventoryBalanceButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MDS Pharmacy - Admin");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2, 5, 0));

        userLabel.setText("User: ");
        jPanel1.add(userLabel);

        dateTimeLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jPanel1.add(dateTimeLabel);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        jPanel2.setBackground(new java.awt.Color(254, 254, 254));
        jPanel2.setPreferredSize(new java.awt.Dimension(80, 80));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/lentrix/pharmapos/images/mdslogomin.png"))); // NOI18N
        jPanel2.add(jLabel1, java.awt.BorderLayout.WEST);

        jLabel2.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        jLabel2.setText("   Administration Panel");
        jPanel2.add(jLabel2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel3.setLayout(new java.awt.GridLayout(3, 2, 10, 10));

        manageItemsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/lentrix/pharmapos/images/basket64.png"))); // NOI18N
        manageItemsButton.setText("Manage Items");
        manageItemsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        manageItemsButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        manageItemsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageItemsButtonActionPerformed(evt);
            }
        });
        jPanel3.add(manageItemsButton);

        reStockItemsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/lentrix/pharmapos/images/stock64.png"))); // NOI18N
        reStockItemsButton.setText("Re Stock Items");
        reStockItemsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        reStockItemsButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        reStockItemsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reStockItemsButtonActionPerformed(evt);
            }
        });
        jPanel3.add(reStockItemsButton);

        manageStocksButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/lentrix/pharmapos/images/managestock64.png"))); // NOI18N
        manageStocksButton.setText("Manage Stocks");
        manageStocksButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        manageStocksButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        manageStocksButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageStocksButtonActionPerformed(evt);
            }
        });
        jPanel3.add(manageStocksButton);

        posTerminalButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/lentrix/pharmapos/images/pos64.png"))); // NOI18N
        posTerminalButton.setText("POS Terminal");
        posTerminalButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        posTerminalButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        posTerminalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                posTerminalButtonActionPerformed(evt);
            }
        });
        jPanel3.add(posTerminalButton);

        manageUsersButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/lentrix/pharmapos/images/users64.png"))); // NOI18N
        manageUsersButton.setText("Manage Users");
        manageUsersButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        manageUsersButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        manageUsersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageUsersButtonActionPerformed(evt);
            }
        });
        jPanel3.add(manageUsersButton);

        checkInventoryBalanceButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/lentrix/pharmapos/images/inventory64.png"))); // NOI18N
        checkInventoryBalanceButton.setText("Check Inventory Balance");
        checkInventoryBalanceButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        checkInventoryBalanceButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel3.add(checkInventoryBalanceButton);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Manage Items");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Manage Stocks");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setText("Manage Users");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);
        jMenu1.add(jSeparator1);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem11.setText("Exit");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem11);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Transactions");

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("POS Terminal");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setText("Check Balance");
        jMenu2.add(jMenuItem5);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Re Stock Items");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Reports");

        jMenuItem9.setText("Daily Report");
        jMenu4.add(jMenuItem9);

        jMenuItem10.setText("Monthly Report");
        jMenu4.add(jMenuItem10);

        jMenuBar1.add(jMenu4);

        jMenu3.setText("Utilities");

        jMenuItem7.setText("Change Password");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuItem12.setText("Backup Data");
        jMenu3.add(jMenuItem12);

        jMenuItem13.setText("Restore from Backup");
        jMenu3.add(jMenuItem13);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        setSize(new java.awt.Dimension(702, 630));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new ReStockItemsModule(user).setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        new ChangePasswordModule(this, true, user).setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void manageItemsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageItemsButtonActionPerformed
        startManageItemsModule();
    }//GEN-LAST:event_manageItemsButtonActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        startManageItemsModule();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void reStockItemsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reStockItemsButtonActionPerformed
        startReStockItemModule();
    }//GEN-LAST:event_reStockItemsButtonActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        startManageStocksModule();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void manageStocksButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageStocksButtonActionPerformed
        startManageStocksModule();
    }//GEN-LAST:event_manageStocksButtonActionPerformed

    private void manageUsersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageUsersButtonActionPerformed
        startManageUsersModule();
    }//GEN-LAST:event_manageUsersButtonActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        startManageUsersModule();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void posTerminalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_posTerminalButtonActionPerformed
        startPOSTerminalModule();
    }//GEN-LAST:event_posTerminalButtonActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        startPOSTerminalModule();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void startManageUsersModule() {
        this.setVisible(false);
        new ManageUsersModule(this, true, user).setVisible(rootPaneCheckingEnabled);
    }
    
    private void startManageItemsModule() {
        this.setVisible(false);
        new ManageItemsModule(this, user).setVisible(true);
    }
    
    private void startManageStocksModule() {
        new ManageStocksModule(this, true, user).setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton checkInventoryBalanceButton;
    private javax.swing.JLabel dateTimeLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JButton manageItemsButton;
    private javax.swing.JButton manageStocksButton;
    private javax.swing.JButton manageUsersButton;
    private javax.swing.JButton posTerminalButton;
    private javax.swing.JButton reStockItemsButton;
    private javax.swing.JLabel userLabel;
    // End of variables declaration//GEN-END:variables

    private void startReStockItemModule() {
        new ReStockItemsModule(user).setVisible(true);
    }

    private void startPOSTerminalModule() {
        new POSTerminal(this, user).setVisible(true);
        setVisible(false);
    }
}
