package view.gameobjects;

import view.swingcomponents.MenuWindow;
import controller.GameController;
import controller.PhysicsController;
import controller.PhysicsController.DIRECTION;
import controller.SoundController;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import model.GameData;
import view.swingcomponents.MainWindow;

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
//    public enum DIRECTION {
//        LEFT, RIGHT, UP, DOWN
//    }
    
    private PhysicsController pyc;
    
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
        
        pyc = new PhysicsController(this);
        

        //no keys start out pressed pressed
        leftKeyDown = false;
        rightKeyDown = false;
        spaceKeyDown = false;
        
    }
    
    
    /**
    * This method clears all force and resets the object to defaults.
    * Its useful when the same objects are reused in different levels such as the hero.
    */
    @Override
    public void clear(){
        super.clear();
        
        pyc.clear();
        
    }
    
    
    /**
    * The update method gets called by the Animator class once per frame.
    * This is where we take the force that is applied to the object and actually 
    * make make the movements
    */
    @Override
    public void update(){

        if(leftKeyDown){
                if(pyc.canMove(DIRECTION.LEFT)){
                    pyc.getLeftMovementForce().setForcePerSecond(.00000000000005);
                }
                else{
                    pyc.getLeftMovementForce().setForcePerSecond(.00000000000001);
                }
                pyc.getLeftMovementForce().setActive(true);
        }
        else{
             pyc.getLeftMovementForce().setActive(false);
             pyc.setForce(DIRECTION.LEFT, 0);
        }
        
        if(rightKeyDown){
                if(pyc.canMove(DIRECTION.RIGHT)){
                    pyc.getRightMovementForce().setForcePerSecond(.00000000000005);
                }
                else{
                    pyc.getRightMovementForce().setForcePerSecond(.00000000000001);
                }
                pyc.getRightMovementForce().setActive(true);
        }
        else{
             pyc.getRightMovementForce().setActive(false);
             pyc.setForce(DIRECTION.RIGHT, 0);
        }
        
        
//        //if the left key is down add some left force
//        if(leftKeyDown){
//            if(pyc.canMove(DIRECTION.LEFT)){
//                //if you're standing on the ground you can run faster and accelerate
//                if(!pyc.canMove(DIRECTION.DOWN)){
//                    //acceleration is the sqrt of the current force
//                    //leftForce += 3+Math.sqrt(leftForce);
//                    pyc.addForce(PhysicsController.DIRECTION.LEFT, 3);
//                    SoundController.getInstance().playerMove();
//                }
//                else{
//                    //in air you move slower and dont accelerate
//                    pyc.addForce(PhysicsController.DIRECTION.LEFT, 5);
//                }
//            }
//        }
//        
//        //if the right key is down add some right force
//        if(rightKeyDown){
//            
//            if(pyc.canMove(DIRECTION.RIGHT)){
//                //if you're standing on the ground you can run faster and accelerate
//                if(!pyc.canMove(DIRECTION.DOWN)){
//                    //acceleration is the sqrt of the current force
//                    //rightForce += 3+Math.sqrt(rightForce);
//                    pyc.addForce(PhysicsController.DIRECTION.RIGHT, 3);
//                    SoundController.getInstance().playerMove();
//                }
//                else {
//                    //in air you move slower and dont accelerate
//                    //rightForce += 5;
//                    pyc.addForce(PhysicsController.DIRECTION.RIGHT, 5);
//                }
//            }
//        }
//        
//        //if the space key is down add some upward force to jump
//        if(spaceKeyDown){
//            //can only jump if you are standing on the ground, can't fly lol
//            if(!pyc.canMove(DIRECTION.DOWN) && pyc.canMove(DIRECTION.UP)){
//                //upForce += 450; //jump height
//                pyc.addForce(PhysicsController.DIRECTION.UP, 450);
//                spaceKeyDown = false; //only jump once per key down event
//            }
//        }

        //update physics controller
        pyc.update();
       
        //get translation from physics controller
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
        if(!GameData.getInstance().checkCollision(testRect, this)){
            if(dx == -1){
                Hero.movingLeft = true;
            }
            else if(dx == 1){
                Hero.movingRight = true;
            }
            if(dy == -1){
                Hero.movingUp = true;
            }
            else if(dy == 1){
                Hero.movingDown = true;
            }
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
            case KeyEvent.VK_D:
                rightKeyDown = true;
                break;                
            case KeyEvent.VK_LEFT:
                leftKeyDown = true;
                break;
            case KeyEvent.VK_A:
                leftKeyDown = true;
                break;                
            case KeyEvent.VK_SPACE:
                spaceKeyDown = true;
                if(!pyc.canMove(DIRECTION.DOWN)){
                    pyc.setForce(DIRECTION.UP, 1000);
                }
                
                break;
            case KeyEvent.VK_C:
                MenuWindow dialogMenu = new MenuWindow(MainWindow.getInstance(), false);
                int parentX = MainWindow.getInstance().getX();
                int parentY = MainWindow.getInstance().getY();
                int parentWidth = MainWindow.getInstance().getWidth();
                int parentHeight = MainWindow.getInstance().getHeight();
                dialogMenu.setLocation(parentX + parentWidth/2 - dialogMenu.getWidth()/2, parentY + parentHeight/2 - dialogMenu.getHeight()/2);
                dialogMenu.getContentPane().setBackground(Color.BLACK);
                dialogMenu.levellbl.setText(GameController.getScreen());
                dialogMenu.primarylbl.setText(Integer.toString(PrimaryWeapon.getWeaponType()));
                dialogMenu.setResizable(true);
                dialogMenu.setAlwaysOnTop(true);
                dialogMenu.setVisible(true);
                
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
            case KeyEvent.VK_D:
                rightKeyDown = false;
                break;                
            case KeyEvent.VK_LEFT:
                leftKeyDown = false;
                break;
            case KeyEvent.VK_A:
                leftKeyDown = false;
                break;                
            case KeyEvent.VK_SPACE:
                spaceKeyDown = false;
                break;
        }
    }
}
