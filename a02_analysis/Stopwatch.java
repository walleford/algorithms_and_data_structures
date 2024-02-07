/**
 * Module A03 Algorithm Analysis
 * Section A31: Measure Execution Time
 * Task 1: Basic Implementation of a Stopwatch
 */

package a02_analysis;

/**
 * Stopwatch simulates a time watch to record times
 *
 * @author Jordan Wallingsford
 * @version 1.0
 */
public class Stopwatch
{
    private long startTime;
    public void startWatch()
    {
        startTime = System.currentTimeMillis();
    }

    public Stopwatch()
    {
        startWatch();
    }

    public long elapsedTime()
    {
        return System.currentTimeMillis() - startTime;
    }
}
