/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author 2dam
 */
public class ConnectionOpenClose {
    private ResourceBundle configFile;
    private String url;
    private String user;
    private String pass;
    
    public ConnectionOpenClose(){
        configFile = ResourceBundle.getBundle("DAOConnection.DBConf", new Locale("es"));
        url = configFile.getString("URL");
        user = configFile.getString("DBUser");
        pass = configFile.getString("DBPass");
    }
    
    public Connection openConnection()throws SQLException{
        
        Connection con = null;
        try{
            con = DriverManager.getConnection(url, user, pass);
        }catch (SQLException e){
        }
        return con;
    }
    
    public void closeConnection(PreparedStatement stmt, Connection con) throws SQLException{
        
        if(stmt != null){
            stmt.close();
        }
        if(con != null){
            con.close();
        }
    }
}
