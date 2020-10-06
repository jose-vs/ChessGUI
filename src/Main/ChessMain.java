package Main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

import Game.*;
import Pieces.*;

/**
 * Chess Game For PDC in AUT 2020 Semester 2
 *
 * @author Jose 17993442
 * @author David 19065861
 *
 */

public class ChessMain {

	public File profiles = new File("profiles.txt");
	public Scanner keyboard = new Scanner(System.in);

	public ArrayList<User> users;
	public ArrayList<Game> games;

	public String input;
	public int intInput;
	public Game selectedGame;
	public boolean continuingGame; // determines if the user is continuing a previously saved game


	/**
	 * Constructor
	 * @author Jose Santos 		17993442
	 */
	public ChessMain() {
		this.continuingGame = false;

		this.users = new ArrayList<User>();
		this.games = new ArrayList<Game>();
		this.readFile();
		this.baseMenu();

	}


	/**
	 * the first menu presented to the user
	 * @author Jose Santos 		17993442
	 */
	public void baseMenu() {

		do {

			if(this.users.size() != 0) {
				int x;

					System.out.println("Select which profile you would like to load "
							+ "(Select a number or write \"quit\"if you want to quit the application");

					/**
					 * prints out the list of all users inside the text file
					 * including an option to create a new user
					 */
					for ( x = 0; x <= this.users.size(); x++) {

						if (x == this.users.size()) {
							System.out.println((x+1) + ": create new user?");
						} else {

							System.out.println((x+1) + ": " + this.users.get(x).toString());
						}
					}

					this.input = keyboard.nextLine();

					//calls createuser to create a new user
					if (isNumerical(this.input) && this.intInput == this.users.size())
						createUser();

					//calls show game to show all the games the chosen user has in the text file
					else if(isNumerical(this.input) && this.intInput < this.users.size() && this.intInput >= 0)
						showGames(this.users.get(this.intInput));

			} else { // automatically creates a new user when no saved profile has been found

				System.out.println("No saved profiles found.\nCreating new user...\n");
				createUser();
			}

		} while (!this.input.replaceAll("\\s+","").equalsIgnoreCase("quit"));
	}


	/**
	 * shows the user all the games stored in the chosen profile and allows the user to
	 * choose if they want to:
	 * 		* select a game (to view move history or continue it if it is unfinished
	 * 		* start a new game
	 * 		* go back to the previous menu
	 *
	 * @param user 		the user object that was acquired by the user from the previous menu
	 * @author Jose Santos 		17993442
	 */
	public void showGames(User user) {

		System.out.println("\nWelcome " + user.getName()+"\n");
		this.games = user.matchHistory;

		do {

			/**
			 * if the user file has games stored within it, show all the games with the options
			 * if not, automatically start a new game
			 */
			if(user.getNumOfGames() != 0) {

				System.out.println(
						 	"	Type		\"select game\" 	to select a game\n"
						+ 	"			\"new game\" 	to start a new game\n"
						+ 	"			\"back\" 		to leave\n");

				this.input = keyboard.nextLine();

				// when select game has been chosen, show the user all the games
				if(this.input.replaceAll("\\s+","").equalsIgnoreCase("selectgame")) {
					int x;

					for (x = 0; x < this.games.size(); x++) {
						System.out.print((x+1) + ": Game"+ (x+1) +" "+ this.games.get(x).toString());
					}

					this.input = keyboard.nextLine();

					// show the options for when a user chooses a previously saved game
					if (isNumerical(this.input) && this.intInput < this.games.size() && this.intInput >= 0)
						continueOptions(user, this.games.get(this.intInput));

				// start a new game
				} else if (this.input.replaceAll("\\s+","").equalsIgnoreCase("newgame")){

					startGame(user, new Game());
				}

			} else { //start new game when no previous games have been found
				System.out.println("No previous games found");
				startGame(user, new Game());
			}

		} while(!this.input.replaceAll("\\s+","").equalsIgnoreCase("back"));

		this.input = "";
	}


	/**
	 * shows the user all options they can choose when they selected a specific game like:
	 * 		* move history 	(displays all the moves each player did with chess algebraic notation)
	 * 		* continue 		(continues a previously saved game. does not appear when game has already been finished)
	 * 		* back 			(takes the user back to previous menu)
	 *
	 * @param user		user object used to save the game object in the matchhistory arraylist in the User
	 * @param game		game object selected by the user from the previous menu
	 * @author Jose Santos 		17993442
	 */
	public void continueOptions(User user, Game game) {

		// determines whether or not the game selected can continue
		boolean canContinue = !(game.player1.isLoser || game.player2.isLoser);

		do {
			System.out.println(
						"	Type		\"move history\" 		to view the move history");
			if(canContinue)
				System.out.println("			\"continue\" 		to continue the game");

			System.out.println("			\"back\" \n");

			this.input = keyboard.nextLine();

			// prints the move history of the selected game
			if (this.input.replaceAll("\\s+","").equalsIgnoreCase("movehistory")) {

				System.out.println(game.gameBoard.toString() + "\n" + game.moveHistory);

			//continues a game
			} else if (this.input.replaceAll("\\s+","").equalsIgnoreCase("continue") && canContinue) {

				this.continuingGame = true;
				startGame(user, game);

				// breaks the loop if one of the players was set to loser, or finished the game after startGame finished
				if (game.player1.isLoser || game.player2.isLoser)
					break;

			}

		} while (!this.input.replaceAll("\\s+","").equalsIgnoreCase("back"));

		this.input = "";
	}


