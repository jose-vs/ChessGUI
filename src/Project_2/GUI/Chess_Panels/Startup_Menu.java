/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_2.GUI.Chess_Panels;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.ImageIO;


/**
 *
 * @author Jose
 */
public class Startup_Menu extends JPanel implements Observer{
    
    public JButton login, exit, new_user, back, create_user; 
    public JLabel welcome_message, userNotFound, username, password;
    public JTextField username_input, password_input; 
    
    public BufferedImage chessLogo;
    
    public Startup_Menu() {
        
        welcome_message = new JLabel("Chess Game for PDC AUT 2020");
        welcome_message.setBounds(50,0,1000,100);
        welcome_message.setFont(new Font("Arial", Font.PLAIN, 38));
        welcome_message.setForeground(new Color(255,255,255));
        add(welcome_message);
        
        userNotFound = new JLabel("Invalid username or password"); 
        userNotFound.setBounds(80,80,400,100);
        userNotFound.setFont(new Font("Arial", Font.BOLD, 16));
        userNotFound.setForeground(new Color(255,255,255));
        userNotFound.setVisible(false);
        add(userNotFound);
        
        username_input = new JTextField("username");
        username_input.setBounds(100, 155, 180, 25);
        add(username_input);
        
        password_input = new JTextField("password");
        password_input.setBounds(100, 195, 180, 25);
        add(password_input);
        
        login = new JButton("Log in"); // goes invisible if user clicks on new user
        login.setBounds(100,235,180,25);
        add(login);
        
        new_user = new JButton("New User"); // goes invisible if user clicks on new user
        new_user.setBounds(130,275, 120, 25);
        add(new_user);
        
        back = new JButton("Back"); 
        back.setBounds(100,235,65,25);
        add(back);
        
        create_user = new JButton("Create User"); 
        create_user.setBounds(175,235,105,25);
        add(create_user);
        
        
        
        
        
        exit = new JButton("Exit"); //closes the whole program 
        exit.setBounds(50, 600, 75, 25);
        add(exit);
        
        try { 
            chessLogo = ImageIO.read(new File("C:\\Users\\Jose\\Desktop\\Code\\Java\\PDC\\PDC - Project\\Chess Game\\src\\Project_2\\GUI_assets\\chessLogo.png"));
        } catch (IOException e) { 
            System.err.println("FILE NOT FOUND");
        }
        
        
        setLayout(null);
        setPreferredSize(new Dimension(980,720));
        setBackground(new Color(50, 50, 50));
    }
    
    @Override 
    protected void paintComponent(Graphics g) { 
        super.paintComponent(g);
        g.drawImage(chessLogo, 300, 70, this);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
    
}
