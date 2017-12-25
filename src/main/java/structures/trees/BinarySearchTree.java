package structures.trees;

import structures.vectors.classes.DynamicallySizedDataStructure;
//import structures.trees.auxiliary.interfaces.Tree;
import structures.vectors.ArrayList;

import java.util.Arrays;
import java.util.NoSuchElementException;

public final class BinarySearchTree<T extends Comparable> extends DynamicallySizedDataStructure<T> {

    public static final int IN_ORDER    = 0;
    public static final int PRE_ORDER   = 1;
    public static final int POST_ORDER  = 2;
    public static final int LEVEL_ORDER = 3;

    private int defaultOrder;
    private BinarySearchTreeNode<T> root;

    /**
     * Initialize an empty BinarySearchTree
     */
    public BinarySearchTree() {
        super();
        this.root(null);
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
    
    public boolean empty() {
        return this.size() == 0 && this.root() == null;
    }

//    
    public void init() {

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
    
    public int height() {
        return BinarySearchTreeNode.height(this.root());
    }

    /**
     * Retrieves and removes a specified value from the tree
     *
     * @param value
     * @return Specified value
     */
    
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

    
    public String toString() {
        return this.toString(defaultOrder);
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
    
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(int traversalType, T[] array) {

        ArrayList<T> arrayList = (ArrayList<T>) this.root().toArrayList(traversalType);

        // Convert the list into an array vida the ArrayList class
        T[] arr = arrayList.toArray(array);

        return (T[]) Arrays.copyOf(arr, arr.length, array.getClass());
    }


    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] array) {

        return this.toArray(defaultOrder, array);
    }

    
    @SuppressWarnings("unchecked")
    public <T> T[] toArray() {
        return (T[]) this.toArray(defaultOrder, new Comparable[0]);
    }

    
    public <T> T[] toArray(int traversalType) {
        return (T[]) this.toArray(traversalType, new Comparable[0]);
    }

    /**
     * Determines whether or not a specified value is present in the tree
     *
     * @param value Specified value
     * @return True if and nly if the specified value is in the tree
     */
    
    public boolean contains(T value) {
        if (this.empty()) {
            return false;
        }

        return this.root().contains(value);
    }

    /**
     * Inserts a specified value into the tree
     *
     * @param value The specified value to insert
     */
    
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