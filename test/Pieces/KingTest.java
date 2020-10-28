package Pieces;

import Exception.InvalidEndPointException;
import Game.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author David
 */
public class KingTest {
    
    public KingTest() {
    }

    /**
     * Test of isValidPath method, of class King.
     * @throws java.lang.Exception
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
