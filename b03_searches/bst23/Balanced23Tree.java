package b03_searches.bst23;

public class Balanced23Tree<Key extends Comparable<Key>, Value>
{
    /**
     * abstract super class for all types of nodes
     */
    private abstract class Node
    {

    }

    /**
     * A 2-node with a root and left and right subtrees
     */
    private class Node2 extends Node
    {
        private Node leftSubtree;
        private Key rootKey;
        private Value rootValue;
        private Node rightSubtree;

        Node2(Key key, Value value)
        {
            leftSubtree = null;
            rootKey = null;
            rootValue = null;
            rightSubtree = null;
        }

        Node2(Node left, Key key, Value value, Node right)
        {
            leftSubtree = left;
            rootKey = key;
            rootValue = value;
            rightSubtree = right;
        }
    }

    private class Node3 extends Node
    {
        private Node leftSubtree;
        private Key root1Key;
        private Value root1Value;
        private Node middleSubtree;
        private Key root2Key;
        private Value root2Value;
        private Node rightSubtree;

        /**
         * 3-node with null links (final node in a tree)
         * @param key1 : first key to be assigned to the node
         * @param value1 : value to be associated with the first key
         * @param key2 : second key to be assigned to the node
         * @param value2 : value to be associated with the second key
         */
        Node3(Key key1, Value value1, Key key2, Value value2)
        {
            leftSubtree = null;
            root1Key = key1;
            root1Value = value1;
            middleSubtree = null;
            root2Key = key2;
            root2Value = value2;
            rightSubtree = null;
        }

        /**
         * 3-node with links to the its children (left, middle, and right subtrees)
         * @param left : left subtree for the child (keys smaller than the root 3-node)
         * @param key1 : first key to be assigned to the node
         * @param value1 : value to be associated with the first key
         * @param middle : middle subtree (key between the two keys of the root 3-node)
         * @param key2 : second key to be assigned to the node
         * @param value2 : value to be associated with the second key
         * @param right : right subtree (keys larger than the root 3-node)
         */
        Node3(Node left, Key key1, Value value1, Node middle, Key key2, Value value2, Node right)
        {
            leftSubtree = left;
            root1Key = key1;
            root1Value = value1;
            middleSubtree = middle;
            root2Key = key2;
            root2Value = value2;
            rightSubtree = right;
        }
    }

    /**
     * 4-node containing left subtree, right subtree, a middle left subtree, and a middle right subtree.
     * Can be initialized with either no links or all links.
     */
    private class Node4 extends Node
    {
        private Node leftSubtree;
        private Key root1Key;
        private Value root1Value;
        private Node middleLeftSubtree;
        private Key root2Key;
        private Value root2Value;
        private Node middleRightSubtree;
        private Key root3Key;
        private Value root3Value;
        private Node rightSubtree;

        Node4(Key key1, Value value1, Key key2, Value value2, Key key3, Value value3)
        {
            leftSubtree = null;
            root1Key = key1;
            root1Value = value1;
            middleLeftSubtree = null;
            root2Key = key2;
            root2Value = value2;
            middleRightSubtree = null;
            root3Key = key3;
            root3Value = value3;
            rightSubtree = null;
        }

        Node4(Node left, Key key1, Value value1, Node middleLeft, Key key2, Value value2, Node middleRight,
              Key key3, Value value3, Node right)
        {
            leftSubtree = left;
            root1Key = key1;
            root1Value = value1;
            middleLeftSubtree = middleLeft;
            root2Key = key2;
            root2Value = value2;
            middleRightSubtree = middleRight;
            root3Key = key3;
            root3Value = value3;
            rightSubtree = right;
        }
    }

    /**
     * root node for the entire 2-3 tree
     */
    private Node rootNode;

    /**
     * empty balanced tree
     */
    public Balanced23Tree()
    {
        rootNode = null;
    }
}
