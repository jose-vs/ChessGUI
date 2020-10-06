/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Jose
 */
public class Chess_Controller{
    
    public Chess_View view;
    public Chess_Model model; 
    
    /**
     *
     * @param view
     * @param model
     */
    public Chess_Controller(Chess_View view, Chess_Model model) { 
        this.view = view; 
        this.model = model; 
      
    }
    
    public class NewGameListener implements ActionListener { 

        @Override
        public void actionPerformed(ActionEvent e) {
            
        }  
    }
    
    public class LoadGameListener implements ActionListener { 

        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
        
    }
}

