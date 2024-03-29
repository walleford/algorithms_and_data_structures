/**
 * Module A01 Data Structures: Bags, Queues, and Stacks.
 * Section 01 Bags
 */

package a01_data_structure.s1_bags;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Testing class for bags moving forward.
 * @author Jordan Wallingsford
 */

public class TestBag
{
    // Basic test of the fixed capacity bag
    public static void testFixedCapacityBag()
    {
        System.out.println("Create an empty bag with capacity: 5");
        FixedCapacityBag<String> bag = new FixedCapacityBag<>(5);
        System.out.println("Bag: " + bag.toString());
        System.out.println("Adding item: book");
        bag.add("book");
        System.out.println("Bag: " + bag.toString());
        bag.add("laptop");
        bag.add("camera");
        bag.add("shoes");
        bag.add("keys");
        System.out.println("Bag: " + bag.toString());
        System.out.println("How many items start with letter b?");
        int count=0;
        // for each can be used on our bag because of the Iterable<item> method we have made for it.
        for (String item: bag)
        {
            if (item.charAt(0)=='b') count++;
        }
        System.out.println("There are "+count+" items in the bag that start with B.");
    }

    /**
     * This will be used to test the fixedCapacityBag with a bag of integers.
     * @param args
     */
    public static void testFixedCapacityBagInts()
    {
        System.out.println("Create an empty bag with capacity: 5");
        FixedCapacityBag<Integer> bag = new FixedCapacityBag<>(5);
        System.out.println("Bag: " + bag.toString());
        System.out.println("Adding item: 5");
        bag.add(5);
        System.out.println("Bag: " + bag.toString());
        bag.add(3);
        bag.add(7);
        bag.add(11);
        bag.add(3);
        System.out.println("Bag: " + bag.toString());
        System.out.println("How many items equal 3?");
        int count=0;
        // for each can be used on our bag because of the Iterable<item> method we have made for it.
        for (Integer item: bag)
        {
            if (item == 3) count++;
        }
        System.out.println("There are "+count+" items in the bag that equal 3.");
    }

    /**
     * This is testing whether or not the isSingleton method functions correctly
     */
    public static void fixedCapacitySingletonBag()
    {
        System.out.println("Creating an empty integer bag with capacity 4");
        FixedCapacityBag<Integer> bag = new FixedCapacityBag<>(4);
        System.out.println("Empty bag: "+bag.toString());
        System.out.println("Bag is singleton?: " + bag.isSingleton());
        bag.add(3);
        System.out.println("Adding bag item 3. Bag: " + bag.toString());
        System.out.println("Bag is singleton?: " + bag.isSingleton());
        bag.add(4);
        bag.add(13);
        bag.add(35);
        System.out.println("Adding bag items 4, 13, 35. Bag: " + bag.toString());
        System.out.println("Bag is singleton?: " + bag.isSingleton());
        System.out.println("How many items equal 3?");
        int count=0;
        // for each can be used on our bag because of the Iterable<item> method we have made for it.
        for (Integer item: bag)
        {
            if (item == 3) count++;
        }
        System.out.println("There are "+count+" items in the bag that equal 3.");
    }

    /**
     * This is testing the isSingleton method for doubles
     */
    public static void fixedCapacitySingletonBagDoubles()
    {
        System.out.println("Creating an empty double bag with capacity 4");
        FixedCapacityBag<Double> bag = new FixedCapacityBag<>(4);
        System.out.println("Empty bag: "+bag.toString());
        System.out.println("Bag is singleton?: " + bag.isSingleton());
        bag.add(3.0);
        System.out.println("Adding bag item 3.0 Bag: " + bag.toString());
        System.out.println("Bag is singleton?: " + bag.isSingleton());
        bag.add(4.0);
        bag.add(13.6);
        bag.add(35.5);
        System.out.println("Adding bag items 4.0, 13.6, 35.5. Bag: " + bag.toString());
        System.out.println("Bag is singleton?: " + bag.isSingleton());
        System.out.println("How many items are even?");
        int count=0;
        // for each can be used on our bag because of the Iterable<item> method we have made for it.
        for (Double item: bag)
        {
            if (item % 2 == 0) count++;
        }
        System.out.println("There are "+count+" items in the bag that are even");
    }

