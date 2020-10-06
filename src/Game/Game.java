package Game;

import Exception.*;
import Pieces.*;

/**
 * Stores information about a Game including
 * 		* the date and time the game was played
 * 		* the move history of the game
 * 		* each player involved in the game
 * 		* the game board
 * handles most of the functionality of the whole game
 * checks if every move is valid
 *
 * @author Jose Santos 		17993442
 * @author David Anderson 	19065861
 */

public class Game {

	public Board gameBoard;
	public Player player1, player2;
	public int turnCount;
	public String moveHistory;
	public String datePlayed;
	public boolean isCapture; // to show if the move results in a capture


	/**
	 * Constructor for starting a new Game
	 * @author Jose Santos 		17993442
	 */
	public Game() {
		this.turnCount = 0;
		this.moveHistory = "";
		this.gameSetup();

	}


	/**
	 * Constructor for loading a previous game
	 * @param turnCount
	 * @param moveHistory
	 * @param gameBoard
	 * @author Jose Santos 		17993442
	 */
	public Game(int turnCount, String moveHistory, Board gameBoard, String datePlayed, Player player1, Player player2) {

		this.turnCount = turnCount;
		this.moveHistory = moveHistory;
		this.gameBoard = gameBoard;
		this.player1 = player1;
		this.player2 = player2;
		this.player1.game = this;
		this.player2.game = this;
		this.datePlayed = datePlayed;

		addPieces();

	}


	/**
	 * sets up the game with two players
	 * @author Jose Santos 		17993442
	 */
	public void gameSetup() {

		gameBoard = new Board();
		this.setPlayer();

	}


	/**
	 * initializes both players
	 * currently with player 1 as white and player 2 as black
	 * @author Jose Santos 		17993442
	 */
	public void setPlayer() {

		player1 = new Player(Side.WHITE);
		assignPieces(player1);
		player1.isTurn = true;

		player2 = new Player(Side.BLACK);
		assignPieces(player2);

		player1.game = this;
		player2.game = this;

		addPieces();

	}


	/**
	 * intilializes piece objects with the player object and determines
	 * which side the player is
	 *
	 * @param player	player object
	 * @author Jose Santos 		17993442
	 */
	public void assignPieces(Player player) {

		if(player.getSide() == Side.WHITE){

			gameBoard.setPlayerWhite(player);

		} else if(player.getSide() == Side.BLACK) {

			gameBoard.setPlayerBlack(player);

		}
	}


