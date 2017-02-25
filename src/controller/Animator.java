package controller;

import java.awt.Graphics2D;
import java.awt.Image;

/**
* This class is the rendering engine for the game, it runs on it's own thread 
* and calls the render and update methods of every game object once per frame.
*
* @author  SATAS
* @version 1.0
* @since   2017-02-20 
*/
public class Animator implements Runnable
{
    public boolean running = true; //if false then stop rendering
    private final int FRAMES_PER_SECOND = 120; //the target FPS for rendering
    public Image dbImage = null; //An offscreen image used for Double buffering
    
    
    /**
    * Initialize double buffer image etc.
    */
    public void init(){
        
        //The traditional notion of double-buffering in Java applications is 
        //fairly straightforward: create an offscreen image, draw to that image 
        //using the image's graphics object, then, in one step, call drawImage 
        //using the target window's graphics object and the offscreen image.
        //this will reduce flicker because the slower process of rendering each 
        //object is done off screen
        
        //make sure the off screen image is defined
        //if (dbImage == null ) {
            // Creates an off-screen drawable image
            dbImage = Main.gamePanel.createImage(Main.gamePanel.getSize().width, Main.gamePanel.getSize().height);
            if (dbImage == null) {
                System.out.println("Critical Error: dbImage is null");
                System.exit(1);
            }
        //}
    }
    
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void run()
    {
        
        init();
        
        //start the main game loop
        while (running)
        {
            
            //create the graphics object from the offscreen image
            Graphics2D g2 = (Graphics2D) dbImage.getGraphics();
            
            //clear everything
            g2.fillRect(0, 0, dbImage.getWidth(null), dbImage.getHeight(null));
            
            //start time for calculating how long to sleep between frames
            long startTime = System.currentTimeMillis();
            
            //since we are on a seperate thread we need to sync the object list
            synchronized (Main.gameData.gameObjects){
                try{
                    //loop over every game object
                    for(int i=0; i<Main.gameData.gameObjects.size(); i++){
                        //call update, this advances state of any animations
                        Main.gameData.gameObjects.get(i).update();
                        //call render
                        Main.gameData.gameObjects.get(i).render(g2, Main.gameData.viewport);
                    }
                }
                catch(Exception e) {
                    System.out.println(e.getMessage()); 
                }
            }
            
            //to finish up the double buffer process draw the offscreen image to 
            //the panel
            Main.gamePanel.getGraphics().drawImage(dbImage, 0, 0, Main.gamePanel);
            
            //sleep the remaining amount of time based on the FPS
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            long targetTime = 1000/FRAMES_PER_SECOND;
            if(elapsedTime < targetTime){
                try {
                    Thread.sleep(targetTime-elapsedTime);
                } 
                catch (InterruptedException e) {}
            }
        }
    }

//    private synchronized void processCollisions()
//    {
//        // detect collisions between friendFigure and enemyFigures
//        // if detected, mark it as STATE_DONE, so that
//        // they can be removed at update() method       
//        for (GameFigure friendFigure : Main.gameData.friendFigures)
//        {
//            Rectangle2D friendBox = ((GameFigure) friendFigure).getCollisionBox();
//            for (GameFigure enemyFigure : Main.gameData.enemyFigures)
//            {
//                GameFigure enemy = (GameFigure) enemyFigure;
//                if (friendBox.intersects(enemy.getCollisionBox()))
//                {
//                    enemy.damageFigure();
//                    if(enemy.getHealth() == 0){ enemy.state = GameFigure.STATE_DESTROYED; }
//                    if(enemy instanceof Blackhole) { Main.score.addToScore(10000); Main.gameData.goUpLevel(); }
//                }
//            }
//        }    
//    }

}
