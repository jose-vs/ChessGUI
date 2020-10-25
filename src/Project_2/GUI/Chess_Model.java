/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_2.GUI;

import Project_2.Data;
import Project_2.*;
import java.util.Observable;

/**
 *
 * @author Jose
 */
public class Chess_Model extends Observable {
    
    public Chess_DB db;
    public Data data = new Data(); 
    
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
    
    public void openCreateUser() { 
        
        data.isCreatingNewUser = true;
        this.setChanged();
        this.notifyObservers(this.data);
    }
    
    public void createUser(String username, String password) { 
        
        this.username = username; 
        this.data = this.db.insertUser(username, password); 
        
        this.setChanged(); 
        this.notifyObservers(this.data);
        
    }
    
    public void back(Data data) { 
        
        this.data = data;
        this.data.backClicked = true;
        
        if (this.data.isCreatingNewUser){ 
            this.data.isCreatingNewUser = false;
        }
        
        this.setChanged();
        this.notifyObservers(this.data);
        
    }
    
    /**
     * methods for handling the game itself 
     */
    
}
