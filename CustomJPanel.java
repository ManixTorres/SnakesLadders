import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

////////////////////////////////////////
////////////////////////////////////////
////////////////////////////////////////
//Class description

/**
 * This class represents each square on the snakes and ladders board. Each
 * square should be colored differently. Each square will also display image of
 * what players are occupying them.
 */
public class CustomJPanel extends JPanel 
{
    ////////////////////////////////////////
    ////////////////////////////////////////
    ////////////////////////////////////////
    // Member variables
    private static final String PlayerImageFP1 = "/Pictures/Player1.png";
    private static final String PlayerImageFP2 = "/Pictures/Player2.png";
    private static final String PlayerImageFP3 = "/Pictures/Player3.png";
    private static final String PlayerImageFP4 = "/Pictures/Player4.png";

    //Checks if a player is occupying this JPanel
    private boolean p1OnTile = false;
    private boolean p2OnTile = false;
    private boolean p3OnTile = false;
    private boolean p4OnTile = false;

    //Keeps track of which number this should be on the board
    private int tileNumber;

    private static final long serialVersionUID = 1L;
    private JLabel internalJLabel = new JLabel();

    //Images for player pictures
    private BufferedImage image1 = null;
    private BufferedImage image2 = null;
    private BufferedImage image3 = null;
    private BufferedImage image4 = null;

    ////////////////////////////////////////
    ////////////////////////////////////////
    ////////////////////////////////////////
    // Constructor(s)

    /**
     * Default constructor, calls constructor of superclass
     * 
     * @param tNum Which numbered tile this class represents
     * @param SLnum Which snakeLadder is associated with this panel
     * @param desc The description string that determines if a snake or a ladder is at this panel
     */
    public CustomJPanel(int tNum, int SLnum, String desc) 
    {
        super();

        //Initialize member variables
        tileNumber = tNum;
        String SLnumStr = "";
        if(SLnum != 0) {SLnumStr = Integer.toString(SLnum);}
        try 
        {
            image1 = ImageIO.read(this.getClass().getResource(PlayerImageFP1));
            image2 = ImageIO.read(this.getClass().getResource(PlayerImageFP2));
            image3 = ImageIO.read(this.getClass().getResource(PlayerImageFP3));
            image4 = ImageIO.read(this.getClass().getResource(PlayerImageFP4));
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        // Set Panel specifics
        internalJLabel.setText("<html><body>" + Integer.toString(tileNumber) + "<br>" + desc + SLnumStr + "</body></html>");
        internalJLabel.setHorizontalAlignment(JLabel.CENTER);

        //Add label to panel
        this.setLayout(new BorderLayout());
        this.add(internalJLabel, BorderLayout.CENTER);
    }

    ////////////////////////////////////////
    ////////////////////////////////////////
    ////////////////////////////////////////
    /**
     * Overloaded function from @see JPanel Calls parent repaint, but also draws
     * picture in the Panel depending on the status of player objects
     */
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);

        //Get width and height of 'this'
        int width = this.getSize().width;
        int height = this.getSize().height;

        //Adds player1-player4 icons to 'this' if the player is occupying this tile - Images are placed by their top-left corner
        if(p1OnTile == true) {g.drawImage(image1.getScaledInstance(15, 30, Image.SCALE_SMOOTH), 0, 0, this); }
        if(p2OnTile == true) {g.drawImage(image2.getScaledInstance(15, 30, Image.SCALE_SMOOTH), width-15, 0, this); }
        if(p3OnTile == true) {g.drawImage(image3.getScaledInstance(15, 30, Image.SCALE_SMOOTH), 0, height-30, this); }
        if(p4OnTile == true) {g.drawImage(image4.getScaledInstance(15, 30, Image.SCALE_SMOOTH), width-15, height-30, this); }
        repaint();
    }

    ////////////////////////////////////////
    ////////////////////////////////////////
    ////////////////////////////////////////
    /**
    * Given the positions of player objects, checks to see which players on currently on this tile
    * @param P1 Position of player1
    * @param P2 Position of player2
    * @param P3 Position of player3
    * @param P4 Position of player4
    */
    public void updatePanel(int P1, int P2, int P3, int P4)
    {
        p1OnTile = false;
        p2OnTile = false;
        p3OnTile = false;
        p4OnTile = false;

        //If a player is occupying this tile, set variables
        if(P1 == tileNumber) { p1OnTile = true; }
        if(P2 == tileNumber) { p2OnTile = true; }
        if(P3 == tileNumber) { p3OnTile = true; }
        if(P4 == tileNumber) { p4OnTile = true; }
    }
}
