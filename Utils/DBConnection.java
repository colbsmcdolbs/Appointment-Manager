/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.mysql.jdbc.Connection;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author colby
 */
public class DBConnection {
    
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//3.227.166.251/U07NLU";
    
    
    private static final String jbdcUrl = protocol + vendorName + ipAddress;
    
    //Driver Reference
    private static final String MYSQLJBDCDriver = "com.mysql.jdbc.Driver";
    private static Connection conn = null;
    
    private static final String username = "U07NLU";
    private static String password = "53689075762"; //Shhhhhhhhhhhhhhhh.
    
    
    public static Connection startConnection() {
        try {
            Class.forName(MYSQLJBDCDriver);
            //password = Details.getPassword();
            conn = (Connection) DriverManager.getConnection(jbdcUrl, username, password);
            System.out.println("Connection to Database Succeeded.");
        }
        catch(ClassNotFoundException e) {
            System.err.println("Class Not Found Error: " + e.getMessage());
        }
        catch(SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
        
        return conn;
    }
    
    public static void stopConnection() {
        try {
            conn.close();
            System.out.println("Connection to Database closed.");   
        }
        catch(SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
    }
    
    public Connection getConnection() {
        return this.conn;
    }
    
    public static void populateDatabase() {
        //CallableStatement statement = conn.prepareCall(jbdcUrl)
    }
}
