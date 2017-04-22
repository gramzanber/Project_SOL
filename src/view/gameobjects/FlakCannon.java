/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gameobjects;

import controller.AnimationController;
import controller.Main;
import controller.PhysicsController;
import controller.PhysicsController.DIRECTION;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import model.GameData;

/**
 *
 * @author alebel
 */
public class FlakCannon extends Weapon {

    private PhysicsController pyc;
    private AnimationController animationController;
    private int initialX = 0;
    private int initialY = 0;
    Point deleteFlak;
    
    public FlakCannon(float sx, float sy, RenderableObject owner) {
        super(sx, sy, owner);
        
        //TODO: this should be passed in via parameters im just overiding it here for quick debuging
        int x = GameData.getInstance().getHero().getBoundingBox().x;
        int y = GameData.getInstance().getHero().getBoundingBox().y;
        //super.boundingBox = new Rectangle(x + 50, y + 25, 100, 100);
        
        //deleteFlak = new Point(x + 250, y + 25);
        pyc = new PhysicsController(this);
        
        
        
        if(Hero.facingRight){
            super.boundingBox = new Rectangle(x + 50, y + 25, 100, 100);
            animationController = new AnimationController(AnimationController.Mode.AUTO, "FlakCannonRight");
            animationController.setFps(18);
        
            animationController.setSpriteSheet("FlakCannonRight");
            pyc.addForce(DIRECTION.RIGHT, 150);
            deleteFlak = new Point(x + 250, y + 25);
        }else {
            super.boundingBox = new Rectangle(x - 50, y + 25, 100, 100);
            animationController = new AnimationController(AnimationController.Mode.AUTO, "FlakCannonLeft");
            animationController.setFps(18);
        
            animationController.setSpriteSheet("FlakCannonLeft");
            pyc.addForce(DIRECTION.LEFT, 150);
            deleteFlak = new Point (x - 150, y + 25);
        }
         
    }
    
    @Override
    public void render(Graphics2D g2, Rectangle viewport){
        int translatedX =  (int)boundingBox.getX() - (int)viewport.getX();
        int translatedY =  (int)boundingBox.getY() - (int)viewport.getY();
//       
        Rectangle boundingBoxForRendering = new Rectangle(translatedX, translatedY, 100, 100);
        
        BufferedImage sprite = animationController.getFrame();
        g2.drawImage(sprite, translatedX, translatedY, 101, 111, null);
        animationController.update();
        
        if(Main.debug){
            g2.setColor(Color.red);
            g2.drawRect(boundingBoxForRendering.x, boundingBoxForRendering.y, boundingBoxForRendering.width, boundingBoxForRendering.height);
            g2.setColor(Color.yellow);
            g2.drawRect(translatedX, translatedY, boundingBox.width, boundingBox.height);
            
        }
    }
    
    @Override
    public void update() {
        super.update();
        //pyc.update(); 
        Point.Double p = pyc.getNextTranslation();
        
        //translate object
        if(p.x >0){
            for(int i=0; i<p.x; i++){
                translate(1, 0);
            }
        }
        else if(p.x < 0){
            for(int i=0; i<-p.x; i++){
                translate(-1, 0);
            }
        }
        if(p.y >0){
            for(int i=0; i<p.y; i++){
                translate(0, 1);
            }
        }
        else if(p.y < 0){
            for(int i=0; i<-p.y; i++){
                translate(0, -1);
            }
        }
       
        
        if(boundingBox.contains(deleteFlak))
            GameData.getInstance().removeGameObject(this);
    }
    
    public void translate(int dx, int dy){
        
        //the location to move to
        Point newLoc = new Point(boundingBox.x+dx, boundingBox.y + dy); 
        boundingBox.setLocation(newLoc);
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

    public String getObjectType(){
        return "Flak Cannon";
    }
}
