# Chapter 2

## Merge Sorts

Based on a simple operation known as *merging*: combining two arrays to make one larger ordered array. This automatically
leads to a simple recursive sort method known as *mergesort*: to sort an array, divide it into two halves, sort the two
halves (recursively), and then merge the results.

Merge sort guarantees to sort any array of N items in time proportional to NlogN. However, it uses space proportional
to N.

### Abstract in place merge

Straightforward implementation of merge sort is to design a method that merges two disjoint ordered arrays of Comparable
objects into a third array. 

Create an output array of the requisite size and then choose successively the smallest remaining item from the two input
arrays to be the next item added to the output array.

**Downside** : when the array is large, it involves a lot of merges so the cost of creating a new array to hold the 
output every time we do a merge is problematic. Using an in-place sort to sort the first half in place then sort the second
half in place, and finally merging those two halves would be desirable but hard to implement.

```java
private static void merge(Comparable[] a, int lo, int mid, int hi)
{  // Merge a[lo..mid] with a[mid+1..hi].
   int i = lo, j = mid+1;

   for (int k = lo; k <= hi; k++)  // Copy a[lo..hi] to aux[lo..hi].
      aux[k] = a[k];

   for (int k = lo; k <= hi; k++)  // Merge back to a[lo..hi].
      if      (i > mid)              a[k] = aux[j++];
      else if (j > hi )              a[k] = aux[i++];
      else if (less(aux[j], aux[i])) a[k] = aux[j++];
      else                           a[k] = aux[i++];
}
```

### Top Down Merge Sort

uses between ~1/2NlogN and NlogN compares to sort any array length N

```java
public class Merge
{
   private static Comparable[] aux;      // auxiliary array for merges

   public static void sort(Comparable[] a)
   {
      aux = new Comparable[a.length];    // Allocate space just once.
      sort(a, 0, a.length - 1);
   }

   private static void sort(Comparable[] a, int lo, int hi)
   {  // Sort a[lo..hi].
      if (hi <= lo) return;
      int mid = lo + (hi - lo)/2;
      sort(a, lo, mid);       // Sort left half.
      sort(a, mid+1, hi);     // Sort right half.
      merge(a, lo, mid, hi);  // Merge results (code on page 271).
   }
}
```

So to sort a sub-array of a[lo...hi] we divide it into two parts: a[lo..mid] and a[mid+1...hi], sort them both through
recursive sort calls and merge the resulting ordered subarrays to produce the result

Top down merge sort uses at most 6NlogN array accesses to sort an array of length N

The time required by mergesort is that of NlogN

### Improvements

#### Using insertion sort

we know that insertion sort is simple and therfore likely to be faster than mergesort for tiny arrays. We can use 
it to sort small subarrays. Implement insertion sort for arrays less than like 15 elements

#### Test if it is already in order

time will be linear if we skip the call to merge() if a[mid] is less than or equal to a[mid+1].

#### Eliminating the copy to the aux array
It is possible to eliminate the time (but not the space) taken to copy to the auxiliary array used for merging. 
To do so, we use two invocations of the sort method: one takes its input from the given array and puts the sorted output
in the auxiliary array; the other takes its input from the auxiliary array and puts the sorted output in the given 
array. With this approach, in a bit of recursive trickery, we can arrange the recursive calls such that the computation 
switches the roles of the input array and the auxiliary array at each level


### Bottom Up Merge Sort

Divide and conquer: divide a larger problem into smaller sub problems to solve

bottom up is organizing the merges so that we do all merges of tiny subarrays on one pass, then do a second pass to merge
those subarrays in pairs, and so forth. 

Start by doing a pass and merging 1-by-1 merges, then a pass of 2-by-2 merges, and then 4-by-4 merges and so on. 

```java
public class MergeBU
{
   private static Comparable[] aux;       // auxiliary array for merges

   // See page 271 for merge() code.

   public static void sort(Comparable[] a)
   {  // Do lg N passes of pairwise merges.
      int N = a.length;
      aux = new Comparable[N];
      for (int sz = 1; sz < N; sz = sz+sz)        // sz: subarray size
         for (int lo = 0; lo < N-sz; lo += sz+sz) // lo: subarray index
            merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
   }
}
```

This uses between ~1/2NlogN and NlogN compares and at most 6NlogN array accesses to sort an array of length N
When an array length is a power of 2, top-down and bottom-up perform precisely the same compares and array accesses, just
in different order. When it isn't a power of 2, the numbers will be different.

A version of BU Merge sort is the method of choice when sorting data in a linked list. 


If all items in an array are the same value, merge sort time is linear.