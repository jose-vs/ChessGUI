package Pieces;
import Game.*;
import Exception.InvalidPathException;
/**
* This class stores data relating to the movement limitations of the Bishop Piece,
* Setting the rank to Bishop and what moves are Valid.

* @author David Anderson 19065861
* @author Jose Santos 17993442
*
*/
public class Bishop extends Piece {

	public Bishop(Player player) {
		/**
		 * This constructor initializes Player. And sets the rank of this piece
		 * to bishop.
		 *
		 *  @param player a Player object containing the Player and their side
		 *  @author David Anderson 19065861
		**/
		super(player);
	    this.setRank(Rank.BISHOP);
	}


	@Override
	public boolean isValidPath(BoardSquare bSquare, int xDes, int yDes) throws InvalidPathException{
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

		int xChange = Math.abs(xDes - bSquare.getxPos());
		int yChange = Math.abs(yDes - bSquare.getyPos());

		if (xChange== yChange){
			return xChange == yChange; // Since a bishop can only move diagonal the change in X and change in Y must be equal
		}
		else{
			throw new InvalidPathException();
		}
	}

	@Override
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
		int distance = Math.abs(xDes - xStart);

		int xDirection = 1;
		int yDirection = 1;
		if (xDes - xStart < 0)
			xDirection = -1;
		if (yDes - yStart < 0)
			yDirection = -1;

		int [][] journey = new int[2][distance -1];

		if (distance - 1 > 0)
		{
			for(int i = 0; i < distance - 1; i++)
			{
				journey[0][i] = xStart + (xDirection * 1);
				journey[1][i] = yStart + (yDirection * 1);
			}
		}
		return journey;
	}

}
