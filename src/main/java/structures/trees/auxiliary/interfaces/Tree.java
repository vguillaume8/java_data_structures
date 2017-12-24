package structures.trees.auxiliary.interfaces;

import structures.auxiliary.interfaces.DataStructure;
import java.util.Iterator;

/**
 * Interface for Linked BinaryTrees
 *
 * @param <T> Generic type
 */
public interface Tree<T> extends DataStructure<T> {

    // Constants
    int IN_ORDER   = 0;
    int PRE_ORDER  = 1;
    int POST_ORDER = 2;
    int LEVEL_ORDER = 3;
    int DEFAULT_ORDER = IN_ORDER;

    /**
     * Displays information about the tree.
     * Size, levels, all 3 traversals
     */
    void display();

    /**
     * Returns the maximum level that the tree spans to
     *
     * @return Max depth of tree
     */
    int height();

    /**
     * Retrieves and removes a specified value from the tree
     *
     * @return Specified value
     */
    boolean remove(T value);

    /**
     * Returns ArrayList representation of tree
     * in specified order
     *
     * @return Array representation of tree
     */
    <T> T[] toArray(int traversalType, T[] array);

    /**
     * Returns ArrayList representation of tree
     * in pre-order
     *
     * @return Array representation of tree
     */
    <T> T[] toArray(T[] array);

    /**
     *
     * @return
     */
    <T> T[] toArray();

    /**
     *
     * @param traversalType
     * @param <T>
     * @return
     */
    <T> T[] toArray(int traversalType);

    /**
     *
     * @param traversalType
     * @return
     */
    String toString(int traversalType);

    /**
     * Basic iterator for iterating over Tree in order,
     * essentially, the Tree is converted to an ArrayList,
     * and then traversed with the ArrayList's iterator. This is
     * not space effective, or time effective, but it keeps the
     * code a lot simpler
     *
     * @param <T> Generic type
     */
    class TreeIterator<T> implements Iterator<T> {
        Iterator<T> iterator;

        /**
         * Constructs for iterating over Tree. By
         * default, traversal is done "pre-order," that is,
         * left, parent, right
         *
         * @param binaryTree Tree to be traversed
         */
        public TreeIterator(Tree<T> binaryTree) {
//            iterator = Arrays.asList(binaryTree.toArray());
        }

        /**
         * Determines whether or not there are more
         * nodes to be iterated over
         *
         * @return True if and only if there are still
         * unvisited nodes to return
         */
        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        /**
         * Returns next Node in in-order traversal
         *
         * @return Next Node
         */
        @Override
        public T next() {
            return iterator.next();
        }
    }
}
