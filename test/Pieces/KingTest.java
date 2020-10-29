package Pieces;

import Exception.InvalidEndPointException;
import Game.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class contains tests that relate to the Bishop Piece
 *
 * @author Jose Santos 17993442
 * @author David Anderson 19065861
 */
public class KingTest {
    
    public KingTest() {
    }

  /**
    * This method tests the upwards movement of the King Piece
    * 
    * @author Jose Santos 17993442
    * @author David Anderson 19065861
    */
   @Test
    public void testKingMoveUp() throws Exception
    {
        Game game = new Game();
        
        Piece king = new King(game.player1);
        game.gameBoard.board[2][2].setPiece(king);
        BoardSquare start = game.gameBoard.board[2][2];
        
        game.move(start, 2, 3); //up 1
        
        assertEquals(king, game.gameBoard.board[3][2].getPiece());
    }
   /**
    * This method tests the Left movement of the King Piece
    * 
    * @author Jose Santos 17993442
    * @author David Anderson 19065861
    */
    @Test
    public void testKingMoveleft() throws Exception
    {
        Game game = new Game();
        
        Piece king = new King(game.player1);
        game.gameBoard.board[2][2].setPiece(king);
        BoardSquare start = game.gameBoard.board[2][2];
        
        game.move(start, 1, 2); //left 1
        
        assertEquals(king, game.gameBoard.board[2][1].getPiece());
    }   
   /**
    * This method tests the Right movement of the King Piece
    * 
    * @author Jose Santos 17993442
    * @author David Anderson 19065861
    */
    @Test
    public void testKingMoveRight() throws Exception
    {
        Game game = new Game();
        
        Piece king = new King(game.player1);
        game.gameBoard.board[2][2].setPiece(king);
        BoardSquare start = game.gameBoard.board[2][2];
        
        game.move(start, 3, 2); //right one
        
        assertEquals(king, game.gameBoard.board[2][3].getPiece());
    }   
   /**
    * This method tests the Diagonal movement of the King Piece
    * 
    * @author Jose Santos 17993442
    * @author David Anderson 19065861
    */
    @Test
    public void testKingDiagonal() throws Exception
    {
        Game game = new Game();
        Piece king = new King(game.player1);
        game.gameBoard.board[2][2].setPiece(king);
        BoardSquare start = game.gameBoard.board[2][2];

        game.move(start, 3, 3); //move diagonal

        assertEquals(king,game.gameBoard.board[3][3].getPiece());
    }
   /**
    * This method tests if the movement of the King Piece into
    * an invalid end point throws the correct exception
    * 
    * @exception InvalidEndPointException 
    * 
    * @author Jose Santos 17993442
    * @author David Anderson 19065861
    */
    @Test(expected = InvalidEndPointException.class)
    public void testInvalidEndPoint() throws Exception
    {
        Game game = new Game();
        Piece king = new King(game.player1);
        game.gameBoard.board[0][3].setPiece(king);
        BoardSquare start = game.gameBoard.board[0][4];
        
        game.isValidEndPoint(start, 4, 1);
    }
}
