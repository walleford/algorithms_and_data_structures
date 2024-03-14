# Chapter 3: Searching

## 3.1 Symbol Tables

  - primary purpose of a symbol table is to associate a value with a key
  - client can insert key-value pairs into the symbol table with the expectation of later being able to search for the 
    value associated with a given key
  - To implement:

    - define the underyling data structure
    - specify the algorithms for insertion of new pairs, and searching existing pairs
    - specify operations that create and manipulate the data structure
  - Symbol table API consists of:

    - `public class ST<Key, Value>`: class definition
    - `ST()`: creates a new symbol table
    - `void put(Key key, Value val)`: put key-value pair into the table and remove key from table if value is `null`
    - `Value get(Key key)`: return the value paired with provided key
    - `void delete(Key key)`: remove key and its value from the table
    - `boolean isEmpty()`: is the table empty?
    - `boolean contains(Key key)`: is there a value paired with this key?
    - `int size()`: number of key-value pairs in the table
    - `Iterable<Key> keys()`: return all the keys in the table
  - we adopt the following assumptions:
    
    - only one value is associated with each key (no duplicate keys in a table)
    - when a client puts a key-value pair into a table already containing that key, the new value replaces the old
  - This defines the *associative array abstraction* 
  - Keys must no be null
  - Values should not be null
  - Deletion

    - deletion in symbol tables generally involves one of two strategies: lazy deletion, where we simply associate the 
      keys with null and eager deletion, where we remove the key from the table immediately
  - Iteration

    - to enable clients to process all keys and values in the table, we specify a `keys()` method that returns an
      `Iterable<Key>` object for clients to use to iterate through the keys.

### Ordered Symbol Tables

  - Several symbol-table implementations take advantage of order among the keys that is implied in Comparable objects
    to provide efficient implementations of the `put()` and `get()` methods. 
  - For applications where the keys are comparable this is our API:

    - `public class ST<Key extends Comparable<Key>, Value>` : class definition
    - `ST()`                                                : creates a new symbol table
    - `void put(Key key, Value val)`                        : put key-value pair into the table and remove key from 
                                                              table if value is `null`
    - `Value get(Key key)`                                  : return the value paired with provided key
    - `void delete(Key key)`                                : remove key and its value from the table
    - `boolean isEmpty()`                                   : is the table empty?
    - `boolean contains(Key key)`                           : is there a value paired with this key?
    - `int size()`                                          : number of key-value pairs in the table
    - `Key min()`                                           : smallest key
    - `Key max()`                                           : largest key
    - `Key floor(Key key)`                                  : largest key less than or equal to key
    - `Key ceiling(Key key)`                                : smallest key greater than or equal to key
    - `int rank(Key key)`                                   : number of keys less than key
    - `Key select(int k)`                                   : key of rank k
    - `void deleteMin()`                                    : delete smallest key
    - `void deleteMax()`                                    : delete largest key
    - `int size(Key lo, Key hi)`                            : number of keys in [lo...hi]
    - `Iterable<Key> keys(Key lo, Key hi)`                  : keys in [lo...hi] in sorted order
    - `Iterable<Key> keys()`                                : return all the keys in the table

### Cost Model

  - when studying symbol-tables we count compares (equality tests or key comparisons). This is referring to the operation
    of comparing a symbol-table entry against the search key.

### Sequential Search unordered

  - Basic frequency counter is a sequential search. This is when we search by considering the keys in a table one after
    another, using `equals()` to test for a match with our search key.
  - Average cost of sequential search is roughly N/2 or (N+1)/2 accesses
  - it is too slow for large amounts of data. Total number of compares is proportional to the product of the number of
    searches and the number of inserts. 

### Binary Search in an Ordered Array

  - this involves the ordered symbol table API above.
  - The heart of the implementation is the `rank()` method, which returns the number of keys smaller than a given key.
  - The rank tells us precisely where a key is found in the table, and whether it is in the table or not. 
  - For `put()`, the rank tells us exactly where to update the value when the key is in the table, and where to put the
    key when the key is not in the table.
  - carries the inconvenience of having to create a Key array of type Comparable and a Value array of type Object, and
    to cast them back to Key[] and Value[] in the constructor.

### Binary Search

  - maintain indices in the array that delimit the subarray that might contain the search key. To search, we compare
    the search key against the key in the middle of the subarray. If the search key is less than the key in the middle, 
    we search in the left half of the subarray. If it is greater than the middle key, we search in the right half.
    Otherwise, it is equal to the middle.

### Analysis of Binary Search

  - Binary search in an ordered array with N keys uses no more than lg N + 1 compares for a search (successful or unsuccessful).
  - Inserting a new key into an ordered array of size N uses ~ 2N array accesses in the worst case, so inserting N keys
  into an initially empty table uses ~ N2 array accesses in the worst case.