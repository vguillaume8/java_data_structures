package structures.trees;

import structures.commons.LinearDataStructure;
import structures.commons.Pair;
import structures.vectors.ArrayList;
import structures.vectors.Queue;

/**
 * Implementation of Binary Search BinaryTree.
 *
 * @param <K> Generic type for keys (must extend java.lang.Comparable)
 * @param <V> Generic type for values
 */
public class BinarySearchTree<K extends Comparable, V> implements BinaryTree<K, V> {

    protected int size;

    /**
     * Pointer to root node of the BinarySearchTree
     */
    protected BinarySearchTreeNode<K, V> root;

//------------------------------------------------------------------------------

    /**
     * Initialize an empty BinarySearchTree.
     */
    @SuppressWarnings("unused")
    public BinarySearchTree() {
        super();
        this.root = null;
    }

//------------------------------------------------------------------------------

    /**
     * Initialize a BinarySearchTree with a specified
     * set of keys.
     *
     * @param keys Specified keys to insert into BinarySearchTree
     */
    @SuppressWarnings("unused")
    public BinarySearchTree(K[] keys) {
        this();

        // Insert all the keys into the tree
        for (K value : keys) {
            this.insert(value);
        }
    }

    //------------------------------------------------------------------------------

    /**
     * Constructs a tree from an array of Key-value pairs.
     *
     * @param pairs Key-value pairs to construct tree from.
     */
    @SuppressWarnings("unused")
    public BinarySearchTree(Pair<K, V>[] pairs) {
        this();

        // Insert all the pairs into the tree
        for (Pair<K, V> pair : pairs) {
            insert(pair.key(), pair.value());
        }
    }

//------------------------------------------------------------------------------

    /**
     * Constructs a tree from a linear data structure of pairs.
     *
     * @param pairs Key-value pairs to construct tree from.
     */
    @SuppressWarnings("unused")
    public BinarySearchTree(LinearDataStructure<Pair<K, V>> pairs) {
        this();

        // Insert all the pairs into the tree
        for (Pair<K, V> pair : pairs) {
            this.insert(pair.key(), pair.value());
        }
    }

//------------------------------------------------------------------------------

    /**
     * Determines whether or not a specified key is present in the tree.
     *
     * @param key Key to search for in tree.
     * @return True if and only if the tree contains a node with the specified key.
     */
    @Override
    public boolean contains(K key) {

        // Must not be empty, and contain the value
        return !this.empty() && this.contains(root, key);
    }

//------------------------------------------------------------------------------

    /**
     * Determines whether or not a specified key-value pair is
     * present in the tree. The key must correspond to the value.
     *
     * @param key Specified key.
     * @param value Specified value.
     * @return True if and only if there is a node that both the specified key and value.
     */
    @SuppressWarnings("unused")
    public boolean contains(K key, V value) {
        BinarySearchTreeNode<K, V> found = find(root, key);

        // If we did not find the key
        if (found == null)
            return false;

        // If we found the value, we must check that
        // their values are equal
        return value.equals(found.value);
    }

//------------------------------------------------------------------------------

    /**
     * Auxiliary function to start recursive call to check if the tree
     * contains a given key.
     *
     * @param key Key to search for in tree.
     * @return True if and only if the tree contains a node with the specified key.
     */
    private boolean contains(BinarySearchTreeNode<K, V> node, K key) {
        return !(this.find(node, key) == null);
    }

//------------------------------------------------------------------------------

    /**
     * Wrapper around {@code contains()} method for more readable syntax.
     *
     * @param key Key to search for in tree.
     * @return True if and only if the tree contains a node with the specified key.
     */
    @SuppressWarnings("unused")
    public boolean containsKey(K key) {
        return contains(key);
    }

//------------------------------------------------------------------------------

    /**
     * Determines whether or not the tree contains a given value. Note, there may
     * be multiple instances of the specified value, as the only thing that is unique
     * in the tree is the key. This search will stop after the first instance of the value
     * is found via pre-order traversal.
     *
     * @param value Value to look for.
     * @return True if and only if the value is in the tree.
     */
    @SuppressWarnings("unused")
    public boolean containsValue(V value) {
        boolean contains;

        // Empty tree cannot contain
        // a value
        if (empty()) {
            return false;
        }

        // If we got a node back, we found it.
        // if we got null back, we did not find it
        return getByValue(root, value) != null;

    }

//------------------------------------------------------------------------------

