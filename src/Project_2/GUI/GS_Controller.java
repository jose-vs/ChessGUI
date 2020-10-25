/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_2.GUI;

import Project_2.GUI.Chess_Panels.Game_Select_Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        
        
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); 

        switch (command) {
            
        }
            
    }
    
    
    
}
