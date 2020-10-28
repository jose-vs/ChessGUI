package Pieces;

import Exception.*;
import Game.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *  
 *  @author David Anderson 19065861
 *  @author Jose Santos 17993442
 */
public class RookTest {
    
    public RookTest() {
    }

    /**
     * Test of isValidPath method, of class Rook.
     * @throws Exception
     */
    @Test
    public void testIsValidPath() throws Exception {
        
        Game game = new Game();
        BoardSquare rook = game.gameBoard.board[0][0];
        try {
            assertTrue(rook.getPiece().isValidPath(rook, 0, 4));
        } catch (InvalidPathException ex) {
           System.err.print("Error!");
        }
    }
    
    
     /**
     * Test of VerticalMovement method, of class Rook.
     * @throws Exception
     */
    @Test
    public void testVerticalMovement() throws Exception {
         Game game = new Game();
         Piece rook = new Rook(game.player1);
         game.gameBoard.board[2][3].setPiece(rook);
         BoardSquare start = game.gameBoard.board[2][3];
         
         System.out.println("\nMOVE VERTICAL");
         System.out.println(game.gameBoard.toString());
         
         game.move(start, 3, 5);
         System.out.println("MOVED VERTICAL\n");
         System.out.println(game.gameBoard.toString());
         
         assertEquals(rook,game.gameBoard.board[5][3].getPiece());
    }
   
    /**
     * Test of Rook Horizontal Movement
     * @throws Exception
     * 
     * @author David Anderson 19065861
     */
    @Test
    public void testHorizontalMovement() throws Exception{
        Game game = new Game();
        Piece rook = new Rook(game.player1);
        game.gameBoard.board[2][3].setPiece(rook);
        BoardSquare start = game.gameBoard.board[2][3];
   
        System.out.println("\nMOVE HORIZONTAL");
        System.out.println(game.gameBoard.toString());
        
        game.move(start, 6, 2); //move right by three
        System.out.println("MOVED HORIZONTAL\n");
        System.out.println(game.gameBoard.toString());
     
        assertEquals(rook,game.gameBoard.board[2][6].getPiece());
    }
    
     /**
     * Test of invalidEndPoint for Rook piece
     * @throws Exception
     * 
     * @author David Anderson 19065861
     */
    @Test(expected = InvalidEndPointException.class)
    public void testInvalidEndPoint() throws Exception
    {
        Game game = new Game();
        Piece rook = new Rook(game.player1);
        game.gameBoard.board[0][3].setPiece(rook);
        BoardSquare start = game.gameBoard.board[0][3];
        
        game.isValidEndPoint(start, 3, 1);
    }
    
    @Test(expected = MoveOutOfBoundsException.class)
    public void tesOutofBounds() throws Exception
    {
        Game game = new Game();
        Piece rook = new Rook(game.player1);
        game.gameBoard.board[0][3].setPiece(rook);
        BoardSquare start = game.gameBoard.board[0][3];
       
        game.move(start, -1, 3);
    }
}
