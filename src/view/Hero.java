package view;

import controller.Main;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
* Render a hero object to the screen.
*
* @author  SATAS
* @version 1.0
* @since   2017-02-18 
*/
public class Hero extends Actor {
    
    private Image heroImage;
    private Rectangle viewportMain;
    private float health = 400;
    private float displayHealth =100;
    private float greenValue = 255;
    private int healthPacks =0;
    
    /**
    * Constructor 
    * 
    * @param loc
    * @param width
    * @param height
    */
    public Hero(Point loc) {
        //call superclass constructor
        super(loc);
        
        //update bounding box for the object
        super.boundingBox = new Rectangle(loc.x, loc.y, 32, 83);
        
        heroImage = null;
        try {
            heroImage = ImageIO.read(getClass().getResource("/Images/robot_9_right-psd_32x83.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open hero.png");
            System.exit(-1);
        }
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void update(){
      super.update();
      healthBound();     
      
    }
    
    // Tyrel wrote this method
    @Override
    public void mouseClicked(MouseEvent e)
    {
        int translatedX =  ((int)boundingBox.getX() - (int)viewportMain.getX())+(int)boundingBox.getWidth();
        int translatedY =  ((int)boundingBox.getY() - (int)viewportMain.getY())+(int)boundingBox.getHeight()/2;
        if(SwingUtilities.isRightMouseButton(e))
        {
            System.out.println("Secondary Weapon!");
        }
        else if(SwingUtilities.isLeftMouseButton(e))
        {
            Missile m = new Missile(translatedX, translatedY);
            Main.soundController.primaryWeaponFire();
            synchronized (Main.gameData.gameObjects) { Main.gameData.addGameObject(m); }
        }
        else { System.out.printf("Mouse click error, Package: Controller; Class: view.Hero.java"); }
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void render(Graphics2D g2,Rectangle viewport)
    {
        if(viewport.contains(boundingBox.getLocation()))
        {
            this.viewportMain = viewport;
            //draw in relation to the viewport
            int translatedX =  (int)boundingBox.getX() - (int)viewport.getX();
            int translatedY =  (int)boundingBox.getY() - (int)viewport.getY();

            int border = 2;
            g2.drawImage(heroImage, translatedX, translatedY, (int)boundingBox.getWidth()-border*2, (int)boundingBox.getHeight()-border*2, null); 
        }
        float tempHealth = displayHealth;
        if(tempHealth > 100) tempHealth =100;

        g2.setColor(Color.darkGray);
        g2.fillRect(2,5,(int)(100 * 2.5), 15);
        g2.setColor(new Color(150,(int)greenValue,0));
        g2.fillRect(2, 5, (int) (tempHealth * 2.5), 15);
        g2.setColor(Color.white);
        g2.drawRect(2,5,(int)(100 *2.5), 15);
        
        for(int i=0; i<healthPacks-1; i++){
         //g2.setColor(Color.red);
         g2.setColor(new Color(150,(int)greenValue,0)); //the rectangles below the health bar have same color as health bar
         g2.fillRect(1, 22, 10, 15);
         if(healthPacks > 1 && i>0){
             g2.fillRect((2*i)*7, 22, 10, 15);
         }
        }
    }

    private void healthBound() {
        //the health is depleated constatntly but just as a demo. will be changed when there are enemies in the game
        if(displayHealth > 100){
            displayHealth = 100;
        }
        if(displayHealth > 0){
            displayHealth -=1;
        }else if(displayHealth <=0 && health>0){
            health -= 100;
            displayHealth = health;
        }
        System.out.println("Display::"+displayHealth+" :: Health::"+health);
        greenValue = displayHealth*3;
        if(health <=0){
            health =0;
        }
        if(greenValue > 255){
            greenValue = 255;
        }
        
        healthPacks = (int)health/100;
    }
    
    public float getHealth(){
        return displayHealth;
    }
    public void setHealth(float powerUp){
        this.displayHealth += powerUp;
        this.health += powerUp;
    }
}
