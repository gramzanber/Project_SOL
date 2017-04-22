package view.gameobjects;

import controller.AnimationController;
import controller.PhysicsController;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import model.GameData;
import model.ID;

/**
* Render a small alien object to the screen.
*
* @author  SATAS
* @version 1.0
* @since   2017-02-18 
*/
public class MoonLargeEnemy extends RenderableObject
{   
    //private Image enemyImage;

    private PhysicsController pyc = new PhysicsController(this);
    
    boolean directionLeft = true;
    
    private AnimationController animationController;
    
    
    /**
    * Constructor 
    * 
    * @param loc
    * @param width
    * @param height
    */
    public MoonLargeEnemy(Point loc, ID id)
    {
        //call superclass constructor
        super(loc);
        this.id = id;
        
        animationController = new AnimationController(AnimationController.Mode.AUTO, "alien_1-walkleft");
        animationController.setFps(48);
        
        //update bounding box for the object
        super.boundingBox = new Rectangle(loc.x, loc.y, 100, 200);
        
        //enemyImage = null;
        /*try {
            enemyImage = ImageIO.read(getClass().getResource("/Images/alien_5-walk0.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open small enemy.png");
            System.exit(-1);
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
            boundingBox.setLocation(newLoc);
        }
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void update()
    {
        super.update();
        
        if(!pyc.canMove(PhysicsController.DIRECTION.LEFT))
            directionLeft = false;
        if(!pyc.canMove(PhysicsController.DIRECTION.RIGHT))
            directionLeft = true;
        
        if(directionLeft)pyc.addForce(PhysicsController.DIRECTION.LEFT, 2);
        else pyc.addForce(PhysicsController.DIRECTION.RIGHT, 2);
        
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
        super.collide();
        /*if(GameData.getInstance().getHero().getBoundingBox().intersects(boundingBox))
        {
            System.out.println("Killed Enemy! By walking into it.");
            
            this.clear();
            GameData.getInstance().gameObjects.remove(this);
            GameData.getInstance().getHero().setShield(-10);
        }*/
        for(int i = 0; i < GameData.getInstance().gameObjects.size(); i++)
        if((GameData.getInstance().gameObjects.get(i) instanceof PrimaryWeapon || GameData.getInstance().gameObjects.get(i) instanceof Weapon) &&
                GameData.getInstance().gameObjects.get(i).boundingBox.intersects(boundingBox))
        {
            
            this.clear();
            GameData.getInstance().gameObjects.remove(this);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e)
    {
        boundingBox.x = boundingBox.x + 5;
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void render(Graphics2D g2, Rectangle viewport)
    {
        //if(viewport.contains(boundingBox.getLocation()))
        if(viewport.intersects(this.getBoundingBox()))
        {
            // draw in  relation to the viewport
            int translatedX =  (int)boundingBox.getX() - (int)viewport.getX();
            int translatedY =  (int)boundingBox.getY() - (int)viewport.getY();
            int border = 2;
            
            if (directionLeft) {
                animationController.setSpriteSheet("alien_1-walkleft");
                BufferedImage sprite = animationController.getFrame();
                g2.drawImage(sprite, translatedX, translatedY, (int) boundingBox.getWidth() - border * 2, (int) boundingBox.getHeight() - border * 2, null);
                animationController.update();
            }
            else {
                animationController.setSpriteSheet("alien_1-walkright");
                BufferedImage sprite = animationController.getFrame();
                g2.drawImage(sprite, translatedX, translatedY, (int) boundingBox.getWidth() - border * 2, (int) boundingBox.getHeight() - border * 2, null);
                animationController.update();
            }
        }
        
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
}
