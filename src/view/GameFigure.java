package view;

import java.awt.Point;

public abstract class GameFigure extends RenderableObject 
{
    // public for a faster access during animation
    public float x;
    public float y;
    protected float velX, velY;
    protected boolean boolEarth = true,boolMoon,boolSun,boolMercury,boolVenus;
    
    public int state;
    public static final int STATE_ALIVE = 1;
    public static final int STATE_DYING = 2;
    public static final int STATE_DESTROYED = 2;
    public static final int STATE_DONE = 0;

    public GameFigure(Point loc) {
        super(loc);
    }
    
    // Object type
    public abstract String getObjectType();
    
    // Involves Figures health
    public abstract int getHealth();
    public abstract void damageFigure();
    
    public void setVelX(int velX){
        this.velX = velX;
    }
    public void setVelY(int velY){
        this.velY = velY;
    }
    public float getVelX(){
        return velX;
    }
    public float getVelY(){
        return velY;
    }
    
}
