package structures.vectors.auxiliary;

import structures.auxiliary.DynamicallySizedDataStructure;
import structures.auxiliary.Value;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

/**
 * Abstract class to be implemented by all types
 * of LinkedLists, and data structures that are dynamically
 * size, but we do not know how many elements we will start
 * with / have.
 *
 * @author Jabari Dash
 * @param <T> Generic type
 */
public abstract class ChainedDataStructure<T> extends DynamicallySizedDataStructure<T> implements LinearDataStructure<T> {

    private Node<T> head;   // Pointer to the front of the list

//------------------------------------------------------------------------------

    /**
     * Determines whether or not the list is empty
     *
     * @return True if and only if there are no elements in the list, otherwise false
     */
    public boolean empty() {
        int length = this.size();

        if (length == 0 && this.head() == null) {
            return true;
        }

        // Not a valid state
        if (length != 0 && this.head() == null) {
            throw new IllegalStateException("");
        }

        // Not a valid state
        if (length == 0 && this.head() != null) {
            throw new IllegalStateException("");
        }

        return false;
    }


//------------------------------------------------------------------------------

    /**
     * Initializes the LinkedListed to a specified length
     * with a provided default value
     *
     * @param length Specified lent of ChainedDataStructure
     * @param value Specified default value
     */
    public void init(int length, T value) {
        if (length < 0) {
            throw new IllegalArgumentException("Length must be at least 0");
        }

        this.init();

        for (int i = 0; i < length; i++) {
            this.insert(value);
        }
    }

//------------------------------------------------------------------------------

    /**
     * Initializes the ChainedDataStructure with a specified
     * array of values
     *
     * @param values Values to be inserted into the ChainedDataStructure
     */
    public void init(T[] values) {
        this.init();

        for (int i = 0; i < values.length; i++) {
            this.insert(values[i]);
        }
    }

//------------------------------------------------------------------------------

    /**
     * Determines whether or not a specified value is in the vector
     *
     * <br>
     * <br>
     * <strong>Time Complexity:</strong><br>
     * <strong>Best: </strong>&Omega;(1)<br>
     * <strong>Worst: </strong>O(n)<br>
     *
     * <br>
     * <strong>Space Complexity:</strong><br>
     * <strong>Avg: </strong>&Theta;(1)<br>
     *
     * @param value Specified value to search for
     * @return True if and only if the specified value if in the vector
     */
    public boolean contains(T value) {

        // Iterate over the list
        for (T v : this) {

            // Return true if we find a match
            if (v == value) {
                return true;
            }
        }

        // Otherwise, by default return false
        return false;
    }

//------------------------------------------------------------------------------

    /**
     * Sets pointer to head node via a specified Node pointer
     *
     * @param head New head pointer
     */
    protected void head(Node<T> head) {
        this.head = head;
    }

//------------------------------------------------------------------------------

    /**
     * Returns a pointer to the first Node in the chain
     *
     * @return Pointer to first Node in chain
     */
    protected Node<T> head() {
        return this.head;
    }

//------------------------------------------------------------------------------

    /**
     * Initializes an empty ChainedDataStructure
     */
    public void init() {
        super.init();
        this.head(null);
    }


    @Override
    public Iterator<T> iterator() {
        return new ChainedDataStructureIterator(this.head());
    }

    //------------------------------------------------------------------------------

    /**
     * Returns a String representation of the list
     * @return String version of the list
     */
    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ");

        // Iterate over list, appending
        // values with StringJoiner
        for (T value : this) {

            // Check for null values
            if (value == null) {
                stringJoiner.add("null");

            } else {
                stringJoiner.add(value.toString());
            }
        }

        return stringJoiner.toString();
    }

    public T[] toArray() {
        // TODO - Implement
        // TODO Unit test

        return null;
    }

//------------------------------------------------------------------------------

    /**
     * Node class for chaining together values in a LinkedList
     *
     * @author Jabari Dash
     * @param <T> Generic type
     */
    public static class Node<T> extends Value<T> {
        private Node<T> prev;   // Pointer to previous node in chain
        private Node<T> next;   // Pointer to previous node in chain

//------------------------------------------------------------------------------

        /**
         * Constructs a new node with a specified value, and pointers to its
         * previous and its next nodes
         *
         * @param value The specified value of teh node
         * @param prev Pointer to the node that comes before this node in the chain
         * @param next Pointer to the node that comes after this node in the chain
         */
        public Node(T value, Node<T> prev, Node<T> next) {
            this.value(value);
            this.prev(prev);
            this.next(next);
        }

//------------------------------------------------------------------------------

        /**
         * Inserts a data value into the chained nodes
         *
         * @param value Specified value to be inserted into the chain
         */
        public void insert(T value) {

            // If the following node is empty
            // we have reached the end of the list
            if (this.next() == null) {
                this.next(new Node<T>(value, this, null));

                // Otherwise, we must recurse to the end
            } else {
                this.next().insert(value);
            }
        }

//------------------------------------------------------------------------------

        /**
         * Returns the pointer to the next node that follows this node
         *
         * @return Pointer to the following node
         */
        public Node<T> next() {
            return this.next;
        }

//------------------------------------------------------------------------------

        /**
         * Sets the pointer to the next node
         *
         * @param next Designated node to be the next of this node
         */
        public void next(Node<T> next) {
            this.next = next;
        }

//------------------------------------------------------------------------------

        /**
         * Returns the pointer to the previous node that precedes this node
         *
         * @return Pointer to the previous node
         */
        public Node<T> prev() {
            return this.prev;
        }

//------------------------------------------------------------------------------

        /**
         * Sets the pointer to the prev node
         *
         * @param prev Designated node to be the prev of this node
         */
        public void prev(Node<T> prev) {
            this.prev = prev;
        }
    }

    /**
     * Iterates over a ChainedDataStructure via pointers
     *
     * @author Jabari Dash
     * @param <T> Generic type
     */
    public static class ChainedDataStructureIterator<T> implements Iterator<T> {
        private Node<T> node;

        /**
         * Constructor
         *
         * @param head Pointer to first node to start iterating with
         */
        public ChainedDataStructureIterator(Node<T> head) {
            this.node = head;
        }

        /**
         * Determines whether or not there are more Nodes
         * to iterate over
         *
         * @return True if and only if there are more nodes in the chain
         */
        @Override
        public boolean hasNext() {

            // If there is a node left to print, return false
            // if the node is null, we have traversed off the list
            return this.node == null ? false : true;
        }

        /**
         * Returns the value of the next Node in the chain
         *
         * @return Value of the next Node
         */
        @Override
        public T next() {
            // If there are no more values left, throw an Exception
            if (!hasNext()) {
                throw new NoSuchElementException("No element");
            }

            T value = node.value();
            this.node = this.node.next();

            return value;
        }
    }
}



