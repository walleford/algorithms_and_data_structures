/**
 * Module A01 Data Structures: Bags, Queues, and Stacks.
 * Section 2: Stacks
 */
package a01_data_structure.s2_stacks;

import java.util.Iterator;

/**
 * A stack with dynamic capacity, doubles upon needs.
 * @author Jordan Wallingsford
 * @version 1.0
 * @param <Item> elements
 */
public class DynamicCapacityStack<Item> implements Stack<Item>
{

    private Item[] elements;

    private int numberOfElements;


    /**
     * Creates a stack of size initial capacity
     * @param initialCapacity int size of stack
     */
    @SuppressWarnings("unchecked")
    public DynamicCapacityStack(int initialCapacity)
    {
        elements = (Item[]) new Object[initialCapacity];
        numberOfElements = 0;
    }

    public DynamicCapacityStack()
    {
        this(10);
    }

    /**
     * Doubles the capacity of the stack
     */
    private void doubleCapactiy()
    {
        @SuppressWarnings("unchecked")
        Item[] newElements = (Item[]) new Object[elements.length * 2];
        for (int i = 0; i < elements.length; i++)
        {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }


    private void halfCapacity()
    {
        if (numberOfElements >= elements.length / 2)
        {
            throw new RuntimeException("Stack is more than half full");
        }
        @SuppressWarnings("unchecked")
        Item[] newElements = (Item[]) new Object[elements.length / 2];
        for (int i = 0 ; i < numberOfElements;i++)
        {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }
    @Override
    public void push(Item item)
    {
        if (numberOfElements == elements.length)
        {
            doubleCapactiy();
        }
        elements[numberOfElements++] = item;
    }

    @Override
    public Item pop()
    {
        //check is stack is empty
        if (numberOfElements==0)
        {
            throw new RuntimeException("stack empty");
        }
        numberOfElements--;
        Item top = elements[numberOfElements];
        if (numberOfElements<elements.length / 4 && elements.length > 10)
        {
            halfCapacity();
        }
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
            @Override
            public boolean hasNext()
            {
                return index < numberOfElements;
            }

            @Override
            public Item next()
            {
                if (hasNext()) return elements[index++];
                throw new RuntimeException("No more elements");
            }
        };
    }
}
