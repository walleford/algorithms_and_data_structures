/**
 * B13 Task 1: Shell Sort
 *
 * @author Jordan Wallingsford
 * @version 1.0
 */
package b01_sorts.shell;

import util.array.ArrayUtility;

import java.util.Date;

public class ShellSortTest
{
    /**
     * tests the shell sort method
     * @param name name of the test STRING
     * @param arrLength length of the array int
     * @param min int minimum value for each element
     * @param max int maximum value for each element
     */
    public static void insertionSortTest(int arrLength, int min, int max){
        int[] array = ArrayUtility.generateIntArray(arrLength,min,max);
        System.out.printf("Random Array with " + arrLength + " elements of range "+min+":"+max +" :\n%s\n ",
                ArrayUtility.toString(array, "[",", ","]"));
        ShellSort.sort(array);
        System.out.printf("Sorted Array: \n%s\n",ArrayUtility.toString(
                array,"[",", ","]"
        ));
    }


    public static void main (String[] args)
    {
        System.out.println("Shell Sort - Task 2 - Jordan Wallingsford");
        Date date = new Date();
        System.out.println("Executed On: " + date.toString());
        System.out.println("--------------------------");
        ShellSortAnalysis.printMeanExecutionTimeGrowthTable(10,100000,50000,500000,
                1,100);
    }
}
