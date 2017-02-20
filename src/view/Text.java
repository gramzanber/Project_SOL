/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
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
 * @author sean
 */
public class Text extends RenderableObject {
    
    private String text; //this is the text to display
    private Font font; //the font for drawing the text
    private Color color; //he color of the text
    private FontMetrics fm;
    private Point loc;
    private boolean centered;
    
    public Text(Point loc, String text, Font font, boolean centered) {
        super(loc);
        this.font = font;
        color = Color.RED;
        this.centered = centered;
        
        this.loc = loc;
        setText(text);
        
        
        
    }
    public void update(){
    }
    public void render(Graphics2D g2, Rectangle viewport){
        //set the font and color
        g2.setFont(getFont());
        g2.setColor(getColor());
        
        //calculate the x and y to draw the text centered in the bounding box
        int strX = (int)(boundingBox.x + (boundingBox.getWidth() / 2) - getWidth()/2);
        int strY = (int)(boundingBox.y + (boundingBox.getHeight() / 2) + (getHeight()/2) - 5);
        
        //draw text
        g2.drawString(text, strX, strY);
        
        //g2.drawRect(boundingBox.x, boundingBox.y, (int)boundingBox.getWidth(), (int)boundingBox.getHeight());
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
        g2.setFont(getFont());
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

    public Font getFont() {
        return font;
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
}
