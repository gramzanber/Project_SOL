package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Laser;
import model.Missile;
import model.Shooter;
import view.Background;
import view.GameButton;
import static view.GamePanel.height;
import static view.GamePanel.width;
import view.Menu;
import view.Text;

/**
* The GameController class is responsible for making changes to the GameModel.
* This is whare the logic goes for handling user interaction with the game
*
* @author  SATAS
* @version 1.0
* @since   2017-02-18 
*/
public class GameController implements ActionListener{
    
    private GameButton mainMenuButton;
    private GameButton startButton;
    private GameButton scoresButton;
    private GameButton controlsButton;
    private GameButton quitButton;
    private Background menuBackground;
    
    public GameController(){
        menuBackground = new Background("/Images/corona_bk.png", false, true);
    }
    
    private void clear(){
        for(int i=0; i<Main.gameData.gameObjects.size(); i++){
            Main.gameData.gameObjects.get(i).clear();
        }
        Main.gameData.gameObjects.clear();
    }
    
    public void showMainMenu(){
        
        //clear all game objects
        clear();
        
        //add background
        Main.gameData.gameObjects.add(menuBackground);
        
        
        //add game title
        Font font = new Font("TimesRoman", Font.BOLD, 75); 
        Text text = new Text(new Point(Main.gameData.viewport.width/2, 50),"SOL", font, true);
        Main.gameData.addGameObject(text);
        
        
        //create and add a menu object
        int width = 120;
        int height = 30;
        int x = Main.gameData.viewport.width/2 - width/2;
        int y = 150;
        
        Menu mainMenu = new Menu(new Point(x,y));
        Main.gameData.addGameObject(mainMenu);
        
        startButton = new GameButton(new Point(x,y+50),width,height, "Start");
        startButton.addActionListener(this);
        mainMenu.addButton(startButton);
        
        scoresButton = new GameButton(new Point(x,y+100),width,height, "High Score");
        scoresButton.addActionListener(this);
        mainMenu.addButton(scoresButton);
        
        controlsButton = new GameButton(new Point(x,y+150),width,height, "Controls");
        controlsButton.addActionListener(this);
        mainMenu.addButton(controlsButton);
        
        quitButton = new GameButton(new Point(x,y+200),width,height, "Quit");
        quitButton.addActionListener(this);
        mainMenu.addButton(quitButton);
        
    }




    public void showWorld(){
        //clear all game objects
        clear();
        
        //add background
        Main.gameData.gameObjects.add(new Background("/Images/world_map_1.png", true, false));
        
        //g2.setFont(new Font("TimesRoman", Font.BOLD, 30));
        Font font = new Font("TimesRoman", Font.BOLD, 30);
        String gameNameString = "SYSTEM MAP - Select assault point";
        //g2.drawString(gameNameString, (width / 2) - 250, 40);
        Text text = new Text(new Point(Main.gameData.viewport.width/2, 10),gameNameString, font, true);
        text.setColor(Color.WHITE);
        Main.gameData.addGameObject(text);
        
        //draw rect arround planets 
        //g2.drawRect((width / 2) - 70, (height / 2) + 30, 120, 30);
        //g2.drawRect((width / 2) - 70, (height / 2) + 30, 120, 30);
        //g2.drawRect((width / 2) - 70, (height / 2) + 30, 120, 30);
        //g2.drawRect((width / 2) - 70, (height / 2) + 30, 120, 30);
        //g2.drawRect((width / 2) - 70, (height / 2) + 30, 120, 30);
        
        
        Main.gameData.addGameObject(new Shooter(new Point(100, 100)));
        
    }
    
