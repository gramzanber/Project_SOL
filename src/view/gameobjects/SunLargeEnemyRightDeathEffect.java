package view.gameobjects;

import controller.AnimationController;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import model.GameData;

/**
* @author  SATAS
* @version 1.0
* @since   2017-02-18 
*/
public class SunLargeEnemyRightDeathEffect extends RenderableObject {
    
    private AnimationController animationController;
    
    public SunLargeEnemyRightDeathEffect(Point loc) {
        super(loc);
        animationController = new AnimationController(AnimationController.Mode.AUTO, "sun_large_enemy_die_right");
        animationController.setFps(12);
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void update(){
        super.update();
        animationController.update();
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void render(Graphics2D g2,Rectangle viewport)
    {
        
        //draw in relation to the viewport
        int translatedX =  (int)boundingBox.getX() - (int)viewport.getX();
        int translatedY =  (int)boundingBox.getY() - (int)viewport.getY();
            
        BufferedImage sprite = animationController.getFrame();
        if(viewport.intersects(this.getBoundingBox())){
            g2.drawImage(sprite, translatedX-120, translatedY-80, sprite.getWidth()*2, sprite.getHeight()*2, null);                 
        }
        if(animationController.getLoopCount() > 0){
            AnimationController.explosionEffect(new Point((int)getBoundingBox().getCenterX(), (int)getBoundingBox().getCenterY()+80));            
            GameData.getInstance().removeGameObject(this);
        }
    }

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
