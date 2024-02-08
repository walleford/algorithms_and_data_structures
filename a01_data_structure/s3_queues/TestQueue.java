/**
 * Module A01 Data Structures: bags, stacks, and queues.
 * Section A01-3 Queues
 */
package a01_data_structure.s3_queues;

import java.util.Date;

/**
 * Class to test all the queues we build
 *
 * @author Jordan Wallingsford
 * @version 1.0
 */
public class TestQueue
{
    private static <Item> void printQueue(Queue<Item> q, String name)
    {
        System.out.println("Queue: "+name);
        System.out.println("    - elements = "+q.toString("<<","<<",", "));
        System.out.println("    - size     = "+q.size());
        System.out.println("    - isEmpty  = "+q.isEmpty());
    }

    /**
     * General test to validate enqueueing works
     * @param q queue of type Item
     * @param name name of the queue
     * @param item specific item to add
     * @param <Item> type of the elements in the queue
     */
    private static <Item> void testEnqueueItem(Queue<Item> q, String name, Item item)
    {
        System.out.println(" ");
        System.out.println("Enqueue an item to a queue");
        try {
            q.enqueue(item);
            System.out.println("    - add successful!");
        } catch (Exception e) {
            System.out.println("    - runtime exception: "+e.getMessage());
        }
        printQueue(q, name);
    }

    /**
     * Basic test removing the first item from the list
     * @param q queue to remove item from
     * @param name name of the queue
     * @param <Item> type of the elements in the queue
     */
    private static <Item> void testDequeueItem(Queue<Item> q, String name)
    {
        System.out.println(" ");
        System.out.println("Dequeue the next item from the queue.");
        try {
            Item item = q.dequeue();
            System.out.println("    - dequeue success!");
            System.out.println("    = item: " + item.toString());
        } catch (Exception e){
            System.out.println("    - runtime exception: "+e.getMessage());
        }
        printQueue(q, name);
    }

    private static void testWithStrings()
    {
        System.out.println("Fixed Capacity Queue - Task 2 - Jordan Wallingsford");
        Date date = new Date();
        System.out.println("Executed on " + date.toString());
        Queue<String> fcq3 = new FixedCapacityQueue<>(3);
        String fcq3Name = "fixed capacity queue of 3 elements";
        printQueue(fcq3, fcq3Name);
        testEnqueueItem(fcq3, fcq3Name, "jordan");
        testEnqueueItem(fcq3, fcq3Name, "susu");
        testEnqueueItem(fcq3, fcq3Name, "bata");
        testEnqueueItem(fcq3, fcq3Name, "mozeh");
        testDequeueItem(fcq3, fcq3Name);
        testDequeueItem(fcq3, fcq3Name);
        testEnqueueItem(fcq3, fcq3Name, "mozeh");
        testEnqueueItem(fcq3, fcq3Name, "i have no more names");
        testDequeueItem(fcq3, fcq3Name);
        testDequeueItem(fcq3, fcq3Name);
        testDequeueItem(fcq3, fcq3Name);
        testDequeueItem(fcq3, fcq3Name);
    }

    private static void testWithIntegers()
    {
        System.out.println("Fixed Capacity Queue - Task 2 - Jordan Wallingsford");
        Date date = new Date();
        System.out.println("Executed on " + date.toString());
        Queue<Integer> fcq3 = new FixedCapacityQueue<>(3);
        String fcq3Name = "fixed capacity queue of 3 elements";
        printQueue(fcq3, fcq3Name);
        testEnqueueItem(fcq3, fcq3Name, 1);
        testEnqueueItem(fcq3, fcq3Name, 2);
        testEnqueueItem(fcq3, fcq3Name, 3);
        testEnqueueItem(fcq3, fcq3Name, 4);
        testDequeueItem(fcq3, fcq3Name);
        testDequeueItem(fcq3, fcq3Name);
        testEnqueueItem(fcq3, fcq3Name, 5);
        testEnqueueItem(fcq3, fcq3Name, 6);
        testDequeueItem(fcq3, fcq3Name);
        testDequeueItem(fcq3, fcq3Name);
        testDequeueItem(fcq3, fcq3Name);
        testDequeueItem(fcq3, fcq3Name);
    }

    public static <Item> void testQueue(Queue<Item> q, String name, Item[] items, int dequeueAmount, Item[] addons, int dequeueAmount2)
    {
        System.out.println("Linked List Queue - Task 1 - Jordan Wallingsford");
        System.out.println("------------");
        System.out.println("Testing Queue Named: " + name);
        System.out.println("Empty Queue:         " + q.toString());
        for (int i =1; i <= items.length; i++)
        {
            testEnqueueItem(q, name, items[i-1]);
        }
        for (int i = 1; i <= dequeueAmount; i++)
        {
            testDequeueItem(q, name);
        }
        for (int i = 1; i <= addons.length; i++)
        {
            testEnqueueItem(q, name, addons[i-1]);
        }
        for (int i = 1; i <= dequeueAmount2; i++)
        {
            testDequeueItem(q, name);
        }
    }

    public static void main(String[] args)
    {
        Queue<Integer> llqInt = new LinkedListQueue<>();
        Queue<String> llqStr = new LinkedListQueue<>();
        Integer[] intItems = {1,2,3};
        Integer[] intAddons = {1,2};
        String[] strItems = {"jordan", "mozeh"};
        String[] strAddons = {"suhayla", "bata"};
        String strName = "Dynamic Capacity Strings";
        String intName = "Dynamic Capacity Ints";
        testQueue(llqInt, intName, intItems, 1, intAddons, 2);
        testQueue(llqStr, strName, strItems, 1, strAddons, 1);
    }
}
