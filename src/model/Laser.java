// Author:	SATAS
// Course:	SDD
// Semester:    Spring, 2017

package model;

import controller.Main;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import view.GamePanel;

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
//        Shooter shooter = (Shooter) Main.gameData.friendFigures.get(0);
//        float shooterX = shooter.getXofMissileShoot();
//        if(this.x == shooterX) { this.direction = !shooter.getDirection(); } else { this.direction = super.x-shooterX > 0; }
    }

//    @Override
//    public void render(Graphics2D g)
//    {
//        g.setColor(color);
//        // Top Laser
//        g.fillOval((int) (super.x - size / 2), (int) (super.y - size / 2) + 3, size * 5, size);
//        // Bottom Laser
//        g.fillOval((int) (super.x - size / 2), (int) (super.y - size / 2) + 20, size * 5, size);
//    }

    @Override
    public void update()
    {
        updateState();
        if (state == STATE_ALIVE)
        {
            updateLocation();
            System.out.println("test");
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
//    
    @Override
    public String getObjectType() { return "Laser"; }
    
    @Override
    public int getSize() { return 3; }

    @Override
    public int getHealth() { return this.health; }

    @Override
    public void damageFigure() { this.health = this.health - 1; }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
