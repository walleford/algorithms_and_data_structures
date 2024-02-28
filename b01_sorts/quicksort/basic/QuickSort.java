/**
 * B31-PA QuickSort Basic Sort
 */
package b01_sorts.quicksort.basic;

/**
 * Uses a recursive sort method to partition an array and then recursively sort each side of that
 * array until the entire thing is sorted.
 *
 * @author Jordan Wallingsford
 * @version 1.0
 */
public class QuickSort
{
    private static int partition(int[] a, int startIndex, int endIndex)
    {
        // pivot is the first element in the range [startIndex...endIndex]
        int pivot = a[startIndex];

        // start searching beginning with the startIndex
        int lowerIndex = startIndex;
        // the higher index to search to is the spot 1 farther right than the endIndex
        int higherIndex = endIndex+1;

        // checks and constantly swaps out items so that no entry to the left of pivot is greater than it
        // and no entry to the right of pivot is less than it.
        while (true)
        {
            // increment the index FIRST and then check if less than pivot and endIndex
            while (a[++lowerIndex] <= pivot && lowerIndex<endIndex) ;
            // decrement the index FIRST and then check if greater than pivot and startIndex
            while (a[--higherIndex] >= pivot && higherIndex > startIndex) ;

            // once they cross over, break
            if (lowerIndex >= higherIndex) break;

            int temp = a[lowerIndex];
            a[lowerIndex] = a[higherIndex];
            a[higherIndex] = temp;
        }

        //put the partition in the middle (higher index)
        int temp = a[startIndex];
        a[startIndex] = a[higherIndex];
        a[higherIndex] = temp;

        return higherIndex;
    }

    private static void sort(int[] a, int startIndex, int endIndex)
    {
        // if singleton or empty, do nothing
        if (startIndex >= endIndex) return;
        // get the pivot index from partition satisfying the 3 pivot rules
        int pivot = partition(a, startIndex, endIndex);
        // sort the left half
        sort(a, startIndex, pivot-1);
        // sort the right half
        sort(a, pivot+1, endIndex);

        // now it is sorted :)
    }

    public static void sort(int[] a)
    {
        // begins our quicksort on the entire array
        sort(a, 0, a.length - 1);
    }
}
