package Project_2;

import Project_2.GUI.Chess_App_Window;
import Project_2.GUI.*;

/**
 *
 * @author Jose
 */
public class Chess {
    
    public static void main(String[] args) {
        

        Chess_App_Window window = new Chess_App_Window(); 
        
        
        Chess_Model model = new Chess_Model(); 
        SM_Controller SM_cont = new SM_Controller(window.startup_menu, model); 
        GS_Controller GS_cont = new GS_Controller(window.game_select_menu, model);
        
        model.addObserver(window);
        model.addObserver(window.startup_menu);
        model.addObserver(window.game_select_menu);
    }
}
