package b03_searches.rbbst;

import b03_searches.binary.BinarySearchTree;

import java.util.NoSuchElementException;

public class RedBlackBST<Key extends Comparable<Key>, Value>
{
    /**
     * Color of the link with the parent of a specific node: red or black
     * RED: node on the same level (must be followed by a black link)
     * BLACK: reflects going to the next level
     */
    private enum COLOR { RED, BLACK }

    private class Node
    {
        private Key key; // key to be assigned to this node
        private Value value; // value associated with this nodes key
        private Node leftSubtreeRoot; // can be black or red link, all keys less than current key
        private Node rightSubtreeRoot; // must be black link, all keys greater than current key
        private COLOR color; // if red, the node is considered on the same level as this nodes parent
        private int size; // cached size of the subtree with the root in this node

        public Node(Key key, Value value, COLOR color, int size)
        {
            this.key = key;
            this.value = value;
            this.color = color;
            this.rightSubtreeRoot = null;
            this.leftSubtreeRoot = null;
            this.size = size;
        }

        public Node(Key key, Value value, COLOR color, Node rightSubtree, Node leftSubtree)
        {
            this.key = key;
            this.value = value;
            this.color = color;
            this.rightSubtreeRoot = rightSubtree;
            this.leftSubtreeRoot = leftSubtree;
        }
    }

    /**
     * root of the entire BST tree
     */
    private Node root;

    public RedBlackBST() {  }

    private int size(Node node)
    {
        if (node==null) return 0;
        return node.size;
    }

    /**
     * rotate left the node previousRoot (its right node will replace the root)
     * used for when you have a red link on the right subtree
     *
     * @param previousRoot node to be rotated
     * @return new root
     */
    private Node rotateLeft(Node previousRoot)
    {
        //new root is right subtree of previous root
        Node newRoot = previousRoot.rightSubtreeRoot;
        // update the previousRoot as the left node of the new root
        previousRoot.rightSubtreeRoot = newRoot.leftSubtreeRoot;
        // left node of the new root becomes the previous root
        newRoot.leftSubtreeRoot = previousRoot;
        // update the colors: new root keeps the color of the previous root
        newRoot.color = previousRoot.color;
        // previous root color will become red
        previousRoot.color = COLOR.RED;
        // new root will have the same size as the previous root
        newRoot.size = previousRoot.size;
        // previous node size must be recomputed
        previousRoot.size = size(previousRoot.leftSubtreeRoot) + size(previousRoot.rightSubtreeRoot) + 1;
        return newRoot;
    }

    /**
     * rotate right the node n1 (its left node will replace the root)
     * previousRoot must not be null, its right node must be red
     * @param previousRoot node to be rotated
     * @return new top node
     */
    private Node rotateRight(Node previousRoot)
    {
        // new root is the left node of the previous root (red node)
        Node newRoot = previousRoot.leftSubtreeRoot;
        // right node of previous root stays the same, left node of previous will keep the elements between
        // leftN1 and n1
        previousRoot.leftSubtreeRoot = newRoot.rightSubtreeRoot;
        // left node of new root stays the same, right node of the new root becomes the previous root
        newRoot.rightSubtreeRoot = previousRoot;
        // update the colors: new root keeps the color of the previous root
        newRoot.color = previousRoot.color;
        // previous root color will become red
        previousRoot.color = COLOR.RED;
        // new root will have the same size as the previous root
        newRoot.size = previousRoot.size;
        // previous node size must be recomputed
        previousRoot.size = size(previousRoot.leftSubtreeRoot) + size(previousRoot.rightSubtreeRoot) + 1;
        return newRoot;
    }

    /**
     * move up the middle node of a 4-node by flipping the colors
     * left and right nodes must be red nodes
     * @param middleNode the middle node of a 4-node
     */
    private void flipColors(Node middleNode)
    {
        middleNode.color = COLOR.RED;
        middleNode.leftSubtreeRoot.color = COLOR.BLACK;
        middleNode.rightSubtreeRoot.color = COLOR.BLACK;
    }

    /**
     * adding value to root node tree
     * @param key
     * @param value
     */
    public void put(Key key, Value value)
    {
        // check if key is not null
        if (key==null) throw new NullPointerException("Key must not be null");
        // if no value is provided, we delete the key
        if (value == null) delete(root, key);
        if (root == null)
        {
            root = new Node(key, value, COLOR.BLACK, 1);
        } else
        {
            root = put(root, key, value);
            root.color = COLOR.BLACK;
            root.size++;
        }
    }

    private Node put(Node h, Key key, Value val) {
        if (h == null) return new Node(key, val, COLOR.RED, 1);

        int cmp = key.compareTo(h.key);
        if      (cmp < 0) h.leftSubtreeRoot  = put(h.leftSubtreeRoot,  key, val);
        else if (cmp > 0) h.rightSubtreeRoot = put(h.rightSubtreeRoot, key, val);
        else              h.value   = val;

        // fix-up any right-leaning links
        if (isRed(h.rightSubtreeRoot) && !isRed(h.leftSubtreeRoot))      h = rotateLeft(h);
        if (isRed(h.leftSubtreeRoot)  &&  isRed(h.leftSubtreeRoot.leftSubtreeRoot)) h = rotateRight(h);
        if (isRed(h.leftSubtreeRoot)  &&  isRed(h.rightSubtreeRoot))     flipColors(h);
        h.size = size(h.leftSubtreeRoot) + size(h.rightSubtreeRoot) + 1;

        return h;
    }

