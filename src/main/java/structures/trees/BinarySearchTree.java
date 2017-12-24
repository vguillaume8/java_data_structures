package structures.trees;

import structures.auxiliary.classes.incomplete.DynamicallySizedDataStructure;
import structures.trees.auxiliary.classes.BinarySearchTreeNode;
import structures.trees.auxiliary.interfaces.Tree;
import structures.vectors.ArrayList;

import java.util.Arrays;
import java.util.NoSuchElementException;

public final class BinarySearchTree<T extends Comparable> extends DynamicallySizedDataStructure<T> implements Tree<T> {

    private BinarySearchTreeNode<T> root;

    /**
     * Initialize an empty BinarySearchTree
     */
    public BinarySearchTree() {
        this.init();
    }

    /**
     * Initialize a BinarySearchTree with a specified
     * set of values
     *
     * @param values Specified values to insert into BinarySearchTree
     */
    public BinarySearchTree(T[] values) {
        this.init();

        for (T value : values) {
            this.insert(value);
        }
    }

    /**
     * Display tree's important information to verify that
     * it was build correctly. This method is for development purposes
     */
    @SuppressWarnings("unchecked")
    public void display() {
        int size   = this.size();
        int height = this.height();

        System.out.println(this.getClass().getSimpleName());
        System.out.println("Size: "      + size);
        System.out.println("Height: "    + height);
        System.out.println("inorder: "   + this.toString(IN_ORDER));
        System.out.println("preorder: "  + this.toString(PRE_ORDER));
        System.out.println("postorder: " + this.toString(POST_ORDER));
    }

    /**
     *
     * @return
     */
    @Override
    public boolean empty() {
        return this.size() == 0 && this.root() == null;
    }

    /**
     * Returns a pointer the root node of the tree
     *
     * @return Pointer to root of tree
     */
    public BinarySearchTreeNode<T> root() {
        return this.root;
    }

    public void root(BinarySearchTreeNode<T> root) {
        this.root = root;
    }

    /**
     * Returns the height of the tree
     *
     * @return Max depth of the tree
     */
    @Override
    public int height() {
        return BinarySearchTreeNode.height(this.root());
    }

    /**
     * Retrieves and removes a specified value from the tree
     *
     * @param value
     * @return Specified value
     */
    @Override
    public boolean remove(T value) {
        boolean result;

        if (this.empty()) {
            throw new EmptyDataStructureException("Cannot remove from an empty Binary Search Tree");
        } else {
            if (root().value() == value) {
                BinarySearchTreeNode<T> temp = new BinarySearchTreeNode<T>(null, null, null);
                temp.leftChild(root());
                result = root.remove(root, value);
                root = temp.leftChild();
            } else {
                result = root().remove(null, value);
            }
        }

        // If the removal was successful
        if (result) {
            this.decrementSize();
        } else {

            throw new NoSuchElementException("Cannot remove value {" + value + "} because its not in the tree");
        }

        if (this.size() == 0) {
            this.root = null;
        }

        return result;
    }

    @Override
    public String toString() {
        return this.toString(DEFAULT_ORDER);
    }

    public String toString(int traversalType) {
        T[] array;

        switch (traversalType) {
            case IN_ORDER:   array = this.toArray(IN_ORDER);   break;
            case PRE_ORDER:  array = this.toArray(PRE_ORDER);  break;
            case POST_ORDER: array = this.toArray(POST_ORDER); break;
            default: throw new IllegalArgumentException("Traversal type {" + traversalType + "} note recognized");
        }

        return Arrays.toString(array);
    }

    /**
     * Returns ArrayList representation of tree
     * in pre-order
     *
     * @return Array representation of tree
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(int traversalType, T[] array) {

        // Convert the list into an array vida the ArrayList class
        T[] arr = ((ArrayList<T>) this.root().toArrayList(traversalType)).toArray();

        return (T[]) Arrays.copyOf(arr, arr.length, array.getClass());
    }


    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] array) {

        return this.toArray(DEFAULT_ORDER, array);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray() {
        return (T[]) this.toArray(new Comparable[0]);
    }

    @Override
    public <T> T[] toArray(int traversalType) {
        return (T[]) this.toArray(traversalType, new Comparable[0]);
    }

    /**
     * Determines whether or not a specified value is present in the tree
     *
     * @param value Specified value
     * @return True if and nly if the specified value is in the tree
     */
    @Override
    public boolean contains(T value) {
        if (this.empty()) {
            return false;
        }

        return this.root().contains(value);
    }

    /**
     * Initializes an empty tree
     */
    @Override
    public void init() {
        super.init();
        this.root = null;
    }

    /**
     * Inserts a specified value into the tree
     *
     * @param value The specified value to insert
     */
    @Override
    public boolean insert(T value) {
        boolean result;

        if (this.empty()) {
            this.root = new BinarySearchTreeNode<T>(value, null, null);
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
}