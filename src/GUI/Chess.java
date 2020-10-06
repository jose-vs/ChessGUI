package GUI;

/**
 *
 * @author Jose
 */
public class Chess {
    
    public static void main(String[] args) {
        Chess_View view = new Chess_View(); 
        Chess_Model model = new Chess_Model(); 
        Chess_Controller controller = new Chess_Controller(view, model); 
        model.addObserver(view);
    }
}