    /**
     * Determines whether or not the BinarySearchTree is empty.
     *
     * @return True if and only if there are no elements in the tree.
     */
    @Override
    public boolean empty() {

        // Empty is defined as size 0, and root node is null
        return this.size == 0 && this.root == null;
    }

//------------------------------------------------------------------------------

    /**
     * Finds a key via binary search.
     *
     * @param node Node to start recursion from.
     * @param key Key to search for.
     * @return True if the key is found.
     */
    @SuppressWarnings("unused")
    private BinarySearchTreeNode<K, V> find(BinarySearchTreeNode<K, V> node, K key) {

        // If this node is not null
        // perform binary search
        if (node != null) {

            // node.key == key
            if (node.key.equals(key)) {
                return node;
            }

            // If node.key < key
            else if (key.compareTo(node.key) < 0) {
                return find(node.leftChild, key);
            }

            // If node.key > key
            else {
                return find(node.rightChild, key);
            }
        }

        // Otherwise we did not
        // find the node
        return null;
    }

//------------------------------------------------------------------------------

    /**
     * Retrieves and returns the value that corresponds to a specified key.
     *
     * @param key Specified key.
     * @return corresponding value to the key.
     */
    public V get(K key) {

        // Get node by key
        BinarySearchTreeNode<K, V> gotten = find(root, key);

        // If its null, we did not find it, so return null,
        // otherwise, return we found it, and return its value.
        return gotten == null ? null : gotten.value;
    }

//------------------------------------------------------------------------------

    /**
     * Find node by value via pre-order traversal.
     *
     * @param node Node to start recursion from.
     * @param value Value to search for.
     * @return True if the value is found.
     */
    private BinarySearchTreeNode<K, V> getByValue(BinarySearchTreeNode<K, V> node, V value) {

        // Check this value
        if (node == null || value.equals(node.value)) {
            return node;
        }

        // Check to see if we found it on the left
        BinarySearchTreeNode<K, V> left = getByValue(node.leftChild, value);

        // If the we found it on the left, return it, otherwise, check right
        return left != null ? left : getByValue(node.rightChild, value);
    }

//------------------------------------------------------------------------------

    /**
     * Returns the first key found that corresponds to a specified value. Traversal
     * is done in pre-order.
     *
     * @param value Specified value.
     * @return Corresponding key.
     */
    @SuppressWarnings("unused")
    public K getKey(V value) {
        BinarySearchTreeNode<K, V> found = getByValue(root, value);

        return found == null ? null : found.key;
    }

//------------------------------------------------------------------------------

    /**
     * Returns the height of the tree.
     *
     * @return Max depth of the tree
     */
    public int height() {

        return height(root);
    }

//------------------------------------------------------------------------------

    /**
     * Determines the maximum depth (number of levels) in the tree.
     *
     * @param node Node to start recursion from.
     * @return Number of levels in the BinaryTree
     */
    protected int height(BinarySearchTreeNode<K, V> node) {

        // Pick the larger of the two, and add one to account
        // for the level that we are currently at.
        return node == null ? 0 : Math.max(height(node.leftChild), height(node.rightChild)) + 1;
    }

//------------------------------------------------------------------------------

    /**
     * Inserts a specified key-value pair into the tree.
     *
     * @param key Key
     * @param value Value
     * @return True if and only if the key is not already in the tree.
     */
    public boolean insert(K key, V value) {
        int oldSize = this.size;

        root = insert(root, key, value);

        return oldSize != this.size;
    }

    protected BinarySearchTreeNode<K, V> insert(BinarySearchTreeNode<K, V> node, K key, V value) {

        if (node == null) {
            this.size++;

            return new BinarySearchTreeNode<>(key, value);
        }

        int comparison = key.compareTo(node.key);

        if (comparison < 0) {

            node.leftChild = insert(node.leftChild, key, value);

        } else if (comparison > 0) {

            node.rightChild = insert(node.rightChild, key, value);
        }


        return node;
    }

//------------------------------------------------------------------------------

    /**
     * Determines whether or not the tree is balanced. A balanced tree is defined
     * as a tree where for all subtrees of a given node, the maximum depth of the
     * left subtree differs from the maximum depth of right subtree by at most one.
     * An example follows:
     *
     * TODO - Insert image of example
     *
     * @return True if and only if the tree is balanced.
     */
    @SuppressWarnings("unused")
    public boolean isBalanced() {

        return empty() || isBalanced(root) > -1;
    }

