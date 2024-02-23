package b01_sorts.topdown;
import util.array.ArrayUtility;
import java.util.Date;

public class MergeArrayTest
{
    public static boolean mergeAuxTest(String name, int[] a1, int start1, int end1, int end2, int[] expected)
    {
        int[] aux = new int[a1.length];

        System.out.println("Test: " + name);
        System.out.println("Pre-mergesort: " + ArrayUtility.toString(a1, "[", ", ", "]"));
        System.out.println("--------------------------------");
        System.out.println("Passing to mergesort now...");
        MergeArray.mergeAux(a1, aux, start1, end1, end2);
        System.out.println("Result: " + ArrayUtility.toString(a1, "[", ", ", "]"));
        boolean result = ArrayUtility.equals(a1, expected);
        System.out.println("Expected Result: " + ArrayUtility.toString(expected, "[", ", ", "]"));
        if (result) System.out.println("Test successful!");
        else System.out.println("Test unsuccessful :(");
        System.out.println("--------------------------------");
        return result;
    }

    public static void mergeAuxAllTests()
    {
        boolean result = true;
        result = result & mergeAuxTest("both length 1",
                new int[] {10,6,32,1,12}, 1,1,2,
                new int[] {10,6,32,1,12});
        result = result & mergeAuxTest("both length 2",
                new int[] {13,23,11,45,21,67}, 0,1,3,
                new int[] {11,13,23,45,21,67});
        result = result & mergeAuxTest("both length 3",
                new int[] {11,33,44,22,55,77,1111}, 0,2,5,
                new int[] {11,22,33,44,55,77,1111});
        result = result & mergeAuxTest("both length 5",
                new int[] {-5,-3,0,1,5,-9,-7,2,4,6}, 0,4,9,
                new int[] {-9,-7,-5,-3,0,1,2,4,5,6});
        result = result & mergeAuxTest("length 6 and 7",
                new int[] {1111,3333,6666,7777,8888,9999,1112,2223,3334,4445,5556,6667,7778}, 0,5,12,
                new int[] {1111,1112,2223,3333,3334,4445,5556,6666,6667,7777,7778,8888,9999});
        System.out.println("All mergeAux tests successful?:    " + result);
    }

    public static void main(String[] args)
    {
        System.out.println("Merge Sort Task 1 - Jordan Wallingsford");
        Date date = new Date();
        System.out.println("Executed On: " + date.toString());
        System.out.println("--------------------------");
        mergeAuxAllTests();
    }
}
