package Pieces;

import Exception.*;
import Game.BoardSquare;
import Game.Game;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class contains tests that relate to the Queen Piece
 *
 * @author Jose Santos 17993442
 * @author David Anderson 19065861
 */
public class QueenTest {
    
    public QueenTest() {
    }
    /**
     * Test of isValidPath method, of class Queen.
     * @throws Exception.InvalidPathException An exception that prints out relevant information if User enters invalid path.
     * 
     * @author Jose Santos 17993442
     * @author David Anderson 19065861
     */
    @Test
    public void testIsValidPath() throws InvalidPathException {
        Game game = new Game();
        BoardSquare queen = game.gameBoard.board[0][3];
        try {
            assertTrue(queen.getPiece().isValidPath(queen, 5, 0));
        } catch (InvalidPathException ex) {
           System.err.print("Error!");
        }
    }
    
    
    /**
     * Test of Queens Vertical Movement
     * @throws Exception
     * 
     * @author Jose Santos 17993442
     * @author David Anderson 19065861
     */
    @Test
    public void testVerticalMovement() throws Exception {
         Game game = new Game();
         Piece queen = new Queen(game.player1);
         game.gameBoard.board[2][3].setPiece(queen);
         BoardSquare start = game.gameBoard.board[2][3];
         
         System.out.println("\nMOVE VERTICAL");
         System.out.println(game.gameBoard.toString());
         
         game.move(start, 3, 5);
         System.out.println("MOVED VERTICAL\n");
         System.out.println(game.gameBoard.toString());
         
         assertEquals(queen,game.gameBoard.board[5][3].getPiece());
    }
   
    /**
     * Test of Queens Horizontal Movement
     * @throws Exception
     * 
     * @author Jose Santos 17993442
     * @author David Anderson 19065861
     */
    @Test
    public void testHorizontalMovement() throws Exception{
        Game game = new Game();
        Piece queen = new Queen(game.player1);
        game.gameBoard.board[2][3].setPiece(queen);
        BoardSquare start = game.gameBoard.board[2][3];
   
        System.out.println("\nMOVE HORIZONTAL");
        System.out.println(game.gameBoard.toString());
        
        game.move(start, 6, 2); //move right by three
        System.out.println("MOVED HORIZONTAL\n");
        System.out.println(game.gameBoard.toString());
     
        assertEquals(queen,game.gameBoard.board[2][6].getPiece());
    }
    /**
     * Test of Queens Diagonal Movement
     * @throws Exception
     * 
     * @author Jose Santos 17993442
     * @author David Anderson 19065861
     */
    @Test
    public void testDiagonalMovement() throws Exception {
        Game game = new Game();
        Piece queen = new Queen(game.player1);
        game.gameBoard.board[2][3].setPiece(queen);
        BoardSquare start = game.gameBoard.board[2][3];
        
        System.out.println("\nMOVE DIAGONAL");
        System.out.println(game.gameBoard.toString());
        
        game.move(start, 6, 5); //move diagonal
        System.out.println("MOVED DIAGONALLY\n");
        System.out.println(game.gameBoard.toString());
     
        assertEquals(queen,game.gameBoard.board[5][6].getPiece());
    }
    /**
     * Test of Queens Movement to an Invalid point
     * 
     * @throws Exception
     * @expected InvalidEndPointException
     * 
     * @author Jose Santos 17993442
     * @author David Anderson 19065861
     */
    @Test(expected = InvalidEndPointException.class)
    public void testInvalidEndPoint() throws Exception
    {
        Game game = new Game();
        Piece queen = new Queen(game.player1);
        game.gameBoard.board[0][3].setPiece(queen);
        BoardSquare start = game.gameBoard.board[0][3];
        
        game.isValidEndPoint(start, 3, 1);
    }
    
}
