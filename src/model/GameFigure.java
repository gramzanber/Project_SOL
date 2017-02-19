// Author:	SATAS
// Course:	SDD
// Semester:    Spring, 2017

package model;

import java.awt.Graphics2D;
import java.awt.Point;
import view.RenderableObject;

public abstract class GameFigure extends RenderableObject
{
    // public for a faster access during animation
    public float x;
    public float y;
    
    public int state;
    public static final int STATE_ALIVE = 1;
    public static final int STATE_DYING = 2;
    public static final int STATE_DESTROYED = 2;
    public static final int STATE_DONE = 0;

    public GameFigure(Point loc) {
        super(loc);
    }



    // How to render on the canvas
    //public abstract void render(Graphics2D g);

    // Changes per frame
    //public abstract void update();
    
    // Object type
    public abstract String getObjectType();
    
    // Figure "size" used to calculate score
    public abstract int getSize();
    
    // Involves Figures health
    public abstract int getHealth();
    public abstract void damageFigure();
}
