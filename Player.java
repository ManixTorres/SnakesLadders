////////////////////////////////////////
////////////////////////////////////////
////////////////////////////////////////
//Class description

/**
* This class is what keeps track of where players are on the snakes and ladders board. 
* Also keeps track of if a player has won or lost.
* 
* @see AIPlayer inheirits from this class.
* Player will be implemented by @see GameBoard
*/
public class Player
{
    ////////////////////////////////////////
    ////////////////////////////////////////
    ////////////////////////////////////////
    //Member variables
    private int position;       //1-100
    private boolean hasWon;

    ////////////////////////////////////////
    ////////////////////////////////////////
    ////////////////////////////////////////
    //Constructor(s)

    /**Creates an instance of a player object. 'Position' is initialized to 0. 'hasWon' initialized to false. */
    public Player() { setPosition(1); setWinStatus(false); }

    ////////////////////////////////////////
    ////////////////////////////////////////
    ////////////////////////////////////////
    //Getter(s)

    /** @return Position (1-100) of player on board*/
    public int getPosition() {return position; }

    /**@return Whether the player has won or not*/
    public boolean getWinStatus() {return hasWon; }

    ////////////////////////////////////////
    ////////////////////////////////////////
    ////////////////////////////////////////
    //Setter(s)

    /** @param pos Given valid integer from 0-100, sets the position of 'this' */
    public void setPosition(int pos)
    {
        position = pos;
        assert position > 100 : "Position too large";
        assert position < 0: "Position too small";
    }

    /** @param pos Given boolean value, sets status of 'this' */
    public void setWinStatus(boolean status) { hasWon = status; }
}