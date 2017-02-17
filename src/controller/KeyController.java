// Author:	SATAS
// Course:	SDD
// Semester:    Spring, 2017

package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.Shooter;

public class KeyController implements KeyListener
{
    
    public static boolean enterKeyPressed = false;
    @Override
    public void keyPressed(KeyEvent e) 
    {   
        SoundController soundController = new SoundController();
        if(Main.animator.isAtMainMenu())
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_UP:
                    Main.gamePanel.mainMenuSelectOption(1);
                    break;
                case KeyEvent.VK_DOWN:
                    Main.gamePanel.mainMenuSelectOption(-1);
                    break;
                case KeyEvent.VK_ENTER:
                    this.enterKeyPressed = true;
                    try{
                        sleepNow();
                    }
                    catch(Exception a){
                        
                    }
                    switch(Main.gamePanel.getMainMenuSelection())
                    {
                        case 0:
                            soundController.selectConfirm();
                            Main.animator.startGame();
                            break;
                        case 1:
                            soundController.selectConfirm();
                            Main.animator.scoreScreen();
                            break;
                        case 2:
                            if (Main.animator.running) { Main.animator.running = false; }
                            else { System.exit(0); }
                            break;
                    }
                    break;
            }
            if(getEnterKeyPressed()){
                enterKeyPressed = false;
            }
             
        }
        else if(Main.animator.isAtScoreScreen())
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_ENTER:
                    Main.animator.mainMenu();
                    break;
            }
        }
        else
        {
            Shooter shooter = (Shooter) Main.gameData.friendFigures.get(0);
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_LEFT:
                    shooter.translate(-10, 0);
                    break;
                case KeyEvent.VK_RIGHT:
                    shooter.translate(10, 0);
                    break;
                case KeyEvent.VK_UP:
                    shooter.translate(0, -10);
                    break;
                case KeyEvent.VK_DOWN:
                    shooter.translate(0, 10);
                    break;
                case KeyEvent.VK_ESCAPE:
                    System.out.println("Implement pause menu");
                    if (Main.animator.running) { Main.animator.running = false; }
                    else { System.exit(0); }
                    break;
                case KeyEvent.VK_ENTER:
                    System.out.println("Implement Chat box");
                    break;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    public static boolean getEnterKeyPressed(){
        return enterKeyPressed;
    }
    
    public void sleepNow() throws InterruptedException{
        Thread.sleep(1000);
    }
}