	/**
	 * the function that plays the whole game of chess
	 *
	 * @param user 		user object used to store the game object into
	 * @param game		the game object that the chess game will use and store in the
	 * 					matchHistory ArrayList in the user object
	 * @author Jose Santos 		17993442
	 */
	public void startGame(User user, Game game) {

		System.out.println("Starting Game!\n"
				+ "		Move Format = \"e2 e4 OR e2e4\"\n"
				+ "		Type \"quit\" to leave\n\n");

		String startPos, desPos;

		// iterates while none of the players are losers
		while (!(game.player1.isLoser || game.player2.isLoser)) {

			if(game.player1.isTurn) {

				System.out.println(game.gameBoard.toString()+"\n\n");
				System.out.println("	WHITE to move...\n\n");

			} else if(game.player2.isTurn) {

				// prints the board out in reverse orientation so that the black side is at the bottom
				System.out.println(game.gameBoard.reverseBoardStr()+"\n\n");
				System.out.println("	BLACK to move...\n\n");

			}

			this.input = keyboard.nextLine();

			//breaks the while loop if the user inputs "quit"
			if (this.input.replaceAll("\\s+","").equalsIgnoreCase("quit"))
				break;

			//deletes all the spaces in the user input
			this.input = this.input.replaceAll("\\s+","");

			/**
			 * only registers input with the size of 4 as the input only requires a string with the size of 4 characters
			 * it will throw an exception if the 4 characters do not follow the correct format and parameters
			 */
			if (this.input.length() == 4) {

				try {
					startPos = this.input.substring(0,2);
					desPos = this.input.substring(2,4);

					// moves the piece
					game.move(game.getSquare(startPos), desPos);
					System.out.println("\n\n" + game.moveHistory+ "\n\n");

				} catch (Exception o) {
					System.err.println("Wrong Input Format!!");
				}
			}
		}


		// displays who lost the game if there is a loser
		if (game.player1.isLoser || game.player2.isLoser) {
			if (game.player1.isLoser)
				System.out.println("White Lost!!\n");
			else
				System.out.println("Black Lost!!\n");
		}

		System.out.println("Game Finished\n\n");

		//save time and date the game was played
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    Date date = new Date();
	    game.datePlayed = formatter.format(date)+"\n";

	    // only adds the game to the games array if it is a new game
		if(!this.continuingGame)
			this.games.add(game);

		//updates both profiles.txt and chosen users game.txt
		user.updateGames(this.games);
		this.updateFile();

	}


	/**
	 *  Creates a new user and automatically starts a new game with the new user object
	 * @author Jose Santos 		17993442
	 */
	public void createUser() {

		String username;
		System.out.print("Name? ");
		username = keyboard.nextLine();

		User newUserObj = new User(username+",0");

		this.users.add(newUserObj);
		startGame(newUserObj, new Game());
	}


	/**
	 * checks if the input is strictly just an integer
	 * and saves it into a local variable
	 *
	 * @param input 	the user input
	 * @return			if the string isnt an Integer then return false, otherwise, return true
	 * @author Jose Santos 		17993442
	 */
	public boolean isNumerical(String input) {

		try {
			// minus 1 to match the index in an array
			this.intInput = Integer.parseInt(String.valueOf(input))-1;
			return true;

		}  catch(NumberFormatException o) {
			return false;
		}
	}


	/**
	 * main function
	 * @author Jose Santos 		17993442
	 */
	public static void main (String[] args)  {

		System.out.println("Welcome to Chesssssssss\n");

		ChessMain chess = new ChessMain();
	}


	/**
	 *	reads the profile.txt file and adds the users gathered into a local ArrayList
	 *	if a file has not been found, the function will create a new file and create a new user
	 * @author Jose Santos 		17993442
	 */
	public void readFile() {

		try {
			Scanner reader = new Scanner(new FileReader(this.profiles));

			while (reader.hasNextLine()) {
				this.users.add(new User(reader.nextLine()));
			}

		} catch(IOException e) {
			System.err.println("File not Found!");
			System.out.println("Creating new file....\n");
			createUser();

		}
	}

	/**
	 * updates the profile.txt file with the users ArrayList
	 * @author Jose Santos 		17993442
	 */
	public void updateFile() {
		try {
			FileWriter fileWriter = new FileWriter(this.profiles, false);
			BufferedWriter buffer = new BufferedWriter(fileWriter);
			PrintWriter printWriter = new PrintWriter(buffer);

			String allUserInfo = "";

			for (int i = 0; i < this.users.size(); i++) {
				allUserInfo += this.users.get(i).getName() +","+ this.users.get(i).getNumOfGames() + "\n";
			}

			printWriter.print(allUserInfo);
			printWriter.close();

		} catch (IOException e) {
			System.err.println("File not Found!");
		}
	}
}
