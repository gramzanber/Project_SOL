package controller;

import java.io.File;
import java.io.IOException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundController 
{
    
    //singleton instance
    private static SoundController instance;
    
    /**
     * Singleton class
     * 
     * @return the singleton instance of this class
     */
    public static SoundController getInstance(){
        //initialize instance on first use
        if(instance == null){
            instance = new SoundController();
        }
        //return the instance
        return instance;
    }
    
    
    private String backgroundMusic;
    private Clip menuBGMClip;
    private Clip earthBGMClip;
    private Clip moonBGMClip;
    private Clip venusBGMClip;
    private Clip mercuryBGMClip;
    private Clip sunBGMClip;    
    private Clip openWorldBGMClip;
    private Clip bossBGMClip;
    
    private Clip playerWalkClip;
    private Clip smallEnemyWalkClip;
    private Clip mediumEnemyWalkClip;
    private Clip largeEnemyWalkClip;
    private boolean paused = false;
    
    /**
     * private constructor prevents bypassing singleton pattern
     */
    private SoundController(){
        try {
            menuBGMClip = AudioSystem.getClip();
            earthBGMClip = AudioSystem.getClip();
            moonBGMClip = AudioSystem.getClip();
            venusBGMClip = AudioSystem.getClip();
            mercuryBGMClip = AudioSystem.getClip();
            sunBGMClip = AudioSystem.getClip();            
            openWorldBGMClip = AudioSystem.getClip();
            bossBGMClip = AudioSystem.getClip();
            
            playerWalkClip = AudioSystem.getClip();
            smallEnemyWalkClip = AudioSystem.getClip();
            mediumEnemyWalkClip = AudioSystem.getClip();
            largeEnemyWalkClip = AudioSystem.getClip();
        } catch (LineUnavailableException ex) {
        }
        
        try
        {
            File file = new File("src/Sounds/sol-menu2.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            menuBGMClip.open(audioIn);
            
            
            file = new File("src/Sounds/sol-earthBGM.wav");
            audioIn = AudioSystem.getAudioInputStream(file);
            earthBGMClip.open(audioIn);
            
            file = new File("src/Sounds/sol-moonBGM.wav");
            audioIn = AudioSystem.getAudioInputStream(file);
            moonBGMClip.open(audioIn);

            file = new File("src/Sounds/sol-venusBGM.wav");
            audioIn = AudioSystem.getAudioInputStream(file);
            venusBGMClip.open(audioIn);

            file = new File("src/Sounds/sol-mercuryBGM.wav");
            audioIn = AudioSystem.getAudioInputStream(file);
            mercuryBGMClip.open(audioIn);

            file = new File("src/Sounds/sol-sunBGM.wav");
            audioIn = AudioSystem.getAudioInputStream(file);
            sunBGMClip.open(audioIn);            
            
            file = new File("src/Sounds/sol-worldmap.wav");
            audioIn = AudioSystem.getAudioInputStream(file);
            openWorldBGMClip.open(audioIn);
            
            
            
            
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
        {
            System.out.printf("Error: %s \n", e.toString());
        }
        
        
        backgroundMusic = "MENU";

    }
    
    /**
    * Method for music toggle - Tyrel Tachibana
    * updated by Cameron Droke
    */
    public void pauseSound()
    {
        if(paused)
        {
            if(backgroundMusic == "MENU"){
                menuBGMClip.start();
            }
            else if(backgroundMusic == "EARTH"){
                earthBGMClip.start();
            }
            else if(backgroundMusic == "MOON"){
                moonBGMClip.start();
            }
            else if(backgroundMusic == "VENUS"){
                venusBGMClip.start();
            }
            else if(backgroundMusic == "MERCURY"){
                mercuryBGMClip.start();
            }
            else if(backgroundMusic == "SUN"){
                sunBGMClip.start();
            }            
            else if(backgroundMusic == "WORLD"){
                openWorldBGMClip.start();
            }
            else if(backgroundMusic == "BOSS"){
                bossBGMClip.start();
            }
            
            playerWalkClip.start();
            smallEnemyWalkClip.start();
            mediumEnemyWalkClip.start();
            largeEnemyWalkClip.start();
        }
        else
        {
            menuBGMClip.stop();
            earthBGMClip.stop();
            moonBGMClip.stop();
            venusBGMClip.stop();
            mercuryBGMClip.stop();
            sunBGMClip.stop();            
            openWorldBGMClip.stop();
            bossBGMClip.stop();
            
            playerWalkClip.stop();
            smallEnemyWalkClip.stop();
            mediumEnemyWalkClip.stop();
            largeEnemyWalkClip.stop();
        }
        this.paused = !this.paused;

        
    }
    
    public void stop(){
        System.out.println("Sound Clear");
        menuBGMClip.stop();
        earthBGMClip.stop();
        moonBGMClip.stop();
        venusBGMClip.stop();
        mercuryBGMClip.stop();
        sunBGMClip.stop();        
        openWorldBGMClip.stop();
        bossBGMClip.stop();
        
        playerWalkClip.stop();
        smallEnemyWalkClip.stop();
        mediumEnemyWalkClip.stop();
        largeEnemyWalkClip.stop();
        
       // menuBGMClip.close();
        //earthBGMClip.close();
        //openWorldBGMClip.close();
       // bossBGMClip.close();
        
        playerWalkClip.close();
        smallEnemyWalkClip.close();
        mediumEnemyWalkClip.close();
        largeEnemyWalkClip.close();
    }
    
    public void menuBGM()
    {
        backgroundMusic = "MENU";
        menuBGMClip.loop(Clip.LOOP_CONTINUOUSLY);
        menuBGMClip.start();
        if(paused)stop();
    }
    
    public void selectConfirm()
    {
        try
        {
            File file = new File("src/Sounds/sol-confirm.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            Clip clipNew = AudioSystem.getClip();
            if(!paused)
            {
                clipNew.open(audioIn);
                clipNew.start();
            }
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
        {
            System.out.printf("Error: %s \n", e.toString());
        }
    }
    
    public void selectDeny()
    {
        try
        {
            File file = new File("src/Sounds/sol-deny.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            Clip clipNew = AudioSystem.getClip();
            if(!paused)
            {
                clipNew.open(audioIn);
                clipNew.start();
            }
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
        {
            System.out.printf("Error: %s \n", e.toString());
        }
    }
    
    public void openWorldBGM()
    {
        backgroundMusic = "WORLD";
        openWorldBGMClip.loop(Clip.LOOP_CONTINUOUSLY);
        openWorldBGMClip.start();
        if(paused)stop();
        
//        try
//        {
//            File file = new File("src/Sounds/sol-worldmap.wav");
//            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
//            openWorldBGMClip.open(audioIn);
//            openWorldBGMClip.loop(Clip.LOOP_CONTINUOUSLY);
//            if(paused)openWorldBGMClip.stop();
//        }
//        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
//        {
//            System.out.printf("Error: %s \n", e.toString());
//        }
    }
    
    public void earthBGM()
    {
        
        backgroundMusic = "EARTH";
        earthBGMClip.loop(Clip.LOOP_CONTINUOUSLY);
        earthBGMClip.start();
        if(paused)stop();
        
        
//        try
//        {
//            File file = new File("src/Sounds/sol-earthBGM.wav");
//            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
//            earthBGMClip.open(audioIn);
//            earthBGMClip.loop(Clip.LOOP_CONTINUOUSLY);
//            if(paused)earthBGMClip.stop();
//        }
//        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
//        {
//            System.out.printf("Error: %s \n", e.toString());
//        }
    }
    
    public void moonBGM()
    {
        backgroundMusic = "MOON";
        moonBGMClip.loop(Clip.LOOP_CONTINUOUSLY);
        moonBGMClip.start();
        if(paused)stop();        
    }
    
    public void venusBGM()
    {
        backgroundMusic = "VENUS";
        venusBGMClip.loop(Clip.LOOP_CONTINUOUSLY);
        venusBGMClip.start();
        if(paused)stop();        
    }
    
    public void mercuryBGM()
    {
        backgroundMusic = "MERCURY";
        mercuryBGMClip.loop(Clip.LOOP_CONTINUOUSLY);
        mercuryBGMClip.start();
        if(paused)stop();        
    }
    
    public void sunBGM()
    {
        backgroundMusic = "SUN";
        sunBGMClip.loop(Clip.LOOP_CONTINUOUSLY);
        sunBGMClip.start();
        if(paused)stop();        
    }
    
    public void bossBGM()
    {
        
        backgroundMusic = "BOSS";
        bossBGMClip.loop(Clip.LOOP_CONTINUOUSLY);
        bossBGMClip.start();
        if(paused)stop();
        
        
//        try
//        {
//            File file = new File("src/Sounds/sol-bossBGM.wav");
//            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
//            bossBGMClip.open(audioIn);
//            bossBGMClip.loop(Clip.LOOP_CONTINUOUSLY);
//            if(paused)bossBGMClip.stop();
//        }
//        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
//        {
//            System.out.printf("Error: %s \n", e.toString());
//        }
    }
    
    public void primaryWeaponFire()
    {
        try
        {
            File file = new File("src/Sounds/sol-laser.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            Clip clipNew = AudioSystem.getClip();
            if(!paused)
            {
                clipNew.open(audioIn);
                clipNew.start();
            }
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
        {
            System.out.printf("Error: %s \n", e.toString());
        }
    }
    
    public void grenadeFire()
    {
        try
        {
            File file = new File("src/Sounds/grenade.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            Clip clipNew = AudioSystem.getClip();
            if(!paused)
            {
                clipNew.open(audioIn);
                clipNew.start();
            }
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
        {
            System.out.printf("Error: %s \n", e.toString());
        }
    }    
    
    public void seekerMissileFire()
    {
        try
        {
            File file = new File("src/Sounds/sol-missile.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            Clip clipNew = AudioSystem.getClip();
            if(!paused)
            {
                clipNew.open(audioIn);
                clipNew.start();
            }
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
        {
            System.out.printf("Error: %s \n", e.toString());
        }
    }
    
    public void flakFire()
    {
        try
        {
            File file = new File("src/Sounds/flak.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            Clip clipNew = AudioSystem.getClip();
            if(!paused)
            {
                clipNew.open(audioIn);
                clipNew.start();
            }
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
        {
            System.out.printf("Error: %s \n", e.toString());
        }
    }    
    
    public void coinPickUp()
    {
        try
        {
            File file = new File("src/Sounds/sol-coin.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            Clip clipNew = AudioSystem.getClip();
            if(!paused)
            {
                clipNew.open(audioIn);
                clipNew.start();
            }
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
        {
            System.out.printf("Error: %s \n", e.toString());
        }
    }
    
    public void repurposedFleshMove()
    {
        try
        {
            File file = new File("src/Sounds/sol-repurposedFlesh.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            if(!paused)
            {
                // If clip is not already running and has not been opened yet,
                // open the clip and start play. Else reset the clip to play
                // from the beginning then play.
                if(!smallEnemyWalkClip.isRunning())
                {
                    if(!smallEnemyWalkClip.isOpen())
                    {
                        smallEnemyWalkClip.open(audioIn);
                        smallEnemyWalkClip.start();
                    }
                    else
                    {
                        smallEnemyWalkClip.setFramePosition(0);
                        smallEnemyWalkClip.start();
                    }
                }
            }
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
        {
            System.out.printf("Error: %s \n", e.toString());
        }
    }
    
    public void harvesterDroneMove()
    {
        try
        {
            File file = new File("src/Sounds/sol-harvesterDrone.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            if(!paused)
            {
                // If clip is not already running and has not been opened yet,
                // open the clip and start play. Else reset the clip to play
                // from the beginning then play.
                if(!mediumEnemyWalkClip.isRunning())
                {
                    if(!mediumEnemyWalkClip.isOpen())
                    {
                        mediumEnemyWalkClip.open(audioIn);
                        mediumEnemyWalkClip.start();
                    }
                    else
                    {
                        mediumEnemyWalkClip.setFramePosition(0);
                        mediumEnemyWalkClip.start();
                    }
                }
            }
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
        {
            System.out.printf("Error: %s \n", e.toString());
        }
    }
    
    public void assaultCommanderMove()
    {
        try
        {
            File file = new File("src/Sounds/sol-assaultCommander.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            if(!paused)
            {
                // If clip is not already running and has not been opened yet,
                // open the clip and start play. Else reset the clip to play
                // from the beginning then play.
                if(!largeEnemyWalkClip.isRunning())
                {
                    if(!largeEnemyWalkClip.isOpen())
                    {
                        largeEnemyWalkClip.open(audioIn);
                        largeEnemyWalkClip.start();
                    }
                    else
                    {
                        largeEnemyWalkClip.setFramePosition(0);
                        largeEnemyWalkClip.start();
                    }
                }
            }
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
        {
            System.out.printf("Error: %s \n", e.toString());
        }
    }
    
    public void playerMove()
    {
        try
        {
            File file = new File("src/Sounds/step.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            if(!paused)
            {
                // If clip is not already running and has not been opened yet,
                // open the clip and start play. Else reset the clip to play
                // from the beginning then play.
                if(!playerWalkClip.isRunning())
                {
                    if(!playerWalkClip.isOpen())
                    {
                        playerWalkClip.open(audioIn);
                        playerWalkClip.start();
                    }
                    else
                    {
                        playerWalkClip.setFramePosition(0);
                        playerWalkClip.start();
                    }
                }
            }
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
        {
            System.out.printf("Error: %s \n", e.toString());
        }
    }
}