package view;

import controller.Main;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
* Render a goal to the screen.
* When the hero touches the goal the level is completed
* 
* @author  SATAS
* @version 1.0
* @since   2017-02-18 
*/
public class Goal extends RenderableObject {
    
    private ArrayList<ActionListener> listeners; //action listeners
    
    /**
    * Constructor 
    * 
    * @param loc
    */
    public Goal(Point loc, int width, int height) {
        //call superclass constructor
        super(loc);
        
        //initialize listeners list
        listeners = new ArrayList();
        
        solid = false;
        
        //update bounding box for the object
        super.boundingBox = new Rectangle(loc.x, loc.y, width, height);
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void update(){
        
        if(Main.gameData.getHero().getBoundingBox().intersects(boundingBox)){
            System.out.println("Goal reached!");
            
            ActionEvent e = new ActionEvent(this, 1, "select");
            for(int i=0; i<listeners.size(); i++){
                listeners.get(i).actionPerformed(e);
            }
            Main.soundController.selectConfirm();

        }
        
    }
    
    /**
    * Add an action listener
    * 
    * @param listener The action listener to add.
    */
    public void addActionListener(ActionListener listener) {
        this.listeners.add(listener);
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void render(Graphics2D g2,Rectangle viewport){
        if(viewport.contains(boundingBox.getLocation())){
            
            //draw the block in relation to the viewport
            int translatedX =  (int)boundingBox.getX() - (int)viewport.getX();
            int translatedY =  (int)boundingBox.getY() - (int)viewport.getY();
            
            int border = 2;
            g2.setColor(Color.GRAY);
            g2.fillRect(translatedX, translatedY, (int)boundingBox.getWidth(), (int)boundingBox.getHeight());
            g2.setColor(Color.GREEN);
            g2.fillRect(translatedX+border, translatedY+border, (int)boundingBox.getWidth()-border*2, (int)boundingBox.getHeight()-border*2);
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
