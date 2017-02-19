// Author:	SATAS
// Course:	SDD
// Semester:    Spring, 2017

package controller;

import java.awt.Rectangle;
import javax.swing.JFrame;
import model.GameData;
import view.GamePanel;
import view.MainWindow;
import model.Scoretracker;

public class Main
{
    public static GamePanel gamePanel;
    public static GameController gameController;
    public static GameData gameData;
    public static Animator animator;
    public static Scoretracker score;
    public static MainWindow game;
    public static SoundController soundController;

    public static int WIN_WIDTH = 700;
    public static int WIN_HEIGHT = 600;

    public static void main(String[] args)
    {
        animator = new Animator();
        gameController = new GameController();
        gameData = new GameData();
        gamePanel = new GamePanel();
        score = new Scoretracker();
        soundController = new SoundController();

        game = new MainWindow();
        game.setTitle("SOL - SDD SATAS");
        game.setSize(WIN_WIDTH, WIN_HEIGHT);
        game.setLocation(100, 0);
        game.setResizable(false);                                               // Window size cannot change
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);
        
        gameData.viewport = new Rectangle(gamePanel.getWidth(),gamePanel.getHeight());
        
        //initially show the main menu
        gameController.showMainMenu();
        
        // Start animation
        new Thread(animator).start();
    }
}
