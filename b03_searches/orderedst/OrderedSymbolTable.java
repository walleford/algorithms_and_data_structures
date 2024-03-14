package b03_searches.orderedst;

import b03_searches.symboltable.SymbolTable;

public interface OrderedSymbolTable<Key extends Comparable<Key>, Value> extends SymbolTable<Key, Value>
{
    /**
     *
     * @return the smallest key
     */
    public Key min();

    /**
     * @return the largest key
     */
    public Key max();

    /**
     * returns the largest key less than or equal to a key.
     * @param key : key that is or isn't contained in the symbol table
     * @return key : largest but less than or equal to the specified key
     */
    public Key floor(Key key);

    /**
     * @param key : key contained or not in the table
     * @return key : associated with the value thats smallest but greater than or equal to the specified key
     */
    public Key ceiling(Key key);

    /**
     *
     * @param key key contained or not in the table
     * @return the number of keys less than the key
     */
    public int rank(Key key);

    /**
     * @param rank : rank of a valid rank and key
     * @return the key at that rank in the table
     */
    public Key select(int rank);

    /**
     * deletes the minimum value in the table
     */
    public void deleteMin();

    /**
     * deletes the maximum value in the table
     */
    public void deleteMax();

    public int size(Key low, Key high);

    public Iterable<Key> keys(Key low, Key high);

    @Override
    public Iterable<Key>keys();
}
