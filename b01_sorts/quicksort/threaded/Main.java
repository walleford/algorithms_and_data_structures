package b01_sorts.quicksort.threaded;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class Main
{
    //private static Random random = new Random();
    //private static int arraySize = 20;
    //private static int[] array = new int[arraySize];
    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
    }

    static class QuickSortTask extends RecursiveTask
    {
        private final int left;
        private final int right;
        int[] array;
        private final int maxInsert;
        public QuickSortTask(int[] array, int left, int right, int maxInsert)
        {
            this.array = array;
            this.left = left;
            this.right = right;
            this.maxInsert = maxInsert;
        }
        @Override
        protected Object compute()
        {
            if (right <= left) return null;
            // if singleton or empty, do nothing
            if (right < left + maxInsert)
            {
                for (int i  = left + 1; i <= right; i++)
                {
                    int temp = array[i];
                    int j;
                    for (j = i; j > left && temp<array[j-1]; j--)
                    {
                        array[j] = array[j-1];
                    }
                    array[j] = temp;
                }
                return null;
            }
            // get the pivot index from partition satisfying the 3 pivot rules
            int pivotIndex = partition(array, left, right);
            // sort the left half

            invokeAll(
                    new QuickSortTask(array, left, pivotIndex -1, maxInsert),
                    new QuickSortTask(array, pivotIndex + 1, right, maxInsert)
            );
            return null;
        }
    }

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
}