    public void delete(Key key)
    {
        if (!isRed(root.leftSubtreeRoot) && !isRed(root.rightSubtreeRoot))
            root.color = COLOR.RED;
        root = delete(root, key);
        if (!isEmpty()) root.color = COLOR.BLACK;
    }

    private Node delete(Node h, Key key)
    {
        if (key.compareTo(h.key) < 0) {
            if (!isRed(h.leftSubtreeRoot) && !isRed(h.leftSubtreeRoot.leftSubtreeRoot))
                h = moveRedLeft(h);
            h.leftSubtreeRoot = delete(h.leftSubtreeRoot, key);
        } else {
            if (isRed(h.leftSubtreeRoot))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.rightSubtreeRoot == null))
                return null;
            if (!isRed(h.rightSubtreeRoot) && !isRed(h.rightSubtreeRoot.leftSubtreeRoot))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                Node x = min(h.rightSubtreeRoot);
                h.key = x.key;
                h.value = x.value;
                h.rightSubtreeRoot = deleteMin(h.rightSubtreeRoot);
            } else h.rightSubtreeRoot = delete(h.rightSubtreeRoot, key);
        }
        return balance(h);
    }

    private Node balance(Node h)
    {
        if (isRed(h.rightSubtreeRoot))                      h = rotateLeft(h);
        if (isRed(h.leftSubtreeRoot) && isRed(h.leftSubtreeRoot.leftSubtreeRoot)) h = rotateRight(h);
        if (isRed(h.leftSubtreeRoot) && isRed(h.rightSubtreeRoot))     flipColors(h);
        h.size = size(h.leftSubtreeRoot) + size(h.rightSubtreeRoot) + 1;
        return h;
    }

    private Node moveRedLeft(Node h)
    {  // Assuming that h is red and both h.left and h.left.left
        // are black, make h.left or one of its children red.
        flipColors(h);
        if (isRed(h.rightSubtreeRoot.leftSubtreeRoot))
        {
            h.rightSubtreeRoot = rotateRight(h.rightSubtreeRoot);
            h = rotateLeft(h);
        }
        return h;
    }

    private Node moveRedRight(Node h)
    {  // Assuming that h is red and both h.right and h.right.left
        // are black, make h.right or one of its children red.
        flipColors(h);
        if (isRed(h.leftSubtreeRoot.leftSubtreeRoot))
            h = rotateRight(h);
        return h;
    }

    public void deleteMin()
    {
        if (!isRed(root.leftSubtreeRoot) && !isRed(root.rightSubtreeRoot))
            root.color = COLOR.RED;
        root = deleteMin(root);
        if (!isEmpty()) root.color = COLOR.BLACK;
    }

    private Node deleteMin(Node h)
    {
        if (h.leftSubtreeRoot == null)
            return null;
        if (!isRed(h.leftSubtreeRoot) && !isRed(h.leftSubtreeRoot.leftSubtreeRoot))
            h = moveRedLeft(h);
        h.leftSubtreeRoot = deleteMin(h.leftSubtreeRoot);
        return balance(h);
    }

    public void deleteMax()
    {
        if (!isRed(root.leftSubtreeRoot) && !isRed(root.rightSubtreeRoot))
            root.color = COLOR.RED;
        root = deleteMax(root);
        if (!isEmpty()) root.color = COLOR.BLACK;
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == COLOR.RED;
    }

    public boolean isEmpty() {
        return root == null;
    }

    private Node deleteMax(Node h)
    {
        if (isRed(h.leftSubtreeRoot))
            h = rotateRight(h);
        if (h.rightSubtreeRoot == null)
            return null;
        if (!isRed(h.rightSubtreeRoot) && !isRed(h.rightSubtreeRoot.leftSubtreeRoot))
            h = moveRedRight(h);
        h.rightSubtreeRoot = deleteMax(h.rightSubtreeRoot);
        return balance(h);
    }

    /**
     * Returns the smallest key in the symbol table.
     * @return the smallest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    // the smallest key in subtree rooted at x; null if no such key
    private Node min(Node x) {
        // assert x != null;
        if (x.leftSubtreeRoot == null) return x;
        else                return min(x.leftSubtreeRoot);
    }

    public String keysToTreeString()
    {
        return keysToString(root, "");
    }

    public String keysToString(Node subtreeRoot, String prefix)
    {
        if (subtreeRoot == null) return "";
        return keysToString(subtreeRoot.rightSubtreeRoot, prefix+" ")
                + prefix + subtreeRoot.key.toString()+"("+subtreeRoot.value.toString()+") "
                + ((subtreeRoot.color==COLOR.RED)?"R":"B") + "\n"
                +keysToString(subtreeRoot.leftSubtreeRoot, prefix + " ");
    }
}
