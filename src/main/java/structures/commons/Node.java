package structures.commons;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Node class for chaining together values in a LinkedList
 *
 * @author Jabari Dash
 * @param <T> Generic type
 */
public class Node<T> {
    public T value;        // Value of the node
    public Node<T> prev;   // Pointer to previous node in chain
    public Node<T> next;   // Pointer to next node in chain

//------------------------------------------------------------------------------

    /**
     * Constructs a new node with a specified value, and pointers to its
     * previous and its next nodes
     *
     * @param value The specified value of teh node
     * @param prev  Pointer to the node that comes before this node in the chain
     * @param next  Pointer to the node that comes after this node in the chain
     */
    public Node(T value, Node<T> prev, Node<T> next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }


    /**
     * @param value
     */
    public Node(T value) {
        this(value, null, null);
    }

//------------------------------------------------------------------------------

    /**
     * Recursively inserts a data value at the end of the chain.
     * Note, this method should be override in extending classes,
     * or pointers to the end of the chain should be used to make
     * this operation a {@code O(1)} operation, rather than {@code O(n)}.
     * <p>
     * TODO - Make protected
     *
     * @param value Specified value to be inserted into the chain.
     * @return True if and only if the value was successfully inserted.
     */
    public boolean insert(T value) {

        // If the following node is empty
        // we have reached the end of the list
        if (this.next == null) {
            this.next = new Node<T>(value, this, null);

            // Otherwise, we must recurse to the end
        } else {

            // TODO - VERY VERY DANGEROUS GET RID OF THIS IMMEDIATELY
            this.next.insert(value);
        }

        return true;
    }

//------------------------------------------------------------------------------


    /**
     * Returns a String representation of the node
     *
     * @return String version of the node's value
     */
    @Override
    public String toString() {

        if (this.value == null) {
            return "null";

        } else {
            return this.value.toString();
        }
    }

    /**
     * Iterates over a ChainedDataStructure via pointers
     *
     * @param <T> Generic type
     * @author Jabari Dash
     */
    public static class NodeIterator<T> implements Iterator<T> {
        private Node<T> node;

        /**
         * Constructor
         *
         * @param head Pointer to first node to start iterating with
         */
        public NodeIterator(Node<T> head) {
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

            T value = node.value;
            this.node = this.node.next;

            return value;
        }
    }
}