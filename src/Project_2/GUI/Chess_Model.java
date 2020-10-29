/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_2.GUI;

import Game.*;
import Project_2.Data;
import Project_2.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;

/**
 *
 * @author Jose
 */
public class Chess_Model extends Observable {
    
    public Chess_DB db;
    public Data data = new Data(); 
    
    public String username;
    public String gameID;
   
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
        
        System.out.println(this.username);
        System.out.println(this.gameID);
        
        this.data = this.db.getMoveHistory(gameID, username, this.data);
        this.data.menu = MENU_STATE.MOVE_HISTORY;
        
        this.setChanged(); 
        this.notifyObservers(this.data);
    }
    
    public void startNewGame(String gameID){ 
        if (this.data.menu != MENU_STATE.NEW_GAME)
            this.data.menu = MENU_STATE.NEW_GAME;
        else { 
            this.data.menu = MENU_STATE.START_GAME;
            this.data.storedGames.add(gameID);
            this.gameID = gameID;
            this.data.setMoveHistory("");
            this.data.createNewGame();
            
            System.out.println("NEW GAME CREATED " + gameID +" \nat username: " + this.username );
        }
        
        this.setChanged(); 
        this.notifyObservers(this.data);
    }
    
    public void continueGame() { 
        Game game = this.db.loadGame(this.gameID, this.username);
        this.data.setMoveHistory(game.moveHistory);
        this.data.game = game; 
        this.data.menu = MENU_STATE.START_GAME;
        
        this.setChanged(); 
        this.notifyObservers(this.data);       
    }
    
    public void saveGame(){
        
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
       Date date = new Date(); 
       this.data.game.datePlayed = formatter.format(date);      
       this.db.insertGametoDB(username, this.gameID, this.data.game);
       
       this.setChanged(); 
       this.notifyObservers(this.data); // changed?
       
    }
    
    
    /**
     * GOES BACK TO THE PREVIOUS MENU
     * @param data 
     */
    public void back(Data data) { 
        
        switch (data.menu) {
            
            case NEW_USER :
            case GAME_SELECT_MENU :
            case MOVE_HISTORY :
                
                this.data.menu = MENU_STATE.START_MENU;
                break;
                
            case GAME_FINISHED :   
            case NEW_GAME :
                
                this.data.menu = MENU_STATE.GAME_SELECT_MENU;
                break;
            case PIECE_SELECTED :
            case NEW_POS_SELECTED :
            case START_GAME : 
                
                this.data.setMoveHistory(data.game.moveHistory);
                this.data.menu = MENU_STATE.GAME_SELECT_MENU;
            
        }
        
        this.setChanged();
        this.notifyObservers(this.data);
    }
    
    public void getGame(String gameID) { 
      
        this.gameID = gameID; 
        System.out.println(gameID);
        
    }
    
    public void movePiece(int xPos, int yPos) { 
        
        System.out.println(data.game.player1.isLoser);
        System.out.println(data.game.player2.isLoser);
        
        if(!data.game.player1.isLoser && !data.game.player2.isLoser ) {
            
            if(data.menu == MENU_STATE.PIECE_SELECTED ) {

                try { 

                    data.game.move(data.startPos, xPos, yPos);

                } catch (NullPointerException e) { 

                        System.err.println("No piece selected");
                } 

                data.menu = MENU_STATE.START_GAME;
                System.out.println(data.game.gameBoard.toString());

            } else {

                data.startPos = data.game.getSquare(xPos, yPos);
                data.menu = MENU_STATE.PIECE_SELECTED;
            }
            
            data.setMoveHistory(data.game.moveHistory);

        }
        if (data.game.player1.isLoser || data.game.player2.isLoser){ 
            data.menu = MENU_STATE.GAME_FINISHED;
        }
 
        this.setChanged();
        this.notifyObservers(this.data);
    }
   
}
