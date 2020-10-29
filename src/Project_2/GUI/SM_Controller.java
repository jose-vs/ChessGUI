/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_2.GUI;

import Project_2.GUI.Chess_Panels.Startup_Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Jose
 */
public class SM_Controller implements ActionListener{
    
    public Startup_Menu sm; 
    public Chess_Model model; 
    
    public SM_Controller(Startup_Menu sm, Chess_Model model) {
        this.sm = sm;
        this.model = model; 
        
        this.sm.login.addActionListener(this);
        this.sm.new_user.addActionListener(this);
        this.sm.back.addActionListener(this);
        this.sm.create_user.addActionListener(this);
        this.sm.exit.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); 

        switch (command) {
            case "Log in":
                {
                    String username = sm.username_input.getText();
                    String password = sm.password_input.getText();
                    model.checkName(username, password);
                    break;
                }
            case "New User":
                model.createUserMenu();
                break;
            case "Create User":
                {
                    String username = sm.username_input.getText();
                    String password = sm.password_input.getText();
                    model.createUser(username, password);
                    break;
                } 
            case "Exit":
                System.exit(0);
                break;
            case "Back":
                model.back(model.data);
                break;

        }
    }   
}
