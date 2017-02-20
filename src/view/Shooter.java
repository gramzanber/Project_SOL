// Author:	SATAS
// Course:	SDD
// Semester:    Spring, 2017

package view;

import view.GameFigure;
import controller.Main;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Shooter extends GameFigure
{
    private Image launcherImageLeft, launcherImageRight;
    private boolean direction;
    private int health;

    public Shooter(Point loc) {
        super(loc);
        
        super.state = STATE_ALIVE;

        try { 
            launcherImageLeft = ImageIO.read(getClass().getResource("/Images/Player_Ship_Left.png")); 
        }
        catch (IOException ex) { 
            JOptionPane.showMessageDialog(null, "Error: Cannot open shooter.png" + ex.getMessage()); 
            System.exit(-1); 
        }
        
        try { 
            launcherImageRight = ImageIO.read(getClass().getResource("/Images/Player_Ship_Right.png")); 
        }
        catch (IOException ex) { 
            JOptionPane.showMessageDialog(null, "Error: Cannot open shooter.png" + ex.getMessage()); 
            System.exit(-1); 
        }
        
        this.direction = true;
        this.health = 10;
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void render(Graphics2D g2,Rectangle viewport){
        if(!this.direction) { 
            g2.drawImage(launcherImageRight, (int) loc.x, (int) loc.y, 30, 30, null); 
        }
        else { 
            g2.drawImage(launcherImageLeft, (int) loc.x, (int) loc.y, 30, 30, null); 
        }
    }
    
    
    
    public boolean getDirection() { return this.direction; }

//    @Override
//    public void render(Graphics2D g)
//    {
//        if(!this.direction) { g.drawImage(launcherImageRight, (int) super.x, (int) super.y, 30, 30, null); }
//        else { g.drawImage(launcherImageLeft, (int) super.x, (int) super.y, 30, 30, null); }
//    }
//
//    @Override
//    public void update() {
//        // no periodic update is required (not animated)
//        // update is done via 'translate' when a key is pressed
//    }

    public void translate(int dx, int dy)
    {
        if(dx >= 0) { this.direction = true; } else if(dx < 0) { this.direction = false; }
        loc.x = loc.x + dx;
        loc.y = loc.y + dy;
        boundingBox.setLocation(loc);
    }

    // Missile shoot location: adjut x and y to the image
    public float getXofMissileShoot() {
        return loc.x + 15;
    }

    public float getYofMissileShoot() {
        return loc.y;
    }

    public static Image getImage(String fileName) {
        Image image = null;
        try {
            image = ImageIO.read(new File(fileName));
        } catch (IOException ioe) {
            System.out.println("Error: Cannot open image:" + fileName);
            JOptionPane.showMessageDialog(null, "Error: Cannot open image:" + fileName);
        }
        return image;
    }

//    @Override
//    public Rectangle2D getCollisionBox() {
//        return new Rectangle2D.Double(this.x, this.y, 30.0, 30.0);
//    }
    
    @Override
    public String getObjectType()
    {
        return "Player";
    }

//    @Override
//    public int getSize()
//    {
//        return 5;
//    }

    @Override
    public int getHealth() { return this.health; }

    @Override
    public void damageFigure() { this.health = this.health - 1; }

    @Override
    public void update() {



    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(SwingUtilities.isRightMouseButton(e)){
            int px = e.getX();
            int py = e.getY();
            Main.gameController.addMissile(getXofMissileShoot(),getYofMissileShoot(), px, py, Color.RED);
        }
        else{
            Main.gameController.addLaser(getXofMissileShoot(),getYofMissileShoot(), Color.RED, this.getDirection());
        }
    
            

        
        
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
        
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_LEFT:
                    translate(-10, 0);
                    break;
                case KeyEvent.VK_RIGHT:
                    translate(10, 0);
                    break;
                case KeyEvent.VK_UP:
                    translate(0, -10);
                    break;
                case KeyEvent.VK_DOWN:
                    translate(0, 10);
                    break;
            }
        
        
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
