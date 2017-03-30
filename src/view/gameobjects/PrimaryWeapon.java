// Author:	SATAS
// Course:	SDD
// Semester:    Spring, 2017

package view.gameobjects;

import controller.AnimationController;
import view.gameobjects.GameFigure;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import model.GameData;

public class PrimaryWeapon extends RenderableObject
{
    // missile size
    //private Image missileImage;
    //private int health;
    private static int weaponLevel = 1;

    private static final int UNIT_TRAVEL_DISTANCE = 2; // per frame move
    
    private AnimationController animationController;

    /**
     *
     * @param sx start x of the missile
     * @param sy start y of the missile
     */
    public PrimaryWeapon(float sx, float sy)
    {
        super(new Point((int)sx, (int)sy));
        super.boundingBox = new Rectangle(loc.x, loc.y, 30, 30);
        
        //state = STATE_ALIVE;
        
        animationController = new AnimationController(AnimationController.Mode.AUTO, "primary1_right");
        animationController.setFps(8);  
        
        if(Hero.facingRight){
            animationController.setSpriteSheet("primary1_right");
        }
        else{
            animationController.setSpriteSheet("primary1_left");
        }        

        
        //check collisions
//        ArrayList<RenderableObject> collisions = GameData.getInstance().getCollisions(this);
//        for(int i=0; i< collisions.size(); i++){
//            RenderableObject obj = collisions.get(i);
//            if(!(obj instanceof Hero)){
//                GameData.getInstance().removeGameObject(this);
//                return;
//            }
//        }
        
        
        //missileImage = null;
        //try {
        //    missileImage = ImageIO.read(getClass().getResource("/Images/Missile.bmp"));
        //} catch (IOException ex) {
        //    JOptionPane.showMessageDialog(null, "Error: Cannot open shooter.png");
        //    System.exit(-1);
        //}
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void render(Graphics2D g2,Rectangle viewport){

        //draw in relation to the viewport
        int translatedX =  (int)boundingBox.getX() - (int)viewport.getX();
        int translatedY =  (int)boundingBox.getY() - (int)viewport.getY();
            
            
            BufferedImage sprite = animationController.getFrame(); 
            g2.drawImage(sprite, translatedX, translatedY, 30, 30, null); 
            animationController.update();

    }

//    @Override
//    public void render(Graphics2D g)
//    {
//        g.drawImage(missileImage, (int) (super.x - size / 2), (int) (super.y - size / 2), 10, 10, null);
//    }

//    @Override
//    public void update() {
//        //updateState();
//        if (state == STATE_ALIVE) {
//            updateLocation();
//        }
//        else if (state == STATE_DYING) {
//        }
//        
//        
//        
//    }

    public void update()
    {
        loc.x = loc.x + UNIT_TRAVEL_DISTANCE;
        boundingBox.setLocation(loc);
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
    
    //@Override
    public String getObjectType()
    {
        return "Missile";
    }
    
    //@Override
    //public int getSize()
    //{
    //    return 3;
    //}

//    @Override
//    public int getHealth() { return this.health; }
//
//    @Override
//    public void damageFigure() { this.health = this.health - 1; }

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
    
    public static int getWeaponType(){
        return weaponLevel;
    }
}
