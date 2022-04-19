package com.mycompany.finalproje;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ProductPayment extends javax.swing.JFrame {

    DefaultTableModel defaultTbl_product;
    DefaultTableModel defaultTbl_payment;
    int customerId;

    public ProductPayment(int customerId) throws SQLException {
        initComponents();
        this.customerId = customerId;
        defaultTbl_product = new DefaultTableModel();
        defaultTbl_payment = new DefaultTableModel();
        defaultTbl_product.setColumnIdentifiers(new Object[]{"Product No", "Product Name", "Price"});
        tbl_products.setModel(defaultTbl_product);
        defaultTbl_payment.setColumnIdentifiers(new Object[]{"Order No", "Amount", "Payment Date"});
        tbl_payment.setModel(defaultTbl_payment);
        getProducts();
        getPayments();
    }
    Basket frame;

    private ProductPayment() throws SQLException {
    }

    //bringing the products defined in the database into the frame
    public boolean getProducts() throws SQLException {

        defaultTbl_product.setRowCount(0);
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/SaleDB", "sa", "as");
            Statement stm = con.createStatement();
            String query = "SELECT * FROM PRODUCTS ORDER BY PRODUCTID DESC";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("PRODUCTID");
                String productName = rs.getString("PRODUCTNAME");
                String price = rs.getString("PRODUCTPRICE");
                defaultTbl_product.addRow(new Object[]{id, productName, price});
            }
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductPayment.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (con != null && !(con.isClosed())) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductPayment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //bring unpaid payments defined in the database to the frame
    public boolean getPayments() throws SQLException {

        defaultTbl_payment.setRowCount(0);
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/SaleDB", "sa", "as");
            Statement stm = con.createStatement();
            String query = "SELECT * FROM PAYMENTS WHERE CUSTOMERID = " + customerId + "AND ISPAID = 0";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int orderNo = rs.getInt("ORDERNO");
                double amount = rs.getDouble("AMOUNT");
                Date date = rs.getDate("DUEDATE");
                defaultTbl_payment.addRow(new Object[]{orderNo, amount, date});
            }
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductPayment.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (con != null && !(con.isClosed())) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductPayment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //find the inventory of the product whose name was entered
    public int getProductStock(String productName) throws SQLException {

        Connection con = null;
        int stockCount = 0;
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/SaleDB", "sa", "as");
        Statement stm = con.createStatement();
        String query = "SELECT STOCKCOUNT FROM PRODUCTS WHERE PRODUCTNAME = '" + productName + "'";
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
            Logger.getLogger(ProductPayment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stockCount;
    }

    //find the order number using customer id
    public int getOrderId() throws SQLException {

        int orderId = 0;
        Connection con = null;
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/SaleDB", "sa", "as");
        Statement stm = con.createStatement();
        String query = "SELECT ORDERID FROM ORDERS WHERE CUSTOMERID = " + customerId;
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) {
            orderId = rs.getInt("ORDERID");
        }
        con.close();
        try {
            if (con != null && !(con.isClosed())) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductPayment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderId;
    }

    //replacement of the outstanding installment using the entered date, customer id, and order id
    public void payMoney(Date date) throws SQLException {

        int orderId = getOrderId();
        Connection con = null;
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/SaleDB", "sa", "as");
        Statement stm = con.createStatement();
        String query = "UPDATE PAYMENTS SET ISPAID = 1, PAYMENDDATE = '" + LocalDate.now() + "' WHERE DUEDATE = '" + date
                + "' AND ORDERNO = " + orderId + " AND CUSTOMERID = " + customerId;
        int rs = stm.executeUpdate(query);
        con.close();
        try {
            if (con != null && !(con.isClosed())) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductPayment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //add the product to the basket
    public void addBasket(int id) {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/SaleDB", "sa", "as");
            Statement stm = con.createStatement();
            String query = "SELECT COUNT (*) FROM BASKETS";
            ResultSet rs = stm.executeQuery(query);
            rs.next();
            query = "INSERT INTO BASKETS (CUSTOMERID,PRODUCTID,COUNT) VALUES (" + customerId + "," + id + ",1)";
            stm.executeUpdate(query);
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductPayment.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (con != null && !(con.isClosed())) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductPayment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tbdpn_products = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_products = new javax.swing.JTable();
        btn_basket = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lbl_products = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_payment = new javax.swing.JTable();
        btn_exit = new javax.swing.JButton();
        btn_pay = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lbl_payment = new javax.swing.JLabel();
        spn_payCount = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        tbdpn_products.setBackground(new java.awt.Color(102, 102, 102));
        tbdpn_products.setForeground(new java.awt.Color(0, 0, 0));
        tbdpn_products.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(221, 176, 250));

        tbl_products.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_productsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_products);

        btn_basket.setFont(new java.awt.Font("Segoe UI Black", 1, 15)); // NOI18N
        btn_basket.setForeground(new java.awt.Color(0, 0, 0));
        btn_basket.setText("BASKET");
        btn_basket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_basketActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(182, 61, 243));

        lbl_products.setFont(new java.awt.Font("Segoe UI Black", 1, 20)); // NOI18N
        lbl_products.setForeground(new java.awt.Color(0, 0, 0));
        lbl_products.setText("PRODUCT LIST");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(lbl_products)
                .addContainerGap(175, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbl_products)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(341, 341, 341)
                        .addComponent(btn_basket, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_basket)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbdpn_products.addTab("PRODUCTS", jPanel1);

        jPanel2.setBackground(new java.awt.Color(221, 176, 250));

        jScrollPane2.setViewportView(tbl_payment);

        btn_exit.setFont(new java.awt.Font("Segoe UI Black", 1, 15)); // NOI18N
        btn_exit.setForeground(new java.awt.Color(0, 0, 0));
        btn_exit.setText("EXIT");
        btn_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exitActionPerformed(evt);
            }
        });

        btn_pay.setFont(new java.awt.Font("Segoe UI Black", 1, 15)); // NOI18N
        btn_pay.setForeground(new java.awt.Color(0, 0, 0));
        btn_pay.setText("PAY");
        btn_pay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_payActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(182, 61, 243));

        lbl_payment.setFont(new java.awt.Font("Segoe UI Black", 1, 20)); // NOI18N
        lbl_payment.setForeground(new java.awt.Color(0, 0, 0));
        lbl_payment.setText("ACCOUNT");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(181, 181, 181)
                .addComponent(lbl_payment)
                .addContainerGap(195, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_payment)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        spn_payCount.setFont(new java.awt.Font("Segoe UI Black", 1, 15)); // NOI18N
        spn_payCount.setModel(new javax.swing.SpinnerNumberModel(1, 1, 9, 1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(spn_payCount, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_pay)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_exit))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_pay)
                    .addComponent(btn_exit)
                    .addComponent(spn_payCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        tbdpn_products.addTab("ACCOUNT", jPanel2);

        getContentPane().add(tbdpn_products);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_productsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_productsMouseClicked

        int row = tbl_products.getSelectedRow();
        int id = (int) tbl_products.getValueAt(row, 0);
        String name = (String) tbl_products.getValueAt(row, 1);
        try {
            int productStok = getProductStock(name);
            if (productStok == 0) {
                //if the desired product is not in stock, give a message 
                JOptionPane.showMessageDialog(rootPane, "This product sold out");
            } else {
                //if the product is in stock, ask if you want to add it to the cart
                int sure = JOptionPane.showConfirmDialog(rootPane, "Add basket", "", JOptionPane.YES_NO_OPTION);
                if (sure == JOptionPane.YES_OPTION) {
                    //if wants to add, add it to the basket database
                    addBasket(id);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductPayment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tbl_productsMouseClicked

    private void btn_basketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_basketActionPerformed
        try {
            //transferring the user's id to the next frame
            frame = new Basket(customerId);
            //closing this frame
            dispose();
            //switching to another frame
            frame.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(ProductPayment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_basketActionPerformed

    private void btn_payActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_payActionPerformed

        if (((int) spn_payCount.getValue() <= tbl_payment.getRowCount())) {
            //give the function by taking the date in the first row from the table
            for (int i = 0; i < (int) spn_payCount.getValue(); i++) {
                Date date = (Date) tbl_payment.getValueAt(0, 2);
                try {
                    payMoney(date);
                    //give the function by taking the date in the first row from the table
                    defaultTbl_payment.removeRow(0);
                } catch (SQLException ex) {
                    Logger.getLogger(ProductPayment.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            JOptionPane.showMessageDialog(rootPane,"You can pay for the maximum number of rows in the table");
        }
    }//GEN-LAST:event_btn_payActionPerformed
    private void btn_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exitActionPerformed
        //close frame
        dispose();
    }//GEN-LAST:event_btn_exitActionPerformed

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
            java.util.logging.Logger.getLogger(ProductPayment.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductPayment.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductPayment.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductPayment.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ProductPayment().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(ProductPayment.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_basket;
    private javax.swing.JButton btn_exit;
    private javax.swing.JButton btn_pay;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_payment;
    private javax.swing.JLabel lbl_products;
    private javax.swing.JSpinner spn_payCount;
    private javax.swing.JTabbedPane tbdpn_products;
    private javax.swing.JTable tbl_payment;
    private javax.swing.JTable tbl_products;
    // End of variables declaration//GEN-END:variables

}
