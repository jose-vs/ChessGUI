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

    public JButton continueGame, newGame, back, mHistory_but, exit;
    public JList games; 
    public JLabel welcomeUser, pastGames, mHistory_title, moveHistory;
    
    public Game_Select_Menu(){
    
        
        continueGame = new JButton("Continue"); 
        continueGame.setBounds(100,155, 120,25); 
        add(continueGame); 
        
        newGame = new JButton("New Game"); 
        newGame.setBounds(100,195, 120,25); 
        add(newGame);
        
        back = new JButton("Back"); 
        back.setBounds(100,235, 120,25); 
        add(back);
        
        games = new JList(); 
        games.setBounds(250,150,150,300);
        add(games);
        
        mHistory_but = new JButton("Move History"); 
        mHistory_but.setBounds(100,350, 120,25);
        add(mHistory_but);
        
        moveHistory = new JLabel("asdfasdfasdf"); 
        moveHistory.setBounds(100,100,720,100);
        add(moveHistory);
        
        setLayout(null);
        setPreferredSize(new Dimension(980,720));
        setBackground(new Color(50, 50, 50));
    }
    
    public void paintComponent(Graphics g) {
		
	super.paintComponent(g); 	 
	g.setColor(new Color(20,20,20));	
	g.fillRect(0,400,980,320); 

	}
    
    
    @Override
    public void update(Observable o, Object arg) {
        
        Data data = (Data) arg; 
        
        System.out.println("GAME SELECT MENU STATE: "+data.menu);
        
        switch (data.menu) {
            
            case GAME_SELECT_MENU: 
                games.setListData(data.u_data.gameID.toArray());
        }

    }
    
}
