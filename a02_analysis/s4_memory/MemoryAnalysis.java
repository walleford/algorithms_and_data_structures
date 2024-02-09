/**
 * Module  A02: Analysis of Algorithms
 * Section 2  : Time Growth Analysis
 * Task    1a : Sum of Integers Linear Growth Analysis
 */
package a02_analysis.s4_memory;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Simple class used to compute memory requirements
 */
public class MemoryAnalysis
{
    public static double doubleSum(int maxValueAdded)
    {
        double sum = 0;
        for (int i = 0; i <= maxValueAdded; i++)
        {
            sum += i;
        }
        return sum;
    }

    public static int[] randomArray(int size)
    {
        int[] a = new int[size];
        for (int i = 0; i < size; i++)
        {
            a[i] = ThreadLocalRandom.current().nextInt(-1000000, 1000000);
        }
        return a;
    }

    public static int count(int[] a) {
        int n = a.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (a[i] + a[j] == 0) {
                    count++;
                }
            }
        }
        System.out.println(count);
        return count;
    }

    public static void main(String[] args)
    {
        int[] a = randomArray(10000);
        int count = count(a);
        System.out.println("Memory Usage     : Bytes");
        System.out.println("Memory Allocation of Array (24bytes + 4N): " + (24 + 4 * a.length));
        System.out.println("Internal Memory used for randomArray(): " + 40028 + " bytes");
        System.out.println("Internal Memory Used for count(): " + 16 + " bytes.");
    }
}
