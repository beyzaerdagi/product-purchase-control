package com.mycompany.finalproje;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Basket extends javax.swing.JFrame {

    DefaultTableModel dfltTbl_basket;
    int customerId;

    public Basket(int customerId) throws SQLException {
        initComponents();
        this.customerId = customerId;
        dfltTbl_basket = new DefaultTableModel();
        dfltTbl_basket.setColumnIdentifiers(new Object[]{"Product Name", "Count"});
        tbl_basket.setModel(dfltTbl_basket);
        getProducts();
    }
    Pay frame;

    private Basket() throws SQLException {
        initComponents();
    }

    //there is no product name in the basket database, bring the same one from the product database 
    //as the product id in the basket
    public boolean getProducts() throws SQLException {

        dfltTbl_basket.setRowCount(0);
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/SaleDB", "sa", "as");
            Statement stm = con.createStatement();
            String query = "SELECT P.PRODUCTNAME,COUNT FROM BASKETS B JOIN PRODUCTS P ON (P.PRODUCTID = B.PRODUCTID) "
                    + "WHERE B.CUSTOMERID = " + customerId;
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("PRODUCTNAME");
                int count = rs.getInt("COUNT");
                dfltTbl_basket.addRow(new Object[]{name, count});
            }
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Basket.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (con != null && !(con.isClosed())) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Basket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //delete using product id
    public boolean delete(int productId) throws SQLException {

        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/SaleDB", "sa", "as");
            Statement stm = con.createStatement();
            String query = "DELETE FROM BASKETS WHERE CUSTOMERID = " + customerId + " AND PRODUCTID = " + productId;
            stm.executeUpdate(query);
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Basket.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (con != null && !(con.isClosed())) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Basket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //name id of the product entered
    public int getProductId(String name) throws SQLException {

        int productId = 0;
        Connection con = null;
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/SaleDB", "sa", "as");
        Statement stm = con.createStatement();
        String query = "SELECT PRODUCTID FROM PRODUCTS WHERE PRODUCTNAME = '" + name + "'";
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) {
            productId = rs.getInt("PRODUCTID");
        }
        con.close();
        try {
            if (con != null && !(con.isClosed())) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Basket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productId;
    }

    //find the inventory of the product whose product id was entered
    public int getProductStock(int productID) throws SQLException {

        Connection con = null;
        int stockCount = 0;
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/SaleDB", "sa", "as");
        Statement stm = con.createStatement();
        String query = "SELECT STOCKCOUNT FROM PRODUCTS WHERE PRODUCTID = " + productID;
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) {
            stockCount = rs.getInt("STOCKCOUNT");
        }
        con.close();
        try {
            if (con != null && !(con.isClosed())) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Basket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stockCount;
    }

    //inventory control
    public boolean validateBasket() throws SQLException {

        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/SaleDB", "sa", "as");
            Statement stm = con.createStatement();
            String query = "SELECT * FROM BASKETS WHERE CUSTOMERID = " + customerId;
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int count = rs.getInt("COUNT");
                int stockCount = getProductStock(rs.getInt("PRODUCTID"));
                for (int i = 0; i < tbl_basket.getRowCount(); i++) {
                    //navigating the table using for loop
                    String productName = (String) tbl_basket.getValueAt(i, 0);
                    if (count > stockCount) {
                        //if the number entered is more than the stock, give a message
                        JOptionPane.showMessageDialog(rootPane, productName + " maximum from named product " + stockCount + " you can buy");
                        return false;
                    }
                }
            }
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Basket.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (con != null && !(con.isClosed())) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Basket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btn_delete = new javax.swing.JButton();
        lbl_count = new javax.swing.JLabel();
        btn_changeCount = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_basket = new javax.swing.JTable();
        btn_pay = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lbl_basket = new javax.swing.JLabel();
        txt_count = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel2.setBackground(new java.awt.Color(221, 176, 250));

        btn_delete.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        btn_delete.setForeground(new java.awt.Color(0, 0, 0));
        btn_delete.setText("DELETE");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        lbl_count.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        lbl_count.setForeground(new java.awt.Color(0, 0, 0));
        lbl_count.setText("Count:");

        btn_changeCount.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        btn_changeCount.setForeground(new java.awt.Color(0, 0, 0));
        btn_changeCount.setText("UPDATE COUNT");
        btn_changeCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_changeCountActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(tbl_basket);

        btn_pay.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        btn_pay.setForeground(new java.awt.Color(0, 0, 0));
        btn_pay.setText("PAY");
        btn_pay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_payActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(182, 61, 243));

        lbl_basket.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        lbl_basket.setForeground(new java.awt.Color(0, 0, 0));
        lbl_basket.setText("BASKET");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(233, 233, 233)
                .addComponent(lbl_basket)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lbl_basket)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lbl_count)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_count, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btn_delete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_changeCount, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_pay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 20, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_count)
                            .addComponent(txt_count, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addComponent(btn_changeCount)
                        .addGap(31, 31, 31)
                        .addComponent(btn_delete)
                        .addGap(29, 29, 29)
                        .addComponent(btn_pay))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //delete using the product id of the selected product from the table
    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed

        int row = tbl_basket.getSelectedRow();
        int productId = 0;
        try {
            productId = getProductId((String) tbl_basket.getValueAt(row, 0));
            delete(productId);
            getProducts();
        } catch (SQLException ex) {
            Logger.getLogger(Basket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_deleteActionPerformed
    //checked using regex to check if the entered count is all digit
    public boolean isAllDigit(String count) {
        String regex = "[0-9]+";
        return count.matches(regex);
    }
    private void btn_changeCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_changeCountActionPerformed

        int row = tbl_basket.getSelectedRow();
        String count = txt_count.getText();
        if (!isAllDigit(count)) {
            //if the count entered contains something other than a digit,gave a message
            JOptionPane.showMessageDialog(rootPane, "Can only enter numbers", "Warning message",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            int productId = 0;
            try {
                //bring the product id of the selected product from the table
                productId = getProductId((String) tbl_basket.getValueAt(row, 0));
            } catch (SQLException ex) {
                Logger.getLogger(Basket.class.getName()).log(Level.SEVERE, null, ex);
            }
            Connection con = null;
            try {
                //update count in basket database
                con = DriverManager.getConnection("jdbc:derby://localhost:1527/SaleDB", "sa", "as");
                Statement stm = con.createStatement();
                String query = "UPDATE BASKETS SET COUNT = " + count + "WHERE CUSTOMERID = " + customerId + " AND PRODUCTID = " + productId;
                int rs = stm.executeUpdate(query);
                con.close();
                getProducts();
            } catch (SQLException ex) {
                Logger.getLogger(Basket.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if (con != null && !(con.isClosed())) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Basket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_changeCountActionPerformed

    private void btn_payActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_payActionPerformed

        try {
            if (validateBasket()) {
                //after checking the inventory, transferring the user's id to the next frame
                frame = new Pay(customerId);
                //closing this frame
                setDefaultCloseOperation(HIDE_ON_CLOSE);
                dispose();
                //switching to another frame
                frame.setVisible(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Basket.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btn_payActionPerformed

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
            java.util.logging.Logger.getLogger(Basket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Basket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Basket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Basket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Basket().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Basket.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_changeCount;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_pay;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_basket;
    private javax.swing.JLabel lbl_count;
    private javax.swing.JTable tbl_basket;
    private javax.swing.JTextField txt_count;
    // End of variables declaration//GEN-END:variables
}
