/**
 * Section B21 Assignment Top-Down Merge Sort
 */
package b01_sorts.topdown;

import util.array.ArrayUtility;

import java.util.Date;

/**
 * This will be used to craft and run tests against the {@link b01_sorts.topdown.TopDownMergeSort}
 *
 * @author Jordan Wallingsford
 * @version 1.0
 */
public class TopDownMergeSortTest
{
    public static boolean sortTest(String name, int[] a, int[] expected)
    {
        System.out.println("Test: " + name);
        System.out.println("Pre-mergesort: " + ArrayUtility.toString(a, "[", ", ", "]"));
        System.out.println("--------------------------------");
        System.out.println("Passing to mergesort now...");
        TopDownMergeSort.sort(a);
        System.out.println("Result: " + ArrayUtility.toString(a, "[", ", ", "]"));
        boolean result = ArrayUtility.equals(a, expected);
        System.out.println("Expected Result: " + ArrayUtility.toString(expected, "[", ", ", "]"));
        if (result) System.out.println("Test successful!");
        else System.out.println("Test unsuccessful :(");
        System.out.println("--------------------------------");
        return result;
    }

    public static void sortAllTests()
    {
        boolean result = true;
        result = result && sortTest("empty array", new int[] {}, new int[] {});
        result = result && sortTest("singleton array", new int[] {3}, new int[] {3});
        result = result && sortTest("pair sorted array", new int[] {1,2}, new int[] {1,2});
        result = result && sortTest("pair unsorted array", new int[] {2,1}, new int[] {1,2});
        result = result && sortTest("3-elem sorted", new int[] {1,2,3}, new int[] {1,2,3});
        result = result && sortTest("3-elem inversed", new int[] {3,2,1}, new int[] {1,2,3});
        result = result && sortTest("7-elem array", new int[] {3,2,1,6,4,7,5}, new int[] {1,2,3,4,5,6,7});
        System.out.println("All sort tests were successful?:    " + result);

    }

    public static void main(String args[])
    {
        System.out.println("Merge Sort Task 2 - Jordan Wallingsford");
        Date date = new Date();
        System.out.println("Executed On: " + date.toString());
        System.out.println("--------------------------");
        sortAllTests();
    }
}
