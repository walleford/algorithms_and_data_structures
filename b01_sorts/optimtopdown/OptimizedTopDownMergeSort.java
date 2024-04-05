package b01_sorts.optimtopdown;
import b01_sorts.topdown.MergeArray;
import b01_sorts.insert.InsertionSort;
public class OptimizedTopDownMergeSort
{

    private static void sort(int[] a, int[] aux, int hi, int lo, int insertionSortSize)
    {
        // if hi <= lo then it is either empty or a singleton array so its sorted
        if (hi <= lo)
            return;

        // insertionSortSize sets a threshold for when to use merge sort and when to use insertionSort
        // if the bottom index added to the insertion sort size is less than the high index then we should just
        // utilize insertion sort.
        if (hi < lo + insertionSortSize)
        {
            /**
             * Rearranges the elements of an array of int values in ascending order
             * Uses the insertion sort method for the implementation
             * For/Each position in the initial array, move the value to the left until the array is sorted
             * Check if the left value is less than this one, move on to that one to make sure
             *
             */
            for (int i = lo+1; i<=hi;i++)
            {
                int temp = a[i];
                int j;
                for (j = i; j > lo && temp<a[j-1]; j--)
                {
                    a[j] = a[j-1];
                }
                a[j] = temp;
            }
            return;
        }

        // if it doesn't use the insertion sort, we will recursively call sort for the left and right halves of the
        // array until we get a merge sorted array
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid, insertionSortSize);
        sort(a, aux, mid + 1, hi, insertionSortSize);
        MergeArray.mergeAux(a, aux, lo, mid, hi);
    }

    /**
     *
     * This is the public class that will be called by the client. If the initial array is less than the insertion
     * threshold, we will just insertion sort the array.
     * @param a integer array
     * @param insertionSortSize threshold for insertion sorting
     */
    public static void sort(int[] a, int insertionSortSize)
    {
        if (a.length <= insertionSortSize)
        {
            InsertionSort.sort(a);
            return;
        }

        int[] aux = new int[a.length];
        sort(a,aux,0,a.length-1,insertionSortSize);

    }
}
