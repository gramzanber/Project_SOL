/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author sean
 */
public abstract class RenderableObject {
    
    protected Rectangle boundingBox;
    
    public RenderableObject(int x, int y, int width, int height){
        this.boundingBox = new Rectangle(width, height, x, y);
    }
    public abstract void update();
    public void render(Graphics2D g2, Rectangle viewport){
        if(viewport.contains(boundingBox)){
            g2.drawRect(boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
        }
    }
}