    public static void test4FixedCapacityBag()
    {
        System.out.println("Creating a new Bag<Long>");
        FixedCapacityBag<Long> bag = new FixedCapacityBag<>(3);
        System.out.println("Bag: " + bag.toString());
        System.out.println("Adding bag item: 1*10000000000L");
        bag.add(6*10000000000L);
        System.out.println("Bag: " + bag.toString());
        System.out.println("Bag is singleton: " + bag.isSingleton());
        System.out.println("Adding other long numbers...");
        bag.add(3*10000000000L);
        bag.add(4*10000000000L);
        System.out.println("Bag: " + bag.toString());
        System.out.println("Bag is singleton: " + bag.isSingleton());
    }

    /**
     * This will be used to take input and test the bag that way rather than directly declaring items.
     */
    public static void testFixedCapacityBagOfStrings(String item1, String item2, String item3)
    {
        System.out.println("Create an empty bag of Strings with size 3");
        FixedCapacityBag<String> bag = new FixedCapacityBag<>(3);
        System.out.println("Bag: " + bag.toString());
        System.out.println("Bag isSingleton: " + bag.isSingleton());
        System.out.println("Add the first item: " + item1);
        bag.add(item1);
        System.out.println("Bag: " + bag.toString());
        System.out.println("Bag isSingleton: " + bag.isSingleton());
        System.out.println("Add the rest of the items: " + item2 + ", " + item3);
        bag.add(item2);
        bag.add(item3);
        System.out.println("Bag: " + bag.toString());
        System.out.println("Bag isSingleton: " + bag.isSingleton());
        System.out.println("How many items start with letter b?");
        int count=0;
        // for each can be used on our bag because of the Iterable<item> method we have made for it.
        for (String item: bag)
        {
            if (item.charAt(0)=='b') count++;
        }
        System.out.println("There are "+count+" items in the bag that start with B.");
    }
    // Basic test of the dynamic capacity bag
    public static void testDynamicCapacityBag()
    {
        System.out.println("Create an empty bag with capacity: 2");
        DynamicCapacityBag<String> bag = new DynamicCapacityBag<>(2);
        System.out.println("Bag: " + bag.toString());
        System.out.println("Bag isSingleton: " + bag.isSingleton());
        System.out.println("Adding item: book");
        bag.add("book");
        System.out.println("Bag: " + bag.toString());
        System.out.println("Bag isSingleton: " + bag.isSingleton());
        bag.add("laptop");
        System.out.println("Bag: " + bag.toString());
        System.out.println("Bag isSingleton: " + bag.isSingleton());
        System.out.println("Adding elements to dynamically increase capacity: shoes, shirt");
        bag.add("shoes");
        bag.add("shirt");
        System.out.println("Bag: " + bag.toString());
        System.out.println("Bag isSingleton: " + bag.isSingleton());
        System.out.println("How many items start with letter b?");
        int count=0;
        // for each can be used on our bag because of the Iterable<item> method we have made for it.
        for (String item: bag)
        {
            if (item.charAt(0)=='b') count++;
        }
        System.out.println("There are "+count+" items in the bag that start with B.");
    }

    public static void testDynamicCapacityBagOfStrings(String item1, String item2, String item3, String item4, String item5)
    {
        System.out.println("Create an empty bag with capacity: 3");
        DynamicCapacityBag<String> bag = new DynamicCapacityBag<>(3);
        System.out.println("Bag: " + bag.toString());
        System.out.println("Bag isSingleton: " + bag.isSingleton());
        System.out.println("Add the first item: " + item1);
        bag.add(item1);
        System.out.println("Bag: " + bag.toString());
        System.out.println("Bag isSingleton: " + bag.isSingleton());
        System.out.println("Add the rest of the items: " + item2 + ", " + item3);
        bag.add(item2);
        bag.add(item3);
        System.out.println("Bag: " + bag.toString());
        System.out.println("Bag isSingleton: " + bag.isSingleton());
        System.out.println("Adding elements to dynamically increase capacity: " + item4 + ", " + item5);
        bag.add(item4);

        bag.add(item5);
        System.out.println("Bag: " + bag.toString());
        System.out.println("Bag isSingleton: " + bag.isSingleton());
        System.out.println("How many items start with letter b?");
        int count=0;
        // for each can be used on our bag because of the Iterable<item> method we have made for it.
        for (String item: bag)
        {
            if (item.charAt(0)=='b') count++;
        }
        System.out.println("There are "+count+" items in the bag that start with B.");
    }


