package Pieces;

import Game.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class contains tests that relate to the Knight Piece
 *
 * @author Jose Santos 17993442
 * @author David Anderson 19065861
 */
public class KnightTest {
    
    public KnightTest() {
    }
   /**
    * This method tests the upwards movement of the Knight Piece
    * 
    * @throws Exception
    * 
    * @author David Anderson 19065861
    */
    @Test
    public void knightMoveUp() throws Exception
    {
        Game game = new Game();
        
        Piece knight = new Knight(game.player1);
        game.gameBoard.board[3][3].setPiece(knight);
        BoardSquare start = game.gameBoard.board[3][3];
       
        System.out.println("\nMOVE KNIGHT");
        System.out.println(game.gameBoard.toString());
       
        game.move(start, 2, 5); //up 2, left 1
        
        System.out.println("MOVED KNIGHT\n");
        System.out.println(game.gameBoard.toString());
        
        assertEquals(knight, game.gameBoard.board[5][2].getPiece());
    }
   /**
    * This method tests the Across movement of the Knight Piece
    * 
    * @throws Exception
    * 
    * @author David Anderson 19065861
    */
    @Test
    public void knightAcross() throws Exception
    {
        Game game = new Game();
        
        Piece knight = new Knight(game.player1);
        game.gameBoard.board[2][2].setPiece(knight);
        BoardSquare start = game.gameBoard.board[2][2];
       
        System.out.println("\nMOVE KNIGHT");
        System.out.println(game.gameBoard.toString());
       
        game.move(start, 4, 3); //right 2, up 1
        
        System.out.println("MOVED KNIGHT\n");
        System.out.println(game.gameBoard.toString());
       
        assertEquals(knight, game.gameBoard.board[3][4].getPiece());
    }
    
    
      /**
    * This method tests the leaping movement of the Knight Piece
    * 
    * @throws Exception
    * 
    * @author David Anderson 19065861
    */
    @Test
    public void testLeaping() throws Exception
    {
        Game game = new Game();
        Piece knight = new Knight(game.player1);
        game.gameBoard.board[0][1].setPiece(knight);
        BoardSquare start = game.gameBoard.board[0][1];
        
        int[][] path = start.getPiece().drawMove(start.getxPos(), start.getyPos(), 2, 2);
        
        System.out.println("\nMOVE KNIGHT");
        System.out.println(game.gameBoard.toString());

        game.move(start, 2, 2);
        
        System.out.println("MOVED KNIGHT\n");
        System.out.println(game.gameBoard.toString());
  
        assertTrue(game.isLeapingValid(knight, path));
    }
}
