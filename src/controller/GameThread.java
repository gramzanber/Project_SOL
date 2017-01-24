package controller;

public class GameThread extends Thread
{

    public boolean running = true;
    public static boolean paused;
    public static boolean gameOver;
    private final int FRAMES_PER_SECOND = 24;
    public static int loading;

    @Override
    public void run()
    {
        loading = 60;

        while (running)
        {
            long startTime = System.currentTimeMillis();

            if (!paused && !gameOver)
            {
                if (loading == 0)
                {
                    // Update image locations here
                }
                else
                {
                    loading--;
                }
                // Draw the images onto the canvas here.
            }

            long endTime = System.currentTimeMillis();
            int sleepTime = (int) (1.0/FRAMES_PER_SECOND*1500)-(int)(endTime-startTime);

            if (sleepTime > 0)
            {
                try
                {
                    Thread.sleep(sleepTime);
                }
                catch (InterruptedException e)
                {
                    System.out.println("THREAD ERROR: " + e.toString());
                }
            }
        }
        System.exit(0);
    }
}
