/**
 * B32-PA QuickSort Optimization
 */
package b01_sorts.quicksort.optim;

public class OptimizedQuickSort
{
    /**
     * Maximum default size of the subarray for which we apply insertion sort
     */
    private static int MAX_INSERTION_SIZE = 10;
    private static int partition(int[] a, int startIndex, int endIndex)
    {
        // pivot is the first element in the range [startIndex...endIndex]
        median3(a, startIndex, endIndex);
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

    private static void sort(int[] a, int startIndex, int endIndex, int maxInsert)
    {
        if (endIndex <= startIndex) return;
        // if singleton or empty, do nothing
        if (endIndex < startIndex + maxInsert)
        {
            for (int i  = startIndex + 1; i <= endIndex; i++)
            {
                int temp = a[i];
                int j;
                for (j = i; j > startIndex && temp<a[j-1]; j--)
                {
                    a[j] = a[j-1];
                }
                a[j] = temp;
            }
            return;
        }
        // get the pivot index from partition satisfying the 3 pivot rules
        int pivotIndex = partition(a, startIndex, endIndex);
        // sort the left half
        sort(a, startIndex, pivotIndex-1, maxInsert);
        // sort the right half
        sort(a, pivotIndex+1, endIndex, maxInsert);

        // now it is sorted :)
    }

    /**
     * This will take a range of elements from an array array[startIndex ... endIndex] and calculate the median of
     * those values and place it in the startIndex position of the array.
     * @param array int[] array of elements
     * @param startIndex int index to use for one of the 3 values to compare
     * @param endIndex int end index to use as one of the values to compare
     */
    private static void median3(int[] array, int startIndex, int endIndex)
    {
        // put the median value in the start position
        // median = value in the middle of the smaller and larger
        // 10, 40, 30 : 30 is median
        int midIndex = (startIndex + endIndex)/2;
        int a = array[startIndex];
        int b = array[midIndex];
        int c = array[endIndex];
        if (a<b)
        {
            if (b<c)
            { // a < b < c
                int temp = array[startIndex];
                array[startIndex] = array[midIndex];
                array[midIndex] = temp;
            } else if (a<c)
            {
                // a < c < b
                int temp = array[startIndex];
                array[startIndex] = array[endIndex];
                array[endIndex] = temp;
            }
        } else { // b < a
            if (a < c) {
                // b < a < c
                // a is the median so its already in the right spot
            } else if ( b < c)
            { // b < c < a
                int temp = array[startIndex];
                array[startIndex] = array[endIndex];
                array[endIndex] = temp;
            } else
            { // c < b < a
                int temp = array[startIndex];
                array[startIndex] = array[midIndex];
                array[midIndex] = temp;
            }
        }
    }

    public static void sort(int[] a)
    {
        // begins our quicksort on the entire array
        sort(a, 0, a.length - 1, MAX_INSERTION_SIZE);
    }
}
