/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_2;

import Game.*;
import java.util.ArrayList;

/**
 *
 * @author Jose
 */
public class Data {
    
    public MENU_STATE menu; 
    public User_Data u_data;
    
    public String gameID;
    public String moveHistory;
    public ArrayList<String> storedGames;
    
    public Game game;
    
    
    public Data() { 
        menu = MENU_STATE.START_MENU;
        
        storedGames = new ArrayList<>();
        game = new Game();
    }
    
    public void createNewGame() { 
        Game newGame = new Game(); 
        this.game = newGame;
    }
   
}
