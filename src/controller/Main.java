package controller;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
import javax.swing.JPanel;
import view.BufferedImageLoader;
import view.swingcomponents.MainWindow;




/**
* Main class, all shared objects are accessed through this class.
*
* @author  SATAS
* @version 1.0
* @since   2017-02-20 
*/
public class Main
{
    
    static GraphicsDevice device = GraphicsEnvironment
        .getLocalGraphicsEnvironment().getScreenDevices()[0];
    
    public static int WIN_WIDTH = 700; //the width of the gui window
    public static int WIN_HEIGHT = 600; //the height of the gui window
    
    public static boolean debug = false;
   /**
   * This is the main entry point for the application
   * 
   * @param args Array of command line arguments
   */
    public static void main(String[] args)
    {
        //build and display the gui
        MainWindow.getInstance().setVisible(true);

        
        //initialize the viewport
        //gameData.viewport = new Rectangle(gamePanel.getWidth(),gamePanel.getHeight());
        
        //initially show the main menu screen
        GameController.getInstance().resetViewport();
        GameController.getInstance().showMainMenu();
        
        // Start rendering engine
        new Thread(Animator.getInstance()).start();
    }
}
