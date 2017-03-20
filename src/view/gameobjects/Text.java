package view.gameobjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
* The Text class is responsible for rendering text to a
* Graphics2d object.
*
* @author  SATAS
* @version 1.0
* @since   2017-02-18 
*/
public class Text extends RenderableObject {
    
    private String text; //this is the text to display
    private Font font; //the font for drawing the text
    private Color color; //he color of the text
    private FontMetrics fm; //for calculating the width of the string
    private Point loc; //the location of the text
    private boolean centered; //should this text be centered at loc
    
    
    /**
    * A simple constructor 
    */
    public Text(Point loc, String text, Font font, boolean centered) {
        super(loc);
        this.font = font;
        color = Color.RED;
        this.centered = centered;
        this.loc = loc;
        setText(text);
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
    public void render(Graphics2D g2, Rectangle viewport){
        //set the font and color
        g2.setFont(font);
        g2.setColor(color);
        
        //calculate the x and y to draw the text centered in the bounding box
        int strX = (int)(boundingBox.x + (boundingBox.getWidth() / 2) - getWidth()/2);
        int strY = (int)(boundingBox.y + (boundingBox.getHeight() / 2) + (getHeight()/2) - 5);
        
        //draw text
        g2.drawString(text, strX, strY);
        
    }

        public String getText() {
        return text;
    }
        
        
    public int getWidth(){
        return fm.stringWidth(text);
    }
    
    
    public int getHeight(){
        return (int)fm.getHeight();
    }

    private void updateFontMetrics(){
        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.setFont(font);
        fm = g2.getFontMetrics();
    }
    
    public void setText(String text) {
        this.text = text;
        updateFontMetrics();
        if(centered){
            this.boundingBox = new Rectangle(loc.x - getWidth()/2, loc.y, getWidth(), getHeight());
        }
        else{
            this.boundingBox = new Rectangle(loc.x, loc.y, getWidth(), getHeight());
        }
    }

    public void setFont(Font font) {
        this.font = font;
        updateFontMetrics();
        if(centered){
            this.boundingBox = new Rectangle(loc.x - getWidth()/2, loc.y, getWidth(), getHeight());
        }
        else{
            this.boundingBox = new Rectangle(loc.x, loc.y, getWidth(), getHeight());
        }
    }


    public void setColor(Color color) {
        this.color = color;
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
