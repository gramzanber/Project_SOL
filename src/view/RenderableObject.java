/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Main;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author sean
 */
public abstract class RenderableObject implements MouseListener,KeyListener,MouseMotionListener{
    
    protected Point loc;
    protected Rectangle boundingBox;
    
    public RenderableObject(Point loc){
        this.loc = loc;
        boundingBox = new Rectangle(loc.x, loc.y, 10, 10);
    }
    public abstract void update();
    public void render(Graphics2D g2, Rectangle viewport){
        if(viewport.contains(loc)){
            g2.drawRect(boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
        }
    }
    public void clear(){
        Main.gamePanel.removeMouseListener(this);
        Main.gamePanel.removeKeyListener(this);
        Main.gamePanel.removeMouseMotionListener(this);
    }

}
