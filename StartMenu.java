import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Name brian ndungu
 * Course CS40S
 * Teacher Mr.Hardman
 * Lab #1, program #0
 * Date last modified: February 22nd,2018
 */
public class StartMenu extends World
{
    private GreenfootImage startBackground = new GreenfootImage( "rizo.jpg" );
    
    /**
     * StartMenu is the constructor for objects of class StartMenu.
     *
     * @param There are no parameters
     * @return objects of StartMenu
     */
    public StartMenu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(640, 360, 1);
        
        startBackground.scale( getWidth(), getHeight() );
        
        setBackground( startBackground );
        
        addObject( new StartButton(), getWidth()/2, 100 );
    }
}
