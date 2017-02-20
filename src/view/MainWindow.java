// Author:	SATAS
// Course:	SDD
// Semester:    Spring, 2017

package view;

import controller.Main;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame {
    
    public static JPanel gameStatPanel;
    public static JButton quitButton;

    public MainWindow()
    {
        //quitButton = new JButton("Quit");

        //quitButton.setFocusable(false);

        //ButtonListener buttonListener = new ButtonListener();
        //quitButton.addActionListener(buttonListener);

        //JPanel southPanel = new JPanel();
        //southPanel.add(quitButton);

        //MouseController mouseController = new MouseController();
        //Main.gamePanel.addMouseListener(mouseController);
        
        //SoundController soundController = new SoundController();
        //soundController.menuBGM();

        //KeyController keyListener = new KeyController();
        //Main.gamePanel.addKeyListener(keyListener);
        Main.gamePanel.setFocusable(true);

        Container c = getContentPane();
        c.add(Main.gamePanel, "Center");
        //c.add(southPanel, "South");
    }
}