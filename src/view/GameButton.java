package view;

import controller.Main;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class GameButton extends RenderableObject {
    
    private String text; //this is the text of the button
    private Font font; //the font for drawing the text
    private Color color; //he color of the text
    private boolean mouseHover,highlighted = false;
    private ArrayList<ActionListener> listeners; //action listeners for this button
    
    //for adding a delay effect when the button is clicked
    private int selectTime;
    private boolean selected;
    
    /**
    * Constructor 
    * 
    * @param loc
    * @param text This is the text for the button.
    */
    public GameButton(Point loc, int width, int height, String text) {
        //call superclass constructor
        super(loc);
        
        //update bounding box for the object
        this.boundingBox = new Rectangle(loc.x, loc.y, width, height);
        
        //set a default font and color for the text
        font = new Font("TimesRoman", Font.PLAIN, 15);
        color = Color.RED;
        
        //for the delay effect when a button is clicked
        selected = false;
        selectTime = 20;
        
        //initialize listeners list
        listeners = new ArrayList();
        
        //set text
        this.text = text;
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void update(){
        
        //for the delay effect 
        if(selectTime == 0){
            activate();
        }
        if(selected){
            selectTime = selectTime - 1;
            System.out.println(selectTime);
        }
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void render(Graphics2D g2,Rectangle viewport){
        
        //set the font and color
        g2.setFont(font);
        g2.setColor(color);
        
        //calculate the width of the text for centering
        FontMetrics fm = g2.getFontMetrics();
        int strWidth = fm.stringWidth(text);
        int strHeight = (int)fm.getStringBounds(text, g2).getHeight();
         
        //calculate the x and y to draw the text centered in the button
        int strX = (int)(boundingBox.x + (boundingBox.getWidth() / 2) - strWidth/2);
        int strY = (int)(boundingBox.y + (boundingBox.getHeight() / 2) + (strHeight/2));
        
        //draw the text
        g2.drawString(text, strX, strY);
         
        //draw a rectangle around the button
        if(mouseHover || highlighted){
            g2.drawRect(boundingBox.x, boundingBox.y, (int)boundingBox.getWidth(), (int)boundingBox.getHeight());
        }
        
    }
    
    /**
    * This method is called whenever the button is clicked or selected. 
    * Notify all listeners
    */
    public void select(){
        this.selected = true;
           
        //grab current background
        Background background = null;
        for(int i=0; i<Main.gameData.gameObjects.size(); i++){
            if(Main.gameData.gameObjects.get(i).getClass() == Background.class){
                background = (Background)Main.gameData.gameObjects.get(i);
            }
        }

        Main.gameController.clear();

        Main.gameData.addGameObject(background);
        Main.gameData.addGameObject(this);

    }
    
    /**
    * This method is called whenever the button is clicked or selected. 
    * Notify all listeners
    */
    public void activate(){
        ActionEvent e = new ActionEvent(this, 1, "select");
        for(int i=0; i<listeners.size(); i++){
            listeners.get(i).actionPerformed(e);
        }
        Main.soundController.selectConfirm();
    }

    /**
    * Set the button text
    * @param text The text
    */
    public void setText(String text) {
        this.text = text;
    }

    /**
    * Update the font
    * 
    * @param font The new font to use
    */
    public void setFont(Font font) {
        this.font = font;
    }
    
    /**
    * Mark this button as highlighted in a menu,
    * This will case it to be selected when the enter key is pressed
    * 
    * @param highlighted Is this button highlighted or not
    */
    void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }


    /**
    * Add an action listener for this button
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
    public void mouseClicked(MouseEvent e) {
        if(boundingBox.contains(e.getPoint())){
           this.select();
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
        this.mouseHover = false;
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
        if(boundingBox.contains(e.getPoint())){
            this.mouseHover = true;
        }
        else{
            this.mouseHover = false;
        }
    }
}
