/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose
 */
public class Chess_DB {
    
    Connection conn = null; 
    String url = "jdbc:derby://localhost:1527/ChessDB";
    String username = "ChessPDC"; 
    String password = "ChessPDC"; 
    Statement statement = null;
    
    public void setupChess_DB_Player() { 
        if (this.conn == null){ 
            try { 
                conn = DriverManager.getConnection(url, username, password);
                this.statement = conn.createStatement();
                String tableName = "Player";
                
                if (!tableExists(tableName))
                    this.statement.addBatch("CREATE TABLE " +tableName+ " (USERNAME VARCHAR(10), GAMESPLAYED INT) ");
                
                statement.close();
            } catch (SQLException ex){ 
                Logger.getLogger(Chess_DB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public boolean tableExists(String tableName){ 
        try { 
            System.out.println("checking for existing tables.... ");

            DatabaseMetaData dbmd = this.conn.getMetaData();
            String[] types = {"TABLE"}; 
            ResultSet rs = dbmd.getTables(null, null, null, types); 
            Statement statement = this.conn.createStatement();
            while (rs.next()){ 
                String table_name = rs.getString("TABLE_NAME"); 
                System.out.println(table_name); 
                
                if (table_name.equalsIgnoreCase(tableName)){ 
                    statement.executeUpdate("Drop table " + tableName); 
                    System.out.println("Table " + tableName + " has been deleted.");
                    return true;
                }
            }
        } catch (SQLException ex ){ 
            Logger.getLogger(Chess_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
}
