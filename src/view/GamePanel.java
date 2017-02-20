// Author:	SATAS
// Course:	SDD
// Semester:    Spring, 2017

package view;
import controller.Main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel
{
    // Size of the canvas - determined at runtime once rendered
    public static int width;
    public static int height;

    // Off screen rendering
    private Graphics2D g2;
    private Image dbImage = null;   // Double buffer image
    private Image backgroundImage[];                                       // Double buffer image
    private int backgroundLocation;
    private int mainMenuHover;
    private boolean enterKeyPressed = false;
    
    public GamePanel()
    {
        this.backgroundImage = new Image[3];
        try
        {
            backgroundImage[0] = ImageIO.read(getClass().getResource("/Images/corona_bk.png"));
            backgroundImage[1] = ImageIO.read(getClass().getResource("/Images/redeclipse_bk.png"));
            backgroundImage[2] = ImageIO.read(getClass().getResource("/Images/world_map_1.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open background.png");
            System.exit(-1);
        }
        this.backgroundLocation = 0;
        this.mainMenuHover = 0;
    }
    
    public void menuRender()
    {
        //The traditional notion of double-buffering in Java applications is 
        //fairly straightforward: create an offscreen image, draw to that image 
        //using the image's graphics object, then, in one step, call drawImage 
        //using the target window's graphics object and the offscreen image.
        //this will reduce flicker because the slower process of rendering each 
        //object is done off screen
        
        //make sure the off screen image is defined
        if (dbImage == null) {
            // Creates an off-screen drawable image
            dbImage = createImage(this.getSize().width, this.getSize().height);
            if (dbImage == null) {
                System.out.println("Critical Error: dbImage is null");
                System.exit(1);
            }
        }
        //create graphics2d object from off screen image
        g2 = (Graphics2D) dbImage.getGraphics();
        
        //draw everything on dbimage
        
        //enterKeyPressed = KeyController.getEnterKeyPressed();
        width = getSize().width;
        height = getSize().height;
        
        //g2 = (Graphics2D)this.getGraphics();
        g2.setColor(Color.RED);
        
        if(Math.abs(this.backgroundLocation) >= 350) { this.backgroundLocation = 0; }
        else { this.backgroundLocation = this.backgroundLocation - 5; }
        //System.out.println("Size: " + backgroundImage[0].getWidth(null) + " Location: " + Math.abs(this.backgroundLocation));
        g2.drawImage(backgroundImage[0], this.backgroundLocation, 0, backgroundImage[0].getWidth(null), backgroundImage[0].getHeight(null), null);
        
        g2.setFont(new Font("TimesRoman", Font.BOLD, 75));
        String gameNameString = "SOL";
        g2.drawString(gameNameString, (width / 2) - 80, 100);
        
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        
        // Game Information
        String informationString = "Start";
        String levelString = "High Score";
        String scoreString = String.format("Controls");
        String quitString = String.format("Quit");
        
        if(!enterKeyPressed){
            g2.drawString(informationString, (width / 2) - 50, (height / 2) - 50);
            // Level Information
            g2.drawString(levelString, (width / 2) - 50, (height / 2));
            // Score
            g2.drawString(scoreString, (width / 2) - 50, (height / 2) + 50);
            //help screen
            g2.drawString(quitString, (width / 2) - 50, (height / 2) + 100);
            //animateButton(g2);
            // Show selection
            if(this.mainMenuHover == 0) { g2.drawRect((width / 2) - 60, (height / 2) - 70, 120, 30); }
            else if(this.mainMenuHover == 1) { g2.drawRect((width / 2) - 60, (height / 2) - 20, 120, 30); }
            else if(this.mainMenuHover == 2) { g2.drawRect((width / 2) - 60, (height / 2) + 30, 120, 30); }
            else if(this.mainMenuHover == 3) { g2.drawRect((width / 2) - 60, (height / 2) + 80, 120, 30); }
        }
        else{
            if(this.mainMenuHover == 0) { 
                g2.drawString(informationString, (width / 2) - 50, (height / 2) - 50);
                g2.drawRect((width / 2) - 60, (height / 2) - 70, 120, 30); 
            }
            else if(this.mainMenuHover == 1) { 
                g2.drawString(levelString, (width / 2) - 50, (height / 2));
                g2.drawRect((width / 2) - 60, (height / 2) - 20, 120, 30); 
            }
            else if(this.mainMenuHover == 2) { 
                 g2.drawString(scoreString, (width / 2) - 50, (height / 2) + 50);
                g2.drawRect((width / 2) - 60, (height / 2) + 30, 120, 30); 
            }
            else if(this.mainMenuHover == 3) { 
                g2.drawString(quitString, (width / 2) - 50, (height / 2) + 100);
                g2.drawRect((width / 2) - 60, (height / 2) + 80, 120, 30); 
            }
            
        }

        //draw the dbimage to the panel
        this.getGraphics().drawImage(dbImage, 0, 0, this);
        
    }
    
    public void worldRender() {
        
//        //The traditional notion of double-buffering in Java applications is 
//        //fairly straightforward: create an offscreen image, draw to that image 
//        //using the image's graphics object, then, in one step, call drawImage 
//        //using the target window's graphics object and the offscreen image.
//        //this will reduce flicker because the slower process of rendering each 
//        //object is done off screen
//        
//        //make sure the off screen image is defined
//        if (dbImage == null) {
//            // Creates an off-screen drawable image
//            dbImage = createImage(this.getSize().width, this.getSize().height);
//            if (dbImage == null) {
//                System.out.println("Critical Error: dbImage is null");
//                System.exit(1);
//            }
//        }
//        //create graphics2d object from off screen image
//        g2 = (Graphics2D) dbImage.getGraphics();
//        
//        //draw everything on dbimage
//        
//        width = getSize().width;
//        height = getSize().height;
//        
//        //g2 = (Graphics2D)this.getGraphics();
//        g2.setColor(Color.WHITE);
//        
//        g2.drawImage(backgroundImage[2], 0, 0,getWidth(), getHeight(), this);
//        
//        g2.setFont(new Font("TimesRoman", Font.BOLD, 30));
//        String gameNameString = "SYSTEM MAP - Select assault point";
//        g2.drawString(gameNameString, (width / 2) - 250, 40);
//        
//        //draw rect arround planets 
//        g2.drawRect((width / 2) - 70, (height / 2) + 30, 120, 30);
//        g2.drawRect((width / 2) - 70, (height / 2) + 30, 120, 30);
//        g2.drawRect((width / 2) - 70, (height / 2) + 30, 120, 30);
//        g2.drawRect((width / 2) - 70, (height / 2) + 30, 120, 30);
//        g2.drawRect((width / 2) - 70, (height / 2) + 30, 120, 30);
//        
//        //render spaceship
//        width = getSize().width;
//        height = getSize().height;
//        
//        if (Main.animator.running)
//        { 
//            synchronized (Main.gameData.enemyFigures)
//            {
//                try{
//            Main.gameData.friendFigures.stream().forEach((f) -> {
//                    f.render(g2);
//                });
//            }
//            catch(Exception e) { System.out.println("GamePanel Line 82: " + e.getMessage()); }
//            }
//
//        }
//        
//        //draw the dbimage to the panel
//        this.getGraphics().drawImage(dbImage, 0, 0, this);
//        
    }
    
    public void helpScreenRender() {
        
        //The traditional notion of double-buffering in Java applications is 
        //fairly straightforward: create an offscreen image, draw to that image 
        //using the image's graphics object, then, in one step, call drawImage 
        //using the target window's graphics object and the offscreen image.
        //this will reduce flicker because the slower process of rendering each 
        //object is done off screen
        
        //make sure the off screen image is defined
        if (dbImage == null) {
            // Creates an off-screen drawable image
            dbImage = createImage(this.getSize().width, this.getSize().height);
            if (dbImage == null) {
                System.out.println("Critical Error: dbImage is null");
                System.exit(1);
            }
        }
        //create graphics2d object from off screen image
        g2 = (Graphics2D) dbImage.getGraphics();
        
        width = getSize().width;
        height = getSize().height;
        
        //g2 = (Graphics2D)this.getGraphics();
        g2.setColor(Color.RED);
        
        if(Math.abs(this.backgroundLocation) >= 350) { this.backgroundLocation = 0; }
        else { this.backgroundLocation = this.backgroundLocation - 5; }
        g2.drawImage(backgroundImage[0], this.backgroundLocation, 0, backgroundImage[0].getWidth(null), backgroundImage[0].getHeight(null), null);
        
        g2.setFont(new Font("TimesRoman", Font.BOLD, 75));
        String gameNameString = "SOL";
        g2.drawString(gameNameString, (width / 2) - 80, 100);
        
        
            g2.setFont(new Font("TimesRoman", Font.BOLD, 22));
            g2.drawString("Controls:", ((width+40)-width), 150);
            g2.setFont(new Font("TimesRoman", Font.PLAIN, 18));            
            g2.drawString("Move Left:", ((width+40)-width), 200);
            g2.drawString("Move Right:", ((width+40)-width), 225);
            g2.drawString("Look Up:", ((width+40)-width), 250);
            g2.drawString("Crouch:", ((width+40)-width), 275);
            g2.drawString("Jump:", ((width+40)-width), 300);
            g2.drawString("Boost:", ((width+40)-width), 325);
            g2.drawString("Fire Primary Wepon:", ((width+40)-width), 350);
            g2.drawString("Fire Secondary Weapon:", ((width+40)-width), 375);    
            
            g2.drawString("Left Arrow / A", (width-200), 200);
            g2.drawString("Right Arrow / D", (width-200), 225);
            g2.drawString("Up Arrow / W", (width-200), 250);
            g2.drawString("Down Arrow / S", (width-200), 275);
            g2.drawString("Space", (width-200), 300);
            g2.drawString("Space (while midair)", (width-200), 325);
            g2.drawString("Left Mouse", (width-200), 350);
            g2.drawString("Right Mouse", (width-200), 375);             
        
        // Back Button
        String scoreString = String.format("Main Menu");
        g2.drawString(scoreString, (width / 2) - 55, (height / 2) + 222);
        
        // Highlight back button
        g2.drawRect((width / 2) - 70, (height / 2) + 200, 120, 30);
        
        //draw the dbimage to the panel
        this.getGraphics().drawImage(dbImage, 0, 0, this);
    }
    
    public void scoreScreenRender()
    {
        //The traditional notion of double-buffering in Java applications is 
        //fairly straightforward: create an offscreen image, draw to that image 
        //using the image's graphics object, then, in one step, call drawImage 
        //using the target window's graphics object and the offscreen image.
        //this will reduce flicker because the slower process of rendering each 
        //object is done off screen
        
        //make sure the off screen image is defined
        if (dbImage == null) {
            // Creates an off-screen drawable image
            dbImage = createImage(this.getSize().width, this.getSize().height);
            if (dbImage == null) {
                System.out.println("Critical Error: dbImage is null");
                System.exit(1);
            }
        }
        //create graphics2d object from off screen image
        g2 = (Graphics2D) dbImage.getGraphics();
        
        
        width = getSize().width;
        height = getSize().height;
        
        //g2 = (Graphics2D)this.getGraphics();
        g2.setColor(Color.RED);
        
        if(Math.abs(this.backgroundLocation) >= 350) { this.backgroundLocation = 0; }
        else { this.backgroundLocation = this.backgroundLocation - 5; }
        g2.drawImage(backgroundImage[0], this.backgroundLocation, 0, backgroundImage[0].getWidth(null), backgroundImage[0].getHeight(null), null);
        
        g2.setFont(new Font("TimesRoman", Font.BOLD, 75));
        String gameNameString = "SOL";
        g2.drawString(gameNameString, (width / 2) - 80, 100);
        
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        
        // Level Information
        String levelString = "High Scores";
        g2.drawString(levelString, (width / 2) - 50, (height / 2) - 140);
        // Score
        String scoreString01 = "1. Tyrel Tachibana 4526 1/27/2017";
        String scoreString02 = "2. Sam Sopp 4515 1/28/2017";
        String scoreString03 = "3. N/A";
        String scoreString04 = "4. N/A";
        String scoreString05 = "5. N/A";
        g2.drawString(scoreString01, (width / 2) - 70, (height / 2) - 100);
        g2.drawString(scoreString02, (width / 2) - 70, (height / 2) - 80);
        g2.drawString(scoreString03, (width / 2) - 70, (height / 2) - 60);
        g2.drawString(scoreString04, (width / 2) - 70, (height / 2) - 40);
        g2.drawString(scoreString05, (width / 2) - 70, (height / 2) - 20);
        // Back Button
        String scoreString = String.format("Main Menu");
        g2.drawString(scoreString, (width / 2) - 50, (height / 2) + 50);
        
        // Highlight back button
        g2.drawRect((width / 2) - 70, (height / 2) + 30, 120, 30);
        
        //draw the dbimage to the panel
        this.getGraphics().drawImage(dbImage, 0, 0, this);
    }
    
    
    public void gameRender()
    {
//        width = getSize().width;
//        height = getSize().height;
//        if (dbImage == null) {
//            // Creates an off-screen drawable image to be used for double buffering
//            dbImage = createImage(width, height);
//            if (dbImage == null) {
//                System.out.println("Critical Error: dbImage is null");
//                System.exit(1);
//            } else {
//                g2 = (Graphics2D) dbImage.getGraphics();
//            }
//        }
//        
//        if(Math.abs(this.backgroundLocation) >= 350) { this.backgroundLocation = 0; }
//        else { this.backgroundLocation = this.backgroundLocation - 5; }
//        g2.drawImage(backgroundImage[Main.gameData.getLevel()], this.backgroundLocation, 0, backgroundImage[Main.gameData.getLevel()].getWidth(null), backgroundImage[Main.gameData.getLevel()].getHeight(null), null);
//        
//        this.drawStatistics();
//
//        if (Main.animator.running)
//        {
//            synchronized (Main.gameData.enemyFigures)
//            {
//                Main.gameData.enemyFigures.stream().forEach((f) -> { f.render(g2); });
//
//                try{
//            Main.gameData.friendFigures.stream().forEach((f) -> {
//                    f.render(g2);
//                });
//            }
//            catch(Exception e) { System.out.println("GamePanel Line 82: " + e.getMessage()); }
//                
//
//            }
//
//        }
    }

    // Use active rendering to put the buffered image on-screen
    public void printScreen()
    {
        Graphics g;
        try {
            g = this.getGraphics();
            if ((g != null) && (dbImage != null)) {
                g.drawImage(dbImage, 0, 0, null);
            }
            Toolkit.getDefaultToolkit().sync();                                 // Sync the display on some systems
            if (g != null) {
                g.dispose();
            }
        } catch (Exception e) {
            System.out.println("Graphics error: " + e);
        }
    }
    
    private void drawStatistics()
    {
        g2.setColor(Color.RED);
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        
        // Game Information
        String informationString = "SOL";
        g2.drawString(informationString, 20, 20);
        // Level Information
        //String levelString = "Level: " + (Main.gameData.getLevel() + 1);
        //g2.drawString(levelString, (width / 2) - 50, 20);
        // Score
        //String scoreString = String.format("Score: %010d", Main.score.getScore());
        //g2.drawString(scoreString, width - 135, 20);
    }
    
    public int getMainMenuSelection()
    {
        return this.mainMenuHover;
    }
    
    public void mainMenuSelectOption(int movement)
    {
        if(movement > 0)
        {
            if(this.mainMenuHover > 0){ this.mainMenuHover = this.mainMenuHover - 1; }
            else if(this.mainMenuHover == 0){ this.mainMenuHover = 3; }
        }
        if(movement < 0)
        {
            if(this.mainMenuHover < 3){ this.mainMenuHover = this.mainMenuHover + 1; }
            else if(this.mainMenuHover == 3){ this.mainMenuHover = 0; }
        }
    }
    
    public void animateButton(Graphics2D g3){
//        if(KeyController.getEnterKeyPressed()){
//            try{ 
//                Thread.sleep(200);
//            }
//            catch(Exception c){
//                
//            }
//            
//            
//        }
    }
    
}
