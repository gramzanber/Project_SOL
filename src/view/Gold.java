package view;

import controller.Main;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
*
* @author  SATAS
* @version 1.0
* @since   2017-02-18 
*/
public class Gold extends Tile {


    private static BufferedImage coinSpriteSheet = null;
    private int step;
    private long lastStep;
    
    /**
    * Constructor 
    * 
    * @param loc
    */
    public Gold(Point loc) {
        //call superclass constructor
        super(loc);
        
        
        //load spritesheet if not already loaded
        if(coinSpriteSheet == null){
            try {
                coinSpriteSheet = ImageIO.read(getClass().getResource("/Images/spinning-coin-spritesheet.png"));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error: Cannot open tile sprite sheet");
                System.exit(-1);
            }
        }
        
        step = 0;
        lastStep = System.currentTimeMillis();
        this.solid = false;
        
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void update(){
        
        if(System.currentTimeMillis() - lastStep > 100){
            lastStep = System.currentTimeMillis();
            step++;
            if(step > 13){
                step = 0;
            }
        }
        
        
        if(Main.gameData.getHero().getBoundingBox().intersects(boundingBox)){
            System.out.println("collected gold!");
            Main.soundController.coinPickUp();
            
            this.clear();
            Main.gameData.gameObjects.remove(this);

        }
        
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void render(Graphics2D g2,Rectangle viewport){
        if(viewport.intersects(boundingBox)){
            
            //draw the block in relation to the viewport
            int translatedX =  (int)boundingBox.getX() - (int)viewport.getX();
            int translatedY =  (int)boundingBox.getY() - (int)viewport.getY();
            
            
            //get sprite image from sprite sheet
            Image sprite = coinSpriteSheet.getSubimage(171*step, 0, 171, 171);
            
            //draw sprite
            g2.drawImage(sprite, translatedX, translatedY, TILESIZE, TILESIZE, null);
        }
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void mouseClicked(MouseEvent e) {
        if(boundingBox.contains(e.getPoint())){
        }
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void mouseEntered(MouseEvent e) {
        if(boundingBox.contains(e.getPoint())){
        }
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
