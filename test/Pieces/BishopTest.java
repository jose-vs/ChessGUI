package Pieces;

import Game.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class contains tests that relate to the Bishop Piece
 * 
 * @author Jose Santos 17993442
 * @author David Anderson 19065861
 */
public class BishopTest {
    
    public BishopTest() {
    }

    
 /**
  * This method tests the Diagonal movement of the Bishop Piece
  * 
  * @author Jose Santos 17993442
  * @author David Anderson 19065861
  */
   @Test
    public void testBishopDiagonal() throws Exception
    {
        Game game = new Game();
        Piece bishop = new Bishop(game.player1);
        game.gameBoard.board[2][2].setPiece(bishop);
        BoardSquare start = game.gameBoard.board[2][2];

        game.move(start, 4, 4); //move diagonal

        assertEquals(bishop,game.gameBoard.board[4][4].getPiece());
    }
}
