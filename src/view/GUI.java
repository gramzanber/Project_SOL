package view;

import java.awt.HeadlessException;
import javax.swing.JFrame;

public abstract class GUI extends JFrame
{
    private final String title;
    
    public GUI()
    {
        title = "Team SATAS Awesome Project";
    }
    
    public void start() throws HeadlessException
    {
        this.loadComponents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(title);
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    abstract void loadComponents();
}
