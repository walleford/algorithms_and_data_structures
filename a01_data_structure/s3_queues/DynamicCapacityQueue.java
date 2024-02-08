/**
 * Module A01 Data Structures: bags, stacks, and queues.
 * Section A01-3 Queues
 */
package a01_data_structure.s3_queues;

import java.util.Iterator;


/**
 * queue works on a first in first out basis
 * capacity for this queue is modified once it reaches the initial set capacity.
 *
  * @param <Item> type of elements stored
 *
 * @author Jordan Wallingsford
 * @version 1.0
 */
public class DynamicCapacityQueue<Item> implements Queue<Item>
{
    private Item[] elements;

    private int numberOfElements;

    private int startIndex;
    private int endIndex;

    public DynamicCapacityQueue(int capacity)
    {
        elements = (Item[]) new Object[capacity];
        numberOfElements = 0;
        startIndex = -1;
        endIndex = -1;
    }

    public DynamicCapacityQueue()
    {
        this(10);
    }

    public void doubleCapacity()
    {
        Item[] newElements = (Item[]) new Object[elements.length*2];
        int oldIndex = startIndex;

        int newIndex = 0;

        for (int i = 1; i <= numberOfElements; i++)
        {
            newElements[newIndex++] = elements[oldIndex++];
            if (oldIndex==elements.length)
            {
                oldIndex=0;
            }
        }
        elements=newElements;
        startIndex = 0;
        endIndex = numberOfElements-1;
    }

    @Override
    public void enqueue(Item item)
    {
        if (numberOfElements == elements.length)
        {
            doubleCapacity();
        }
        numberOfElements++;
        endIndex++;
        if (endIndex>= elements.length)
        {
            endIndex=0;
        }
        if (startIndex==-1)
        {
            startIndex=0;
        }
        elements[endIndex] = item;
    }

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
