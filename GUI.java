import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
////////////////////////////////////////
////////////////////////////////////////
////////////////////////////////////////
//Class description

/**
* UI class, has internal @see GUI_Controller 
*
* This class gives the ability to display the front screen, the in-game screen, and end-game screen
* of a snakes and ladder's game. 
*/
public class GUI extends JFrame implements ActionListener 
{
    ////////////////////////////////////////
    ////////////////////////////////////////
    ////////////////////////////////////////
    // Member variables
    private static final long serialVersionUID = 1L;
    private int NumPlayers = 1;
    private GUI_Controller InternalC = new GUI_Controller(NumPlayers);

    //JPanels
    private JPanel TopPanel = new JPanel();
    private JPanel CentralPanel = new JPanel();
    private JPanel BottomPanel = new JPanel();

    //JButtons
    private JButton DiceRoll = new JButton();
    private JButton GoToMainMenuButton = new JButton("Menu");
    private JButton StartGameButton = new JButton("Start Game");
    private JButton OnePlayerButton = new JButton("One player");    
    private JButton TwoPlayerButton = new JButton("Two players");
    private JButton ThreePlayerButton = new JButton("Three players");
    private JButton FourPlayerButton = new JButton("Four players");
    private JButton quit = new JButton("Quit");

    //JLabels
    private JLabel gameover = new JLabel();
    private JLabel currentTurn = new JLabel();

    //Internal image
    private ImageIcon iconLogo = new ImageIcon();

    ////////////////////////////////////////
    ////////////////////////////////////////
    ////////////////////////////////////////
    // Constructor(s)

