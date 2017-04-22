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
public class GrenadeWeapon extends Weapon {

    private PhysicsController pyc;
    private AnimationController animationController;
    public GrenadeWeapon(float sx, float sy, RenderableObject owner) {
        super(sx, sy, owner);
        
        //TODO: this should be passed in via parameters im just overiding it here for quick debuging
        int x = GameData.getInstance().getHero().getBoundingBox().x;
        int y = GameData.getInstance().getHero().getBoundingBox().y;
        super.boundingBox = new Rectangle(x, y, 60, 30);
        
        pyc = new PhysicsController(this);
        
        animationController = new AnimationController(AnimationController.Mode.AUTO, "grenade");
        animationController.setFps(32);
        animationController.setSpriteSheet("grenade");
        
        pyc.addForce(DIRECTION.UP, 100);
        if(Hero.facingRight){
            //pyc.atAngle(70, PhysicsController.DIRECTION.RIGHT, 25);
            pyc.addForce(DIRECTION.RIGHT, 300);
        }
        else if(!Hero.facingRight){
            //pyc.atAngle(70, PhysicsController.DIRECTION.LEFT, 25);
            pyc.addForce(DIRECTION.LEFT, 300);
        }
    }
    
    @Override
    public void render(Graphics2D g2, Rectangle viewport){
        int translatedX =  (int)boundingBox.getX() - (int)viewport.getX();
        int translatedY =  (int)boundingBox.getY() - (int)viewport.getY();
            
        Rectangle boundingBoxForRendering = new Rectangle(translatedX-166/3, translatedY, 166, 155);
        
        BufferedImage sprite = animationController.getFrame();
        g2.drawImage(sprite, translatedX, translatedY, 40, 40, null);
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
        pyc.update();
        
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
        
        //stop when hits the ground
        if(!pyc.canMove(DIRECTION.DOWN)){
            pyc.clear();
        }
    }
    
    public void translate(int dx, int dy){
        //Move bounding box to new location
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
        return "Grenade Weapon";
    } 
}
