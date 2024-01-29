/**
 * Module A01 Data Structures: Bags, Queues, and Stacks.
 * Section 02 Stacks
 */

package a01_data_structure.s2_stacks;
import java.util.Iterator;

/**
 * A fixed capacity stack has a specified maximum capacity. When full, elements can't be added.
 * @author Jordan Wallingsford
 * @version 1.0
 * @param <Item> the type of elements in the stack
 */
public class FixedCapacityStack<Item> implements Stack<Item>
{
    /**
     * This is the array of elements, it is final because it can not be modified.
     */
    private final Item[] elements;

    private int numberOfElements;

    /**
     * Creates an empty stack with int capacity. It casts the array of objects to an array of items.
     * Since the compiler can not check this, you will get warnings of unchecked type, so we silence it.
     * @param capacity - int of maximum capacity
     */
    @SuppressWarnings("unchecked")
    public FixedCapacityStack(int capacity)
    {
        elements = (Item[]) new Object[capacity];
        numberOfElements = 0;
    }



    @Override
    public void push(Item item)
    {
        // check if stack is full first, if it is we can't push anymore
        if (numberOfElements == elements.length)
            throw new RuntimeException("Stack is full");
        elements[numberOfElements++] = item;
    }

    @Override
    public Item pop()
    {
        // once again, check if empty. Can't return anything if there is nothing there.
        if (numberOfElements==0)
            throw new RuntimeException("Stack empty");
        numberOfElements--;
        Item top = elements[numberOfElements];
        // setting this to null prevents loitering in the memory, this will allow garbage collection to occur on the
        // popped element.
        elements[numberOfElements]=null;
        return top;
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
            int index = 0;

            /**
             * This returns whether the Item has a next value by returning whether the current index is
             * less than the total number of elements.
             * @return true if there is a next item
             */
            @Override
            public boolean hasNext()
            {
                return index < numberOfElements;
            }

            /**
             * This returns the next item by returning the index + 1 of the elements array
             * @return elements[index++]
             */
            @Override
            public Item next()
            {
                if (hasNext()) {
                    return elements[index++];
                }
                throw new RuntimeException("No more elements");
            }
        };
    }
}
