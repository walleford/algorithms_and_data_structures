/**
 * Module A03 Algorithm Analysis
 * Section A31: Measure Execution Time
 * Task 1: Basic Implementation of a Stopwatch
 */

package a02_analysis.s1_time;

/**
 * Stopwatch simulates a time watch to record times
 *
 * @author Jordan Wallingsford
 * @version 1.0
 */
public class Stopwatch
{

    // This holds the time when the watch has been started or restarted
    private long startTime;

    // sets the start time to the current time in order to start the watch
    public void startWatch()
    {
        startTime = System.currentTimeMillis();
    }

    //creates a new stopwatch class by starting the watch
    public Stopwatch()
    {
        startWatch();
    }

    // calculates the elapsed time by subtracting the start time from the current time.
    public long elapsedTime()
    {
        return System.currentTimeMillis() - startTime;
    }
}
