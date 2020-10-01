package Pieces;

import java.util.HashSet;

import Exception.InvalidPathException;
import Game.*;

/**
* This class stores data relating to the movement limitations of the IKing Piece,
* Setting the rank to King and returning whether or not the move is Valid.
*
* @author David Anderson 19065861
* @author Jose Santos 17993442
*
*/
public class King extends Piece {

	public King(Player player){
		/**
		 * This constructor initializes Player. And sets the rank of this piece
		 * to King.
		 *
		 *  @param player a Player object containing the Player and their side
		 *  @author David Anderson 19065861
		 **/
	    super(player);
	    this.setRank(Rank.KING);
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
            int xDifference = Math.abs(xDes - bSquare.getxPos());
            int yDifference = Math.abs(yDes - bSquare.getyPos());
            
		if((xDifference < 2) && (yDifference < 2)) {

			return true;

		} else {

			throw new InvalidPathException();
		}
	}
/*
	public boolean path(BoardSquare bSquare, int xDes, int yDes){

		int xDifference = Math.abs(xDes - bSquare.getxPos());
		int yDifference = Math.abs(yDes - bSquare.getyPos());

		return ((xDifference < 2) && (yDifference < 2));
	}



	public boolean inCheck(int xKing, int yKing) {

		HashSet<Piece> enemies = this.getPlayer().getEnemyPieces();

		for (Piece e : enemies) {

			if (canKillKing(e, xKing, yKing))
				return true;

		}

    return false;
	}

	public boolean canKillKing(Piece enemy, int xKing, int yKing) {
		Board board = enemy.getPlayer().game.gameBoard;

		if(enemy.getRank() == Rank.PAWN && (board.isValidPath(enemy, xKing, yKing) && enemy.isValidPath(xKing, yKing)))
      return true;


	}
*/	
        @Override
	public int[][] drawMove(int xStart, int yStart, int xDes, int yDes) {
		/**
		 * This method draws a path from one point to another based on the piece type and returns the coordinates
		 * King doesn't have a path (as it only can move one square in any direction)
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
		int[][] path = new int[2][pairs];

		return path;
	}
}
