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
        }
            
    }
    
    public class GameSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            
            String gameID = (String) gs_menu.games.getSelectedValue();
            model.data.gameID = gameID.substring(1).replaceAll("\\s+","");
            System.out.println(model.data.gameID);
        }
    }
    
}
    
    

