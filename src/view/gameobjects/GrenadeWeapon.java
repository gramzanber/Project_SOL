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
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import model.GameData;
import static view.gameobjects.GameFigure.STATE_ALIVE;

/**
 *
 * @author alebel
 */
public class GrenadeWeapon extends Weapon {

    private PhysicsController pyc;
    private AnimationController animationController;
    private boolean alive = false; 
    public GrenadeWeapon(float sx, float sy) {
        super(sx, sy);
        
        //TODO: this should be passed in via parameters im just overiding it here for quick debuging
        int x = GameData.getInstance().getHero().getBoundingBox().x;
        int y = GameData.getInstance().getHero().getBoundingBox().y;
        super.boundingBox = new Rectangle(x, y, 60, 30);
        
        pyc = new PhysicsController(this);
        
        //state = STATE_ALIVE;
        
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
        
        
        /*
        //one last thing, since this is a side scroller we need to move the
        //viewport when we get close to the edge of the screen
        if(boundingBox.getX() + 300 >= GameData.getInstance().viewport.getX()+(int) GameData.getInstance().viewport.getWidth()){
            GameData.getInstance().viewport.setLocation((int)boundingBox.getX() + 300 - (int)GameData.getInstance().viewport.getWidth(), (int)GameData.getInstance().viewport.getY());
        }
        else if(boundingBox.getX() - 100 <= GameData.getInstance().viewport.getX()){
            GameData.getInstance().viewport.setLocation((int)boundingBox.getX() - 100 , (int)GameData.getInstance().viewport.getY());
        }
        
        if(boundingBox.getY() + 200 >= GameData.getInstance().viewport.getY()+(int) GameData.getInstance().viewport.getHeight()){
            GameData.getInstance().viewport.setLocation((int)GameData.getInstance().viewport.getX(), (int)boundingBox.getY() + 200 - (int)GameData.getInstance().viewport.getHeight());
        }
        else if(boundingBox.getY() - 200 <= GameData.getInstance().viewport.getY()){
            GameData.getInstance().viewport.setLocation((int)GameData.getInstance().viewport.getX(), (int)boundingBox.getY() - 200);
        }*/
        
    
    }
    
    public void translate(int dx, int dy){
        
        //Make sure we can actually move by creating a testrect object and trying it first
        
        //the location to move to
        Point newLoc = new Point(boundingBox.x+dx, boundingBox.y + dy); 
        
        //the location for testing, subject 1 from y so we dont count ground
        Point testLoc = new Point(boundingBox.x+dx, boundingBox.y + dy - 1);
        
        //build testRect
        Rectangle testRect = new Rectangle(boundingBox.width, boundingBox.height);
        testRect.setLocation(testLoc);
        
        //if test rect was successful its safe to move the real object
        if(!GameData.getInstance().checkCollision(testRect, this)){
//            if(dx == -1){
//                this.movingLeft = true;
//            }
//            else if(dx == 1){
//                this.movingRight = true;
//            }
//            if(dy == -1){
//                this.movingUp = true;
//            }
//            else if(dy == 1){
//                this.movingDown = true;
//            }
            boundingBox.setLocation(newLoc);
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

    
    
    public String getObjectType(){
        return "Grenade Weapon";
    }
    
    public boolean isAlive(){
        return this.alive;
    }
    
    public void setAlive(boolean a){
        alive = a;
    }
    
    
}
