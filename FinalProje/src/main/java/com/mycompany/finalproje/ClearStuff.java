package com.mycompany.finalproje;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ClearStuff {

    //clear basket from database
    public static void clearBasket() throws SQLException {

        Connection con = null;
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/SaleDB", "sa", "as");
        Statement stm = con.createStatement();
        String query = "DELETE FROM BASKETS";
        stm.executeUpdate(query);
        con.close();
    }
}
