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
    
    public String gameID;
    public String moveHistory;
    public String moveHistoryHTML;
    public ArrayList<String> storedGames;
    
    public Game game;
    public BoardSquare startPos;
    
    public Data() { 
        menu = MENU_STATE.START_MENU;
        
        
        storedGames = new ArrayList<>();
        game = new Game();
    }
    
    public void createNewGame() { 
        Game newGame = new Game(); 
        this.game = newGame;
    }
    /*
        public void setMoveHistory(String moveHistory) { 
            this.moveHistory = moveHistory;
            this.moveHistoryHTML = "<html>" +moveHistory.replaceAll("\n", "<br/>") +"</html>";
        }
   */
}
