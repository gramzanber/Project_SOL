package model;

import controller.Animator;
import controller.Main;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import view.gameobjects.Hero;
import view.gameobjects.RenderableObject;
import view.swingcomponents.MainWindow;
import view.worldmap.Shooter;

/**
* The GameData class is responsible for holding the state of the game.
* This is whare game objects list is kept, current level is recorded, etc.
* 
* @author  SATAS
* @version 1.0
* @since   2017-02-18 
*/
public class GameData{
    
    //singleton instance
    private static GameData instance;
    
    /**
     * Singleton class
     * 
     * @return the singleton instance of this class
     */
    public static GameData getInstance(){
        //initialize instance on first use
        if(instance == null){
            instance = new GameData();
        }
        //return the instance
        return instance;
    }
    
    
    
    public final List<RenderableObject> gameObjects; //everything that is rendered is in this list
    public Rectangle viewport; //the current location of the viewport
    private Hero hero;
    private Shooter shooter;
    public Rectangle world;
    
    /**
     * private constructor prevents bypassing singleton pattern
     */
    public GameData(){
        //initialize the game objects list
        gameObjects = Collections.synchronizedList(new ArrayList<RenderableObject>());
        hero = new Hero(new Point(50, Main.WIN_HEIGHT-500));
        shooter = new Shooter(new Point(50, Main.WIN_HEIGHT - 500));
        world = new Rectangle(0,0,2048, 1536);
    }
    
    public Hero getHero(){
        return hero;
    }
    
    public Shooter getShooter(){
        return shooter;
    }
    
    /**
    * Add a game object to be rendered. This also adds the object as an action listener
    * 
    * @param gameObject The object to add.
    */
    public void addGameObject(RenderableObject gameObject){
        //add object to action listeners
        MainWindow.getInstance().getGamePanel().addMouseListener(gameObject);
        MainWindow.getInstance().getGamePanel().addKeyListener(gameObject);
        MainWindow.getInstance().getGamePanel().addMouseMotionListener(gameObject);
        
        //add object to list
        gameObjects.add(gameObject);
    }

    /**
    * Check if any solid game objects intersect a given rectangle
    * 
    * @param boundingBox The rectangle to check.
    */
    public boolean checkCollision(Rectangle boundingBox, RenderableObject obj) {
        synchronized (gameObjects){
            try{
                //loop over every game object
                for(int i=0; i < gameObjects.size(); i++){
                    if(obj != gameObjects.get(i) && gameObjects.get(i).isSolid()){
                        if(boundingBox.intersects(gameObjects.get(i).getBoundingBox())){
                            return true;
                        }
                    }
                }
            }
            catch(Exception e) {
                System.out.println(e.getMessage()); 
            }
        }
        return false;
    }
    
}
