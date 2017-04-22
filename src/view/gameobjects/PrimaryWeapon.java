// Author:	SATAS
// Course:	SDD
// Semester:    Spring, 2017

package view.gameobjects;

import controller.AnimationController;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import model.GameData;

public class PrimaryWeapon extends RenderableObject
{
    // missile size
    //private Image missileImage;
    //private int health;
    private static int weaponLevel = 1;

    private static int UNIT_TRAVEL_DISTANCE = 10; // per frame move
    
    private AnimationController animationController;

    private RenderableObject owner;
    
    /**
     *
     * @param sx start x of the missile
     * @param sy start y of the missile
     */
    public PrimaryWeapon(float sx, float sy, RenderableObject owner)
    {
        super(new Point((int)sx, (int)sy));
        if(weaponLevel == 1){
            super.boundingBox = new Rectangle(loc.x, loc.y, 30, 30);
        
            this.owner = owner;
                
            animationController = new AnimationController(AnimationController.Mode.AUTO, "primary1_right");
            animationController.setFps(4);  
        
            if(Hero.facingRight){
                animationController.setSpriteSheet("primary1_right");
                this.rightProjectile = true;
            }
            else{
                animationController.setSpriteSheet("primary1_left");
                this.rightProjectile = false;
            }            
        }
        else{
            super.boundingBox = new Rectangle(loc.x, loc.y, 90, 18);        
        
            this.owner = owner;
               
            animationController = new AnimationController(AnimationController.Mode.AUTO, "primary2_right");
            animationController.setFps(4);  
        
            if(Hero.facingRight){
                animationController.setSpriteSheet("primary2_right");
                this.rightProjectile = true;
            }
            else{
                animationController.setSpriteSheet("primary2_left");
                this.rightProjectile = false;
            }            
        }

    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void render(Graphics2D g2,Rectangle viewport){
        if(weaponLevel == 1){
            //draw in relation to the viewport
            int translatedX =  (int)boundingBox.getX() - (int)viewport.getX();
            int translatedY =  (int)boundingBox.getY() - (int)viewport.getY();
                        
            BufferedImage sprite = animationController.getFrame(); 
            g2.drawImage(sprite, translatedX, translatedY, 30, 30, null); 
            animationController.update();        
        }
        else{
            //draw in relation to the viewport
            int translatedX =  (int)boundingBox.getX() - (int)viewport.getX();
            int translatedY =  (int)boundingBox.getY() - (int)viewport.getY();
            
            BufferedImage sprite = animationController.getFrame(); 
            g2.drawImage(sprite, translatedX, translatedY, 90, 18, null); 
            animationController.update();       
        }

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

    @Override
    public void update()
    {
        if(this.rightProjectile){
            loc.x = loc.x + UNIT_TRAVEL_DISTANCE;            
        }
        else{
            loc.x = loc.x - UNIT_TRAVEL_DISTANCE;            
        }

        boundingBox.setLocation(loc);
        
        //check collisions
        ArrayList<RenderableObject> collisions = GameData.getInstance().getCollisions(this);
        
        for(int i=0; i<collisions.size(); i++){
            RenderableObject obj = collisions.get(i);
            if(obj != owner){
               GameData.getInstance().removeGameObject(this);
               break;
            }
        }


        
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
    
    public static void setWeaponType(int l){
        weaponLevel = l;
        UNIT_TRAVEL_DISTANCE = l*10;
    }    
}
