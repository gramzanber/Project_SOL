package view.menus;

import controller.Main;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import view.gameobjects.RenderableObject;

/**
* The GameButton class is responsible for rendering an on-screen button to a
* Graphics2d object.
*
* @author  SATAS
* @version 1.0
* @since   2017-02-18 
*/
public class Menu extends RenderableObject {
    
    private int highlightedIndex = 0; //which button is currently highlighted
    private ArrayList<GameButton> buttons; //the list of buttons in this menu
    
    /**
    * A simple constructor 
    * 
    * @param loc The location of the button
    */
    public Menu(Point loc) {
        //call superclass constructor
        super(loc);
        
        //initialize listeners list
        buttons = new ArrayList();
    }
    
    /**
    * Remove this button from all listener lists
    */
    @Override
    public void clear(){
        super.clear();
        for(int i=0; i<buttons.size(); i++){
            Main.gamePanel.removeMouseListener(buttons.get(i));
            Main.gamePanel.removeKeyListener(buttons.get(i));
            Main.gamePanel.removeMouseMotionListener(buttons.get(i)); 
        }
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void update(){
        for(int i=0; i<buttons.size(); i++){
            buttons.get(i).update();
        }
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void render(Graphics2D g2,Rectangle viewport){
        //loop over every button
        for(int i=0; i<buttons.size(); i++){
            //update highlighted status
            if(i == highlightedIndex){
                buttons.get(i).setHighlighted(true);
            }
            else{
                buttons.get(i).setHighlighted(false);
            }
            
            //render the button
            buttons.get(i).render(g2, viewport);
        }
    }


    /**
    * Add a button to the menu
    * 
    * @param button The button to add
    */
    public void addButton(GameButton button) {
        Main.gamePanel.addMouseListener(button);
        Main.gamePanel.addKeyListener(button);
        Main.gamePanel.addMouseMotionListener(button);
        this.buttons.add(button);
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void mouseClicked(MouseEvent e) {
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

    /**
    * {@inheritDoc}
    */
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            highlightedIndex -= 1;
            if(highlightedIndex < 0){
                highlightedIndex = buttons.size()-1;
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            highlightedIndex += 1;
            if(highlightedIndex > buttons.size()-1){
                highlightedIndex = 0;
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_ENTER){
            buttons.get(highlightedIndex).select();
        }
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
        if(boundingBox.contains(e.getPoint())){
        }
    }

}
