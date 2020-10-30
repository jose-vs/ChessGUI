package Project_2.GUI.Chess_Panels;

import Project_2.Data;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.ImageIO;
/**
 * This class contains/creates the GUI components for the JPanel
 * that correlates to the first Menu the user sees, the Startup
 * 
 * @author Jose Santos 17993442
 * @author David Anderson 19065861
 */
public class Startup_Menu extends JPanel implements Observer{
    
    public JButton login, exit, new_user, back, create_user; 
    public JLabel welcome_message, userNotFound, username, password;
    public JTextField username_input, password_input; 
    
    public BufferedImage chessLogo;
    
    /**
     * Constructor setting up GUI within The Startup Menu
     * 
     * @author Jose Santos 17993442
     */
    public Startup_Menu() {
        
        welcome_message = new JLabel("Chess Game for PDC AUT 2020"); //Create Title
        welcome_message.setBounds(50,0,1000,100);
        welcome_message.setFont(new Font("Arial", Font.PLAIN, 38));
        welcome_message.setForeground(new Color(242, 243, 244 ));
        add(welcome_message);
        
        userNotFound = new JLabel("Invalid username or password");  //Create Invalid username/password message
        userNotFound.setBounds(80,80,400,100);
        userNotFound.setFont(new Font("Arial", Font.BOLD, 16));
        userNotFound.setForeground(new Color(242, 243, 244 ));
        userNotFound.setVisible(false);
        add(userNotFound);
        
        username_input = new JTextField("username"); //Create textField for user to supply username
        username_input.setBounds(100, 155, 180, 25);
        add(username_input);
        
        password_input = new JTextField("password");//Create textField for user to supply password
        password_input.setBounds(100, 195, 180, 25);
        add(password_input);
        
        login = new JButton("Log in");//Create Button for Log in (goes invisible if user clicks on new user)
        login.setBounds(100,235,180,25);  
        add(login);
        
        new_user = new JButton("New User"); // Create button for New User (goes invisible if user clicks on new user)
        new_user.setBounds(130,275, 120, 25);
        add(new_user);
        
        back = new JButton("Back");  //Create button for back
        back.setBounds(100,235,65,25);
        add(back);
        
        create_user = new JButton("Create User");  //create button for Create User
        create_user.setBounds(175,235,105,25);
        add(create_user);
        
        exit = new JButton("Exit"); //Create button for exit (closes the whole application) 
        exit.setBounds(50, 600, 75, 25);
        add(exit);
        
        try { 
            chessLogo = ImageIO.read(new File("chessLogo.png")); //look for chessLogo image
        } catch (IOException e) { 
            System.err.println("FILE NOT FOUND");
        }
        
        setLayout(null);
        setPreferredSize(new Dimension(980,720));
        setBackground(new Color(23, 32, 42));
    }
    
    /**
     * This paintComponent method takes in a graphic and draws it onto the
     * JPanel.
     * 
     * @param g a Graphic/Icon
     * @author Jose Santos 17993442
     */
    @Override 
    protected void paintComponent(Graphics g) { 
        super.paintComponent(g);
        g.drawImage(chessLogo, 300, 70, this);
    }
    
    /**
     * This Update method, takes in an observable and an Object
     * which is responsible for updating the visibility of certain GUI components
     * in regards to the MENU_STATE
     * 
     * @param o an Observer 
     * @param arg an object
     * 
     * @author Jose Santos 17993442
     */
    @Override
    public void update(Observable o, Object arg) {

        Data data = (Data) arg; 
        
        switch (data.menu) {
               
            case LOG_IN_FAILED: //user failed to Log in
                System.out.println("failed to login");
                userNotFound.setVisible(true);
                break;
                
            case NEW_USER: //User selects New user
                login.setVisible(false); 
                new_user.setVisible(false);
                userNotFound.setVisible(false);
                back.setVisible(true);
                create_user.setVisible(true);
                this.repaint();
                break;
                
            case CREATE_USER://User selects Create User
                System.out.println("create new user"); 
                login.setVisible(false);
                new_user.setVisible(false);
                userNotFound.setVisible(false);
                back.setVisible(true);
                create_user.setVisible(true);
                this.repaint();
                break;
                
            case START_MENU://Default menu
                login.setVisible(true);
                new_user.setVisible(true);
                userNotFound.setVisible(false);
                back.setVisible(false);
                create_user.setVisible(false);
                this.repaint();
                break;   
        } 
    } 
}
    
