/**
 * B23 Bottom Up Merge Sort
 */
package b01_sorts.bottomUpMerge;

import b01_sorts.topdown.TopDownMergeSort;
import util.array.ArrayUtility;

import java.util.Date;

/**
 * Used to test the BUMergeSort class.
 */
public class BottomUpMergeSortTest
{
    private static boolean sortTest(String name, int[] a, int[] expected)
    {
        System.out.println("Test: " + name);
        System.out.println("Pre-mergesort: " + ArrayUtility.toString(a, "[", ", ", "]"));
        System.out.println("--------------------------------");
        System.out.println("Passing to mergesort now...");
        BottomUpMergeSort.sort(a);
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
        result = result && sortTest("pair sorted array", new int[] {10,45}, new int[] {10,45});
        result = result && sortTest("pair unsorted array", new int[] {20,13}, new int[] {13,20});
        result = result && sortTest("3-elem sorted", new int[] {100,223,3345}, new int[] {100,223,3345});
        result = result && sortTest("3-elem inversed", new int[] {3345,223,100}, new int[] {100,223,3345});
        result = result && sortTest("7-elem array", new int[] {38,26,15,64,43,76,54}, new int[] {15,26,38,43,54,64,76});
        System.out.println("All sort tests were successful?:    " + result);

    }

    public static void main(String[] args)
    {
        System.out.println("Bottom Up Merge Sort Task 1 - Jordan Wallingsford");
        Date date = new Date();
        System.out.println("Executed On: " + date.toString());
        System.out.println("--------------------------");
        sortAllTests();
    }
}
