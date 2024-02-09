/**
 * Module  A02: Analysis of Algorithms
 * Section 2  : Time Growth Analysis
 * Task    1a : Sum of Integers Linear Growth Analysis
 */
package a02_analysis.s3_quadratic;

import a02_analysis.s1_time.Stopwatch;
import a02_analysis.s1_time.TimeAnalysis;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Program with n^2 run time. Reads n integers and counts the number of pairs that cum to exactly 0
 *
 * @author Jordan Wallingsford
 * @version 1.0
 */
public class TwoSum
{

    /**
     * will return the distinct pairs in the array where i + j = 0
     * this is only possible for each instance where a[i] and -a[i] exists so we binary search the array for the
     * opposite of a[i] and set it to j.
     *
     * @param a array of ints to search through
     * @return count of items that sum up to 0
     */
    public static int count(int[] a)
    {
        int n = a.length;
        Arrays.sort(a);
        if (containsDuplicates(a)) throw new IllegalArgumentException("array contains duplicate integers");
        int count = 0;
        for (int i = 0; i < n; i++)
        {
            int j = Arrays.binarySearch(a, -a[i]);
            if (j > i) count++;
        }
        return count;
    }

    private static boolean containsDuplicates(int[] a)
    {
        for (int i = 1; i < a.length; i++)
        {
            if (a[i] == a[i-1]) return true;
        }
        return false;
    }

}
