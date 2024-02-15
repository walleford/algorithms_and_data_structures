package util.array;

import java.util.Date;

public class ArrayUtilityTest
{

    public static void equalsTest()
    {
        int[] a1 = {};
        int[] a2 = {};
        int[] singleton1 = {1};
        int[] singleton2 = {2};
        int[] singleton3 = {2};
        int[] doubleArr1 = {1,2};
        int[] doubleArr2 = {1,2};
        int[] doubleArr3 = {4,3};
        System.out.println("Two Empty Arrays: "+ArrayUtility.equals(a1, a2));
        System.out.println("One Empty One Singleton: "+ArrayUtility.equals(a1,singleton1));
        System.out.println("Two Same Singletons: "+ArrayUtility.equals(singleton2,singleton3));
        System.out.println("Two Diff Singletons: "+ArrayUtility.equals(singleton1, singleton2));
        System.out.println("Two Element Same Arrays: "+ArrayUtility.equals(doubleArr1,doubleArr2));
        System.out.println("Two Element Diff Arrays: "+ArrayUtility.equals(doubleArr1,doubleArr3));
    }

    public static void toStringTest()
    {
        int[] empty = {};
        System.out.println("Empty Array: "+ArrayUtility.toString(empty, "{"," ","}"));

        int[] singleton = {1};
        System.out.println("Singleton Array: "+ArrayUtility.toString(singleton, "{"," ","}"));

        int[] twoElements = {1, 2};
        System.out.println("Two Elements Array: "+ArrayUtility.toString(twoElements, "{"," ","}"));

    }

    public static void isSortedTest()
    {
        int[] sortedArray = {1,2,3,4};
        int[] unsortedArray = {3,2,1};
        System.out.println("Sorted Array ({1,2,3,4}): " + ArrayUtility.isSorted(sortedArray));
        System.out.println("Unsorted Array ({3,2,1}): " + ArrayUtility.isSorted(unsortedArray));
    }

    public static void generationTest()
    {
        int[] generation = ArrayUtility.generateIntArray(6,1,20);
        System.out.println("Generating an array of length 6, min 1, and max 20");
        System.out.println("Array: " + ArrayUtility.toString(generation,"[",", ","]"));
    }

    public static void main(String[] args)
    {
        System.out.println("Selection Sort - Task 4 - Jordan Wallingsford");
        Date date = new Date();
        System.out.println("Executed On: " + date.toString());
        System.out.println("--------------------------");
        generationTest();
    }
}
