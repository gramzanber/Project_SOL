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

public class Earth extends StarMap{
    
    private ArrayList<ActionListener> listeners; //action listeners
    GameController gameController;
    
    public Earth(Point loc, int width, int height) {
        //call superclass constructor
        super(loc);
        this.gameController = GameController.getInstance();
        
        //initialize listeners list
        listeners = new ArrayList();
        
        //update bounding box for the object
        super.boundingBox = new Rectangle((int) (MainWindow.getInstance().getWidth()/1.65), MainWindow.getInstance().getHeight()/3, 112, 130);
    }

    @Override
    public void update() {
        
    }
    
    public void addActionListener(ActionListener listener){
        this.listeners.add(listener);
    }
    
    @Override
    public void render(Graphics2D g2, Rectangle bounds){
        //Earth
        g2.setColor(Color.RED);
        //g2.drawRect(385, 175, 112, 130);
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
            gameController.showLevel1();
            }
        }
        
    }

    
}
