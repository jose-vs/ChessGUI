/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_2;

import Game.*;
import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Queen;
import Pieces.Rook;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose
 */
public class Chess_DB {
    
    Connection conn = null; 
    String dbURL = "jdbc:derby://localhost:1527/chessDB;create=true"; 
    String dbUSERNAME = "app"; 
    String dbPASSWORD = "app"; 
    String userTable = "C_USER"; 
    String gameTable = "C_GAME";
    
    public void dbSetup() { 
        try { 
            
            conn = DriverManager.getConnection(dbURL, dbUSERNAME, dbPASSWORD); 
            Statement statement = conn.createStatement(); 

            if(!checkTableExisting(userTable)) { 
                statement.executeUpdate(
                    "CREATE TABLE " +userTable+ " ("
                            + "userName VARCHAR(64), "
                            + "userPassword VARCHAR(64), "
                            + "userGames INTEGER, "
                    + "CONSTRAINT user_PK PRIMARY KEY (userName)");
            } else if(!checkTableExisting(gameTable)) { 
                statement.executeUpdate(
                    "CREATE TABLE " +gameTable+ " ("
                            + "gameID VARCHAR(10), "
                            + "userName VARCHAR(64), "
                            + "datePlayed DATE, "
                            + "gameFile VARCHAR(32), "
                            + "isFinished BOOLEAN NOT NULL, "
                            + "playerTurn VARCHAR(10)"
                            + "winner VARCHAR (10)"
                    + "CONSTRAINT game_PK PRIMARY KEY (gameID, userName), "
                    + "CONSTRAINT game_user_FK FOREIGN KEY (userName) REFERENCES " +userTable+ "(userName)");
            }
            
            statement.close();
            
        } catch (Throwable e ) {
            System.err.println("ERROR");
            e.printStackTrace();
        }
    }
    
    
    /*
    
    TODO: 
    
        CREATE A METHOD TO CHECK IF THE USER EXISTS IN THE DATABASE
        CREATE A METHOD TO CHECK FOR ALL GAMES STORED IN THE DATABASE
    */
    
