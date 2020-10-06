package Gui;
import java.awt.*;
import Game.*;
import Pieces.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author  David Anderson 19065861
 * @author Jose 17993442
 */

//1. Make Board
public class ChessBoardGui {
   
    private final JFrame gFrame;
    private final static Dimension OUTER_FRAME_DIMENSION = new Dimension(600,600);
  //  private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    //private JButton[][] chessBoardSquares = new JButton[8][8];
   // private JPanel chessBoard;
   // private final JLabel message = new JLabel("Chess Game :)");
   // private static final String COLS = "ABCDEFGH";

   public ChessBoardGui() 
   {
       this.gFrame = new JFrame("Chess");
       final JMenuBar chessMenuBar = makeChessMenuBar();
       this.gFrame.setJMenuBar(chessMenuBar);
       this.gFrame.setSize(OUTER_FRAME_DIMENSION);
       this.gFrame.setVisible(true);
    }
   
   private JMenuBar makeChessMenuBar()
   {
       final JMenuBar chessMenuBar = new JMenuBar();
       chessMenuBar.add(makeFileMenu());
       return chessMenuBar;
   }

   private JMenu makeFileMenu()
   {
       final JMenu fMenu = new JMenu("File");
       final JMenuItem openPGN = new JMenuItem("Load PGN");
       openPGN.addActionListener(new ActionListener()
       {
          @Override
          public void actionPerformed(ActionEvent e)
          {
          System.out.println("Open Pgn");
          }
       });
      fMenu.add(openPGN);
      return fMenu;
   }
   
   private class BoardPanel extends JPanel {
       final List<TilePanel> boardTiles;
       
       BoardPanel(){
           super(new GridLayout(8,8));
           this.boardTiles = new ArrayList<>();

           }
       }
   
   private class TilePanel extends JPanel
   {
       
   } 
  
}
   
   

               

    
    

