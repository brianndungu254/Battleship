import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ship here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ship extends Actor
{
    private boolean set;
    private int size;
    
    /**
     * Ship is the default constructor for object type ship
     * 
     * @param There are no parameters
     * @return objects of type ship
     */
    public Ship()
    {
        GreenfootImage shipImage = new GreenfootImage( 72, 72 );
        shipImage.setColor( Color.GRAY );
        shipImage.fillRect( 0, 0, shipImage.getWidth(), shipImage.getHeight() );
        setImage( shipImage );
        
        set = false;
    }
    
    /**
     * Ship is the constructor for object of type ship
     * 
     * @param s is the size of the ship
     * @return objects of type ship
     */
    public Ship( int s )
    {
        size = s;
        
        GreenfootImage shipImage = new GreenfootImage( 72 * size, 72 );
        shipImage.setColor( Color.GRAY );
        shipImage.fillRect( 0, 0, shipImage.getWidth(), shipImage.getHeight() );
        setImage( shipImage );
        
        
    }
    
    /**
     * Act - do whatever the Ship wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if( Greenfoot.mouseClicked(this) )
        {
            set = true;
         
            checkSettings();
        }
        
        if(mouse != null && set == false )
        {
            setLocation( mouse.getX(), mouse.getY() );
        }
    }
    
     /**
     * getSize returns the size of the ship
     * 
     * @param There are no parameters
     * @return an int that represents the size of the ship
     */
    public int getSize()
    {
        return size;
    }
    
    /**
     * checkSettings ensures that the ships are placed in the correct location
     * due to the fact the world uses cells of one 1 pixel by 1 pixel
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    private void checkSettings()
    {
        if( getRotation() == 0 || getRotation() == 180 )
        {
            if( getImage().getWidth() /72 % 2 == 0)
            {
                setLocation( getX() /72 * 72, getY() /72 * 72 + 36);
            }
            else
            {
                setLocation( getX() /72 * 72 + 36, getY() /72 * 72 + 36);
            }
        }
        else
        {
            if( (getImage().getWidth() / 72) % 2 ==0 )
            {
               setLocation( getX() /72 * 72 + 36 , getY() /72 * 72 );
            }
            else
            {
               setLocation( getX() /72 * 72 + 36, getY() /72 * 72 + 36); 
            }
        }       
    }    
}
