package Exception;
/**
* This Exception class, stores and contains NotPlayersTurnException, Which prints out
* the relevant information to the user about the error.
*
* @author David Anderson 19065861
* @author Jose Santos 17993442
*/
public class NotPlayersTurnException extends Exception{

	public NotPlayersTurnException() {
            
		System.err.println("Invalid Move! Wrong sides turn");
                
	}
        
}
