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

