package view;

import controller.Main;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
* Render a hero object to the screen.
*
* @author  SATAS
* @version 1.0
* @since   2017-02-18 
*/
public class Hero extends Actor {
    
    private Image heroImage;
    private Rectangle viewportMain;
    
    /**
    * Constructor 
    * 
    * @param loc
    * @param width
    * @param height
    */
    public Hero(Point loc, int width, int height) {
        //call superclass constructor
        super(loc);
        
        //update bounding box for the object
        super.boundingBox = new Rectangle(loc.x, loc.y, width, height);
        
        heroImage = null;
        try {
            heroImage = ImageIO.read(getClass().getResource("/Images/robot_9_right-psd.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open hero.png");
            System.exit(-1);
        }
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void update(){
      super.update();
    }
    
    // Tyrel wrote this method
    @Override
    public void mouseClicked(MouseEvent e)
    {
        int border = 2;
        int translatedX =  ((int)boundingBox.getX() - (int)viewportMain.getX())-(int)boundingBox.getWidth()-border*2;
        int translatedY =  ((int)boundingBox.getY() - (int)viewportMain.getY())-(int)boundingBox.getHeight()-border*2;
        if(SwingUtilities.isRightMouseButton(e))
        {
            Missile m = new Missile((int)boundingBox.getX(), (int)boundingBox.getY());
            synchronized (Main.gameData.gameObjects) { Main.gameData.addGameObject(m); }
        }
        else if(SwingUtilities.isLeftMouseButton(e))
        {
            Missile m = new Missile(translatedX, translatedY);
            System.out.println("X:" + translatedX + " Y:" + translatedY);
            synchronized (Main.gameData.gameObjects) { Main.gameData.addGameObject(m); }
        }
        else { System.out.printf("Mouse click error, Package: Controller; Class: view.Hero.java"); }
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void render(Graphics2D g2,Rectangle viewport)
    {
        if(viewport.contains(boundingBox.getLocation()))
        {
            this.viewportMain = viewport;
            //draw in relation to the viewport
            int translatedX =  (int)boundingBox.getX() - (int)viewport.getX();
            int translatedY =  (int)boundingBox.getY() - (int)viewport.getY();

            //right now just draw a filled rectangle as a placeholder
            //TODO: implement sprite
            int border = 2;
            //g2.setColor(Color.GRAY);
            //g2.fillRect(translatedX, translatedY, (int)boundingBox.getWidth(), (int)boundingBox.getHeight());
            //g2.setColor(Color.YELLOW);
            //g2.fillRect(translatedX+border, translatedY+border, (int)boundingBox.getWidth()-border*2, (int)boundingBox.getHeight()-border*2);
            
            g2.drawImage(heroImage, translatedX, translatedY, (int)boundingBox.getWidth()-border*2, (int)boundingBox.getHeight()-border*2, null); 
        }
    }
}
