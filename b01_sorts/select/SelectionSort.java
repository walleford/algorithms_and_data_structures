/**
 * B11 Task 2: Selection Sort
 * Contains various versions of the selection sort algorithm
 */
package b01_sorts.select;

public class SelectionSort
{
    public static void sort(int[] array)
    {
        // select each element from the array except the last
        for (int i=0; i<array.length-1;i++)
        {
            // find the index of the minimum of the array
            int iMin = i;
            int min = array[i];
            // compare the next element to the current min
            for (int j=i+1;j< array.length;j++)
            {
                // if the next element is less than the current min, set the current min to the next index
                if(array[j]<min)
                {
                    iMin = j;
                    min = array[j];
                }
            }
            array[iMin] = array[i];
            array[i] = min;
        }
    }
}
