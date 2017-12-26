package structures.trees;

import structures.util.Util;
import structures.vectors.ArrayList;
import structures.vectors.Queue;
import structures.vectors.classes.DynamicallySizedDataStructure;
import java.util.Iterator;

/**
 * Implementation of Binary Search Tree.
 *
 * @author Jabari Dash
 * @param <T> Generic type that extends java.lang.Comparable
 */
public class BinarySearchTree<T extends Comparable> extends DynamicallySizedDataStructure<T> implements Iterable {

    /**
     * Integer code for in-order tree traversal.
     */
    public static final int IN_ORDER = 0;

    /**
     * Integer code for pre-order tree traversal.
     */
    public static final int PRE_ORDER = 1;

    /**
     * Integer code for post-order tree traversal.
     */
    public static final int POST_ORDER = 2;

    /**
     * Integer code for level-order tree traversal.
     */
    public static final int LEVEL_ORDER = 4;

    /**
     * Integer code for default tree traversal order.
     */
    public static final int DEFAULT_ORDER = PRE_ORDER;

    /**
     * Integer code for printing tree horizontally.
     */
    public static final int HORIZONTAL_TREE_STRING = 5;

    /**
     * Integer code for printing tree vertically.
     */
    public static final int VERTICAL_TREE_STRING = 6;

    /**
     * Pointer to root node of the BinarySearchTree
     */
    private BinarySearchTreeNode<T> root;

    /**
     * Initialize an empty BinarySearchTree.
     */
    @SuppressWarnings("unused")
    public BinarySearchTree() {
        super();
        this.root(null);
    }

//------------------------------------------------------------------------------

    /**
     * Initialize a BinarySearchTree with a specified
     * set of values.
     *
     * @param values Specified values to insert into BinarySearchTree
     */
    @SuppressWarnings("unused")
    public BinarySearchTree(T[] values) {
        super();
        this.root(null);

        // Insert all the values into the tree
        for (T value : values) {
            this.insert(value);
        }
    }

//------------------------------------------------------------------------------

    /**
     * Determines whether or not a specified value is present in the tree.
     *
     * @param value Specified value to search for
     * @return True if and nly if the specified value is in the tree
     */
    @Override
    public boolean contains(T value) {

        // Must not be empty, and contain the value
        return !this.empty() && this.root().contains(value);
    }

//------------------------------------------------------------------------------

    /**
     * Display tree's important information to verify that
     * it was built correctly. This method is for development purposes
     */
    @SuppressWarnings("unchecked")
    public void display() {

        System.out.println(this.getClass().getSimpleName());
        System.out.println("Size: "         + this.size());
        System.out.println("Height: "       + this.height());
        System.out.println("Balanced: "     + this.isBalanced());
        System.out.println("Full: "         + this.isFull());
        System.out.println("Complete: "     + this.isComplete());
        System.out.println("Perfect: "      + this.isPerfect());
        System.out.println("In-order: "     + this.toString(IN_ORDER));
        System.out.println("Pre-order: "    + this.toString(PRE_ORDER));
        System.out.println("Post-order: "   + this.toString(POST_ORDER));
        System.out.println("Level-order: "  + this.toString(LEVEL_ORDER));
        System.out.println("Tree String:\n" + this.toTreeString());
    }

//------------------------------------------------------------------------------

    /**
     * Determines whether or not the BinarySearchTree is empty.
     *
     * @return True if and only if there are no elements in the tree.
     */
    @Override
    public boolean empty() {
        return this.size() == 0 && this.root() == null;
    }

//------------------------------------------------------------------------------

    /**
     * Returns the height of the tree.
     *
     * @return Max depth of the tree
     */
    public int height() {
        return BinarySearchTreeNode.height(this.root());
    }

//------------------------------------------------------------------------------

