/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_2.GUI;

import Project_2.Data;
import Project_2.GUI.Chess_Panels.*;
import Project_2.MENU_STATE;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;

/**
 *
 * @author Jose
 */
public class Chess_App_Window extends JFrame implements Observer{

    public Startup_Menu startup_menu;
    public Game_Select_Menu game_select_menu;
    public Start_Game start_game;
    
    public Chess_App_Window() {
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setBackground(new Color(50, 50, 50));
        this.setSize(980,720); 
        
        startup_menu = new Startup_Menu();
        startup_menu.setBounds(0,0,980,720);
        startup_menu.setVisible(true);
        this.add(startup_menu);
        
        game_select_menu =  new Game_Select_Menu();
        game_select_menu.setBounds(0,0,980,720);
        game_select_menu.setVisible(false);
        this.add(game_select_menu);
        
        start_game = new Start_Game();
        start_game.setBounds(0,0,980,720);
        start_game.setVisible(false);
        this.add(start_game);
        
        
        
        this.setResizable(false);
        this.setVisible(true);
    }
  

    @Override
    public void update(Observable o, Object arg) {
       
        Data data = (Data) arg;
       // System.out.println("CHANGE MENU TO: "+data.menu);
       
        
        switch (data.menu) { 
            
            case GAME_SELECT_MENU :
                
                startup_menu.setVisible(false);
                game_select_menu.setVisible(true);
                start_game.setVisible(false);
                break;
                
            case START_MENU : 
                
                game_select_menu.setVisible(false); 
                startup_menu.setVisible(true);
                start_game.setVisible(false);
                break;
                
            case START_GAME : 
                game_select_menu.setVisible(false); 
                startup_menu.setVisible(false);
                start_game.setVisible(true);
        }
       
    }
}
