/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import view.BufferedImageLoader;

/**
 *
 */
public class ImageLibrary {
    
    //singleton instance
    private static ImageLibrary instance;
    
    /**
     * Singleton class
     * 
     * @return the singleton instance of this class
     */
    public static ImageLibrary getInstance(){
        //initialize instance on first use
        if(instance == null){
            instance = new ImageLibrary();
        }
        //return the instance
        return instance;
    }

    
    private Map<String, ArrayList<BufferedImage>> library;
    
    /**
     * private constructor prevents bypassing singleton pattern
     */
    private ImageLibrary(){
        library = new HashMap();
    }
    
    public ArrayList<BufferedImage> getSprites(String spriteSheet){
        return library.get(spriteSheet);
    }
    
    
    public void loadSpriteSheet(String name, String imagePath, int spriteWidth, int spriteHeight, int vGap, int hGap){
        
        //load image
        BufferedImage spriteSheet = BufferedImageLoader.getInstance().loadImage(imagePath);
        
        int sheetWidth = spriteSheet.getWidth();
        int sheetHeight = spriteSheet.getHeight();
        
        ArrayList<BufferedImage> sprites = new ArrayList();
        
        //loop over each sprite on sheet
        for(int y = 0; y < (int)(sheetHeight/(spriteHeight+hGap)); y++ ){
            for(int x = 0; x < (int)(sheetWidth/(spriteWidth+vGap)); x++ ){
                BufferedImage sprite = spriteSheet.getSubimage(x*spriteWidth, y*spriteHeight, spriteWidth, spriteHeight);
                sprites.add(sprite);
            }
        }
        
        library.put(name, sprites);
    }
    
}
