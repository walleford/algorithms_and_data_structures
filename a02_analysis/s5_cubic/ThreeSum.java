package a02_analysis.s5_cubic;

import java.util.Arrays;

public class ThreeSum
{
    private ThreeSum() { }

    public static void printAll(int[] a)
    {
        int n = a.length;
        for (int i = 0; i < n; i++)
        {
            for (int j = i+1; j < n; j++)
            {
                for (int k = j + 1; k < n; k++)
                {
                    if ( a[i] + a[j] + a[k] ==0)
                    {
                        System.out.println(a[i] + " " + a[j] + " " + a[k]);
                    }
                }
            }
        }
    }

    public static int count(int[] a)
    {
        int n = a.length;
        Arrays.sort(a);
        if (containsDuplicates(a)) throw new IllegalArgumentException("Array contains duplicate ints");
        int count = 0;
        for (int i = 0; i < n; i++)
        {
            for (int j = i + 1; j<n; j++)
            {
                int k = Arrays.binarySearch(a, -(a[i] + a[j]));
                if (k > j) count++;
            }
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
