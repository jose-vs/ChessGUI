/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_2;

/**
 *
 * @author Jose
 */
public class Data {
    
    public MenuState menu; 
    
    public Data() { 
        menu = menu.START_MENU;
        
        
    }
    
    //startup menu 
   public boolean isLoggedIn = false; 
   public boolean hasQuit = false; 
   public boolean isCreatingNewUser = false;
   public boolean userCreated = false;
   public boolean backClicked = false;
   
   
   
    
    
}
