/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 * @param <T>
 */
public abstract class DBContext {

    protected Connection connection;
    public DBContext()
   {
       try {
           String url = "jdbc:sqlserver://LAPTOP-S76U273F\\SQLEXPRESS04:1433;databaseName=PROJECT";
           String user = "sa";
           String pass = "123";
           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           connection = DriverManager.getConnection(url, user, pass);
       } catch (ClassNotFoundException | SQLException ex) {
           Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
       }
       
   }


}
