/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_2.GUI.Chess_Panels;

import Game.*;
import Pieces.*;
import Project_2.Data;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Jose
 */
public class Start_Game extends JPanel implements Observer{
    
    /**
     * when a user clicks on a button, the controller will tell the 
     * controller that a piece has been moved to another spot
     * the model will change the data object with that move
     * 
     * the model will then update the observer with the new data so 
     * the view will be updated and move the piece into the right place
     */
   
    
    JButton[][] board;
    
    
    public Start_Game() { 
        board = new JButton[8][8];
        boardSetup();
        
        
        
        setLayout(null);
        setPreferredSize(new Dimension(980,720));
        setBackground(new Color(50, 50, 50));
    }
    
    
    public void  boardSetup() { 
        
        int xPos = 50;
        int yPos = 50;

        for (int row = 0; row < board.length; row++) { 
            for (int col = 0; col < board[row].length; col++) {

                board[row][col] = new JButton();

                if ((row % 2 == 0 && col % 2 == 0) || 
                        ((row+1) % 2 == 0 && (col+1) % 2 == 0))
                    board[row][col].setBackground(new Color(254,254,254));
		else
                    board[row][col].setBackground(new Color(251, 166, 108));
                
                board[row][col].setBorderPainted(true);
                board[row][col].setBounds(xPos, yPos, 70,70);
                add(board[row][col]);
                
                xPos += 70;
            }
            xPos = 50;
            yPos += 70;
        }
    }
    
    public void updatePieces(Game game){ 
        
        BoardSquare[][] gameBoard = game.gameBoard.board;

        Piece boardInPiece;
        Rank rank = null; 
        Side side = null;
        
        for (int row = 0; row < board.length; row++) { 
            for (int col = 0; col < board[row].length; col++) {
             
                try { 
                    rank = gameBoard[row][col].getPiece().getRank();
                    side = gameBoard[row][col].getPiece().getPlayer().getSide();
                } catch (NullPointerException e) { 
                    //no pieces found
                }
  
                if (side == Side.WHITE) { 
                    
                    switch (rank) { 
                        case PAWN :
                            try {
                                Image whitePawn = ImageIO.read(new File("whitePawn.png"));
                                board[row][col].setIcon(new ImageIcon(whitePawn));
                            } catch (IOException o) { 
                                System.err.println("Image not Found");
                            }
                            break;
                            
                        case BISHOP :
                            try {
                                Image whiteBishop = ImageIO.read(new File("whiteBishop.png"));
                                board[row][col].setIcon(new ImageIcon(whiteBishop));
                            } catch (IOException o) { 
                                System.err.println("Image not Found");
                            }
                            break;
                        case ROOK :
                            try {
                                Image whiteRook = ImageIO.read(new File("whiteRook.png"));
                                board[row][col].setIcon(new ImageIcon(whiteRook));
                            } catch (IOException o) { 
                                System.err.println("Image not Found");
                            }
                            break;
                        case KNIGHT :
                            try {
                                Image whiteKnight = ImageIO.read(new File("whiteKnight.png"));
                                board[row][col].setIcon(new ImageIcon(whiteKnight));
                            } catch (IOException o) { 
                                System.err.println("Image not Found");
                            }
                            break;    
                        case QUEEN :
                            try {
                                Image whiteQueen = ImageIO.read(new File("whiteQueen.png"));
                                board[row][col].setIcon(new ImageIcon(whiteQueen));
                            } catch (IOException o) { 
                                System.err.println("Image not Found");
                            }
                            break;
                        case KING :
                            try {
                                Image whiteKing = ImageIO.read(new File("whiteKing.png"));
                                board[row][col].setIcon(new ImageIcon(whiteKing));
                            } catch (IOException o) { 
                                System.err.println("Image not Found");
                            }
                            break; 
                        default:
                            break;
                    }
                    
                } else if (side == Side.BLACK) {
                    
                    switch (rank) { 
                        case PAWN :
                            try {
                                Image blackPawn = ImageIO.read(new File("blackPawn.png"));
                                board[row][col].setIcon(new ImageIcon(blackPawn));
                            } catch (IOException o) { 
                                System.err.println("Image not Found");
                            }
                            break;
                            
                        case BISHOP :
                            try {
                                Image blackBishop = ImageIO.read(new File("blackBishop.png"));
                                board[row][col].setIcon(new ImageIcon(blackBishop));
                            } catch (IOException o) { 
                                System.err.println("Image not Found");
                            }
                            break;
                        case ROOK :
                            try {
                                Image blackRook = ImageIO.read(new File("blackRook.png"));
                                board[row][col].setIcon(new ImageIcon(blackRook));
                            } catch (IOException o) { 
                                System.err.println("Image not Found");
                            }
                            break;
                        case KNIGHT :
                            try {
                                Image blackKnight = ImageIO.read(new File("blackKnight.png"));
                                board[row][col].setIcon(new ImageIcon(blackKnight));
                            } catch (IOException o) { 
                                System.err.println("Image not Found");
                            }
                            break;    
                        case QUEEN :
                            try {
                                Image blackQueen = ImageIO.read(new File("blackQueen.png"));
                                board[row][col].setIcon(new ImageIcon(blackQueen));
                            } catch (IOException o) { 
                                System.err.println("Image not Found");
                            }
                            break;
                        case KING :
                            try {
                                Image blackKing = ImageIO.read(new File("blackKing.png"));
                                board[row][col].setIcon(new ImageIcon(blackKing));
                            } catch (IOException o) { 
                                System.err.println("Image not Found");
                            }
                            break; 
                        default:
                            break;
                    
                    }

                }
                
                rank = null; 
                side = null;
            }
        }
    }
    
    
    @Override
    public void update(Observable o, Object arg) {
        
        Data data = (Data) arg;
        
        updatePieces(data.game);
        
        
        
    }
    
}