    /**
     * Inserts a specified value into the tree.
     *
     * @param value The specified value to insert
     */
    @Override
    public boolean insert(T value) {
        boolean result;

        if (this.empty()) {
            this.root(new BinarySearchTreeNode<T>(value, null, null));
            result = true;
        } else {

           result = this.root().insert(value);
        }

        // If the node was successfully inserted
        if (result) {
            this.incrementSize();
        }

        return result;
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
        return root.isBalanced(root) > -1;
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
        Queue<BinarySearchTreeNode<T>> queue;
        BinarySearchTreeNode<T> temp;
        BinarySearchTreeNode<T> left;
        BinarySearchTreeNode<T> right;
        boolean flag;

        // An empty tree is a complete tree
        if (root == null) {
            return true;
        }

        // Create an empty Queue
        queue = new Queue<>();

        // Flag variables which will be true
        // when non full node is seen
        flag = false;

        // Level order traversal using Queue
        queue.insert(root);

        while (!queue.empty()) {

            // Get node thing from Queue
            temp = queue.remove();

            // Get the children
            left = temp.leftChild();
            right = temp.rightChild();

            // Check if the node has a left child
            if (left != null) {

                // If we have seen a non full node, and
                // we also see a node (temp) with a non empty
                // left child, then the tree is not complete
                if (flag == true)
                    return false;

                // We must check the left child's children
                queue.insert(left);

            // If this is a non-full node, set the flag true
            } else {
                flag = true;
            }

            if (right != null) {

                if (flag == true)
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
     * @return
     */
    @SuppressWarnings("unused")
    public boolean isFull() {
        return this.isFull(root);
    }

//------------------------------------------------------------------------------

    /**
     *
     * @param node
     * @return
     */
    @SuppressWarnings("unused")
    private boolean isFull(BinarySearchTreeNode<T> node) {
        BinarySearchTreeNode<T> left;
        BinarySearchTreeNode<T> right;

        // An empty tree is full
        if (node == null) {
            return true;
        }

        // Get the left and right children
        left = node.leftChild();
        right = node.rightChild();

        // Either both must not be null
        if (left != null && right != null) {
            return isFull(node.leftChild()) && isFull(node.rightChild());
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
    private boolean isPerfect(BinarySearchTreeNode<T> node, int depth, int level) {
        BinarySearchTreeNode<T> left;
        BinarySearchTreeNode<T> right;

        // An empty tree is perfect
        if (node == null) {
            return true;
        }

        // Get the left and right children
        left = node.leftChild();
        right = node.rightChild();

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
     *
     * @return
     */
    public Iterator<T> iterator() {
        return iterator(PRE_ORDER);
    }

//------------------------------------------------------------------------------

    /**
     *
     * @param traversalType
     * @return
     */
    public Iterator<T> iterator(int traversalType) {
        Iterator iterator = null;

        switch (traversalType) {
            case PRE_ORDER:   iterator = new BinarySearchTreeIterator(this, PRE_ORDER);   break;
            case IN_ORDER:    iterator = new BinarySearchTreeIterator(this, IN_ORDER);    break;
            case POST_ORDER:  iterator = new BinarySearchTreeIterator(this, POST_ORDER);  break;
            case LEVEL_ORDER: iterator = new BinarySearchTreeIterator(this, LEVEL_ORDER); break;
        }

        return iterator;
    }

//------------------------------------------------------------------------------

    /**
     * Retrieves and removes a specified value from the tree.
     *
     * Algorithm inspired by http://www.algolist.net/Data_structures/Binary_search_tree/Removal
     *
     * @param value The specified value to remove from the tree
     * @return Specified value
     */
    public boolean remove(T value) {
        boolean result;

        // If the tree is empty
        if (this.empty()) {
            return false;

            // If the tree is not empty
        } else {

            // If the value we want to remove is at the root
            if (root.value().equals(value)) {
                BinarySearchTreeNode<T> auxRoot = new BinarySearchTreeNode<T>();

                auxRoot.leftChild(root);
                result = root.remove(auxRoot, value);
                root = auxRoot.leftChild();
            }

            // If the value might be somewhere in the tree
            else {
                result = root.remove(null, value);
            }
        }

        // If the removal was successful
        if (result) {
            this.decrementSize();
        }

        return result;
    }

//------------------------------------------------------------------------------

    /**
     * Returns a pointer the root node of the tree.
     *
     * @return Pointer to root of tree
     */
    protected BinarySearchTreeNode<T> root() {
        return this.root;
    }

//------------------------------------------------------------------------------

    /**
     * Sets the root pointer to a specified node
     *
     * @param root New root
     */
    protected void root(BinarySearchTreeNode<T> root) {
        this.root = root;
    }

//------------------------------------------------------------------------------

    /**
     * Returns ArrayList representation of tree
     * in pre-order.
     *
     * @return Array representation of tree
     */
    @SuppressWarnings("unchecked")
    public T[] toArray(int traversalType, T[] array) {

        /*
        If the tree is empty?
        TODO - User Util.ArrayCopy() instead, to maintain the array type
        */
        if (this.empty()) {
            return (T[]) new Comparable[0];
        }

        return this.root().toArray(traversalType, array);
    }

//------------------------------------------------------------------------------

    /**
     *
     * @param array Instance of array that is of the desired type.
     * @return
     */
    @SuppressWarnings("{unchecked, unused}")
    public T[] toArray(T[] array) {
        return this.toArray(DEFAULT_ORDER, array);
    }

//------------------------------------------------------------------------------

    /**
     * Returns an array representation of the tree in preorder.
     *
     * @return Array representation of the tree
     */
    @SuppressWarnings("{unused, unchecked}")
    public T[] toArray() {
        return (T[]) this.toArray(DEFAULT_ORDER, (T[]) new Comparable[0]);
    }

//------------------------------------------------------------------------------

    /**
     * Returns an array representation of the tree in a specified order.
     *
     * @param traversalType Order in which the tree should be traversed
     * @return Array version of the tree.
     */
    @SuppressWarnings("unchecked")
    public T[] toArray(int traversalType) {
        return (T[]) this.toArray(traversalType, (T[]) new Comparable[0]);
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
     * Returns a String representation of the tree
     * in a specified order.
     *
     * @param traversalType Specified order to traverse the tree
     * @return String representation of the tree
     */
    public String toString(int traversalType) {
        T[] array;

        switch (traversalType) {
            case IN_ORDER:    array = this.toArray(IN_ORDER);      break;
            case PRE_ORDER:   array = this.toArray(PRE_ORDER);     break;
            case POST_ORDER:  array = this.toArray(POST_ORDER);    break;
            case LEVEL_ORDER: array = this.toArray(LEVEL_ORDER);   break;
            default:          array = this.toArray(DEFAULT_ORDER); break;
        }

        return Util.ArrayToString(array);
    }

//------------------------------------------------------------------------------

    /**
     * Returns the tree as a {@code String} in tree format
     * for visualization
     */
    public String toTreeString() {

        return this.toTreeString(VERTICAL_TREE_STRING);
    }

//------------------------------------------------------------------------------

    /**
     *
     * @param orientation
     * @return
     */
    @SuppressWarnings("unused")
    public String toTreeString(int orientation) {
        String string;

        switch (orientation) {
            case HORIZONTAL_TREE_STRING: string = this.toTreeStringHorizontal();           break;
            case VERTICAL_TREE_STRING:   string = this.toTreeStringVertical();             break;
            default:                     string = this.toTreeStringVertical();             break;
        }

        return string;
    }

//------------------------------------------------------------------------------

    /**
     *
     * @return
     */
    @SuppressWarnings("unused")
    private String toTreeStringVertical() {
        return this.toTreeStringVertical("", true, "", root);
    }

    /**
     *
     * @param prefix
     * @param top
     * @param tree
     * @param node
     * @return
     */
    private String toTreeStringVertical(String prefix, boolean top, String tree, BinarySearchTreeNode<T> node) {
        BinarySearchTreeNode<T> left;
        BinarySearchTreeNode<T> right;
        String temp;

        // Get children nodes
        left = node.leftChild();
        right = node.rightChild();

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
        tree += " " + node.value() + "\n";

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
     * Auxiliary function for printing the tree as a vertical String
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
    @SuppressWarnings("unused")
    private String toTreeStringHorizontal() {
        return "";
    }

//==============================================================================

    /**
     *
     * @author Jabari Dash
     * @param <T> Generic type
     */
    private static class BinarySearchTreeIterator<T extends Comparable> implements Iterator<T> {
        private Iterator<T> iterator;

        /**
         * Constructs the iterator from a tree
         *
         * @param tree
         * @param traversalType
         */
        private BinarySearchTreeIterator(BinarySearchTree<T> tree, int traversalType) {

            // Convert to array, then arrayList, get the iterator
            this.iterator = new ArrayList<>(tree.toArray(traversalType)).iterator();
        }

        /**
         *
         * @return
         */
        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        /**
         *
         * @return
         */
        @Override
        public T next() {
            return iterator.next();
        }
    }
}