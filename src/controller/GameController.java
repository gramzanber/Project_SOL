package controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.Background;
import view.GameButton;
import view.Menu;

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
    
    
    private void clear(){
        for(int i=0; i<Main.gameData.gameObjects.size(); i++){
            Main.gamePanel.removeMouseListener(Main.gameData.gameObjects.get(i));
            Main.gamePanel.removeKeyListener(Main.gameData.gameObjects.get(i));
            Main.gamePanel.removeMouseMotionListener(Main.gameData.gameObjects.get(i));
        }
        Main.gameData.gameObjects.clear();
    }
    
    public void showMainMenu(){
        
        //clear all game objects
        clear();
        
        //add background
        Main.gameData.gameObjects.add(new Background("/Images/corona_bk.png"));
        
        int width = 120;
        int height = 30;
        int x = Main.gameData.viewport.width/2 - width/2;
        int y = 50;
        
        //create and add a menu object
        Menu mainMenu = new Menu(new Point(x,y));
        Main.gameData.addGameObject(mainMenu);
        
        startButton = new GameButton(new Point(x,y+50),width,height, "Start");
        startButton.addActionListener(this);
        mainMenu.addButton(startButton);
        
        scoresButton = new GameButton(new Point(x,y+120),width,height, "High Score");
        scoresButton.addActionListener(this);
        mainMenu.addButton(scoresButton);
        
        controlsButton = new GameButton(new Point(x,y+190),width,height, "Controls");
        controlsButton.addActionListener(this);
        mainMenu.addButton(controlsButton);
        
        quitButton = new GameButton(new Point(x,y+260),width,height, "Quit");
        quitButton.addActionListener(this);
        mainMenu.addButton(quitButton);
        
    }
    
    public void showScores(){
        
        //clear all game objects
        clear();
        
        //add background
        Main.gameData.gameObjects.add(new Background("/Images/corona_bk.png"));
        
        int width = 120;
        int height = 30;
        int x = Main.gameData.viewport.width/2 - width/2;
        int y = 50;
        
        //create and add a menu object
        Menu scoresMenu = new Menu(new Point(x,y));
        Main.gameData.addGameObject(scoresMenu);
        
        mainMenuButton = new GameButton(new Point(x,y+350),width,height, "Main Menu");
        mainMenuButton.addActionListener(this);
        scoresMenu.addButton(mainMenuButton);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == scoresButton){
            showScores();
        }
        else if(e.getSource() == mainMenuButton){
            showMainMenu();
        }
    }
    
}