	/**
	 * adds all available pieces in the current game to each player
	 * @author Jose Santos 		17993442
	 */
	public void addPieces() {

		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j <= 7; j++) {

				/**
				 * checks each square on the board which side it is to determine
				 * which player to assign the pieces to
				 */
				if (this.gameBoard.board[i][j].toString().substring(1, 9).replaceAll("\\s+","").charAt(0) == 'W') {

					player1.pieces.add(this.gameBoard.board[i][j].getPiece());

				} else if ((this.gameBoard.board[i][j].toString().substring(1, 9).replaceAll("\\s+","").charAt(0) == 'B')){

					player2.pieces.add(this.gameBoard.board[i][j].getPiece());

				}
			}
		}
	}


	/**
	 * used to get a specific boardsquare object using a string
	 * @param piecePos a string representation of the coordinates in the board
	 * @return returns a boardsquare object
	 * @author Jose Santos 	17993442
	 */
	public BoardSquare getSquare(String piecePos) {

		String p = piecePos.toUpperCase();

			return gameBoard.board[(int)p.charAt(1)-49][(int)p.charAt(0)-65];

	}


	/**
	 * a function that is used to determine if a specific move to a new location is valid
	 * 	it has to match the criteria of:
	 * 		1) the move is within the boundary of the board
	 * 		2) the move cannot be the same as the starting position
	 * 		3) the move cannot land on another piece from the same side
	 * 		4) the players king is not in check (the player will only be able to move the king)
	 * 		5) there are no other pieces on its path unless it the piece can leap over others
	 * @param bSquare the boardsquare with a piece that we are trying to relocate somewhere else
	 * @param newPiecePos	the coordinates to the new piece position
	 * @author Jose Santos 		17993442
	 */
	public void move(BoardSquare bSquare, String newPiecePos) {

		this.isCapture = false; // resets is capture
		String p = newPiecePos.toUpperCase();

		int yDes = (int)p.charAt(1)-49;
		int xDes = (int)p.charAt(0)-65;

		//TODO: make it so that pieces can't move if the kind is in check

		int[][] path = bSquare.getPiece().drawMove(bSquare.getxPos(), bSquare.getyPos(), xDes, yDes);

		try {

			// checks if the move is valid
			if ((inBoundary(xDes, yDes) && hasMoved(bSquare, xDes, yDes) && isValidEndPoint(bSquare, xDes, yDes) &&
					isLeapingValid(bSquare.getPiece(), path) && bSquare.getPiece().isValidPath(bSquare, xDes, yDes) &&
					isTurn(bSquare)) && (!player1.isLoser || !player2.isLoser))

				if (isCapture(bSquare, xDes, yDes)) {

					gameBoard.board[yDes][xDes].setPiece(null);

				}

				writeMoveHistory(bSquare, newPiecePos);
				movePiece(bSquare, newPiecePos);

		} catch(MoveOutOfBoundsException | IllegalLeapingException |
				InvalidEndPointException | PieceHasNotMovedException |
				InvalidPathException | NotPlayersTurnException e) {

			e.printStackTrace();
		}

	}


	/**
	 * Checks if the new position is within the boundary of the board
	 * @param xDes 	coordinate on the x plane
	 * @param yDes	coordinate on the y plane
	 * @return boolean showing if the new position is within the boundary
	 * @throws MoveOutOfBoundsException
	 * @author Jose Santos 		17993442
	 */
	public boolean inBoundary(int xDes, int yDes) throws MoveOutOfBoundsException {

		if ((xDes >= 0 && xDes < 8) && (yDes >= 0 && yDes < 8)) {

			return true;

		} else {

			throw new MoveOutOfBoundsException();

		}

	}


	/**
	 * Checks if the move actually moves the piece out of its original position
	 * @param bSquare	the square on the board with the piece we are currently checking
	 * @param xDes		x destination coordinate
	 * @param yDes		y destination coordinate
	 * @return		returns a boolean on whether the piece has moved or not
	 * @throws PieceHasNotMovedException
	 * @author Jose Santos 		17993442
	 */
	public boolean hasMoved(BoardSquare bSquare, int xDes, int yDes) throws PieceHasNotMovedException {

			if ((bSquare.getxPos() != xDes) || (bSquare.getyPos() != yDes)) {

				return true;

			} else {

				throw new PieceHasNotMovedException();

			}

	}


	/**
	 * checks whether the move made is valid by if the destination coordinates is currently unoccupied.
	 * if it is, then it will check whether the piece occupying it is from the same side, otherwise, it will return true
	 * @param bSquare	the square containing the current piece we are checking
	 * @param xDes		x destination coordinate
	 * @param yDes		y destination coordinate
	 * @return		a boolean that tells whether or not the move is a valid end point
	 * @throws InvalidEndPointException
	 * @author Jose Santos 		17993442
	 */
	public boolean isValidEndPoint(BoardSquare bSquare, int xDes, int yDes) throws InvalidEndPointException {

		if ((gameBoard.board[yDes][xDes].getPiece() == null) ||  // checks if the coordinates doesn't have a piece.
				((gameBoard.board[yDes][xDes].getPiece() != null) && // checks if the current spot is occupied
				gameBoard.board[yDes][xDes].getPiece().getPlayer().getSide() != bSquare.getPiece().getPlayer().getSide())) { // and make sure if that piece does not belong to the player side

			return true;

		} else {

			throw new InvalidEndPointException();

		}
	}
        
	/**
	 * checks if the move made to the destination coordinate will result in capturing an enemy piece
	 * @param bSquare	the square containing the piece we are currently checking
	 * @param xDes		x destination coordinate
	 * @param yDes		y destination coordinate
	 * @return			returns a boolean that tells if the move made will make a capture
	 * @author Jose Santos 		17993442
	 */
	public boolean isCapture(BoardSquare bSquare, int xDes, int yDes) {

		if(gameBoard.board[yDes][xDes].getPiece() != null && gameBoard.board[yDes][xDes].getPiece().getPlayer().getSide() != bSquare.getPiece().getPlayer().getSide()) {

			// if the king is the piece that was captured, set the player to the loser of the game
			if (gameBoard.board[yDes][xDes].getPiece().getRank() == Rank.KING) {

				gameBoard.board[yDes][xDes].getPiece().getPlayer().isLoser = true;
			}

			//removes the piece from players hashset
			if (bSquare.getPiece().getPlayer().getSide() == this.player1.getSide()) {

				this.player2.pieces.remove(gameBoard.board[yDes][xDes].getPiece());


			} else if (bSquare.getPiece().getPlayer().getSide() == this.player2.getSide()){

				this.player1.pieces.remove(gameBoard.board[yDes][xDes].getPiece());

			}

			this.isCapture = true;
			return true;

		} else {

			return false;
		}
	}


	/**
	 * Checks if a piece can leap over another piece
	 * @param 	piece 		the piece that the function is checking
	 * @param 	movePath	an array of coordinates the piece takes
	 * @return				returns true if leaping is true
	 * @throws 	IllegalLeapingException
	 * @author Jose Santos 		17993442
	 */
	public boolean isLeapingValid(Piece piece, int[][] movePath) throws IllegalLeapingException {

		if(piece.getRank() == Rank.KNIGHT) // knights can leap
			return true;

		//pawns only have a path under special circumstances. kings will never have a path
		if (piece.getRank() == Rank.PAWN || piece.getRank() == Rank.KING)
			return true;

		int pairs = movePath[0].length;

		for (int i = 0; i < pairs - 1; i++) {

			if (gameBoard.board[movePath[1][i]][movePath[0][i]].getPiece() != null)
				throw new IllegalLeapingException();
		}

		return true;
	}


	/**
	 * Determines if it is the player who is in the bsquares's turn
	 * checks the piece that is currently in it
	 * @param bSquare		the bSquare that the user chose
	 * @return				true if it is the players turn
	 * @throws NotPlayersTurnException
	 * @author Jose Santos 		17993442
	 */
	public boolean isTurn(BoardSquare bSquare) throws NotPlayersTurnException {

		//checks if the square has a piece and it is the pieces turn
		if (bSquare.getPiece() != null && bSquare.getPiece().getPlayer().isTurn == true) {

			// switch turn
			if (bSquare.getPiece().getPlayer().getSide() == player1.getSide()) {

				player1.isTurn = false;
				player2.isTurn = true;
				this.turnCount++;

			} else {

				player1.isTurn = true;
				player2.isTurn = false;

			}

			return true;
		}

		throw new NotPlayersTurnException();

	}


	/**
	 * moves a piece from the board to a chosen location and sets the original position to null
	 * @param bSquare		the initial square containing the piece we want to move to a different location
	 * @param newPiecePos	a string that contains the coordinates to the new location we want to place the piece at
	 * @author Jose Santos 		17993442
	 */
	public void movePiece(BoardSquare bSquare, String newPiecePos) {

		String p = newPiecePos.toUpperCase();

		int xDes = (int)p.charAt(0)-65;
		int yDes = (int)p.charAt(1)-49;
		int xStart = bSquare.getxPos();
		int yStart = bSquare.getyPos();

		gameBoard.board[yDes][xDes].setPiece(bSquare.getPiece());
		gameBoard.board[yStart][xStart].setPiece(null);

	}


	/**
	 * writes the format for the move history of the whole game
	 * @param move		the users input
	 * @author Jose Santos 		17993442
	 */
	public void writeMoveHistory(BoardSquare bSquare, String move) {
		String round = "";

		if (bSquare.getPiece().getPlayer().getSide() == player1.getSide()) {

			round += this.turnCount + ". ";

			round += moveNotation(bSquare, move);

		} else {
			round += moveNotation(bSquare, move) + "\n";

		}

		this.moveHistory += round;
	}


	/**
	 * writes the notation for each individual move a player takes
	 * @param bSquare	gets the piece in the square
	 * @param move		the users input
	 * @return			returns a chess move in chess algebraic notation
	 * @author Jose Santos 		17993442
	 */
	public String moveNotation(BoardSquare bSquare, String move) {

		//TODO: add if king is in check

		Piece piece = bSquare.getPiece();


		String m = "";

		if (piece instanceof Pawn == false) {
			if (piece instanceof Knight)
				m += "n";
			else
				m += piece.getClass().getName().toLowerCase().charAt(7);

		}

		if (this.isCapture)
			m += "x";

		m += move;

		// if (king in check
		//		m += "+"

		//if (checkmate)
		//		m += "#"

		return String.format("%8s", m);
	}


	/**
	 * identifies whether this game is finished and who won
	 * also shows what date and time it was saved
	 * @author Jose Santos 		17993442
	 */
	public String toString() {

		String returnStr = "";

		if (this.player1.isLoser || this.player2.isLoser)
			returnStr += this.player1.isLoser ? "Black Won!			" : "White Won			";
		else
			returnStr += this.player1.isTurn ? 	"Unfinished, Whites turn	" : "Unfinished, Blacks turn	";

		returnStr += this.datePlayed;

		return returnStr;

	}

}
