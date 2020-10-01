package Main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Game.*;
import Pieces.*;

/**
 * Stores information about all users including
 * 		* the number of games this user has played
 * 		* all games this user has played
 * 		* the name of the User
 * updates and reads the text files and saves it onto a local variable to be used
 *
 * @author Jose Santos 		17993442
 * @author David Anderson 	19065861
 */
public class User {

	private String name;
	private int numOfGames;
	public ArrayList<Game> matchHistory;
	public File gameFile;


	/**
	 * Constructor
	 *
	 * @param userDet 	String containing the name of the user and the amount of games the user has played
	 * @author Jose Santos 		17993442
	 */
	public User(String userDet) {

		matchHistory = new ArrayList<Game>();
		String[] userInfo = userDet.split(",");

		this.setName(userInfo[0]);
		this.setNumOfGames(Integer.parseInt(userInfo[1]));
		this.gameFile = new File(this.name+"Games.txt");

		// only add games if the has user has any games stored in its text file
		if(this.getNumOfGames() > 0)
			this.addGames(gameFile);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumOfGames() {
		return this.numOfGames;
	}

	public void setNumOfGames(int numOfGames) {
		this.numOfGames = numOfGames;
	}


	/**
	 * add games read from the text files
	 *
	 * @param file 		the users text file
	 * @author Jose Santos 		17993442
	 */
	public void addGames(File file) {

		try {
			Scanner reader = new Scanner(new FileReader(file));

			String fileString = "";

			while (reader.hasNextLine())
				fileString += reader.nextLine() + "\n";

			String[] gamesInFile = fileString.split("@@");

			/**
			 * reading individual Games from the text file
			 */
			for (int i = 1; i < gamesInFile.length; i++ ) {

				String[] gameInfo = gamesInFile[i].split("@");

				/**
				 * Reading and saving the Board
				 */
				String[] board = gameInfo[2].split("\n");
				String[][] boardSquares = new String[8][8];
				BoardSquare[][] initBoardSquare = new BoardSquare[8][8];
				Player player1 = new Player(Side.WHITE);
				Player player2 = new Player(Side.BLACK);
				int yPos = 7;
				int xPos = 0;

				/**
				 * Creates a 2d String array to easily check what each square on the board contains
				 */
				for (int x = 1; x <= 8; x++) {

					//the row split up into substrings representing each square and what's contained within them
					boardSquares[x-1] = new String[]{board[x].substring(3, 12), board[x].substring(15, 24), board[x].substring(27, 36),
							board[x].substring(39, 48), board[x].substring(51, 60), board[x].substring(63, 72),
							board[x].substring(75, 84), board[x].substring(87, 96)};
				}

				/**
				 * Saves the 2d String array of the board from the file into a 2d array of BoardSquare objects
				 */
				for (String[] row: boardSquares) {
					for(String bSquare: row) {

						String square = bSquare.replaceAll("\\s+","").toLowerCase();

						if (square.charAt(0) == 'w') { // checks if the piece on the current bSquare is white

							initBoardSquare[yPos][xPos] = new BoardSquare(yPos, xPos);

							if (this.addPieces(square, player1) != null)
							initBoardSquare[yPos][xPos].setPiece(this.addPieces(square, player1));

						} else if (square.charAt(0) == 'b') { // checks if the piece on the current bSquare is black

							initBoardSquare[yPos][xPos] = new BoardSquare(yPos, xPos);

							if (this.addPieces(square, player2) != null)
								initBoardSquare[yPos][xPos].setPiece(this.addPieces(square, player2));

						} else { // if there are no pieces found, create a BoardSquare object without a piece
							initBoardSquare[yPos][xPos] = new BoardSquare(yPos, xPos);
						}
						xPos++;
					}
					yPos--;
					xPos = 0;
				}

				//Initialized Board
				Board initBoard = new Board(initBoardSquare);

				/**
				 * Reading the move History
				 */
				String moveHistory = gameInfo[3].substring(gameInfo[3].indexOf('\n')+1);

				int gameTurns = moveHistory.split("\n").length;


				/**
				 * read the status of the previous game
				 */
				String[] gameStatus = gameInfo[4].substring(gameInfo[4].indexOf('\n')+1).split(",");


				if(gameStatus[0].equals("Finished")) {

					if (gameStatus[1].equals("White won\n"))
						player2.isLoser = true;
					else if (gameStatus[1].equals("Black won\n"))
						player1.isLoser = true;

				} else if(gameStatus[0].equals("Unfinished")) {

					if (gameStatus[1].equals("White to move..\n"))
						player1.isTurn = true;
					else if (gameStatus[1].equals("Black to move..\n"))
						player2.isTurn = true;
				}

				String datePlayed = gameInfo[5].substring(gameInfo[4].indexOf('\n')+1);

				//game object loaded from a previous game
				Game initGame = new Game(gameTurns, moveHistory, initBoard, datePlayed, player1, player2);

				//add game to matchHistory
				matchHistory.add(initGame);
			}

		} catch (IOException o) {
			System.err.println("File Not Found!");
			o.printStackTrace();
		}
	}


	/**
	 * updates the users game file
	 *
	 * @param game 	arraylist storing all the games including new games made from the main
	 * @author Jose Santos 		17993442
	 */
	public void updateGames(ArrayList<Game> games) {
		try {
			FileWriter fileWriter = new FileWriter(gameFile, false);
			BufferedWriter buffer = new BufferedWriter(fileWriter);
			PrintWriter printWriter = new PrintWriter(buffer);

			//updates the number of games a User object has
			this.setNumOfGames(games.size());

			String allGameInfo = "";

			allGameInfo += games.size() + "\n";

			//Iterate through all of the elements in the ArrayList
			for(int i = 0; i < games.size(); i++) {

				allGameInfo += "@@\n@Game"+(i+1)+"\n";

				allGameInfo += "@Board\n" + games.get(i).gameBoard.toString() + "\n";

				allGameInfo += "@Move_History\n" + games.get(i).moveHistory;

				allGameInfo += "\n@Game_Status\n";

				/**
				 * determines whether the game gathered from the text file is finished or not
				 * also determines who won or who's move it is next if it is an unfinished game
				 */
				String gameStatus;
				if (games.get(i).player1.isLoser || games.get(i).player2.isLoser )
					gameStatus = games.get(i).player1.isLoser? "Finished,Black won\n" : "Finished,White won\n";
				else
					gameStatus = games.get(i).player1.isTurn? "Unfinished,White to move..\n" : "Unfinished,Black to move..\n";

				allGameInfo += gameStatus;

				allGameInfo += "@Date_Played\n";

				allGameInfo += games.get(i).datePlayed;

			}

			printWriter.print(allGameInfo);
			printWriter.close();

		} catch (IOException e) {
			System.err.println("File not Found!");
		}
	}

	/**
	 * function that determines what piece was acquired based on a string
	 *
	 * @param pieces 	name of the piece gathered from the text file
	 * @param player	black or white side player
	 * @return			a piece object
	 * @author Jose Santos 		17993442
	 */
	public Piece addPieces(String pieces, Player player) {

		if(pieces.contains("pawn")) {

			return new Pawn(player);

		} else if (pieces.contains("rook")) {

			return new Rook(player);

		} else if (pieces.contains("knight")) {

			return new Knight(player);

		} else if (pieces.contains("bishop")) {

			return new Bishop(player);

		} else if (pieces.contains("queen")) {

			return new Queen(player);

		} else if (pieces.contains("king")) {

			return new King(player);

		} else {

			return null;

		}

	}

	/**
	 * Shows the name of the user and the number of games saved
	 * @author Jose Santos 		17993442
	 */
	public String toString() {
		return this.name + ", " + this.numOfGames + " saved games.";
	}

}
