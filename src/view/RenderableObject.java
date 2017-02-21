package view;

import controller.Main;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
* An abstract class to define needed methods for the rendering engine
*
* @author  SATAS
* @version 1.0
* @since   2017-02-18 
*/
public abstract class RenderableObject implements MouseListener,KeyListener,MouseMotionListener{
    
    protected Point loc; //the location of this object
    protected Rectangle boundingBox;
    
    /**
    * A simple constructor 
    * 
    * @param loc The location of the object
    */
    public RenderableObject(Point loc){
        this.loc = loc;
        boundingBox = new Rectangle(loc.x, loc.y, 10, 10);
    }
    
    /**
    * Called once per frame, advance any animations
    */
    public abstract void update();
    
    /**
    * Render to a graphics 2d object
    * 
    * @param g2 The graphics2d object
    * @param viewport The current viewport, objects outside of viewport are skipped
    */
    public void render(Graphics2D g2, Rectangle viewport){
        if(viewport.contains(loc)){
            g2.drawRect(boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
        }
    }
    
    /**
    * Remove this object from all listener lists
    */
    public void clear(){
        Main.gamePanel.removeMouseListener(this);
        Main.gamePanel.removeKeyListener(this);
        Main.gamePanel.removeMouseMotionListener(this);
    }

}
