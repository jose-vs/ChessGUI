package Exception;
/**
* This Exception class, stores and contains InvalidPathException, Which prints out
* the relevant information to the user about the error.
*
* @author David Anderson 19065861
* @author Jose Santos 17993442
*/
public class InvalidPathException extends Exception {
	
	public InvalidPathException() {
	
		System.err.println("Invalid Move! Invalid path");

	}
        
}
