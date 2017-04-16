package view.gameobjects;

import controller.AnimationController;
import controller.SoundController;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import model.GameData;

/**
*
* @author  SATAS
* @version 1.0
* @since   2017-02-18 
*/
public class Gold extends Tile {


    private static BufferedImage coinSpriteSheet = null;
    private int step;
    private long lastStep;
    
    private AnimationController animationController;
    
    /**
    * Constructor 
    * 
    * @param loc
    */
    public Gold(Point loc) {
        //call superclass constructor
        super(loc);
        
        animationController = new AnimationController(AnimationController.Mode.AUTO, "gold_coin");
        animationController.setFps(13);
        
        this.solid = false;
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void update(){
        animationController.update();

        //check collision
        if(GameData.getInstance().getHero().getBoundingBox().intersects(boundingBox)){
            SoundController.getInstance().coinPickUp();
            
            this.clear();
            GameData.getInstance().removeGameObject(this);
            
            Score.setScore(1);
        }
        
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
            
            //draw sprite
            BufferedImage frame = animationController.getFrame();
            g2.drawImage(frame, translatedX, translatedY, TILESIZE, TILESIZE, null);
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