    /**
     * Returns true if the tree is balanced.
     *
     * @param node Node to start recursion from.
     * @return True if the above condition is met.
     */
    private int isBalanced(BinarySearchTreeNode<K, V> node) {
        if (node == null)
            return 0;

        // Get heights of subtrees
        int h1 = isBalanced(node.leftChild);
        int h2 = isBalanced(node.rightChild);

        // If either or the subtrees are not
        // height balanced, we propagate the
        // -1 back up the tree because we kno
        // if a subtree is not balanced, the whole
        // tree is unbalanced, and thus we do not
        // need to continue traversing the tree.
        if (h1 == -1 || h2 == -1)
            return -1;

        // If they differ by more than 1,
        // then we return -1, signalling
        // that the tree at this node is not
        // height balanced
        if (Math.abs(h1 - h2) > 1)
            return -1;

        // Pick the max between left and right subtree
        // heights, and add 1 to include the level that we
        // are currently at.
        if (h1 > h2)
            return h1 + 1;
        else
            return h2 + 1;
    }

//------------------------------------------------------------------------------

    /**
     *
     * Determines whether or not the tree is complete. A complete tree is defined
     * as a tree where all levels are filled, except possibly the bottom (deepest) level.
     * In the event that the last level is not filled, all values in tree are as left as possible.
     * An example follows:
     *
     * TODO - Insert image of example
     *
     *
     * @return True if and only if the above conditions are met.
     */
    public boolean isComplete() {
        Queue<BinarySearchTreeNode<K, V>> queue;
        BinarySearchTreeNode<K, V> temp;
        BinarySearchTreeNode<K, V> left;
        BinarySearchTreeNode<K, V> right;
        boolean flag;

        // An empty tree is a complete tree
        if (empty()) {
            return true;
        }

        // Create an empty Queue
        queue = new Queue<>();

        // Flag variables which will be true
        // when non full node is seen
        flag = false;

        // Start with the first root, insert into queue
        queue.insert(root);

        // Level order traversal using Queue
        while (!queue.empty()) {

            // Get node thing from Queue
            temp = queue.remove();

            // Get the children
            left = temp.leftChild;
            right = temp.rightChild;

            // Check if the node has a left child
            if (left != null) {

                // If we have seen a non full node, and
                // we also see a node (temp) with a non empty
                // left child, then the tree is not complete
                if (flag)
                    return false;

                // We must check the left child's children
                queue.insert(left);

            // If this is a non-full node, set the flag true
            } else {
                flag = true;
            }

            if (right != null) {

                if (flag)
                    return false;

                queue.insert(right);
            } else {
                flag = true;
            }
        }

        return true;
    }

//------------------------------------------------------------------------------

    /**
     * Determines whether or not the tree is full. A full tree is defined
     * as a tree where all nodes other than leaf nodes have two children.
     * An example follows:
     *
     * TODO - Insert image of example
     *
     * @return True if the above condition is met.
     */
    @SuppressWarnings("unused")
    public boolean isFull() {
        return this.isFull(root);
    }

//------------------------------------------------------------------------------

    /**
     * Auxiliary function to determine whether or not the tree is full.
     *
     * @param node Node to start recursion from.
     * @return True if the above condition is met.
     */
    @SuppressWarnings("unused")
    private boolean isFull(BinarySearchTreeNode<K, V> node) {
        BinarySearchTreeNode<K, V> left;
        BinarySearchTreeNode<K, V> right;

        // An empty tree (or leaf node) is full be definition
        if (node == null) {
            return true;
        }

        // Get the left and right children
        left = node.leftChild;
        right = node.rightChild;

        // Either both must not be null
        if (left != null && right != null) {
            return isFull(node.leftChild) && isFull(node.rightChild);
        }

        // Or both must be null
        return left == null && right == null;
    }

//------------------------------------------------------------------------------

    /**
     * Returns true if an only if the tree is perfect. A perfect tree is defined
     * as a tree whether all nodes that are not leaf nodes have two children, and
     * all leaf nodes are at the same level. An example follows:
     *
     * TODO - Insert image of example
     *
     * @return True if and only if the above conditions are met.
     */
    public boolean isPerfect() {
        return this.isPerfect(root, height(), 0);
    }

//------------------------------------------------------------------------------

