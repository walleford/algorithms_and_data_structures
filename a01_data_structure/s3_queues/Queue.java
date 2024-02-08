/**
 * Module A01 Data Structures: bags, stacks, and queues.
 * Section A01-3 Queues
 */
package a01_data_structure.s3_queues;

/**
 * A queue is a collection of items. It operates on a First-in-first-out policy (like a typical queue).
 * When we iterate through a queue, we start at the beginning (first element ever added) and make our way to the last.
 *
 * @author Jordan Wallingsford
 * @version 1.0
 *
 * @param <Item> generic type for the elements the queue will be taking in.
 */
public interface Queue<Item> extends Iterable<Item>
{
    /**
     * Add an item at the end of the queue
     * @param item the Item to be added
     */
    public void enqueue(Item item);

    /**
     * This will remove and return the item from the beginning of the queue.
     * @return Item that is next in the queue
     */
    public Item dequeue();

    public int size();

    /**
     * returns the boolean of whether ornot the queue is empty by checking size^^
     * @return
     */
    default public boolean isEmpty()
    {
        return size()==0;
    }

    /**
     * Creates a string representation of the elements within the queue.
     * Will display the elements beginning with the first element added
     *
     * @param start string to designate the start of the queue
     * @param end string to designate the end
     * @param separator string to designate the characters separating each element
     * @return String representation of the elements
     */
    default public String toString(String start, String end, String separator)
    {
        String result = start;
        boolean needSeparator = false;
        for (Item item: this) {
            if (needSeparator) {
                result += separator;
            } else {
                needSeparator = true;
            }
            result += item;
        }
        result += end;
        return result;
    }
}
