package view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BufferedImageLoader {
    
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
