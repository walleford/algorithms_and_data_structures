/**
 * B23 Bottom Up Merge Sort
 */
package b01_sorts.bottomUpMerge;
import b01_sorts.topdown.MergeArray;

/**
 * Bottom up is organizing the merges so that we do all merges of tiny subarrays on one pass, then do a second pass to
 * merge those subarrays in pairs, and so forth. Start by doing a pass and merging 1-by-1 merges, then a pass of 2-by-2
 * merges, and then 4-by-4 merges and so on. This uses between ~1/2NlogN and NlogN compares and at most 6NlogN array
 * accesses to sort an array of length N. When an array length is a power of 2, top-down and bottom-up perform precisely
 * the same compares and array accesses, just in different order. When it isn't a power of 2, the numbers
 * will be different.
 *
 * @author Jordan Wallingsford
 * @version 1.0
 */
public class BottomUpMergeSort
{
    public static void sort(int[] a)
    {
        // auxiliary array to be copied into
        int[] aux = new int[a.length];

        //
        for (int i = 1; i < a.length; i *= 2)
        {
            for (int lo = 0; lo < a.length - i; lo += i+i)
            {
                int mid = lo + i - 1;
                int hi = Math.min(lo + i + i - 1, a.length - 1);
                MergeArray.mergeAux(a, aux, lo, mid, hi);
            }
        }
    }
}
