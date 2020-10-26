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
        
        if(this.data.menu ==  MENU_STATE.LOGGED_IN) { //if logged in, enter game select menu
            
            this.data = this.db.getUser(username);
            
        }
        this.setChanged();
        this.notifyObservers(this.data);
    }
    
    public void createUserMenu() { 
        
        this.data.menu = MENU_STATE.NEW_USER;
        this.setChanged();
        this.notifyObservers(this.data);
    }
    
    public void createUser(String username, String password) { 
        
        this.username = username; 
        this.data = this.db.insertUser(username, password); 
        
        this.setChanged(); 
        this.notifyObservers(this.data);
    }
    
    public void getMoveHistory() { 
        this.data = this.db.getMoveHistory(data, username);
        this.data.menu = MENU_STATE.MOVE_HISTORY;
        
        System.out.println(this.data.moveHistory);
        this.setChanged(); 
        this.notifyObservers(this.data);
    }
    
    
    /**
     * GOES BACK TO THE PREVIOUS MENU
     * @param data 
     */
    public void back(Data data) { 
        
        if (data.menu == MENU_STATE.NEW_USER)  
            data.menu = MENU_STATE.START_MENU;
        else if (data.menu == MENU_STATE.GAME_SELECT_MENU || data.menu == MENU_STATE.MOVE_HISTORY) {
            data.menu = MENU_STATE.START_MENU;
        }

        this.data = data;
        this.setChanged();
        this.notifyObservers(this.data);
      
    }
    
    /**
     * methods for handling the game itself 
     */
    
}
