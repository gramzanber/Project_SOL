package view;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
* This class renders a background image.
* 
* @author  SATAS
* @version 1.0
* @since   2017-02-18 
*/
public class Background extends RenderableObject {
    
    private Image backgroundImage; //the image
    private double backgroundLocation; //for scroll animation, this is the current step
    boolean stretch; //is the images stretched or cliped?
    boolean scroll; //should the background be animated to scroll?
    
    /**
    * Constructor for the Background object
    *
    * @param imagePath The relative path to the image file
    * @param stretch Should the image be stretched
    * @param scroll Should the image be animated
    */
    public Background(String imagePath, boolean stretch, boolean scroll) {
        super(new Point(0, 0)); //all backgrounds start at 0,0
        
        this.stretch = stretch; //set stretched
        this.scroll = scroll; //set scroll
        
        //initialize offset for animated backgrounds
        backgroundLocation = 0;
        
        //load the image file
        try{
            this.backgroundImage = ImageIO.read(getClass().getResource(imagePath));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open background.png");
            System.exit(-1);
        }
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void update(){
        //if scroll is true update the location every frame
        if(scroll){
            if(Math.abs(this.backgroundLocation) >= 350) { 
                this.backgroundLocation = 0; 
            }
            else { 
                this.backgroundLocation = this.backgroundLocation - (.5); 
            }  
        }
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void render(Graphics2D g2, Rectangle viewport){
        //set size to the size of the image
        int width = backgroundImage.getWidth(null);
        int height = backgroundImage.getHeight(null);
        
        //if stretch set size to the size of the viewport
//        if(stretch){
//            width = (int)viewport.getWidth();
//            height = (int)viewport.getHeight();
//        }

        //draw the image
        g2.drawImage(backgroundImage, (int)this.backgroundLocation, 0, width, height, null);
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void keyPressed(KeyEvent e) {
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
}
