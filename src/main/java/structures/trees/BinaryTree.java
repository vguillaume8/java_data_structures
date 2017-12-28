package structures.trees;

import structures.commons.DataStructure;
import structures.commons.Pair;
import structures.vectors.ArrayList;

import java.util.Arrays;
import java.util.Iterator;

/**
 * All trees will implement this interface.
 *
 * @param <K> Generic type for keys
 * @param <V> Generic type for values
 */
public interface BinaryTree<K, V> extends DataStructure<K>, Iterable<Pair<K, V>> {

    /**
     * Integer code for in-order tree traversal.
     */
    int IN_ORDER = 0;

    /**
     * Integer code for pre-order tree traversal.
     */
    int PRE_ORDER = 1;

    /**
     * Integer code for post-order tree traversal.
     */
    int POST_ORDER = 2;

    /**
     * Integer code for level-order tree traversal.
     */
    int LEVEL_ORDER = 4;

    /**
     * Integer code for default tree traversal order.
     */
    int DEFAULT_ORDER = PRE_ORDER;

    /**
     * Integer code for printing tree horizontally.
     */
    int HORIZONTAL_TREE_STRING = 5;

    /**
     * Integer code for printing tree vertically.
     */
    int VERTICAL_TREE_STRING = 6;

    /**
     * Determines whether or not a specified key is present in the tree.
     *
     * @param key Key to search for in tree.
     * @return True if and only if the tree contains a node with the specified key.
     */
    boolean contains(K key);

    /**
     * Determines whether or not a specified key-value pair is
     * present in the tree. The key must correspond to the value.
     *
     * @param key Specified key.
     * @param value Specified value.
     * @return True if and only if there is a node that both the specified key and value.
     */
    boolean contains(K key, V value);

    /**
     * Wrapper around {@code contains()} method for more readable syntax.
     *
     * @param key Key to search for in tree.
     * @return True if and only if the tree contains a node with the specified key.
     */
    @SuppressWarnings("unused")
    boolean containsKey(K key);

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
    boolean containsValue(V value);

    /**
     * Display tree's important information to verify that
     * it was built correctly. This method is for development purposes
     */
    @SuppressWarnings("unused")
    default void display() {

        System.out.println(this.getClass().getSimpleName());
        System.out.println("Size: "           + this.size());
        System.out.println("Height: "         + this.height());
        System.out.println("Balanced: "     + this.isBalanced());
        System.out.println("Full: "         + this.isFull());
        System.out.println("Complete: "     + this.isComplete());
        System.out.println("Perfect: "      + this.isPerfect());
        System.out.println("Keys, In-order: "     + this.toString(IN_ORDER));
        System.out.println("Keys, Pre-order: "    + this.toString(PRE_ORDER));
        System.out.println("Keys, Post-order: "   + this.toString(POST_ORDER));
        System.out.println("Keys, Level-order: "  + this.toString(LEVEL_ORDER));
        System.out.println("Values, In-order: "     + Arrays.toString(this.values(IN_ORDER)));
        System.out.println("Values, Pre-order: "    + Arrays.toString(this.values(PRE_ORDER)));
        System.out.println("Values, Post-order: "   + Arrays.toString(this.values(POST_ORDER)));
        System.out.println("Values, Level-order: "  + Arrays.toString(this.values(LEVEL_ORDER)));
        System.out.println("BinaryTree String:\n"         + this.toTreeString());
    }

    /**
     * Retrieves and returns the value that corresponds to a specified key.
     *
     * @param key Specified key.
     * @return corresponding value to the key.
     */
    V get(K key);

    /**
     * Returns the first key found that corresponds to a specified value. Traversal
     * is done in pre-order.
     *
     * @param value Specified value.
     * @return Corresponding key.
     */
    @SuppressWarnings("unused")
    K getKey(V value);

    /**
     * Returns the height of the tree.
     *
     * @return Max depth of the tree
     */
    int height();

    /**
     * Inserts a specified key into the tree with an empty value.
     *
     * @param key The specified key to insert
     * @return True if the insertion was successful. An insertion is successful
     * if the specified key is not already in the tree.
     */
    @Override
    default boolean insert(K key) {
        return this.insert(key, null);
    }

    /**
     * Inserts a specified key-value pair into the tree.
     *
     * @param key Key
     * @param value Value
     * @return True if and only if the key is not already in the tree.
     */
    boolean insert(K key, V value);

    /**
     * Returns iterator that iterate over pairs in pre-order.
     *
     * @return Iterator in pre-order
     */
    @SuppressWarnings("unused")
    default Iterator<Pair<K, V>> iterator() {
        return new TreeIterator<>(this, DEFAULT_ORDER);
    }

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
    boolean isBalanced();

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
    boolean isComplete();

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
    boolean isFull();

    /**
     * Returns true if an only if the tree is perfect. A perfect tree is defined
     * as a tree whether all nodes that are not leaf nodes have two children, and
     * all leaf nodes are at the same level. An example follows:
     *
     * TODO - Insert image of example
     *
     * @return True if and only if the above conditions are met.
     */
    boolean isPerfect();

