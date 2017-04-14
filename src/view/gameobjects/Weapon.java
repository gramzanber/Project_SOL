/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gameobjects;

import controller.AnimationController;
import controller.PhysicsController;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import model.GameData;

/**
 *
 * @author alebel
 */
public abstract class Weapon extends RenderableObject{
    
    private PhysicsController pyc;
    private AnimationController animationController;
    private RenderableObject owner;
    

    public Weapon(float sx, float sy, RenderableObject owner) {
        super(new Point((int)sx, (int)sy));
        pyc = new PhysicsController(this);
        this.owner = owner;
        super.boundingBox = new Rectangle(loc.x, loc.y, 20, 10);
    }
    
    @Override
    public void update(){
        ArrayList<RenderableObject> collisions = GameData.getInstance().getCollisions(this);
        
        for(int i=0; i<collisions.size(); i++){
            RenderableObject obj = collisions.get(i);
            if(obj != owner){
               GameData.getInstance().removeGameObject(this);
               break;
            }
            //if(obj == instanceof EarthBoss)
        }
    }
    
    public PhysicsController getPyc(){
        return pyc;
    }
    
    public void setPyc(PhysicsController p){
        this.pyc = p;
    }
    
    public AnimationController getAnimationController(){
        return animationController;
    }
    
    public void setAnimationController(AnimationController a){
        this.animationController = a;
    }
    
}
    
