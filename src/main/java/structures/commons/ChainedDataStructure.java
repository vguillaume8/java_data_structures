package structures.commons;

import java.util.Arrays;
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

    /**
     * Pointer to the first node in the chain
     */
    private Node<T> head;

    /**
     * Pointer to the last node in the chain
     *
     * TODO - Use the tail in implementing classes such as linkedlist so insert last is O(1)
     */
    private Node<T> tail;

    /**
     * Constructs an empty {@code ChainedDataStructure}
     */
    public ChainedDataStructure() {
        super();
    }

    /**
     * Initializes the chain with a specified array of values;
     *
     * @param values Specified array of values from which the chain is initialized.
     */
    public ChainedDataStructure(T[] values) {
        super(values);
    }

    /**
     * Initializes the chain to a specified length, with a specified default
     * value in all positions.
     *
     * @param length Specified length of the chain.
     * @param value specified default value.
     */
    public ChainedDataStructure(int length, T value) {
        super(length, value);
    }

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
            throw new IllegalStateException("Fix code");
        }

        // Not a valid state
        if (length == 0 && this.head() != null) {
            throw new IllegalStateException("Fix code");
        }

        return false;
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

        return this.head.contains(value);
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

    /**
     * Returns an iterator for the chain.
     *
     * @return Iterator for the {@code ChainedDataStructure}
     */
    @Override
    @SuppressWarnings("unchecked")
    public Iterator<T> iterator() {
        return new ChainedDataStructureIterator(this.head());
    }

    //------------------------------------------------------------------------------

    /**
     * Returns a String representation of the list
     *
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

    /**
     * Returns array representation of the chain.
     *
     * @return Array version of the chain.
     */
    @Override
    @SuppressWarnings("uncheck")
    public T[] toArray() {

        return (T[]) this.toArray((T[])new Object[0]);
    }

    /**
     * Returns array representation of the data structure with
     * a specified type.
     *
     * @param array Array that specifies the type.
     * @return Array representation of the data structure.
     */
    @Override
    @SuppressWarnings("uncheck")
    public T[] toArray(T[] array) {

        array = (T[]) Arrays.copyOf(array, this.size(), array.getClass());

        int i = 0;

        for (Object value : this) {
            array[i] = (T) value;
            i++;
        }

        return array;
    }

//------------------------------------------------------------------------------

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
            return this.node != null;
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

    /**
     * Node class for chaining together values in a LinkedList
     *
     * @author Jabari Dash
     * @param <T> Generic type
     */
    public static class Node<T> implements Value<T> {
        private T value;        // Value of the node
        private Node<T> prev;   // Pointer to previous node in chain
        private Node<T> next;   // Pointer to next node in chain

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

        /**
         * Constructs empty Node
         */
        public Node() {

        }

//------------------------------------------------------------------------------

        /**
         *
         *
         * @param value
         * @return
         */
        protected boolean contains(T value) {
            return this.contains(this, value);
        }

        /**
         * Perform linear search for value.
         *
         * @param node First node in the chain to start searching from.
         * @param value Value to search for in the chain.
         * @return True if and only if the specified value was found in the chain.
         */
        protected boolean contains(Node<T> node, T value) {

            // If the node passed in is null
            // then we have reached a dead-end
            if (node == null) {
                return false;

                // Check if this Node is the value
            } else if (this.equals(node)) {
                return true;

            } else {
                return this.contains(this.next(), value);
            }
        }

//------------------------------------------------------------------------------

        /**
         * Recursively inserts a data value at the end of the chain.
         * Note, this method should be override in extending classes,
         * or pointers to the end of the chain should be used to make
         * this operation a {@code O(1)} operation, rather than {@code O(n)}.
         *
         * TODO - Make protected
         *
         * @param value Specified value to be inserted into the chain.
         * @return True if and only if the value was successfully inserted.
         */
        public boolean insert(T value) {

            // If the following node is empty
            // we have reached the end of the list
            if (this.next() == null) {
                this.next(new Node<T>(value, this, null));

                // Otherwise, we must recurse to the end
            } else {

                // TODO - VERY VERY DANGEROUS GET RID OF THIS IMMEDIATELY
                this.next().insert(value);
            }

            return true;
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

        /**
         * Determines whether or not two {@code Value} objects are equivalent
         * to each other.
         *
         * @param value Specified value to be compared to.
         * @return True if and only if their internal values are equivalent.
         */
        @Override
        public boolean equals(Value<T> value) {
            return this.equals(value.value());
        }

        /**
         * Returns a String representation of the node
         *
         * @return String version of the node's value
         */
        @Override
        public String toString() {

            if (this.value() == null) {
                return "null";

            } else {
                return this.value().toString();
            }
        }

        /**
         * Returns this internal value of the {@code Value} object.
         *
         * @return Internal value.
         */
        public T value() {
            return this.value;
        }

        /**
         * Sets the new internal value to a specified value.
         *
         * @param value New value of the node
         */
        public void value(T value) {
            this.value = value;
        }
    }

}



