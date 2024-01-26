/**
 * Module A12 Dynamic Capacity Bags
 * @author Jordan Wallingsford
 */

package a01_data_structure.s1_bags;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A bag with dynamic capacity. Capacity is modified with new elements.
 *
 * @author Jordan Wallingsford
 */
public class DynamicCapacityBag<Item> implements Bag<Item>
{
    /**
     * data structure for the elements in the bag
     *
     */
    private Item[] elements;

    /**
     * integer count of how many elements are in the bag
     */
    private int numberOfElements;

    /**
     * constructor for the dynamically sized bag
     * @param initialCapacity the initial capacity of the bag.
     */
    public DynamicCapacityBag(int initialCapacity)
    {
        //noinspection unchecked
        elements = (Item[]) new Object[initialCapacity];
        numberOfElements = 0;
    }

    /**
     * creates a default bag with 8 for capacity
     */
    public DynamicCapacityBag()
    {
        this(8);
    }

    /**
     * Checks if the capacity has been met, if so it doubles it then adds the new element
     * @param item
     */
    @Override
    public void add(Item item) {
        if (numberOfElements == elements.length)
        {
            doubleCapacity();
        }
        elements[numberOfElements++] = item;
    }

    @Override
    public void maxFreq()
    {
        HashMap<String, Integer> freq = new HashMap<String, Integer>();
        for (Item item: this)
        {
            if (!freq.containsKey(item))
            {
                freq.put(item.toString(), 1);
            } else {
                int value = freq.get(item.toString());
                freq.put(item.toString(), value+1);
            }
        }
        Map.Entry.comparingByValue();
    }

    @Override
    public int size() {
        return numberOfElements;
    }

    /**
     * Doubles the capacity of the bag once it reaches the max capacity
     */
    private void doubleCapacity()
    {
        @SuppressWarnings("unchecked")
        Item[] newElements = (Item[]) new Object[elements.length*2];
        for (int i = 0; i < elements.length; i++)
        {
            newElements[i] = elements[i];
        }
        elements = newElements;
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
        String seperator = "";
        for (int i=0 ; i< numberOfElements; i++)
        {
            result += seperator + elements[i].toString();
            seperator = ", ";
        }
        result += "]";
        return result;
    }
}
