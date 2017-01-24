package controller;

import view.GameScreen;
import view.MainMenu;

public class Game
{
    // 0 = Main Screen, 1 = Game Screen
    private int currentScreen;
    
    public Game()
    {
        currentScreen = 0;
    }
    
    public static void main(String[] args)
    {
        Game currentObject = new Game();
        currentObject.runGame();
    }
    
    public void runGame()
    {
        MainMenu menu = new MainMenu();
        GameScreen game = new GameScreen();
        
        switch(this.currentScreen)
        {
            case(0):
                menu.start();
                break;
            case(1):
                game.start();
                break;
            default:
                System.out.println("Someone forgot to get the screen!");
                break;
        }
    }
    
    public void changeGameScreen(int screen){this.currentScreen = screen;}
}
