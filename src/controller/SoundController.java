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
    private Clip clip;
    private boolean paused = false;
    public SoundController(){
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException ex) {
        }
    }
    
    /**
    * Method for music toggle - Tyrel Tachibana
    */
    public void pauseSound()
    {
        if(paused)
            clip.start();
        else
            clip.stop();
        this.paused = !this.paused;
    }
    
    public void stop(){
        clip.stop();
        clip.close();
    }
    public void menuBGM()
    {
        try
        {
            File file = new File("src/Sounds/sol-menu2.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            if(paused)clip.stop();
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
        {
            System.out.printf("Error: %s \n", e.toString());
        }
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
        try
        {
            File file = new File("src/Sounds/sol-worldmap.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            if(paused)clip.stop();
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
        {
            System.out.printf("Error: %s \n", e.toString());
        }
    }
    
    public void earthBGM()
    {
        try
        {
            File file = new File("src/Sounds/sol-earthBGM.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            if(paused)clip.stop();
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
        {
            System.out.printf("Error: %s \n", e.toString());
        }
    }
    
    public void moonBGM()
    {
        
    }
    
    public void venusBGM()
    {
        
    }
    
    public void mercuryBGM()
    {
        
    }
    
    public void sunBGM()
    {
        
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
    
    public void grenadeLauncherFire()
    {
        
    }
    
    public void seekerMissileFire()
    {
        
    }
    
    public void flakeCannonFire()
    {
        
    }
    
    public void forceBlasterFire()
    {
        
    }
    
    public void weaponPickUp()
    {
        
    }
    
    public void repurposedFleshMove()
    {
        try
        {
            File file = new File("src/Sounds/sol-repurposedFlesh.wav");
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
    
    public void harvesterDroneMove()
    {
        try
        {
            File file = new File("src/Sounds/sol-harvesterDrone.wav");
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
    
    public void assaultCommanderMove()
    {
        try
        {
            File file = new File("src/Sounds/sol-assaultCommander.wav");
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
    
    public void playerMove()
    {
        try
        {
            File file = new File("src/Sounds/sol-player.wav");
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
}