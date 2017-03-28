package controller;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import static javax.swing.Spring.height;
import static javax.swing.Spring.width;
import model.GameData;
import model.quadtree.QuadTree;
import view.gameobjects.RenderableObject;
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
    private final int FRAMES_PER_SECOND = 60; //the target FPS for rendering
    private Image dbImage = null; //An offscreen image used for Double buffering
    private RenderingThread t;
    private QuadTree quadTree;
    
    /**
     * private constructor prevents bypassing singleton pattern
     */
    private Animator(){
        this.init(100, 100);
        
        quadTree = new QuadTree(0, 0, 9999.0, 9999.0);
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
    public QuadTree getQuadTree(){
        return quadTree;
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
        
        //see: http://stackoverflow.com/questions/658059/graphics-drawimage-in-java-is-extremely-slow-on-some-computers-yet-much-faster
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        GraphicsConfiguration config = device.getDefaultConfiguration();
        dbImage = config.createCompatibleImage(canvasWidth, canvasHeight, Transparency.TRANSLUCENT);
    
        //dbImage = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB); 
        
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
                
                startTime = System.currentTimeMillis();
                
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
                    catch (InterruptedException e) {
                        System.out.println(e.getMessage()); 
                    }
                }
            
               //System.out.println(elapsedTime);
                
            }
        }
        
    }

    
    public void renderScene(){
        
        
        GameData.getInstance().lock.lock();
        try {
            
            //create the graphics object from the offscreen image
            Graphics2D g2 = (Graphics2D) dbImage.getGraphics();
            
            //clear
            g2.fillRect(0,0,dbImage.getWidth(null), dbImage.getHeight(null));
            
            //initialize a new quad tree
            QuadTree nextQuadTree = new QuadTree(0, 0, 9999.0, 9999.0);
            
            
            //since we are on a seperate thread we need to sync the object list
            //synchronized (GameData.getInstance().gameObjects){
                try {
                    //loop over every game object
                    RenderableObject[] objects = (RenderableObject[]) GameData.getInstance().gameObjects.toArray(new RenderableObject[GameData.getInstance().gameObjects.size()]);
                    for(int i=0; i<objects.length; i++){
                        
                        RenderableObject obj = (RenderableObject)objects[i];
                        
                        nextQuadTree.set(obj.getBoundingBox().getX(), obj.getBoundingBox().getY(), obj);
                        
                        //call render
                        obj.render(g2, GameData.getInstance().viewport);
                        
                        //call update, this advances state of any animations
                        obj.update();
                    }
                }
                catch(Exception e) {
                    System.out.println(e.getMessage()); 
                }
            //}
            
            
            quadTree = nextQuadTree;
            
            
            
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
        finally {
            GameData.getInstance().lock.unlock();
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
