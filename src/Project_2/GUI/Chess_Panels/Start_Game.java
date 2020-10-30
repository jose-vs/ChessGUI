package Project_2.GUI.Chess_Panels;

import Game.*;
import Pieces.*;
import Project_2.Data;
import Project_2.GUI.Chess_Square_Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

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
   
    
    public Chess_Square_Button[][] board;
    
    public JLabel moveHistoryTitle, playerTurn, savedGame; 
    public JTextArea moveHistory;
    public JButton save, back; 
    
    public Start_Game() { 
        board = new Chess_Square_Button[8][8];
        boardSetup();
        
        //FIX
        moveHistory = new JTextArea(); 
        moveHistory.setBounds(650,80,290,475);
        moveHistory.setFont(new Font("Arial", Font.BOLD, 16));
        moveHistory.setForeground(new Color(70,70,70));
        moveHistory.setBackground(new Color(242, 243, 244 ));
        moveHistory.setEditable(false);
        add(moveHistory);
        
        moveHistoryTitle = new JLabel("Move History"); 
        moveHistoryTitle.setBounds(640,40,100,50);
        moveHistoryTitle.setFont(new Font("Arial", Font.BOLD, 16));
        moveHistoryTitle.setForeground(new Color(66, 73, 73));
        add(moveHistoryTitle);
        
        playerTurn = new JLabel(); 
        playerTurn.setBounds(100,615,200,50); 
        playerTurn.setFont(new Font("Arial", Font.BOLD, 18));
        playerTurn.setForeground(new Color(242, 243, 244 ));
        add(playerTurn);
	    
        savedGame = new JLabel("Saved Game");
        savedGame.setBounds(640,615,200,50);
        savedGame.setFont(new Font("Arial", Font.BOLD, 18));
        savedGame.setForeground(new Color(242, 243, 244));
        savedGame.setVisible(false);
        add(savedGame);
	    
        save = new JButton("Save"); 
        save.setBounds(630,585,140,25);
        add(save); 
        
        back = new JButton("Back"); 
        back.setBounds(800,585,140,25);
        add(back);
        
        
        setLayout(null);
        setPreferredSize(new Dimension(980,720));
        setBackground(new Color(23, 32, 42));
    }
    
    public void  boardSetup() { 
        //[col][row]
        int xPos = 50;
        int yPos = 50;
        
        int xCoor = 0; 
        int yCoor = 0;

        for (int row = 0; row < board.length; row++) { 
            for (int col = 0; col < board[row].length; col++) {

                board[row][col] = new Chess_Square_Button();

                if ((row % 2 == 0 && col % 2 == 0) || 
                        ((row+1) % 2 == 0 && (col+1) % 2 == 0))
                    board[row][col].setBackground(new Color(242,243,244));
		else
                    board[row][col].setBackground(new Color(251, 166, 108));
                
                board[row][col].setBorderPainted(true);
                board[row][col].setBounds(xPos, yPos, 70,70);
                board[row][col].square_coordinates(xCoor, yCoor);
                add(board[row][col]);
                
                xPos += 70;
                xCoor++;
            }
            xPos = 50;
            yPos += 70;
            
            xCoor = 0;
            yCoor++;
        }
    }
    
    public void updatePieces(Game game){ 
        
        BoardSquare[][] gameBoard = game.gameBoard.board;
        Piece boardInPiece;
        Rank rank; 
        Side side;
        
        for (int row = 0; row < board.length; row++) { 
            for (int col = 0; col < board[row].length; col++) {
             
                try { 
                    rank = gameBoard[row][col].getPiece().getRank();
                    side = gameBoard[row][col].getPiece().getPlayer().getSide();
                    
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
                        }

                    }
                    
                } catch (NullPointerException e) { 
                    board[row][col].setIcon(null);
                }

                rank = null; 
                side = null;
            }
        }
        
        this.repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) { 
        super.paintComponent(g); 
        g.setColor(new Color(242, 243, 244 ));
        g.fillRect(630,50,310,515); 
    }
    
    
    @Override
    public void update(Observable o, Object arg) {

        Data data = (Data) arg;
        
        updatePieces(data.game);
  
        this.moveHistory.setText(data.moveHistory);
        String playerTurn = data.game.player1.isTurn ? "White to move." : "Black to move.";
        this.playerTurn.setText(playerTurn);
        
        switch(data.menu) { 
            case GAME_FINISHED :
                
                String winnerBanner = data.game.player1.isLoser ? "Black Won!!" : "White Won!!";
                
                this.playerTurn.setText(winnerBanner);
        }
   
    }
    
}
