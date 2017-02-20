// Author:	SATAS
// Course:	SDD
// Semester:    Spring, 2017

package view;

import view.GameFigure;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Missile extends GameFigure
{
    // missile size
    private static final int SIZE = 5;
    private static final int MAX_EXPLOSION_SIZE = 50;
    private float dx; // displacement at each frame
    private float dy; // displacement at each frame
    private Image missileImage;
    private int health;

    // public properties for quick access
    public Color color;
    public Point2D.Float target;

    private static final int UNIT_TRAVEL_DISTANCE = 2; // per frame move

    private int size = SIZE;

    /**
     *
     * @param sx start x of the missile
     * @param sy start y of the missile
     * @param tx target x of the missile
     * @param ty target y of the missile
     * @param color color of the missile
     */
    public Missile(float sx, float sy, float tx, float ty, Color color)
    {
        super(new Point((int)sx, (int)sy));
        
        state = STATE_ALIVE;
        target = new Point2D.Float(tx, ty);
        this.color = color;
        double angle = Math.atan2(Math.abs(ty - sy), Math.abs(tx - sx));
        dx = (float) (UNIT_TRAVEL_DISTANCE * Math.cos(angle));
        dy = (float) (UNIT_TRAVEL_DISTANCE * Math.sin(angle));
        this.health = 3;

        if (tx > sx && ty < sy) { // target is upper-right side
            dy = -dy; // dx > 0, dx < 0
        } else if (tx < sx && ty < sy) { // target is upper-left side
            dx = -dx;
            dy = -dy;
        } else if (tx < sx && ty > sy) { // target is lower-left side
            dx = -dx;
        } else { // target is lower-right side
            // dx > 0 , dy > 0
        }
        
        missileImage = null;

        try {
            missileImage = ImageIO.read(getClass().getResource("..\\Images\\Missile.bmp"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open shooter.png");
            System.exit(-1);
        }
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void render(Graphics2D g2,Rectangle viewport){
            g2.drawImage(missileImage, (int) loc.x, (int) loc.y, 30, 30, null); 
    }

//    @Override
//    public void render(Graphics2D g)
//    {
//        g.drawImage(missileImage, (int) (super.x - size / 2), (int) (super.y - size / 2), 10, 10, null);
//    }

    @Override
    public void update() {
        //updateState();
        if (state == STATE_ALIVE) {
            updateLocation();
        } else if (state == STATE_DYING) {
            updateSize();
        }
    }

    public void updateLocation() {

        loc.x = loc.x + (int)dx;
        loc.y = loc.y + (int)dy;
        super.boundingBox.setLocation(loc);

    }

    public void updateSize() {
        size += 2;
    }

    public void updateState() {
//        if (state == STATE_ALIVE) {
//            double distance = target.distance(super.x, super.y);
//            boolean targetReached = distance <= 2.0;
//            if (targetReached) {
//                state = STATE_DYING;
//            }
//        } else if (state == STATE_DYING) {
//            if (size >= MAX_EXPLOSION_SIZE) {
//                state = STATE_DONE;
//            }
//        }
    }

//    @Override
//    public Rectangle2D getCollisionBox() {
//        return new Rectangle2D.Double(this.x - this.size / 2, this.y - this.size / 2, .9 * this.size, .9 * this.size);
//    }
    
    @Override
    public String getObjectType()
    {
        return "Missile";
    }
    
    //@Override
    //public int getSize()
    //{
    //    return 3;
    //}

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
