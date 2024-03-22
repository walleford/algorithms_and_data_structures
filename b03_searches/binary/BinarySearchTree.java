/**
 * AIT 512 C21 Binary Search Tree
 */
package b03_searches.binary;

import b03_searches.orderedst.OrderedSymbolTable;

import java.util.NoSuchElementException;

/**
 * A symbol table associates a key with a value.
 * Client inserts key-value pairs into the table and can search for the given value for a specific key.
 * There must be a value associated with each given key.
 * No duplicated Keys.
 * This implementation uses a Binary Search Tree for searching/sorting.
 *
 * @author Jordan Wallingsford
 * @version 1.0
 *
 * @param <Key> : key to create entry with
 * @param <Value> : value to associate with the key
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> implements OrderedSymbolTable<Key, Value>
{
    /**
     * A specific node in the entire Binary tree.
     * Contains the association between a key and a value, a leftSubtree containing keys less than the roots,
     * a rightSubtree containing values greater than the roots, a count of the total nodes.
     */
    private class Node
    {
        /**
         * Key associated with this node, greater than keys in leftSubtree and less than keys in rightSubtree
         */
        private Key key;
        /**
         * Value associated with this specific key
         */
        private Value value;
        /**
         * Link/pointer to the leftSubtree containing keys smaller than the roots key
         */
        private Node leftSubtree;
        /**
         * Link/pointer to the rightSubtree containing keys greater than the roots key
         */
        private Node rightSubtree;

        /**
         * total number of nodes in this subtree
         */
        private int size;

        /**
         * Creates a new node with no subtrees
         * @param key : key to assign this node
         * @param value : value to associate with the key
         */
        public Node(Key key, Value value)
        {
            this.key = key;
            this.value = value;
            this.size = 1;
            this.leftSubtree = null;
            this.rightSubtree = null;
        }
    }

    /**
     * root node for the entire binary tree. Null for an empty symbol table
     */
    private Node rootNode;

    /**
     * initializes the empty BST symbol table.
     */
    public BinarySearchTree()
    {
        rootNode = null;
    }

    /**
     * returns the smallest key in the symbol table
     *
     * @return smallest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    @Override
    public Key min()
    {
        if (isEmpty())
            throw new NoSuchElementException("an empty symbol table has no minimum.");
        return min(rootNode).key;
    }

    /**
     * return the minimum key node from a non-empty subtree provided
     * @param subtreeRoot the root of the subtree in which the key is searched
     * @return node of the min key
     */
    private Node min(Node subtreeRoot)
    {
        if (subtreeRoot.leftSubtree == null)
            return subtreeRoot;
        else
            return min(subtreeRoot.leftSubtree);
    }

    /**
     * returns the max key for a given subtree root node
     * @param subtreeRoot : subtree to search for the maximum node within
     * @return the node associated with the maximum key
     */
    private Node max(Node subtreeRoot)
    {
        if (subtreeRoot.rightSubtree == null)
            return subtreeRoot;
        else
            return max(subtreeRoot.rightSubtree);
    }

    /**
     * returns the largest key in the entire BST
     * @return largest key in the table
     * @throws NoSuchElementException if the table is empty
     */
    @Override
    public Key max()
    {
        if (isEmpty())
            throw new NoSuchElementException("Symbol table is empty and has no maximum.");
        return max(rootNode).key;
    }

    @Override
    public Key floor(Key key)
    {
        return null;
    }

    @Override
    public Key ceiling(Key key)
    {
        return null;
    }

    @Override
    public int rank(Key key)
    {
        return 0;
    }

    @Override
    public Key select(int rank)
    {
        return null;
    }

    /**
     * deletes the minimum node for a given root node
     * @param rootNode : tree to delete the min from
     * @return rootNode : new Node without the minimum from before
     */
    private Node deleteMin(Node rootNode)
    {
        // if no left tree, the root is the min so return the larger keys (the right tree)
        if (rootNode.leftSubtree == null)
            return rootNode.rightSubtree;
        // recursively call deleteMin on the left subtree until we get to a node with a null left subtree
        rootNode.leftSubtree = deleteMin(rootNode.leftSubtree);
        // decrement the size of the root
        rootNode.size--;
        return rootNode;
    }

    /**
     * removes the smallest key and associated value from the BST
     * @throws NoSuchElementException if the table is empty
     */
    @Override
    public void deleteMin()
    {
        if (isEmpty())
            throw new NoSuchElementException("Symbol table is empty. Can't delete.");
        rootNode = deleteMin(rootNode);
    }

    /**
     * Deletes the maximum key for the rootNode tree
     * @param rootNode the node to begin searching from for the max
     * @return rootNode - new rootNode without the maximum key from before
     */
    private Node deleteMax(Node rootNode)
    {
        // if right subtree is null, root is the max so return the smaller keys (left sub tree)
        if (rootNode.rightSubtree == null)
            return rootNode.leftSubtree;
        // call recursively until right subtree is null
        rootNode.rightSubtree = deleteMax(rootNode.rightSubtree);
        // decrement size and return the new rootNode without the max
        rootNode.size--;
        return rootNode;
    }

    @Override
    public void deleteMax()
    {
        if (isEmpty())
            throw new NoSuchElementException("Can't delete from an empty table");
        rootNode = deleteMax(rootNode);
    }

    @Override
    public int size(Key low, Key high)
    {
        return 0;
    }

    /**
     * returns the number of associations for a specific subtree
     * return 0 if subtree null
     * @param subtree root node of specified subtree
     * @return int size of subtree
     */
    private int size(Node subtree)
    {
        if (subtree == null)
            return 0;
        return subtree.size;
    }

    /**
     * Calls the private size() to return the size of the overall subtree by passing rootNode as the subtree.
     * @return
     */
    @Override
    public int size()
    {
        return size(rootNode);
    }

    @Override
    public Iterable<Key> keys(Key low, Key high)
    {
        return null;
    }

    /**
     * adds the association key/value if both are not null
     * @param treeRoot tree in which to add the association
     * @param key key associated
     * @param value value to associate with key
     * @return root node of the obtained tree
     */
    public Node put(Node treeRoot, Key key, Value value)
    {
        if (treeRoot == null) // if the tree is empty
            return new Node(key, value); // create a new single Node tree and return it
        // compare the key with the key in the root node of the tree
        int cmp = key.compareTo(treeRoot.key);
        // if the key is less than the key of the root node:
        if (cmp < 0)
            // put it in left subtree
            treeRoot.leftSubtree = put(treeRoot.leftSubtree, key, value);
        else if (cmp > 0)
            // put it in right subtree
            treeRoot.rightSubtree = put(treeRoot.rightSubtree, key, value);
        else // if it is the same as the root just assign it to the value
            treeRoot.value = value;
        // increment the size
        treeRoot.size = 1 + size(treeRoot.leftSubtree) + size(treeRoot.rightSubtree);

        return treeRoot;
    }

    @Override
    public void put(Key key, Value value)
    {
        if (key == null)
            throw new IllegalArgumentException("Keys are not allowed to be null. Fix it.");
        if (value == null)
        {
            delete(key);
            return;
        }
        rootNode = put(rootNode, key, value);
    }

    /**
     * Calls the private get() method for the rootNode to search for a specific key.
     * @param key : the key to return the value for
     * @return Value value: the value associated with the provided key in the root tree.
     */
    @Override
    public Value get(Key key)
    {
        return get(rootNode, key);
    }

    /**
     * Returns the value associated with a key in the subtree rooted at subTree
     *
     * If the key is less than the subtree key, recursively run get() in the left subTree. If the key is greater than
     * the root key, recursively run get() in the rightSubtree. Else, it is equal to this subtrees key so return it.
     * @param subTree : specific subtree to search for the key in
     * @param key : key to get from a subtree / compare against the current subtree key
     * @return : value associated with the searched for key
     */
    private Value get(Node subTree, Key key)
    {
        if (subTree == null)
            return null;
        int cmp = key.compareTo(subTree.key);
        if (cmp < 0)
            return get(subTree.leftSubtree, key);
        else if (cmp > 0)
            return get(subTree.rightSubtree, key);
        else return subTree.value;
    }

    private Node delete(Node rootNode, Key key)
    {
        if (rootNode == null)
            return null;
        int cmp = key.compareTo(rootNode.key);
        if (cmp < 0)
            rootNode.leftSubtree = delete(rootNode.leftSubtree, key);
        else if (cmp > 0)
            rootNode.rightSubtree = delete(rootNode.rightSubtree, key);
        else
        {
            // the key is equal to the root node key
            // if the root node right sub tree is empty, we delete the root and leave the entire left subtree
            if (rootNode.rightSubtree == null)
                return rootNode.leftSubtree;
            // if the left subtree is empty, we delete the root and return the entire right subtree
            if (rootNode.leftSubtree == null)
                return rootNode.rightSubtree;
            // otherwise we have both a right and left subtree/
            // we need to create a new temp node to be able to construct the new node without the deleted key
            Node temp = rootNode;
            // we take the min Node from the right subtree, this node is smaller than all nodes in the right sub tree
            // also we placed the node in the right subtree, so, its greater than the root node which is greater than
            // all the nodes in the left subtree. Because of this fact, we can use the minimum of the right subtree as
            // the new root node (the min of the right tree is greater than the max of the left tree)
            rootNode = min(temp.rightSubtree);
            // we must also delete the minimum from the right subtree after, to ensure it isn't in the tree twice.
            // this will result in the new right subtree
            rootNode.rightSubtree = deleteMin(temp.rightSubtree);
            // the left subtree can stay unchanged.
            rootNode.leftSubtree = temp.leftSubtree;
        }
        rootNode.size = size(rootNode.leftSubtree) + size(rootNode.rightSubtree) + 1;
        return rootNode;
    }

    @Override
    public void delete(Key key)
    {
        if (isEmpty())
            throw new NoSuchElementException("tree is empty, can't delete.");
        delete(rootNode, key);
    }

    /**
     * check if the provided key exists in the tree
     * @param key : key to check
     * @return {@code true} if the tree contains the key, {@code false} if it doesn't.
     */
    @Override
    public boolean contains(Key key)
    {
        // if get(key) == null, it doesn't contain the key.
        // if get(key) != null, it does contain the key.
        return get(key) != null;
    }

    /**
     * returns whether the table is empty or not
     * @return {@code true} if table is empty; {@code false} if table is not empty
     */
    @Override
    public boolean isEmpty()
    {
        return size() == 0;
    }

    @Override
    public Iterable<Key> keys()
    {
        return null;
    }

}
