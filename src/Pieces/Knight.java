package Pieces;

import Game.*;
import Exception.InvalidPathException;
/**
* This class stores data relating to the movement limitations of the Knight Piece,
* Setting the rank to Knight and what moves are Valid.
*
* @author David Anderson 19065861
* @author Jose Santos 17993442
*
*/
public class Knight extends Piece {

	public Knight(Player player){
		/**
		 * This constructor initializes Player. And sets the rank of this piece
		 * to Knight.
		 *
		 *  @param player a Player object containing the Player and their side
		 *  @author David Anderson 19065861
		**/
	    super(player);
	    this.setRank(Rank.KNIGHT);
	}

	public boolean isValidPath(BoardSquare bSquare, int xDes, int yDes) throws InvalidPathException {
		/**
		 * This method determines if the move is possible based on the piece type
		 *
		 * @param bSquare a BoardSquare object containing the position of piece within the board
		 * @param xDes an integer variable containing the destination of the piece on the x Axis
		 * @param yDes an integer variable containing the destination of the piece on the y Axis
		 * @return a boolean indicating if the move is valid
		 * @throws InvalidPathException An exception that prints out relevant information if User enters invalid path.
		 *
		 * @author David Anderson 19065861
		 */
		int xDif = Math.abs(xDes - bSquare.getxPos());
		int yDif = Math.abs(yDes - bSquare.getyPos());

		if ((xDif == 1 && yDif == 2) || (xDif == 2 && yDif == 1)){ //Since a Knight can only travel two up and one across or two up and one across
			return true;
		}
		else {
			throw new InvalidPathException();
		}
	}

	public int[][] drawMove(int xStart, int yStart, int xDes, int yDes) {
		/**
		 * This method draws a path from one point to another based on the piece type and returns the coordinates
		 *
		 * @param xStart an Integer containing the x Axis starting point
		 * @param yStart an Integer containing the y Axis starting point
		 * @param xDes an Integer containing the x Axis destination
		 * @param yDes an Integer containing the y Axis destination
		 *
		 * @return journey an integer array containing the coordinates of the given path
		 *
		 * @author David Anderson 19065861
		 */
		int pairs = 0;
		int [][] journey = new int[2][pairs];

		return journey;
	}
}