    /**
     * Test case to test whether or not a bag is empty after adding an item.
     * @param bag
     * @param items
     * @param <Item>
     */
    public static <Item> void testIsEmpty(Bag<Item> bag, Item[] items)
    {
        System.out.println("Initial Bag: "+bag.toString());
        System.out.println("Bag Is Empty: "+bag.isEmpty());
        for (int i=0; i<items.length;i++)
        {
            System.out.println("Add item: " + items[i]);
            bag.add(items[i]);
            System.out.println("Bag: "+bag.toString());
            System.out.println("Bag Is Empty: "+bag.isEmpty());
        }
    }

    /**
     * Test case to dynamically check if a bag is a singleton after adding each new element
     * @param bag
     * @param items
     * @param <Item>
     */
    public static <Item> void testIsSingleton(Bag<Item> bag, Item[] items)
    {
        System.out.println("Initial Bag: "+bag.toString());
        System.out.println("Bag Is Singleton: "+bag.isSingleton());
        for (int i=0; i<items.length;i++)
        {
            System.out.println("Add item: " + items[i]);
            bag.add(items[i]);
            System.out.println("Bag: "+bag.toString());
            System.out.println("Bag Is Singleton: "+bag.isSingleton());
        }
    }

    public static void testBag(Bag<String> bag, String title)
    {
        System.out.println("");
        System.out.println(title);
        System.out.println("Bag: " + bag.toString());
        System.out.println("Adding item: 1");
        bag.add("book");
        System.out.println("Bag: " + bag.toString());
        System.out.println("-----------");
        bag.add("tshirt");
        System.out.println("Bag: " + bag.toString());
        System.out.println("-----------");
        bag.add("laptop");
        System.out.println("Bag: " + bag.toString());
        System.out.println("-----------");
        bag.add("cellphone");
        System.out.println("Bag: " + bag.toString());
        System.out.println("-----------");
        bag.add("pens");
        System.out.println("Bag: " + bag.toString());
        System.out.println("-----------");
        System.out.println("Bag: " + bag.toString());
        try {
            System.out.println("Adding the sixth item: money");
            bag.add("money");
            System.out.println("Bag: " + bag.toString());
        } catch (Exception e) {
            System.out.println("Error adding a sixth element: " +e.getMessage());
        }
        System.out.println("Finding the value with the most frequency...");
    }


    public static int maxFreq(Bag<Integer> bag, int item1, int item2, int item3, int item4, int item5, int item6)
    {
        System.out.println("Adding items to bag: " + item1 + " " + item2 + " " + item3 + " " + item4 + " " + item5 + " " + item6 + " ");
        System.out.println("Bag: " + bag.toString());
        System.out.println("Adding item: 1");
        bag.add(item1);
        System.out.println("Bag: " + bag.toString());
        bag.add(item2);
        bag.add(item3);
        bag.add(item4);
        bag.add(item5);
        bag.add(item6);
        System.out.println("Added all items: " + bag.toString());

        HashMap<Integer, Integer> freq = new HashMap<Integer, Integer>();
        for (Integer item: bag)
        {
            int key = item;
            if (freq.containsKey(key))
            {
                int frequencyCount = freq.get(key);
                frequencyCount++;
                freq.put(key, frequencyCount);
            } else {
                freq.put(key, 1);
            }
        }

        int max_count = 0;
        int res = -1;
        for (Map.Entry<Integer, Integer> entry: freq.entrySet())
        {
            if (max_count < entry.getValue())
            {
                res = entry.getKey();
                max_count = entry.getValue();
            }
        }

        return res;
    }

    // This is the main method performing all of the tests declared above.
    public static void main(String[] args)
    {
         /**
         * Testing the bag with strings.
         */
        System.out.println("Linked List - Task 2 - by Jordan Wallingsford");
        Date date = new Date();
        System.out.println("Executed on: " + date.toString());
        //Bag<String> b1 = new DynamicCapacityBag<>(6);
        //Bag<String> b2 = new FixedCapacityBag<>(6);
        Bag<Integer> b1 = new LinkedListBag<>();
        Bag<Integer> b2 = new LinkedListBag<>();
        Bag<Integer> b3 = new LinkedListBag<>();
        //testBag(b1, "Test of a Dynamic Capacity Bag");
        System.out.println("Test 1:  " + maxFreq(b1, 1, 2, 1, 3, 4, 1));
        System.out.println("Test 2:  " + maxFreq(b2, 1, 2, 3, 2, 4, 8));
        System.out.println("Test 3:  " + maxFreq(b3, 1, 2, 3, 4, 1, 6));
    }
}
