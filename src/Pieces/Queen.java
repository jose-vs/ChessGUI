package Pieces;

import Game.*;
import Exception.InvalidPathException;
/**
* This class stores data relating to the movement limitations of the Queen Piece,
* Setting the rank to Queen and returning whether or not the move is Valid.
*
* @author David Anderson 19065861
* @author Jose Anderson 17993442
*
*/
public class Queen extends Piece {

	public Queen(Player player){
		/**
		 * This constructor initializes Player. And sets the rank of this piece
		 * to Queen.
		 *
		 *  @param player a Player object containing the Player and their side
		 *  @author David Anderson 19065861
		 **/
	    super(player);
	    this.setRank(Rank.QUEEN);
	}

	@Override
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
		int xChange = Math.abs(xDes - bSquare.getxPos());
		int yChange = Math.abs(yDes - bSquare.getyPos());

		if((xChange == yChange) || (xDes == bSquare.getxPos()) || (yDes == bSquare.getyPos())){  // As the Queen piece can move any direction except Leap
			return true;
		}
		else{
			throw new InvalidPathException();
		}
	}

	@Override
	public int[][] drawMove(int xStart, int yStart, int xDes, int yDes) {
		/**
		 * This method draws a path from one point to another based on the piece type and returns the coordinates
		 * As the queen piece can move any direction, this method includes all the other pieces
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
		int pairs;
		int xDirection = 0;
		int yDirection = 0;

		if (yDes == yStart)
		{
			pairs = Math.abs(xDes - xStart);
			if (xDes - xStart < 0)
			{
				xDirection = -1;
			}
			else
				xDirection = 1;

		}
		else if (xDes == xStart)
		{
			pairs = Math.abs(yDes - yStart);
			if (yDes - yStart < 0)
			{
				yDirection = -1;
			}
			else
				yDirection = 1;

		}
		else
		{
			pairs = Math.abs(xDes - xStart);

			if (xDes - xStart < 0)
			{
				xDirection = -1;
			}
			else
				xDirection = 1;

			if (yDes - yStart < 0)
			{
				yDirection = -1;
			}
			else
				yDirection = 1;
		}

		int[][] journey = new int[2][pairs];

		if (pairs - 1 > 0)
		{
			for (int i = 0; i < pairs - 1; i++)
			{
				journey[0][i] = xStart + xDirection;
				journey[1][i] = yStart + yDirection;
			}
		}
		return journey;
	}

}
