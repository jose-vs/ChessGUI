/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TEST_TEMP_CLASSES;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Jose
 */
public class Driver {
    public static void main(String[] args) {
        try { 
            // get a connection 
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ChessDB");
            // create a statement
            
            // execute sql query 
            
            //process the result set 
            
            
            
        } catch (Exception e) { 
            e.printStackTrace();
        }
    }
}
