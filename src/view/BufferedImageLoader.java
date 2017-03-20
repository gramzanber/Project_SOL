package view;

import controller.Animator;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BufferedImageLoader {
    
    //singleton instance
    private static BufferedImageLoader instance;
    
    /**
     * Singleton class
     * 
     * @return the singleton instance of this class
     */
    public static BufferedImageLoader getInstance(){
        //initialize instance on first use
        if(instance == null){
            instance = new BufferedImageLoader();
        }
        //return the instance
        return instance;
    }
    
    /**
     * private constructor prevents bypassing singleton pattern
     */
    private BufferedImageLoader(){
        
    }
        
            
    private BufferedImage image;
    
    
    public BufferedImage loadImage(String images){
        try {
            image = ImageIO.read(getClass().getResource(images));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
