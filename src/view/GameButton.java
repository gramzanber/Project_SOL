/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Main;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import static view.GamePanel.height;
import static view.GamePanel.width;

/**
 *
 * @author sean
 */
public class GameButton extends RenderableObject {
    
    private String text;
    
    public GameButton(int x, int y, int width, int height, String text) {
        super(x, y, width, height);
        this.text = text;
    }
    public void update(){
    }
    public void render(Graphics2D g2, Rectangle viewport){
         g2.setFont(new Font("TimesRoman", Font.BOLD, 44));
         g2.setColor(Color.RED);
         
         FontMetrics fm = g2.getFontMetrics();
         int strWidth = fm.stringWidth(text);
         int strHeight = (int)fm.getStringBounds(text, g2).getHeight();
         
         double x = boundingBox.getX() + (boundingBox.getWidth() / 2) - strWidth/2;
         double y = boundingBox.getY() + (boundingBox.getHeight() / 2) + (strHeight/3);
         g2.drawString(text, (int)x, (int)y);
         
         g2.drawRect((int)boundingBox.getX(), (int)boundingBox.getY(), (int)boundingBox.getWidth(), (int)boundingBox.getHeight());
         
    }
    
}
