package util.array;

import java.util.Date;

public class ArrayUtility
{
    /**
     * This will take in an array and return a string representation of it, utilizing the
     * start, end, and separator variables to specify how to delineate between elements.
     *
     * @param array - array to be output
     * @param start - string object to represent the beginning of the array
     * @param separator - string to represent the space between elements
     * @param end - string to represent the end of the array
     * @return String result representing the array
     */
    public static String toString(int[] array, String start, String separator, String end)
    {
        String result = start;
        boolean needSeparator = false;
        for (int elem: array)
        {
            if (needSeparator) result += separator;
            result += elem;
            needSeparator = true;
        }
        result += end;
        return result;
    }

    /**
     * Tests whether or not two arrays are equal length and have the same elements
     *
     * @param a1 first array of ints
     * @param a2 second array of ints
     * @return boolean whether or not 2 arrays have same length and elements
     */
    public static boolean equals(int[] a1, int[] a2)
    {
        if (a1.length != a2.length) return false;
        for (int i = 0; i<a1.length; i++)
        {
            if (a1[i]!=a2[i]) return false;
        }
        return true;
    }


    /**
     * For an array to be sorted, it should be in ascending order. So if an element is larger than the
     * next element in the array, it isn't sorted.
     * @param a array to check
     * @return boolean of whether or not it is sorted
     */
    public static boolean isSorted(int[] a)
    {
        for (int i=0; i<a.length-1; i++){
            if (a[i]>a[i+1]) return false;
        }
        return true;
    }


    /**
     * Generates a random array of provided length, with elements randomly generated between a min and max value
     * @param length length of array
     * @param min minimum a value can be
     * @param max maximum a value can be
     * @return the generated array
     */
    public static int[] generateIntArray(int length, int min, int max)
    {
        int[] array = new int[length];
        for (int i = 0; i<length;i++)
        {
            array[i] = (int) (min+Math.random()*(1.0+max-min));
        }
        return array;
    }

}
