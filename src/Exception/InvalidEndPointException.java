package Exception;
/**
* This Exception class, stores and contains InvalidEndPointException, Which prints out
* the relevant information to the user about the error.
*
* @author David Anderson 19065861
* @author Jose Santos 17993442
*/
public class InvalidEndPointException extends Exception {

	public InvalidEndPointException() {
            
		System.err.println("Invalid Move! End point is not valid");
                
	}

}
