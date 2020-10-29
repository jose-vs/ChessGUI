/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_2.GUI;

import Project_2.GUI.Chess_Panels.Game_Select_Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Jose
 */
public class GS_Controller implements ActionListener {

    Game_Select_Menu gs_menu; 
    Chess_Model model; 
    
    public GS_Controller (Game_Select_Menu gs_menu, Chess_Model model) { 
        this.gs_menu = gs_menu; 
        this.model = model;
        
        gs_menu.back.addActionListener(this);
        gs_menu.mHistory_but.addActionListener(this);
        gs_menu.games.addListSelectionListener(new GameSelectionListener());
        gs_menu.newGame.addActionListener(this);
        gs_menu.start.addActionListener(this);
        gs_menu.continueGame.addActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); 

        switch (command) {
            case "Back":
                model.back(model.data);
                break;
            case "Move History" : 
                model.getMoveHistory();
                break; 
            case "New Game" : 
                model.startNewGame(null);
                break;
            case "Start" : 
                String newGameName = 
                        gs_menu.newGameName.getText().replaceAll("\\s+","");
                model.startNewGame(newGameName);
                break;
            case "Continue" : 
               
                model.continueGame();
                break;
                
        }
            
    }
    
    public class GameSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            try { 
            //null pointer exception
            //doestn affect the program : must handle 
            
                String gameID = gs_menu.games.getSelectedValue().toString();
                
                //System.err.println(gameID +" game ID GS_CONTROLLER");
                
                model.getGame(gameID.substring(1).replaceAll("\\s+",""));
                
            } catch (NullPointerException o) { 
                 //System.err.println("NULL POINTER at GS_CONTROLLER");
            }
        }
    }
    
}
    
    

    