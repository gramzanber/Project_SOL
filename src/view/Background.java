/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Main;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author sean
 */
public class Background extends RenderableObject {
    
    private Image backgroundImage;
    private double backgroundLocation;
    
    public Background(int x, int y, int width, int height, String imagePath) {
        super(x, y, width, height);
        backgroundLocation = 0;
        try{
            this.backgroundImage = ImageIO.read(getClass().getResource(imagePath));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open background.png");
            System.exit(-1);
        }
        
    }
    public void update(){
        if(Math.abs(this.backgroundLocation) >= 350) { this.backgroundLocation = 0; }
        else { this.backgroundLocation = this.backgroundLocation - (0.1); }
    }
    public void render(Graphics2D g2, Rectangle viewport){

        
        
        //System.out.println("Size: " + backgroundImage[0].getWidth(null) + " Location: " + Math.abs(this.backgroundLocation));
        g2.drawImage(backgroundImage, (int)this.backgroundLocation, 0, backgroundImage.getWidth(null), backgroundImage.getHeight(null), null);
        
        
    }
    
}
