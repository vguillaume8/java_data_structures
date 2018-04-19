package structures.commons;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Abstract class that all linked
 * structures such as linked lists,
 * linked queues, etc will extend.
 *
 * @author Jabari Dash
 * @param <E>
 */
public abstract class LinkedStructure<E> implements DataStructure<E>, Iterable<E> {

    /**
     * Number of elements in linked list
     */
    protected int size;

    /**
     * Pointer to first node in list
     */
    protected Node<E> head;

    /**
     * Pointer to last node in list
     */
    protected Node<E> tail;

    /**
     * Determines whether or not this LinkedList is equal to
     * a provided object.
     *
     * @param object Object to compare this LinkedList with.
     * @return True if and only if their types are the same,
     * lengths are the same, and the contain all the same elements.
     */
    @Override
    public boolean equals(Object object) {

        // Object must be an LinkedList, and all elements must be equal, or object
        // must be this LinkedList itself
        return equivalentTo(object);
    }

    /**
     * Append a specified value  to the back of the list
     *
     * @param value Specified value to be inserted into the list
     * @return True to indicate the insertion was successful.
     */
    @Override
    public boolean insert(E value) {

        return insertTail(value);
    }

    /**
     * Prepends a new value to the front
     * of the structure.
     *
     * @param value New head value
     * @return True if insertion is successful.
     */
    protected boolean insertHead(E value) {
        Node<E> node = new Node<E>(value);

        if (empty()) {

            head = node;
            tail = node;

        } else {

            node.next = head;
            head.prev = node;
            head      = node;
        }

        size++;

        return true;
    }

    /**
     * Appends a new value to the back
     * of the structure.
     *
     * @param value New tail value
     * @return True if the insertion is successful.
     */
    protected boolean insertTail(E value) {
        Node<E> node = new Node<E>(value);

        if (empty()) {

            head = node;
            tail = node;

        } else {

            node.prev = tail;
            tail.next = node;
            tail      = node;
        }

        size++;

        return true;
    }

    /**
     * Returns an Iterator to iterate over the list.
     *
     * @return Iterator object.
     */
    @Override
    public Iterator<E> iterator() {

        return iterator(head);
    }

    /**
     * Provided the head node of any LinkedList,
     * this method returns an Iterator. This method is used
     * by instances of linked objects such as Queues, and Stacks.
     * That a similar function does not need to be implemented
     * multiple times.
     *
     * @param head The first node in the list.
     * @return Iterator object.
     */
    public static <E> Iterator<E> iterator(Node<E> head) {

        return new Iterator<E>() {

            Node<E> node = head;

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
            public E next() {
                // If there are no more keys left, throw an Exception
                if (!hasNext()) {
                    throw new NoSuchElementException("No element");
                }

                E value = node.value;
                node = node.next;

                return value;
            }
        };
    }

//------------------------------------------------------------------------------

    /**
     * Returns the number of elements in the list.
     *
     * @return Number of elements in list
     */
    @Override
    public int size() {
        return size;
    }

//------------------------------------------------------------------------------

    /**
     * Node class for chaining together keys in a LinkedList
     *
     * @author Jabari Dash
     * @param <T> Generic type
     */
    public static class Node<T> {
        public T       value;  // Value of the node
        public Node<T> prev;   // Pointer to previous node in chain
        public Node<T> next;   // Pointer to next node in chain

        /**
         * Constructs a new {@code Node} with a specified value.
         *
         * @param value Specified value of Node
         */
        public Node(T value) {

            this.value = value;
            this.prev  = null;
            this.next  = null;
        }

        /**
         * Returns a String representation of the node
         *
         * @return String version of the node's value
         */
        @Override
        public String toString() {

            return value == null ? "null" : value.toString();
        }

    }
}