    /**
     * Returns true if an only if the tree is perfect.
     *
     * @param node Current node in the tree to check.
     * @return True if and only if the above conditions are met.
     */
    private boolean isPerfect(BinarySearchTreeNode<K, V> node, int depth, int level) {
        BinarySearchTreeNode<K, V> left;
        BinarySearchTreeNode<K, V> right;

        // An empty tree is perfect
        if (node == null) {
            return true;
        }

        // Get the left and right children
        left = node.leftChild;
        right = node.rightChild;

        // If both children are null, we just need
        // to make sure the levels are the same
        if (left == null && right == null)
            return depth == level + 1;

        // If either child is null, then the tree
        // automatically is not perfect
        if (left == null || right == null)
            return false;

        // Otherwise we need to continue down the tree
        return isPerfect(left, depth, level+1) && isPerfect(right, depth, level+1);
    }

//------------------------------------------------------------------------------

    /**
     * Returns an array of the keys in the tree in a specified order.
     *
     * @param traversalType Specified order.
     * @return Array of keys in the tree.
     */
    public K[] keys(int traversalType) {
        ArrayList<K> arrayList = keysToArrayList(root, traversalType);

        return arrayList.toArray((K[]) new Comparable[0]);
    }

//------------------------------------------------------------------------------

    /**
     * Returns {@code ArrayList} of keys in specified order.
     *
     * @param node Node to start recursion from.
     * @param traversalType Order in which to return the keys.
     * @return ArrayList of keys.
     */
    @SuppressWarnings("unused")
    private ArrayList<K> keysToArrayList(BinarySearchTreeNode<K, V> node, int traversalType) {
        ArrayList<K> arrayList1;

        switch (traversalType) {
            case IN_ORDER:    arrayList1 = this.keysToArrayListInOrder(node, new ArrayList<K>());   break;
            case PRE_ORDER:   arrayList1 = this.keysToArrayListPreOrder(node, new ArrayList<K>());  break;
            case POST_ORDER:  arrayList1 = this.keysToArrayListPostOrder(node, new ArrayList<K>()); break;
            case LEVEL_ORDER: arrayList1 = this.keysToArrayListLevelOrder(node);                    break;
            default:          arrayList1 = this.keysToArrayList(node, DEFAULT_ORDER);               break;
        }

        return arrayList1;
    }

//------------------------------------------------------------------------------

    /**
     * Returns ArrayList of keys in-order.
     *
     * @param node Node to start recursion from.
     * @param arrayList ArrayList of keys in-order.
     * @return ArrayList of keys.
     */
    private ArrayList<K> keysToArrayListInOrder(BinarySearchTreeNode<K, V> node, ArrayList<K> arrayList) {
        if (node == null) {
            return arrayList;
        }

        arrayList = keysToArrayListInOrder(node.leftChild, arrayList);
        arrayList.insert(node.key);
        arrayList = keysToArrayListInOrder(node.rightChild, arrayList);

        return arrayList;
    }

//------------------------------------------------------------------------------

    /**
     * Returns ArrayList of keys in pre-order.
     *
     * @param node Node to start recursion from.
     * @param arrayList ArrayList of keys pre-order.
     * @return ArrayList of keys.
     */
    private ArrayList<K> keysToArrayListPreOrder(BinarySearchTreeNode<K, V> node, ArrayList<K> arrayList) {
        if (node == null) {
            return arrayList;
        }

        arrayList.insert(node.key);
        arrayList = keysToArrayListPreOrder(node.leftChild, arrayList);
        arrayList = keysToArrayListPreOrder(node.rightChild, arrayList);

        return arrayList;
    }

//------------------------------------------------------------------------------

    /**
     * Returns ArrayList of keys in post-order.
     *
     * @param node Node to start recursion from.
     * @param arrayList ArrayList of keys post-order.
     * @return ArrayList of keys.
     */
    private ArrayList<K> keysToArrayListPostOrder(BinarySearchTreeNode<K, V> node, ArrayList<K> arrayList) {
        if (node == null) {
            return arrayList;
        }

        arrayList = keysToArrayListPostOrder(node.leftChild, arrayList);
        arrayList = keysToArrayListPostOrder(node.rightChild, arrayList);
        arrayList.insert(node.key);

        return arrayList;
    }

