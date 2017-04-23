/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gameobjects;

import controller.AnimationController;
import controller.SoundController;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import model.GameData;
import model.ID;

/**
 *
 * @author Ayodeji
 */
public abstract class VenusEnemy extends RenderableObject{

    
    /**
    * Constructor 
    * 
    * @param loc
     * @param id
    */
    public VenusEnemy(Point loc, ID id) {
        //call superclass constructor
        super(loc);
        this.id = id;
        
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void update(){
        super.update();
        healthBound();     
        
        //check collisions
        ArrayList<RenderableObject> collisions = GameData.getInstance().getCollisions(this);
        for(int i=0; i< collisions.size(); i++){
            RenderableObject obj = collisions.get(i);
            if(obj instanceof Hero){
                ((Hero)obj).setShield(-1);
            }
            else if(obj instanceof PrimaryWeapon || obj instanceof Weapon){
                this.setShield(-10);
                //GameData.getInstance().removeGameObject(obj);
                System.out.println("hit!");
            }
            if(this.getShield()<10){
                SoundController.getInstance().largeEnemyDeath();
                AnimationController.explosionEffect(loc);
                this.clear();
                GameData.getInstance().removeGameObject(this);
            }
        }
    }
    
    /**
    * This method will move the object
    * 
    * @param dx the change in the X location of the object
    * @param dy the change in the Y location of the object
    */
    public void translate(int dx, int dy){
    }
        
    /**
    * {@inheritDoc}
    */
    @Override
    public void render(Graphics2D g2,Rectangle viewport)
    {
        
    }

    private void healthBound() {
        
    }
    
    public abstract float getShield();
    public void setShield(float powerUp){
        
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

    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
}
