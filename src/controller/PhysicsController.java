/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Point;
import java.awt.Rectangle;
import model.GameData;
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
    
    public class Force {
        private DIRECTION d;
        private double forcePerNanoSecond;
        private boolean active;
        public Force(DIRECTION d, double forcePerNanoSecond, boolean active){
            this.d = d;
            this.forcePerNanoSecond = forcePerNanoSecond;
            this.active = active;
        }
        public void setForcePerSecond(double forcePerNanoSecond){
            this.forcePerNanoSecond = forcePerNanoSecond;
        }
        public double getForceAmount(double nanoSeconds){
            if(active){
                return  (nanoSeconds * forcePerNanoSecond);
            }
            else{
                return 0;
            }
        }
        public void setActive(boolean active){
            this.active = active;
        }
    }
    
    private Force gravityForce;
    private Force rightMovementForce;
    private Force leftMovementForce;
    private Force upMovementForce;
    
    private double upForceValue; //the amount of upwards force on the object
    private double downForceValue; //the amount of downwards force on the object
    private double leftForceValue; //the amount of left force on the object
    private double rightForceValue; //the amount of right force on the object
    
    private RenderableObject o;
    
    private long lastUpdateTime;
    
    
    public PhysicsController(RenderableObject o){
        
        this.o = o; 
        
        //initialize force variables
        gravityForce = new Force(DIRECTION.DOWN, 1, true);
        rightMovementForce = new Force(DIRECTION.RIGHT, 1, false);
        leftMovementForce = new Force(DIRECTION.LEFT, 1, false);
        upMovementForce = new Force(DIRECTION.UP, 1, false);
        
        
        upForceValue = 0;
        downForceValue = 0;
        leftForceValue = 0;
        rightForceValue = 0;
        
        lastUpdateTime = System.currentTimeMillis();
        
    }
    
    public void update(){
        double timeSinceLastUpdate = ((System.currentTimeMillis() - lastUpdateTime));
        
        lastUpdateTime = System.currentTimeMillis();
        
        //Add gravity effect by applying downward force at each update
        if(canMove(DIRECTION.DOWN)){
            downForceValue += gravityForce.getForceAmount(timeSinceLastUpdate); //gravity
        }
        rightForceValue += rightMovementForce.getForceAmount(timeSinceLastUpdate);
        leftForceValue += leftMovementForce.getForceAmount(timeSinceLastUpdate);
        
        
        
    }
    
    public void addForce(DIRECTION d, int amount){
        
        switch(d){
            case UP:
                upForceValue += amount;
                break;
            case DOWN:
                downForceValue += amount;
                break;
            case LEFT:
                leftForceValue += amount;
                break;
            case RIGHT:
                rightForceValue += amount;
                break;
        }
        
        
    }
    public void setForce(DIRECTION d, int amount){
        
        switch(d){
            case UP:
                upForceValue = amount;
                break;
            case DOWN:
                downForceValue = amount;
                break;
            case LEFT:
                leftForceValue = amount;
                break;
            case RIGHT:
                rightForceValue = amount;
                break;
        }
        
        
    }
    
    public Point.Double getNextTranslation(){
        Point.Double p = new Point.Double(0,0);
        
        
        
        
        
        //just like in the real world two opposite forces cancel out eachother
        //here I resolve all of that.
        //exampe if you move left and right at the same time you shouldnt move
        if(upForceValue > downForceValue){
            upForceValue -= downForceValue;
        }
        else if(downForceValue > upForceValue){
            downForceValue -= upForceValue;
        }
        if(leftForceValue > rightForceValue){
            leftForceValue -= rightForceValue;
        }
        else if(rightForceValue > leftForceValue){
            rightForceValue -= leftForceValue;
        }
        
        //if standing on the ground then down force is 0
        if(!canMove(DIRECTION.DOWN)){
            downForceValue = 0;
        }
        
        
        
        //now I need to set some maximum speeds and make sure force isnt out of controll
        //upForceValue = Math.min(upForceValue, 1000);
        //downForceValue = Math.min(downForceValue, 1000);
        //leftForceValue = Math.min(leftForceValue, 50);
        //rightForceValue = Math.min(rightForceValue, 50);
        
        
        //now to actually apply the force
        //the step is how many pixals to move at one time and increases as the force grows
        //this means that if you apply a ton of force to the object it will move faster
        //a little force will move slower
        double step; //step
        
        //move up
        if(upForceValue > 0){
            step = (Math.sqrt(upForceValue));
            p.setLocation(new Point.Double(p.x, -step));
            upForceValue -= step;
        }
        
        //move down
        else if(downForceValue > 0){
            step = (Math.sqrt(downForceValue));
            p.setLocation(new Point.Double(p.x, step));
            downForceValue -= step;
        }
        
        //move left
        if(leftForceValue > 0){
            step = (Math.sqrt(leftForceValue));
            p.setLocation(new Point.Double(-step, p.y));
            leftForceValue -= step;
        }
        
        //move right
        if(rightForceValue > 0){
            step = (Math.sqrt(rightForceValue));
            p.setLocation(new Point.Double(step, p.y));
            rightForceValue -= step;
        }
        
        return p;
    }
    
    
    public void clear(){

        //clear force on object
        upForceValue = 0;
        downForceValue = 0;
        leftForceValue = 0;
        rightForceValue = 0;
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
        return !GameData.getInstance().checkCollision(testRect, o);
    }
    
    //Attempting to create a method for objects to travel in an arc
    //angleRad will be the angle of arc
    //Direction is left or right
    //amount is how much force behind the object
    public void atAngle(double angleRad, DIRECTION horizontal, double amount){
        //Need angle in radians
        angleRad = Math.toRadians(angleRad);
        
        //If we have the hypotenuse (amount) and angle, we can get Xvalue
        //by amount * sin(angle) and yval by using cos
        double x = amount * Math.cos(angleRad);
        double y = amount * Math.sin(angleRad);
        
        //add horizontal force 
        addForce(DIRECTION.UP, (int)amount);
        switch(horizontal) {
            case LEFT:
                addForce(DIRECTION.LEFT, (int)x);
                break;
            case RIGHT:
                addForce(DIRECTION.RIGHT, (int)-x);
                break;
            default:
                break;
        }
        
    }
    
    

    public Force getRightMovementForce() {
        return rightMovementForce;
    }

    public void setRightMovementForce(Force rightMovementForce) {
        this.rightMovementForce = rightMovementForce;
    }

    public Force getLeftMovementForce() {
        return leftMovementForce;
    }

    public void setLeftMovementForce(Force leftMovementForce) {
        this.leftMovementForce = leftMovementForce;
    }

    public Force getUpMovementForce() {
        return upMovementForce;
    }

    public void setUpMovementForce(Force upMovementForce) {
        this.upMovementForce = upMovementForce;
    }

    
    
    public double getUpForceValue() {
        return upForceValue;
    }

    public double getDownForceValue() {
        return downForceValue;
    }

    public double getLeftForceValue() {
        return leftForceValue;
    }

    public double getRightForceValue() {
        return rightForceValue;
    }
    
    
    
    
}
