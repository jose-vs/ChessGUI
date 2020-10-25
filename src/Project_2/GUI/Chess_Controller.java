/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_2.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Jose
 */
public class Chess_Controller {
    
    public Chess_App_Window view; 
    public Chess_Model model; 
    
    public Chess_Controller(Chess_App_Window view, Chess_Model model) {
        this.view = view;
        this.model = model; 
        
        this.view.startup_menu.login.addActionListener(new ButtonListener());
        this.view.startup_menu.new_user.addActionListener(new ButtonListener());
        this.view.startup_menu.back.addActionListener(new ButtonListener());
        this.view.startup_menu.create_user.addActionListener(new ButtonListener());
    }
    
    
    
    class ButtonListener implements ActionListener { 

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand(); 

            if(command.equals("Log in")) { 
                String username = view.startup_menu.username_input.getText();
                String password = view.startup_menu.password_input.getText();
                model.checkName(username, password); 

            } else if (command.equals("New User")) { 
                
                System.out.println("NEW USER CLICKED");

                model.openCreateUser();
                
            } else if(command.equals("Create User")){ 
                
                String username = view.startup_menu.username_input.getText();
                String password = view.startup_menu.password_input.getText();
                model.createUser(username, password); 
                
            } else if (command.equals("Exit")){ 
                
            } else if (command.equals("Back")) { 
                model.back(model.data);
            }
        }
        
    }
    
    
}
