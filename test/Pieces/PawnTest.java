package Pieces;

import Game.BoardSquare;
import Game.Game;
import org.junit.Test;
import static org.junit.Assert.*;
import Exception.InvalidPathException;

/**
 *
 * @author David
 */
public class PawnTest {
    
    public PawnTest() {
    }

    /**
     * Test of isValidPath method, of class Pawn.
     * @throws Exception.InvalidPathException
     */
    @Test
    public void testIsValidPath() throws InvalidPathException {
        Game game = new Game();
        BoardSquare pawn = game.gameBoard.board[1][0];
        try {
            assertTrue(pawn.getPiece().isValidPath(pawn, 0, 2));
        } catch (InvalidPathException ex) {
           System.err.print("Error!");
        }
    }
    
     @Test
    public void testIsMoveForwardValid() throws Exception
    {
       Game game = new Game();
       BoardSquare pawn = game.gameBoard.board[1][0];
       game.move(pawn, 0, 2); //move up by one
       BoardSquare moved = game.gameBoard.board[2][0];
       System.out.println(game.gameBoard.toString());
       
       assertEquals(moved, game.gameBoard.board[2][0]);
    }
    
     @Test
    public void testIsMoveTwiceValid() throws Exception
    {
       Game game = new Game();
       BoardSquare pawn = game.gameBoard.board[1][0];
       System.out.println(game.gameBoard.toString());
        
       game.move(pawn, 0, 3); //move up by two
       BoardSquare moved = game.gameBoard.board[3][0];
       System.out.println(game.gameBoard.toString());
       
       assertEquals(moved, game.gameBoard.board[3][0]);
    }
    
    @Test
    public void testIsCaptureValid() throws Exception
    {
       Game game = new Game();
       Piece pawn = new Pawn(game.player1);
       Piece enemyPawn = new Pawn(game.player2);
       game.gameBoard.board[2][3].setPiece(pawn);
       game.gameBoard.board[3][4].setPiece(enemyPawn);
       BoardSquare start = game.gameBoard.board[2][3];
      
       System.out.println("\nMOVE TO CAPTURE");
       System.out.println(game.gameBoard.toString());
       
       game.move(start, 4, 3); //move to enemy pawn position
       
       System.out.println("CAPTURED\n");
       System.out.println(game.gameBoard.toString());

       assertEquals(pawn, game.gameBoard.board[3][4].getPiece());
    } 
}
