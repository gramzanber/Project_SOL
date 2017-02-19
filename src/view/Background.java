/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Main;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
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
    private int width;
    private int height;
    boolean stretch;
    boolean scroll;
    
    public Background(String imagePath, boolean stretch, boolean scroll) {
        super(new Point(0, 0));
        this.stretch = stretch;
        this.scroll = scroll;
        backgroundLocation = 0;
        try{
            this.backgroundImage = ImageIO.read(getClass().getResource(imagePath));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open background.png");
            System.exit(-1);
        }
        
    }
    public void update(){
        if(scroll){
            if(Math.abs(this.backgroundLocation) >= 350) { 
                this.backgroundLocation = 0; 
            }
            else { 
                this.backgroundLocation = this.backgroundLocation - (0.1); 
            }  
        }
    }
    public void render(Graphics2D g2, Rectangle viewport){

        int width = backgroundImage.getWidth(null);
        int height = backgroundImage.getHeight(null);
        if(stretch){
            width = (int)viewport.getWidth();
            height = (int)viewport.getHeight();
        }
        
        //System.out.println("Size: " + backgroundImage[0].getWidth(null) + " Location: " + Math.abs(this.backgroundLocation));
        g2.drawImage(backgroundImage, (int)this.backgroundLocation, 0, width, height, null);
        
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
