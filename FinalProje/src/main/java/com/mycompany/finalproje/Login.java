package com.mycompany.finalproje;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    int customerId;

    public Login(int customerId) throws SQLException {
        initComponents();
        this.customerId = customerId;
    }

    private Login() {
        initComponents();
    }
    ProductPayment productFrame;
    SignUp signFrame;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtp_password = new javax.swing.JPasswordField();
        btn_login = new javax.swing.JButton();
        lbl_nameSurname = new javax.swing.JLabel();
        lbl_password = new javax.swing.JLabel();
        txt_userName = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_signUp = new javax.swing.JButton();

        jButton1.setText("jButton1");

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel2.setBackground(new java.awt.Color(221, 176, 250));

        btn_login.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        btn_login.setForeground(new java.awt.Color(0, 0, 0));
        btn_login.setText("SIGN IN");
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });

        lbl_nameSurname.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        lbl_nameSurname.setForeground(new java.awt.Color(0, 0, 0));
        lbl_nameSurname.setText("Username:");

        lbl_password.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        lbl_password.setForeground(new java.awt.Color(0, 0, 0));
        lbl_password.setText("Password:");

        jPanel1.setBackground(new java.awt.Color(182, 61, 243));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("LOGIN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        btn_signUp.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        btn_signUp.setForeground(new java.awt.Color(0, 0, 0));
        btn_signUp.setText("SIGN UP");
        btn_signUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_signUpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_nameSurname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_password, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtp_password, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                        .addComponent(txt_userName))
                    .addComponent(btn_signUp, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(64, 64, 64))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_nameSurname)
                    .addComponent(txt_userName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtp_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_password))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_login)
                    .addComponent(btn_signUp))
                .addGap(28, 28, 28))
        );

        getContentPane().add(jPanel2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed

        if (!isAllLetters(txt_userName.getText())) {
            //if the user name entered contains something other than a letter,gave a message using JOptionPane
            JOptionPane.showMessageDialog(rootPane, "Username can only contain letters", "Warning message",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            Connection con = null;
            try {
                //check the entered username and password from the database
                con = DriverManager.getConnection("jdbc:derby://localhost:1527/SaleDB", "sa", "as");
                Statement stm = con.createStatement();
                String queryString = "SELECT * FROM CUSTOMERS WHERE USERNAME='" + txt_userName.getText()
                        + "'AND PASSWORD='" + txtp_password.getText() + "'";
                ResultSet results = stm.executeQuery(queryString);
                if (!results.next()) {
                    //if the entries are wrong,give a message using JOptionPane
                    JOptionPane.showMessageDialog(rootPane, "Wrong username or password", "Wrong answer", JOptionPane.ERROR_MESSAGE);
                } else {
                    //if the entries are correct,add the user id to activeuser
                    customerId = results.getInt("CUSTOMERNO");
                    //transferring the user's id to the next frame
                    productFrame = new ProductPayment(customerId);
                    //closing this frame
                    dispose();
                    //switching to another frame
                    productFrame.setVisible(true);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if (con != null && !(con.isClosed())) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_loginActionPerformed

    private void btn_signUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_signUpActionPerformed

        signFrame = new SignUp();
        //closing this frame
        dispose();
        //switching to another frame
        signFrame.setVisible(true);

    }//GEN-LAST:event_btn_signUpActionPerformed
    //checked using regex to check if the entered username is all letters
    public boolean isAllLetters(String name) {
        Pattern p = Pattern.compile("^[a-zA-Z]*$");
        return p.matcher(name).find();
    }

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_login;
    private javax.swing.JButton btn_signUp;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbl_nameSurname;
    private javax.swing.JLabel lbl_password;
    private javax.swing.JTextField txt_userName;
    private javax.swing.JPasswordField txtp_password;
    // End of variables declaration//GEN-END:variables
}
