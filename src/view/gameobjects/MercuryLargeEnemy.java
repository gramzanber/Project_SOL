package view.gameobjects;

import controller.AnimationController;
import controller.PhysicsController;
import controller.SoundController;
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
public class MercuryLargeEnemy extends RenderableObject
{   
    //private Image enemyImage;

    private PhysicsController pyc = new PhysicsController(this);
    
    boolean directionLeft = true;
    private boolean alive;
    
    private AnimationController animationController;
    
    
    /**
    * Constructor 
    * 
    * @param loc
    * @param width
    * @param height
    */
    public MercuryLargeEnemy(Point loc, ID id)
    {
        //call superclass constructor
        super(loc);
        this.id = id;
        alive = true;
        
        animationController = new AnimationController(AnimationController.Mode.AUTO, "mercury_large_enemy_attack_left");
        animationController.setFps(10);
        
        //update bounding box for the object
        super.boundingBox = new Rectangle(loc.x, loc.y, 360, 420);
        
        //enemyImage = null;
        /*try {
            enemyImage = ImageIO.read(getClass().getResource("/Images/alien_5-walk0.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open small enemy.png");
            System.exit(-1);
        }*/
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void update()
    {
        super.update();
        
        super.collide();
        
        for(int i = 0; i < GameData.getInstance().gameObjects.size(); i++)
        if((GameData.getInstance().gameObjects.get(i) instanceof PrimaryWeapon || GameData.getInstance().gameObjects.get(i) instanceof Weapon) &&
                GameData.getInstance().gameObjects.get(i).boundingBox.intersects(boundingBox))
        {
            System.out.println("hit");
            die();
            
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e)
    {
        //boundingBox.x = boundingBox.x + 5;
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
            int translatedY =  (int)boundingBox.getY() - (int)viewport.getY() + 10;
            int border = 2;
            
            animationController.setSpriteSheet("mercury_large_enemy_attack_left");
            BufferedImage sprite = animationController.getFrame();
            g2.drawImage(sprite, translatedX, translatedY, (int) boundingBox.getWidth() - border * 2, (int) boundingBox.getHeight() - border * 2, null);
            animationController.update(); 
        }
        
    }
    
    public void die(){
        alive = false;
        SoundController.getInstance().largeEnemyDeath();        
            AnimationController.mercuryLargeEnemyDeathEffect(new Point((int)getBoundingBox().getCenterX()-120, (int)getBoundingBox().getCenterY()-80));
            this.clear();
            GameData.getInstance().removeGameObject(this);                            

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
