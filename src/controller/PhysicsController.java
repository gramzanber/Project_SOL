/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Point;
import java.awt.Rectangle;
import view.gameobjects.Actor;
import view.gameobjects.RenderableObject;

/**
 *
 * @author sean
 */
public class PhysicsController {
    
    
    
    //directions that this object can be moved
    public enum DIRECTION {
        LEFT, RIGHT, UP, DOWN
    }
    
    private int upForce; //the amount of upwards force on the object
    private int downForce; //the amount of downwards force on the object
    private int leftForce; //the amount of left force on the object
    private int rightForce; //the amount of right force on the object
    
    private RenderableObject o;
    
    
    public PhysicsController(RenderableObject o){
        
        this.o = o; 
        
        //initialize force variables
        upForce = 0;
        downForce = 0;
        leftForce = 0;
        rightForce = 0;
        
    }
    
    public void update(){
        
        //Add gravity effect by applying downward force at each update
        if(canMove(DIRECTION.DOWN)){
            downForce += 5; //gravity
        }
        
    }
    
    public void addForce(DIRECTION d, int amount){
        
        switch(d){
            case UP:
                upForce += amount;
                break;
            case DOWN:
                downForce += amount;
                break;
            case LEFT:
                leftForce += amount;
                break;
            case RIGHT:
                rightForce += amount;
                break;
        }
        
        
    }
    
    public Point getNextTranslation(){
        Point p = new Point(0,0);
        
        
        
        
        
        //just like in the real world two opposite forces cancel out eachother
        //here I resolve all of that.
        //exampe if you move left and right at the same time you shouldnt move
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
        
        //now I need to set some maximum speeds and make sure force isnt out of controll
        upForce = Math.min(upForce, 600);
        downForce = Math.min(downForce, 200);
        leftForce = Math.min(leftForce, 50);
        rightForce = Math.min(rightForce, 50);
        
        
        //now to actually apply the force
        //the step is how many pixals to move at one time and increases as the force grows
        //this means that if you apply a ton of force to the object it will move faster
        //a little force will move slower
        int step; //step
        
        //move up
        if(upForce > 0){
            step = (int)(Math.sqrt(upForce));
            p.setLocation(new Point(p.x, -step));
            upForce -= step;
        }
        
        //move down
        else if(downForce > 0){
            step = (int)(Math.sqrt(downForce));
            p.setLocation(new Point(p.x, step));
            downForce -= step;
        }
        
        //move left
        if(leftForce > 0){
            step = (int)(Math.sqrt(leftForce));
            p.setLocation(new Point(-step, p.y));
            leftForce -= step;
        }
        
        //move right
        if(rightForce > 0){
            step = (int)(Math.sqrt(rightForce));
            p.setLocation(new Point(step, p.y));
            rightForce -= step;
        }
        
        return p;
    }
    
    
    public void clear(){

        //clear force on object
        upForce = 0;
        downForce = 0;
        leftForce = 0;
        rightForce = 0;
    }
    
    /**
    * This method checks if the object can move in any given direction 
    * 
    * @param direction The direction to check
    */
    public boolean canMove(PhysicsController.DIRECTION direction){
        
        //basically I create a new boundingBox object and move it to the new location
        //then I check collision on the test bounding box to see if the movement would have
        //been possible. if no collision then we can return true
        Rectangle testRect = (Rectangle)o.getBoundingBox().clone();
        
        if(null != direction) //because the collision detection will also check edges I am cheating
        //by making the testRect a pixal smaller on the edges that im not testing.
        //this way you can still move up even if youre standing next to a wall.
        switch (direction) {
            case UP:
                testRect.setSize(o.getBoundingBox().width-2, o.getBoundingBox().height);
                testRect.setLocation(new Point(o.getBoundingBox().x+1, o.getBoundingBox().y - 1));
                break;
            case DOWN:
                testRect.setSize(o.getBoundingBox().width-2, o.getBoundingBox().height);
                testRect.setLocation(new Point(o.getBoundingBox().x+1, o.getBoundingBox().y + 1));
                break;
            case LEFT:
                testRect.setSize(o.getBoundingBox().width, o.getBoundingBox().height-2);
                testRect.setLocation(new Point(o.getBoundingBox().x - 1, o.getBoundingBox().y+1));
                break;
            case RIGHT:
                testRect.setSize(o.getBoundingBox().width, o.getBoundingBox().height-2);
                testRect.setLocation(new Point(o.getBoundingBox().x + 1, o.getBoundingBox().y+1));
                break;
        }
        
        //check collision and return
        return !Main.gameData.checkCollision(testRect, o);
    }

    public int getUpForce() {
        return upForce;
    }

    public int getDownForce() {
        return downForce;
    }

    public int getLeftForce() {
        return leftForce;
    }

    public int getRightForce() {
        return rightForce;
    }
    
    
    
    
}