    public Data validateUser(String username, String password) { 
        /*
            if user is not found, ask the user if the they want to create a new user
            then insert the new username and password into the database
        */
        Data data = new Data();
        
        try { 
                       
            Statement statement = conn.createStatement(); 
            ResultSet rs = statement.executeQuery("SELECT userName, userPassword FROM "+userTable
                    + " WHERE userName = '" +username+ "'");
            
            if (rs.next()) { 
                String pass = rs.getString("userPassword"); 
                System.out.println("user found");
                
                if(password.equals(pass)) { 
                    data.menu = MENU_STATE.LOGGED_IN;
                } else { 
                    data.menu = MENU_STATE.LOG_IN_FAILED;
                }
                
            } else { 
                
                System.out.println("No user found");
                data.menu = MENU_STATE.LOG_IN_FAILED;
                
            }
  
        } catch (SQLException ex) {
            Logger.getLogger(Chess_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return data;
    }
    
    public Data insertUser(String username, String password) { 
        Data data = new Data(); 
        
        try { 
            
            Statement statement = conn.createStatement(); 
            statement.executeUpdate("INSERT INTO " +userTable+ 
                    "(userName, userPassword, userGames) VALUES ('" +username+ "', '" +password+ "', 0)");
       
            System.out.println("user created");
            
            data.menu = MENU_STATE.START_MENU;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Chess_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return data;
    }
    
    public Data getUser(String username) { 
        System.out.println("reached getUser");
        Data data_update = new Data();
        Scanner reader;
        File gameFile;

        try { 
            
            //add all the games to the user ArrayList
            Statement statement = conn.createStatement(); 
            ResultSet rst = statement.executeQuery(
                "SELECT * FROM C_GAME WHERE username = '" +username+ "'");
            
            while (rst.next()) { 

               String gameDesc_txt = rst.getString("isFinished").equals("Yes") ? " " : "*";
               gameDesc_txt += rst.getString("gameID"); // fix format
             
               data_update.storedGames.add(gameDesc_txt);
                //data_update.gameID = rst.getString("gameID");
                
                //move this out of the try catch statement
               // data_update.menu = MENU_STATE.GAME_SELECT_MENU;
                
             
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Chess_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        data_update.menu = MENU_STATE.GAME_SELECT_MENU;

        return data_update;
    }
    
    public Data getMoveHistory(Data data, String username) { 
        System.out.println("reached getMatchHistory");

        Scanner reader;
        File gameFile;

        try { 
                    
            Statement statement = conn.createStatement(); 
            ResultSet rst = statement.executeQuery(
                "SELECT gameID FROM C_GAME WHERE username = '" +username+ "' AND "
                        + "gameID = '" +data.gameID+"'");
            
            try {
                    
                if (rst.next()) { 
                    gameFile = new File(rst.getString("gameID")+".txt");
                    reader = new Scanner(new FileReader(gameFile));
                    String fileString = "";
                    
                    while (reader.hasNextLine())
                        fileString += reader.nextLine() + "\n"; 
                    String[] gameInfo = fileString.split("@");
                    
                    data.moveHistory = gameInfo[2].substring(gameInfo[2].indexOf('\n')+1);
                }
      
            }catch (IOException o) {
                
		System.err.println("File Not Found!");
			
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Chess_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return data;
    }
    
    
    
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
    
    public Game loadGame(String gameID, String username) { 
        Game initGame = null;
        Scanner reader;
        File gameFile;
        
    
        
        try { 
            String sqlQuery = "SELECT * FROM C_GAME WHERE username = '" +username+ "'"
                    + "AND gameID = '" +gameID+"'"; 
            Statement statement = conn.createStatement(); 
            ResultSet rst = statement.executeQuery(sqlQuery);
            
            if(rst.next()) { 
                try{ 
                    
                    
                    String fileString = "";
                    gameFile = new File(rst.getString("gameID")+".txt");
                    
                    //System.out.println(rst.getString("gameID"));
                    reader = new Scanner(new FileReader(gameFile)); 
                    
                    while (reader.hasNextLine())
                        fileString += reader.nextLine() + "\n"; 
                    String[] gameInfo = fileString.split("@");
                    
                    Player player1 = new Player(Side.WHITE); 
                    Player player2 = new Player(Side.BLACK);
                    String[] board = gameInfo[1].split("\n");
                    String[][] boardSquares = new String[8][8];
                    BoardSquare[][] initBoardSquare = new BoardSquare[8][8];
                    int yPos = 7; 
                    int xPos = 0;
                    
                    
                    
                    
                    for (int x = 1; x <= 8; x++) {

                        //the row split up into substrings representing each square and what's contained within them
                        boardSquares[x-1] = new String[]{board[x].substring(3, 12), board[x].substring(15, 24), board[x].substring(27, 36),
			board[x].substring(39, 48), board[x].substring(51, 60), board[x].substring(63, 72),
			board[x].substring(75, 84), board[x].substring(87, 96)};
                    }
                    
                
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
               
                System.out.println(rst.getString("isFinished"));
                System.out.println(rst.getString("datePlayed"));
                
                String moveHistory = gameInfo[2].substring(gameInfo[2].indexOf('\n')+1);
                int gameTurns = moveHistory.split("\n").length;
        
                String gameStateCompare = rst.getString("isFinished");
                System.out.println(gameStateCompare.equals("Yes"));
                System.out.println(gameStateCompare.equals("No"));
                
                if (gameStateCompare.equals("Yes")) {
                    
                    System.out.println("Hi1");
                    
                    if(rst.getString("winner").equals("White")) 
                        player2.isLoser = true; 
                    else if(rst.getString("winner").equals("Black")) 
                        player1.isLoser = true; 
                    
                } else { 
                    
                    System.out.println("Hi2");

                    if (rst.getString("playerTurn").equals("White"))
			player1.isTurn = true;
                        
                    else if (rst.getString("playerTurn").equals("Black"))
			player2.isTurn = true;
                }
                
         
                
                initGame = new Game(gameTurns, moveHistory, initBoard, rst.getString("datePlayed"), player1, player2);
                    
                
                } catch (IOException e) { 
                    System.err.println("cant find game");
                }
               
            }

        } catch(SQLException e) { 
            
            e.printStackTrace();
        }
        
        System.out.println(initGame.gameBoard.toString());
        
        return initGame;
    }
    
    
    public void insertGametoDB(String username, String gameID, Game game) { 

        File gameFile = new File(gameID+".txt");
        String sqlQuery = "";
        
        try {
            Statement statement = conn.createStatement();
            
                /**
                 * generate a text file so that the move history 
                 * and the the pieces location can be stored
                 */
                FileWriter fileWriter = new FileWriter(gameFile, false);
		BufferedWriter buffer = new BufferedWriter(fileWriter);
		PrintWriter printWriter = new PrintWriter(buffer);
                
                String gameTxt = ""; 
                gameTxt += "@Board\n" + game.gameBoard.toString()+"\n";
                gameTxt += "@Move_History\n" + game.moveHistory;
                
                printWriter.print(gameTxt);
		printWriter.close();
        
            if (checkGameExists(username, gameID)) {    //update 
                                                    
                System.out.println("saving previous game game...");
                
                sqlQuery = "UPDATE C_GAME SET datePlayed = '" +game.datePlayed+ "', ";
            
                if (game.player1.isLoser || game.player2.isLoser ) {
                    
                    sqlQuery += "isFinished = 'Yes', playerTurn = null, winner = ";
                    sqlQuery += game.player1.isLoser ? "'Black'" : "'White'";
                    
                } else { 
                    sqlQuery += "playerTurn = ";
                    sqlQuery += game.player1.isTurn ? "'White'" : "'Black'"; 
                }
                
                sqlQuery += "WHERE username = '" +username+ "' AND "
                        + "gameID = '" +gameID+"'";
                
            } else { //insert new row
            
                System.out.println("saving game...");
            
                sqlQuery  = 
                         "INSERT INTO " + this.gameTable +" (gameID, username, datePlayed, isFinished, playerTurn, winner) "
                                + " VALUES ('" + gameID + "', '" + username + "', '" + game.datePlayed +"', ";
                
                
                if (game.player1.isLoser || game.player2.isLoser ) {
                    
                    sqlQuery += "'Yes', null, "; 
                    sqlQuery += game.player1.isLoser ? "'Black')" : "'White')";
                } else { 
                    
                    sqlQuery += "'No', "; 
                    sqlQuery += game.player1.isTurn ? "'White', null)" : "'Black', null)"; 
                }
                
            }
            
            System.out.println(sqlQuery); 
            statement.executeUpdate(sqlQuery);
            
        } catch (SQLException | IOException e) { 
                 e.printStackTrace();
        }
        
    }
    
    private boolean checkGameExists(String username, String gameID){ 
       boolean flag = false;
        try { 
            Statement statement = conn.createStatement(); 
            ResultSet rst = statement.executeQuery(
                "SELECT gameID FROM C_GAME WHERE username = '" +username+ "' AND "
                        + "gameID = '" +gameID+"'");
            
            if (rst.next()) { 
                flag = true;
            } else { 
                flag = false;
            }
           
       } catch (SQLException e) { 
           e.printStackTrace();
       }
        
        return flag;
        
    }
    
    private boolean checkTableExisting(String newTableName) {
        boolean flag = false;
        try {

            System.out.println("check existing tables.... ");
            String[] types = {"TABLE"};
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, null, null);
            
            while (rsDBMeta.next()) {
                
                String tableName = rsDBMeta.getString("TABLE_NAME");
                
                if (tableName.compareToIgnoreCase(newTableName) == 0) {
                    System.out.println(tableName + "  is there");
                    flag = true;
                }
            }
            
            if (rsDBMeta != null) {
                
                rsDBMeta.close();
                
            }
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
        
        return flag;
    }
    
    /**
     * check for every row in the game table 
     * if the txt file cannot be found then delete
     * the game from the database 
     */
    
    
    
    
    
}
