/**
 * C11-PA Elementary Symbol Table
 * 03/08/2024
 */
package b03_searches.symboltable;

/**
 * A symbol table is a data structure containing key-value pairs (think dictionary)
 * Client can insert key-value pairs into the symbol table with the expectation of later being able to search for the
 * value associated with a given key.
 *
 * @author Jordan Wallingsford
 * @version 1.0
 *
 * @param <Key> generic type for the keys in the symbol-table
 * @param <Value> generic type for the values associated with said keys
 */
public interface SymbolTable<Key, Value>
{

    /**
     * If value is not null, add the key-value pair to the symbol table.
     * If the value is null, remove the pair from the symbol table associated with the key
     *
     * @param key : the key for which the value is to be added/removed
     * @param value : value that is to be added
     */
    public void put(Key key, Value value);

    /**
     * Return the value paired with a particular key
     * @param key : the key to return the value for
     * @return Value : the value to return from the key
     */
    public Value get(Key key);

    /**
     * remove the key and its value from the table
     * @param key : key to be removed
     */
    public void delete(Key key);

    /**
     * checks whether or not there is a value associated with this key
     * @param key : key to check
     * @return true/false : true is their is a value for that key, false if not
     */
    public boolean contains(Key key);

    /**
     * Checks if the symbol-table is empty or not
     * @return true/false : true if the table is empty, false if it is not.
     */
    public boolean isEmpty();

    /**
     * Number of key-value pairs in the table
     * @return integer : count of key-value pairs
     */
    public int size();

    /**
     * Returns all the keys in the symbol-table as an iterable object.
     * @return Iterable<Key> : key set that can be iterated over.
     */
    public Iterable<Key> keys();
}
