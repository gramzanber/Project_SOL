package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.Laser;
import view.Missile;
import view.Shooter;
import view.Background;
import view.GameButton;
import view.Menu;
import view.Text;

/**
* The GameController class is responsible for making changes to the GameModel.
* This is whare the logic goes for handling user interaction with the game.
* Any new objects are created here.
* Levels and menus are loaded here
* 
* @author  SATAS
* @version 1.0
* @since   2017-02-18 
*/
public class GameController implements ActionListener{
  
    //define any menu buttons here so we can reference them in the action events
    private GameButton mainMenuButton;
    private GameButton startButton;
    private GameButton scoresButton;
    private GameButton controlsButton;
    private GameButton quitButton;
    
    //we can either create a new background obj each time we load a new menu
    //which will cause the animation to restart and look awkward
    //or we can define the background as a class var and reuse the same one that
    //way switching between menus is smooth.
    private Background menuBackground;
    
   /**
   * A simple constructor.
   */
    public GameController(){
        //Initialize the menu background.
        //note that other backgrounds can be created as needed. this is only here
        //because multiple screens use this background and it is animated
        menuBackground = new Background("/Images/corona_bk.png", false, true);
    }
    
   /**
   * This method removes all references to game objects and clears the obj list.
   * Each renderable object defines a clear method that takes care of action
   * listeners.
   * This method is called every time a new screen is loaded such as menus or levels
   */
    public void clear(){
        //call clear() on each game object
        for(int i=0; i<Main.gameData.gameObjects.size(); i++){
            Main.gameData.gameObjects.get(i).clear();
        }
        //clear the game object list
        Main.gameData.gameObjects.clear();
        //stop sounds
        Main.soundController.stop();
    }
    
   /**
   * Clear everything and load the main menu. 
   */
    public void showMainMenu(){
        //clear all game objects
        clear();
        
        //add background
        Main.gameData.gameObjects.add(menuBackground);
        
        //play background music
        Main.soundController.menuBGM();
        
        //add game title
        Font font = new Font("TimesRoman", Font.BOLD, 75); 
        Text text = new Text(new Point(Main.gameData.viewport.width/2, 50),"SOL", font, true);
        Main.gameData.addGameObject(text);
        
        //create and add a menu object
        int width = 120; //width of the buttons
        int height = 30; //height of the buttons
        int x = Main.gameData.viewport.width/2 - width/2; //center buttons
        int y = 150; //y position of first button
        int gap = 50; //the verticle gap between buttons
        
        //create a new menu object, this is a container that will handle highlighting
        //and adding of buttons to the object list
        Menu mainMenu = new Menu(new Point(x,y));
        Main.gameData.addGameObject(mainMenu);
        
        //add start button to the menu
        startButton = new GameButton(new Point(x,y+gap),width,height, "Start");
        startButton.addActionListener(this);
        mainMenu.addButton(startButton);
        
        //add score button to the menu
        scoresButton = new GameButton(new Point(x,y+gap*2),width,height, "High Score");
        scoresButton.addActionListener(this);
        mainMenu.addButton(scoresButton);
        
        //add controls button to the menu
        controlsButton = new GameButton(new Point(x,y+gap*3),width,height, "Controls");
        controlsButton.addActionListener(this);
        mainMenu.addButton(controlsButton);
        
        //add quit button to the menu
        quitButton = new GameButton(new Point(x,y+gap*4),width,height, "Quit");
        quitButton.addActionListener(this);
        mainMenu.addButton(quitButton);
    }


   /**
   * Clear everything and load the world map.
   * This is the screen where players select their level.
   */
    public void showWorld(){
        //clear all game objects
        clear();
        
        //add background
        Main.gameData.gameObjects.add(new Background("/Images/world_map_1.png", true, false));
        
        //play background music
        Main.soundController.openWorldBGM();
        
        //Add informational text at the top of the map
        Font font = new Font("TimesRoman", Font.BOLD, 30);
        String gameNameString = "SYSTEM MAP - Select assault point";
        Text text = new Text(new Point(Main.gameData.viewport.width/2, 10),gameNameString, font, true);
        text.setColor(Color.WHITE);
        Main.gameData.addGameObject(text);
        
        //draw rect arround planets
        //g2.drawRect((width / 2) - 70, (height / 2) + 30, 120, 30);
        //g2.drawRect((width / 2) - 70, (height / 2) + 30, 120, 30);
        //g2.drawRect((width / 2) - 70, (height / 2) + 30, 120, 30);
        //g2.drawRect((width / 2) - 70, (height / 2) + 30, 120, 30);
        //g2.drawRect((width / 2) - 70, (height / 2) + 30, 120, 30);
        
        //add the shooter object
        Main.gameData.addGameObject(new Shooter(new Point(100, 100)));
    }
    
