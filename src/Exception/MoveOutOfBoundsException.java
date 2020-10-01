package Exception;
/**
* This Exception class, stores and contains MoveOutOfBoundsException, Which prints out
* the relevant information to the user about the error.
*
* @author David Anderson 19065861
* @author Jose Santos 17993442
*/
public class MoveOutOfBoundsException extends Exception {
	
	public MoveOutOfBoundsException() {
            
		System.err.println("Invalid Move! Out of Bounds");
                
	}

}
