// Author:	SATAS
// Course:	SDD
// Semester:    Spring, 2017

package controller;

import java.awt.geom.Rectangle2D;
import model.Blackhole;
import model.GameFigure;
import model.Shooter;

public class Animator implements Runnable
{
    public boolean running = true;
    private boolean mainMenu = true;
    private boolean mainWorld = false;
    private boolean scoreScreen = false;
    private final int FRAMES_PER_SECOND = 30;
    private boolean helpScreen;
    
    @Override
    public void run()
    {
        //long startTime = System.currentTimeMillis();
        
        while (running)
        {
            if(this.mainMenu)
            {
                Main.gamePanel.menuRender();
            }
            else if(this.mainWorld){
                Main.gamePanel.worldRender();
            }
            else if(this.scoreScreen)
            {
                Main.gamePanel.scoreScreenRender();
            }
            else if(this.helpScreen){
                Main.gamePanel.helpScreenRender();
            }
            else
            {
                processCollisions();

                Main.gameData.update();
                Main.gamePanel.gameRender();
                Main.gamePanel.printScreen();
                
            }
            //long endTime = System.currentTimeMillis();
            int sleepTime = (int) (1.0 / FRAMES_PER_SECOND * 3000);// - (int) (endTime - startTime);
            //System.out.println("Sleep Time: " + sleepTime);
            if (sleepTime > 0)
            {
                try {
                    Thread.sleep(sleepTime); // ms
                } catch (InterruptedException e) {

                }
            }
        }
        System.exit(0);
    }

    private synchronized void processCollisions()
    {
        // detect collisions between friendFigure and enemyFigures
        // if detected, mark it as STATE_DONE, so that
        // they can be removed at update() method       
        for (GameFigure friendFigure : Main.gameData.friendFigures)
        {
            Rectangle2D friendBox = ((GameFigure) friendFigure).getCollisionBox();
            for (GameFigure enemyFigure : Main.gameData.enemyFigures)
            {
                GameFigure enemy = (GameFigure) enemyFigure;
                if (friendBox.intersects(enemy.getCollisionBox()))
                {
                    enemy.damageFigure();
                    if(enemy.getHealth() == 0){ enemy.state = GameFigure.STATE_DESTROYED; }
                    if(enemy instanceof Blackhole) { Main.score.addToScore(10000); Main.gameData.goUpLevel(); }
                }
            }
        }    
    }
    
    public boolean isAtMainMenu()
    {
        return this.mainMenu;
    }
    
    public boolean isAtScoreScreen()
    {
        return this.scoreScreen;
    }
    
    boolean isAtMainWorld() {
        return this.mainWorld;
    }
    
    boolean isAtHelpScreen() {
        return this.helpScreen;
    }
    
    public void startGame()
    {
        this.mainMenu = false;
        this.scoreScreen = false;
        //test code to try to get the world map to showup after you hit start
        this.mainWorld = true;
        this.helpScreen = false;
    }
    
    public void scoreScreen()
    {
        this.mainMenu = false;
        this.scoreScreen = true;
        this.mainWorld = false;
        this.helpScreen = false;
    }
    
    public void mainMenu()
    {
        this.mainMenu = true;
        this.scoreScreen = false;
        this.mainWorld = false;
        this.helpScreen = false;
    }
    
    public void worldmap(){
        this.mainWorld = false;
        this.mainMenu = false;
        this.scoreScreen = false;
        this.helpScreen = false;
    }

    void helpScreen() {
        this.mainWorld = false;
        this.mainMenu = false;
        this.scoreScreen = false;
        this.helpScreen = true;
    }

}
