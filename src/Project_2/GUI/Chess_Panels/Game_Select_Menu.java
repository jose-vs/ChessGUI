/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_2.GUI.Chess_Panels;

import Project_2.Data;
import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Jose
 */
public class Game_Select_Menu extends JPanel implements Observer {

    public JButton continueGame, newGame, back, mHistory_but, exit, start;
    public JList games; 
    public JLabel welcomeUser, pastGames, mHistory_title, moveHistory;
    public JTextField newGameName; // this will be the game ID
    
    public Game_Select_Menu(){
    
        newGameName = new JTextField("Enter Game Name");
        newGameName.setBounds(100,155,120,25);
        newGameName.setVisible(false);
        add(newGameName);
        
        continueGame = new JButton("Continue"); 
        continueGame.setBounds(100,155, 120,25); 
        add(continueGame); 
        
        newGame = new JButton("New Game"); 
        newGame.setBounds(100,195, 120,25); 
        add(newGame);
        
        start = new JButton("Start"); 
        start.setBounds(100,195, 120,25);
        start.setVisible(false);
        add(start);
        
        back = new JButton("Back"); 
        back.setBounds(100,235, 120,25); 
        add(back);
        
        games = new JList(); 
        games.setBounds(250,150,200,300);
        games.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(games);
        
        mHistory_but = new JButton("Move History"); 
        mHistory_but.setBounds(100,350, 120,25);
        add(mHistory_but);
        
        moveHistory = new JLabel(); 
        moveHistory.setBounds(500,160,720,100);
        moveHistory.setFont(new Font("Arial", Font.BOLD, 16));
        moveHistory.setForeground(new Color(255,255,255));
        moveHistory.setBackground(new Color(70,70,70));
        add(moveHistory);
        
        mHistory_title = new JLabel("Move History"); 
        mHistory_title.setBounds(480,80,720,100);
        mHistory_title.setFont(new Font("Arial", Font.BOLD, 18));
        mHistory_title.setForeground(new Color(255,255,255));
        add(mHistory_title);
        
        setLayout(null);
        setPreferredSize(new Dimension(980,720));
        setBackground(new Color(50, 50, 50));
    }
    
    public void paintComponent(Graphics g) {
		
	super.paintComponent(g); 	 
	g.setColor(new Color(30,30,30));	
	g.fillRect(0,470,980,320); 
        g.setColor(new Color(70,70,70));
        g.fillRect(480,150,500,320);
    }
    
    
    @Override
    public void update(Observable o, Object arg) {
        
        Data data = (Data) arg; 
       
        
        switch (data.menu) {
            
            case GAME_SELECT_MENU: 
                
                games.setListData(data.storedGames.toArray());
                moveHistory.setVisible(false);
                newGameName.setVisible(false);
                start.setVisible(false);
                continueGame.setVisible(true);
                newGame.setVisible(true);
                break;
               
            case NEW_GAME : 
                
                continueGame.setVisible(false);
                newGame.setVisible(false);
                newGameName.setVisible(true);
                start.setVisible(true);
                break;

            case MOVE_HISTORY :
                moveHistory.setText(data.moveHistoryHTML);
                moveHistory.setVisible(true);
                break;
        }

    }
    
}
