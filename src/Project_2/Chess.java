package Project_2;

import Project_2.GUI.Chess_App_View;
import Project_2.GUI.*;

/**
 *
 * @author Jose
 */
public class Chess {
    
    public static void main(String[] args) {
        
        boolean ye = true; 
        System.out.println(ye);
        
        Chess_App_View view = new Chess_App_View(); 
        Chess_Model model = new Chess_Model(); 
        Chess_Controller controller = new Chess_Controller(view, model); 
        model.addObserver(view);
    }
}
