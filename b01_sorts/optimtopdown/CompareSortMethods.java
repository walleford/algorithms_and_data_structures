package b01_sorts.optimtopdown;
import a02_analysis.s1_time.Stopwatch;
import a02_analysis.s1_time.TimeAnalysis;
import b01_sorts.select.SelectionSort;
import b01_sorts.insert.InsertionSort;
import b01_sorts.shell.ShellSort;
import b01_sorts.topdown.TopDownMergeSort;
import util.array.ArrayUtility;

import java.util.Date;

public class CompareSortMethods
{
    public static TimeAnalysis[] meanTimeComparison(int numberOfExecutions, int length)
    {
        Stopwatch watch = new Stopwatch();
        TimeAnalysis[] ta = new TimeAnalysis[4];
        ta[0] = new TimeAnalysis("selection", numberOfExecutions);
        ta[1] = new TimeAnalysis("insertion", numberOfExecutions);
        ta[2] = new TimeAnalysis("shell", numberOfExecutions);
        ta[3] = new TimeAnalysis("top-down merge", numberOfExecutions);
        for (int trial = 0; trial < numberOfExecutions; trial++)
        {
            int[] a0 = ArrayUtility.generateIntArray(length, Integer.MIN_VALUE, Integer.MAX_VALUE);
            int[] a1 = ArrayUtility.copy(a0);
            int[] a2 = ArrayUtility.copy(a0);
            int[] a3 = ArrayUtility.copy(a0);

            watch.startWatch();
            // testing this code
            SelectionSort.sort(a0);
            // end of tested code
            long time = watch.elapsedTime();
            ta[0].add(time);

            watch.startWatch();
            // testing this code
            InsertionSort.sort(a1);
            // end of tested code
            time = watch.elapsedTime();
            ta[1].add(time);

            watch.startWatch();
            // testing this code
            ShellSort.sort(a2);
            // end of tested code
            time = watch.elapsedTime();
            ta[2].add(time);

            watch.startWatch();
            // testing this code
            TopDownMergeSort.sort(a3);
            // end of tested code
            time = watch.elapsedTime();
            ta[3].add(time);
        }
        return ta;
    }

    public static TimeAnalysis[] meanTimeComparison2(int numberOfExecutions, int length, int repeatFactor)
    {
        Stopwatch watch = new Stopwatch();
        TimeAnalysis[] ta = new TimeAnalysis[4];
        ta[0] = new TimeAnalysis("selection", numberOfExecutions);
        ta[1] = new TimeAnalysis("insertion", numberOfExecutions);
        ta[2] = new TimeAnalysis("shell", numberOfExecutions);
        ta[3] = new TimeAnalysis("top-down merge", numberOfExecutions);
        for (int trial = 0; trial < numberOfExecutions; trial++)
        {
            int[] a0 = ArrayUtility.generateIntArray(length, Integer.MIN_VALUE, Integer.MAX_VALUE);
            int[] a1 = ArrayUtility.copy(a0);
            int[] a2 = ArrayUtility.copy(a0);
            int[] a3 = ArrayUtility.copy(a0);

            watch.startWatch();
            // testing this code
            for (int i=1; i<=repeatFactor;i++)
                SelectionSort.sort(a0);
            // end of tested code
            long time = watch.elapsedTime();
            ta[0].add(time);

            watch.startWatch();
            // testing this code
            for (int i=1; i<=repeatFactor;i++)
                InsertionSort.sort(a1);
            // end of tested code
            time = watch.elapsedTime();
            ta[1].add(time);

            watch.startWatch();
            // testing this code
            for (int i=1; i<=repeatFactor;i++)
                ShellSort.sort(a2);
            // end of tested code
            time = watch.elapsedTime();
            ta[2].add(time);

            watch.startWatch();
            // testing this code
            for (int i=1; i<=repeatFactor;i++)
                TopDownMergeSort.sort(a3);
            // end of tested code
            time = watch.elapsedTime();
            ta[3].add(time);
        }
        return ta;
    }

    private static void printComparisonTable1(int numberOfExecutions, int minLength, int increment, int maxLength)
    {
        System.out.println("Mean execution time growth comparison table");
        System.out.println("  -                Method                    : SELection INSertion SHEll TopDownMerge");
        System.out.println("  - Number of executions for time estimation : "+numberOfExecutions);
        System.out.println("|-----------|--------|---------|----------|--------|");
        System.out.println("|         N |   SEL  | INS     |   SHELL  |   TDM  |");
        System.out.println("|-----------|--------|---------|----------|--------|");
        for (int n=minLength; n<=maxLength; n+=increment) {
            String name = "Sort " + n + " integers";
            TimeAnalysis[] ta = meanTimeComparison(numberOfExecutions, n);
            System.out.printf("| %9d | %6.1f | %6.1f  | %6.1f  | %6.1f  |\n", n,  ta[0].getMeanTime(),
                    ta[1].getMeanTime(), ta[2].getMeanTime(), ta[3].getMeanTime());
        }
        System.out.println("|-----------|--------|------|------|------------------|");
    }

    private static void printComparisonTable2(int numberOfExecutions, int minLength, int increment, int maxLength,
                                              int repeatFactor)
    {
        System.out.println("Mean execution time growth comparison table");
        System.out.println("  -                Method                    : SELection INSertion SHEll TopDownMerge");
        System.out.println("  - Number of executions for time estimation : "+numberOfExecutions);
        System.out.println("|-----------|--------|---------|----------|--------|");
        System.out.println("|         N |   SEL  | INS     |   SHELL  |   TDM  |");
        System.out.println("|-----------|--------|---------|----------|--------|");
        for (int n=minLength; n<=maxLength; n+=increment) {
            String name = "Sort " + n + " integers";
            TimeAnalysis[] ta = meanTimeComparison2(numberOfExecutions, n, repeatFactor);
            System.out.printf("| %9d | %6.1f | %6.1f  | %6.1f  | %6.1f  |\n", n,  ta[0].getMeanTime(),
                    ta[1].getMeanTime(), ta[2].getMeanTime(), ta[3].getMeanTime());
        }
        System.out.println("|-----------|--------|------|------|------------------|");
    }

    public static void main(String args[])
    {
        System.out.println("Optimized Top Down Merge Sort - Task 3 - Jordan Wallingsford");
        Date date = new Date();
        System.out.println("Executed On: " + date.toString());
        System.out.println("--------------------------");
        printComparisonTable2(21, 5,5,100, 1000000);
    }
}
