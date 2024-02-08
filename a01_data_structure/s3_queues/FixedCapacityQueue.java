/**
 * Module A01 Data Structures: bags, stacks, and queues.
 * Section A01-3 Queues
 */
package a01_data_structure.s3_queues;
import java.util.Iterator;

/**
 * Fixed capacity queue. Has a maximum specified capacity. When full, nothing else can be added.
 *
 * @param <Item>
 */
public class FixedCapacityQueue<Item> implements Queue<Item>
{
    /**
     * array of elements stored in the queue. If the end of the array is reached, and there is space at the beginning,
     * the elements will be stored from the beginning. It is circular, as in it loops back around to the beginning
     * if available to continue storing items.
     */
    private final Item[] elements;

    private int numberOfElements;

    /**
     * Index of the first element in the queue. -1 if empty. When it reaches the end of the array, it resets to the
     * beginning
     */
    private int startIndex;

    /**
     * Index of the last element of the array, -1 if empty. When it reaches the end of the array, it resets to the
     * beginning.
     */
    private int endIndex;

    /**
     * Creates the initial queue with no elements, and the indexes set to -1 since it is empty.
     * @param capacity integer number representing the total size of a queue.
     */
    public FixedCapacityQueue(int capacity)
    {
        elements = (Item[]) new Object[capacity];
        numberOfElements = 0;
        startIndex = -1;
        endIndex = -1;
    }

    /**
     * Adds new items to the queue, after verifying it isn't full. It sets indexes to 0 if it is the first element.
     * If the length of the elements is equal to the index, it sets the endIndex to the first position in the queue
     *
     * @param item the Item to be added
     */
    @Override
    public void enqueue(Item item)
    {
        if (numberOfElements == elements.length)
        {
            throw new RuntimeException("Queue is full");
        }
        if (numberOfElements == 0)
        {
            startIndex = 0;
            endIndex = 0;
        } else {
            endIndex++;
            if (endIndex == elements.length)
            {
                endIndex = 0;
            }
        }
        elements[endIndex] = item;
        numberOfElements++;
    }

    /**
     * 1. removes the element in the startIndex position because that is the element that has been in the queue the longest
     * 2. Decrements the number of elements by 1
     * 3. if there are no elements, set the indexes to -1
     * 4. if there are, increase the startindex by 1, moving it to the next longest living element in queue
     * 5. if the start index now equals the length of the array, make it equal to the first position in the array
     * 6. return the item that was popped
     *
     * @return Item removed from the longest living element position in the queue
     */
    @Override
    public Item dequeue()
    {
        if (numberOfElements == 0)
        {
            throw new RuntimeException("Queue is empty");
        }
        Item item = elements[startIndex];
        numberOfElements--;
        if (numberOfElements == 0)
        {
            startIndex = -1;
            endIndex = -1;
        } else {
            startIndex++;
            if ( startIndex == elements.length )
            {
                startIndex = 0;
            }
        }
        return item;
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
            int nextItem = startIndex;
            @Override
            public boolean hasNext()
            {
                return nextItem != -1;
            }

            @Override
            public Item next()
            {
                if (nextItem == -1)
                    throw new RuntimeException("no more elements");
                Item item = elements[nextItem];
                if (nextItem == endIndex)
                    nextItem = -1;
                else {
                    nextItem++;
                    if (nextItem == elements.length)
                        nextItem = 0;
                }
                return item;
            }
        };
    }
}
