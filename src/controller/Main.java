package controller;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.GameData;
import view.MainWindow;




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
    
    public static JPanel gamePanel; //the canvas for rendering
    public static GameController gameController; //for adding game objects
    public static GameData gameData; //data model, game stats and object list
    public static Animator animator; //rendering engine and main game loop
    public static MainWindow game; //gui window
    public static SoundController soundController;//all sounds controlled here
    public static int WIN_WIDTH = 700; //the width of the gui window
    public static int WIN_HEIGHT = 600; //the height of the gui window

   /**
   * This is the main entry point for the application
   * 
   * @param args Array of command line arguments
   */
    public static void main(String[] args)
    {
        //initialize all global objects
        animator = new Animator();
        gameController = new GameController();
        gameData = new GameData();
        gamePanel = new JPanel(); 
        soundController = new SoundController();

        
        //build and display the gui
        game = new MainWindow();
        game.setTitle("SOL - SDD SATAS");
        game.setSize(WIN_WIDTH, WIN_HEIGHT);
        game.setLocation(100, 0);
        //game.setResizable(false);// Window size cannot change
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);
        
        
        gamePanel.addKeyListener(gameController);
        gamePanel.addComponentListener(gameController);
        
        
        //initialize the viewport
        //gameData.viewport = new Rectangle(gamePanel.getWidth(),gamePanel.getHeight());
        
        //initially show the main menu screen
        gameController.showMainMenu();
        
        // Start rendering engine
        new Thread(animator).start();
    }
}
