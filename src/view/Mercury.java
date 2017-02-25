package view;

import controller.GameController;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import controller.Main;
import java.awt.Color;
import java.awt.Graphics2D;

public class Mercury extends StarMap{
    
    private ArrayList<ActionListener> listeners; //action listeners
    GameController gameController;
    
    public Mercury(Point loc, int width, int height) {
        //call superclass constructor
        super(loc);
        this.gameController = new GameController();
        //initialize listeners list
        listeners = new ArrayList();
        
        //update bounding box for the object
        super.boundingBox = new Rectangle(170, 279, 70, 78);
    }

    @Override
    public void update() {
        
    }
    
    public void addActionListener(ActionListener listener){
        this.listeners.add(listener);
    }
    
    @Override
    public void render(Graphics2D g2, Rectangle bounds){
        //Mercury
        g2.setColor(Color.YELLOW);
        g2.drawRect(170, 279, 70, 78);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (super.boundingBox.contains(e.getPoint())){
            System.out.println("TESTED WORKED");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(Main.gameData.getShooter().getBoundingBox().intersects(boundingBox)){
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
            gameController.showLevel4();
            }
        }
    }

    
}
