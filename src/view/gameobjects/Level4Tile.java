package view.gameobjects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
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
public class Level4Tile extends RenderableObject {

    public static final int TILESIZE = 30;
    
    private static BufferedImage spriteSheet = null;
    private int spriteX;
    private int spriteY;
    
    private int trim; //amount to oversize tile for hiding gaps in sprite
    
    /**
    * Constructor 
    * 
    * @param loc
    */
    public Level4Tile(Point loc) {
        //call superclass constructor
        super(loc);
        solid = true;
        
        //load spritesheet if not already loaded
        if(spriteSheet == null){
            try {
                spriteSheet = ImageIO.read(getClass().getResource("/Images/level4_tiles.png"));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error: Cannot open level4_tile sprite sheet");
                System.exit(-1);
            }
        }

        spriteX = 0;
        spriteY = 0;
        trim = 0;
        
        //update bounding box for the object
        super.boundingBox = new Rectangle(loc.x, loc.y, TILESIZE, TILESIZE);
    }
    
    public void setSprite(int x, int y){
        this.spriteX = x;
        this.spriteY = y;
    }

    public void setTrim(int trim) {
        this.trim = trim;
    }
    
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void update(){
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
            Image sprite = spriteSheet.getSubimage((spriteX*34)+1, (spriteY*34)+1, 30, 30);
            
            //draw sprite
            g2.drawImage(sprite, translatedX-trim, translatedY-trim, TILESIZE+trim, TILESIZE+trim, null);
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
