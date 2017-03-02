package controller;

import static controller.Main.gamePanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import view.Laser;
import view.Missile;
import view.Background;
import view.Block;
import view.GameButton;
import view.Goal;
import view.Earth;
import view.Menu;
import view.Mercury;
import view.Moon;
import view.Sun;
import view.Text;
import view.Venus;

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
public class GameController implements ActionListener, KeyListener, ComponentListener{
  
    //define any menu buttons here so we can reference them in the action events
    private GameButton mainMenuButton;
    private GameButton startButton;
    private GameButton scoresButton;
    private GameButton controlsButton;
    private GameButton quitButton;
    
    private boolean fullscreen;
    
    Goal goal;
    Earth earth;
    Sun sun;
    Moon moon;
    Venus venus;
    Mercury mercury;
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
        menuBackground = new Background("/Images/corona_bk.png", Background.Stretch.NONE, true, true);
        
        fullscreen = false;
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
        
        //reset viewport
        //Main.gameData.viewport.setLocation(new Point(0,0));
        resetViewport();
    }
    
    public void resetViewport(){
        Main.gameData.viewport = new Rectangle(0,Main.gameData.world.height-Main.gamePanel.getHeight(),Main.gamePanel.getWidth(),Main.gamePanel.getHeight());
        Main.animator.init();
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
        Main.gameData.gameObjects.add(new Background("/Images/world_map_1.png", Background.Stretch.VIEWPORT, false, false));
        
        //play background music
        Main.soundController.openWorldBGM();
        
        //Add informational text at the top of the map
        Font font = new Font("TimesRoman", Font.BOLD, 30);
        String gameNameString = "SYSTEM MAP - Select assault point";
        Text text = new Text(new Point(Main.gameData.viewport.width/2, 10),gameNameString, font, true);
        text.setColor(Color.WHITE);
        Main.gameData.addGameObject(text);
        
        //add the shooter object
        Main.gameData.getShooter();
        Main.gameData.addGameObject(Main.gameData.getShooter());
        //draw rect arround planets
        //Earth on world map
        earth = new Earth(new Point(800,Main.WIN_HEIGHT-400),50,50);
        Main.gameData.addGameObject(earth);
        earth.addActionListener(this);
        //Sun on world map
        sun = new Sun(new Point(800,Main.WIN_HEIGHT-400),50,50);
        Main.gameData.addGameObject(sun);
        sun.addActionListener(this); 
        //Moon on world map
        moon = new Moon(new Point(800,Main.WIN_HEIGHT-400),50,50);
        Main.gameData.addGameObject(moon);
        moon.addActionListener(this);
        //Venus on world map
        venus = new Venus(new Point(800,Main.WIN_HEIGHT-400),50,50);
        Main.gameData.addGameObject(venus);
        venus.addActionListener(this);
        //Mercury on world map
        mercury = new Mercury(new Point(800,Main.WIN_HEIGHT-400),50,50);
        Main.gameData.addGameObject(mercury);
        mercury.addActionListener(this);
        
        
        //Main.gameData.addGameObject(new Shooter(new Point(100, 100)));
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
   * Clear everything and load the first level
   */
    public void showLevel1(){
        
        //clear all game objects
        clear();
        
        //add background
        Main.gameData.gameObjects.add(new Background("/Images/BG Apocalyptic 2.jpg", Background.Stretch.WORLD, true, false));
        
        //play background music
        Main.soundController.earthBGM();
        
        int rightEdge = 0;
        int groundLevel = Main.gameData.world.height - 150;
        
        //add ground
        Main.gameData.addGameObject(new Block(Block.Style.TRANSPARENT, new Point(0, groundLevel), 99999, 50));

        //add left edge
        Main.gameData.addGameObject(new Block(Block.Style.TRANSPARENT, new Point(0, 0), 50, Main.gameData.world.height));
        

        //stairs
        for(int i=0; i<11; i++){
            Main.gameData.addGameObject(new Block(Block.Style.BLUE, new Point(rightEdge+500+150*i, groundLevel-80-80*i), 150, 8));
        }
        Main.gameData.addGameObject(new Block(Block.Style.BLUE, new Point(rightEdge+500+150*5 - 150-100-1000, groundLevel-80-80*5),1000, 8));

        
        //add hero
        Main.gameData.getHero().setLocation(new Point(50, groundLevel-100));
        Main.gameData.addGameObject(Main.gameData.getHero());
        
        //add goal
        goal = new Goal(new Point(1000,groundLevel-200), 20, 200);
        goal.addActionListener(this);
        Main.gameData.addGameObject(goal);
    }
    public void showLevel2(){
        System.out.println("Level 2 under construction");
    }
    public void showLevel3(){
        System.out.println("Level 3 under construction");
    }
    public void showLevel4(){
        System.out.println("Level 4 under construction");
    }
    public void showLevel5(){
        System.out.println("Level 5 under construction");
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
        else if(e.getSource() == goal){
            showMainMenu();
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
        //if(){}
        if(e.getKeyCode() == KeyEvent.VK_1){
            showLevel1();
        }
        else if(e.getKeyCode() == KeyEvent.VK_0){
            showMainMenu();
        }
        else if(e.getKeyCode() == KeyEvent.VK_F11){
            fullscreen = !fullscreen;
            if(fullscreen){
                //GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
                //device.setFullScreenWindow(Main.game);
                
                Main.game.setExtendedState(JFrame.MAXIMIZED_BOTH); 
                Main.game.dispose();
                Main.game.setUndecorated(true);
                
                Main.game.setVisible(true);
            }
            else{
                 
                
                Main.game.dispose();
                Main.game.setSize(700, 600);
                Main.game.setExtendedState(JFrame.NORMAL);
                Main.game.setUndecorated(false);
                Main.game.setVisible(true);
            }
        }
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void componentResized(ComponentEvent e) {
        resetViewport();
        //Main.gameData.viewport = new Rectangle(0, 600-Main.gamePanel.getHeight(), Main.gamePanel.getWidth(),Main.gamePanel.getHeight());
        //Main.animator.init();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
    
}