    /**
     * Returns ArrayList of keys in level-order.
     *
     * @param node Node to start recursion from.
     * @return ArrayList of keys.
     */
    private ArrayList<K> keysToArrayListLevelOrder(BinarySearchTreeNode<K, V> node) {
        ArrayList<K> arrayList;
        Queue<BinarySearchTreeNode<K, V>> queue;
        BinarySearchTreeNode<K, V> temp;
        BinarySearchTreeNode<K, V> left;
        BinarySearchTreeNode<K, V> right;

        // Initialize the list
        arrayList = new ArrayList<>();

        // Empty tree
        if (node == null) {
            return arrayList;
        }

        // Create a Queue for level order traversal
        queue = new Queue<>();

        // Insert the first node to start
        queue.insert(node);

        // While the Queue is not empty (all nodes have not been seen)
        while (!queue.empty()) {

            // Remove from queue, and insert into ArrayList
            temp = queue.remove();
            arrayList.insert(temp.key);

            // Get children
            left = temp.leftChild;
            right = temp.rightChild;

            // Check left
            if (left != null) {
                queue.insert(left);
            }

            // Check right
            if (right != null) {
                queue.insert(right);
            }
        }

        return arrayList;
    }

//------------------------------------------------------------------------------

    /**
     * Returns an ArrayList of key-value pairs in the a tree in a specified order.
     *
     * @param traversalType Specified order.
     * @return ArrayList of key-value pairs.
     */
    public ArrayList<Pair<K, V>> pairs(int traversalType) {

        return pairsToArrayList(root, traversalType);
    }

//------------------------------------------------------------------------------

    /**
     * Auxiliary function for returning ArrayList of key-value pairs.
     *
     * @param node Node to start recursion from.
     * @param traversalType Specified order.
     * @return ArrayList of key-value pairs.
     */
    private ArrayList<Pair<K, V>> pairsToArrayList(BinarySearchTreeNode<K, V> node, int traversalType) {
        ArrayList<Pair<K, V>> arrayList1;

        switch (traversalType) {
            case IN_ORDER:    arrayList1 = this.pairsToArrayListInOrder(node, new ArrayList<Pair<K, V>>());   break;
            case PRE_ORDER:   arrayList1 = this.pairsToArrayListPreOrder(node, new ArrayList<Pair<K, V>>());  break;
            case POST_ORDER:  arrayList1 = this.pairsToArrayListPostOrder(node, new ArrayList<Pair<K, V>>()); break;
            case LEVEL_ORDER: arrayList1 = this.pairsToArrayListLevelOrder(node);                             break;
            default:          arrayList1 = this.pairsToArrayList(node, DEFAULT_ORDER);                        break;
        }

        return arrayList1;
    }

//------------------------------------------------------------------------------

    /**
     * Returns ArrayList of pairs in in-order.
     *
     * @param node Node to start recursion from.
     * @param arrayList ArrayList to append to.
     * @return ArrayList of pairs.
     */
    private ArrayList<Pair<K, V>> pairsToArrayListInOrder(BinarySearchTreeNode<K, V> node, ArrayList<Pair<K, V>> arrayList) {
        if (node == null) {
            return arrayList;
        }

        arrayList = pairsToArrayListInOrder(node.leftChild, arrayList);
        arrayList.insert(node);
        arrayList = pairsToArrayListInOrder(node.rightChild, arrayList);

        return arrayList;
    }

//------------------------------------------------------------------------------

    /**
     * Returns ArrayList of pairs in pre-order.
     *
     * @param node Node to start recursion from.
     * @param arrayList ArrayList to append to.
     * @return ArrayList of pairs.
     */
    private ArrayList<Pair<K, V>> pairsToArrayListPreOrder(BinarySearchTreeNode<K, V> node, ArrayList<Pair<K, V>> arrayList) {
        if (node == null) {
            return arrayList;
        }

        arrayList.insert(node);
        arrayList = pairsToArrayListPreOrder(node.leftChild, arrayList);
        arrayList = pairsToArrayListPreOrder(node.rightChild, arrayList);

        return arrayList;
    }

//------------------------------------------------------------------------------

    /**
     * Returns ArrayList of pairs in post-order.
     *
     * @param node Node to start recursion from.
     * @param arrayList ArrayList to append to.
     * @return ArrayList of pairs.
     */
    private ArrayList<Pair<K, V>> pairsToArrayListPostOrder(BinarySearchTreeNode<K, V> node, ArrayList<Pair<K, V>> arrayList) {
        if (node == null) {
            return arrayList;
        }

        arrayList = pairsToArrayListPostOrder(node.leftChild, arrayList);
        arrayList = pairsToArrayListPostOrder(node.rightChild, arrayList);
        arrayList.insert(node);

        return arrayList;
    }

//------------------------------------------------------------------------------

