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

## Quick Sort

- popular because it isn't difficult to implement, works well for a variety of use-cases, and is substantially faster 
than any other sorting algorithm
- uses an in-place sort, requires time proportional to NlogN
- None of the algorithms we have implemented combine both of these properties
- Quicksort has a shorter inner loop than most other algorithms
- How it works:

  - It is a divide and conquer
  - partitions an array into two subarrays, then sorts those separately. 
  - rearrange the array such that, when the two subarrays are sorted, the entire thing is sorted
  ```java
    public class Quick
    {
    public static void sort(Comparable[] a)
    {
    StdRandom.shuffle(a);          // Eliminate dependence on input.
    sort(a, 0, a.length - 1);
    }
    
    private static void sort(Comparable[] a, int lo, int hi)
    {
    if (hi <= lo) return;
    int j = partition(a, lo, hi);  // Partition (see page 291).
    sort(a, lo, j-1);              // Sort left part a[lo .. j-1].
    sort(a, j+1, hi);              // Sort right part a[j+1 .. hi].
    }
    }
    ```
  - The crux of the method is `partition()` which rearranges the array such that 
    
    - the entry a[j] is in its final place in the array for some j
    - no entry in a[lo] through a[j-1] is greater than a[j]
    - no entry in a[j+1] is less than a[j]
- Steps:

  - arbitrarily choose a[lo] as the partition item
  - scan from left to right until we find an entry greater than or equal to the partition item
  - scan from right to left until we find an entry less than or equal to the partition item
  - these 2 items are out of place, so we swap them (putting the larger one on the right side of the partition and the 
  smaller one on the left side)
  - doing this continuously ensures that no entry to the right of the index `j` are less than the partitioning item
  - When we finish, we just replace a[lo] with the rightmost entry in the first/left subarray a[j] and return its index j

### Performance Analysis

- inner loop of quicksort increments an index and compares an array entry against a fixed value. This simplicity
is one factor that makes quicksort quick: it is hard to envision a shorter inner loop in a sorting algorithm
- best case for quicksort is when each partitioning stage divides the array exactly in half.
- When keys aren't distinct, precise number of compares is more complicated but it will never be greater than Cn
- However, if the partitions are unbalanced, quicksort can become very inefficient. This is the benefit of shuffling
the array, it makes the probability of that so unlikely that we don't have to worry about it.

### Improvements

- use insertion sort if the array length is tiny (any value between 5-15)
- use the median of 3 items in the array as the selected partitioning item
- In the case of many of the same elements:

  - utilize a triple partitioning system: left = less than, middle = equal to, right = greater than.
  ```java
    public class Quick3way
    {
    private static void sort(Comparable[] a, int lo, int hi)
    {  // See page 289 for public sort() that calls this method.
    if (hi <= lo) return;
    int lt = lo, i = lo+1, gt = hi;
    Comparable v = a[lo];
    while (i <= gt)
    {
    int cmp = a[i].compareTo(v);
    if      (cmp < 0) exch(a, lt++, i++);
    else if (cmp > 0) exch(a, i, gt--);
    else              i++;
    }  // Now a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
    sort(a, lo, lt - 1);
    sort(a, gt + 1, hi);
    }
    }
    ```
  

#### Questions to Know:
  - Which of the following methods has the best average running time for random arrays of 10 elements? 
    (if one method has best time and another other times, select both - if one methods always perform an addition 
    operation than will not be considered)

    Response Feedback:
    Insertion Sort works best for small arrays and has the smallest overhead. 
    Even optimized quick sort or optimized top-down merge sort, will have one additional comparison because 
    they will need to see if insertion sort is applied for the size of the input array. 

  - Which of the following methods has an average execution time of O(N*N)? (O - order of growth)
    Selection Sort uses around N2/2 compares and N exchanges to sort an array of length N, 
    giving an order of growth of O(N2) (see textbook)
    
    Insertion Sort, for randomly ordered arrays of length N with with distinct keys, insertion sort uses around N2/4 
    compares and around N2/4 exchanges on the average. The worst case is around N2/2 compares and around N2/2 exchanges 
    and the best case is N-1 compares and 0 exchanges. (see textbook) Therefore the average order of growth is O(N2) 
    with the worst case also O(N2) but with the best case O(N).
    
    Shell Sort with the increments 1, 4, 13, 40, 121, 364, ... has the number of compares used with the order of growth 
    of O(N3/2). (see textbook)
    
    Top Down Merge Sort uses between 1/2 N lg N and N lg N compares and at most 6 N lg N array accesses to sort any array 
    of length N (see textbook) Therefore, the order of growth is O(N lg N).
    
    Optimized Top Down Merge Sort, will execute faster but the order of growth will be the same O(N lg N).
    
    Bottom Up Merge Sort, uses between 1/2 N lg N and N lg N compares and at most 6 N lg N array accesses to sort any array 
    of length N. (see textbook) Therefore, the order of growth is O(N lg N).
    
    Quicksort uses around 2 N ln N compares (and one-sixth that many exchanges) on the average to sort an array of 
    length N with distinct keys (see textbook) However, it uses N2/2 compares in the worst case (see textbook). 
    The average execution time is O(N ln N) with worst case O(N2).
    
    Optimized QuickSort runs faster but with the same average order of growth. However, the probability of worst case 
    scenario for typical arrays is reduced. 

  - Which of the following methods has around the same running time for all arrays of 10,000 elements?

    Selection sort, top down merge sort and bottom up merge sort will do the same operations idenpendent of the specific
    values in the arrays. However, insertion sort, shell sort, optimized top down merge sort, quick sort and optimized 
    quick sort will run much faster for some arrarys compared with others. 

  - Which of the following methods will execute much faster for some arrays of 10,000 elements?

    Selection sort, top down merge sort and bottom up merge sort will do the same operations idenpendent of the 
    specific values in the arrays. However, insertion sort, shell sort, optimized top down merge sort, quick sort and 
    optimized quick sort will run much faster for some arrarys compared with others. 

  - Which of the following methods has (sometimes) the worse running time for an already sorted array of 10,000 elements?
  (if one method has best time and another other times, select both)

    Quick Sort has O(N*N) time for an already sorted array performing N(N-1) comparisons and N-1 recursive calls on the 
    stack. All the other methods are at least a little faster.

  - Which of the following methods has (sometimes) the best running time for an already sorted array of 10,000 elements?
  (if one method has best time and another other times, select both)

    Insertion Sort has O(N) time for an already sorted array performing only N-1 comparisons and not moving any elements
    All the other methods are much slower.

  - For the standard selection sort method select all that apply:

    For a given array length, the running time will be almost equal for any two different arrays