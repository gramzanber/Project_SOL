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
import model.ID;

public class LargeEnemyBlast extends RenderableObject
{
    private static int UNIT_TRAVEL_DISTANCE = 20; // per frame move
    
    private AnimationController animationController;

    private RenderableObject owner;

    
    /**
     *
     * @param sx start x of the missile
     * @param sy start y of the missile
     * @param owner
     */ 
    public LargeEnemyBlast(float sx, float sy, RenderableObject owner, ID id)
    {
        super(new Point((int)sx, (int)sy));

            super.boundingBox = new Rectangle(loc.x, loc.y, 330, 327);
        
            this.owner = owner;
            this.id = id;
            this.distanceTraveled = 0;
                
            animationController = new AnimationController(AnimationController.Mode.AUTO, "large_enemy_blast");
            animationController.setFps(24);             

    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void render(Graphics2D g2,Rectangle viewport){

            //draw in relation to the viewport
            if(this.distanceTraveled >= 100){
                GameData.getInstance().removeGameObject(this);
            }

            int translatedX =  (int)boundingBox.getX() - (int)viewport.getX();
            int translatedY =  (int)boundingBox.getY() - (int)viewport.getY();
                        
            BufferedImage sprite = animationController.getFrame(); 
            g2.drawImage(sprite, translatedX, translatedY, 330, 327, null); 
            distanceTraveled++;
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

    @Override
    public void update()
    {
        loc.x = loc.x - UNIT_TRAVEL_DISTANCE;            

        boundingBox.setLocation(loc);
        
        super.collide();
        
        //check collisions
        ArrayList<RenderableObject> collisions = GameData.getInstance().getCollisions(this);
        
        for(int i=0; i<collisions.size(); i++){
            RenderableObject obj = collisions.get(i);
            if(obj instanceof Hero){
               //GameData.getInstance().removeGameObject(this);
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
       
}
