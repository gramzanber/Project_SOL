package view.gameobjects;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import model.GameData;
import model.ID;
import view.swingcomponents.MainWindow;

/**
* An abstract class to define needed methods for the rendering engine
*
* @author  SATAS
* @version 1.0
* @since   2017-02-18 
*/
public abstract class RenderableObject implements MouseListener,KeyListener,MouseMotionListener{
    
    protected Point loc; //the location of this object
    protected boolean rightProjectile;
    protected Rectangle boundingBox;
    protected boolean solid;
    protected ID id;
    
    /**
    * A simple constructor 
    * 
    * @param loc The location of the object
    */
    public RenderableObject(Point loc){
        this.loc = loc;
        solid = false;
        boundingBox = new Rectangle(loc.x, loc.y, 10, 10);
    }
    
    /**
    * Called once per frame, advance any animations
    */
    public void update(){
        collide();
    }
    
    /**
    * Render to a graphics 2d object
    * 
    * @param g2 The graphics2d object
    * @param viewport The current viewport, objects outside of viewport are skipped
    */
    public void render(Graphics2D g2, Rectangle viewport){
        if(viewport.contains(loc)){
            g2.drawRect(boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
        }
    }
    
    /**
    * Remove this object from all listener lists
    */
    public void clear(){
        MainWindow.getInstance().getGamePanel().removeMouseListener(this);
        MainWindow.getInstance().getGamePanel().removeKeyListener(this);
        MainWindow.getInstance().getGamePanel().removeMouseMotionListener(this);
    }

    public Rectangle getBoundingBox(){
        return boundingBox;
    }
    
    public void setLocation(Point loc){
        boundingBox.setLocation(loc);
    }
    
    public boolean isSolid(){
        return solid;
    }
    public void setSolid(boolean solid){
        this.solid = solid;
    }
    public ID getId(){
        return id;
    }
    public void collide(){
        if(GameData.getInstance().getHero().getBoundingBox().intersects(boundingBox) && getId() == ID.SmallEnemy)
        {
            //if(boundingBox.){
                System.out.println("Killed Enemy! By walking into it.");
            
                this.clear();
                GameData.getInstance().gameObjects.remove(this);
                GameData.getInstance().getHero().setShield(-10);
            
                System.out.println("Health::"+ GameData.getInstance().getHero().getShield());
            //}
            
        }
        if(GameData.getInstance().getHero().getBoundingBox().intersects(boundingBox) && getId() == ID.LargeEnemyProjectile)
        {
            System.out.println("Killed Enemy! By walking into it.");
            
            this.clear();
            GameData.getInstance().gameObjects.remove(this);
            GameData.getInstance().getHero().setShield(-10);
            
            //System.out.println("Health::"+ GameData.getInstance().getHero().getShield());
        }
        if(GameData.getInstance().getHero().getBoundingBox().intersects(boundingBox)&& getId() == ID.LargeEnemyCollision)
        {
            System.out.println("Killed Enemy! By walking into it.");
            
            this.clear();
            GameData.getInstance().gameObjects.remove(this);
            GameData.getInstance().getHero().setShield(-30);
            
            //System.out.println("Health::"+ GameData.getInstance().getHero().getShield());
        }
        if(GameData.getInstance().getHero().getBoundingBox().intersects(boundingBox) && getId() == ID.BossProjectile)
        {
            System.out.println("Killed Enemy! By walking into it.");
            
            this.clear();
            GameData.getInstance().gameObjects.remove(this);
            GameData.getInstance().getHero().setShield(-20);
            
            //System.out.println("Health::"+ GameData.getInstance().getHero().getShield());
        }
        if(GameData.getInstance().getHero().getBoundingBox().intersects(boundingBox) && getId() == ID.BossCollision)
        {
            System.out.println("Killed Enemy! By walking into it.");
            
            this.clear();
            GameData.getInstance().gameObjects.remove(this);
            GameData.getInstance().getHero().setShield(-40);
            
            //System.out.println("Health::"+ GameData.getInstance().getHero().getShield());
        }
        //collision for projectiles
        //if(GameData.getInstance().)
    }
    
}
