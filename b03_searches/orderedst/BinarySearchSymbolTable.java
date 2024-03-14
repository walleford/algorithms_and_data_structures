package b03_searches.orderedst;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BinarySearchSymbolTable<Key extends Comparable<Key>, Value> implements OrderedSymbolTable<Key, Value>
{
    private static final int INITIAL_CAPACITY = 2;

    /**
     * Array of keys
     */
    private Key[] keys;

    /**
     * Array of values
     */
    private Value[] values;

    private int numberOfKeys = 0;

    /**
     * Creates the default symbol table.
     */
    public BinarySearchSymbolTable()
    {
        this(INITIAL_CAPACITY);
    }

    /**
     * Initializes the empty symbol table with the specified initial capacity
     * @param capacity : max capacity
     */
    public BinarySearchSymbolTable(int capacity)
    {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    /**
     * Resizes the array to the given capacity
     * @param capacity : int capacity to resize to.
     */
    private void resize(int capacity)
    {
        if (capacity < numberOfKeys)
            throw new RuntimeException("The resized capacity is too small");
        Key[] tempkeys = (Key[]) new Comparable[capacity];
        Value[] tempValues = (Value[]) new Object[capacity];

        for (int i = 0; i < numberOfKeys; i++)
        {
            tempkeys[i] = keys[i];
            tempValues[i] = values[i];
        }
        values = tempValues;
        keys = tempkeys;
    }

    /**
     * Returns the value associated with a specific key provided
     *
     * @param key : the key to return the value for
     * @return : value associated with the given key if the key exists, null if it doesn't.
     *
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key)
    {
        if (key == null)
            throw new IllegalArgumentException("argument to get() is null");
        if (isEmpty())
            return null;
        int i = rank(key);
        if (i < numberOfKeys && keys[i].compareTo(key) == 0)
            return values[i];
        return null;
    }

    /**
     * returns the smallest key in the symbol table
     * @return
     */
    @Override
    public Key min()
    {
        if (isEmpty())
            throw new NoSuchElementException("called min() on an empty symbol table");
        return keys[0];
    }

    /**
     * returns the largest key in the table.
     * @return
     */
    @Override
    public Key max()
    {
        if (isEmpty())
            throw new NoSuchElementException("called max() on an empty symbol table");
        return keys[numberOfKeys-1];
    }

    /**
     * returns largest key in the table less than or equal to the key
     * @param key : key that is or isn't contained in the symbol table
     * @return key that is directly beneath the provided key
     */
    @Override
    public Key floor(Key key)
    {
        if (key == null)
            throw new IllegalArgumentException("Argument provided to floor() is null");

        int i = rank(key);
        if (i< numberOfKeys && key.compareTo(keys[i]) == 0)
            return keys[i];
        if (i==0)
            throw new NoSuchElementException("argument to floor() is too small");
        else
            return keys[i-1];
    }

    /**
     * Returns the smallest key greater than or equal to the key
     * @param key : key contained or not in the table
     * @return key greater than or equal to key but smallest
     */
    @Override
    public Key ceiling(Key key)
    {
        if (key == null)
            throw new IllegalArgumentException("Argument provided to ceiling() is null");
        int i = rank(key);
        if (i==numberOfKeys)
            throw new NoSuchElementException("argument to ceiling is too large()");
        else
            return keys[i];
    }

    /**
     * Returns the number of keys in this table strictly less than the key
     *
     * @param key key to be checked against
     * @return number of keys in the symbol table strictly less than the key
     *
     * @throws IllegalArgumentException if key is null
     */
    @Override
    public int rank(Key key)
    {
        if (key == null)
            throw new IllegalArgumentException("argument for rank() is null");
        int low = 0, high = numberOfKeys - 1;
        while (low<=high)
        {
            int mid = low + (high - low) / 2;
            int cmp = key.compareTo(keys[mid]);
            /**
             * compareTo() returns negative if the key is less than the mid, positive if the key is greater than mid and
             * 0 if the key is equal to mid.
             */
            if (cmp < 0)
                high = mid - 1;
            else if (cmp > 0)
                low = mid + 1;
            else
                return mid;
        }
        return low;
    }

    /**
     * returns the kth smallest key in the table
     * @param rank : rank of a valid rank and key
     * @return the rank of the kth smallest key
     */
    @Override
    public Key select(int rank)
    {
        if (rank < 0 || rank >= size())
            throw new IllegalArgumentException("called select() with an invalid argument: " + rank);
        return keys[rank];
    }

    @Override
    public void deleteMin()
    {
        if (isEmpty())
            throw new NoSuchElementException("Symbol table underflow error");
        delete(min());
    }

    @Override
    public void deleteMax()
    {
        if (isEmpty())
            throw new NoSuchElementException("Symbol table underflow error");

        delete(max());
    }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old value with the new value if the
     * symbol table already contains a specified key. Deletes the specified key and its value from the symbol table
     * if the specified value is null.
     *
     * @param key : the key for which the value is to be added/removed
     * @param value : value that is to be added
     */
    @Override
    public void put(Key key, Value value)
    {
        if (key == null)
            throw new IllegalArgumentException("argument for put() is null");

        if (value == null)
        {
            delete(key);
            return;
        }

        int i = rank(key);
        if (i < numberOfKeys && keys[i].compareTo(key) == 0)
        {
            values[i] = value;
            return;
        }

        if (numberOfKeys == keys.length)
            resize(2*keys.length);

        for (int j = numberOfKeys; j > i; j--)
        {
            keys[j] = keys[j-1];
            values[j] = values[j-1];
        }

        keys[i] = key;
        values[i] = value;
        numberOfKeys++;
    }

    /**
     * Removes the specified key and associated value from this symbol table (if it is present).
     *
     * @param key : key to be removed
     * @throws IllegalArgumentException is key is null
     */
    @Override
    public void delete(Key key)
    {
        if (key == null)
            throw new IllegalArgumentException("argument for delete() is null");
        if (isEmpty())
            return;

        int i = rank(key);
        // if the key isn't in the table.
        if (i == numberOfKeys || keys[i].compareTo(key) != 0)
            return;

        /**
         * set the deleted keys position equal to the one above it, and so forth for all the keys above.
         */
        for (int j = i; j < numberOfKeys-1;j++)
        {
            keys[j] = keys[j+1];
            values[j] = values[j+1];
        }

        numberOfKeys--;
        keys[numberOfKeys] = null;
        values[numberOfKeys] = null;

        // resize to half the size if the number of keys is 1/4 of the length.
        if (numberOfKeys > 0 && numberOfKeys == keys.length / 4)
            resize(keys.length / 2);
    }

    @Override
    public boolean contains(Key key)
    {
        if (key == null)
            throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    /**
     * @return {@code true} if the table is empty
     *         {@code false} if the table is not empty
     */
    @Override
    public boolean isEmpty()
    {
        return size() == 0;
    }

    @Override
    public int size()
    {
        return numberOfKeys;
    }

    @Override
    public Iterable<Key> keys()
    {
        return keys(min(), max());
    }

    /**
     * Returns the number of keys in the symbol table in a specified range [low...high]
     * @param low : lowest end of the range
     * @param high : high end of the range
     * @return number of keys in this range
     */
    public int size(Key low, Key high)
    {
        if (low == null)
            throw new IllegalArgumentException("first argument to size() is null");
        if (high == null)
            throw new IllegalArgumentException("second argument to size() is null");

        if (low.compareTo(high) > 0) // if the low is greater than the high, its 0
            return 0;
        if (contains(high)) // if the high key is in the table, return the diff between high and low + 1
            return rank(high) - rank(low) + 1;
        else // else return just the diff between high and low
            return rank(high) - rank(low);
    }

    /**
     * returns all keys in this symbol table in a given range as an iterable
     *
     * @param low lowest end of range
     * @param high high end of range
     * @return ArrayList<Key> of keys in the range
     */
    public Iterable<Key> keys(Key low, Key high)
    {
        if (low == null)
            throw new IllegalArgumentException("first argument to size() is null");
        if (high == null)
            throw new IllegalArgumentException("second argument to size() is null");

        ArrayList<Key> queue = new ArrayList<>();
        if (low.compareTo(high) > 0)
            return queue;
        for (int i = rank(low); i < rank(high); i++)
        {
            queue.add(keys[i]);
        }
        return queue;
    }

    boolean check()
    {
        return isSorted() && rankCheck();
    }

    /**
     * checks the keys in the table are in assending order
     * @return true if sorted, false if not
     */
    boolean isSorted()
    {
        for (int i = 1; i < size(); i++)
        {
            if (keys[i].compareTo(keys[i-1]) < 0)
                return false;
        }
        return true;
    }

    /**
     * tests the rank() and select method are working properly.
     * rank(select(i)) = i
     * @return true if working properly, false if not.
     */
    boolean rankCheck()
    {
        for (int i = 0; i < size(); i++)
        {
            if (i != rank(select(i)))
            {
                return false;
            }
        }
        for (int i = 0; i < size(); i++)
        {
            if (keys[i].compareTo(select(rank(keys[i]))) != 0)
            {
                return false;
            }
        }
        return true;
    }
}
