package controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import model.GameData;
import view.swingcomponents.MainWindow;

/**
* This class is the rendering engine for the game, it runs on it's own thread 
* and calls the render and update methods of every game object once per frame.
*
* @author  SATAS
* @version 1.0
* @since   2017-02-20 
*/
public class Animator
{
    
    //singleton instance
    private static Animator instance;
    
    /**
     * Singleton class
     * 
     * @return the singleton instance of this class
     */
    public static Animator getInstance(){
        //initialize instance on first use
        if(instance == null){
            instance = new Animator();
        }
        //return the instance
        return instance;
    }
    
    public boolean running = true; //if false then stop rendering
    private final int FRAMES_PER_SECOND = 120; //the target FPS for rendering
    private Image dbImage = null; //An offscreen image used for Double buffering
    private RenderingThread t;
    
    /**
     * private constructor prevents bypassing singleton pattern
     */
    private Animator(){
        this.init(100, 100);
    }
    
    
    public void start(){
        if(t == null){
            t = new RenderingThread();
        }
        if(!t.isAlive()){
            t.start();
        }
        this.running = true;
    }
    public void stop(){
        this.running = false;
    }
    public boolean isRunning(){
        return this.running;
    }
    
    
    /**
     * Create the double buffer image
     */
    public void init(int canvasWidth, int canvasHeight){
        
        //The traditional notion of double-buffering in Java applications is 
        //fairly straightforward: create an offscreen image, draw to that image 
        //using the image's graphics object, then, in one step, call drawImage 
        //using the target window's graphics object and the offscreen image.
        //this will reduce flicker because the slower process of rendering each 
        //object is done off screen
        
        dbImage = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB); 
        
    }
    
    /**
     * Private inner class.
     * This is the rendering thread
     */
    private class RenderingThread extends Thread {
    
        //start time for calculating how long to sleep between frames
        long startTime = System.currentTimeMillis();
        
        public RenderingThread(){
            
        }
        
        @Override
        public void run(){
            while (Animator.getInstance().isRunning()){
                
                //render frame
                Animator.getInstance().renderScene();
               
               
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
        
    }

    
    public void renderScene(){
        
            //create the graphics object from the offscreen image
            Graphics2D g2 = (Graphics2D) dbImage.getGraphics();
            
            //clear
            g2.fillRect(0,0,dbImage.getWidth(null), dbImage.getHeight(null));
            
            
            //since we are on a seperate thread we need to sync the object list
            synchronized (GameData.getInstance().gameObjects){
                try{
                    //loop over every game object
                    for(int i=0; i<GameData.getInstance().gameObjects.size(); i++){
                        //call update, this advances state of any animations
                        GameData.getInstance().gameObjects.get(i).update();
                        //call render
                        GameData.getInstance().gameObjects.get(i).render(g2, GameData.getInstance().viewport);
                    }
                }
                catch(Exception e) {
                    System.out.println(e.getMessage()); 
                }
            }
            
            //to finish up the double buffer process draw the offscreen image to 
            //the panel
            //adding try/catch because of null pointer error sometimes when changing window size
            try{
                MainWindow.getInstance().getGamePanel().getGraphics().drawImage(dbImage, 0, 0, MainWindow.getInstance().getGamePanel());
            }
            catch(Exception e){
                e.printStackTrace();
            }
            
            
            
        
    }

//    private synchronized void processCollisions()
//    {
//        // detect collisions between friendFigure and enemyFigures
//        // if detected, mark it as STATE_DONE, so that
//        // they can be removed at update() method       
//        for (GameFigure friendFigure : GameData.getInstance().friendFigures)
//        {
//            Rectangle2D friendBox = ((GameFigure) friendFigure).getCollisionBox();
//            for (GameFigure enemyFigure : GameData.getInstance().enemyFigures)
//            {
//                GameFigure enemy = (GameFigure) enemyFigure;
//                if (friendBox.intersects(enemy.getCollisionBox()))
//                {
//                    enemy.damageFigure();
//                    if(enemy.getHealth() == 0){ enemy.state = GameFigure.STATE_DESTROYED; }
//                    if(enemy instanceof Blackhole) { Main.score.addToScore(10000); GameData.getInstance().goUpLevel(); }
//                }
//            }
//        }    
//    }

}
