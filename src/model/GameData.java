package model;

import controller.Main;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import view.RenderableObject;

/**
* The GameData class is responsible for holding the state of the game.
* This is whare game objects list is kept, current level is recorded, etc.
* 
* @author  SATAS
* @version 1.0
* @since   2017-02-18 
*/
public class GameData{
    
    public List<RenderableObject> gameObjects; //everything that is rendered is in this list
    public Rectangle viewport; //the current location of the viewport

    
    /**
    * A simple constructor.
    */
    public GameData(){
        //initialize the game objects list
        gameObjects = Collections.synchronizedList(new ArrayList<RenderableObject>());
    }
    
    /**
    * Add a game object to be rendered. This also adds the object as an action listener
    * 
    * @param gameObject The object to add.
    */
    public void addGameObject(RenderableObject gameObject){
        //add object to action listeners
        Main.gamePanel.addMouseListener(gameObject);
        Main.gamePanel.addKeyListener(gameObject);
        Main.gamePanel.addMouseMotionListener(gameObject);
        
        //add object to list
        gameObjects.add(gameObject);
    }
    
}
