/**
 * Module A03 Algorithm Analysis
 * Section A31: Measure Execution Time
 * Task 1: Basic Implementation of a Stopwatch
 */
package a02_analysis.s1_time;

import a02_analysis.s1_time.Stopwatch;

import java.util.Date;

/**
 * Basic class used to test the stop watch class
 * Tests the {@link Stopwatch} class with a predefined operation
 *
 * @author Jordan Wallingsford
 * @version 1.0
 */
public class TestStopwatch
{
    public static void main(String[] args)
    {
        System.out.println("Stopwatch 2B - Task 2 - Jordan Wallingsford");
        Date date = new Date();
        System.out.println("Executed On: " + date.toString());
        System.out.println("--------------------------");
        Stopwatch w = new Stopwatch();
        double sum = 0;
        for (int i = 1; i <=2000000000; i++)
        {
            sum += i;
        }
        long t = w.elapsedTime();
        System.out.println("Computes the sum of the first one billion integers as a double");
        System.out.println("Sum (double): " + sum);
        System.out.println("Time (milli-s): " + t);
    }
}
