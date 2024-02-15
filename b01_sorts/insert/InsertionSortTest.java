/**
 * Module B12 Task 1 Insertion Sort Implementation
 *
 * @author Jordan Wallingsford
 * @version 1.0
 */
package b01_sorts.insert;

public class InsertionSortTest
{
    /**
     * Rearranges the elements of an array of int values in ascending order
     * Uses the insertion sort method for the implementation
     * For/Each position in the initial array, move the value to the left until the array is sorted
     * Check if the left value is less than this one, move on to that one to make sure
     * @param a
     */
    public static void sort(int[] a)
    {
        for (int i = 1; i < a.length; i++)
        {
            int temp = a[i]; // current element
            int j = i;
            // for each if the current element is less than the element to the left, move to the left
            for (; j>0 && temp<a[j-1]; j--)
            {
                a[j] = a[j-1];
            }
            a[j]=temp;
        }
    }
}
