package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
* Render a block to the screen.
*
* @author  SATAS
* @version 1.0
* @since   2017-02-18 
*/
public class Block extends RenderableObject {

    
    
    public enum Style {
        TRANSPARENT, GROUND, BLUE, SIMPLE
    }
    
    private final Style style;
    
    /**
    * Constructor 
    * 
    * @param loc
    */
    public Block(Style style, Point loc, int width, int height) {
        //call superclass constructor
        super(loc);
        this.style = style;
        solid = true;
        
        //update bounding box for the object
        super.boundingBox = new Rectangle(loc.x, loc.y, width, height);
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void update(){
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void render(Graphics2D g2,Rectangle viewport){
        if(viewport.intersects(boundingBox)){
            
            //draw the block in relation to the viewport
            int translatedX =  (int)boundingBox.getX() - (int)viewport.getX();
            int translatedY =  (int)boundingBox.getY() - (int)viewport.getY();
            
            if(style == Style.TRANSPARENT){
                //dont render
            }
            else if(style == Style.BLUE){
                //render blue block
                int border = 2;
                g2.setColor(Color.GRAY);
                g2.fillRect(translatedX, translatedY, (int)boundingBox.getWidth(), (int)boundingBox.getHeight());
                g2.setColor(Color.BLUE);
                g2.fillRect(translatedX+border, translatedY+border, (int)boundingBox.getWidth()-border*2, (int)boundingBox.getHeight()-border*2);

            }
            else if(style == Style.GROUND){
                //render ground texture
                g2.setColor(Color.GRAY);
                g2.fillRect(translatedX, translatedY, (int)boundingBox.getWidth(), (int)boundingBox.getHeight());
            }
            
            
            
        }
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void mouseClicked(MouseEvent e) {
        if(boundingBox.contains(e.getPoint())){
        }
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
        if(boundingBox.contains(e.getPoint())){
        }
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
