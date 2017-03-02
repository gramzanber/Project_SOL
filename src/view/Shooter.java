// Author:	SATAS
// Course:	SDD
// Semester:    Spring, 2017

package view;

import controller.Main;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
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
    private float extraSpeed = 2.5f;

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
    
    @Override
    public String getObjectType()
    {
        return "Player";
    }


    @Override
    public int getHealth() { return this.health; }

    @Override
    public void damageFigure() { this.health = this.health - 1; }

    @Override
    public void update() {
        boundingBox.setLocation(loc);
        gotoEarth(boolEarth);
        gotoMoon(boolMoon);
        gotoVenus(boolVenus);
        gotoMercury(boolMercury);
        gotoSun(boolSun);
    }
    //auto navigate
    //EARTH 
    public void gotoEarth(boolean bool){
        if(bool){
            float diffX = (float) (loc.x - (Main.game.getWidth()/1.65) - 7.5);
            float diffY = (float) (loc.y - (Main.game.getHeight()/3) - 7.5);
            float distance = (float) Math.sqrt((loc.x - (Main.game.getWidth()/1.65)) * (loc.x - (Main.game.getWidth()/1.65)) + (loc.y - (Main.game.getHeight()/3)) * (loc.y - (Main.game.getHeight()/3)));
            //System.out.println(Main.game.getWidth());
            velX = (float) (diffX * (-1.0/distance));  
            velY = (float) (diffY * (-1.0/distance));
            
            loc.x += (extraSpeed * velX);
            loc.y += (extraSpeed * velY);
        }    
    }
    //MOON 
    public void gotoMoon(boolean bool){
        if(bool){
            float diffX = (float) (loc.x - (Main.game.getWidth()/1.4) - 7.5);
            float diffY = (float) (loc.y - (Main.game.getHeight()/4) - 7.5);
            float distance = (float) Math.sqrt((loc.x - (Main.game.getWidth()/1.4)) * (loc.x - (Main.game.getWidth()/1.4)) + (loc.y - (Main.game.getHeight()/4)) * (loc.y - (Main.game.getHeight()/4)));
            //System.out.println(Main.game.getWidth());
            velX = (float) (diffX * (-1.0/distance));  
            velY = (float) (diffY * (-1.0/distance));
            
            loc.x += (extraSpeed * velX);
            loc.y += (extraSpeed * velY);
        }    
    }
    //VENUS 
    public void gotoVenus(boolean bool){
        if(bool){
            float diffX = (float) (loc.x - (Main.game.getWidth()/2.3) - 7.5);
            float diffY = (float) (loc.y - (Main.game.getHeight()/1.65) - 7.5);
            float distance = (float) Math.sqrt((loc.x - (Main.game.getWidth()/2.3)) * (loc.x - (Main.game.getWidth()/2.3)) + (loc.y - (Main.game.getHeight()/1.65)) * (loc.y - (Main.game.getHeight()/1.65)));
            //System.out.println(Main.game.getWidth());
            velX = (float) (diffX * (-1.0/distance));  
            velY = (float) (diffY * (-1.0/distance));
            
            loc.x += (extraSpeed * velX);
            loc.y += (extraSpeed * velY);
        }    
    }
    //MERCURY 
    public void gotoMercury(boolean bool){
        if(bool){
            float diffX = (float) (loc.x - (Main.game.getWidth()/4) - 7.5);
            float diffY = (float) (loc.y - (Main.game.getHeight()/2) - 7.5);
            float distance = (float) Math.sqrt((loc.x - (Main.game.getWidth()/4)) * (loc.x - (Main.game.getWidth()/4)) + (loc.y - (Main.game.getHeight()/2)) * (loc.y - (Main.game.getHeight()/2)));
            //System.out.println(Main.game.getWidth());
            velX = (float) (diffX * (-1.0/distance));  
            velY = (float) (diffY * (-1.0/distance));
            
            loc.x += (extraSpeed * velX);
            loc.y += (extraSpeed * velY);
        }    
    }
    //SUN
    public void gotoSun(boolean bool){
        if(bool){
            float diffX = (float) (loc.x - (Main.game.getWidth()/12) - 7.5);
            float diffY = (float) (loc.y - (Main.game.getHeight()/2) - 7.5);
            float distance = (float) Math.sqrt((loc.x - (Main.game.getWidth()/10)) * (loc.x - (Main.game.getWidth()/10)) + (loc.y - (Main.game.getHeight()/2)) * (loc.y - (Main.game.getHeight()/2)));
            System.out.println(Main.game.getWidth());
            velX = (float) (diffX * (-1.0/distance));  
            velY = (float) (diffY * (-1.0/distance));
            
            loc.x += (extraSpeed * velX);
            loc.y += (extraSpeed * velY);
        }    
    }
    
    /**
    * {@inheritDoc}
    */
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
            //if(e.getKeyCode() != null){
                //System.out.println("KEY PRESSED");
                //boolVenus = true;   
            //}
            if(boolEarth == true && e.getKeyCode() == KeyEvent.VK_RIGHT){
                System.out.println("KEY PRESSED");
                boolEarth = false;
                boolMoon = true;
            }else if(boolMoon == true && e.getKeyCode() == KeyEvent.VK_LEFT){
                System.out.println("KEY PRESSED");
                boolMoon = false;
                boolEarth = true;
            }else if(boolEarth == true && e.getKeyCode() == KeyEvent.VK_LEFT){
                System.out.println("KEY PRESSED");
                boolEarth = false;
                boolVenus = true;
            }else if(boolVenus == true && e.getKeyCode() == KeyEvent.VK_LEFT){
                System.out.println("KEY PRESSED");
                boolMercury = true;
                boolVenus = false;
            }else if(boolMercury == true && e.getKeyCode() == KeyEvent.VK_LEFT){
                System.out.println("KEY PRESSED");
                boolMercury = false;
                boolSun = true;
            }else if(boolSun == true && e.getKeyCode() == KeyEvent.VK_RIGHT){
                System.out.println("KEY PRESSED");
                boolSun = false;
                boolMercury = true;
            }else if(boolMercury == true && e.getKeyCode() == KeyEvent.VK_RIGHT){
                System.out.println("KEY PRESSED");
                boolMercury = false;
                boolVenus = true;
            }else if(boolVenus == true && e.getKeyCode() == KeyEvent.VK_RIGHT){
                System.out.println("KEY PRESSED");
                boolVenus = false;
                boolEarth = true;
            }
        
        
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
