/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gameobjects;

import controller.AnimationController;
import controller.PhysicsController;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author alebel
 */
public abstract class Weapon extends RenderableObject{
    
    private PhysicsController pyc;
    private AnimationController animationController;
    

    public Weapon(float sx, float sy) {
        super(new Point((int)sx, (int)sy));
        pyc = new PhysicsController(this);
        super.boundingBox = new Rectangle(loc.x, loc.y, 20, 10);
    }
    
    
    
}
