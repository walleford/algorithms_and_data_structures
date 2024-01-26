/**
 * Module A01 Data Structures: Bags, Queues, and Stacks.
 * Section 01 Bags
 */

package a01_data_structure.s1_bags;

/**
 * A bag is a DS that collects unordered items, allowing for iteration without removal of items.
 * @author Jordan Wallingsford
 * @param Item the type of elements stored in the bag.
 */


public interface Bag<Item> extends Iterable<Item>
{
    /**
     * Adds an item to the bag in no particular order.
     * @param item
     */
    public void add(Item item);

    /**
     * Check if bag is empty
     * @return true for an empty bag
     */
    default public boolean isEmpty()
    {
        return size() == 0;
    }

    /**
     * Number of elements inside of the bag currently
     * @return number of items in bag
     */
    public int size();

    /**
     * This will return true if the bag only contains a single item in it.
     * @return true if only 1 element
     */
    default public boolean isSingleton()
    {
        return size() == 1;
    }

    public void maxFreq();


    /**
     * Creates a string representation of the bag, starts with start string, ends with end string, and is separated
     * with the separator.
     * @param start
     * @param end
     * @param separator
     * @return
     */
    default public String toString(String start, String end, String separator)
    {
        String result = start;
        boolean needSeparator = false;
        for (Item item: this) {
            if (needSeparator)
            {
                result += separator;
            } else{
                needSeparator = true;
            }
        }
        result += end;
        return result;
    }
}
