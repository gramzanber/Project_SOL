// Author:	SATAS
// Course:	SDD
// Semester:    Spring, 2017

package view.swingcomponents;

import controller.Animator;
import controller.GameController;
import controller.Main;
import static controller.Main.WIN_HEIGHT;
import static controller.Main.WIN_WIDTH;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame {
    
    //singleton instance
    private static MainWindow instance;
    
    /**
     * Singleton class
     * 
     * @return the singleton instance of this class
     */
    public static MainWindow getInstance(){
        //initialize instance on first use
        if(instance == null){
            instance = new MainWindow();
        }
        //return the instance
        return instance;
    }
    
    
    private JPanel gamePanel; //the canvas for rendering
    
    public static JPanel gameStatPanel;
    public static JButton quitButton;

    /**
     * private constructor prevents bypassing singleton pattern
     */
    private MainWindow()
    {
        
        this.setTitle("SOL - SDD SATAS");
        this.setSize(WIN_WIDTH, WIN_HEIGHT);
        this.setLocation(100, 0);
        //game.setResizable(false);// Window size cannot change
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        gamePanel = new JPanel();
        gamePanel.addKeyListener(GameController.getInstance());
        gamePanel.addComponentListener(GameController.getInstance());
        gamePanel.setFocusable(true);
        this.getContentPane().add(gamePanel, "Center");
        
    }
    public JPanel getGamePanel(){
        return gamePanel;
    }
}