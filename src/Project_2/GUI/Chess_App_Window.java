/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_2.GUI;

import Project_2.Data;
import Project_2.GUI.Chess_Panels.*;
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
    public Game_Start game = new Game_Start();
    
    public Chess_App_Window() {
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
        
        System.out.println(
                    data.backClicked + "\n" +
                    data.hasQuit + "\n" +
                    data.isCreatingNewUser + "\n" +
                    data.isLoggedIn + "\n" );
            
       
        
        if(!data.isLoggedIn && !data.isCreatingNewUser  && !data.backClicked) { 
            System.out.println("failed to login");
            this.startup_menu.userNotFound.setVisible(true);
//            this.startup_menu.username.setText("username");
//            this.startup_menu.password.setText("password");
            
        } else if (data.isCreatingNewUser){
            
            System.out.println("create new user"); // test to see if code reaches this point
            
            this.startup_menu.login.setVisible(false);
            this.startup_menu.new_user.setVisible(false);
            this.startup_menu.userNotFound.setVisible(false);
            this.startup_menu.back.setVisible(true);
            this.startup_menu.create_user.setVisible(true);
            this.repaint();
            
           
            
        } else if (data.backClicked) { //when everything is false 
            System.out.println("back");
            this.startup_menu.login.setVisible(true);
            this.startup_menu.new_user.setVisible(true);
            this.startup_menu.userNotFound.setVisible(false);
            this.startup_menu.back.setVisible(false);
            this.startup_menu.create_user.setVisible(false);
            this.repaint();
            
        } else if(data.userCreated){
            System.out.println("yeyey");
        
        } else if(data.isLoggedIn) { 
  
            this.startup_menu.userNotFound.setVisible(false);
        }

    }
    
}