    /**
     * Returns ArrayList of pairs in level-order.
     *
     * @param node Node to start recursion from.
     * @return ArrayList of pairs.
     */
    private ArrayList<Pair<K, V>> pairsToArrayListLevelOrder(BinarySearchTreeNode<K, V> node) {
        ArrayList<Pair<K, V>> arrayList;
        Queue<BinarySearchTreeNode<K, V>> queue;
        BinarySearchTreeNode<K, V> temp;
        BinarySearchTreeNode<K, V> left;
        BinarySearchTreeNode<K, V> right;

        // Initialize the list
        arrayList = new ArrayList<>();

        // Empty tree
        if (node == null) {
            return arrayList;
        }

        // Create a Queue for level order traversal
        queue = new Queue<>();

        // Insert the first node to start
        queue.insert(node);

        // While the Queue is not empty (all nodes have not been seen)
        while (!queue.empty()) {

            // Remove from queue, and insert into ArrayList
            temp = queue.remove();
            arrayList.insert(temp);

            // Get children
            left = temp.leftChild;
            right = temp.rightChild;

            // Check left
            if (left != null) {
                queue.insert(left);
            }

            // Check right
            if (right != null) {
                queue.insert(right);
            }
        }

        return arrayList;
    }

//------------------------------------------------------------------------------

    /**
     * Retrieves and removes a specified key from the tree.
     *
     * Algorithm inspired by http://www.algolist.net/Data_structures/Binary_search_tree/Removal
     *
     * @param key The specified key to remove from the tree.
     * @return True if the node associate with the key was removed successfully.
     */
    public boolean remove(K key) {
        boolean result;

        // If the tree is empty
        if (this.empty()) {
            return false;

            // If the tree is not empty
        } else {

            // If the key we want to remove is at the root
            if (root.key.equals(key)) {
                BinarySearchTreeNode<K, V> auxRoot = new BinarySearchTreeNode<K, V>(null, null);

                auxRoot.leftChild = root;
                result = root.remove(auxRoot, key);
                root = auxRoot.leftChild;
            }

            // If the key might be somewhere in the tree
            else {
                result = root.remove(null, key);
            }
        }

        // If the removal was successful
        if (result) {
            this.size--;
        }

        return result;
    }

//------------------------------------------------------------------------------

    /**
     * Returns a specified array of keys in ascending order via tree sort.
     *
     * @param keys Keys to sort.
     * @param <K> Comparable keys
     * @param <V> Value type for the tree (can be anything).
     * @return Sorted array of pairs.
     */
    @SuppressWarnings("unused")
    public static <K extends Comparable, V> K[] sort(K[] keys) {

        // Build the tree
        BinarySearchTree<K, V> tree = new BinarySearchTree<K, V>(keys);

        // Return it as an in-order array
        return tree.keys(IN_ORDER);
    }

//------------------------------------------------------------------------------

    /**
     * Returns a specified array of key-value pairs in ascending order via tree sort.
     *
     * @param pairs Keys to sort.
     * @param <K> Comparable keys.
     * @param <V> Value type for the tree (can be anything).
     * @return Sorted array of pairs.
     */
    @SuppressWarnings("unused")
    public static <K extends Comparable, V> ArrayList<Pair<K, V>> sort(ArrayList<Pair<K, V>> pairs) {

        // Build the tree
        BinarySearchTree<K, V> tree = new BinarySearchTree<K, V>(pairs);

        // Return it as an in-order ArrayList
        return tree.pairs(IN_ORDER);
    }

//------------------------------------------------------------------------------

    /**
     * Returns a String representation of the tree.
     *
     * @return String representation of the tree
     */
    @Override
    public String toString() {
        return this.toString(DEFAULT_ORDER);
    }

//------------------------------------------------------------------------------

    /**
     * Returns tree as String on it's side.
     *
     * @return String representation of tree.
     */
    @Override
    @SuppressWarnings("unused")
    public String toTreeStringVertical() {
        return this.toTreeStringVertical("", true, "", root);
    }

//------------------------------------------------------------------------------

