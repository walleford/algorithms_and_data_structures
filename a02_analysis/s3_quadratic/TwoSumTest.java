package a02_analysis.s3_quadratic;

import java.util.concurrent.ThreadLocalRandom;

public class TwoSumTest
{

    public static int[] randomArray(int size)
    {
        int[] a = new int[size];
        for (int i = 0; i < size; i++)
        {
            a[i] = ThreadLocalRandom.current().nextInt(-1000000, 1000000);
        }
        return a;
    }


    public static void main(String[] args)
    {
        int[] t1 = randomArray(1000);
        System.out.println("TEST 1 Count: " + TwoSum.count(t1));
        int[] t2 = randomArray(1000);
        System.out.println("TEST 2 Count: " + TwoSum.count(t2));
    }
}
