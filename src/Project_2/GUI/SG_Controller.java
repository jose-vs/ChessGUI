/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_2.GUI;

import Project_2.GUI.Chess_Panels.Start_Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Jose
 */
public class SG_Controller implements ActionListener {
    
    Start_Game sg; 
    Chess_Model model;
    
    public SG_Controller (Start_Game sg, Chess_Model model) { 
        this.sg = sg; 
        this.model = model;
        
        
        
    }
    
    
    

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    
}
