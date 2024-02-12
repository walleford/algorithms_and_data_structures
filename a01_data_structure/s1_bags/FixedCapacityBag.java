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
    private Item[] elements; // ref to array = 8 bytes

    // contains an integer count of the number of current elements inside the bag
    private int numberOfElements; // int = 4 bytes

    /**
     * Constructs the initial bag and sets it's capacity.
     * @param capacity
     */
    public FixedCapacityBag(int capacity) // overhead of 16 bytes + 4 padding
    {
        //noinspection unchecked
        elements = (Item[]) new Object[capacity]; // 8 bytes for ref to array
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
            int index=0; // 4 bytes for int
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

    protected int getCapacity()
    {
        return elements.length;
    }
}