    /**
     * Returns an array of the keys in the tree in pre-order.
     *
     * @return Array of keys in the tree.
     */
    @SuppressWarnings("unused")
    default K[] keys() {
        return keys(DEFAULT_ORDER);
    }

    /**
     * Returns an array of the keys in the tree in a specified order.
     *
     * @param traversalType Specified order.
     * @return Array of keys in the tree.
     */
    K[] keys(int traversalType);

    /**
     * Returns an ArrayList of key-value pairs in the tree in pre-order.
     *
     * @return Key-value pairs in ArrayList.
     */
    @SuppressWarnings("unused")
    default ArrayList<Pair<K, V>> pairs() {
        return pairs(DEFAULT_ORDER);
    }

    /**
     * Returns an ArrayList of key-value pairs in the a tree in a specified order.
     *
     * @param traversalType Specified order.
     * @return ArrayList of key-value pairs.
     */
    ArrayList<Pair<K, V>> pairs(int traversalType);

    /**
     * Retrieves and removes a specified key from the tree.
     *
     * Algorithm inspired by http://www.algolist.net/Data_structures/Binary_search_tree/Removal
     *
     * @param key The specified key to remove from the tree.
     * @return True if the node associate with the key was removed successfully.
     */
    boolean remove(K key);

    /**
     * Removes a value from the tree. Note, avoid removing by value
     * if possible, as we must first find the node, then remove it.
     * Finding the node by value requires a full traversal of the tree,
     * whereas removing by key performs a binary search on the keys. The remove
     * operation is still O(n) in the worst case though because a simple binary search tree
     * may be linear.
     *
     * @param value Specified value to remove.
     * @return True if the specified value was present and removed from the tree.
     */
    @SuppressWarnings("unused")
    default boolean removeValue(V value) {

        // Find key via tree traversal
        K key = getKey(value);

        // If the key was not found, return false,
        // otherwise proceed to remove the key
        return key != null && remove(key);
    }

    /**
     * Returns a String representation of the tree
     * in a specified order.
     *
     * @param traversalType Specified order to traverse the tree
     * @return String representation of the tree
     */
    default String toString(int traversalType) {
        K[] array;

        switch (traversalType) {
            case IN_ORDER:    array = this.keys(IN_ORDER);      break;
            case PRE_ORDER:   array = this.keys(PRE_ORDER);     break;
            case POST_ORDER:  array = this.keys(POST_ORDER);    break;
            case LEVEL_ORDER: array = this.keys(LEVEL_ORDER);   break;
            default:          array = this.keys(DEFAULT_ORDER); break;
        }

        return Arrays.toString(array);
    }

    /**
     * Returns the tree as a {@code String} in tree format
     * for visualization.
     *
     * @return String representation of tree (vertical)
     */
    default String toTreeString() {

        return this.toTreeString(VERTICAL_TREE_STRING);
    }

    /**
     * Returns the tree as a {@code String} in specified orientation.
     *
     * @param orientation Specified orientation.
     * @return String of tree.
     */
    @SuppressWarnings("unused")
    default String toTreeString(int orientation) {
        String string;

        switch (orientation) {
            case HORIZONTAL_TREE_STRING: string = this.toTreeStringHorizontal();           break;
            case VERTICAL_TREE_STRING:   string = this.toTreeStringVertical();             break;
            default:                     string = this.toTreeStringVertical();             break;
        }

        return string;
    }

    /**
     * Returns tree as String on it's side.
     *
     * @return String representation of tree.
     */
    @SuppressWarnings("unused")
    String toTreeStringVertical();

    /**
     * Returns String representation of tree horizontally.
     *
     * @return BinaryTree string.
     */
    @SuppressWarnings("unused")
    String toTreeStringHorizontal();


    /**
     * Returns an array of the values in the tree in pre-order.
     *
     * @return Array of values in pre-order.
     */
    @SuppressWarnings("unused")
    default V[] values() {
        return this.values(DEFAULT_ORDER);
    }

    /**
     * Returns an array of the values in the tree in a specified order.
     *
     * @param traversalType Specified order.
     * @return Array of values in the tree.
     */
    @SuppressWarnings("unused")
    V[] values(int traversalType);

//==============================================================================

    /**
     * Iterator for Trees.
     *
     * @author Jabari Dash
     * @param <K> Generic type for keys that must be comparable
     * @param <V> Generic type for values.
     */
    class TreeIterator<K, V> implements Iterator<Pair<K, V>> {
        private Iterator<Pair<K, V>> iterator;

        /**
         * Initializes the iterator with a specified tree, and its ArrayLis of pairs in
         * a specified order.
         *
         * @param tree BinaryTree to instantiate iterator from.
         * @param traversalType Order in which to return the pairs.
         */
        @SuppressWarnings("unused")
        private TreeIterator(BinaryTree<K, V> tree, int traversalType) {
            this.iterator = tree.pairs(traversalType).iterator();
        }

//------------------------------------------------------------------------------

        /**
         * Determines whether or not there are more pairs in traversal.
         *
         * @return True if all pairs have not been seen.
         */
        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

//------------------------------------------------------------------------------

        /**
         * Returns next pair in traversal.
         *
         * @return Next pair, if any.
         */
        @Override
        public Pair<K, V> next() {
            return iterator.next();
        }
    }

}
