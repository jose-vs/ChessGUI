package Game;


import java.util.HashSet;

import Pieces.Piece;

/**
 * Stores information about each player including
 * 		* which side they are on
 * 		* if they lost the game
 * 		* all the players current pieces 
 *
 * @author Jose Santos 		17993442
 * @author David Anderson 	19065861
 */


public class Player {
	
    private Side side;
    public Game game; // to access gameboard
    
    public HashSet<Piece> pieces;
    public boolean isLoser = false, isTurn = false; 
    
    public Player(Side side) {
    	this.pieces = new HashSet<Piece>();
    	this.side = side; 
    }
    
    public HashSet<Piece> getEnemyPieces(){ 
    	
    	if (getSide() == Side.WHITE) { 
    		return this.game.player2.pieces;
    	} else { 
    		return this.game.player1.pieces; 
    	}
    }
    
    public Side getSide() {
    	return this.side;
    }
   

}
