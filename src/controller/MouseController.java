// Author:	SATAS
// Course:	SDD
// Semester:    Spring, 2017
package controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import model.Laser;
import model.Missile;
import model.Shooter;
import view.GamePanel;

public class MouseController extends MouseAdapter {

    private int px;
    private int py;

    @Override
    public void mousePressed(MouseEvent me)
    {
        px = me.getX();
        py = me.getY();
        SoundController soundController = new SoundController();
        
        if(Main.animator.isAtMainMenu()){//mouse clicks set to location of start highscore and quit
            if(px >= (GamePanel.width/2) - 50 && py >=(GamePanel.height/2)-75 && py <= (GamePanel.height/2)-15){
                //System.out.println("START");
                soundController.selectConfirm();
                Main.animator.startGame();
            }else if(px >= (GamePanel.width/2) - 50 && py >=(GamePanel.height/2)-10 && py <= (GamePanel.height/2)+15){
                //System.out.println("HIGH 123");
                soundController.selectConfirm();
                Main.animator.scoreScreen();
            }else if(px >= (GamePanel.width/2) - 50 && py >=(GamePanel.height/2)+25 && py <= (GamePanel.height/2)+50){
                //System.out.println("Help ");
                soundController.selectConfirm();
                Main.animator.helpScreen();
            }else if(px >= (GamePanel.width/2) - 50 && py >=(GamePanel.height/2)+60){
                //System.out.println("QUIT ");
                soundController.selectConfirm();
                if (Main.animator.running) { Main.animator.running = false; }
                else { System.exit(0); }
            }
        }else if(Main.animator.isAtHelpScreen()){
            if(px >= (GamePanel.width/2) - 20 && py >=(GamePanel.height/2)+25 && py <= (GamePanel.height/2)+60){
                soundController.selectConfirm();
                Main.animator.mainMenu();
            }
        }else if(Main.animator.isAtScoreScreen()){
            if(px >= (GamePanel.width/2) - 40 && py >=(GamePanel.height/2)+25 && py <= (GamePanel.height/2)+60){
                soundController.selectConfirm();
                Main.animator.mainMenu();
            }
        }

        Shooter shooter = (Shooter) Main.gameData.friendFigures.get(0);
        
        if(SwingUtilities.isRightMouseButton(me))
        {
            Missile m = new Missile(shooter.getXofMissileShoot(), shooter.getYofMissileShoot(), px, py, Color.RED);
            synchronized (Main.gameData.friendFigures) { Main.gameData.friendFigures.add(m); }
        }
        else if(SwingUtilities.isLeftMouseButton(me))
        {
            Laser m = new Laser(shooter.getXofMissileShoot(), shooter.getYofMissileShoot(), Color.RED);
            synchronized (Main.gameData.friendFigures) { Main.gameData.friendFigures.add(m); }
        }
        else { System.out.printf("Mouse click error, Package: Controller; Class: MouseController.java \n"); }
    }
}
