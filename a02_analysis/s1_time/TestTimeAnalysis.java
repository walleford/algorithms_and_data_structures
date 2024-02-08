/**
 * Module A03 Algorithm Analysis
 * Section A31: Measure Execution Time
 * Task 2a: Mean Execution Time
 */
package a02_analysis.s1_time;

import java.util.Date;

/**
 * Test the {@link TimeAnalysis} class with a linear algorithm
 *
 * @author Jordan Wallingsford
 * @version 1.0
 */
public class TestTimeAnalysis
{
    /**
     * Analyzes the execution time for the addition of the first n integers (1 + 2 + .. + maxValueAdded)\
     *
     * @param numberOfExecutions how many times the operation should be repeated
     * @param maxValueAdded the highest number it should add up to
     * @return bag of TimeAnalysis of all the times
     */
    public static TimeAnalysis testAddition(String name, int numberOfExecutions, int maxValueAdded)
    {
        Stopwatch watch = new Stopwatch();
        TimeAnalysis ta = new TimeAnalysis(name, numberOfExecutions);
        for (int trial = 0; trial < numberOfExecutions; trial++)
        {
            watch.startWatch();
            @SuppressWarnings("unused")
            double sum = 0;
            for (int i = 1; i <= maxValueAdded; i++)
            {
                sum += i;
            }
            long time = watch.elapsedTime();
            ta.add(time);
        }
        return ta;
    }

    public static void printMeanExecutionTable(int[] executions, int maxValueAdded)
    {
        String name = "Add the First "+maxValueAdded+" Integers.";
        System.out.println("|------|------|------|------|---------------|");
        System.out.println("| Exec |  Min |  Max | Mean |     CI        |");
        System.out.println("|------|------|------|------|---------------|");
        for (int i = 0; i < executions.length; i++)
        {
            TimeAnalysis ta = testAddition(name, executions[i], maxValueAdded);
            System.out.printf("| %4d | %4d | %4d | %4.1f | (%4.1f, %4.1f) |\n",
                    (long)ta.size(), ta.getMinTime(), ta.getMaxTime(), ta.getMeanTime(),
                    ta.getMinMean999Confidence(), ta.getMaxMean999Confidence());
        }
        System.out.println("|------|------|------|------|---------------|");
    }

    /**
     * Tests the stopwatch with the execution of testAddition.
     * @param args
     */
    public static void main(String[] args)
    {
        System.out.println("Analysis Table 1B - Task 7 - Jordan Wallingsford");
        Date date = new Date();
        System.out.println("Executed On: " + date.toString());
        System.out.println("--------------------------");
        printMeanExecutionTable(new int[] {11, 21, 41, 81, 101, 1001}, 1000000000);
    }
}
