
package controller;

import java.awt.Point;
import java.awt.image.BufferedImage;
import model.GameData;
import model.ImageLibrary;
import view.gameobjects.ExplosionEffect;
import view.gameobjects.SunLargeEnemyLeftDeathEffect;
import view.gameobjects.SunLargeEnemyRightDeathEffect;

public class AnimationController {

    public static enum Mode{
        AUTO, MANUAL
    }
    
    private int index;
    private int fps;
    private long lastStep;
    private String spriteSheet;
    private Mode m;
    private int loopCount;
    
    public AnimationController(Mode m, String spriteSheet){
        this.m = m;
        this.spriteSheet = spriteSheet;
        
        this.fps = fps;
        index = 0;
        loopCount = 0;
        this.lastStep = 0;
        fps = ImageLibrary.getInstance().getSprites(spriteSheet).size();
    }
    
    public void update(){
        
        //advance frame on every call to update
        if(m == Mode.MANUAL){
            index+=1;
        }
        
        //advance frame using fps, call update every render iteration
        else{
            if(System.currentTimeMillis() - lastStep > 1000/fps){
                lastStep = System.currentTimeMillis();
                index+=1;  
            }
        }
        
        //loop animation
        if(index > ImageLibrary.getInstance().getSprites(spriteSheet).size()-1){
            index = 0;
            loopCount += 1;
        }
        
    }
    
    public void setSpriteSheet(String spriteSheet){
        this.spriteSheet = spriteSheet;
    }
    
    public BufferedImage getFrame(){
        if(ImageLibrary.getInstance().getSprites(spriteSheet).size() > 0){
            return ImageLibrary.getInstance().getSprites(spriteSheet).get(index);
        }
        return null;
    }
    
    
    public static void explosionEffect(Point point) {
        GameData.getInstance().addGameObject(new ExplosionEffect(point));
    }
    
    public static void sunLargeEnemyLeftDeathEffect(Point point) {
        GameData.getInstance().addGameObject(new SunLargeEnemyLeftDeathEffect(point));
    }
    
    public static void sunLargeEnemyRightDeathEffect(Point point) {
        GameData.getInstance().addGameObject(new SunLargeEnemyRightDeathEffect(point));
    }
    
    
    public int getIndex(){       
        return index;
    }

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }
    
    public void setFrame(int index){
        this.index = index;
    }

    public int getLoopCount() {
        return loopCount;
    }
    
    
}
