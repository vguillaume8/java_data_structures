package structures.trees;

import structures.commons.Pair;
import structures.vectors.ArrayList;

public class MinHeap<K extends Comparable<K>, V> implements Heap<K, V> {
    /**
     * Determines whether or not a specified key is present in the tree.
     *
     * @param key Key to search for in tree.
     * @return True if and only if the tree contains a node with the specified key.
     */
    @Override
    public boolean contains(K key) {
        return false;
    }

    /**
     * Returns the number of keys in the data structure.
     *
     * @return Number of keys in the DataStructure.
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * Determines whether or not a specified key-value pair is
     * present in the tree. The key must correspond to the value.
     *
     * @param key   Specified key.
     * @param value Specified value.
     * @return True if and only if there is a node that both the specified key and value.
     */
    @Override
    public boolean contains(K key, V value) {
        return false;
    }

    /**
     * Wrapper around {@code contains()} method for more readable syntax.
     *
     * @param key Key to search for in tree.
     * @return True if and only if the tree contains a node with the specified key.
     */
    @Override
    public boolean containsKey(K key) {
        return false;
    }

    /**
     * Determines whether or not the tree contains a given value. Note, there may
     * be multiple instances of the specified value, as the only thing that is unique
     * in the tree is the key. This search will stop after the first instance of the value
     * is found via pre-order traversal.
     *
     * @param value Value to look for.
     * @return True if and only if the value is in the tree.
     */
    @Override
    public boolean containsValue(V value) {
        return false;
    }

    /**
     * Retrieves and returns the value that corresponds to a specified key.
     *
     * @param key Specified key.
     * @return corresponding value to the key.
     */
    @Override
    public V get(K key) {
        return null;
    }

    /**
     * Returns the first key found that corresponds to a specified value. Traversal
     * is done in pre-order.
     *
     * @param value Specified value.
     * @return Corresponding key.
     */
    @Override
    public K getKey(V value) {
        return null;
    }

    /**
     * Returns the height of the tree.
     *
     * @return Max depth of the tree
     */
    @Override
    public int height() {
        return 0;
    }

    /**
     * Inserts a specified key-value pair into the tree.
     *
     * @param key   Key
     * @param value Value
     * @return True if and only if the key is not already in the tree.
     */
    @Override
    public boolean insert(K key, V value) {
        return false;
    }

    /**
     * Determines whether or not the tree is balanced. A balanced tree is defined
     * as a tree where for all subtrees of a given node, the maximum depth of the
     * left subtree differs from the maximum depth of right subtree by at most one.
     * An example follows:
     * <p>
     * TODO - Insert image of example
     *
     * @return True if and only if the tree is balanced.
     */
    @Override
    public boolean isBalanced() {
        return false;
    }

    /**
     * Determines whether or not the tree is complete. A complete tree is defined
     * as a tree where all levels are filled, except possibly the bottom (deepest) level.
     * In the event that the last level is not filled, all keys in tree are as left as possible.
     * An example follows:
     * <p>
     * TODO - Insert image of example
     *
     * @return True if and only if the above conditions are met.
     */
    @Override
    public boolean isComplete() {
        return false;
    }

    /**
     * Determines whether or not the tree is full. A full tree is defined
     * as a tree where all nodes other than leaf nodes have two children.
     * An example follows:
     * <p>
     * TODO - Insert image of example
     *
     * @return True if the above condition is met.
     */
    @Override
    public boolean isFull() {
        return false;
    }

    /**
     * Returns true if an only if the tree is perfect. A perfect tree is defined
     * as a tree whether all nodes that are not leaf nodes have two children, and
     * all leaf nodes are at the same level. An example follows:
     * <p>
     * TODO - Insert image of example
     *
     * @return True if and only if the above conditions are met.
     */
    @Override
    public boolean isPerfect() {
        return false;
    }

    /**
     * Returns an array of the keys in the tree in a specified order.
     *
     * @param traversalType Specified order.
     * @return Array of keys in the tree.
     */
    @Override
    public <K1> K1[] keys(int traversalType) {
        return null;
    }

    /**
     * Returns an ArrayList of key-value pairs in the a tree in a specified order.
     *
     * @param traversalType Specified order.
     * @return ArrayList of key-value pairs.
     */
    @Override
    public ArrayList<Pair<K, V>> pairs(int traversalType) {
        return null;
    }

    /**
     * Retrieves and removes a specified key from the tree.
     * <p>
     * Algorithm inspired by http://www.algolist.net/Data_structures/Binary_search_tree/Removal
     *
     * @param key The specified key to remove from the tree.
     * @return True if the node associate with the key was removed successfully.
     */
    @Override
    public boolean remove(K key) {
        return false;
    }

    /**
     * Returns the tree as a {@code String} in tree format
     * for visualization.
     *
     * @return String representation of tree (vertical)
     */
    @Override
    public String toTreeString() {
        return null;
    }

    /**
     * Returns an array of the keys in the tree in a specified order.
     *
     * @param traversalType Specified order.
     * @return Array of keys in the tree.
     */
    @Override
    public V[] values(int traversalType) {
        return (V[]) new Object[0];
    }
}
