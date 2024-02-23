package b01_sorts.topdown;

public class MergeArray
{
    public static void mergeAux(int[] a, int[] aux, int start1, int end1, int end2)
    {
        /**
         * This is copying the original array to the aux array
         */
        for (int i = start1; i <= end2; i++)
        {
            aux[i] = a[i];
        }

        /**
         * We use three indexes here: i, j, and k
         *
         * i: the index for the left array being merged
         * j: for the right array being merged
         * k: for the aux array index
         *
         */
        int i = start1;
        int j = end1+1;

        /**
         * This will merge from the aux array back into the original array.
         * The logic is: from index 0 to the highest index (hi), if the left half is exhausted, take from the right
         * half. If the right half is exhausted, take from the left half. If  the current key on the right is less than
         * the current key on the left, take from the right. Else, (the current key on the right is greater than or equal
         * to the key on the left) we take from the left.
         */
        for (int k = start1; k <= end2; k++)
        {
            if (i > end1) // if the left half is exhausted
            {
                a[k] = aux[j++];
            } else if (j > end2) { // if the right half is exhausted
                a[k] = aux[i++];
            } else if (aux[j] < aux[i]) { // if current key on right is greater than the left key
                a[k] = aux[j++];
            } else // else the left key is less than the right key so add it to the original array
            {
                a[k] = aux[i++];
            }
        }
    }
}
