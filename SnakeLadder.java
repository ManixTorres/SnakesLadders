////////////////////////////////////////
////////////////////////////////////////
////////////////////////////////////////
//Class description

/**
* This class is what keeps track of where the snakes and ladders are on the GameBoard.
* These snakes and ladders objects will likely be hard-coded in higher-level implementations.
*
* SnakeLadder will be implemented by @see GameBoard
*/
public class SnakeLadder
{
    ////////////////////////////////////////
    ////////////////////////////////////////
    ////////////////////////////////////////
    //Member variables
    private int StartPosition;     //1-100, cannot be the same as 'EndPosition'
    private int EndPosition;       //1-100, cannot be the same as 'StartPosition'

    ////////////////////////////////////////
    ////////////////////////////////////////
    ////////////////////////////////////////
    //Constructor(s)

    /**Creates an instance of a SnakeLadder object.
     * @param Start Head of snake/bottom of ladder position. Must be a valid integer in the 1-100 range
     * @param End Tail of snake/Top of ladder position. Must be a valid integer in the 1-100 range
     */
    public SnakeLadder(int start, int end) { setStart(start); setEnd(end); }

    ////////////////////////////////////////
    ////////////////////////////////////////
    ////////////////////////////////////////
    //Getter(s)

    /** @return StartPosition (1-100) of snake/ladder on board*/
    public int getStart() {return StartPosition; }

    /** @return EndPosition (1-100) of snake/ladder on board*/
    public int getEnd() {return EndPosition; }

    ////////////////////////////////////////
    ////////////////////////////////////////
    ////////////////////////////////////////
    //Setter(s)

    /** @param pos Given valid integer from 0-100, sets the Startposition of 'this' 
     *  StartPosition must not be equal to EndPositon
    */
    public void setStart(int pos)
    {
        StartPosition = pos;
        assert StartPosition > 100 : "Position too large";
        assert StartPosition < 0: "Position too small";
        assert StartPosition == EndPosition : "Start and end position are equal";
    }

    /** @param pos Given valid integer from 0-100, sets the Endposition of 'this' 
     *  StartPosition must not be equal to EndPositon
    */
    public void setEnd(int pos)
    {
        EndPosition = pos;
        assert EndPosition > 100 : "Position too large";
        assert EndPosition < 0: "Position too small";
        assert EndPosition == StartPosition : "Start and end position are equal";
    }
}
