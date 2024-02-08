/**
 * Module A03 Algorithm Analysis
 * Section A31: Measure Execution Time
 * Task 2a: Mean Execution Time
 */
package a02_analysis.s1_time;

import a01_data_structure.s1_bags.FixedCapacityBag;

import java.util.Iterator;

/**
 * Records and analyzes the duration of a set of repeated executions.
 * Stores the items in a fixed capacity bag of long items.
 * 2 phases:
 *      1. collect results of execution
 *      2. analyze the results using an on-demand strategy
 * No thread-safe implementation
 *
 * @author Jordan Wallingsford
 * @version 1.0
 *
 */
public class TimeAnalysis extends FixedCapacityBag<Long>
{
    // name of the method/algo tested
    private String name;

    // true if we have already performed the computation
    private boolean startComputed;

    // minimum execution time
    private long min;

    // maximum execution time
    private long max;

    // sum of executions as a double
    private double sum;

    // sample mean execution time
    private double mean;

    // sample variance
    private double variance;

    // sample stdev
    private double standardDeviation;

    // minimum mean execution time with 99.9% confidence
    private double minMean999Confidence;

    // max mean execution time with 99.9% confidence
    private double maxMean999Confidence;

    /**
     * Creates a time analysis class for the specific num of executions
     * @param name name of the method/algo tested
     * @param numberOfExecutions int of times to be executed
     */
    public TimeAnalysis(String name, int numberOfExecutions)
    {
        super(numberOfExecutions);
        if (numberOfExecutions < 1)
        {
            throw new RuntimeException("at least one execution expected");
        }
        this.name = name;
    }

    /**
     * Creates a full bag with the provided execution times
     *
     * @param name name of the method/algo tested
     * @param executionTimes array of long with execution times in ms
     */
    public TimeAnalysis(String name, long[] executionTimes)
    {
        this(name, executionTimes.length);
        for (long v : executionTimes)
        {
            add(v);
        }
        computeStatistics();
    }

    /**
     * ensures the executions have finished before analysis can be done
     */
    private void checkExecutionEnded()
    {
        if (size() < getCapacity())
        {
            throw new RuntimeException("Not all experiments performed. Only " + size() + " out of " + getCapacity() +".");
        }
    }

    /**
     * Compute the following statistics if executions have ended.
     * <ul>
     *     <li>Min Execution Time</li>
     *     <li>Max Execution Time</li>
     *     <li>Mean Execution Time</li>
     *     <li>Sample stdev</li>
     *     <li>99.9% confidence interval</li>
     * </ul>
     */
    private void computeStatistics()
    {
        checkExecutionEnded();
        if (startComputed)
            return;
        Iterator<Long> iterator = iterator();
        // firstValue is set to the first item in the array
        long firstValue = iterator.next();
        min = firstValue;
        max = firstValue;
        sum = firstValue;
        while (iterator.hasNext())
        {
            /**
             * If the min is greater than the current value, set the min to the current value
             * If the max is less than the current value, set the max to the current value
             * Add the current value to the sum to keep a total sum of all values
             */
            long value = iterator.next();
            if (min > value)
                min = value;
            else if (max < value)
                max = value;
            sum += value;
        }
        mean = sum / getCapacity();

        variance = 0;
        for (long value : this)
        {
            variance += (value - mean) * (value - mean);
        }
        variance = variance / (getCapacity() - 1);
        standardDeviation = Math.sqrt(variance);
        double e = tValue999(getCapacity() - 1) * standardDeviation / Math.sqrt(getCapacity());
        minMean999Confidence = mean - e;
        maxMean999Confidence = mean + e;
        startComputed = true;
    }


    /**
     * These functions will return the required values, and for each function it will compute the statistics.
     * Will only compute statistics if they haven't been already.
     */
    public long getMinTime()
    {
        computeStatistics();
        return min;
    }

    public long getMaxTime()
    {
        computeStatistics();
        return max;
    }

    public double getMeanTime()
    {
        computeStatistics();
        return mean;
    }

    public double getTimeStandardDeviation()
    {
        computeStatistics();
        return standardDeviation;
    }

    public double getMinMean999Confidence()
    {
        computeStatistics();
        return minMean999Confidence;
    }

    public double getMaxMean999Confidence()
    {
        computeStatistics();
        return maxMean999Confidence;
    }

    public void printStatistics()
    {
        computeStatistics();
        System.out.println("Execution Time Analysis for: " + name);
        System.out.println("    - Sample Number of Values        = " + size());
        System.out.println("    - Sample Min Execution Times     = " + min);
        System.out.println("    - Sample Max Execution Times     = " + max);
        System.out.println("    - Sample Mean Value              = " + mean);
        System.out.println("    - Sample Standard Deviation      = " + standardDeviation);
        System.out.println(
                "    - Mean 99.9% Confidence Interval = " + minMean999Confidence + ", " + maxMean999Confidence
        );
    }


    // T TEST CONSTANTS
    public static final double t999[] = { 636.6, 31.60, 12.92, 8.610, 6.869, 5.959, 5.408, 5.041, 4.781, 4.587, 4.437,
            4.318, 4.221, 4.140, 4.073, 4.015, 3.965, 3.922, 3.882, 3.850, 3.819, 3.792, 3.768, 3.745, 3.725, 3.707, 3.690,
            3.674, 3.659, 3.646 };

    private static double tValue999(int df)
    {
        if (df < 1)
            throw new RuntimeException("Invalid Degree of Freedoms");
        if (df >= 1000)
            return 3.3;
        if (df >= 100)
            return 3.39;

        if (df >= 80)
            return 3.416;
        if (df >= 60)
            return 3.460;
        if (df >= 50)
            return 3.496;
        if (df >= 40)
            return 3.551;
        if (df >= 30)
            return 3.646;
        return t999[df-1];
    }

    /**
     * local test to validate its working
     * @param args
     */
    public static void main(String[] args)
    {
        long[] a = { 1025, 1017, 1031, 1009, 1005, 1022 };
        TimeAnalysis ta = new TimeAnalysis("test", a);
        ta.printStatistics();
    }
}