    public void showScores(){
        
        //clear all game objects
        clear();
        
        //add background
        Main.gameData.gameObjects.add(menuBackground);
        
        
        //add game title
        Font font = new Font("TimesRoman", Font.BOLD, 75); 
        Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/2, 50),"SOL", font, true));
        
        //add score text
        font = new Font("TimesRoman", Font.PLAIN, 15); 
        // Level Information
        String levelString = "High Scores";
        //g2.drawString(levelString, (width / 2) - 50, (height / 2) - 140);'
        Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/2, 175),levelString, font, true));
        // Score
        String scoreString01 = "1. Tyrel Tachibana 4526 1/27/2017";
        String scoreString02 = "2. Sam Sopp 4515 1/28/2017";
        String scoreString03 = "3. N/A";
        String scoreString04 = "4. N/A";
        String scoreString05 = "5. N/A";
        //g2.drawString(scoreString01, (width / 2) - 70, (height / 2) - 100);
        Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/3, 225),scoreString01, font, false));
        //g2.drawString(scoreString02, (width / 2) - 70, (height / 2) - 80);
        Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/3, 250),scoreString02, font, false));
        //g2.drawString(scoreString03, (width / 2) - 70, (height / 2) - 60);
        Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/3, 275),scoreString03, font, false));
        //g2.drawString(scoreString04, (width / 2) - 70, (height / 2) - 40);
        Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/3, 300),scoreString04, font, false));
        //g2.drawString(scoreString05, (width / 2) - 70, (height / 2) - 20);
        Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/3, 325),scoreString05, font, false));
        
        
        
        
        int width = 120;
        int height = 30;
        int x = Main.gameData.viewport.width/2 - width/2;
        int y = 50;
        
        //create and add a menu object
        Menu scoresMenu = new Menu(new Point(x,y));
        Main.gameData.addGameObject(scoresMenu);
        
        mainMenuButton = new GameButton(new Point(x,y+450),width,height, "Main Menu");
        mainMenuButton.addActionListener(this);
        scoresMenu.addButton(mainMenuButton);
        
    }

    
    public void showControls(){
        
        //clear all game objects
        clear();
        
        //add background
        Main.gameData.gameObjects.add(menuBackground);
        
        //add game title
        Font font = new Font("TimesRoman", Font.BOLD, 75); 
        Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/2, 50),"SOL", font, true));
        
        //add controls text
        font = new Font("TimesRoman", Font.BOLD, 22); 
        //g2.drawString("Controls:", ((width+40)-width), 150);
        Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/16, 175),"Controls:", font, false));    
            
            //g2.setFont(new Font("TimesRoman", Font.PLAIN, 18));   
            font = new Font("TimesRoman", Font.PLAIN, 18); 
            
            //g2.drawString("Move Left:", ((width+40)-width), 200);
            Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/16, 225),"Move Left:", font, false));
            //g2.drawString("Move Right:", ((width+40)-width), 225);
            Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/16, 250),"Move Right:", font, false));
            //g2.drawString("Look Up:", ((width+40)-width), 250);
            Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/16, 275),"Look Up:", font, false));
            //g2.drawString("Crouch:", ((width+40)-width), 275);
            Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/16, 300),"Crouch:", font, false));
            //g2.drawString("Jump:", ((width+40)-width), 300);
            Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/16, 325),"Jump:", font, false));
            //g2.drawString("Boost:", ((width+40)-width), 325);
            Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/16, 350),"Boost:", font, false));
            //g2.drawString("Fire Primary Wepon:", ((width+40)-width), 350);
            Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/16, 375),"Fire Primary Wepon:", font, false));
            //g2.drawString("Fire Secondary Weapon:", ((width+40)-width), 375);
            Main.gameData.addGameObject(new Text(new Point(Main.gameData.viewport.width/16, 400),"Fire Secondary Weapon:", font, false));
            
            //g2.drawString("Left Arrow / A", (width-200), 200);
            Main.gameData.addGameObject(new Text(new Point((Main.gameData.viewport.width/16)*12, 225),"Left Arrow / A", font, false));
            //g2.drawString("Right Arrow / D", (width-200), 225);
            Main.gameData.addGameObject(new Text(new Point((Main.gameData.viewport.width/16)*12, 250),"Right Arrow / D", font, false));
            //g2.drawString("Up Arrow / W", (width-200), 250);
            Main.gameData.addGameObject(new Text(new Point((Main.gameData.viewport.width/16)*12, 275),"Up Arrow / W", font, false));
            //g2.drawString("Down Arrow / S", (width-200), 275);
            Main.gameData.addGameObject(new Text(new Point((Main.gameData.viewport.width/16)*12, 300),"Down Arrow / S", font, false));
            //g2.drawString("Space", (width-200), 300);
            Main.gameData.addGameObject(new Text(new Point((Main.gameData.viewport.width/16)*12, 325),"Space", font, false));
            //g2.drawString("Space (while midair)", (width-200), 325);
            Main.gameData.addGameObject(new Text(new Point((Main.gameData.viewport.width/16)*12, 350),"Space (while midair)", font, false));
            //g2.drawString("Left Mouse", (width-200), 350);
            Main.gameData.addGameObject(new Text(new Point((Main.gameData.viewport.width/16)*12, 375),"Left Mouse", font, false));
            //g2.drawString("Right Mouse", (width-200), 375);  
            Main.gameData.addGameObject(new Text(new Point((Main.gameData.viewport.width/16)*12, 400),"Right Mouse", font, false));
        
        
        
        int width = 120;
        int height = 30;
        int x = Main.gameData.viewport.width/2 - width/2;
        int y = 50;
        
        //create and add a menu object
        Menu scoresMenu = new Menu(new Point(x,y));
        Main.gameData.addGameObject(scoresMenu);
        
        mainMenuButton = new GameButton(new Point(x,y+450),width,height, "Main Menu");
        mainMenuButton.addActionListener(this);
        scoresMenu.addButton(mainMenuButton);
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
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
        
        
    }

    public void addMissile(float sx, float sy, int px, int py, Color color) {
        
        Missile m = new Missile(sx, sy, px, py, color); 
        Main.gameData.addGameObject(m);
        
    }
    public void addLaser(float x, float y, Color color, boolean direction) {
        
        Laser l = new Laser(x, y, color, direction); 
        Main.gameData.addGameObject(l);
        
    }
    
}
