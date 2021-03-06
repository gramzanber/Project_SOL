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
public class SeekerMissile extends Weapon {

    private PhysicsController pyc;
    private AnimationController animationController;
    private int mouseX = 0;//mouseclick location x
    private int mouseY = 0;//mouseclick location y
    
    public SeekerMissile(float sx, float sy, int mx, int my, RenderableObject owner) {
        super(sx, sy, owner);
        //All this mess and experimentation
        this.mouseX = mx + GameData.getInstance().viewport.x; //get mouselocation passed from Hero when mouse clicked
        this.mouseY = my + GameData.getInstance().viewport.y;
        
        //get delta x and y from the mouse loc - the spawn loc of this missile
        //so I know how much force to add and in which direction
        int travelX = mx - (int)sx;
        int travelY = my - (int)sy;
     
        //TODO: this should be passed in via parameters im just overiding it here for quick debuging
        int x = GameData.getInstance().getHero().getBoundingBox().x;
        int y = GameData.getInstance().getHero().getBoundingBox().y;
        super.boundingBox = new Rectangle(x, y, 140, 50);
        
        pyc = new PhysicsController(this);
        
        animationController = new AnimationController(AnimationController.Mode.AUTO, "seeker");
        animationController.setFps(8);
        //animationController.setSpriteSheet("seeker");
        if(travelX > 0){
            animationController.setSpriteSheet("seeker");
        }
        else
            animationController.setSpriteSheet("seekerleft");
            
        if(travelY > 0)
            pyc.addForce(DIRECTION.DOWN, travelY * 2);
        else 
            pyc.addForce(DIRECTION.UP, -travelY - 50);
        if(travelX > 0)
            pyc.addForce(DIRECTION.RIGHT, travelX);
        else
            pyc.addForce(DIRECTION.LEFT, -travelX);
        
         
    }
    
    @Override
    public void render(Graphics2D g2, Rectangle viewport){
        int translatedX =  (int)boundingBox.getX() - (int)viewport.getX();
        int translatedY =  (int)boundingBox.getY() - (int)viewport.getY();
        
        Rectangle boundingBoxForRendering = new Rectangle(translatedX, translatedY, 130, 40);
        
        BufferedImage sprite = animationController.getFrame();
        g2.drawImage(sprite, translatedX, translatedY, 120, 30, null);
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
        
        Point mouseloc = new Point(mouseX, mouseY);
        
        if(boundingBox.contains(mouseloc)){
            animationController.explosionEffect(new Point((int)getBoundingBox().getX(), (int)getBoundingBox().getY()));
            GameData.getInstance().removeGameObject(this);
        }
    }
    
    public void translate(int dx, int dy){
//        
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
        return "Seeker Missile";
    }
}
