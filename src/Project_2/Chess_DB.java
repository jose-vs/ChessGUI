/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose
 */
public class Chess_DB {
    
    Connection conn = null; 
    String dbURL = "jdbc:derby://localhost:1527/chessDB;create=true"; 
    String dbUSERNAME = "app"; 
    String dbPASSWORD = "app"; 
    String userTable = "C_USER"; 
    String gameTable = "C_GAME";
    
    public void dbSetup() { 
        try { 
            
            conn = DriverManager.getConnection(dbURL, dbUSERNAME, dbPASSWORD); 
            Statement statement = conn.createStatement(); 
            String userTable = "C_USER"; 
            String gameTable = "C_GAME"; 
            
            if(!checkTableExisting(userTable)) { 
                statement.executeUpdate(
                    "CREATE TABLE " +userTable+ " ("
                            + "userName VARCHAR(64), "
                            + "userPassword VARCHAR(64), "
                            + "userGames INTEGER, "
                    + "CONSTRAINT user_PK PRIMARY KEY (userName)");
            } else if(!checkTableExisting(userTable)) { 
                statement.executeUpdate(
                    "CREATE TABLE " +gameTable+ " ("
                            + "gameID VARCHAR(10), "
                            + "userID INTEGER, "
                            + "datePlayed DATE, "
                            + "gameFile VARCHAR(32), "
                    + "CONSTRAINT game_PK PRIMARY KEY (gameID, userName), "
                    + "CONSTRAINT game_user_FK FOREIGN KEY (userName) REFERENCES " +userTable+ "(userName)");
            }
            
            statement.close();
            
        } catch (Throwable e ) {
            System.err.println("ERROR");
            e.printStackTrace();
        }
    }
    
    
    /*
    
    TODO: 
    
        CREATE A METHOD TO CHECK IF THE USER EXISTS IN THE DATABASE
        CREATE A METHOD TO CHECK FOR ALL GAMES STORED IN THE DATABASE
    */
    
    public Data validateUser(String username, String password) { 
        /*
            if user is not found, ask the user if the they want to create a new user
            then insert the new username and password into the database
        */
        Data data = new Data();
        
        try { 
                       
            Statement statement = conn.createStatement(); 
            ResultSet rs = statement.executeQuery("SELECT userName, userPassword FROM "+userTable
                    + " WHERE userName = '" +username+ "'");
            
            if (rs.next()) { 
                String pass = rs.getString("userPassword"); 
                System.out.println("user found");
                
                if(password.equals(pass)) { 
                    data.isLoggedIn = true;
                } else { 
                    data.isLoggedIn = false;
                }
                
            } else { 
                
                System.out.println("No user found");
                
            }
  
        } catch (SQLException ex) {
            Logger.getLogger(Chess_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return data;
    }
    
    public Data insertUser(String username, String password) { 
        Data data = new Data(); 
        
        try { 
            
            Statement statement = conn.createStatement(); 
            ResultSet rs = statement.executeQuery("INSERT INTO " +userTable+ 
                    " VALUES ('" +username+ "', '" +password+ "', 0)");
       
            System.out.println("user created");
            
            data.userCreated = true;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Chess_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return data;
    }
    
    
    private boolean checkTableExisting(String newTableName) {
        boolean flag = false;
        try {

            System.out.println("check existing tables.... ");
            String[] types = {"TABLE"};
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, null, null);
            
            while (rsDBMeta.next()) {
                
                String tableName = rsDBMeta.getString("TABLE_NAME");
                
                if (tableName.compareToIgnoreCase(newTableName) == 0) {
                    System.out.println(tableName + "  is there");
                    flag = true;
                }
            }
            
            if (rsDBMeta != null) {
                
                rsDBMeta.close();
                
            }
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
        
        return flag;
    }
}