   /**
   * Clear everything and load the score screen. 
   */
    public void showScores(){
        //clear all game objects
        clear();
        
        //add background
        Main.gameData.gameObjects.add(menuBackground);
        
        //play background music
        Main.soundController.menuBGM();
        
        //add game title
        Font font = new Font("TimesRoman", Font.BOLD, 75); 
        Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/2, 50),"SOL", font, true));

        //screen title
        font = new Font("TimesRoman", Font.PLAIN, 15); 
        String levelString = "High Scores";
        Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/2, 175),levelString, font, true));

        // Score board
        String scoreString01 = "1. Tyrel Tachibana 4526 1/27/2017";
        String scoreString02 = "2. Sam Sopp 4515 1/28/2017";
        String scoreString03 = "3. N/A";
        String scoreString04 = "4. N/A";
        String scoreString05 = "5. N/A";
        Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/3, 225),scoreString01, font, false));
        Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/3, 250),scoreString02, font, false));
        Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/3, 275),scoreString03, font, false));
        Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/3, 300),scoreString04, font, false));
        Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/3, 325),scoreString05, font, false));
        
        //create a new menu object, this is a container that will handle highlighting
        //and adding of buttons to the object list
        Menu scoresMenu = new Menu(new Point(0,0));
        Main.gameData.addGameObject(scoresMenu);
        
        //add button for returning to main menu
        mainMenuButton = new GameButton(new Point(Main.gameData.viewport.width/2 - 120/2,500),120,30, "Main Menu");
        mainMenuButton.addActionListener(this);
        scoresMenu.addButton(mainMenuButton);
    }

   /**
   * Clear everything and load the controls screen. 
   */
    public void showControls(){
        
        //clear all game objects
        clear();
        
        //add background
        Main.gameData.gameObjects.add(menuBackground);
        
        //play background music
        Main.soundController.menuBGM();
        
        //add game title
        Font font = new Font("TimesRoman", Font.BOLD, 75); 
        Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/2, 50),"SOL", font, true));
        
        //add controls text
        font = new Font("TimesRoman", Font.BOLD, 22); 
        Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/16, 175),"Controls:", font, false));    
            
        font = new Font("TimesRoman", Font.PLAIN, 18); 

        Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/16, 225),"Move Left:", font, false));
        Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/16, 250),"Move Right:", font, false));
        Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/16, 275),"Look Up:", font, false));
        Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/16, 300),"Crouch:", font, false));
        Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/16, 325),"Jump:", font, false));
        Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/16, 350),"Boost:", font, false));
        Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/16, 375),"Fire Primary Wepon:", font, false));
        Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/16, 400),"Fire Secondary Weapon:", font, false));

        Main.gameData.addGameObject(new Text(new Point((Main.gameData.viewport.width/16)*12, 225),"Left Arrow / A", font, false));
        Main.gameData.addGameObject(new Text(new Point((Main.gameData.viewport.width/16)*12, 250),"Right Arrow / D", font, false));
        Main.gameData.addGameObject(new Text(new Point((Main.gameData.viewport.width/16)*12, 275),"Up Arrow / W", font, false));
        Main.gameData.addGameObject(new Text(new Point((Main.gameData.viewport.width/16)*12, 300),"Down Arrow / S", font, false));
        Main.gameData.addGameObject(new Text(new Point((Main.gameData.viewport.width/16)*12, 325),"Space", font, false));
        Main.gameData.addGameObject(new Text(new Point((Main.gameData.viewport.width/16)*12, 350),"Space (while midair)", font, false));
        Main.gameData.addGameObject(new Text(new Point((Main.gameData.viewport.width/16)*12, 375),"Left Mouse", font, false));
        Main.gameData.addGameObject(new Text(new Point((Main.gameData.viewport.width/16)*12, 400),"Right Mouse", font, false));
        
        //create a new menu object, this is a container that will handle highlighting
        //and adding of buttons to the object list
        Menu scoresMenu = new Menu(new Point(0,0));
        Main.gameData.addGameObject(scoresMenu);
        
        //add button for returning to main menu
        mainMenuButton = new GameButton(new Point(Main.gameData.viewport.width/2 - 120/2,500),120,30, "Main Menu");
        mainMenuButton.addActionListener(this);
        scoresMenu.addButton(mainMenuButton);
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //handle select events from all game buttons
        if(e.getSource() == startButton){
            showWorld();
        }
        else if(e.getSource() == scoresButton){
            showScores();
        }
        else if(e.getSource() == mainMenuButton){
            showMainMenu();
        }
        else if(e.getSource() == controlsButton){
            showControls();
        }
        else if(e.getSource() == quitButton){
            System.exit(0);
        }
        
        
    }

   /**
   * A missile to the game object list
   * 
   * @param sx The starting x position
   * @param sy The starting y position
   * @param px The target x position
   * @param py The target y position
   * @param color The color
   */
    public void addMissile(float sx, float sy, int px, int py, Color color) {
        //create object
        Missile m = new Missile(sx, sy, px, py, color); 
        Main.gameData.addGameObject(m);
    }
   
   /**
   * A laser to the game object list
   * 
   * @param x The starting x position
   * @param y The starting y position
   * @param color The color
   * @param direction laser fired left or right
   */
    public void addLaser(float x, float y, Color color, boolean direction) {
        //add object
        Laser l = new Laser(x, y, color, direction); 
        Main.gameData.addGameObject(l);
    }
    
}