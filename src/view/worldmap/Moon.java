package view.worldmap;

import controller.GameController;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics2D;
import model.GameData;
import view.swingcomponents.MainWindow;

public class Moon extends StarMap{
    
    private ArrayList<ActionListener> listeners; //action listeners
    
    public Moon(Point loc, int width, int height) {
        //call superclass constructor
        super(loc);
        //initialize listeners list
        listeners = new ArrayList();
        
        //update bounding box for the object
        super.boundingBox = new Rectangle((int) (MainWindow.getInstance().getWidth()/1.4), MainWindow.getInstance().getHeight()/4, 30, 25);
    }

    @Override
    public void update() {
        /*if(GameData.getInstance().getShooter().getBoundingBox().intersects(boundingBox)){
            System.out.println("TESTED!!");
        }*/
    }
    
    public void addActionListener(ActionListener listener){
        this.listeners.add(listener);
    }
    
    @Override
    public void render(Graphics2D g2, Rectangle bounds){
    
        //Moon
        g2.setColor(Color.BLUE);
        //g2.drawRect(503, 158, 22, 25);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (super.boundingBox.contains(e.getPoint())){
            System.out.println("TESTED WORKED");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(GameData.getInstance().getShooter().getBoundingBox().intersects(boundingBox)){
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                GameController.getInstance().showLevel2();
            }
        }
    }

    
}
