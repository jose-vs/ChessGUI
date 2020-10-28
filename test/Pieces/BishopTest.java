/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pieces;

import Exception.*;
import Game.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author David
 */
public class BishopTest {
    
    public BishopTest() {
    }

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
