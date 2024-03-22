package b03_searches.binary;

import a02_analysis.s1_time.Stopwatch;
import a02_analysis.s1_time.TimeAnalysis;
import b03_searches.orderedst.BinarySearchSymbolTable;
import b03_searches.symboltable.UnsortedLinkedListSymbolTable;
import util.array.ArrayUtility;

import java.sql.Time;
import java.util.Date;

public class TestSymbolTable
{
    private static TimeAnalysis[] putMeanTimeComparison(int numberOfExecutions, int numberOfAssociations, int minKey,
                                                        double keyDensity)
    {
        int maxKey = minKey + (int) (numberOfAssociations / keyDensity);
        Stopwatch watch = new Stopwatch();
        TimeAnalysis[] ta = new TimeAnalysis[3];
        ta[0] = new TimeAnalysis("linked list", numberOfExecutions);
        ta[1] = new TimeAnalysis("binary search", numberOfExecutions);
        ta[2] = new TimeAnalysis("binary tree", numberOfExecutions);

        for (int trial = 0; trial < numberOfExecutions; trial++)
        {
            int[] keys = ArrayUtility.generateIntArray(numberOfAssociations, minKey, maxKey);
            int[] values = ArrayUtility.generateIntArray(numberOfAssociations, Integer.MIN_VALUE, Integer.MAX_VALUE);

            watch.startWatch();
            // testing code starts here < testing Unsorted linked list put method >
            UnsortedLinkedListSymbolTable<Integer, Integer> st0 = new UnsortedLinkedListSymbolTable<>();
            for (int i = 0; i < numberOfAssociations; i++)
            {
                st0.put(keys[i], values[i]);
            }
            // ending tested code
            long time = watch.elapsedTime();
            ta[0].add(time);

            watch.startWatch();
            // testing code starts here < testing binary search symbol table put() method >
            BinarySearchSymbolTable<Integer, Integer> st1 = new BinarySearchSymbolTable<>();
            for (int i = 0; i < numberOfAssociations; i++)
            {
                st1.put(keys[i], values[i]);
            }
            // ending tested code
            time = watch.elapsedTime();
            ta[1].add(time);

            watch.startWatch();
            // testing code starts here < testing the put method of our BST >
            BinarySearchTree<Integer, Integer> st3 = new BinarySearchTree<>();
            for (int i = 0; i < numberOfAssociations; i++)
            {
                st3.put(keys[i], values[i]);
            }
            // ending tested code
            time = watch.elapsedTime();
            ta[2].add(time);
        }
        return ta;
    }

    private static TimeAnalysis[] getMeanTimeComparison(int numberOfExecutions, int numberOfAssociations, int minKey,
                                                        double keyDensity)
    {
        int maxKey = minKey + (int) (numberOfAssociations / keyDensity);
        Stopwatch watch = new Stopwatch();
        TimeAnalysis[] ta = new TimeAnalysis[3];
        ta[0] = new TimeAnalysis("linked list", numberOfExecutions);
        ta[1] = new TimeAnalysis("binary search", numberOfExecutions);
        ta[2] = new TimeAnalysis("binary tree", numberOfExecutions);

        for (int trial = 0; trial < numberOfExecutions; trial++)
        {
            int[] keys = ArrayUtility.generateIntArray(numberOfAssociations, minKey, maxKey);
            int[] values = ArrayUtility.generateIntArray(numberOfAssociations, Integer.MIN_VALUE, Integer.MAX_VALUE);

            watch.startWatch();
            // testing code starts here < testing Unsorted linked list put method >
            UnsortedLinkedListSymbolTable<Integer, Integer> st0 = new UnsortedLinkedListSymbolTable<>();
            for (int i = 0; i < numberOfAssociations; i++)
            {
                st0.get(keys[i]);
            }
            // ending tested code
            long time = watch.elapsedTime();
            ta[0].add(time);

            watch.startWatch();
            // testing code starts here < testing binary search symbol table put() method >
            BinarySearchSymbolTable<Integer, Integer> st1 = new BinarySearchSymbolTable<>();
            for (int i = 0; i < numberOfAssociations; i++)
            {
                st1.get(keys[i]);
            }
            // ending tested code
            time = watch.elapsedTime();
            ta[1].add(time);

            watch.startWatch();
            // testing code starts here < testing the put method of our BST >
            BinarySearchTree<Integer, Integer> st3 = new BinarySearchTree<>();
            for (int i = 0; i < numberOfAssociations; i++)
            {
                st3.get(keys[i]);
            }
            // ending tested code
            time = watch.elapsedTime();
            ta[2].add(time);
        }
        return ta;
    }

    private static void putComparisonTable(String name, int numberOfExecutions, int minAssoc, int incrementAssoc,
                                           int maxAssoc, int minKey, double keyDensity)
    {
        System.out.println("Mean execution time growth comparison table for put operation.");
        System.out.println("  - Test Name           :   " + name);
        System.out.println("  - Number of Executions:   " + numberOfExecutions);
        System.out.println("  - Key Density         :   " + keyDensity);
        System.out.println("  - Methods             : LL = Unsorted linked list, BS = binary search, BT = binary search tree");
        System.out.println("|----------|----------|----------|----------|");
        System.out.println("|    N     |    LL    |    BS    |    BT    |");
        System.out.println("|----------|----------|----------|----------|");
        for (int n = minAssoc; n <= maxAssoc; n+= incrementAssoc)
        {
            TimeAnalysis[] ta = putMeanTimeComparison(numberOfExecutions, n, minKey, keyDensity);
            System.out.printf("| %9d | %4.1f | %4.1f | %4.1f |\n", n, ta[0].getMeanTime(), ta[1].getMeanTime(),
                    ta[2].getMeanTime());
        }
        System.out.println("|----------|----------|----------|----------|");
    }

    private static void getComparisonTable(String name, int numberOfExecutions, int minAssoc, int incrementAssoc,
                                           int maxAssoc, int minKey, double keyDensity)
    {
        System.out.println("Mean execution time growth comparison table for put operation.");
        System.out.println("  - Test Name           :   " + name);
        System.out.println("  - Number of Executions:   " + numberOfExecutions);
        System.out.println("  - Key Density         :   " + keyDensity);
        System.out.println("  - Methods             : LL = Unsorted linked list, BS = binary search, BT = binary search tree");
        System.out.println("|----------|----------|----------|----------|");
        System.out.println("|    N     |    LL    |    BS    |    BT    |");
        System.out.println("|----------|----------|----------|----------|");
        for (int n = minAssoc; n <= maxAssoc; n+= incrementAssoc)
        {
            TimeAnalysis[] ta = putMeanTimeComparison(numberOfExecutions, n, minKey, keyDensity);
            System.out.printf("|%9d | %8.1f | %8.1f | %8.1f |\n", n, ta[0].getMeanTime(), ta[1].getMeanTime(),
                    ta[2].getMeanTime());
        }
        System.out.println("|----------|----------|----------|----------|");
    }

    private static void deleteTest()

    public static void main(String[] args)
    {
        System.out.println("C22 Searching Binary Search Trees - Task 4 - Jordan Wallingsford");
        Date date = new Date();
        System.out.println("Executed On: " + date.toString());
        System.out.println("--------------------------");
        // getComparisonTable("Sparse Keys", 21, 10000, 1000, 20000, Integer.MIN_VALUE, 0.05);
        // putComparisonTable("Normal Keys", 21, 10000, 1000, 20000, Integer.MIN_VALUE, 0.5);
        getComparisonTable("Dense Keys", 21, 10000, 1000, 20000, 0, 10);
    }
}
