package Game;

import Pieces.*;

/**
 * Stores information about a Board including
 * 		* each individual board square
 *
 *
 * @author Jose Santos 		17993442
 * @author David Anderson 	19065861
 */


public class Board {

	public BoardSquare[][] board = new BoardSquare[8][8];

	/**
	 * Constructor for a new game
	 * @author Jose Santos 		17993442
	 */
	public Board() {

		this.intializeBoard();

	}


	/**
	 *  Constructor for loading a previous game
	 * @param initBoard		an initialized board
	 * @author Jose Santos 		17993442
	 */
	public Board(BoardSquare[][] initBoard) {

		for (int i = 0; i < initBoard.length; i++) {
			for (int j = 0; j < initBoard[i].length; j++) {
				this.board[i][j] = initBoard[i][j];
			}
		}
	}

	/**
	 * initializes the chess board with a 2d array of BoardSquare objects
	 * @author Jose Santos 		17993442
	 */
	public void intializeBoard() {

		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board.length; j++) {
				this.board[i][j] = new BoardSquare(i, j);
			}
		}

	}


	/**
	 * initializes the board of all the black side pieces
	 * @param player 	a black side player
	 * @author Jose Santos 		17993442
	 */
	public void setPlayerBlack(Player player) {

		//assign pawn
		for (int i = 0; i < 8; i++ ) {
			board[6][i].setPiece(new Pawn(player));
		}

		//assign KNIGHT
		board[7][1].setPiece(new Knight(player));
		board[7][6].setPiece(new Knight(player));

		//assign BISHOP
		board[7][2].setPiece(new Bishop(player));
		board[7][5].setPiece(new Bishop(player));

		//assign ROOK
		board[7][0].setPiece(new Rook(player));
		board[7][7].setPiece(new Rook(player));

		//assign QUEEN
		board[7][3].setPiece(new Queen(player));

		//assign KING
		board[7][4].setPiece(new King(player));


	}

	/**
	 * initializes the board of all the white side pieces
	 * @param player 	a white side player
	 * @author Jose Santos 		17993442
	 */
	public void setPlayerWhite(Player player) {

		//assign PAWN
		for (int i = 0; i < 8; i++ ) {
			board[1][i].setPiece(new Pawn(player));
		}

		//assign KNIGHT
		board[0][1].setPiece(new Knight(player));
		board[0][6].setPiece(new Knight(player));

		//assign BISHOP
		board[0][2].setPiece(new Bishop(player));
		board[0][5].setPiece(new Bishop(player));

		//assign ROOK
		board[0][0].setPiece(new Rook(player));
		board[0][7].setPiece(new Rook(player));

		//assign QUEEN
		board[0][3].setPiece(new Queen(player));

		//assign KING
		board[0][4].setPiece(new King(player));

	}


	/**
	 * prints the board in reverse order so that it will be in the proper orientation when
	 * it is blacks turn
	 * @return		a string the board in reverse orientation
	 * @author Jose Santos 		17993442
	 */
	public String reverseBoardStr() {
		String boardStr = "";

		for(int i = 0; i < board.length; i++) {
			boardStr += (i+1) + " ";

			for (int j = 0; j < board[i].length; j++) {
				boardStr += board[i][j].toString() + " ";

			}
			boardStr += "\n";
		}

		boardStr += "    ";

		for (int i = 'A'; i <= 'H'; i++) {
			boardStr += (char)i + "           ";
		}


		return boardStr;
	}


	public String toString() {

		String boardStr = "";

		for (int i = board.length-1; i >= 0; i--) {
			boardStr += (i+1) + " ";

			for (int j = 0; j < board[i].length; j++) {
				boardStr += board[i][j].toString() + " ";

			}

			boardStr += "\n";
		}

		boardStr += "    ";

		//print column
		for (int i = 'A'; i <= 'H'; i++) {
			boardStr += (char)i + "           ";
		}

		return boardStr;
	}


}
