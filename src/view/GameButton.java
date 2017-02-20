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
import view.RenderableObject;

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
    private int width;
    private int height;
    private boolean mouseHover,highlighted = false;
    private ArrayList<ActionListener> listeners; //action listeners for this button
    
   /**
   * A simple constructor 
   * 
   * @param width This will be the width of the button
   * @param height This will be the height of the button
   * @param text This is the text for the button.
   */
    public GameButton(Point loc, int width, int height, String text) {
        //call superclass constructor
        super(loc);
        
        
        this.width = width;
        this.height = height;
        
        this.boundingBox = new Rectangle(loc.x, loc.y, width, height);
        
        //set a default font and color for the text
        font = new Font("TimesRoman", Font.PLAIN, 15);
        color = Color.RED;
        
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
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void render(Graphics2D g2,Rectangle viewport){
        
        //set the font and color
        g2.setFont(getFont());
        g2.setColor(getColor());
        
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
    
    public void select(){
        ActionEvent e = new ActionEvent(this, 1, "select");
        for(int i=0; i<listeners.size(); i++){
            listeners.get(i).actionPerformed(e);
        }
        Main.soundController.selectConfirm();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void addActionListener(ActionListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(boundingBox.contains(e.getPoint())){
             this.select();
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
        this.mouseHover = false;
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
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(boundingBox.contains(e.getPoint())){
            this.mouseHover = true;
        }
        else{
            this.mouseHover = false;
        }
    }

    void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

}
