package view.gameobjects;

import controller.Main;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
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
    
    private Image heroRightImage;
    private Image heroLeftImage;
    private Rectangle viewportMain;
    private float health = 0;
    private float displayHealth =0;
    private float blueValue = 255;
    private int healthPacks =0;
    private static BufferedImage heroRunRightSpriteSheet = null;
    private static BufferedImage heroRunLeftSpriteSheet = null;
    private int rowStep;
    private int columnStep;
    static boolean movingLeft = false;
    static boolean movingRight = false;
    static boolean movingUp = false;
    static boolean movingDown = false;
    static boolean facingRight;
    private int frames = 2;
    
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
        facingRight = true;
        
        
        
        //update bounding box for the object
        super.boundingBox = new Rectangle(loc.x, loc.y, 60, 155);
        
        heroRightImage = null;
        try {
            heroRightImage = ImageIO.read(getClass().getResource("/Images/hero_stand_right.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open hero.png");
            System.exit(-1);
        }
        
        if(heroRunRightSpriteSheet == null){
            try {
                heroRunRightSpriteSheet = ImageIO.read(getClass().getResource("/Images/hero_run_right.png"));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error: Cannot open tile sprite sheet");
                System.exit(-1);
            }
        }
        
        heroLeftImage = null;
        try {
            heroLeftImage = ImageIO.read(getClass().getResource("/Images/hero_stand_left.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open hero.png");
            System.exit(-1);
        }
        
        if(heroRunLeftSpriteSheet == null){
            try {
                heroRunLeftSpriteSheet = ImageIO.read(getClass().getResource("/Images/hero_run_left.png"));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error: Cannot open tile sprite sheet");
                System.exit(-1);
            }
        }        
        
        rowStep = 0;
        columnStep = -1;
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
            PrimaryWeapon m = new PrimaryWeapon(translatedX, translatedY);
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
            
            Rectangle boundingBoxForRendering = new Rectangle(translatedX-166/3, translatedY, 166, 155);
            
            //System.out.println("tx::"+translatedX+" :: ty::"+translatedY);            

            int border = 0;
            
            if(movingRight){
                frames++;
                if(rowStep == 3 && columnStep == 5 && frames % 3 == 0){
                    rowStep = 0;
                    columnStep = 0;
                }
                else if(columnStep == 5 && frames % 3 == 0){
                    columnStep = 0;
                    rowStep++;
                }
                else if(frames % 3 == 0){
                    columnStep++;
                }
                facingRight = true;
                Image sprite = heroRunRightSpriteSheet.getSubimage(166*columnStep, 155*rowStep, 166, 155); 
                g2.drawImage(sprite, boundingBoxForRendering.x, boundingBoxForRendering.y, (int)boundingBoxForRendering.getWidth()-border*2, (int)boundingBoxForRendering.getHeight()-border*2, null); 
                movingRight = false;
                //System.out.println("run horiz::"+columnStep+" :: vert::"+rowStep);  
            }
            else if(movingLeft){
                frames++;
                if(rowStep == 3 && columnStep == 5 && frames % 3 == 0){
                    rowStep = 0;
                    columnStep = 0;
                }
                else if(columnStep == 5 && frames % 3 == 0){
                    columnStep = 0;
                    rowStep++;
                }
                else if(frames % 3 == 0){
                    columnStep++;
                }
                facingRight = false;
                Image sprite = heroRunLeftSpriteSheet.getSubimage(166*columnStep, 155*rowStep, 166, 155); 
                g2.drawImage(sprite, boundingBoxForRendering.x, boundingBoxForRendering.y, (int)boundingBoxForRendering.getWidth()-border*2, (int)boundingBoxForRendering.getHeight()-border*2, null); 
                movingLeft = false;
                //System.out.println("run horiz::"+columnStep+" :: vert::"+rowStep);  
            }            
            else{
                frames = 2;
                rowStep = 0;
                columnStep = 0;
                if(facingRight){
                    g2.drawImage(heroRightImage, boundingBoxForRendering.x, boundingBoxForRendering.y, (int)boundingBoxForRendering.getWidth()-border*2, (int)boundingBoxForRendering.getHeight()-border*2, null);  
                }
                else{
                    g2.drawImage(heroLeftImage, boundingBoxForRendering.x, boundingBoxForRendering.y, (int)boundingBoxForRendering.getWidth()-border*2, (int)boundingBoxForRendering.getHeight()-border*2, null);
                }                      
            }
            
            if(Main.debug){
                g2.setColor(Color.red);
                g2.drawRect(boundingBoxForRendering.x, boundingBoxForRendering.y, boundingBoxForRendering.width, boundingBoxForRendering.height);

                g2.setColor(Color.yellow);
                g2.drawRect(translatedX, translatedY, boundingBox.width, boundingBox.height);
            }

            
        }
        float tempHealth = displayHealth;
        if(tempHealth > 100) tempHealth =100;

        g2.setColor(Color.darkGray);
        g2.fillRect(2,5,(int)(100 * 2.5), 15);
        g2.setColor(new Color(10,50,(int)blueValue));
        g2.fillRect(2, 5, (int) (tempHealth * 2.5), 15);
        g2.setColor(Color.white);
        g2.drawRect(2,5,(int)(100 *2.5), 15);
        
        for(int i=0; i<healthPacks-1; i++){
         //g2.setColor(Color.red);
         g2.setColor(new Color(10,50,(int)blueValue)); //the rectangles below the health bar have same color as health bar
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
        //System.out.println("Display::"+displayHealth+" :: Health::"+health);
        blueValue = displayHealth*5;
        if(health <=0){
            health =0;
        }
        if(blueValue > 255){
            blueValue = 255;
        }
        if(blueValue < 75){
            blueValue =75;
        }
        
        healthPacks = (int)health/100;
    }
    
    public float getHealth(){
        return displayHealth;
    }
    public void setShield(float powerUp){
        this.displayHealth += powerUp;
        this.health += powerUp;
    }
}
