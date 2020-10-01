package Pieces;

import Exception.InvalidPathException;
import Game.*;

/**
 * This Abstract class contains data and methods that relate to the movement of the Chess Pieces
 *
 * @author David Anderson 19065861
 * @author Jose Santos 17993442
 *
 */
public abstract class Piece {

    protected Rank rank;
    private Player player;
    private boolean captured; // get and setter

    public Piece(Player player) {
    /**
     * This constructor initializes the player object.
     * @param player Takes in a player object containing the Players side (Black or White)
     *
     * @author David Anderson 19065861
     */
       this.player = player;
    }

    public Player getPlayer() {
    	 /**
         * This is a getter for Player
         *
         * @return this.player returns the Player e.g (black)
         *
         * @author David Anderson 19065861
         */
    	return this.player;
    }

	public Rank getRank() {
		/**
		 * this a getter for Rank
		 *
		 * @return this.rank returns the Rank of the Piece e.g Pawn
		 *
		 * @author David Anderson 19065861
		 */
		return this.rank;
	}

	public void setRank(Rank rank) {
	/**
	 * this a setter for Rank
	 *
	 * @param rank takes in a Rank Enumerated type relating to the rank of the Piece
	 *
	 * @author David Anderson 19065861
	 */
		this.rank = rank;
	}

	/**
	 * determines if the move is possible based on the piece type
	 * @param xDes destination x location
	 * @param yDes destination y location
	 * @return a boolean indicating if the move is valid
	 * @throws InvalidPathException
	 *
	 * @author David Anderson 19065861
	 */
	public abstract boolean isValidPath(BoardSquare bSquare, int xDes, int yDes) throws InvalidPathException; // function that determines if the move is valid based on its rank

	
	/**
	 * draws a path from one point to another based on the piece type and returns the coordinates
	 * @param bSquare a BoardSquare object containing the position of piece within the board
	 * @param xStart x starting point
	 * @param yStart y starting point
	 * @param xDes x destination
	 * @param yDes y destination
	 * @return an array of coordinates of the given path
	 *
	 * @author David Anderson 19065861
	 */
	public abstract int[][] drawMove(int xStart, int yStart, int xDes, int yDes);


 	public String toString() {
 		/**
		 * This method is a toString to print out the Piece Position and the colour
		 *
		 * @author David Anderson 19065861
		 **/

		return String.valueOf(player.getSide().name().charAt(0)) + String.valueOf( this.getRank().name());

	}

}
