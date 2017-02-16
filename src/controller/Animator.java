// Author:	SATAS
// Course:	SDD
// Semester:    Spring, 2017

package controller;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import model.Blackhole;
import model.GameFigure;
import model.Shooter;
import java.awt.Image;

public class Animator implements Runnable
{
    public boolean running = true;
    private boolean mainMenu = true;
    private boolean mainWorld = false;
    private boolean scoreScreen = false;
    private final int FRAMES_PER_SECOND = 30;
    private boolean helpScreen;
    private Image dbImage = null;   // Double buffer image
    private Graphics2D g2;
    
    @Override
    public void run()
    {
        //long startTime = System.currentTimeMillis();
        
        //The traditional notion of double-buffering in Java applications is 
        //fairly straightforward: create an offscreen image, draw to that image 
        //using the image's graphics object, then, in one step, call drawImage 
        //using the target window's graphics object and the offscreen image.
        //this will reduce flicker because the slower process of rendering each 
        //object is done off screen
        
        //make sure the off screen image is defined
        if (dbImage == null) {
            // Creates an off-screen drawable image
            dbImage = Main.gamePanel.createImage(Main.gamePanel.getSize().width, Main.gamePanel.getSize().height);
            if (dbImage == null) {
                System.out.println("Critical Error: dbImage is null");
                System.exit(1);
            } else {
                //create graphics2d object from off screen image
                g2 = (Graphics2D) dbImage.getGraphics();
            }
        }
        while (running)
        {
            synchronized (Main.gameData.gameObjects){
                try{
                    for(int i=0; i<Main.gameData.gameObjects.size(); i++){
                        Main.gameData.gameObjects.get(i).update();
                        Main.gameData.gameObjects.get(i).render(g2, Main.gameData.viewport);
                    }
                }
                catch(Exception e) {
                    System.out.println(e.getMessage()); 
                }
                
            }
            Main.gamePanel.getGraphics().drawImage(dbImage, 0, 0, Main.gamePanel);
            
//            if(this.mainMenu)
//            {
//                Main.gamePanel.menuRender();
//            }
//            else if(this.mainWorld){
//                Main.gamePanel.worldRender();
//            }
//            else if(this.scoreScreen)
//            {
//                Main.gamePanel.scoreScreenRender();
//            }
//            else if(this.helpScreen){
//                Main.gamePanel.helpScreenRender();
//            }
//            else
//            {
//                processCollisions();
//
//                Main.gameData.update();
//                Main.gamePanel.gameRender();
//                Main.gamePanel.printScreen();
//                
//            }
//            //long endTime = System.currentTimeMillis();
//            int sleepTime = (int) (1.0 / FRAMES_PER_SECOND * 3000);// - (int) (endTime - startTime);
//            //System.out.println("Sleep Time: " + sleepTime);
//            if (sleepTime > 0)
//            {
//                try {
//                    Thread.sleep(sleepTime); // ms
//                } catch (InterruptedException e) {
//
//                }
//            }
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
