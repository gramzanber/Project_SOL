package view;

import controller.Main;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
* Render a block to the screen.
*
* @author  SATAS
* @version 1.0
* @since   2017-02-18 
*/
public class Hero extends RenderableObject {
    
    private int speed;
    private boolean jumping;
    private int jumpHeight;
    
    /**
    * Constructor 
    * 
    * @param loc
    */
    public Hero(Point loc, int width, int height) {
        //call superclass constructor
        super(loc);
        
        //initialize variables
        speed = 0;
        jumping = false;
        jumpHeight = 100;
        
        //update bounding box for the object
        super.boundingBox = new Rectangle(loc.x, loc.y, width, height);
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void update(){
        if(jumping){
            //need to translate in steps of 1 px to prevent overlapping
            if(jumpHeight >0){
                for(int i=0; i<2; i++){
                    translate(0, -1);
                }
                jumpHeight -= 2;
            }
            else{
                jumpHeight = 100;
                jumping = false;
            }
        }
        else{
            //gravity
            //need to translate in steps of 1 px to prevent overlapping
            for(int i=0; i<10; i++){
                translate(0, 1);
            }
        }
        
        //move the viewport when we get close to the edge of the screen
        if(boundingBox.getX() + 50 >= Main.gameData.viewport.getX()+(int) Main.gameData.viewport.getWidth()){
            Main.gameData.viewport.setLocation((int)boundingBox.getX() + 150 - (int)Main.gameData.viewport.getWidth(), (int)Main.gameData.viewport.getY());
        }
        else if(boundingBox.getX() - 50 <= Main.gameData.viewport.getX()){
            Main.gameData.viewport.setLocation((int)boundingBox.getX() - 100 , (int)Main.gameData.viewport.getY());
        }
        
        
    }
    
    public void translate(int dx, int dy){
        Point newLoc = new Point(boundingBox.x+dx, boundingBox.y + dy);
        Point testLoc = new Point(boundingBox.x+dx, boundingBox.y + dy - 1);
        Rectangle testRect = new Rectangle(boundingBox.width, boundingBox.height);
        
        testRect.setLocation(testLoc);
        if(!Main.gameData.checkCollision(testRect, this)){
            boundingBox.setLocation(newLoc);
        }
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
            //need to translate in steps of 1 px to prevent overlapping
            for(int i=0; i<50; i++){
                translate(1, 0);
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            //need to translate in steps of 1 px to prevent overlapping
            for(int i=0; i<50; i++){
                translate(-1, 0);
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_SPACE){
            //need to translate in steps of 1 px to prevent overlapping
            jumping = true;
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
