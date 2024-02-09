package a02_analysis.s5_cubic;

import a02_analysis.s3_quadratic.TwoSum;

import java.util.concurrent.ThreadLocalRandom;

public class TestThreeSum
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
        System.out.println("TEST 1 Count: " + ThreeSum.count(t1));
        int[] t2 = randomArray(1000);
        System.out.println("TEST 2 Count: " + ThreeSum.count(t2));
    }
}
