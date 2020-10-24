/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_2.GUI;

import Project_2.GUI.Chess_Panels.*;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;

/**
 *
 * @author Jose
 */
public class Chess_App_View extends JFrame implements Observer{

    public Startup_Menu startup_menu;
    public Game_Start game = new Game_Start();
    
    public Chess_App_View() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setBackground(new Color(50, 50, 50));
        this.setSize(980,720); 
        
        startup_menu = new Startup_Menu();
        startup_menu.setBounds(0,0,980,720);
        
        this.add(startup_menu);
        this.add(game);
        
        this.setResizable(false);
        this.setVisible(true);
    }
    
    @Override
    public void update(Observable o, Object arg) {

        Data data = (Data) arg; 
        
        if(!data.isLoggedIn) { 
            
        }


    }
    
}
