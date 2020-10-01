package Pieces;

import Game.*;
import Exception.InvalidPathException;
/**
* This class stores data relating to the movement limitations of the Rook Piece,
* Setting the rank to Rook and returning whether or not the move is Valid.
*
* @author David Anderson 19065861
* @author Jose Santos 17993442
*
*/
public class Rook extends Piece{

	public Rook(Player player){
	/**
	 * This constructor initializes Player. And sets the rank of this piece
	 * to Rook.
	 *
	 *  @param player a Player object containing the Player and their side
	 *  @author David Anderson 19065861
	 **/
	    super(player);
	    this.setRank(Rank.ROOK);
	}

	@Override
	public boolean isValidPath(BoardSquare bSquare, int xDes, int yDes) throws InvalidPathException  {
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
		if ((xDes == bSquare.getxPos() )|| (yDes == bSquare.getyPos()) == true){
			return true;
		}
		else{
			throw new InvalidPathException();
		}
	}

	@Override
	public int[][] drawMove(int xStart, int yStart, int xDes, int yDes)
	{
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
		int pairs;
		int xDir = 0 , yDir = 0;


		if (xDes - xStart != 0 && yStart == yDes) // rook is traveling horizontally
		{
			pairs = Math.abs(xDes - xStart);
			if (xDes - xStart < 0)
			{
				xDir = -1;
			}
			else
				xDir = 1;
		}
		else // rook is traveling vertically
		{
			pairs = Math.abs(yDes - yStart);
			if (yDes - yStart < 0)
			{
				yDir = -1;
			}
			else
			{
				yDir = 1;
			}
		}

		int[][] journey = new int[2][pairs];
		if (pairs - 1 > 0)
		{
			for(int i = 0; i < pairs - 1; i++)
			{
				journey[0][i] = xStart + xDir;
				journey[1][i] = yStart + yDir;
			}
		}
		return journey;
	}
}
