import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

////////////////////////////////////////
////////////////////////////////////////
////////////////////////////////////////
//Class description

/**
* Class meant to make changes to @see GUI
*/
public class GUI_Controller 
{
    ////////////////////////////////////////
    ////////////////////////////////////////
    ////////////////////////////////////////
    //Member variables
    private GameBoard InternalBoard;
    private int DiceNumber;
    private ArrayList<CustomJPanel> CustomPanels;

    ////////////////////////////////////////
    ////////////////////////////////////////
    ////////////////////////////////////////
    //Constructor(s)

    /**
    * Default constructor, creates board with specified number of players
    * Creates 100 CustomPanels
    * @param numOfPlayer Integer (1-4) which determines how many players will be added to the @see GameBoard
    */
    public GUI_Controller(int numOfPlayers)
    {
        //Initialize member variables
        InternalBoard = new GameBoard(numOfPlayers);
        CustomPanels = new ArrayList<CustomJPanel>();
        DiceNumber = 0;

        //Create panels for each square of the board
        for(var i = 0; i < 100; i++)
        {
            //Variables that determine if the squares have a snake/ladder or not
            String snakeOrL = "";
            int end = 0;

            //Check all snake ladders
            for(SnakeLadder SL : InternalBoard.getSnakeLadders())
            {
                //If the current panel (i) has a snake or ladder at it
                if(i + 1 == SL.getStart())
                {
                    end = SL.getEnd();

                    //If current panel has a ladder at it
                    if(SL.getEnd() > SL.getStart())
                    {
                        snakeOrL = "Ladder:";
                    }

                    //If current panel has a snake at it
                    if(SL.getEnd() < SL.getStart())
                    {
                        snakeOrL = "Snake:";
                    }
                    break;
                }
            }

            //Create new customPanel
            CustomJPanel CJP = new CustomJPanel(i + 1, end, snakeOrL);

            //Make panels checkerboard color
            if(i % 2 == 0) { CJP.setBackground(Color.WHITE); }
            else if(i % 2 == 1) { CJP.setBackground(Color.GREEN); }

            //Add panel to list
            CustomPanels.add(CJP);
        }
    }

    ////////////////////////////////////////
    ////////////////////////////////////////
    ////////////////////////////////////////
    //Getter(s)
    
    /**@return Gameboard object associated with 'this' */
    public GameBoard getGameBoard() {return InternalBoard; }

    /***@return What number the last dice roll was*/
    public int getDiceNumber() { return DiceNumber; }

    /***@return List of CustomJPanels associated with 'this'*/
    public ArrayList<CustomJPanel> getPanels() { return CustomPanels; }

    /***@return Return no of player that won */
    public int getWinNo()  
    {
        int index = 1;
        for(Player S: InternalBoard.getPlayers())
        {
            if(S.getWinStatus() == true)
            {
                return index;
            }
            index++;   
        }
        return -1;
    }

    ////////////////////////////////////////
    ////////////////////////////////////////
    ////////////////////////////////////////
    //Setters(s)

    /**
     * @param nu Sets the number of the dice (1-6)
     */
    public void setDiceNumber(int nu) 
    {
        assert nu < 0 || nu > 6 : "Dice out of range";
        DiceNumber = nu;
    }

    ////////////////////////////////////////
    ////////////////////////////////////////
    ////////////////////////////////////////
    
    /**
    * Takes all internal player objects into account and updates JPanels accordingly 
    */
    public void updateInternalJPanels()
    {
        int numPlayers = InternalBoard.getPlayers().size();

        //Initialize player position variables
        int P1 = -1;
        int P2 = -1;
        int P3 = -1;
        int P4 = -1;

        //Get the position of each player
        if(numPlayers >= 1){P1 = InternalBoard.getPlayers().get(0).getPosition();}
        if(numPlayers >= 2){P2 = InternalBoard.getPlayers().get(1).getPosition();}
        if(numPlayers >= 3){P3 = InternalBoard.getPlayers().get(2).getPosition();}
        if(numPlayers >= 4){P4 = InternalBoard.getPlayers().get(3).getPosition();}

        //Update JPanels based on player positions
        for(CustomJPanel Panel : CustomPanels)
        {
            Panel.updatePanel(P1, P2, P3, P4);
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Generates a random number between two bounds.
     * @param lowerBound Smallest positive integer desired to be generated
     * @param upperBound Smallest positive integer desired to be generated
     * @return Random number between the two bounds
     */
    public int generateRandomInt(int lowerBound, int upperBound)
    {
        Random rand = new Random();
        return rand.nextInt(upperBound-1)+lowerBound;
    }
}
