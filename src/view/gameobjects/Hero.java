package view.gameobjects;

import controller.AnimationController;
import controller.Main;
import controller.SoundController;
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
import model.GameData;

/**
* Render a hero object to the screen.
*
* @author  SATAS
* @version 1.0
* @since   2017-02-18 
*/
public class Hero extends Actor {
    
    private Rectangle viewportMain;
    private float health = 0;
    private float displayHealth =0;
    private float blueValue = 255;
    private static int score = 0;
    private int healthPacks =0;
    static boolean movingLeft = false;
    static boolean movingRight = false;
    static boolean movingUp = false;
    static boolean movingDown = false;
    static boolean facingRight;
    private static int secondaryWeap; //0 = grenade, 1 = seeker weapon, 2 = FlakCannon 
    
    
    private AnimationController animationController;
    
    /**
    * Constructor 
    * 
    * @param loc
    */
    public Hero(Point loc) {
        //call superclass constructor
        super(loc);
        facingRight = true;
        
        animationController = new AnimationController(AnimationController.Mode.AUTO, "hero_run_right");
        animationController.setFps(48);
        
        //update bounding box for the object
        super.boundingBox = new Rectangle(loc.x, loc.y, 50, 155);
        
        //Change this to try out secondary weapons

        this.secondaryWeap = 3;
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
        Rectangle viewportMain = GameData.getInstance().viewport;
        int translatedX =  ((int)boundingBox.getX() - (int)viewportMain.getX())+(int)boundingBox.getWidth();
        int translatedY =  ((int)boundingBox.getY() - (int)viewportMain.getY())+(int)boundingBox.getHeight()/2;
        if(SwingUtilities.isRightMouseButton(e))
        {
            System.out.println("Secondary Weapon!");
            
            switch(secondaryWeap){
                case 0:
                    System.out.println("Grenade Weapon");
                    Weapon g = new GrenadeWeapon(translatedX, translatedY, this);
                    synchronized (GameData.getInstance().gameObjects) {GameData.getInstance().addGameObject(g); }
                    SoundController.getInstance().primaryWeaponFire();
                    break;
                case 1:
                    System.out.println("Seeker Missile");
                    Weapon s = new SeekerMissile(translatedX, translatedY, e.getX(), e.getY(), this);
                    synchronized (GameData.getInstance().gameObjects) {GameData.getInstance().addGameObject(s); }
                    SoundController.getInstance().seekerMissileFire();
                    break;
                case 2:
                    System.out.println("Flak Cannon");
                    Weapon f = new FlakCannon(translatedX, translatedY, this);
                    synchronized (GameData.getInstance().gameObjects) {GameData.getInstance().addGameObject(f);}
                    SoundController.getInstance().primaryWeaponFire();
                    break;
                default:
                    System.out.println("SecondaryWeap:Default");
                    break;
            }
            
        }
        else if(SwingUtilities.isLeftMouseButton(e))
        {
            PrimaryWeapon m = new PrimaryWeapon((int)boundingBox.getX(), (int)boundingBox.getY()+60, this);
            SoundController.getInstance().primaryWeaponFire();

            synchronized (GameData.getInstance().gameObjects) { GameData.getInstance().addGameObject(m); }
        }
        else { System.out.printf("Mouse click error, Package: Controller; Class: view.Hero.java"); }
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void render(Graphics2D g2,Rectangle viewport)
    {
            //draw in relation to the viewport
            int translatedX =  (int)boundingBox.getX() - (int)viewport.getX();
            int translatedY =  (int)boundingBox.getY() - (int)viewport.getY();
            
            Rectangle boundingBoxForRendering = new Rectangle(translatedX-166/3, translatedY, 166, 155);
            
            if(movingUp){
                if(facingRight){
                    animationController.setSpriteSheet("hero_jump_right");
                    
                    if(animationController.getIndex() == 4){
                        BufferedImage sprite = animationController.getFrame();
                        g2.drawImage(sprite, boundingBoxForRendering.x, boundingBoxForRendering.y, (int)boundingBoxForRendering.getWidth(), (int)boundingBoxForRendering.getHeight(), null);                 
                        movingUp = false;                              
                    }
                    else{
                        BufferedImage sprite = animationController.getFrame();
                        g2.drawImage(sprite, boundingBoxForRendering.x, boundingBoxForRendering.y, (int)boundingBoxForRendering.getWidth(), (int)boundingBoxForRendering.getHeight(), null);                 
                        movingUp = false;                
                        animationController.update();                           
                    }                  
                }
                else{
                    animationController.setSpriteSheet("hero_jump_left");
                    
                    if(animationController.getIndex() == 4){
                        BufferedImage sprite = animationController.getFrame();
                        g2.drawImage(sprite, boundingBoxForRendering.x, boundingBoxForRendering.y, (int)boundingBoxForRendering.getWidth(), (int)boundingBoxForRendering.getHeight(), null);                 
                        movingUp = false;                              
                    }
                    else{
                        BufferedImage sprite = animationController.getFrame();
                        g2.drawImage(sprite, boundingBoxForRendering.x, boundingBoxForRendering.y, (int)boundingBoxForRendering.getWidth(), (int)boundingBoxForRendering.getHeight(), null);                 
                        movingUp = false;                
                        animationController.update();                           
                    }                     
                }
            }
            else if(movingDown){
                if(facingRight){
                    animationController.setSpriteSheet("hero_jump_right");
                    
                    if(animationController.getIndex() == 23){
                        BufferedImage sprite = animationController.getFrame();
                        g2.drawImage(sprite, boundingBoxForRendering.x, boundingBoxForRendering.y, (int)boundingBoxForRendering.getWidth(), (int)boundingBoxForRendering.getHeight(), null);                 
                        movingDown = false;                              
                    }
                    else{
                        BufferedImage sprite = animationController.getFrame();
                        g2.drawImage(sprite, boundingBoxForRendering.x, boundingBoxForRendering.y, (int)boundingBoxForRendering.getWidth(), (int)boundingBoxForRendering.getHeight(), null);                 
                        movingDown = false;                
                        animationController.update();                           
                    }                    
                }
                else{
                    animationController.setSpriteSheet("hero_jump_left");
                    
                    if(animationController.getIndex() == 23){
                        BufferedImage sprite = animationController.getFrame();
                        g2.drawImage(sprite, boundingBoxForRendering.x, boundingBoxForRendering.y, (int)boundingBoxForRendering.getWidth(), (int)boundingBoxForRendering.getHeight(), null);                 
                        movingDown = false;                              
                    }
                    else{
                        BufferedImage sprite = animationController.getFrame();
                        g2.drawImage(sprite, boundingBoxForRendering.x, boundingBoxForRendering.y, (int)boundingBoxForRendering.getWidth(), (int)boundingBoxForRendering.getHeight(), null);                 
                        movingDown = false;                
                        animationController.update();                           
                    }                    
                }                
            }
            else if(movingRight){
                facingRight = true;
                
                animationController.setSpriteSheet("hero_run_right");
                BufferedImage sprite = animationController.getFrame();
                g2.drawImage(sprite, boundingBoxForRendering.x, boundingBoxForRendering.y, (int)boundingBoxForRendering.getWidth(), (int)boundingBoxForRendering.getHeight(), null); 
                
                movingRight = false;
                
                animationController.update();
                SoundController.getInstance().playerMove();
            }
            else if(movingLeft){
                facingRight = false;

                animationController.setSpriteSheet("hero_run_left");
                BufferedImage sprite = animationController.getFrame();
                
                g2.drawImage(sprite, boundingBoxForRendering.x, boundingBoxForRendering.y, (int)boundingBoxForRendering.getWidth(), (int)boundingBoxForRendering.getHeight(), null); 
                movingLeft = false;
                
                animationController.update();
                SoundController.getInstance().playerMove();
            }            
            else{
                animationController.setFrame(0);
                
                if(facingRight){
                    animationController.setSpriteSheet("hero_stand_right");
                    BufferedImage sprite = animationController.getFrame();
                    g2.drawImage(sprite, boundingBoxForRendering.x, boundingBoxForRendering.y, (int)boundingBoxForRendering.getWidth(), (int)boundingBoxForRendering.getHeight(), null);  
                }
                else{
                    animationController.setSpriteSheet("hero_stand_left");
                    BufferedImage sprite = animationController.getFrame();
                    g2.drawImage(sprite, boundingBoxForRendering.x, boundingBoxForRendering.y, (int)boundingBoxForRendering.getWidth(), (int)boundingBoxForRendering.getHeight(), null);
                }                      
            }
            
            if(Main.debug){
                g2.setColor(Color.red);
                g2.drawRect(boundingBoxForRendering.x, boundingBoxForRendering.y, boundingBoxForRendering.width, boundingBoxForRendering.height);

                g2.setColor(Color.yellow);
                g2.drawRect(translatedX, translatedY, boundingBox.width, boundingBox.height);
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
        
        g2.drawString("Score: "+score, 10, 40);
    }

    private void healthBound() {
        //the health is depleated constatntly but just as a demo. will be changed when there are enemies in the game
        if(displayHealth > 100){
            displayHealth = 100;
        }
        if(displayHealth <=0 && health>0){
            health -= 100;
            displayHealth = health;
        }
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
    
    public float getShield(){
        return displayHealth;
    }
    public void setShield(float powerUp){
        this.displayHealth += powerUp;
        this.health += powerUp;
    }
    
    public String getSecondaryWeap(){
        switch(secondaryWeap){
            case 0:
                return "Grenade Launcher";
            case 1:
                return "Seeker Missile";
            case 2:
                return "Flak Cannon";
            default:
                return "None Equipped";
        }
        
    }
    
    public static void setSecondaryWeap(int w){
        secondaryWeap = w;
    }
    
    public static int getScore(){
        return score;
    }
    
    public static void setScore(int s){
        score = s;
    }
}
