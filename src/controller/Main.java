package controller;

import model.ImageLibrary;
import view.swingcomponents.MainWindow;




/**
* Main class, all shared objects are accessed through this class.
*
* @author  SATAS
* @version 1.0
* @since   2017-02-20 
*/
public class Main
{
    
    //static GraphicsDevice device = GraphicsEnvironment
    //    .getLocalGraphicsEnvironment().getScreenDevices()[0];
    
    public static int WIN_WIDTH = 700; //the width of the gui window
    public static int WIN_HEIGHT = 600; //the height of the gui window
    
    public static boolean debug = false;
   /**
   * This is the main entry point for the application
   * 
   * @param args Array of command line arguments
   */
    public static void main(String[] args)
    {
        
        //load images
        ImageLibrary.getInstance().loadSpriteSheet("gold_coin", "/Images/spinning-coin-spritesheet.png", 171, 171, 0, 0);
        
        ImageLibrary.getInstance().loadSpriteSheet("grenade_pickup", "/Images/grenade_pickup.png", 16, 16, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("missile_pickup", "/Images/missile_pickup.png", 16, 16, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("flak_pickup", "/Images/flak_pickup.png", 16, 16, 0, 0);   
        ImageLibrary.getInstance().loadSpriteSheet("energy_tank", "/Images/energy_tank.png", 16, 16, 0, 0);           
        
        ImageLibrary.getInstance().loadSpriteSheet("lava_top", "/Images/lava_top.png", 32, 32, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("lava_mid", "/Images/lava_mid.png", 32, 32, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("lava_bottom", "/Images/lava_bottom.png", 32, 32, 0, 0);      
        
        ImageLibrary.getInstance().loadSpriteSheet("acid_top", "/Images/acid_top.png", 32, 32, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("acid_mid", "/Images/acid_mid.png", 32, 32, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("acid_bottom", "/Images/acid_bottom.png", 32, 32, 0, 0);          
                
        ImageLibrary.getInstance().loadSpriteSheet("hero_run_left", "/Images/hero_run_left.png", 166, 155, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("hero_run_right", "/Images/hero_run_right.png", 166, 155, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("hero_stand_left", "/Images/hero_stand_left.png", 166, 155, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("hero_stand_right", "/Images/hero_stand_right.png", 166, 155, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("hero_jump_left", "/Images/hero_jump_left.png", 166, 155, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("hero_jump_right", "/Images/hero_jump_right.png", 166, 155, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("primary1_left", "/Images/primary1_left.png", 51, 26, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("primary1_right", "/Images/primary1_right.png", 51, 26, 0, 0);        
        ImageLibrary.getInstance().loadSpriteSheet("grenade", "/Images/grenade.png", 144, 82, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("alien_5-walkleft", "/Images/alien_5-walkleft.png", 428, 298, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("alien_5-walkright", "/Images/alien_5-walkright.png", 428, 298, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("alien_1-walkleft", "/Images/alien_1-walkleft.png", 687, 632, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("alien_1-walkright", "/Images/alien_1-walkright.png", 687, 632, 0, 0);

        ImageLibrary.getInstance().loadSpriteSheet("seeker", "/Images/seeker.png", 350, 80, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("seekerleft", "/Images/seekerleft.png", 350, 80, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("FlakCannonRight", "/Images/sprite_sheet_FlakCannon_right_100X100.png", 100, 100, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("FlakCannonLeft", "/Images/sprite_sheet_FlakCannon_left_100X100.png", 100, 100, 0, 0);
        
        ImageLibrary.getInstance().loadSpriteSheet("earth_small_enemy_walk_left", "/Images/earth_small_enemy_walk_left.png", 234, 327, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("earth_small_enemy_walk_right", "/Images/earth_small_enemy_walk_right.png", 234, 327, 0, 0);
        
        ImageLibrary.getInstance().loadSpriteSheet("sprite_sheet_alian_10_walk_right_300x226", "/Images/sprite_sheet_alian_10_walk_right_300x226.png", 300, 226, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("sprite_sheet_alian_10_walk_left_300x226", "/Images/sprite_sheet_alian_10_walk_left_300x226.png", 300, 226, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("sprite_sheet_alian_9_walk_left_300x452", "/Images/sprite_sheet_alian_9_walk_left_300x452.png", 300, 452, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("sprite_sheet_alian_9_walk_right_300x452", "/Images/sprite_sheet_alian_9_walk_right_300x452.png", 300, 452, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("sprite_sheet_alian_8_walk_left_400x210", "/Images/sprite_sheet_alian_8_walk_left_400x210.png", 400, 210, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("sprite_sheet_alian_8_walk_right_400x210", "/Images/sprite_sheet_alian_8_walk_right_400x210.png", 400, 210, 0, 0);
        
        ImageLibrary.getInstance().loadSpriteSheet("sprite_sheet_alian_6_walk_left_300x427", "/Images/sprite_sheet_alian_6_walk_left_300x427.png", 300, 427, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("sprite_sheet_alian_6_walk_right_300x427", "/Images/sprite_sheet_alian_6_walk_right_300x427.png", 300, 427, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("sprite_sheet_alian_7_walk_left_300x351", "/Images/sprite_sheet_alian_7_walk_left_300x351.png", 300, 351, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("sprite_sheet_alian_7_walk_right_300x351", "/Images/sprite_sheet_alian_7_walk_right_300x351.png", 300, 351, 0, 0);
        
        ImageLibrary.getInstance().loadSpriteSheet("blueEnemyWalkingLeft", "/Images/blueEnemyWalkingLeft.png", 300, 226, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("blueEnemyWalkingRight", "/Images/blueEnemyWalkingRight.png", 300, 226, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("greenSmallEnemyWalkingLeft", "/Images/greenSmallAlienWalkingLeft.png", 250, 182, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("greenSmallEnemyWalkingRight", "/Images/greenSmallAlienWalkingRight.png", 250, 182, 0, 0);

        ImageLibrary.getInstance().loadSpriteSheet("sun_large_enemy_run_left", "/Images/sun_large_enemy_run_left.png", 218, 147, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("sun_large_enemy_run_right", "/Images/sun_large_enemy_run_right.png", 218, 147, 0, 0);
        
        ImageLibrary.getInstance().loadSpriteSheet("sun_large_enemy_die_left", "/Images/sun_large_enemy_die_left.png", 218, 147, 0, 0);
        ImageLibrary.getInstance().loadSpriteSheet("sun_large_enemy_die_right", "/Images/sun_large_enemy_die_right.png", 218, 147, 0, 0);        
        
        ImageLibrary.getInstance().loadSpriteSheet("explosion", "/Images/explosion.png", 128, 128, 0, 0);

        
        
        //build and display the gui
        MainWindow.getInstance().setVisible(true);

        //initially show the main menu screen
        GameController.getInstance().resetViewport();
        GameController.getInstance().showMainMenu();
        
        // Start rendering engine
        Animator.getInstance().start();
    }
}
