/**
 * Module A01 Data Structures: Bags, Queues, and Stacks.
 * Section 01 Bags
 */

package a01_data_structure.s1_bags;

import java.util.Iterator;

/**
 * A fixed capacity bag is a bag with a set size: capacity. Capacity is passed when the Bag is initialized
 * @author Jordan Wallingsford
 *
 * @param <Item> data type of element inside the bag
 */
public class FixedCapacityBag<Item> implements Bag<Item>
{
    // The array of elements stored inside the bag
    private Item[] elements;

    // contains an integer count of the number of current elements inside the bag
    private int numberOfElements;

    /**
     * Constructs the initial bag and sets it's capacity.
     * @param capacity
     */
    public FixedCapacityBag(int capacity)
    {
        //noinspection unchecked
        elements = (Item[]) new Object[capacity];
    }

    @Override
    public void add(Item item)
    {
        if (numberOfElements == elements.length)
        {
            throw new RuntimeException("Bag is full");
        }
        elements[numberOfElements]=item;
        numberOfElements++;
    }

    @Override
    public int size()
    {
        return numberOfElements;
    }

    @Override
    public Iterator<Item> iterator()
    {
        return new Iterator<Item>()
        {
            int index=0;
            @Override
            public boolean hasNext()
            {
                return index < numberOfElements;
            }

            @Override
            public Item next()
            {
                if (hasNext()) return elements[index++];
                throw new RuntimeException("No more elements.");
            }
        };
    }


    /**
     * This will return the contents of the bag in string form. Will be called each time a bag is concatenated with a
     * string.
     * @return String result
     */
    @Override
    public String toString()
    {
        String result = "[";
        boolean needSeparator = false;
        String separator = "";
        for (Item item: this) {
            if (needSeparator)
            {
                result += separator;
            } else{
                needSeparator = true;
                separator = " ";
            }
        }
        result += "]";
        return result;
    }
}
