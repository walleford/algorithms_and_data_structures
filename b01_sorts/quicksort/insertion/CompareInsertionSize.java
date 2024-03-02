package b01_sorts.quicksort.insertion;

import a02_analysis.s1_time.Stopwatch;
import a02_analysis.s1_time.TimeAnalysis;
import b01_sorts.quicksort.optim.OptimizedQuickSort;
import util.array.ArrayUtility;

import java.sql.Time;
import java.util.Date;

public class CompareInsertionSize
{
    /**
     * Compare the mean execution times of our optimized quick sort class for various insertion sizes.
     * They will all use the same randomly generated arrays.
     * The sizes are generated starting with the provided startSize and the given incrementsize times the number of times
     *
     * @param numberOfExecutions number of executions to compute the mean
     * @param length length of the generated random arrays
     * @param startSize
     * @param incrementSize
     * @param numberSizes
     * @return an array of time analysis elements
     */
    private static TimeAnalysis[] meanTimeComparison(int numberOfExecutions, int length, int startSize, int incrementSize,
                                           int numberSizes)
    {
        Stopwatch watch = new Stopwatch();
        TimeAnalysis[] ta = new TimeAnalysis[numberSizes+1];
        for (int i = 0; i <=numberSizes; i++)
        {
            int size = startSize + i*incrementSize;
            ta[i] = new TimeAnalysis("insertion size "+size, numberOfExecutions);
        }
        for (int trial = 0; trial < numberOfExecutions; trial++)
        {
            int[] a0 = ArrayUtility.generateIntArray(length, Integer.MAX_VALUE, Integer.MIN_VALUE);
            for(int i = 0; i <= numberSizes; i++)
            {
                int[] a = ArrayUtility.copy(a0);
                int size = startSize + i*incrementSize;
                watch.startWatch();
                OptimizedQuickSort.sort(a, size);
                long time = watch.elapsedTime();
                ta[i].add(time);
            }
        }
        return ta;
    }

    private static void printComparisonTable(int numberOfExecutions, int min, int increment, int max, int startSize,
                                             int incrementSize, int numberSizes)
    {
        System.out.println("Mean Execution Time Growth Table");
        System.out.println("  - Number of executions for the estimation:  " + numberOfExecutions);

        System.out.print("|-----------|");
        for (int i = 0; i <=numberSizes;i++)
            System.out.print("--------|");
        System.out.println();

        System.out.print("|  N\\SIZE  |");
        for (int i =0; i <= numberSizes; i++)
        {
            int size = startSize + i * incrementSize;
            System.out.printf(" %6s |", size);
        }
        System.out.println();

        System.out.print("|-----------|");
        for (int i = 0; i <= numberSizes; i++)
            System.out.print("--------|");
        System.out.println();

        for (int n= min; n <= max; n+=increment)
        {
            TimeAnalysis[] ta = meanTimeComparison(numberOfExecutions, n, startSize, incrementSize, numberSizes);

            System.out.printf("| %9d |", n);
            for (int i = 0; i <= numberSizes; i++)
            {
                System.out.printf(" %6.1f |", ta[i].getMeanTime());
            }
            System.out.println();
        }

        System.out.print("|-----------|");
        for (int i = 0; i <= numberSizes; i++)
            System.out.print("--------|");
        System.out.println();
    }

    public static void main(String[] args)
    {
        System.out.println("Compare Insertion Sort Sizes - Task 2 - Jordan Wallingsford");
        Date date = new Date();
        System.out.println("Executed On: " + date.toString());
        System.out.println("--------------------------");
        printComparisonTable(31, 1000000, 1000000, 10000000, 85, 5, 10);
    }
}
