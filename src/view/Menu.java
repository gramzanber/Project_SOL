package view;

import controller.ButtonListener;
import controller.Main;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
* The GameButton class is responsible for rendering an on-screen button to a
* Graphics2d object.
*
* @author  SATAS
* @version 1.0
* @since   2017-02-18 
*/
public class Menu extends RenderableObject {
    
    private int highlightedIndex = 0;
    private ArrayList<GameButton> buttons; 
    
   /**
   * A simple constructor 
   * 
   * @param text This is the text for the button.
   */
    public Menu(Point loc) {
        //call superclass constructor
        super(loc);
        
        //initialize listeners list
        buttons = new ArrayList();
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
        
        for(int i=0; i<buttons.size(); i++){
            if(i == highlightedIndex){
                buttons.get(i).setHighlighted(true);
            }
            else{
                buttons.get(i).setHighlighted(false);
            }
            
            buttons.get(i).render(g2, viewport);
        }
        
    }


    public void addButton(GameButton button) {
        Main.gamePanel.addMouseListener(button);
        Main.gamePanel.addKeyListener(button);
        Main.gamePanel.addMouseMotionListener(button);
        this.buttons.add(button);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(boundingBox.contains(e.getPoint())){
             System.out.println("clcick");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(boundingBox.contains(e.getPoint())){
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == e.VK_UP){
            highlightedIndex -= 1;
            if(highlightedIndex < 0){
                highlightedIndex = buttons.size()-1;
            }
        }
        else if(e.getKeyCode() == e.VK_DOWN){
            highlightedIndex += 1;
            if(highlightedIndex > buttons.size()-1){
                highlightedIndex = 0;
            }
        }
        else if(e.getKeyCode() == e.VK_ENTER){
            buttons.get(highlightedIndex).select();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(boundingBox.contains(e.getPoint())){
        }
    }

}
