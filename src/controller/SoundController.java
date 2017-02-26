// Last edited by Cameron Droke -- 2/12/2017
package controller;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundController 
{
    private Clip clip;
    public SoundController(){
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException ex) {
        }
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
            //URL url = this.getClass().getClassLoader().getResource("explosion.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            // Get a sound clip resource.
            //Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
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
            //URL url = this.getClass().getClassLoader().getResource("explosion.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            // Get a sound clip resource.
            //clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();
            //clip.loop(Clip.LOOP_CONTINUOUSLY);
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
            //URL url = this.getClass().getClassLoader().getResource("explosion.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            // Get a sound clip resource.
            //clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();
            //clip.loop(Clip.LOOP_CONTINUOUSLY);
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
            //URL url = this.getClass().getClassLoader().getResource("explosion.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            // Get a sound clip resource.
            //clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
        {
            System.out.printf("Error: %s \n", e.toString());
        }
    }
    
    public void earthBGM()
    {
        
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
    
    /*public void backgroundMusic()
    {
        try
        {
            File file = new File("src/Sounds/bgm_hyperspace.wav");
            //URL url = this.getClass().getClassLoader().getResource("explosion.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
        {
            System.out.printf("Error: %s \n", e.toString());
        }
    }*/
}