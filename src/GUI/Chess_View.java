/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Jose
 */
public class Chess_View extends JFrame implements Observer{
    //startup menu
     private JPanel startupMenu = new JPanel(); 
        private JButton newGame = new JButton("New Game"); 
        private JButton loadGame = new JButton("Load Game"); 
        private JButton exitGame = new JButton("Exit");
     
    //user selection
     private JPanel userSelection = new JPanel(); 
        private JComboBox user = new JComboBox();
        private JButton back = new JButton(); 

    // game selection
     private JPanel gameSelection = new JPanel();
        private JComboBox games = new JComboBox();
     
     private JPanel chessGame = new JPanel(); 
     
     
    public Chess_View() { 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(720,480); 
        this.setLocationRelativeTo(null);
        
        this.setVisible(true);
    }
     
     

    @Override
    public void update(Observable o, Object arg) {
        
    }
}
