import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.JOptionPane;
/**
 * Write a description of class PlayerWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerWorld extends World
{
    private int [][] board = new int [10][10];
    
    private boolean firstTurn = true;
    private boolean playerOneTurn = true;
    private boolean messageShown;
    
    private int shipCounter = 0;
    
    private boolean addedToGrid = false;
    
    private Ship toAdd;
    
    private int refreshCounter = 10;
    /**
     * PlayerWorld is the constructor for objects of class PlayerWorld
     * 
     * @param There are no parameters
     * @return an object of type PlayerWorld
     */
    public PlayerWorld()
    {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(720, 720, 1);
        
        drawLines();
        for( int r = 0; r < board.length; r++ )
        {
            for( int c = 0; c < board[r].length; c++ )
            {
                board[r][c] = 0;       
            }
        }
    }
      
    /**
     * drawLines draws the 10x10 grid that we can place our ships inside for the game Battleship
     * 
     * @param
     * @return an object of type 
     */
    private void drawLines()
    {
        getBackground().setColor( Color.RED );
        
        for( int i = 72; i < getWidth(); i += 72 )
        {
            getBackground().drawLine(i, 0, i, getHeight() );
            getBackground().drawLine(0, i, getWidth(), i);
        }
    }
    
    /**
     * act is the act cycle..
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    public void act()
    {
        Ship currentShip;
        GreenfootImage transparentShip;
        
        
        displayTurn();
        
        if( firstTurn == true )
        {
            addShips();
        }
        else
        {
            for( int i = 0; i < getObjects(Ship.class).size(); i++ )
            {
                currentShip = getObjects(Ship.class).get(i);
                transparentShip = new GreenfootImage( currentShip.getImage().getWidth(), currentShip.getImage().getHeight() );
                
            }
            Greenfoot.stop();
        }
    }
   
    /**
     * displayTurn shows different messages at different points of the game
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    private void displayTurn()
    {
        if( firstTurn == true )
        {
            if( messageShown == false)
            {
                JOptionPane.showMessageDialog( null, "Player One, please select where your ships should be placed", "please place ships", JOptionPane.WARNING_MESSAGE );
                messageShown = true;
            }
        }
        else
        {
            if( messageShown == false)
            {   
                JOptionPane.showMessageDialog( null, "Player two, please select a spot to attack on the board....****", "ATTACK SHIPS", JOptionPane.PLAIN_MESSAGE );
                messageShown = true;
        
            }
        }   
    }
    
    /**
     * addShips
     */
    private void addShips()
    {
        if( shipCounter >= 5 )
        {
            firstTurn = false;
            fillBoard();
            displayBoard();
        }
        else
        {
            if( addedToGrid == false )
            {
                toAdd = checkShip();
                
                addObject( toAdd, 0, 0);
                
                addedToGrid = true;
            }
            
            checkKeyPress( toAdd );
            if( Greenfoot.mouseClicked(null) )
            {
                shipCounter++;
                
                addedToGrid = false;
            }
        }
    }
    
    /**
     * checkShip checks what ship is added to the grid
     * 
     * @param
     * @return next ship that will be added to the world
     */
    private Ship checkShip()
    {
        Ship current = new Ship();
        
        if( shipCounter == 0)
        {
            current = new Carrier();
        }
        
        if( shipCounter == 1)
        {
            current = new Battleship();
        }
        
        if( shipCounter == 2)
        {
            current = new Sub();
        }
        
        if( shipCounter == 3)
        {
            current = new Destroyer();
        }
        
        if( shipCounter == 4)
        {
            current = new Patrol();
        }
        
        return current;
    }
    
    private void checkKeyPress( Ship current)
    {
        if( Greenfoot.isKeyDown("shift") && refreshCounter > 10 )
        {
            current.setRotation( current.getRotation() + 90);
            refreshCounter = 0;
        }
        else
        {
            refreshCounter++;
        }
    }
    
    /**
     * fillBoard fills the numeric representation of the board with ones where the ships are located
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    private void fillBoard()
    {
        Ship toAdd;
        
        int currentX;
        int currentY;
        int rotation;
        
        for( int i = 0; i < getObjects(Ship.class).size(); i++ )
        {
            toAdd = getObjects(Ship.class).get(i);
            
            currentX = toAdd.getX();
            currentY = toAdd.getY();
            rotation = toAdd.getRotation();
            
            for( int j = 0; j < (toAdd.getSize() +1) / 2; j++ )
            {
                if( toAdd.getSize() % 2 == 1 )
                {
                    if( rotation == 0 || rotation == 180 )
                    {
                        board[ (currentY - 36) / 72 ] [ (currentX - 36) / 72 - j ] = 1;
                        board[ (currentY - 36) / 72 ] [ (currentX - 36) / 72 + j ] = 1;
                    }
                    else
                    {
                        board[ (currentY - 36) / 72 - j ] [ (currentX - 36) / 72 ] = 1;
                        board[ (currentY - 36) / 72 + j ] [ (currentX - 36) / 72 ] = 1;
                    }
                }
                else
                {
                    if( rotation == 0 || rotation == 180 )
                    {
                        board[ (currentY - 36) / 72 ] [ (currentX - 36) / 72 - j ] = 1;
                        board[ (currentY - 36) / 72 ] [ (currentX - 36) / 72 + j ] = 1;
                        board[ (currentY - 36) / 72 ] [ (currentX - 36) / 72 + j + 1 ] = 1;
                    }
                    else
                    {
                        board[ (currentY - 36) / 72 - j ] [ (currentX - 36) / 72 ] = 1;
                        board[ (currentY - 36) / 72 + j ] [ (currentX - 36) / 72 ] = 1;
                        board[ (currentY - 36) / 72 + j + 1 ] [ (currentX - 36) / 72 ] = 1;
                    }
                }
            }
        }
    }
    
    /**
     * displayBoard displays the numerical representation of the gameboard where 1's indicate
     * that a ship was placed at that location and 0's
     * insicate that no ship is at that location
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    private void displayBoard()
    {
        for( int r = 0; r < board.length; r++ )
        {
            for( int c = 0; c < board[r].length; c++ )
            {
                System.out.print( board[r] [c] + "\t" );
            }
            
            System.out.println();
        }
       
        System.out.println();
    }
}   
