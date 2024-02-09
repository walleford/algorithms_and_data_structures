/**
 * Module  A02: Analysis of Algorithms
 * Section 2  : Time Growth Analysis
 * Task    1a : Sum of Integers Linear Growth Analysis
 */
package a02_analysis.s2_growth;

import a02_analysis.s1_time.Stopwatch;
import a02_analysis.s1_time.TimeAnalysis;

import java.util.Date;

/**
 * Analyze the execution time growth for the sum of a set number of Integers.
 * Analyze the changes as we modify the number N of integers summed.
 *
 * @author Jordan Wallingsford
 * @version 1.0
 */
public class SumGrowthAnalysis
{
    /**
     * analyzes execution time for the addition of all numbers up to lastValueAdded
     * @param name String name of test
     * @param numberOfExecutions int number of times to test the algo
     * @param lastValueAdded int highest number to add up to
     * @return TimeAnalysis bag of calculations for executions
     */
    public static TimeAnalysis testAddition(String name, int numberOfExecutions, int lastValueAdded)
    {
        Stopwatch watch = new Stopwatch();
        TimeAnalysis ta = new TimeAnalysis(name, numberOfExecutions);
        for (int trial = 0; trial < numberOfExecutions; trial++)
        {
            watch.startWatch();
            double sum = 0;
            for (int i = 1; i <= lastValueAdded; i++)
            {
                sum += 1;
            }
            long time = watch.elapsedTime();
            ta.add(time);;
        }
        return ta;
    }

    public static void printMeanExecutionTable(int numberOfExecutions, int minValueAdded, int increment,
                                               int maxValueAdded)
    {
        System.out.println("Mean Execution Time Growth Table");
        System.out.println("    - Method: Sum of First N Ints");
        System.out.println("    - Sample size for time estimation: " + numberOfExecutions);
        System.out.println("|-----------|------|------|--------|---------------|");
        System.out.println("|     N     |  Min |  Max | Mean   |     CI        |");
        System.out.println("|-----------|------|------|--------|---------------|");
        for (int n=minValueAdded; n<=maxValueAdded; n+=increment)
        {
            String name = "Add the First "+maxValueAdded+" Integers.";
            TimeAnalysis ta = testAddition(name, numberOfExecutions, n);
            System.out.printf("| %9d | %4d | %4d | %6.1f | (%6.1f, %6.1f) |\n",
                    n, ta.getMinTime(), ta.getMaxTime(), ta.getMeanTime(),
                    ta.getMinMean999Confidence(), ta.getMaxMean999Confidence());
        }
        System.out.println("|-----------|------|------|--------|---------------|");
    }

    public static void main(String[] args)
    {
        System.out.println("Linear Growth - Task 3 - Jordan Wallingsford");
        Date date = new Date();
        System.out.println("Executed On: " + date.toString());
        System.out.println("--------------------------");
        printMeanExecutionTable(11, 100000000, 100000000, 1000000000);
    }
}
