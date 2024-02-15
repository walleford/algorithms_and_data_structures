/**
 * B11 Task 2: Selection Sort
 * Contains various versions of the selection sort algorithm
 */
package b01_sorts.select;

import util.array.ArrayUtility;

import java.util.Date;

public class SelectionSortTest
{
    public static void testSelectionSort()
    {
        int[] a10 = ArrayUtility.generateIntArray(10,1,20);
        System.out.printf("Random Array with 10 elements of range 1:20 :\n%s\n ",ArrayUtility.toString(
                a10, "[",", ","]"
        ));
        SelectionSort.sort(a10);
        System.out.printf("Sorted Array: \n%s\n",ArrayUtility.toString(
                a10,"[",", ","]"
        ));
    }

    public static void main(String[] args)
    {
        System.out.println("Selection Sort - Task 12 - Jordan Wallingsford");
        Date date = new Date();
        System.out.println("Executed On: " + date.toString());
        System.out.println("--------------------------");
        testSelectionSort();
    }
}