    /**
    * Constructor for GUI object. Initializes various buttons and JFrames.
    * GUI object is a borderLayout with an initial 1000x100 size. 
    */
    public GUI() 
    {
        // Initialize JFrame
        super();
        this.setSize(1000, 1000);
        this.setLayout(new BorderLayout());

        //Set images of JButtons
        Image image = new ImageIcon(this.getClass().getResource("Pictures/dice/defaultdice.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        Image image1 = new ImageIcon(this.getClass().getResource("Pictures/Player1.png")).getImage().getScaledInstance(20, 50, java.awt.Image.SCALE_SMOOTH);
        Image image2 = new ImageIcon(this.getClass().getResource("Pictures/MenuButtons/TwoP.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        Image image3 = new ImageIcon(this.getClass().getResource("Pictures/MenuButtons/ThreeP.png")).getImage().getScaledInstance(70, 50, java.awt.Image.SCALE_SMOOTH);
        Image image4 = new ImageIcon(this.getClass().getResource("Pictures/MenuButtons/FourP.png")).getImage().getScaledInstance(90, 50, java.awt.Image.SCALE_SMOOTH);
        OnePlayerButton.setIcon(new ImageIcon(image1));
        TwoPlayerButton.setIcon(new ImageIcon(image2));
        ThreePlayerButton.setIcon(new ImageIcon(image3));
        FourPlayerButton.setIcon(new ImageIcon(image4));
        DiceRoll.setIcon(new ImageIcon(image));

        //Make all button clickable
        GoToMainMenuButton.addActionListener(this);
        DiceRoll.addActionListener(this);
        StartGameButton.addActionListener(this);
        quit.addActionListener(this);
        OnePlayerButton.addActionListener(this);
        TwoPlayerButton.addActionListener(this);
        ThreePlayerButton.addActionListener(this);
        FourPlayerButton.addActionListener(this);

        //Add Jpanels to frame
        this.add(TopPanel, BorderLayout.NORTH);
        this.add(CentralPanel, BorderLayout.CENTER);

        //Extra frame initialization
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////
    // Main function

    /*** Main function, creates GUI object and sets it to the main menu.*/
    public static void main(String[] args)
    {
        GUI mainGUI = new GUI();
        mainGUI.setToMainMenu();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////
    // Getter(s)
    public GUI_Controller getController() {return InternalC;}

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////
    // Button presses
    public void actionPerformed(ActionEvent e) 
    {
        // If player rolls the dice
        if (e.getSource() == DiceRoll) 
        {
            // Move player
            int move = generateRandomInt(1, 6);

            //Change picture of dice accordingly
            Image image = new ImageIcon(this.getClass().getResource("Pictures/dice/dice" + move + ".png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            DiceRoll.setIcon(new ImageIcon(image));

            //Update panels
            getController().getGameBoard().movePlayer(move);
            getController().updateInternalJPanels();

            //Set whose turn it is
            iconLogo = new ImageIcon("Pictures/Player"+ InternalC.getGameBoard().getCurrentTurn() +".png");
            image = iconLogo.getImage().getScaledInstance(30, 50, java.awt.Image.SCALE_SMOOTH);
            iconLogo = new ImageIcon(image);
            currentTurn.setIcon(iconLogo);
            currentTurn.setText("Player" + InternalC.getGameBoard().getCurrentTurn() + "'s turn");
            TopPanel.add(currentTurn);

            // Check if player has won
            if (InternalC.getGameBoard().getEndGameStatus() == true) {setToEndGameScreen(getController().getWinNo()); }

            revalidate();
            repaint();
        }

        //if the player wants to go to the main menu
        if (e.getSource() == GoToMainMenuButton) {setToMainMenu(); }

        //if the player wants to start the game
        if (e.getSource() == StartGameButton) {setToInGame(); }  

        //if the player wants to quit the game
        if (e.getSource() == quit) {System.exit(0);}

        //Choose number of players
        if(e.getSource() == OnePlayerButton) {NumPlayers = 1;}
        if(e.getSource() == TwoPlayerButton) {NumPlayers = 2;}
        if(e.getSource() == ThreePlayerButton) {NumPlayers = 3;}
        if(e.getSource() == FourPlayerButton) {NumPlayers = 4;}
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////
    //Main UI-update functions

    /**
    * Updates the JPanels of 'this' to make a main menu screen.
    * This main menu screen has clickable buttons that allows the user
    *  to choose the number of players (1-4) and to start the game or quit the program.
    */
    public void setToMainMenu() 
    {
        //Reset internal controller and panels
        clearPanels();
        InternalC = new GUI_Controller(NumPlayers);
        TopPanel.setLayout(new FlowLayout());
        CentralPanel.setLayout(new FlowLayout());
        BottomPanel.setLayout(new GridLayout(3,3));

        //Add menu specific components to panels
        TopPanel.add(makeJLabelFromPicture("/Pictures/Menu.png"));
        CentralPanel.add(StartGameButton);
        BottomPanel.add(OnePlayerButton);
        BottomPanel.add(TwoPlayerButton);
        BottomPanel.add(ThreePlayerButton);
        BottomPanel.add(FourPlayerButton);
        BottomPanel.add(quit);
        BottomPanel.add(new JLabel(""));
        BottomPanel.add(new JLabel(""));

        //Add panels to JFrame
        this.add(BottomPanel, BorderLayout.SOUTH);

        //Re-draw components and JPanels
        revalidate();
        repaint();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    /**
    * Updates the JPanels of 'this' to show the snakes and ladders game in progress.
    * This main menu screen has a clickable button that allows the user roll a dice.
    * It also includes a 10x10 grid that shows each space on the snakes and ladder's board.
    * It also includes a a JLabel that shows which player's turn it is.
    */
    public void setToInGame() 
    {
        //Reset internal controller and panels
        clearPanels();
        InternalC = new GUI_Controller(NumPlayers);
        CentralPanel.setLayout(new GridLayout(10, 10));

        //Add in-game specific components to panels
        TopPanel.add(DiceRoll);
        
        //Set text and image of whose turn it is
        iconLogo = new ImageIcon("Pictures/Player"+ InternalC.getGameBoard().getCurrentTurn() +".png"); //image
        Image image = iconLogo.getImage().getScaledInstance(30, 50, java.awt.Image.SCALE_SMOOTH);
        iconLogo = new ImageIcon(image);
        currentTurn.setIcon(iconLogo);
        currentTurn.setText("Player" + InternalC.getGameBoard().getCurrentTurn() + "'s turn"); //text

        //Add menu specific components to panels
        TopPanel.add(currentTurn);

        //Create squares on board
        addToCentral(100,91);
        addToCentral(81,90);
        addToCentral(80,71);
        addToCentral(61,70);
        addToCentral(60,51);
        addToCentral(41,50);
        addToCentral(40,31);
        addToCentral(21,30);
        addToCentral(20,11);
        addToCentral(1,10);
        
        // Add panels to frame
        this.add(CentralPanel, BorderLayout.CENTER);

        //Re-draw components and JPanels
        getController().updateInternalJPanels();
        revalidate();
        repaint();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    /**
    * Updates the JPanels of 'this' to make an end-game screen.
    * This main menu screen has a clickable button that allows the user to go back to the main menu
    * This screen also shows an image for which player has won
    * @param winNumber Integer between 1-4, this sets which image will be displayed
    */
    public void setToEndGameScreen(int winNumber)
    {
        //Get width and height of JFrame
        int width = this.getSize().width;
        int height = this.getSize().height;

        //Reset internal controller and panels
        clearPanels();
        CentralPanel.setLayout(new FlowLayout());

        //Set Image of which player has won
        iconLogo = new ImageIcon("Pictures/WinScreen/p"+ winNumber +"wins.png");
        Image image = iconLogo.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        iconLogo = new ImageIcon(image);
        gameover.setIcon(iconLogo);

        //Add in-game specific components to panels
        CentralPanel.add(gameover);
        TopPanel.add(GoToMainMenuButton);
        
        // Add panels to frame
        revalidate();
        repaint();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////
    //Supporting functions

    /**
     * Generates a random number between two bounds.
     * @param lowerBound Smallest positive integer desired to be generated
     * @param upperBound Smallest positive integer desired to be generated
     * @return Random number between the two bounds
     */
    public int generateRandomInt(int lowerBound, int upperBound)
    {
        Random rand = new Random();
        return rand.nextInt(upperBound)+lowerBound;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    /**
     * This function adds @see CustomJPanel objects to JPanel
     * @param start Start adding panels from this index
     * @param end End adding panels at this index
     * If start > end, index will subtract down to end
     */
    public void addToCentral(int start, int end)
    {
        if(start > end)
        {
            for (int i = start - 1; i > end-2; i--) 
            {
                CentralPanel.add(InternalC.getPanels().get(i));
            }
        }
        else
        {
            for (int i = start-1; i < end; i++) 
            {
                CentralPanel.add(InternalC.getPanels().get(i));
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    /*** Clears every inner component of every JPanel associated with 'this' object,
     *  This should be preceded with revalidate() and repaint()*/
    public void clearPanels()
    {
        TopPanel.removeAll();
        CentralPanel.removeAll();
        BottomPanel.removeAll();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Function creates a JLabel that has an image 
     * @param filepath String filepath of where the image file is
     * @return Newly constructed JLabel with buffered image as its label
     */
    public JLabel makeJLabelFromPicture(String filepath)
    {
        JLabel pic = new JLabel();
        try 
        {
            BufferedImage image = ImageIO.read(this.getClass().getResource(filepath));
            pic = new JLabel(new ImageIcon(image));
        } catch (IOException e) {e.printStackTrace();}
        return pic;
    }
}