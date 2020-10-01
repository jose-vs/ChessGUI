package Pieces;

import Exception.InvalidPathException;
import Game.*;
/**
* This class stores data relating to the movement limitations of the Pawn Piece,
* Setting the rank to Pawn and returning whether or not the move is Valid.
*
* @author David Anderson 19065861
* @author Jose Santos 17993442
*
*/
public class Pawn extends Piece{


	public Pawn(Player player) {
		/**
		 * This constructor initializes Player. And sets the rank of this piece
		 * to Pawn.
		 *
		 * @param player a Player object containing the Player and their side
		 * @author David Anderson 19065861
		 **/
		super(player);
		this.setRank(Rank.PAWN);
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
		 * @throws InvalidPathException An exception that prints out relevant information if User enters an invalid path for a Pawn piece.
		 *
		 * @author David Anderson 19065861
		 */
		if ((isMoveTwiceValid(bSquare, xDes, yDes))||
				(isCaptureValid(bSquare, xDes, yDes)) ||
				(isMoveForwardValid(bSquare, xDes, yDes))) {

			return true;
		} else {
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
		 * @return path an integer array containing the coordinates of the given path
		 *
		 * @author David Anderson 19065861
		 */
		int pairs = 0;
		int[][] path = new int[2][pairs];

		return path;

	}


	protected boolean isMoveTwiceValid(BoardSquare bSquare, int xDes, int yDes) {
		/**
		 * This method determines if the pawn piece can move twice, as if a pawn starts off, it can make two moves forward
		 *
		 * @param bSquare a BoardSquare object containing the position of piece within the board
		 * @param xDes an Integer containing the x Axis destination
		 * @param yDes an Integer containing the y Axis destination
		 *
		 * @return a boolean regarding whether or not the pawn piece has moved or not
		 *
		 * @author David Anderson 19065861
		 */
		int yDifference = Math.abs(yDes - bSquare.getyPos());

		BoardSquare squareDes = this.getPlayer().game.gameBoard.board[yDes][xDes];

		return((yDifference == 2) &&
				(((this.getPlayer().getSide() == Side.WHITE) && (bSquare.getyPos() == 1) && (squareDes.getPiece() == null )||
				((this.getPlayer().getSide() == Side.BLACK) && (bSquare.getyPos() == 6) && (squareDes.getPiece() == null )))));

	}

	protected boolean isCaptureValid(BoardSquare bSquare, int xDes, int yDes) {
		/**
		 * This method determines if the pawn piece can move diagonal and take the enemy piece
		 *
		 * @param bSquare a BoardSquare object containing the position of piece within the board
		 * @param xDes an Integer containing the x Axis destination
		 * @param yDes an Integer containing the y Axis destination
		 *
		 * @return a boolean regarding whether or not the pawn can move diagonal and capture
		 *
		 * @author David Anderson 19065861
		 */
		int yDiffAbs = Math.abs(yDes - bSquare.getyPos());
		int xDiffAbs = Math.abs(xDes - bSquare.getxPos());
		int yDiff = yDes - bSquare.getyPos();

		BoardSquare squareDes = this.getPlayer().game.gameBoard.board[yDes][xDes];

		if((xDiffAbs == yDiffAbs) && (yDiffAbs == 1)) {

			if (this.getPlayer().getSide() == Side.WHITE && ((squareDes.getPiece() != null
					&& (squareDes.getPiece().getPlayer().getSide() == Side.BLACK) && yDiff > 0)))
				return true;
			else if (this.getPlayer().getSide() == Side.BLACK && ((squareDes.getPiece() != null
					&& (squareDes.getPiece().getPlayer().getSide() == Side.WHITE) && yDiff < 0)))
				return true;

		}

		return false;
	}

	protected boolean isMoveForwardValid(BoardSquare bSquare, int xDes, int yDes) {
		/**
		 * This method determines if the pawn piece is blocked from moving forward.
		 *
		 * @param bSquare a BoardSquare object containing the position of piece within the board
		 * @param xDes an Integer containing the x Axis destination
		 * @param yDes an Integer containing the y Axis destination
		 *
		 * @return a boolean that returns true or false, depending on whether or not the pawn is blocked by another piece infront of it
		 *
		 * @author David Anderson 19065861
		 */

		int yDiffAbs = Math.abs(yDes - bSquare.getyPos());
		int yDiff = yDes - bSquare.getyPos();

		BoardSquare squareDes = this.getPlayer().game.gameBoard.board[yDes][xDes];

		return(((this.getPlayer().getSide() == Side.WHITE && yDiff > 0 && yDiffAbs == 1) ||
				(this.getPlayer().getSide() == Side.BLACK && yDiff < 0 && yDiffAbs == 1)) &&
				squareDes.getPiece() == null && xDes == bSquare.getxPos());
	}
}
