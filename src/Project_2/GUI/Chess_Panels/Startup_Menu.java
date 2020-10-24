/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_2.GUI.Chess_Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Jose
 */
public class Startup_Menu extends JPanel{
    
    public JButton login, exit, new_user, back; 
    public JLabel welcome_message, userNotFound, username, password;
    public JTextField username_input, password_input; 
    public JList userList, all_games; 
    
    public Startup_Menu() {
        
        welcome_message = new JLabel("Chess Game for PDC AUT 2020");
        welcome_message.setBounds(50,0,1000,100);
        welcome_message.setFont(new Font("Arial", Font.PLAIN, 38));
        welcome_message.setForeground(new Color(255,255,255));
        add(welcome_message);
        
        username_input = new JTextField("username");
        username_input.setBounds(100, 155, 180, 25);
        add(username_input);
        
        password_input = new JTextField("password");
        password_input.setBounds(100, 195, 180, 25);
        add(password_input);
        
        login = new JButton("Log in");
        login.setBounds(100,235,180,25);
        add(login);
        
        new_user = new JButton("New User"); 
        new_user.setBounds(130,275, 120, 25);
        add(new_user);
        
        
        
        
        
        exit = new JButton("Exit");
        exit.setBounds(50, 600, 75, 25);
        add(exit);
        
        setLayout(null);
        setPreferredSize(new Dimension(980,720));
        setBackground(new Color(50, 50, 50));
    }
    
}
