/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_2.GUI;

import Game.*;
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
    
    public void startNewGame(String gameID){ 
        if (this.data.menu != MENU_STATE.NEW_GAME)
            this.data.menu = MENU_STATE.NEW_GAME;
        else { 
            this.data.menu = MENU_STATE.START_GAME;
            this.data.gameID = gameID;
            this.data.setMoveHistory("");
            this.data.createNewGame();
            System.out.println("NEW GAME CREATED " + gameID);
        }
        
        this.setChanged(); 
        this.notifyObservers(this.data);
    }
    
    public void saveGame(){ 
        System.out.println(this.username);
       this.db.insertGametoDB(username, this.data.gameID);
    }
    
    
    /**
     * GOES BACK TO THE PREVIOUS MENU
     * @param data 
     */
    public void back(Data data) { 
        
        switch (data.menu) {
            
            case NEW_USER:
                
                this.data.menu = MENU_STATE.START_MENU;
                break;
                
            case GAME_SELECT_MENU:
            case MOVE_HISTORY:
                this.data.menu = MENU_STATE.START_MENU;
                break;
                
            case NEW_GAME:
                this.data.menu = MENU_STATE.GAME_SELECT_MENU;
                break;
            
        }
        
        this.setChanged();
        this.notifyObservers(this.data);
    }
    
    public void getGameID(String gameID) { 
        this.data.gameID = gameID;
        this.setChanged();
        this.notifyObservers(this.data);
    }
    
    public void movePiece(int xPos, int yPos) { 
        
        // ony update when the 
        Game game = data.game; 
        
        if(data.menu == MENU_STATE.PIECE_SELECTED) {
            game.move(data.startPos, xPos, yPos);
            data.menu = MENU_STATE.NEW_POS_SELECTED;
            
        } else {
        
            data.startPos = game.getSquare(xPos, yPos);
            System.out.println(data.startPos.getPiece().getRank());
            data.menu = MENU_STATE.PIECE_SELECTED;
        }
        
        data.setMoveHistory(game.moveHistory);
        data.game = game;
        
            System.out.println(data.moveHistory+"\n");
            System.out.println(data.moveHistoryHTML +"\n");
        
        this.setChanged();
        this.notifyObservers(this.data);
        
        //data.game.move(bSquare, xPos, yPos);
    }
    
    
   // public void gameSetup() 
    
    
    
    
}