    /**
     * Auxiliary function to return tree as String on it's side.
     *
     * @param prefix String to continuously append to.
     * @param top   Boolean value to determine whether or not the node
     *              is a right node, or a left node.
     * @param tree String of the tree, after the current node has been appended to prefix.
     * @param node Note to append to tree String.
     * @return Vertical String representation of the tree.
     */
    private String toTreeStringVertical(String prefix, boolean top, String tree, BinarySearchTreeNode<K, V> node) {
        BinarySearchTreeNode<K, V> left;
        BinarySearchTreeNode<K, V> right;
        String temp;

        // If we were passed an empty tree
        if (node == null) {
            return "";
        }

        // Get children nodes
        left = node.leftChild;
        right = node.rightChild;

        // If the right child is not null
        if (right != null) {

            // Draw a path to the node
            temp = path(top, "" + prefix, "│   ", "    ");

            // Recursively append to right subtree
            tree = toTreeStringVertical(temp, false, tree, right);
        }

        // Draw a path to this node
        tree = path(top, tree + prefix, "└──", "┌──");

        // Append this node to the tree
        tree += " " + node.key + "\n";

        // If the left child is not null
        if (left != null) {

            // Draw a path to the node
            temp = path(top, "" + prefix, "    ", "│   ");

            // Recursively append left sub tree
            tree = toTreeStringVertical(temp, true, tree, left);
        }

        return tree;
    }

//------------------------------------------------------------------------------

    /**
     * Auxiliary function for printing the tree as a vertical String.
     *
     * @param condition
     * @param s
     * @param choice1
     * @param choice2
     * @return
     */
    private String path(boolean condition, String s, String choice1, String choice2) {

        if (condition) {
            s += choice1;
        } else {
            s += choice2;
        }

        return s;
    }

//------------------------------------------------------------------------------

    /**
     *
     * @return
     */
    @Override
    public int size() {
        return this.size;
    }

//------------------------------------------------------------------------------

    /**
     * Returns String representation of tree horizontally.
     *
     * @return BinaryTree string.
     */
    @SuppressWarnings("unused")
    public String toTreeStringHorizontal() {
        return "";
    }

//------------------------------------------------------------------------------

    /**
     * Returns an array of the values in the tree in a specified order.
     *
     * @param traversalType Specified order.
     * @return Array of values in the tree.
     */
    @SuppressWarnings("unused")
    public V[] values(int traversalType) {
        ArrayList<V> arrayList;

        switch (traversalType) {
            case IN_ORDER:      arrayList = valuesToArrayListInOrder(root, new ArrayList<>());   break;
            case PRE_ORDER:     arrayList = valuesToArrayListPreOrder(root, new ArrayList<>());  break;
            case POST_ORDER:    arrayList = valuesToArrayListPostOrder(root, new ArrayList<>()); break;
            case LEVEL_ORDER:   arrayList = valuesToArrayListLevelOrder(root);                   break;
            default:            return values(DEFAULT_ORDER);
        }

        return arrayList.toArray((V[]) new Object[0]);
    }

//------------------------------------------------------------------------------

    /**
     *
     * @param node
     * @param arrayList
     * @return
     */
    @SuppressWarnings("unused")
    private ArrayList<V> valuesToArrayListInOrder(BinarySearchTreeNode<K, V> node, ArrayList<V> arrayList) {
        if (node == null) {
            return arrayList;
        }

        arrayList = valuesToArrayListInOrder(node.leftChild, arrayList);
        arrayList.insert(node.value);
        arrayList = valuesToArrayListInOrder(node.rightChild, arrayList);

        return arrayList;
    }

//------------------------------------------------------------------------------

    /**
     *
     * @param node
     * @param arrayList
     * @return
     */
    @SuppressWarnings("unused")
    private ArrayList<V> valuesToArrayListPreOrder(BinarySearchTreeNode<K, V> node, ArrayList<V> arrayList) {
        if (node == null) {
            return arrayList;
        }

        arrayList.insert(node.value);
        arrayList = valuesToArrayListPreOrder(node.leftChild, arrayList);
        arrayList = valuesToArrayListPreOrder(node.rightChild, arrayList);

        return arrayList;
    }

//------------------------------------------------------------------------------

    /**
     *
     * @param node
     * @param arrayList
     * @return
     */
    @SuppressWarnings("unused")
    private ArrayList<V> valuesToArrayListPostOrder(BinarySearchTreeNode<K, V> node, ArrayList<V> arrayList) {
        if (node == null) {
            return arrayList;
        }

        arrayList = valuesToArrayListPostOrder(node.leftChild, arrayList);
        arrayList = valuesToArrayListPostOrder(node.rightChild, arrayList);
        arrayList.insert(node.value);

        return arrayList;
    }

//------------------------------------------------------------------------------

