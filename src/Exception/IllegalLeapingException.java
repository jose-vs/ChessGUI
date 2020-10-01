package Exception;
/**
* This Exception class, stores and contains IllegalLeapingException, Which prints out
* the relevant information to the user about the error.
*
* @author David Anderson 19065861
* @author Jose Santos 17993442
*/
public class IllegalLeapingException extends Exception{

	public IllegalLeapingException() {
            
		System.err.println("Invalid Move! Illegal Leaping");
                
	}
        
}
