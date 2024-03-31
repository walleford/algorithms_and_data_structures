package b03_searches.rbbst;

public class RedBlackBSTTest
{
    private static String[] test1 = {"N", "U", "M","B","E","R"};
    private static String[] test2 = {"J","O","R","D","A","N","W","L","I","G","S"};

    public static void main(String[] args)
    {
        RedBlackBST<String, Integer> rb = new RedBlackBST<>();
        int index=1;
        for (String key:test2)
        {
            rb.put(key, index++);
            System.out.println(rb.keysToTreeString());
        }
    }
}
