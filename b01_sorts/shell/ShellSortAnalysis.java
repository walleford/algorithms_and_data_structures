package b01_sorts.shell;

import a02_analysis.s1_time.Stopwatch;
import a02_analysis.s1_time.TimeAnalysis;
import b01_sorts.insert.InsertionSort;
import util.array.ArrayUtility;

public class ShellSortAnalysis
{
    public static TimeAnalysis meanTime(String name, int numberOfExecutions, int numberOfElements, int min, int max)
    {
        Stopwatch watch = new Stopwatch();
        TimeAnalysis ta = new TimeAnalysis(name, numberOfExecutions);
        for (int trial = 0; trial < numberOfExecutions; trial++)
        {
            int[] array = ArrayUtility.generateIntArray(numberOfElements, min, max);
            watch.startWatch();
            // test code
            ShellSort.sort(array);
            // end test code
            long time = watch.elapsedTime();
            ta.add(time);;
        }
        return ta;
    }

    public static void printMeanExecutionTimeGrowthTable(int numberOfExecutions,
                                                         int minArrayLength, int arrayIncrementLength, int maxArrayLength,
                                                         int minArrayValue, int maxArrayValue) {
        System.out.println("Mean execution time growth table");
        System.out.println("  - Method: Selection sort of N integers");
        System.out.println("  - Sample size for time estimation: "+numberOfExecutions);
        System.out.println("|-----------|--------|------|------|------------------|");
        System.out.println("|         N |   Mean |  Min |  Max |         CI 99.9% |");
        System.out.println("|-----------|--------|------|------|------------------|");
        for (int n=minArrayLength; n<=maxArrayLength; n+=arrayIncrementLength) {
            String name = "Sort " + n + " integers";
            TimeAnalysis ta = meanTime(name, numberOfExecutions, n, minArrayValue, maxArrayValue);
            System.out.printf("| %9d | %6.1f | %4d | %4d | (%6.1f, %6.1f) |\n", n,  ta.getMeanTime(),
                    ta.getMinTime(), ta.getMaxTime(), ta.getMinMean999Confidence(), ta.getMaxMean999Confidence());
        }
        System.out.println("|-----------|--------|------|------|------------------|");
    }
}
