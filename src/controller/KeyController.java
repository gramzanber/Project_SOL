/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Ayodeji
 */
public class KeyController implements KeyListener{

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                //player object movement goes here
                break;
            case KeyEvent.VK_RIGHT:
                //player object movement goes here
                break;
            case KeyEvent.VK_UP:
                //player object movement goes here
                break;
            case KeyEvent.VK_DOWN:
                //player object movement goes here
                break;
            case KeyEvent.VK_Z:
                //player action goes here (eg shoot)
                break;
            case KeyEvent.VK_X:
                //player action goes here (eg jump)
                break;
            case KeyEvent.VK_C:
                //player action goes here (eg dash)
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //player object actoin reset goes here
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                //player object stops moving
                break;
            case KeyEvent.VK_RIGHT:
                //player object stops moving
                break;
            case KeyEvent.VK_UP:
                //player object stops moving
                break;
            case KeyEvent.VK_DOWN:
                //player object stops moving
                break;
            case KeyEvent.VK_Z:
                //player action stops (eg shoot)
                break;
            case KeyEvent.VK_X:
                //player action stops (eg jump)
                break;
            case KeyEvent.VK_C:
                //player action stops (eg dash)
                break;
        }
    }
    
}
