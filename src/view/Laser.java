// Author:	SATAS
// Course:	SDD
// Semester:    Spring, 2017

package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class Laser extends GameFigure
{
    // public properties for quick access
    public Color color;
    public Point2D.Float target;

    private static final int UNIT_TRAVEL_DISTANCE = 1; // per frame move
    private final int size = 5;
    private final boolean direction;
    private int health;

    public Laser(float x, float y, Color color, boolean direction)
    {
        super(new Point((int)x, (int)y));
        
        state = STATE_ALIVE;
        this.color = color;
        this.health = 1;
        this.direction = direction;
    }



    @Override
    public void update()
    {
        updateState();
        if (state == STATE_ALIVE)
        {
            updateLocation();
        }
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void render(Graphics2D g2,Rectangle viewport){
        
        g2.setColor(color);
        // Top Laser
        g2.fillOval((int) (loc.x - size / 2), (int) (loc.y - size / 2) + 3, size * 5, size);
        // Bottom Laser
        g2.fillOval((int) (loc.x - size / 2), (int) (loc.y - size / 2) + 20, size * 5, size);
    }
    
    
    public void updateLocation()
    {
        if(this.direction) { 
            loc.x = loc.x + UNIT_TRAVEL_DISTANCE; 
        }
        else { 
            loc.x = loc.x - UNIT_TRAVEL_DISTANCE; 
        }
        boundingBox.setLocation(loc);
    }

    public void updateState()
    {
//        if (state == STATE_ALIVE)
//        {
//            boolean targetReached = super.x >= GamePanel.width;
//            if (targetReached) { super.state = STATE_DONE; }
//        }
    }

//    @Override
//    public Rectangle2D getCollisionBox()
//    {
//        return new Rectangle2D.Double(this.x - this.size / 2, (this.y - this.size / 2) + 5, this.size * 5, this.size * 4);
//    }

    @Override
    public String getObjectType() { return "Laser"; }
    
    //@Override
    //public int getSize() { return 3; }

    @Override
    public int getHealth() { return this.health; }

    @Override
    public void damageFigure() { this.health = this.health - 1; }

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
