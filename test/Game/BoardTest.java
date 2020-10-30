package Game;

import org.junit.Test;
/**
 * This class contains tests that check whether or not the
 * Game board is set up correctly with the right pieces
 * 
 * @author Jose Santos 17993442
 * @author David Anderson 19065861
 */
public class BoardTest {
    
    public BoardTest() {
    }
    
    /**
     * Test of setPlayerBlack method, of class Board.
     * 
     * @author David Anderson 19065861
     */
    @Test
    public void testSetPlayerBlack(){
        Game game = new Game();
        
        System.out.println("Row 7 should be: BROOK BKNIGHT BBISHOP BQUEEN BKING BBISHOP BKNIGHT BROOK");
        System.out.print("Row 7 Actually is: ");
        testHelp(7, game.gameBoard);
        
        System.out.println("\nRow 6 should be: BPAWN BPAWN BPAWN BPAWN BPAWN BPAWN BPAWN BPAWN");
        System.out.print("Row 6 Actually is: ");
        testHelp(6, game.gameBoard);
    }
    
    /**
     * Method that takes in a row and a board object that helps to test
     * 
     * @param row  an integer that contains the row to search through
     * @param board a board object that contains the pieces and squares
     * 
     * @author David Anderson 19065861
     */
    public void testHelp(int row, Board board)
    {
           for (int column = 0; column < 8; column++)
        {
            System.out.print(board.board[row][column].getPiece() + " ");
        }
    }
    
    /**
     * Test of setPlayerWhite method, of class Board.
     * 
     * @author David Anderson 19065861
     */
    @Test
    public void testSetPlayerWhite() {
        Game game = new Game();
        
        System.out.println("\nRow 1 should be: WPAWN WPAWN WPAWN WPAWN WPAWN WPAWN WPAWN WPAWN");
        System.out.print("Row 1 Actually is: ");
        testHelp(1, game.gameBoard);
        
        System.out.println("\nRow 0 should be: WROOK WKNIGHT WBISHOP WQUEEN WKING WBISHOP WKNIGHT WROOK");
        System.out.print("Row 0 Actually is: ");
        testHelp(0, game.gameBoard);
    }
    
}