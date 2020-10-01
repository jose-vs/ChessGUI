package Exception;
/**
* This Exception class, stores and contains PieceHasNotMovedException, Which prints out
* the relevant information to the user about the error.
*
* @author David Anderson 19065861
* @author Jose Santos 17993442
*/
public class PieceHasNotMovedException extends Exception{
	
	public PieceHasNotMovedException() {
            
		System.err.println("Invalid Move! Piece has not moved");
                
	}

}
