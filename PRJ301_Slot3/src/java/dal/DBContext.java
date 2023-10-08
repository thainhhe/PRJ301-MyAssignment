/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package dal;

import entity.BaseEntity;
import jakarta.servlet.annotation.WebServlet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Admin
 */
@WebServlet(name="DBContext", urlPatterns={"/DBContext"})
public abstract class DBContext<T extends BaseEntity> {
   protected Connection connection;
   
   public DBContext()
   {
       try {
           String url = "jdbc:sqlserver://LAPTOP-S76U273F\\SQLEXPRESS04:1433;databaseName=PRJ301_FALL2023_Morning";
           String user = "sa";
           String pass = "123";
           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           connection = DriverManager.getConnection(url, user, pass);
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
       }
       
   }
   
   public abstract ArrayList<T> list();
   public abstract void insert(T entity);
   public abstract void update(T entity);
   public abstract void delete(T entity);
   public abstract T get(T entity);
}