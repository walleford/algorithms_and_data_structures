package b01_sorts.quicksort.threaded;


import a02_analysis.s1_time.Stopwatch;
import a02_analysis.s1_time.TimeAnalysis;
import util.array.ArrayUtility;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * Used to test the performance of the threaded quick sort algorithm.
 *
 * @author jordan wallingsford
 */
public class ThreadedQuickSortTest
{
    private static TimeAnalysis meanTime(String name, int numberOfExecutions, int numberOfElements) throws ExecutionException, InterruptedException
    {
        Stopwatch watch = new Stopwatch();
        TimeAnalysis ta = new TimeAnalysis(name, numberOfExecutions);
        for (int trial = 0; trial < numberOfExecutions; trial++)
        {
            int[] array = ArrayUtility.generateIntArray(numberOfElements, Integer.MIN_VALUE, Integer.MAX_VALUE);
            watch.startWatch();
            // test code
            ForkJoinPool forkJoinPool = new ForkJoinPool();
            ForkJoinTask<Void> future = forkJoinPool.submit(new Main.QuickSortTask(array, 0, array.length - 1, 100));
            future.get();
            // end test code
            long time = watch.elapsedTime();
            ta.add(time);
        }
        return ta;
    }

    private static void printMeanExecutionTimeGrowthTable(int numberOfExecutions, int minArrayLength,
                                                          int arrayIncrementLength, int maxArrayLength) throws ExecutionException, InterruptedException
    {
        System.out.println("Mean execution time growth table");
        System.out.println("  - Method: QuickSort of N integers utilizing multiple threads");
        System.out.println("  - Sample size for time estimation: "+numberOfExecutions);
        System.out.println("|-----------|--------|------|------|------------------|");
        System.out.println("|         N |   Mean |  Min |  Max |         CI 99.9% |");
        System.out.println("|-----------|--------|------|------|------------------|");
        for (int n=minArrayLength; n<=maxArrayLength; n+=arrayIncrementLength) {
            String name = "Sort " + n + " integers";
            TimeAnalysis ta = meanTime(name, numberOfExecutions, n);
            System.out.printf("| %9d | %6.1f | %4d | %4d | (%6.1f, %6.1f) |\n", n,  ta.getMeanTime(),
                    ta.getMinTime(), ta.getMaxTime(), ta.getMinMean999Confidence(), ta.getMaxMean999Confidence());
        }
        System.out.println("|-----------|--------|------|------|------------------|");
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        printMeanExecutionTimeGrowthTable(21, 1000000, 1000000,10000000);
    }
}
