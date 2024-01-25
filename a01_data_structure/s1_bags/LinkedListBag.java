/**
 * Module A12 Dynamic Capacity Bags
 * @author Jordan Wallingsford
 */

package a01_data_structure.s1_bags;

import java.util.Iterator;

/**
 * A linked-list bag allowing new elements in an efficient way.
 */
public class LinkedListBag<Item> implements Bag<Item>
{

    private class Node
    {
        /**
         * Next node in the list, following after the current. Null if none.
         */
        Node nextNode;
        /**
         * Item stored in this current node.
         */
        Item item;

    }

    /**
     * START node of list of items
     */
    private Node startNode;

    /**
     * The end node of the list of items
     */
    private Node endNode;

    private int numberOfElements; // num of elements in the list

    /**
     * creates an empty list of nodes
     */
    public LinkedListBag()
    {
        startNode = null;
        endNode = null;
        numberOfElements = 0;
    }

    /**
     * returns whether or not the list is empty: if the startNode is null, it is empty
     * @return true if startNode is null
     */
    @Override
    public boolean isEmpty(){
        return startNode == null;
    }

    @Override
    public void add(Item item) {
        // creates a new Node for the specific item and make the next node after it null since this will be the last
        Node itemNode = new Node();
        itemNode.nextNode = null;
        itemNode.item = item;
        numberOfElements++;
        // if the list was empty, initialize the list with this new node
        if (isEmpty())
        {
            startNode=itemNode;
        } else {
            //the list has elements so we add the item to the next node
            endNode.nextNode = itemNode;
        }
        endNode=itemNode;
    }

    @Override
    public int size() {
        return numberOfElements;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node currentNode = startNode;
            @Override
            public boolean hasNext() {
                return currentNode!=null;
            }

            @Override
            public Item next() {
                Item item = currentNode.item;
                currentNode = currentNode.nextNode;
                return item;
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
            result += seperator + i.item.toString();
            seperator = ", ";
        }
        result += "]";
        return result;
    }
}
