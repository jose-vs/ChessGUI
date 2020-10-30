package Project_2.GUI.Chess_Panels;

import Project_2.Data;
import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * This class contains/creates the GUI components for the JPanel
 * that correlates to the Menu where users select a game or create a new one
 *
 * 
 * @author Jose Santos 17993442
 * @author David Anderson 19065861
 */
public class Game_Select_Menu extends JPanel implements Observer {

    public JButton continueGame, newGame, back, mHistory_but, exit, start;
    public JList games;
    public JLabel welcomeUser, pastGames, mHistory_title;
    public JTextArea moveHistory;
    public JTextField newGameName; //This is the game ID
     
    /**
     * Constructor setting up GUI within The Game Select Menu
     * 
     * @author David Anderson 19065861
     */
    public Game_Select_Menu(){

        newGameName = new JTextField("Enter Game Name"); //Create Label for Game Name
        newGameName.setBounds(100,155,120,25);
        newGameName.setVisible(false);
        add(newGameName);

        continueGame = new JButton("Continue"); //Create Button for Continue
        continueGame.setBounds(100,155, 120,25);
        add(continueGame);

        newGame = new JButton("New Game");//Create Button for New Game
        newGame.setBounds(100,195, 120,25);
        add(newGame);

        start = new JButton("Start");//Create Button for Start
        start.setBounds(100,195, 120,25);
        start.setVisible(false);
        add(start);

        back = new JButton("Back"); //Create Button for Back
        back.setBounds(100,235, 120,25);
        add(back);

        games = new JList(); //Create List that will contain the available games
        games.setBounds(250,150,200,300);
        games.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        games.setBackground(new Color(242, 243, 244 ));
        add(games);

        mHistory_but = new JButton("Move History");//Create Button called Move History
        mHistory_but.setBounds(100,350, 120,25);
        add(mHistory_but);

        moveHistory = new JTextArea(); //Create The Text Area that will contain a games Move History
        moveHistory.setBounds(500,160,400,300);
        moveHistory.setFont(new Font("Arial", Font.BOLD, 16));
        moveHistory.setForeground(new Color(46, 64, 83 ));
        moveHistory.setBackground(new Color(248, 196, 113));
        moveHistory.setEditable(false);
        add(moveHistory);

        mHistory_title = new JLabel("Move History"); //Create Title/Label called Move History
        mHistory_title.setBounds(480,80,720,100);
        mHistory_title.setFont(new Font("Arial", Font.BOLD, 18));
        mHistory_title.setForeground(new Color(242, 243, 244 ));
        add(mHistory_title);

        setLayout(null);
        setPreferredSize(new Dimension(980,720));
        setBackground(new Color(28, 40, 51 ));
    }
    /**
     * This method is responsible for creating the Move History "box"
     * a Space where the move history JTextArea will go.This method sets the size and color of this area
     * 
     * @param g a Graphic/Icon
     * @author David Anderson 19065861
     */
    public void paintComponent(Graphics g) {

	super.paintComponent(g);
	g.setColor(new Color(23, 32, 42));
	g.fillRect(0,470,980,320);
        g.setColor(new Color(248, 196, 113));
        g.fillRect(480,150,500,320);
    }

 /**
     * This Update method, takes in an observable and an Object
     * which is responsible for updating the visibility of certain GUI components
     * in regards to the MENU_STATE
     * 
     * @param o an Observer 
     * @param arg an object
     * @author David Anderson 19065861
     */
    @Override
    public void update(Observable o, Object arg) {

        Data data = (Data) arg;


        switch (data.menu) {

            case GAME_SELECT_MENU: //Starting view

                games.setListData(data.storedGames.toArray());
                moveHistory.setText(data.moveHistory);
                moveHistory.setVisible(false);
                newGameName.setVisible(false);
                start.setVisible(false);
                continueGame.setVisible(true);
                newGame.setVisible(true);
                break;

            case NEW_GAME : //user has selected new game

                continueGame.setVisible(false);
                newGame.setVisible(false);
                newGameName.setVisible(true);
                start.setVisible(true);
                break;

            case MOVE_HISTORY : //User Selects Move_History
		moveHistory.setVisible(true);
               
                moveHistory.setText(data.moveHistory);
                break;
        }
    }
}
