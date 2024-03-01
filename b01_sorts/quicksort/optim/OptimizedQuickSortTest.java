/**
 * B32-PA QuickSort Optimization
 */
package b01_sorts.quicksort.optim;

import b01_sorts.quicksort.basic.QuickSort;
import util.array.ArrayUtility;

import java.util.Date;

public class OptimizedQuickSortTest
{
    private static boolean sortTest(String name, int[] a, int[] expected)
    {
        System.out.println("Test              :    " + name);
        System.out.println("Array to be sorted:    " + ArrayUtility.toString(a,"[",", ","]"));
        System.out.println("Expected Result   :    " + ArrayUtility.toString(expected,"[",", ","]"));
        OptimizedQuickSort.sort(a);
        boolean result = ArrayUtility.equals(a, expected);
        System.out.println("Array after sort  :    " + ArrayUtility.toString(a,"[",", ","]"));
        System.out.println("Arrays equal?     :    " + result);
        System.out.println("-----------------------------------------------------");
        return result;
    }

    private static void sortAllTests()
    {
        boolean result = true;
        result = result && sortTest("Empty Array", new int[] {}, new int[] {});
        result = result && sortTest("Singleton Array", new int[] {1}, new int[] {1});
        result = result && sortTest("Two Sorted Elements", new int[] {1,2}, new int[] {1,2});
        result = result && sortTest("Two Unsorted Elements", new int[] {23, 12}, new int[] {12, 23});
        result = result && sortTest("Three Sorted Elements", new int[] {1,23,45}, new int[] {1,23,45});
        result = result && sortTest("Three Descending Elements", new int[] {34,21,10}, new int[] {10,21,34});
        result = result && sortTest("Seven Random Elements", new int[] {34,56,12,45,98,32,6},
                new int[] {6,12,32,34,45,56,98});
        result = result && sortTest("Ten Random Elements",new int[] {1,23,45,56,78,89,90,21,43,21},
                new int[] {1,21,21,23,43,45,56,78,89,90});
        System.out.println("All sort tests successful?:    " + result);
    }

    public static void main(String[] args)
    {
        System.out.println("Optimized Quick Sort - Task 2 - Jordan Wallingsford");
        Date date = new Date();
        System.out.println("Executed On: " + date.toString());
        System.out.println("--------------------------");
        sortAllTests();
    }
}
