package b01_sorts.shell;

public class ShellSort
{
    /**
     * Shell short is a faster implementation of insertion sort to better handle large unsorted arrays.
     * this is implemented through h-sorting. It sorts items h-distance apart.
     * It begins with h as a larger number, and then decrements h until finally it is insertion sorting a mostly sorted
     * array
     */
    public static void sort(int[] a)
    {
        int N = a.length;
        int h = 1;
        while (h < N/3) h = 3 * h+1; // this is using knuths algorithm for increment sequences
        while (h >= 1)
        { // while h >= 1, h-sort the array
            for (int i = h; i < N; i++) // insert a[i] among the various h-groups
            {
                for (int j=i; j>=h && less(a[j], a[j-h]); j-=h)
                {
                    int t = a[j];
                    a[j] = a[j-h];
                    a[j-h] = t;
                }
            }
            h = h/3;
        }
    }

    public static void instructorsSort(int[] a) {
        // compute the smallest term of the sequence:
        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ...
        // that is larger than one third of the length of the array
        int h = 1;
        while (h < a.length/3) h = 3*h + 1;
        // take all the terms h in the sequence starting to the found one
        // and going left toward 1, including 1
        // when h=1 we do an insertion sort - this will guarantee that the array will be sorted
        while (h >= 1) {
            // h-sort the array
            // for each element in the array, starting to h
            // the first h elements are the starting element in their corresponding h-arrays
            // -   0, h, 2h, 3h, 4h, ...
            // -   1, h+1, 2h+1, 3h+1, ....
            // -   ....
            // -   h-1, 2h-1, 3h-1, ...
            // they are singletons in those arrays, so we start with h which is the first to be inserted
            for (int i = h; i < a.length; i++) {
                // we apply a kind of insertion sort on a subarray:
                // i%h, i%h+h, ... , i-3h, i-2h, i-h, i    (the first element is between 0 and h-1, the reminder of division of i by h)
                // save the element to be inserted in temp
                int temp = a[i];
                // we will find the position in which we insert the element in j
                // start with the element i position and h-insert to the leftt
                int j=i;
                // j will take all the indexes from right to left in the sub-array
                // i%h, i%h+h, ... , i-3h, i-2h, i-h, i
                // until we reach the start of the array (j<h) or we found the insertion point (an element less or equal with the current element)
                for (; j >= h && temp<a[j-h]; j -= h) {
                    // move the elements to the right to make space to insert a[i] stored in temp
                    a[j]=a[j-h];
                }
                // j is the insertion position, place temp (original a[i]) in the place we prepared (all the elements were moved to the right)
                a[j]=temp;
            }
            // all the h-subarrays are sorted
            // go to the left (smaller) h in the sequence above 1, 4, 13, 40, 121, 364, 1093, ...
            h /= 3;
        }
        // last h was 1, we performed insertion sort in an almost sorted array, very fast
    }

    public static boolean less(Comparable v, Comparable w)
    {
        return v.compareTo(w) < 0;
    }
}

