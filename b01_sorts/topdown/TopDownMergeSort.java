/**
 * Section B21 Assignment Top-Down Merge Sort
 */
package b01_sorts.topdown;
import util.array.ArrayUtility;
import a02_analysis.s1_time.Stopwatch;


/**
 * To sort a sub-array of a[lo...hi] we divide it into two parts: a[lo..mid] and a[mid+1...hi], sort them both through
 * recursive sort calls and merge the resulting ordered subarrays to produce the result
 *
 * @author Jordan Wallingsford
 * @version 1.0
 */
public class TopDownMergeSort
{
    public static Comparable aux[]; // this is the auxiliary array

    /**
     * This is the public method called from the client, it will create a new comparable object for aux, and call the
     * private sort method. That aux array will be modified and added to in the merge method.
     * @param a an array of comparable type
     */
    public static void sort(Comparable[] a)
    {
        aux = new Comparable[a.length];    // Allocate space just once.
        sort(a, 0, a.length - 1); // lo is a[0] high is a[a.length-1]
    }

    /**
     * This function is recursive UNTIL the array is sorted all the way down to single elements, at that point
     * merge() is called to merge the array back up into the original array, but sorted.
     *
     * @param a Comparable array
     * @param lo int of the lowest index
     * @param hi int of the highest index (array length - 1)
     */
    private static void sort(Comparable[] a, int lo, int hi)
    {  // Sort a[lo..hi].
        // If our array length is the same as our low index, then our array is split into single elements. Which means
        // we can return and begin merging back up because we have slit it far enough.
        if (hi <= lo) return;
        int mid = lo + (hi - lo)/2; // calculating the mid-point of the array to sort both sides of that mid-point
        sort(a, lo, mid);       // Sort left half.
        sort(a, mid+1, hi);     // Sort right half.
        merge(a, lo, mid, hi);  // Merge results.
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi)
    {  // Merge a[lo...mid] with a[mid+1...hi].
        /**
         * We use three indexes here: i, j, and k
         *
         * i: the index for the left array being merged
         * j: for the right array being merged
         * k: for the aux array index
         *
         */
        int i = lo, j = mid+1;

        for (int k = lo; k <= hi; k++)  // Copy a[lo..hi] to aux[lo..hi].
            aux[k] = a[k];

        /**
         * This will merge from the aux array back into the original array.
         * The logic is: from index 0 to the highest index (hi), if the left half is exhausted, take from the right
         * half. If the right half is exhausted, take from the left half. If  the current key on the right is less than
         * the current key on the left, take from the right. Else, (the current key on the right is greater than or equal
         * to the key on the left) we take from the left.
         */
        for (int k = lo; k <= hi; k++)  // Merge back to a[lo..hi].
            if      (i > mid)              a[k] = aux[j++]; // check if left half is exhausted
            else if (j > hi )              a[k] = aux[i++]; // check if right half is exhausted
            else if (aux[j].compareTo(aux[i]) > 0) a[k] = aux[j++]; // is the current key on right less than the left?
            else                           a[k] = aux[i++]; // is the current key on the left less than the right?
    }
}
