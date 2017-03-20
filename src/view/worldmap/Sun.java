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

public class Sun extends StarMap{
    
    private ArrayList<ActionListener> listeners; //action listeners
    
    public Sun(Point loc, int width, int height) {
        //call superclass constructor
        super(loc);
        //initialize listeners list
        listeners = new ArrayList();
        
        //update bounding box for the object
        super.boundingBox = new Rectangle(MainWindow.getInstance().getWidth()/12, MainWindow.getInstance().getHeight()/2, 100, 450);
    }

    @Override
    public void update() {
        
    }
    
    public void addActionListener(ActionListener listener){
        this.listeners.add(listener);
    }
    
    @Override
    public void render(Graphics2D g2, Rectangle bounds){
        //Sun
        g2.setColor(Color.GREEN);
        //g2.drawRect(1, 70, 100, 450);
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
                GameController.getInstance().showLevel5();
            }
        }
    }

    
}
