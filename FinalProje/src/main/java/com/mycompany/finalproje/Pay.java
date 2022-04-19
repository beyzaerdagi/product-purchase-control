package com.mycompany.finalproje;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Pay extends javax.swing.JFrame {

    List<String> payPlan;
    List<Double> oneInstallmentFee;
    DefaultListModel dflt_payPlan;
    DefaultTableModel dflt_basket;
    int customerId;
    int basketPrice = 0;
    double amount = 0;
    LocalDate date = LocalDate.parse("2021-05-05");

    public Pay(int customerId) throws SQLException {
        initComponents();
        this.customerId = customerId;
        payPlan = new ArrayList<>();
        oneInstallmentFee = new ArrayList<>();
        dflt_payPlan = new DefaultListModel();
        lst_payPlan.setModel(dflt_payPlan);
        dflt_basket = new DefaultTableModel();
        dflt_basket.setColumnIdentifiers(new Object[]{"Product Name", "Count"});
        tbl_basket.setModel(dflt_basket);
        getBasket();
        getBasketPrice();
    }
    Exit frame;

    private Pay() {
        initComponents();
    }

    //there is no product name in the basket database, bring the same one from the product database 
    //as the product id in the basket
    public boolean getBasket() throws SQLException {

        dflt_basket.setRowCount(0);
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/SaleDB", "sa", "as");
            Statement stm = con.createStatement();
            String query = "SELECT P.PRODUCTNAME,COUNT FROM BASKETS B JOIN PRODUCTS P ON (P.PRODUCTID = B.PRODUCTID)"
                    + " WHERE B.CUSTOMERID = " + customerId;

            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("PRODUCTNAME");
                int count = rs.getInt("COUNT");
                dflt_basket.addRow(new Object[]{name, count});
            }
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Pay.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (con != null && !(con.isClosed())) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pay.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //export received product to orders database
    public void insertOrders(int orderId, int productId, String productName, int count, int price, LocalDate date) throws SQLException {

        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/SaleDB", "sa", "as");
            Statement stm = con.createStatement();
            String query = "SELECT COUNT (*) FROM ORDERS";
            ResultSet rs = stm.executeQuery(query);
            query = "INSERT INTO ORDERS (ORDERID,CUSTOMERID,PRODUCTID,PRODUCTNAME,COUNT,PRICE,ORDERDATE) "
                    + "VALUES(" + orderId + ", " + customerId + "," + productId + ",'" + productName + "'," + count + "," + price + ",'" + date + "')";
            stm.executeUpdate(query);
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Pay.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (con != null && !(con.isClosed())) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //export received product to payments database
    public void insertPayments(int orderId, double amountPrice, LocalDate date) {

        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/SaleDB", "sa", "as");
            Statement stm = con.createStatement();
            String query = "SELECT COUNT (*) FROM PAYMENTS";
            ResultSet rs = stm.executeQuery(query);
            query = "INSERT INTO PAYMENTS (CUSTOMERID,ORDERNO,AMOUNT,DUEDATE,ISPAID) "
                    + "VALUES(" + customerId + ", " + orderId + "," + amountPrice + ",'" + date + "',0)";
            stm.executeUpdate(query);
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Pay.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (con != null && !(con.isClosed())) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pay.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            Logger.getLogger(Pay.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productId;
    }

    //id price of the product entered
    public int getProductPrice(int productID) throws SQLException {

        Connection con = null;
        int productPrice = 0;
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/SaleDB", "sa", "as");
        Statement stm = con.createStatement();
        String query = "SELECT PRODUCTPRICE FROM PRODUCTS WHERE PRODUCTID = " + productID;
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) {
            productPrice = rs.getInt("PRODUCTPRICE");
        }
        con.close();
        try {
            if (con != null && !(con.isClosed())) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pay.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productPrice;
    }

    //automatically set order id
    public int orderID() {

        int orderId = 0;
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/SaleDB", "sa", "as");
            Statement stm = con.createStatement();
            String query = "SELECT max(ORDERID) as ORDERID FROM ORDERS";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                orderId = rs.getInt("ORDERID");
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Pay.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (con != null && !(con.isClosed())) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pay.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderId;
    }

    //fetches order id using customer id
    public int getOrderNo() throws SQLException {

        int orderNo = 0;
        Connection con = null;
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/SaleDB", "sa", "as");
        Statement stm = con.createStatement();
        String query = "SELECT ORDERID FROM ORDERS WHERE CUSTOMERID = " + customerId;
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) {
            orderNo = rs.getInt("ORDERID");
        }
        con.close();
        try {
            if (con != null && !(con.isClosed())) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pay.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderNo;
    }

    public void stockUpdate(String name, int count) throws SQLException {

        //deducts quantity of product from product stock and creates new stock
        int newStock = getProductStock(name) - count;
        Connection con = null;
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/SaleDB", "sa", "as");
        Statement stm = con.createStatement();
        String query = "UPDATE PRODUCTS SET STOCKCOUNT = " + newStock + "WHERE PRODUCTNAME = '" + name + "'";
        int rs = stm.executeUpdate(query);
        con.close();
        try {
            if (con != null && !(con.isClosed())) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //find the inventory of the product whose product name was entered
    public int getProductStock(String name) throws SQLException {

        int stockCount = 0;
        Connection con = null;
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/SaleDB", "sa", "as");
        Statement stm = con.createStatement();
        String query = "SELECT STOCKCOUNT FROM PRODUCTS WHERE PRODUCTNAME = '" + name + "'";
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
            Logger.getLogger(Pay.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stockCount;
    }

    //finds basket price using customer id
    public void getBasketPrice() throws SQLException {

        Connection con = null;
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/SaleDB", "sa", "as");
        Statement stm = con.createStatement();
        String query = "SELECT * FROM BASKETS WHERE CUSTOMERID = " + customerId;
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) {
            int count = rs.getInt("COUNT");
            int productPrice = getProductPrice(rs.getInt("PRODUCTID"));
            //multiplies the price of the product as much as the product received and adds it to the total price
            basketPrice += count * productPrice;
        }
        txt_basketPrice.setText(String.valueOf(basketPrice));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        txt_basketPrice = new javax.swing.JTextField();
        lbl_basketPrice = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        cmbx_installmentOption = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_basket = new javax.swing.JTable();
        btn_payPlan = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lst_payPlan = new javax.swing.JList<>();
        chcbx_acceptPay = new javax.swing.JCheckBox();
        btn_exit = new javax.swing.JButton();
        lbl_basket = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jSplitPane2.setDividerLocation(90);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jPanel3.setBackground(new java.awt.Color(182, 61, 243));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PAYMENT", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Black", 1, 15), new java.awt.Color(0, 0, 0))); // NOI18N

        txt_basketPrice.setEditable(false);

        lbl_basketPrice.setFont(new java.awt.Font("Segoe UI Black", 1, 17)); // NOI18N
        lbl_basketPrice.setForeground(new java.awt.Color(0, 0, 0));
        lbl_basketPrice.setText("Total Price:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(lbl_basketPrice)
                .addGap(27, 27, 27)
                .addComponent(txt_basketPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(113, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_basketPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_basketPrice))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane2.setTopComponent(jPanel3);

        jPanel4.setBackground(new java.awt.Color(221, 176, 250));

        cmbx_installmentOption.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        cmbx_installmentOption.setForeground(new java.awt.Color(0, 0, 0));
        cmbx_installmentOption.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3 installment", "6 installment", "9 installment" }));

        jScrollPane1.setViewportView(tbl_basket);

        btn_payPlan.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        btn_payPlan.setForeground(new java.awt.Color(0, 0, 0));
        btn_payPlan.setText("SHOW PAYMENT PLAN");
        btn_payPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_payPlanActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(lst_payPlan);

        chcbx_acceptPay.setFont(new java.awt.Font("Segoe UI Black", 1, 15)); // NOI18N
        chcbx_acceptPay.setForeground(new java.awt.Color(0, 0, 0));
        chcbx_acceptPay.setText("I accept the payment plan");
        chcbx_acceptPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chcbx_acceptPayActionPerformed(evt);
            }
        });

        btn_exit.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        btn_exit.setForeground(new java.awt.Color(0, 0, 0));
        btn_exit.setText("EXIT");
        btn_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exitActionPerformed(evt);
            }
        });

        lbl_basket.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lbl_basket.setForeground(new java.awt.Color(0, 0, 0));
        lbl_basket.setText("BASKET");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(lbl_basket)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(chcbx_acceptPay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                        .addComponent(btn_exit))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(cmbx_installmentOption, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_payPlan))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbl_basket)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_payPlan)
                    .addComponent(cmbx_installmentOption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chcbx_acceptPay)
                    .addComponent(btn_exit))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane2.setRightComponent(jPanel4);

        getContentPane().add(jSplitPane2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chcbx_acceptPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chcbx_acceptPayActionPerformed

        int orderNo = 0;
        if (chcbx_acceptPay.isSelected()) {
            chcbx_acceptPay.setEnabled(false);
            //give a message if accept to pay
            JOptionPane.showMessageDialog(rootPane, "Payment transaction was successfully executed.", "Information message",
                    JOptionPane.INFORMATION_MESSAGE);
            //add 1 to the maximum order found
            int orderId = orderID() + 1;
            //navigating the table using for loop
            for (int i = 0; i < tbl_basket.getRowCount(); i++) {
                String productName = (String) tbl_basket.getValueAt(i, 0);
                int count = (int) tbl_basket.getValueAt(i, 1);
                try {
                    int productId = getProductId(productName);
                    int productPrice = getProductPrice(productId);
                    insertOrders(orderId, productId, productName, count, productPrice, date);
                    stockUpdate(productName, count);
                } catch (SQLException ex) {
                    Logger.getLogger(Pay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                orderNo = getOrderNo();
            } catch (SQLException ex) {
                Logger.getLogger(Pay.class.getName()).log(Level.SEVERE, null, ex);
            }
            switch (cmbx_installmentOption.getSelectedIndex()) {
                case 0:
                    addPayment(3, orderNo, oneInstallmentFee, date);
                    break;
                case 1:
                    addPayment(6, orderNo, oneInstallmentFee, date);
                    break;
                case 2:
                    addPayment(9, orderNo, oneInstallmentFee, date);
                    break;
            }
        }
    }//GEN-LAST:event_chcbx_acceptPayActionPerformed
    public void addPayment(int num, int orderId, List amount, LocalDate date) {

        int idx = 0;
        for (int i = 1; i <= num; i++) {
            //adds a month to date
            LocalDate newDate = date.plusMonths(i);
            insertPayments(orderId, (double) amount.get(idx), newDate);
            idx++;
        }
    }

    //Transfers the payment plan added to the List to the table
    public void showList() {

        dflt_payPlan.removeAllElements();
        for (Object object : payPlan) {
            dflt_payPlan.addElement(object);
        }
        payPlan.removeAll(payPlan);
    }

    public void addPay(int num, double amount) {

        for (int i = 1; i <= num; i++) {
            //if the sum of the divided installments does not amount to the total price,
            //the remaining money is added to the last amount
            if ((i == num) && (amount * i < basketPrice)) {
                amount += basketPrice - (amount * i);
            }
            //adds a month to date
            LocalDate newDate = date.plusMonths(i);
            //added to the payment plan list
            payPlan.add(i + ".installment    " + amount + "    " + newDate);
            //installment price is added to installment list
            oneInstallmentFee.add(amount);
        }
    }
    private void btn_payPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_payPlanActionPerformed

        switch (cmbx_installmentOption.getSelectedIndex()) {
            //payment plan is calculated according to combo box option
            case 0:
                oneInstallmentFee.removeAll(oneInstallmentFee);
                this.amount = basketPrice / 3;
                addPay(3, this.amount);
                showList();
                break;
            case 1:
                oneInstallmentFee.removeAll(oneInstallmentFee);
                this.amount = basketPrice / 6;
                addPay(6, this.amount);
                showList();
                break;
            case 2:
                oneInstallmentFee.removeAll(oneInstallmentFee);
                this.amount = basketPrice / 9;
                addPay(9, this.amount);
                showList();
                break;
        }
    }//GEN-LAST:event_btn_payPlanActionPerformed

    private void btn_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exitActionPerformed

        int sure = JOptionPane.showConfirmDialog(rootPane, "Would you like to add an order note or view your account?",
                "", JOptionPane.YES_NO_OPTION);
        if (sure == JOptionPane.YES_OPTION) {
            try {
                //Transferring the user's id to the next frame
                frame = new Exit(customerId);
                //closing this frame
                dispose();
                //switching to another frame
                frame.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(Pay.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                //if customer wants out,basket is cleared
                ClearStuff.clearBasket();
                dispose();
            } catch (SQLException ex) {
                Logger.getLogger(Pay.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
            java.util.logging.Logger.getLogger(Pay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pay().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_exit;
    private javax.swing.JButton btn_payPlan;
    private javax.swing.JCheckBox chcbx_acceptPay;
    private javax.swing.JComboBox<String> cmbx_installmentOption;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JLabel lbl_basket;
    private javax.swing.JLabel lbl_basketPrice;
    private javax.swing.JList<String> lst_payPlan;
    private javax.swing.JTable tbl_basket;
    private javax.swing.JTextField txt_basketPrice;
    // End of variables declaration//GEN-END:variables
}
