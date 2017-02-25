package view;

import controller.Main;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
* An abstract class extended by all friendly and non friendly characters.
* This class adds movement and collision detection to a renderable object
*
* @author  SATAS
* @version 1.0
* @since   2017-02-18 
*/
public abstract class Actor extends RenderableObject {

    //directions that this object can be moved
    public enum DIRECTION {
        LEFT, RIGHT, UP, DOWN
    }
    
    private int upForce; //the amount of upwards force on the object
    private int downForce; //the amount of downwards force on the object
    private int leftForce; //the amount of left force on the object
    private int rightForce; //the amount of right force on the object

    //which keys are currently being pressed, when pressed we add movement
    private boolean leftKeyDown;
    private boolean rightKeyDown;
    private boolean spaceKeyDown;
    
    /**
    * Constructor 
    * 
    * @param loc
    */
    public Actor(Point loc) {
        //call superclass constructor
        super(loc);
        
        //initialize force variables
        upForce = 0;
        downForce = 0;
        leftForce = 0;
        rightForce = 0;

        //no keys start out pressed pressed
        leftKeyDown = false;
        rightKeyDown = false;
        spaceKeyDown = false;
    }
    
    
    /**
    * This method checks if the object can move in any given direction 
    * 
    * @param direction The direction to check
    */
    private boolean canMove(DIRECTION direction){
        
        //basically I create a new boundingBox object and move it to the new location
        //then I check collision on the test bounding box to see if the movement would have
        //been possible. if no collision then we can return true
        Rectangle testRect = (Rectangle)boundingBox.clone();
        
        if(null != direction) //because the collision detection will also check edges I am cheating
        //by making the testRect a pixal smaller on the edges that im not testing.
        //this way you can still move up even if youre standing next to a wall.
        switch (direction) {
            case UP:
                testRect.setSize(boundingBox.width-2, boundingBox.height);
                testRect.setLocation(new Point(boundingBox.x+1, boundingBox.y - 1));
                break;
            case DOWN:
                testRect.setSize(boundingBox.width-2, boundingBox.height);
                testRect.setLocation(new Point(boundingBox.x+1, boundingBox.y + 1));
                break;
            case LEFT:
                testRect.setSize(boundingBox.width, boundingBox.height-2);
                testRect.setLocation(new Point(boundingBox.x - 1, boundingBox.y+1));
                break;
            case RIGHT:
                testRect.setSize(boundingBox.width, boundingBox.height-2);
                testRect.setLocation(new Point(boundingBox.x + 1, boundingBox.y+1));
                break;
        }
        
        //check collision and return
        return !Main.gameData.checkCollision(testRect, this);
    }
    
    /**
    * This method clears all force and resets the object to defaults.
    * Its useful when the same objects are reused in different levels such as the hero.
    */
    public void clear(){
        //clear force on object
        upForce = 0;
        downForce = 0;
        leftForce = 0;
        rightForce = 0;
    }
    
    
    /**
    * The update method gets called by the Animator class once per frame.
    * This is where we take the force that is applied to the object and actually 
    * make make the movements
    */
    @Override
    public void update(){

        //Add gravity effect by applying downward force at each update
        if(canMove(DIRECTION.DOWN)){
            downForce += 5; //gravity
        }
        
        //if the left key is down add some left force
        if(leftKeyDown){
            if(canMove(DIRECTION.LEFT)){
                //if you're standing on the ground you can run faster and accelerate
                if(!canMove(DIRECTION.DOWN)){
                    //acceleration is the sqrt of the current force
                    leftForce += 3+Math.sqrt(leftForce);
                }
                else{
                    //in air you move slower and dont accelerate
                    leftForce += 2;
                }
            }
        }
        
        //if the right key is down add some right force
        if(rightKeyDown){
            
            if(canMove(DIRECTION.RIGHT)){
                //if you're standing on the ground you can run faster and accelerate
                if(!canMove(DIRECTION.DOWN)){
                    //acceleration is the sqrt of the current force
                    rightForce += 3+Math.sqrt(rightForce);
                }
                else {
                    //in air you move slower and dont accelerate
                    rightForce += 2;
                }
            }
        }
        
        //if the space key is down add som upward force to jump
        if(spaceKeyDown){
            //can only jump if you are standing on the ground, can't fly lol
            if(!canMove(DIRECTION.DOWN) && canMove(DIRECTION.UP)){
                upForce += 300; //jump height
            }
        }

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
        upForce = Math.min(upForce, 300);
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
            for(int i=0; i<step; i++){
                translate(0, -1);
            }
            upForce -= step;
        }
        
        //move down
        if(downForce > 0){
            step = (int)(Math.sqrt(downForce));
            for(int i=0; i<step; i++){
                translate(0, 1);
            }
            downForce -= step;
        }
        
        //move left
        if(leftForce > 0){
            step = (int)(Math.sqrt(leftForce));
            for(int i=0; i<step; i++){
                translate(-1, 0);
            }
            leftForce -= step;
        }
        
        //move right
        if(rightForce > 0){
            step = (int)(Math.sqrt(rightForce));
            for(int i=0; i<step; i++){
                translate(1, 0);
            }
            rightForce -= step;
        }
      
        //one last thing, since this is a side scroller we need to move the
        //viewport when we get close to the edge of the screen
        if(boundingBox.getX() + 300 >= Main.gameData.viewport.getX()+(int) Main.gameData.viewport.getWidth()){
            Main.gameData.viewport.setLocation((int)boundingBox.getX() + 300 - (int)Main.gameData.viewport.getWidth(), (int)Main.gameData.viewport.getY());
        }
        else if(boundingBox.getX() - 100 <= Main.gameData.viewport.getX()){
            Main.gameData.viewport.setLocation((int)boundingBox.getX() - 100 , (int)Main.gameData.viewport.getY());
        }
    }
    
    /**
    * This method will move the object
    * 
    * @param dx the change in the X location of the object
    * @param dy the change in the Y location of the object
    */
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
        if(!Main.gameData.checkCollision(testRect, this)){
            boundingBox.setLocation(newLoc);
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
        if(boundingBox.contains(e.getPoint())){
        }
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void mouseReleased(MouseEvent e) {
        if(boundingBox.contains(e.getPoint())){
        }
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
        if(boundingBox.contains(e.getPoint())){
        }
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void mouseDragged(MouseEvent e) {
        if(boundingBox.contains(e.getPoint())){
        }
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void mouseMoved(MouseEvent e) {
        if(boundingBox.contains(e.getPoint())){
        }
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void keyPressed(KeyEvent e) {
        
        //if movemnet keys are pressed update instance vars
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                rightKeyDown = true;
                break;
            case KeyEvent.VK_LEFT:
                leftKeyDown = true;
                break;
            case KeyEvent.VK_SPACE:
                spaceKeyDown = true;
                break;
        }
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void keyReleased(KeyEvent e) {
        //if movemnet keys are released update instance vars
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                rightKeyDown = false;
                break;
            case KeyEvent.VK_LEFT:
                leftKeyDown = false;
                break;
            case KeyEvent.VK_SPACE:
                spaceKeyDown = false;
                break;
        }
    }
}
