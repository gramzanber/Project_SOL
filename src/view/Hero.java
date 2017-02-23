package view;

import controller.Main;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
* Render a block to the screen.
*
* @author  SATAS
* @version 1.0
* @since   2017-02-18 
*/
public class Hero extends RenderableObject {
    
    private int upForce; //the amount of upwards force on the object
    private int downForce; //the amount of downwards force on the object
    private int leftForce; //the amount of left force on the object
    private int rightForce; //the amount of right force on the object
    private int step; //how many pixals get moved each frame
    
    /**
    * Constructor 
    * 
    * @param loc
    */
    public Hero(Point loc, int width, int height) {
        //call superclass constructor
        super(loc);
        
        //initialize variables
        upForce = 0;
        downForce = 0;
        leftForce = 0;
        rightForce = 0;
        step = 5;
        
        //update bounding box for the object
        super.boundingBox = new Rectangle(loc.x, loc.y, width, height);
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void update(){
        
        //System.out.println("UPF: "+upForce+" DNF:"+downForce);
        
        //add gravity
        if(translate(0, 1, true)){
            downForce += 1; //gravity
        }
        
        //opposite forces cancel out eachother
        if(upForce > downForce){
            upForce -= downForce;
        }
        else if(downForce > upForce){
            downForce -= upForce;
        }
        if(leftForce > rightForce){
            leftForce -= rightForce;
        }
        else if(rightForce > leftForce){
            rightForce -= leftForce;
        }
        
        //need to set max values to limit speed 
        upForce = Math.min(upForce, 100);
        downForce = Math.min(downForce, 100);
        leftForce = Math.min(leftForce, 100);
        rightForce = Math.min(rightForce, 100);
        
        
        //apply forces
        if(upForce > 0){
            for(int i=0; i<step; i++){
                translate(0, -1);
            }
            upForce -= step;
        }
        if(downForce > 0){
            for(int i=0; i<step; i++){
                translate(0, 1);
            }
            downForce -= step;
        }
        if(leftForce > 0){
            for(int i=0; i<step; i++){
                translate(-1, 0);
            }
            leftForce -= step;
        }
        if(rightForce > 0){
            for(int i=0; i<step; i++){
                translate(1, 0);
            }
            rightForce -= step;
        }
      
        //move the viewport when we get close to the edge of the screen
        if(boundingBox.getX() + 50 >= Main.gameData.viewport.getX()+(int) Main.gameData.viewport.getWidth()){
            Main.gameData.viewport.setLocation((int)boundingBox.getX() + 150 - (int)Main.gameData.viewport.getWidth(), (int)Main.gameData.viewport.getY());
        }
        else if(boundingBox.getX() - 50 <= Main.gameData.viewport.getX()){
            Main.gameData.viewport.setLocation((int)boundingBox.getX() - 100 , (int)Main.gameData.viewport.getY());
        }
    }
    public boolean translate(int dx, int dy){
        return translate( dx, dy, false);
    }
    public boolean translate(int dx, int dy, boolean testOnly){
        Point newLoc = new Point(boundingBox.x+dx, boundingBox.y + dy);
        Point testLoc = new Point(boundingBox.x+dx, boundingBox.y + dy - 1);
        Rectangle testRect = new Rectangle(boundingBox.width, boundingBox.height);
        
        testRect.setLocation(testLoc);
        if(!Main.gameData.checkCollision(testRect, this)){
            if(!testOnly){
                boundingBox.setLocation(newLoc);
            }
            return true;
        }
        return false;
    }
    
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void render(Graphics2D g2,Rectangle viewport){
        if(viewport.contains(boundingBox.getLocation())){
            
            //draw in relation to the viewport
            int translatedX =  (int)boundingBox.getX() - (int)viewport.getX();
            int translatedY =  (int)boundingBox.getY() - (int)viewport.getY();

            int border = 2;
            g2.setColor(Color.GRAY);
            g2.fillRect(translatedX, translatedY, (int)boundingBox.getWidth(), (int)boundingBox.getHeight());
            g2.setColor(Color.YELLOW);
            g2.fillRect(translatedX+border, translatedY+border, (int)boundingBox.getWidth()-border*2, (int)boundingBox.getHeight()-border*2);
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
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            //can't move in air
            //if(!translate(0, 1, true)){
                rightForce += 100;
            //}
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            //can't move in air
            //if(!translate(0, 1, true)){
                leftForce += 100;
            //}
        }
        else if(e.getKeyCode() == KeyEvent.VK_SPACE){
            //can't jump if you're not standing on something solid
            if(!translate(0, 1, true)){
                upForce += 100; 
            }
            
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