    /**
     *
     * @param node
     * @return
     */
    @SuppressWarnings("unused")
    private ArrayList<V> valuesToArrayListLevelOrder(BinarySearchTreeNode<K, V> node) {
        ArrayList<V>arrayList;
        Queue<BinarySearchTreeNode<K, V>> queue;
        BinarySearchTreeNode<K, V> temp;
        BinarySearchTreeNode<K, V> left;
        BinarySearchTreeNode<K, V> right;

        // Initialize the list
        arrayList = new ArrayList<>();

        // Empty tree
        if (node == null) {
            return arrayList;
        }

        // Create a Queue for level order traversal
        queue = new Queue<>();

        // Insert the first node to start
        queue.insert(node);

        // While the Queue is not empty (all nodes have not been seen)
        while (!queue.empty()) {

            // Remove from queue, and insert into ArrayList
            temp = queue.remove();
            arrayList.insert(temp.value);

            // Get children
            left = temp.leftChild;
            right = temp.rightChild;

            // Check left
            if (left != null) {
                queue.insert(left);
            }

            // Check right
            if (right != null) {
                queue.insert(right);
            }
        }

        return arrayList;
    }

    /**
     * Node for use in Binary SearchTree.
     *
     * @author Jabari Dash
     * @param <K> Generic type
     */
    protected static class BinarySearchTreeNode<K extends Comparable, V> implements Pair<K, V> {

        /**
         * Key of the node
         */
        K key;

        /**
         * Value of the node
         */
        V value;

        /**
         * Pointer to left child
         */
        protected BinarySearchTreeNode<K, V> leftChild;

        /**
         * Pointer to right child
         */
        protected BinarySearchTreeNode<K, V> rightChild;


        /**
         *
         * @param key
         * @param value
         */
        @SuppressWarnings("unused")
        protected BinarySearchTreeNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         *
         * @param object
         * @return
         */
        @Override
        public boolean equals(Object object) {

            return equivalentTo(object);
        }

        /**
         *
         * @return
         */
        @Override
        public K key() {
            return this.key;
        }

        /**
         *
         * @param key The new key.
         */
        @Override
        public void key (K key) {
            this.key = key;
        }

//------------------------------------------------------------------------------

        // TODO - Remove from node class, put in tree class

        protected BinarySearchTreeNode<K, V> minNode(BinarySearchTreeNode<K, V> node) {

            if (node == null)
                return null;

            // Go as far left as possible
            return node.leftChild == null ? node : minNode(node.leftChild);
        }

//------------------------------------------------------------------------------

        /**
         *
         * @param parent
         * @param key
         * @return
         */
        protected boolean remove(BinarySearchTreeNode<K, V> parent, K key) {

            // Compare the key of current node in tree, and
            // the key we would like to remove
            int comparison = key.compareTo(this.key);

            // Look left
            if (comparison < 0) {

                // If there is no left child, the key was not in the tree, return false, otherwise,
                // continue to recurse down the tree looking to the remove the key
                return this.leftChild != null && this.leftChild.remove(this, key);

                // Look right
            } else if (comparison > 0) {

                // If there is no right child, the key was not in the tree, return false, otherwise,
                // continue to recurse down the tree looking to the remove the key
                return this.rightChild != null && this.rightChild.remove(this, key);

            // Found it, now we need to account for different cases
            } else {

                // The node we want to remove has two children
                if (this.leftChild != null && this.rightChild != null) {

                    // Bring the minimum value from the right subtree up
                    // to the current level (overwriting the value we want to remove)
                    this.key = rightChild.minNode(rightChild).key;

                    // Remove the key we just duplicated from its original position
                    this.rightChild.remove(this, this.key);

                    // The node we want to remove only has a left child
                } else if (parent.leftChild == this) {

                    parent.leftChild = leftChild != null ? leftChild : rightChild;

                    // The node we want to remove only has a right child
                } else if (parent.rightChild == this) {

                    parent.rightChild = (leftChild != null) ? leftChild : rightChild;
                }

                return true;
            }
        }

        /**
         *
         * @return
         */
        public String toString() {
            return asString();
        }

        public V value() {
            return this.value;
        }

        public void value(V value) {
            this.value = value;
        }

    }

}