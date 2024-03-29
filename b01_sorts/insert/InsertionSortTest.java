/**
 * Module B12 Task 1 Insertion Sort Implementation
 *
 * @author Jordan Wallingsford
 * @version 1.0
 */
package b01_sorts.insert;

import b01_sorts.select.SelectionSort;
import util.array.ArrayUtility;

import java.util.Date;

public class InsertionSortTest
{
    /**
     * tests the insertion sort method
     * @param name name of the test STRING
     * @param arrLength length of the array int
     * @param min int minimum value for each element
     * @param max int maximum value for each element
     */
    public static void insertionSortTest(String name, int arrLength, int min, int max){
        int[] array = ArrayUtility.generateIntArray(arrLength,min,max);
        System.out.printf("Random Array with " + arrLength + " elements of range "+min+":"+max +" :\n%s\n ",
                ArrayUtility.toString(array, "[",", ","]"));
        InsertionSort.sort(array);
        System.out.printf("Sorted Array: \n%s\n",ArrayUtility.toString(
                array,"[",", ","]"
        ));
    }

    public static void main (String[] args)
    {
        System.out.println("Insertion Sort - Task 2 - Jordan Wallingsford");
        Date date = new Date();
        System.out.println("Executed On: " + date.toString());
        System.out.println("--------------------------");
        InsertionSortAnalysis.printMeanExecutionTimeGrowthTable(
                5, 100000,100000, 1000000,1, 100
        );
    }
}
