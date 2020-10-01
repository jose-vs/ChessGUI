
package Game;
/**
* This Enumerated type class, stores and contains the six available piece ranks
*
* @author David Anderson 19065861
* @author Jose Santos 17993442
*
*/
public enum Rank {

    PAWN(10), KNIGHT(30), BISHOP(30), ROOK(50), QUEEN(90), KING(900);

    Rank(int value){
        this.value = value;
    }

    private final int value;

    public int getValue() {
        return value;
    }

}
