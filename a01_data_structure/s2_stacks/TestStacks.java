/**
 * Module A01 Data Structures: Bags, Queues, and Stacks.
 * Section 02 Stacks
 */

package a01_data_structure.s2_stacks;


import java.util.Date;

/**
 * Class to test all the different types of stacks
 * @author Jordan Wallingsford
 * @version 1.0
 */
public class TestStacks
{

    /**
     * Test to push an item to a stack, printing each operation.
     * @param s - the stack to puhs items to
     * @param item - the String being pushed into the stack
     */
    public static void testPushItem(Stack<String> s, String item)
    {
        System.out.println("Test of the push() method");
        System.out.println("Pushing Item: " + item.toString());
        try {
            s.push(item);
        } catch (Exception e) {
            System.out.println("Error pushing an item: " + e.getMessage());
        }
        System.out.println("Updated stack " + s.toString("[", "]",", "));
    }

    /**
     * this is used to perform various tests
     * @param s - the tested stack
     */
    public static void testStringStack(Stack<String> s)
    {
        System.out.println("Empty stack: " + s.toString("[", "]",", "));
        testPushItem(s, "jordan");
        testPushItem(s, "suhayla");
        testPushItem(s, "mozeh");
        testPushItem(s, "bata");
        testPopItem(s);
        testPopItem(s);
        testPopItem(s);
        testPopItem(s);
    }


    public static void testPopItem(Stack<String> s)
    {
        System.out.println(" ");
        System.out.println("Pop item from stack");
        System.out.println("Empty stack: " + s.toString("[","]",", "));
        try {
            s.pop();
        } catch (Exception e) {
            System.out.println("Error popping an item: " + e.getMessage());
        }
        System.out.println("Updated stack " + s.toString("[", "]",", "));

    }

    /**
     * Main method calling the test cases.
     * @param args
     */
    public static void main(String[] args)
    {
        System.out.println("Dynamic Capacity Stack - Task 2 - Jordan Wallingsford");
        Date date = new Date();
        System.out.println("Executed on " + date.toString());
        DynamicCapacityStack<String> dcs = new DynamicCapacityStack<>(3);
        testStringStack(dcs);
    }
}
