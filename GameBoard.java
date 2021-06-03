import java.util.ArrayList;

////////////////////////////////////////
////////////////////////////////////////
////////////////////////////////////////
//Class description

/**
* This class brings player and SnakeLadder objects together, and has internal 
* properties that will allow a game of snakes and ladders to be properly played.
* 
* Contains instances of @see SnakeLadder, @see Player, @see AIPlayer
* Gameboard will be implemented by @see GUI_Controller
*/
public class GameBoard
{
    ////////////////////////////////////////
    ////////////////////////////////////////
    ////////////////////////////////////////
    //Member variables
    private ArrayList<Player> Players;
    private ArrayList<SnakeLadder> SnakeLadders;
    private int playerTurn; //must be 1-4
    private boolean gameOver;

    ////////////////////////////////////////
    ////////////////////////////////////////
    ////////////////////////////////////////
    //Constructor(s)

    /**Creates an instance of a player object. 'Position' is initialized to 0. 'hasWon' initialized to false. */
    public GameBoard(int numHumans) 
    {
        //Set arguments
        playerTurn = 1;
        Players = new ArrayList<Player>();
        SnakeLadders = new ArrayList<SnakeLadder>();
        gameOver = false;

        //Add human players
        for(int i = 0; i < numHumans; i++) { Players.add(new Player()); }

        //Add snakes
        SnakeLadders.add(new SnakeLadder(32, 10));
        SnakeLadders.add(new SnakeLadder(36, 6));
        SnakeLadders.add(new SnakeLadder(48, 26));
        SnakeLadders.add(new SnakeLadder(62, 18));
        SnakeLadders.add(new SnakeLadder(88, 24));
        SnakeLadders.add(new SnakeLadder(95, 56));
        SnakeLadders.add(new SnakeLadder(97, 78));

        //Add ladders
        SnakeLadders.add(new SnakeLadder(2, 23));
        SnakeLadders.add(new SnakeLadder(4, 14));
        SnakeLadders.add(new SnakeLadder(8, 30));
        SnakeLadders.add(new SnakeLadder(21, 42));
        SnakeLadders.add(new SnakeLadder(28, 76));
        SnakeLadders.add(new SnakeLadder(50, 67));
        SnakeLadders.add(new SnakeLadder(71, 92));
        SnakeLadders.add(new SnakeLadder(80, 99));
    }

    ////////////////////////////////////////
    ////////////////////////////////////////
    ////////////////////////////////////////
    //Getter(s)

    /** @return ArrayList of player objects associated with 'this'*/
    public ArrayList<Player> getPlayers() {return Players; }

    /**@return ArrayList of SnakeLadder objects associated with 'this' */
    public ArrayList<SnakeLadder> getSnakeLadders() {return SnakeLadders; }

    /**@return Which player's turn it is (1-4)*/
    public int getCurrentTurn() {return playerTurn; }

    /**@return Whether or not the game should be over */
    public boolean getEndGameStatus() {return gameOver; }

    ////////////////////////////////////////
    ////////////////////////////////////////
    ////////////////////////////////////////

    /**
    * Given a dice roll, move the player whose turm it is
    * @param rollNum Output from a dice roll 
    */
    void movePlayer(int rollNum)
    {
        //Move player
        int prevPos = Players.get(playerTurn-1).getPosition();
        int newPos = prevPos + rollNum;

        //If on snake or ladder, move them
        for(SnakeLadder SL : SnakeLadders)
        {
            if(SL.getStart() == newPos) { newPos = SL.getEnd(); }
        }

        //If player reaches end they win
        if(newPos >= 100) 
        {
            newPos = 100; 
            Players.get(playerTurn-1).setWinStatus(true);
            gameOver = true;
        }

        //Update position of player
        Players.get(playerTurn-1).setPosition(newPos);

        //Give the next player the turn
        nextTurn();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    /**
    * Cycles the turn variable to the next player's spot 
    */
    void nextTurn()
    {
        if(playerTurn < Players.size())
            playerTurn++;
        else if(playerTurn >= Players.size())
            playerTurn = 1;
    }
}