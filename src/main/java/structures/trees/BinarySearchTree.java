package structures.trees;

import structures.util.Util;
import structures.vectors.classes.DynamicallySizedDataStructure;

/**
 * Implementation of Binary Search Tree
 *
 * @author Jabari Dash
 * @param <T> Generic type that extends java.lang.Comparable
 */
public class BinarySearchTree<T extends Comparable> extends DynamicallySizedDataStructure<T> {

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
    public static final int LEVEL_ORDER = 3;

    /**
     * Integer code for default tree traversal order.
     */
    private static final int DEFAULT_ORDER = PRE_ORDER;

    /**
     * Pointer to root node of the BinarySearchTree
     */
    private BinarySearchTreeNode<T> root;

    /**
     * Number of nodes in subtree
     */
    private int size;

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

        for (T value : values) {
            this.insert(value);
        }
    }

//------------------------------------------------------------------------------

    /**
     * Determines whether or not a specified value is present in the tree.
     *
     * @param value Specified value
     * @return True if and nly if the specified value is in the tree
     */
    @Override
    public boolean contains(T value) {

        // Must not be empty, and contain the value
        return !this.empty() && this.root().contains(value);
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
     * Determines whether or not the tree is balanced.
     *
     * @return True if and only if the tree is balanced.
     */
    @SuppressWarnings("unused")
    public boolean isBalanced() {
        return true;
    }

//------------------------------------------------------------------------------

    /**
     * Returns ArrayList representation of tree
     * in pre-order.
     *
     * @return Array representation of tree
     */
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(int traversalType, T[] array) {

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
     * @param <T>
     * @return
     */
    @SuppressWarnings("{unchecked, unused}")
    public <T> T[] toArray(T[] array) {
        return this.toArray(DEFAULT_ORDER, array);
    }

//------------------------------------------------------------------------------

    /**
     *
     * @param <T>
     * @return
     */
    @SuppressWarnings("{unused, unchecked}")
    public <T> T[] toArray() {
        return (T[]) this.toArray(DEFAULT_ORDER, new Comparable[0]);
    }

//------------------------------------------------------------------------------

    /**
     *
     * @param traversalType
     * @param <T>
     * @return
     */
    public <T> T[] toArray(int traversalType) {
        return (T[]) this.toArray(traversalType, new Comparable[0]);
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
            case IN_ORDER:   array = this.toArray(IN_ORDER);   break;
            case PRE_ORDER:  array = this.toArray(PRE_ORDER);  break;
            case POST_ORDER: array = this.toArray(POST_ORDER); break;
            default: throw new IllegalArgumentException("Traversal type {" + traversalType + "} note recognized");
        }

        return Util.ArrayToString(array);
    }

//------------------------------------------------------------------------------

    /**
     * Retrieves and removes a specified value from the tree.
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

        if (this.size() == 0) {
            this.root = null;
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
}