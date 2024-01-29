/**
 * Module A01 Data Structures: Bags, Queues, and Stacks.
 * Section 02 Stacks
 */


package a01_data_structure.s2_stacks;

/**
 * @author Jordan Wallingsford
 * A stack is a collection of items utilizing the Last In First Out method
 * When the elements are iterated, we start with the base of the stack and end with the top.
 * @param <Item> types of elements in stack
 */
public interface Stack<Item> extends Iterable<Item>
{
    /**
     *
     * Push will add an element to the stack.
     * @param item item added
     */
    public void push(Item item);

    /**
     *
     * Pop will remove and return the last element add to the stack (the top)
     * @return the top element, an Item
     */
    public Item pop();

    /**
     *
     *Check if the stack is empty
     * @return true if the stack is empty
     */
    default public boolean isEmpty()
    {
     return size() == 0;
    }

    /**
     *
     * Return the size as an int of the stack
     * @return integer of size
     */
    public int size();

    /**
     * Creates a string representation of the stack, starts with start string, ends with end string, and is separated
     * with the separator.
     *
     * The method will displays the elements starting with base of the stack.
     * @param start
     * @param end
     * @param separator
     * @return the generated string
     */
    default public String toString(String start, String end, String separator)
    {
        String result = start;
        boolean needSeparator = false;
        for (Item item: this) {
            if (needSeparator)
            {
                result += separator + item.toString();
            } else{
                result += item.toString();
                needSeparator = true;
            }
        }
        result += end;
        return result;
    }

}
