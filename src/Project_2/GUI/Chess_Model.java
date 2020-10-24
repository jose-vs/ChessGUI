/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_2.GUI;

import Project_2.*;
import java.util.Observable;

/**
 *
 * @author Jose
 */
public class Chess_Model extends Observable {
    
    public Chess_DB db;
    public Data data; 
    
    public String username;
    
 
    
    public Chess_Model() { 
        this.db = new Chess_DB(); 
        this.db.dbSetup();
    }
    
    public void checkName(String username, String password) { 
        
        this.username = username; 
        this.data = this.db.validateUser(username, password);
        
        if(data.isLoggedIn){ 
            
            //enter menu to check if the user wants to select a game or start a new game
        }
        
        this.setChanged();
        this.notifyObservers(this.data);
       
        
        
    }
    
    /**
     * methods for handling the game itself 
     */
    
}
