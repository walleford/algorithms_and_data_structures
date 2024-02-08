/**
 * Module A01 Data Structures: bags, stacks, and queues.
 * Section A01-3 Queues
 */
package a01_data_structure.s3_queues;

import java.util.Iterator;

public class LinkedListQueue<Item> implements Queue<Item>
{
    private class Node
    {
        /**
         * this holds the position of the next node in the queue
         */
        Node belowNode;

        /**
         * this holds the data in the current Node
         */
        Item item;
    }

    private Node startNode;
    private int numberOfElements;
    private Node endNode;

    @Override
    public void enqueue(Item item)
    {
        Node itemNode = new Node();
        itemNode.belowNode = null;
        itemNode.item = item;
        numberOfElements++;

        if (startNode == null)
        {
            startNode = itemNode;
            endNode = itemNode;
        } else {
            endNode.belowNode = itemNode;
            endNode = itemNode;
        }
    }

    @Override
    public Item dequeue()
    {
        if (startNode == null)
            throw new RuntimeException("queue is empty");
        Item first = startNode.item;
        startNode = startNode.belowNode;
        return first;
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
            private Node currentNode = startNode;
            @Override
            public boolean hasNext()
            {
                return currentNode != null;
            }

            @Override
            public Item next()
            {
                Item item = currentNode.item;
                currentNode = currentNode.belowNode;
                return item;
            }
        };
    }
}
